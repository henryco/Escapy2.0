package net.tindersamurai.escapy.components.node.plain.data;

import lombok.Data;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.node.plain.merger.INodeDataMerger;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

@Data @Log
public class NodeData implements INodeData {

	public static NodeData newInstance(INodeDataMerger merger) {
		return new NodeData() {{
			setMerger(merger);
		}};
	}

	private final String id; {
		id = this.hashCode() + ":" + super.hashCode();
		log.info("Node created [" + id + "]");
	} // TODO FIXME Constructor (String) might cause of errors

	private IEscapyModel model;
	private IEscapyPhysObject phys;
	private INodeDataMerger merger;

	private NodeData() { }

	@Override
	public void dispose() {
		log.info("DISPOSE NODE DATA: [" + id + "]");
		if (model != null) model.dispose();
		if (phys != null) phys.dispose();
	}

	public void setModel(IEscapyModel model) {
		this.model = model;
		merge();
	}

	public void setPhys(IEscapyPhysObject object) {
		this.phys = object;
		merge();
	}

}