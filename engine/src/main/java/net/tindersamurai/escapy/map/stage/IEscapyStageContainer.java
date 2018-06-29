package net.tindersamurai.escapy.map.stage;

public interface IEscapyStageContainer {

	IEscapyStage[] getStages();
	IEscapyStage getDefault();
	IEscapyStage getCurrent();

	IEscapyLocationContainer loadStage(String name);
	IEscapyLocationContainer loadStage();
}