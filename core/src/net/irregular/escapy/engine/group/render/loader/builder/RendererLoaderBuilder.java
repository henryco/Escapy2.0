package net.irregular.escapy.engine.group.render.loader.builder;

import net.irregular.escapy.engine.group.render.loader.RendererLoader;

/**
 * @author Henry on 25/07/17.
 */
public interface RendererLoaderBuilder<T> {

	RendererLoader<T> build();


	static DefaultRendererLoaderBuilder Default() {
		return new DefaultRendererLoaderBuilder();
	}

}
