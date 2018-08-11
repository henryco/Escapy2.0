package net.tindersamurai.escapy.components.node.plain.data;

import lombok.Data;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.components.control.plain.animation.IAnimationListener;
import net.tindersamurai.escapy.components.control.plain.phys.IPhysListener;
import net.tindersamurai.escapy.components.node.plain.merger.INodeDataMerger;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

@Data @Log
public class NodeData implements INodeData {

	public static NodeData newInstance(INodeDataMerger<NodeData> merger) {
		return new NodeData(merger);
	}

	private IEscapyModel model;
	private IEscapyPhysObject phys;

	private IAnimationListener animationListener;
	private IPhysListener physListener;

	private final INodeDataMerger<NodeData> merger;
	private final String id;

	private NodeData(INodeDataMerger<NodeData> merger) {
		id = this.hashCode() + ":" + super.hashCode();
		log.info("Node created [" + id + "]");
		this.merger = merger;
	}


	@Override
	public void dispose() {
		log.info("DISPOSE NODE DATA: [" + id + "]");
		if (animationListener != null) animationListener.dispose();
		if (physListener != null) physListener.dispose();
		if (model != null) model.dispose();
		if (phys != null) phys.dispose();
	}

	/**
	 * We can set merger only via constructor
	 */ @Override
	public void setMerger(INodeDataMerger merger) {
		// constructor only
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
		if (physListener == null && this.physListener != null)
			this.physListener.dispose();
		this.physListener = physListener;
		merge();
	}

	public void setAnimationListener(IAnimationListener animationListener) {
		if (animationListener == null && this.animationListener != null)
			this.animationListener.dispose();
		this.animationListener = animationListener;
		merge();
	}

}