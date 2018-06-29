package net.tindersamurai.escapy.map.stage;


public interface IEscapyStageContainer {

	String DEFAULT_FILE_NAME = "index.eacxml";

	IEscapyStage[] getStages();
	IEscapyStage getDefault();
	IEscapyStage getCurrent();

	IEscapyLocationContainer loadStage(String name);
	IEscapyLocationContainer loadStage();
}