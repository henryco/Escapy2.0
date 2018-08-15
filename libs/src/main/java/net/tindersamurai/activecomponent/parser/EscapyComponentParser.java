package net.tindersamurai.activecomponent.parser;


import net.tindersamurai.activecomponent.comp.factory.IEscapyComponentFactory;
import net.tindersamurai.activecomponent.obj.IEscapyObjectFactory;

import java.nio.file.NoSuchFileException;

public interface EscapyComponentParser {

	String PREFIX_COMPONENT = "c";
	String PREFIX_OBJECT = "o";
	String PREFIX_METHOD = "m";

	String ATTR_NAME = "name";
	String ATTR_CLASS = "class";

	String OBJECT_NEW = "new";

	void setComponentFactory(IEscapyComponentFactory factory);

	void setObjectFactory(IEscapyObjectFactory factory);

	<T> T parseComponent(String file) throws NoSuchFileException;

	String getRootPath();
	void setRootPath(String path);
}