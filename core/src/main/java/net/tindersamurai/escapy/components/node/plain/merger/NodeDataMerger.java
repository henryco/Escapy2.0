package net.tindersamurai.escapy.components.node.plain.merger;

import com.github.henryco.injector.meta.annotations.Provide;
import lombok.extern.java.Log;
import lombok.val;
import net.tindersamurai.escapy.components.node.plain.data.NodeData;
import net.tindersamurai.escapy.components.node.plain.merger.components.IControlModelMerger;
import net.tindersamurai.escapy.components.node.plain.merger.components.IControlPhysMerger;
import net.tindersamurai.escapy.components.node.plain.merger.components.IModelPhysMerger;

import javax.inject.Inject;

@Provide @Log
public class NodeDataMerger implements INodeDataMerger<NodeData> {

	private final IControlModelMerger controlModelMerger;
	private final IControlPhysMerger controlPhysMerger;
	private final IModelPhysMerger modelPhysMerger;

	@Inject
	public NodeDataMerger(
			IControlModelMerger controlModelMerger,
			IControlPhysMerger controlPhysMerger,
			IModelPhysMerger modelPhysMerger
	) {
		this.controlModelMerger = controlModelMerger;
		this.controlPhysMerger = controlPhysMerger;
		this.modelPhysMerger = modelPhysMerger;
	}

	@Override
	public void mergeNodeData(NodeData nodeData) {

		val model = nodeData.getModel();
		val phys = nodeData.getPhys();
		val physListener = nodeData.getPhysListener();
		val modelListener = nodeData.getModelListener();

		modelPhysMerger.mergePhysWithModel(phys, model);
		controlPhysMerger.mergePhysWithController(physListener, phys);
		controlModelMerger.mergeModelWithController(model, modelListener);
	}


}