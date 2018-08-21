package net.tindersamurai.escapy.components.node.plain.data;

import lombok.Data;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.control.plain.model.IModelListener;
import net.tindersamurai.escapy.components.control.plain.phys.IPhysListener;
import net.tindersamurai.escapy.components.node.plain.merger.INodeDataMerger;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

@Data @Log
public class NodeData implements INodeData {

	private IEscapyModel model;
	private IEscapyPhysObject phys;

	private IModelListener modelListener;
	private IPhysListener physListener;

	private final INodeDataMerger<NodeData> merger;
	private final String id;


	public static NodeData newInstance(INodeDataMerger<NodeData> merger) {
		return new NodeData(merger);
	}

	private NodeData(INodeDataMerger<NodeData> merger) {
		id = this.hashCode() + ":" + super.hashCode();
		log.info("Node created [" + id + "]");
		this.merger = merger;
	}


	@Override
	public void dispose() {
		log.info("DISPOSE NODE DATA: [" + id + "]");
		if (modelListener != null) modelListener.dispose();
		if (physListener != null) physListener.dispose();
		if (model != null) model.dispose();
		if (phys != null) phys.dispose();
	}

	/**
	 * We can set merger only via constructor
	 */ @Override
	public void setMerger(INodeDataMerger merger) {
		// set via constructor only
	}

	public void setModel(IEscapyModel model) {
		if (model == null && this.model != null)
			this.model.dispose();
		this.model = model;
		merge();
	}

	public void setPhys(IEscapyPhysObject object) {
		if (object == null && this.phys != null)
			this.phys.dispose();
		this.phys = object;
		merge();
	}

	public void setPhysListener(IPhysListener physListener) {
		if (this.physListener != null)
			this.physListener.detach();
	 	if (physListener == null && this.physListener != null)
			this.physListener.dispose();
	 	this.physListener = physListener;
		merge();
		if (this.physListener != null)
			this.physListener.attach();
	}

	public void setModelListener(IModelListener modelListener) {
	 	if (this.modelListener != null)
	 		this.modelListener.detach();
		if (modelListener == null && this.modelListener != null)
			this.modelListener.dispose();
		this.modelListener = modelListener;
		merge();
		if (this.modelListener != null)
			this.modelListener.attach();
	}

}