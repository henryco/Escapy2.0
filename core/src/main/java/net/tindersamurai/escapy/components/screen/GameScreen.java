package net.tindersamurai.escapy.components.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.github.henryco.injector.GrInjector;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.model.ModelRenderer;
import net.tindersamurai.escapy.components.model.plain.light.LightSourceModel;
import net.tindersamurai.escapy.components.node.plain.NodeData;
import net.tindersamurai.escapy.components.stage.plain.LocationSwitcher;
import net.tindersamurai.escapy.context.game.Escapy;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;
import net.tindersamurai.escapy.graphic.camera.EscapyCamera;
import net.tindersamurai.escapy.graphic.camera.IEscapyCamera;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFBO;
import net.tindersamurai.escapy.graphic.render.fbo.EscapyFrameBuffer;
import net.tindersamurai.escapy.graphic.render.light.source.LightSource;
import net.tindersamurai.escapy.graphic.screen.Resolution;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.map.model.IEscapyModelRenderer;
import net.tindersamurai.escapy.map.node.IEscapyNode;
import net.tindersamurai.escapy.physics.IEscapyPhysics;
import net.tindersamurai.escapy.utils.EscapyUtils;

import javax.inject.Inject;

@Provide("game-screen") @Log
public class GameScreen extends EscapyScreenCore {

	private final IEscapyModelRenderer renderer;
	private final LocationSwitcher locationSetter;
	private Thread physicsThread;

	@Inject
	public GameScreen(
			IEscapyModelRenderer renderer,
			LocationSwitcher locationSetter
	) {
		this.locationSetter = locationSetter;
		this.renderer = renderer;
	}

	private IEscapyPhysics physicsManager;
	private IEscapyModel model;

	private DEBUG debuger;

	@Override
	public void show() {

		// get location directly from DI Container
		//noinspection unchecked
		IEscapyNode<NodeData> node = GrInjector.getComponent(IEscapyNode.class);
		log.info("Location tree structure: \n"+node.treeView());

		log.info("GET MODEL DATA DIRECTLY FROM LOCATION ROOT NODE");
		model = node.get().getModel();

		log.info("GET PHYSICS DATA DIRECTLY FROM LOCATION NODE");
		physicsManager = node.getNode("PhysicsNode").get().getPhys().getPhysicsManager();

		debuger = new DEBUG().physDebugConf().lightTransConf(node);
	}

	@Override
	public void render(float delta) {
		physicsManager.updateWorld(delta);
		renderer.render(model, delta);

		debuger.render();
	}

	@Override
	public void resize(int width, int height) {
		// todo
	}


	@Override
	public void pause() {

	}

	/**
	 *  TODO REMOVE DEBUG ONLY!!!
	 */
	private final class DEBUG {

		private LightSource lightSource;

		private DEBUG physDebugConf() {
			log.info("PHYSICS DEBUG RENDERER PREPARE");

			Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
			IEscapyCamera cam = GrInjector.getComponent("final-camera");
			EscapyFBO fbo = new EscapyFrameBuffer(new Resolution(
					getGameContext().getDefaultScrWidth(),
					getGameContext().getDefaultScrHeight()
			));

			EscapyUtils.center(fbo.getSprite(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			((ModelRenderer) renderer).addExtraRenderData((camera, batch, delta) -> {
				fbo.begin(() -> {
					fbo.wipe();
					box2DDebugRenderer.render(physicsManager.getWorld(), camera.update().getProjection());
				});
				batch.setProjectionMatrix(cam.update().getProjection());
				fbo.draw(batch);
			});
			return this;
		}

		private DEBUG lightTransConf(IEscapyNode<NodeData> node) {
			IEscapyNode<NodeData> lightNode = node.getNode("LightsNode:Layer0LightPack:Layer0LightPackShift:LightType0:LightSource1");
			lightSource = ((LightSourceModel) lightNode.get().getModel()).getLightSource();
			return this;
		}

		private void render() {
			lightSource.translate(1.5f, 0);
		}
	}

}