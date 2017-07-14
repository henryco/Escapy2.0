package net.irregular.escapy.engine.map.layer.shift;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanced;

/**
 * @author Henry on 14/07/17.
 */
public class LayerShiftLogicInstancer implements EscapyInstanceLoader {



	@EscapyInstanced("default")
	public LayerShiftLogic defaultShiftLogic() {
		return new LayerShiftLogic() {

			@Override
			public float[] calculateShift(LayerShift shift) {
				// TODO: 14/07/17
				return new float[0];
			}
		};
	}

}
