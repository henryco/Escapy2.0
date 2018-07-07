package net.tindersamurai.escapy.components.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.henryco.injector.meta.annotations.Module;
import com.github.henryco.injector.meta.annotations.Provide;
import net.tindersamurai.escapy.components.config.ConfigModule;
import net.tindersamurai.escapy.context.game.configuration.EscapyGameContext;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.camera.EscapyMemCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyMemoCam;
import net.tindersamurai.escapy.graphic.screen.Resolution;

import javax.inject.Singleton;

@Module(componentsRootClass = ModelModule.class,
		include = { ConfigModule.class }
) public final class ModelModule {


	@Provide @Singleton
	public IEscapyMemoCam provideSplashCamera (
			EscapyGameContext context
	) {

		float dw = context.getDefaultScrWidth();
		float dh = context.getDefaultScrHeight();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		System.out.println("c: " + dw + " : " + dh);
		System.out.println("s: " + w + " : " + h);
		System.out.println(dw / dh);
		System.out.println(w / h);

//		return new EscapyMemCamera(new Resolution (
//				(int) end_w,
//				(int) h
//		));
		return new EscapyMemCamera(new Resolution (
				context.getDefaultScrWidth(),
				context.getDefaultScrHeight()
		));
	}

	@Provide("default-batch") @Singleton
	public final Batch provideBatch() {
		return new SpriteBatch();
	}

}