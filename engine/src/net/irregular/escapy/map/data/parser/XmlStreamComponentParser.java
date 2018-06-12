package net.irregular.escapy.map.data.parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlStreamComponentParser implements EscapyComponentParser {

	@Override
	public <T> T parseComponent(String file) throws Exception {

		InputStream stream = Files.newInputStream(Paths.get(file));

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = inputFactory.createXMLStreamReader(stream);

		while (reader.hasNext()) {

			try {
				System.out.println(reader.getLocalName() + " : " + reader.getPrefix());
			} catch (Exception e) {}

			switch (reader.next()) {

				case XMLStreamReader.NAMESPACE: {
					System.out.println(reader.getPrefix());
				}

			}
		}

		reader.close();
		return null;
	}

}