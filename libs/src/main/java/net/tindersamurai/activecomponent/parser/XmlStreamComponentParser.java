package net.tindersamurai.activecomponent.parser;

import lombok.Setter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.tindersamurai.activecomponent.comp.factory.EscapyComponentAnnotationFactory;
import net.tindersamurai.activecomponent.comp.factory.IEscapyComponentFactory;
import net.tindersamurai.activecomponent.core.UtilityCoreComponent;
import net.tindersamurai.activecomponent.obj.IEscapyObjectFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
public class XmlStreamComponentParser implements EscapyComponentParser {

	@Value private static final class UniComponent {
		Class<?> componentClass;
		String componentName;
		Object instance;
	}

	@Setter private
	IEscapyComponentFactory componentFactory;

	@Setter private
	IEscapyObjectFactory objectFactory;

	/**
	 * @param modules instances annotated by {@link net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory}
	 */
	public XmlStreamComponentParser(Object ... modules) {
		setObjectFactory(IEscapyObjectFactory.Default());
		setComponentFactory(new EscapyComponentAnnotationFactory(new UtilityCoreComponent(), modules));
	}

	@Override
	public <T> T parseComponent(String file) {

		try (InputStream stream = Files.newInputStream(Paths.get(file))) {

			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader reader = inputFactory.createXMLStreamReader(stream);

			while (reader.hasNext()) {
				reader.next();

				if (reader.getPrefix() == null)
					continue;

				switch (reader.getPrefix()) {

					case PREFIX_COMPONENT: {
						onComponent(reader);
						break;
					}

					case PREFIX_OBJECT: {
						System.out.println(onObject(reader));
						break;
					}

				}

			}

		} catch (Exception e) {
			log.error("Escapy Active Component parsing error", e);
			e.printStackTrace();
		}

		return null;
	}


	private UniComponent onComponent (XMLStreamReader reader) throws XMLStreamException {
		System.out.println(reader.getLocalName() + " : " + reader.getPrefix());
		return null;
	}


	private UniComponent onObject (XMLStreamReader reader) throws XMLStreamException {
		val name = reader.getLocalName();
		val atrs = Helper.readAttributes(reader);

		String type = Helper.readObjectType(atrs, name);
		String param = atrs.get(ATTR_NAME);
		String constructor = type;
		Entry[] args = {};

		while (reader.hasNext()) {
			switch (reader.next()) {

				// string constructor argument like:
				// <o:float>  ->> ARGUMENT <<-- </o:float>
				case XMLStreamReader.CHARACTERS: {

					val text = reader.getText().trim();
					if (text.isEmpty()) break;
					args = new Entry[]{new AbstractMap.SimpleEntry<>(String.class, text)};
					break;
				}

				// < something
				case XMLStreamConstants.START_ELEMENT: {
					// <new> ... </new>
					if (reader.getLocalName().equals(OBJECT_NEW)) {
						val cType = Helper.readAttributes(reader).get(ATTR_CLASS);
						if (cType != null) constructor = cType;
						break;
					}

					if (reader.getPrefix() == null)
						break;

					switch (reader.getPrefix()) {

						case PREFIX_METHOD: {
							// todo
							break;
						}

						case PREFIX_OBJECT: {
							val object = onObject(reader);
							Entry[] n = new Entry[args.length + 1];
							System.arraycopy(args, 0, n, 0, args.length);
							n[n.length - 1] = new AbstractMap.SimpleEntry<>(
									object.componentClass, object.instance
							);
							args = n;
							break;
						}

						case PREFIX_COMPONENT: {
							// todo
							break;
						}
					}

					break;
				}

			}

			if (reader.isEndElement() && name.equals(reader.getLocalName())) {
				try {
					val returnClass = Class.forName(type);
					val returnInstance = objectFactory.createObject(constructor, args).getObject();
					return new UniComponent(returnClass, param, returnClass.cast(returnInstance));
				} catch (Exception e) {
					val MSG = "CANNOT INSTANCE OBJECT: " + name;
					log.error(MSG, e);
					throw new RuntimeException(MSG, e);
				}
			}
		}
		return null;
	}


	private static final class Helper {

		private static Map<String, String> readAttributes(XMLStreamReader reader) {
			val map = new HashMap<String, String>();
			for (int i = 0; i < reader.getAttributeCount(); i++)
				map.put(reader.getAttributeLocalName(i), reader.getAttributeValue(i));
			return map;
		}

		private static String readObjectType(Map<String, String> atrs, String name) {
			String cl = atrs.get(ATTR_CLASS);
			if (cl == null)
				cl = "java.lang." + name.substring(0, 1).toUpperCase() + name.substring(1);
			return cl;
		}

	}
}