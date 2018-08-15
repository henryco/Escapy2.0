package net.tindersamurai.activecomponent.parser;

import lombok.Setter;
import lombok.Value;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.activecomponent.comp.factory.EscapyComponentAnnotationFactory;
import net.tindersamurai.activecomponent.comp.factory.EscapyComponentFactoryListener;
import net.tindersamurai.activecomponent.comp.factory.IEscapyComponentFactory;
import net.tindersamurai.activecomponent.obj.IEscapyObject;
import net.tindersamurai.activecomponent.obj.IEscapyObjectFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

@Log
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

	private String contextRootPath;

	/**
	 * @param componentModules instances annotated by {@link net.tindersamurai.activecomponent.comp.annotation.EscapyComponentFactory}
	 */
	public XmlStreamComponentParser(Object ... componentModules) {
		log.info("::EAC:: NEW INSTANCE: XmlStreamComponentParser " + this.hashCode());
		setObjectFactory(IEscapyObjectFactory.Default());
		setComponentFactory(new EscapyComponentAnnotationFactory(componentModules));
	}

	@Override
	public <T> T parseComponent(String file) {

		Path path = Paths.get(file);
		val exists = Files.exists(path);

		String lastRoot = null;

		if (contextRootPath == null) {
			contextRootPath = path.getParent().toString();
			log.info("::EAC:: Context root path: " + contextRootPath + " | " + this.hashCode());
		} else if (!exists)
			path = Paths.get(contextRootPath + File.separator + file);

		if (contextRootPath != null && Files.exists(path)) {
			lastRoot = contextRootPath;
			contextRootPath = path.getParent().toString();
		}

		try (InputStream stream = Files.newInputStream(path)) {
			log.info("PARSE FILE: " + path);

			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLStreamReader reader = inputFactory.createXMLStreamReader(stream);

			if (!reader.hasNext()) {
				if (lastRoot != null) contextRootPath = lastRoot;
				return null;
			}

			reader.next();

			if (reader.getPrefix() == null || !reader.getPrefix().equals(PREFIX_COMPONENT)) {
				if (lastRoot != null) contextRootPath = lastRoot;
				return null;
			}

			final UniComponent component = onComponent(reader);
			if (lastRoot != null) contextRootPath = lastRoot;
			//noinspection unchecked
			return (T) (component != null ? component.instance : null);

		} catch (Exception e) {
			log.throwing(this.getClass().getName(), "parseComponent",
					new RuntimeException("Escapy Active Component parsing error", e)
			);
			e.printStackTrace();
		}

		if (lastRoot != null) contextRootPath = lastRoot;
		return null;
	}

	@Override
	public String getRootPath() {
		return contextRootPath;
	}

	@Override
	public void setRootPath(String path) {
		this.contextRootPath = path;
	}

	private UniComponent onComponent (XMLStreamReader reader) throws XMLStreamException {
		Map<String, Object> args = new HashMap<>();

		val name = reader.getLocalName();
		val atrs = Helper.readAttributes(reader);

		val atrName = atrs.get(ATTR_NAME);
		val nullComponent = new UniComponent(Object.class, atrName, null);

		val componentName = name.substring(1 + name.lastIndexOf(componentFactory.getNameSpaceSeparator()));
		final EscapyComponentFactoryListener listener;
		val factory = componentFactory.getFactory(name);
		if (factory instanceof EscapyComponentFactoryListener)
			listener = (EscapyComponentFactoryListener) factory;
		else listener = null;

		if (factory instanceof EscapyComponentParserProvider)
			((EscapyComponentParserProvider) factory).provideParser(this);

		boolean enter = true;
		if (listener != null && !listener.enterComponent(componentName))
			enter = false;

		int count = -1;
		while (reader.hasNext()) {
			reader.next();

			if (!enter) continue;

			if (reader.isCharacters()) {
				val text = reader.getText().trim();
				if (text.isEmpty())
					continue;

				args.put("0", text);
				continue;
			}

			if (reader.getPrefix() != null && !reader.isEndElement()) {
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

				if (uniComponent != null)
					args.put(
							uniComponent.componentName == null ? Integer.toString(count) : uniComponent.componentName,
							uniComponent.instance
//									== null ? "null" : uniComponent.instance
					);
				else
					args.put(Integer.toString(count),
							null
//							"null"
					);
				continue;
			}

			if (reader.isEndElement() && name.equals(reader.getLocalName())) {

				if (listener != null) {
					val component = listener.leaveComponent(componentName, componentFactory.createComponent(name, args));
					if (component == null)
						return nullComponent;
					return new UniComponent(component.getClass(), atrName, component);
				}

				val component = componentFactory.createComponent(name, args);
				if (component == null)
					return nullComponent;
				return new UniComponent(component.getClass(), atrName, component);
			}
		}
		return nullComponent;
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
					val t = new RuntimeException(MSG, e);
					log.throwing(getClass().getName(), "onObject", t);
					throw t;
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