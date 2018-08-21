package net.tindersamurai.activecomponent.core;

public final class CoreComponentsProvider {

	public static Object[] getCoreComponents () {
		return new Object[] {
				new UtilityCoreComponent(),
				new FilesCoreComponent(),
				new VariablesCoreComponent()
		};
	}
}