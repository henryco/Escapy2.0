package net.irregular.escapy.engine.env.utils.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Henry on 21/07/17.
 */
public abstract class EscapySimpleSerialized implements EscapySerialized {

	@Override public String getName() {
		return name;
	}
	@Override public String getType() {
		return type;
	}
	@Override public Collection<String> getAttributes() {
		return attributes;
	}

	@SerializedName("type") @Expose public String type = "";
	@SerializedName("name") @Expose public String name = "";
	@SerializedName("attributes") @Expose public List<String> attributes = new LinkedList<>();

}
