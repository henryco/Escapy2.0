package net.tindersamurai.escapy.map.stage;

public interface IEscapyStage {

	String getName();
	String getFile();

	boolean isDefault();

	static IEscapyStage DefaultImmutableStage (
			String name,
			String file,
			boolean isDefault
	) {
		return new IEscapyStage() {
			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getFile() {
				return file;
			}

			@Override
			public boolean isDefault() {
				return isDefault;
			}

			@Override
			public String toString() {
				return getClass().getName() +
						": {" + getName() + ": " +
							getFile() + ", " + isDefault() +
						"}";
			}
		};
	}
}