package net.tindersamurai.escapy.components.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.github.henryco.injector.GrInjector;
import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.components.model.ModelRenderer;
import net.tindersamurai.escapy.components.model.plain.light.LightSourceModel;
import net.tindersamurai.escapy.components.node.plain.data.NodeData;
import net.tindersamurai.escapy.components.stage.plain.LocationSwitcher;
import net.tindersamurai.escapy.context.game.screen.EscapyScreenCore;
import net.tindersamurai.escapy.control.manager.IEscapyControlManager;
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
import net.tindersamurai.escapy.utils.loop.EscapyThread;
import net.tindersamurai.escapy.utils.loop.IEscapyThread;
import net.tindersamurai.escapy.utils.loop.IEscapyUpdateble;

import javax.inject.Inject;
import java.util.Random;

@Provide("game-screen") @Log
public class GameScreen extends EscapyScreenCore implements IEscapyUpdateble {

	private final IEscapyControlManager controlManager;
	private final IEscapyModelRenderer renderer;
	private final LocationSwitcher locSetter;
	private final IEscapyThread escapyThread;

	private volatile boolean paused;

	@Inject
	public GameScreen(
			IEscapyModelRenderer renderer,
			LocationSwitcher locationSetter
	) {
		this.controlManager = IEscapyControlManager.instance();
		this.escapyThread = new EscapyThread(1, this);
		this.locSetter = locationSetter;
		this.renderer = renderer;
	}

	private IEscapyPhysics physicsManager;
	private IEscapyModel model;

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

		new DEBUG().physDebugConf().lightTransConf(node);

		resume();
	}

	@Override
	public void render(float delta) {
		if (!paused) {
			escapyThread.nextTick(d -> {
				if (!paused)
					controlManager.update(delta + d);
			});
		}
		renderer.render(model, delta);
	}

	@Override
	public void update(float delta) {
		if (!paused) {
			physicsManager.updateWorld(delta);
		}
	}

	@Override
	public void resume() {
		escapyThread.start();
		paused = false;
	}

	@Override
	public void pause() {
		escapyThread.stop();
		paused = true;
	}

	@Override
	public void hide() {
		pause();
	}




	/**
	 *  TODO REMOVE DEBUG ONLY!!!
	 */
	private final class DEBUG {

		private LightSource lightSource;
		private float r, g, b;

		private DEBUG physDebugConf() {
			log.info("PHYSICS DEBUG RENDERER PREPARE");

			Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
			final float pixelScale = physicsManager.getPixelScale();

			IEscapyCamera cam = GrInjector.getComponent("final-camera");
			IEscapyCamera camCam = new EscapyCamera(new Resolution(
					getGameContext().getDefaultScrWidth() / pixelScale,
					getGameContext().getDefaultScrHeight() / pixelScale
			));

			EscapyFBO fbo = new EscapyFrameBuffer(new Resolution(
					getGameContext().getDefaultScrWidth(),
					getGameContext().getDefaultScrHeight()
			));

			EscapyUtils.center(fbo.getSprite(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			((ModelRenderer) renderer).addExtraRenderData((camera, batch, delta) -> {
				fbo.begin(() -> {
					fbo.wipe();
					final float[] position = camera.update().getPosition();
					camCam.setCameraPosition(
							position[0] / pixelScale,
							position[1] / pixelScale
					);
					box2DDebugRenderer.render(physicsManager.getWorld(), camCam.update().getProjection());
				});
				batch.setProjectionMatrix(cam.update().getProjection());
				fbo.draw(batch);

				val random = new Random().nextFloat();
				if (random < 0.3f)
					r += 0.0025f;
				else if (random < 0.1f)
					g += 0.0025f;
				else if (random > 0.7)
					b += 0.0025f;

				if (r > 0.7f || r < 0.4f) r = 0.4f;
				if (g > 0.7f || r < 0.4f) g = 0.4f;
				if (b > 0.7f || r < 0.4f) b = 0.4f;

//				lightSource.setColor(new Color(r, g, b, 1f));
			});
			return this;
		}

		private void lightTransConf(IEscapyNode<NodeData> node) {
			IEscapyNode<NodeData> lightNode = node.getNode("LightsNode:Layer0LightPack:Layer0LightPackShift:LightType0:LightSource1");
			lightSource = ((LightSourceModel) lightNode.get().getModel()).getLightSource();
		}
	}

}