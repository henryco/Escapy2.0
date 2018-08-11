package net.tindersamurai.escapy.components.node.plain.merger;

import net.tindersamurai.escapy.components.node.plain.data.INodeData;

public interface INodeDataMerger<NODE_DATA extends INodeData> {
	void mergeNodeData(NODE_DATA nodeData);
}