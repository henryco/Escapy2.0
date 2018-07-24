package net.tindersamurai.escapy.components.stage.plain;

import lombok.Value;
import net.tindersamurai.escapy.utils.files.EscapyFileMetaData;

public interface StageInfo {

	@SuppressWarnings("WeakerAccess")
	@Value final class StageMetaData {

		@Value public static final class LocationMetaData {
			private EscapyFileMetaData file;
			private boolean _default;
		}

		private EscapyFileMetaData file;
		private LocationMetaData[] locations;
		private LocationMetaData defaultLocation;
		private boolean _default;
	}

	StageMetaData getDefaultStage();
	StageMetaData[] getStages();

}