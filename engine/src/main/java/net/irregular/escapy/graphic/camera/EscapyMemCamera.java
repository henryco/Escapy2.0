package net.irregular.escapy.graphic.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Delegate;
import net.irregular.escapy.graphic.screen.Resolution;
import net.irregular.escapy.utils.memento.EscapyCloneable;
import net.irregular.escapy.utils.memento.Memento;

public class EscapyMemCamera
		implements IEscapyMemoCam {

	@Data @AllArgsConstructor
	public final static class CameraData
			implements EscapyCloneable<CameraData> {

		private Resolution resolution;
		private float[] position;
		private float zoom;

		@Override
		public CameraData copy() {
			return new CameraData(
					new Resolution(
							resolution.width,
							resolution.height,
							resolution.bool
					), new float[] {
							position[0],
							position[1]
					}, zoom
			);
		}
	}

	private final Memento<CameraData> memento;
	private @Delegate IEscapyCamera camera;

	public EscapyMemCamera(Resolution resolution) {
		this.camera = new EscapyCamera(resolution);
		this.memento = new Memento<>(
				new CameraData(resolution,
						camera.getPosition(),
						camera.getZoom()
				)
		);
	}

	private static IEscapyCamera initializeCamera(IEscapyCamera camera,
												  Memento<CameraData> data) {
		camera.setCameraPosition(data.get().getPosition());
		camera.setResolution(data.get().getResolution());
		camera.setZoom(data.get().getZoom());
		return camera;
	}

	private static Memento<CameraData> initializeMemento(Memento<CameraData> memento,
														 IEscapyCamera camera) {
		memento.get().setResolution(camera.getResolution());
		memento.get().setPosition(camera.getPosition());
		memento.get().setZoom(camera.getZoom());
		return memento;
	}

	@Override
	public void save() {
		initializeMemento(memento, camera).save();
	}

	@Override
	public void revert() {
		this.camera = initializeCamera(
				camera, memento.revert()
		);
	}

}
