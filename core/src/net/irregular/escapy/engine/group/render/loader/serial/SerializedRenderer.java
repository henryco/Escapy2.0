package net.irregular.escapy.engine.group.render.loader.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.irregular.escapy.engine.env.utils.EscapySerialized;

/**
 * @author Henry on 20/07/17.
 */
public class SerializedRenderer implements EscapySerialized {


	@Override public String getName() {
		return name;
	}
	@Override public String getType() {
		return type;
	}
	@Override public String getValidType() {
		return "Renderer";
	}


	@SerializedName("type") @Expose public String type;
	@SerializedName("name") @Expose public String name;








}
