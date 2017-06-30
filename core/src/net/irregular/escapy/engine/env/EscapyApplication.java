package net.irregular.escapy.engine.env;

import com.badlogic.gdx.ApplicationListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Henry on 28/06/17.
 */
public class EscapyApplication implements ApplicationListener {

	private ApplicationListener applicationAdapter;

	public EscapyApplication(Class<? extends ApplicationListener> applicationListener,
							 Object... daggerModules) {

		Class[] configurationClasses = null;
		//TODO

		List<Object> objectList = new ArrayList<>();
		if (configurationClasses != null) {
			for (Class clazz: configurationClasses) {
				try {
					objectList.add(clazz.newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		objectList.addAll(Arrays.asList(daggerModules));
		this.applicationAdapter = new EscapyAdapter(applicationListener, objectList.toArray(new Object[0]));
	}


	@Override
	public void create() {
		applicationAdapter.create();
	}

	@Override
	public void resize(int width, int height) {
		applicationAdapter.resize(width, height);
	}

	@Override
	public void render() {
		applicationAdapter.render();
	}

	@Override
	public void pause() {
		applicationAdapter.pause();
	}

	@Override
	public void resume() {
		applicationAdapter.resume();
	}

	@Override
	public void dispose() {
		applicationAdapter.dispose();
	}
}
