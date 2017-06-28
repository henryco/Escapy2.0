package net.irregular.escapy.engine.graphic.render;


import net.irregular.escapy.engine.graphic.render.mapping.HeightMapRenderer;
import net.irregular.escapy.engine.graphic.render.mapping.NormalMapRenderer;
import net.irregular.escapy.engine.graphic.render.mapping.TextureRenderer;

/**
 * @author Henry on 25/06/17.
 */
public interface EscapyRenderable extends TextureRenderer, NormalMapRenderer, HeightMapRenderer {

}
