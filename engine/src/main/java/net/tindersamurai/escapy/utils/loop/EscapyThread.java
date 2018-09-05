package net.tindersamurai.escapy.utils.loop;

import lombok.Setter;
import lombok.extern.java.Log;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log
public class EscapyThread implements IEscapyThread {

	private final ExecutorService executorService;
	private final Queue<IEscapyUpdateble> bus;
	private final long sleep;

	private @Setter IEscapyUpdateble updateble;
	private Thread thread;

	/**
	 * @param sleep time in ms
	 */
	public EscapyThread(long sleep, IEscapyUpdateble updateble) {
		this.executorService = Executors.newSingleThreadExecutor();
		this.bus = new ConcurrentLinkedQueue<>();
		setUpdateble(updateble);
		this.sleep = sleep;
	}

	public EscapyThread() {
		this(5, null);
	}

	@Override
	public void nextTick(IEscapyUpdateble nextTick) {
		bus.offer(nextTick);
	}

	@Override
	public void start() {
		stop();
		executorService.submit(() -> {
			thread = createThread();
			thread.start();
		});
	}

	@Override
	public void stop() {
		executorService.submit(() -> {
			if (thread == null || !thread.isAlive()) return;
			thread.interrupt();

			while (thread.isAlive()) try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				log.throwing(this.getClass().getName(), "stop", e);
			}
		});
	}

	private Thread createThread() {
		return new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) try {
				final long t0 = System.nanoTime();
				Thread.sleep(sleep);
				final long t1 = System.nanoTime() - t0;

				final float delta = ((float) t1) / 1000000000f;

				IEscapyUpdateble queued;
				while ((queued = bus.poll()) != null)
					queued.update(delta);

				updateble.update(delta);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		});
	}
}