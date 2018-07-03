package net.tindersamurai.escapy.components.node;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.Data;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.map.model.IEscapyModel;

@Provide @Data @Log
public class NodeData {

	{ log.info("Node created [" + this.hashCode() + "]"); }

	private IEscapyModel model;
}