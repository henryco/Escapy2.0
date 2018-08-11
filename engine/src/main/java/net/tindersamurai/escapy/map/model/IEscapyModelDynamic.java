package net.tindersamurai.escapy.map.model;

import net.tindersamurai.escapy.map.model.sprite.IEscapySpriteProvider;

public interface IEscapyModelDynamic extends IEscapyModel {

	void setSpriteProvider(IEscapySpriteProvider spriteProvider);
}