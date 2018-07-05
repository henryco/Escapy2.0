package net.tindersamurai.escapy.deprecated.modules.group.location.dep;

import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.group.map.core.layer.shift.LayerShift;
import net.tindersamurai.escapy.group.map.core.layer.shift.LayerShiftLogic;
import net.tindersamurai.escapy.utils.loader.EscapyInstanceLoader;
import net.tindersamurai.escapy.utils.loader.EscapyInstanced;

public class SimpleShiftAttrInstLoader implements EscapyInstanceLoader<LayerShift> {

	private final EscapyCamera camera;
	public SimpleShiftAttrInstLoader(EscapyCamera camera) {
		this.camera = camera;
	}

	@EscapyInstanced("move")
	public LayerShift moveShiftLogic(LayerShift shift) {

		LayerShiftLogic layerShiftLogic = new LayerShiftLogic() {
			float[] initial = camera.getPosition();
			@Override
			public float[] calculateShift(LayerShift shift) {
				float[] position = camera.getPosition();
				float tx = position[0] - initial[0];
				float ty = position[1] - initial[1];
				float[] direct = shift.getDirect();
				return new float[]{tx * direct[0], ty * direct[1]};
			}
		};
		shift.setLayerShiftLogic(layerShiftLogic);
		return shift;
	}
}