package net.tindersamurai.escapy.components.node.plain.merger;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.components.node.plain.data.INodeData;
import net.tindersamurai.escapy.components.node.plain.data.NodeData;
import net.tindersamurai.escapy.components.node.plain.merger.control.IControlPhysMerger;
import net.tindersamurai.escapy.components.node.plain.merger.model.IModelPhysMerger;

import javax.inject.Inject;

@Provide @Log
public class NodeDataMerger implements INodeDataMerger {

	private final IControlPhysMerger controlPhysMerger;
	private final IModelPhysMerger modelPhysMerger;

	@Inject
	public NodeDataMerger(IControlPhysMerger controlPhysMerger,
						  IModelPhysMerger modelPhysMerger) {
		this.controlPhysMerger = controlPhysMerger;
		this.modelPhysMerger = modelPhysMerger;
	}

	@Override
	public void mergeNodeData(INodeData nodeData) {

		if (!(nodeData instanceof NodeData))
			throw new RuntimeException("Merger data wrong implementation, data type required: " + NodeData.class);

		val model = ((NodeData) nodeData).getModel();
		val phys = ((NodeData) nodeData).getPhys();
		val listener = ((NodeData) nodeData).getControllerListener();

		modelPhysMerger.mergePhysWithModel(phys, model);
		controlPhysMerger.mergePhysWithController(listener, phys);
	}


}