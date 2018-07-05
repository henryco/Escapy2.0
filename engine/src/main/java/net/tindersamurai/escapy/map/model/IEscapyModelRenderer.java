package net.tindersamurai.escapy.map.model;

import net.tindersamurai.escapy.graphic.screen.Wipeable;

public interface IEscapyModelRenderer extends Wipeable {

	void render(IEscapyModel model, float delta);

}