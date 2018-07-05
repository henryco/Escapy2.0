package net.tindersamurai.escapy.graphic.camera;

import com.badlogic.gdx.math.Matrix4;
import lombok.experimental.Delegate;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.utils.memento.EscapyCloneable;
import net.tindersamurai.escapy.utils.memento.Memento;

public class EscapyMemCamera
		implements IEscapyCamera, EscapyCloneable<EscapyMemCamera> {

	private final Memento<EscapyMemCamera> memento;
	private @Delegate IEscapyCamera workingInstance;

	private EscapyMemCamera(EscapyMemCamera camera) {
		this.workingInstance = new EscapyCamera(camera.getResolution());
		this.setCameraPosition(camera.getPosition());
		this.setZoom(camera.getZoom());
		this.update();
		this.memento = camera.memento;
	}

	public EscapyMemCamera(Resolution resolution) {
		this.workingInstance = new EscapyCamera(resolution);
		this.memento = new Memento<>(this);
	}

	@Override
	public EscapyMemCamera copy() {
		return new EscapyMemCamera(this);
	}

	public void save() {
		this.workingInstance = memento.save().get();
	}

	public void revert() {
		this.workingInstance = memento.revert().get();
	}

}
