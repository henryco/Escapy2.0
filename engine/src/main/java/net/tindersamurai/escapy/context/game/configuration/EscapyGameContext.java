package net.tindersamurai.escapy.context.game.configuration;

public interface EscapyGameContext {

	String getConfigsFilePath();

	int getDefaultScrWidth();

	int getDefaultScrHeight();

	String getWorkDir();

	String getResourcesDir();

}