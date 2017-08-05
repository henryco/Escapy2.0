package net.irregular.escapy.utils.serial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class EscapySimpleSerialized implements EscapySerialized {

	@SerializedName("type") @Expose public String type = "";
	@SerializedName("name") @Expose public String name = "";
	@SerializedName("attributes") @Expose public List<String> attributes = new LinkedList<>();

	@Override public Collection<String> getAttributes() {
		return attributes;
	}

	@Override public String getName() {
		return name;
	}

	@Override public String getType() {
		return type;
	}

}
