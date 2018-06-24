package net.tindersamurai.activecomponent.parser;

import lombok.Setter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.tindersamurai.activecomponent.comp.factory.EscapyComponentAnnotationFactory;
import net.tindersamurai.activecomponent.comp.factory.IEscapyComponentFactory;
import net.tindersamurai.activecomponent.core.UtilityCoreComponent;
import net.tindersamurai.activecomponent.obj.IEscapyObject;
import net.tindersamurai.activecomponent.obj.IEscapyObjectFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

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

			if (!reader.hasNext())
				return null;

			reader.next();

			if (reader.getPrefix() == null || !reader.getPrefix().equals(PREFIX_COMPONENT))
				return null;

			final UniComponent component = onComponent(reader);
			//noinspection unchecked
			return (T) (component != null ? component.instance : null);

		} catch (Exception e) {
			log.error("Escapy Active Component parsing error", e);
			e.printStackTrace();
		}

		return null;
	}


	private UniComponent onComponent (XMLStreamReader reader) throws XMLStreamException {

		Map<String, Object> args = new HashMap<>();

		val name = reader.getLocalName();
		val atrs = Helper.readAttributes(reader);

		val atrName = atrs.get(ATTR_NAME);
		val nullComponent = new UniComponent(Object.class, atrName, null);

		int count = -1;
		while (reader.hasNext()) {
			reader.next();

			if (reader.getPrefix() == null || reader.isEndElement())
				continue;

			count += 1;

			UniComponent uniComponent = null;
			switch (reader.getPrefix()) {

				// <o: ...
				case PREFIX_OBJECT: {
					uniComponent = onObject(reader);
					break;
				}

				// <c: ...
				case PREFIX_COMPONENT: {
					uniComponent = onComponent(reader);
					break;
				}
			}

			System.out.println(uniComponent);
			if (uniComponent != null)
				args.put(
						uniComponent.componentName == null ? Integer.toString(count) : uniComponent.componentName,
						uniComponent.instance == null ? "null" : uniComponent.instance
				);
			else
				args.put(Integer.toString(count), "null");
		}
		val component = componentFactory.createComponent(name, args);
		if (component == null)
			return nullComponent;
		return new UniComponent(component.getClass(), atrName, component);
	}


	private UniComponent onObject (XMLStreamReader reader) throws XMLStreamException {
		val name = reader.getLocalName();
		val atrs = Helper.readAttributes(reader);
		val methods = new LinkedList<Consumer<IEscapyObject>>();

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

					UniComponent uniComponent = null;
					switch (reader.getPrefix()) {

						// <m: ...
						case PREFIX_METHOD: {
							methods.add(onMethods(reader));
							break;
						}

						// <o: ...
						case PREFIX_OBJECT: {
							uniComponent = onObject(reader);
							break;
						}

						// <c: ...
						case PREFIX_COMPONENT: {
							uniComponent = onComponent(reader);
							break;
						}
					}
					if (uniComponent != null) {
						Entry[] n = new Entry[args.length + 1];
						System.arraycopy(args, 0, n, 0, args.length);
						n[n.length - 1] = new AbstractMap.SimpleEntry<>(
								uniComponent.componentClass, uniComponent.instance
						);
						args = n;
					}

					break;
				}

			}

			if (reader.isEndElement() && name.equals(reader.getLocalName())) {
				try {
					val returnClass = Class.forName(type);
					//noinspection unchecked
					val instance = objectFactory.createObject(constructor, args);
					for (Consumer<IEscapyObject> method : methods)
						method.accept(instance);
					val returnInstance = instance.getObject();
					return new UniComponent(returnClass, param, returnClass.cast(returnInstance));
				} catch (Exception e) {
					val MSG = "CANNOT INSTANCE OBJECT: " + name;
					log.error(MSG, e);
					throw new RuntimeException(MSG, e);
				}
			}
		}
		return new UniComponent(Object.class, null, null);
	}


	private Consumer<IEscapyObject> onMethods (XMLStreamReader reader) throws XMLStreamException {
		val name = reader.getLocalName();
		List<Entry<Class<?>, Object>> args = new ArrayList<>();

		while (reader.hasNext()) {
			reader.next();

			if (reader.getPrefix() == null)
				continue;

			switch (reader.getPrefix()) {

				case PREFIX_OBJECT: {
					val uniComponent = onObject(reader);
					args.add(new AbstractMap.SimpleEntry<>(
							uniComponent.componentClass,
							uniComponent.instance
					));
					break;
				}

				case PREFIX_COMPONENT: {
					val uniComponent = onComponent(reader);
					args.add(new AbstractMap.SimpleEntry<>(
							uniComponent.componentClass,
							uniComponent.instance
					));
					break;
				}
			}

			if (reader.isEndElement() && name.equals(reader.getLocalName()))
				//noinspection unchecked
				return ieo -> ieo.invokeMethod(name, args.toArray(new Entry[0]));
		}
		return v -> {};
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