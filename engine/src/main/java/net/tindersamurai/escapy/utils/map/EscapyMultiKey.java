package net.tindersamurai.escapy.utils.map;

public final class EscapyMultiKey<KEY_1, KEY_2> {

	private final KEY_1 key1;
	private final KEY_2 key2;

	public EscapyMultiKey(KEY_1 key1, KEY_2 key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof EscapyMultiKey)) return false;

		EscapyMultiKey<?, ?> key = (EscapyMultiKey<?, ?>) o;

		if (!key1.equals(key.key1)) return false;
		return key2.equals(key.key2);
	}

	@Override
	public int hashCode() {
		int result = key1.hashCode();
		result = 31 * result + key2.hashCode();
		return result;
	}
}