package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.Getter;
import lombok.ToString;

import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.activecomponent.parser.EscapyComponentParser;
import net.tindersamurai.escapy.components.stage.plain.StageInfo;
import net.tindersamurai.escapy.components.stage.plain.StageInfo.StageMetaData.LocationMetaData;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.utils.files.EscapyFileMetaData;
import net.tindersamurai.escapy.utils.files.EscapyFiles;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.nio.file.NoSuchFileException;
import java.util.Map;


@Provide @Singleton @ToString @Log
public class GameStageInfo implements StageInfo {

	private final EscapyComponentParser parser;
	private final EscapyGameContext context;
	private final String mainConfigFile;
	private final String stageFileName;

	private @Getter StageMetaData defaultStage;
	private @Getter StageMetaData[] stages;

	@Inject
	public GameStageInfo(
			@Named("MainResConfigFile") String mainConfigFile,
			@Named("DefaultStageFileName") String stageFileName,
			EscapyComponentParser parser,
			EscapyGameContext context
	) {
		this.mainConfigFile = mainConfigFile;
		this.stageFileName = stageFileName;
		this.context = context;
		this.parser = parser;

		try {
			initialize();
		} catch (NoSuchFileException e) {
			log.throwing(this.getClass().getName(),
					"constructor GameStageInfo()", e
			);
		}
	}


	@SuppressWarnings("unchecked")
	private void initialize() throws NoSuchFileException {

		Map<String, Object> main = parser.parseComponent(mainConfigFile);
		val metas = (EscapyFileMetaData[]) main.get("stages");
		val def = (String) main.get("default");

		this.stages = new StageMetaData[metas.length];
		for (int i = 0; i < metas.length; i++) {

			val url = EscapyFiles.safetyPath(context.getConfigsFilePath() + metas[i].getUrl() + "/");

			val isDefault = metas[i].getId().equals(def);
			val stageFile = url + stageFileName;
			val stageName = metas[i].getName();
			val stageId = metas[i].getId();

			Map<String, Object> stage = parser.parseComponent(stageFile);
			val defLocation = stage.get("default");
			val locations = (EscapyFileMetaData[]) stage.get("locations");

			val locs = new LocationMetaData[locations.length];
			LocationMetaData defLoc = null;

			for (int k = 0; k < locations.length; k++) {

				val isDefaultLoc = locations[k].getId().equals(defLocation);
				val locFile = url + EscapyFiles.safetyPath(locations[k].getUrl());
				val locName = locations[k].getName();
				val locId = locations[k].getId();

				locs[k] = new LocationMetaData(new EscapyFileMetaData(locId, locFile, locName), isDefaultLoc);
				if (isDefaultLoc)
					defLoc = locs[k];
			}

			stages[i] = new StageMetaData(new EscapyFileMetaData(stageId, stageFile, stageName), locs, defLoc, isDefault);

			if (isDefault)
				this.defaultStage = stages[i];
		}

	}



}