package net.irregular.escapy.environment.main.group.location.dep;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanced;
import net.irregular.escapy.engine.graphic.camera.EscapyCamera;
import net.irregular.escapy.engine.group.map.core.layer.shift.LayerShift;
import net.irregular.escapy.engine.group.map.core.layer.shift.LayerShiftLogic;

/**
 * @author Henry on 21/07/17.
 */
public class SimpleShiftLogic implements EscapyInstanceLoader<LayerShiftLogic> {


	private final EscapyCamera camera;
	public SimpleShiftLogic(EscapyCamera camera) {
		this.camera = camera;
	}



	@EscapyInstanced("move")
	public LayerShiftLogic moveShiftLogic() {

		return new LayerShiftLogic() {
			@Override
			public float[] calculateShift(LayerShift shift) {
				// TODO: 21/07/17
				return new float[0];
			}
		};
	}



}
