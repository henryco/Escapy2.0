package net.tindersamurai.escapy.components.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.graphic.camera.EscapyMemCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.screen.Resolution;

import javax.inject.Singleton;

@Module(componentsRootClass = ModelModule.class,
		include = { ConfigModule.class }
) @Log public final class ModelModule {


	@Provide("main-camera") @Singleton
	public IEscapyMemoCam provideSplashCamera (
			Resolution resolution ) {
		return new EscapyMemCamera(resolution);
	}

	@Provide("final-camera") @Singleton
	public IEscapyMemoCam provideLastCamera (
			EscapyGameContext context
	) {
		final float w = context.getDefaultScrWidth();
		final float h = context.getDefaultScrHeight();
		final float gw = Gdx.graphics.getWidth();
		final float gh = Gdx.graphics.getHeight();

		float scH = h / gh;
		float scW = w / gw;

		final Resolution resolution;
		if (scW > scH) resolution = new Resolution(((int) (gw * scH)), (int) h);
		else resolution = new Resolution(((int) w), (int) (gh * scW));

		log.info("Final stage camera resolution: " + resolution);

		return new EscapyMemCamera(resolution) {{
			setCameraPosition(gw * 0.5f, gh * 0.5f);
		}};
	}

	@Provide("default-batch") @Singleton
	public final Batch provideBatch() {
		return new SpriteBatch();
	}

	@Provide
	public Resolution defaultResolution (
			EscapyGameContext context ) {
		return new Resolution (
				context.getDefaultScrWidth(),
				context.getDefaultScrHeight()
		);
	}

}