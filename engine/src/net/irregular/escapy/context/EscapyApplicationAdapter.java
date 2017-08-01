package net.irregular.escapy.context;

import com.badlogic.gdx.ApplicationListener;
import dagger.ObjectGraph;
import net.irregular.escapy.context.annotation.Dante;
import net.irregular.escapy.context.annotation.EscapyAPI;
import net.irregular.escapy.context.game.EscapyGameContext;

/**
 * @author Henry on 27/06/17.
 */ @EscapyAPI @Dante
public final class EscapyApplicationAdapter implements ApplicationListener {

	private Class applicationListenerClass;
	private ApplicationListener delegateApplicationListener;
	private Object[] daggerModules;

	public EscapyApplicationAdapter(Class<? extends EscapyGameContext> applicationListener,
									Object... daggerModules) {
		this.setApplicationListenerClass(applicationListener);
		this.setDaggerModules(daggerModules);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void create() {

		final ObjectGraph objectGraph = ObjectGraph.create(this.getDaggerModules());
		final ApplicationListener applicationListener
				= objectGraph.get((Class<? extends ApplicationListener>) getInjectableGameClass());
		this.setDelegateApplicationListener(applicationListener);
		this.getDelegateApplicationListener().create();
	}

	@Override
	public void resize(int width, int height) {
		this.getDelegateApplicationListener().resize(width, height);
	}

	@Override
	public void render() {
		this.getDelegateApplicationListener().render();
	}

	@Override
	public void pause() {
		this.getDelegateApplicationListener().pause();
	}

	@Override
	public void resume() {
		this.getDelegateApplicationListener().resume();
	}

	@Override
	public void dispose() {
		this.getDelegateApplicationListener().dispose();
	}

	public Class getInjectableGameClass() {
		return applicationListenerClass;
	}

	private void setApplicationListenerClass(Class injectableGameClass) {
		this.applicationListenerClass = injectableGameClass;
	}

	private void setDaggerModules(Object[] daggerModules) {
		this.daggerModules = daggerModules;
	}

	private Object[] getDaggerModules() {
		return this.daggerModules;
	}

	private ApplicationListener getDelegateApplicationListener() {
		return delegateApplicationListener;
	}

	private void setDelegateApplicationListener(ApplicationListener applicationListener) {
		this.delegateApplicationListener = applicationListener;
	}

}
