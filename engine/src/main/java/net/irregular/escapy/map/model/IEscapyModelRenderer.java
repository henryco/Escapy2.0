package net.irregular.escapy.map.model;

import net.irregular.escapy.graphic.screen.Wipeable;
public interface IEscapyModelRenderer extends Wipeable {

	void render(IEscapyModel model, float delta);

}