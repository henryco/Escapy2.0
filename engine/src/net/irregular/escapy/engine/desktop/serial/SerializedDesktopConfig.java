package net.irregular.escapy.engine.desktop.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.serial.EscapySerialized;

/**
 * @author Henry on 15/07/17.
 */
public final class SerializedDesktopConfig implements EscapySerialized {

	@SerializedName("type") @Expose public String type;
	@SerializedName("name") @Expose public String name;

	@SerializedName("resizable") @Expose public boolean resizable;
	@SerializedName("vSync") @Expose public boolean vSyncEnabled;
	@SerializedName("fullscreen") @Expose public boolean fullscreen;
	@SerializedName("forceExit") @Expose public boolean forceExit;
	@SerializedName("useGL30") @Expose public boolean useGL30;

	@SerializedName("scrWidth") @Expose public int width;
	@SerializedName("scrHeight") @Expose public int height;
	@SerializedName("fps") @Expose public int foregroundFPS;




	@Override
	public String getValidType() {
		return "EscapyConfiguration";
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}
}
