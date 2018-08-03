package net.tindersamurai.escapy.components.control.plain;

import lombok.Getter;
import net.tindersamurai.escapy.control.IEscapyControllerListener;

import java.lang.reflect.Array;
import java.util.HashSet;

public abstract class AbstractControllerListener <T>
		implements IEscapyControllerListener {

	private @Getter T[] userData;

	@Override
	public void addUserData(Object data) {

		if (!data.getClass().getName().equals(getDataType().getName()))
			throw new RuntimeException("Incompatible UserData type," +
					"requires: " + getDataType() + ", found: " + data.getClass());

		userData = new HashSet<T>() {{
			if (userData != null) {
				for (T datum : userData)
					if (datum != null)
						add(datum);
			}
			add((T) data);
		}}.toArray((T[]) Array.newInstance(getDataType(), 0));
	}

	@Override
	public void removeUserData(Object data) {
		if (userData == null || userData.length == 0) return;

		T[] arr = (T[]) Array.newInstance(getDataType(), userData.length - 1);
		int k = 0; for (T anUserData : userData) {
			if (anUserData != data) {
				arr[k] = anUserData;
				k++;
			}
		}

		userData = arr;
	}


	@Override
	public void dispose() {
		userData = null;
	}

	protected abstract Class<T> getDataType();
}