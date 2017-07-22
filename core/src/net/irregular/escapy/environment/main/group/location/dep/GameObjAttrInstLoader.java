package net.irregular.escapy.environment.main.group.location.dep;

import com.badlogic.gdx.Gdx;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanceLoader;
import net.irregular.escapy.engine.env.utils.loader.EscapyInstanced;
import net.irregular.escapy.engine.group.map.core.object.GameObject;

/**
 * @author Henry on 21/07/17.
 */
public class GameObjAttrInstLoader implements EscapyInstanceLoader<GameObject> {


	@EscapyInstanced("FULL_SCREEN")
	public GameObject fullScreenAttribute(GameObject gameObject) {


		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();

		float sw = gameObject.getGameObjectRenderer().getRenderedWidth();
		float sh = gameObject.getGameObjectRenderer().getRenderedHeight();

		float scaleW = width / sw;
		float scaleH = height/ sh;

		float scale = Math.max(scaleW, scaleH);
		float[] position = gameObject.getObjectDetails().getPosition();

		position[0] += .5f * (width - sw);
		position[1] += .5f * (height - sh);

		gameObject.getGameObjectRenderer().setScale(scale);
		gameObject.getObjectDetails().setPosition(position);

		return gameObject;
	}
}
