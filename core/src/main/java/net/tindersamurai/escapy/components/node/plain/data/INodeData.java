package net.tindersamurai.escapy.components.node.plain.data;

import lombok.val;
import net.tindersamurai.escapy.components.node.plain.merger.INodeDataMerger;
import net.tindersamurai.escapy.map.location.IEscapyLocation;

import java.util.logging.Logger;

public interface INodeData extends IEscapyLocation {

	void setMerger(INodeDataMerger dataMerger);
	INodeDataMerger getMerger();

	default void merge() {
		val merger = getMerger();
		if (merger == null) {
			Logger.getLogger(this.getClass().getName())
					.warning("Data merger doesn't present");
			return;
		}
		merger.mergeNodeData(this);
	}
}