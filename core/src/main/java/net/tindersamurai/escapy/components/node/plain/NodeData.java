package net.tindersamurai.escapy.components.node.plain;

import lombok.Data;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.map.model.IEscapyModel;

@Data @Log
public class NodeData {

	{ log.info("Node created [" + this.hashCode() + super.hashCode() + "]"); }

	private IEscapyModel model;
}