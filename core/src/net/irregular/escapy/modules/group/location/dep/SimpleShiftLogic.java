package net.irregular.escapy.modules.group.location.dep;

import net.irregular.escapy.graphic.camera.EscapyCamera;
import net.irregular.escapy.group.map.core.layer.shift.LayerShift;
import net.irregular.escapy.group.map.core.layer.shift.LayerShiftLogic;
import net.irregular.escapy.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.utils.loader.EscapyInstanced;

public class SimpleShiftLogic implements EscapyInstanceLoader<LayerShiftLogic> {

	private final EscapyCamera camera;
	public SimpleShiftLogic(EscapyCamera camera) {
		this.camera = camera;
	}

	@EscapyInstanced("move")
	public LayerShiftLogic moveShiftLogic(LayerShiftLogic shiftLogic) {

		return new LayerShiftLogic() {
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

	}
}