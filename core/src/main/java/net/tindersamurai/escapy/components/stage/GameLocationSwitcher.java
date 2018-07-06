package net.tindersamurai.escapy.components.stage;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.Getter;
import lombok.val;
import net.tindersamurai.escapy.components.stage.plain.LocationSetter;
import net.tindersamurai.escapy.components.stage.plain.StageInfo;
import net.tindersamurai.escapy.components.stage.plain.StageInfo.StageMetaData;
import net.tindersamurai.escapy.components.stage.plain.StageInfo.StageMetaData.LocationMetaData;
import net.tindersamurai.escapy.map.location.IEscapyLocationHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

import static net.tindersamurai.escapy.map.location.IEscapyLocationHandler.*;

@Provide @Singleton
public class GameLocationSwitcher
		implements LocationSetter, HandlerListener {

	private final IEscapyLocationHandler locationHandler;
	private final @Getter StageInfo stageInfo;

	private LocationMetaData currentLocation;
	private StageMetaData currentStage;

	private boolean stageUpdated;

	@Inject
	public GameLocationSwitcher(
			IEscapyLocationHandler locationHandler,
			StageInfo stageInfo
	) {
		this.locationHandler = locationHandler;
		this.stageInfo = stageInfo;

		currentStage = stageInfo.getDefaultStage();
		currentLocation = currentStage.getDefaultLocation();

		stageUpdated = false;
	}

	@Override
	public boolean setLocation(String id) {

		for (val l : currentStage.getLocations()) {
			if (l.getFile().getId().equals(id)) {
				currentLocation = l;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setStage(String id) {

		for (val s : stageInfo.getStages()) {
			if (s.getFile().getId().equals(id)) {
				stageUpdated = true;
				currentStage = s;
				return true;
			}
		}
		return false;
	}

	@Override
	public LocationMetaData getLocationMetaData() {
		return currentLocation;
	}

	@Override
	public void locationUpdate(String file) {
		if (stageUpdated) reset();
	}

	@Override
	public void reset() {
		locationHandler.reset();
		stageUpdated = false;
	}

}