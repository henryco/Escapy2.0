package net.irregular.escapy.environment.main.group.location.dep;

import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanced;
import net.irregular.escapy.engine.group.map.core.object.GameObject;
import net.irregular.escapy.engine.group.map.core.object.ObjectDetails;

/**
 * @author Henry on 21/07/17.
 */
public class GameObjAttrInstLoader implements EscapyInstanceLoader<GameObject> {


	@EscapyInstanced("FULL_SCREEN")
	public GameObject fullScreenAttribute(GameObject gameObject) {

		ObjectDetails objectDetails = gameObject.getObjectDetails();

		return gameObject;
	}
}
