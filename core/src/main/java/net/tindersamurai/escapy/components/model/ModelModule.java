package net.tindersamurai.escapy.components.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.screen.Resolution;

import javax.inject.Singleton;

@Module(componentsRootClass = ModelModule.class,
		include = { ConfigModule.class }
) public final class ModelModule {


	@Provide @Singleton
	public IEscapyCamera provideSplashCamera() {
		return new EscapyCamera(
				new Resolution(
						Gdx.graphics.getWidth(),
						Gdx.graphics.getHeight()
				)
		);
	}

	@Provide("default-batch") @Singleton
	public final Batch provideBatch() {
		return new SpriteBatch();
	}

}