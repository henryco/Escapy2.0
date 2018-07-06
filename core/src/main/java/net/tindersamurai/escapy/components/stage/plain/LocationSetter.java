package net.tindersamurai.escapy.components.stage.plain;


import static net.tindersamurai.escapy.components.stage.plain.StageInfo.StageMetaData.*;

public interface LocationSetter {
	LocationMetaData getLocationMetaData();
	boolean setLocation(String id);
	boolean setStage(String id);

	void reset();

	StageInfo getStageInfo();
}