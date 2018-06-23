package net.tindersamurai.activecomponent.parser;

import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.tindersamurai.activecomponent.comp.factory.EscapyComponentAnnotationFactory;
import net.tindersamurai.activecomponent.comp.factory.IEscapyComponentFactory;
import net.tindersamurai.activecomponent.core.UtilityCoreComponent;
import net.tindersamurai.activecomponent.obj.IEscapyObjectFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class XmlStreamComponentParser implements EscapyComponentParser {

	private @Setter
	IEscapyComponentFactory componentFactory;

	private @Setter
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
						onObject(reader);
						break;
					}

				}

			}

		} catch (Exception e) {
			log.error("Escapy Active Component parsing error", e);
		}

		return null;
	}


	private void onComponent (XMLStreamReader reader) {
		System.out.println(reader.getLocalName() + " : " + reader.getPrefix());
	}

	private void onObject (XMLStreamReader reader) {
		System.out.println(reader.getLocalName() + " : " + reader.getPrefix());
	}

}