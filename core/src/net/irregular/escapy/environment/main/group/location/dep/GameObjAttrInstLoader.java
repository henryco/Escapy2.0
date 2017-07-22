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

//		System.out.println(width + " : " + height + " : " + sw + " : " + sh + " : " + scaleW + " : " + scaleH);

		float scale = Math.max(scaleW, scaleH);
//		scale = 0.5f;
		gameObject.getGameObjectRenderer().setScale(scale);

		return gameObject;
	}
}
