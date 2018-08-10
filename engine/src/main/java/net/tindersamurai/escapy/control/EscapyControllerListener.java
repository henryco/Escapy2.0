package net.tindersamurai.escapy.control;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;
import net.tindersamurai.escapy.control.IEscapyControllerListener;

import java.lang.reflect.Array;
import java.util.HashSet;

@NoArgsConstructor
public abstract class EscapyControllerListener<T>
		implements IEscapyControllerListener {

	private @Getter T[] userData;

	public EscapyControllerListener(T ... userData) {
		if (userData != null)
			for (val d : userData)
				addUserData(d);
	}

	@Override
	public void addUserData(Object data) {

		if (!getDataType().isAssignableFrom(data.getClass()))
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