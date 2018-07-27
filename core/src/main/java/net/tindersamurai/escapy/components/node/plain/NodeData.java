package net.tindersamurai.escapy.components.node.plain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import net.tindersamurai.escapy.map.location.IEscapyLocation;
import net.tindersamurai.escapy.map.model.IEscapyModel;
import net.tindersamurai.escapy.physics.obj.IEscapyPhysObject;

@Data @Log @NoArgsConstructor
public class NodeData implements IEscapyLocation {

	private final String id; {
		id = this.hashCode() + ":" + super.hashCode();
		log.info("Node created [" + id + "]");
	} // TODO FIXME Constructor (String) might cause of errors

	private IEscapyModel model;
	private IEscapyPhysObject phys;

	@Override
	public void dispose() {
		log.info("DISPOSE NODE DATA: [" + id + "]");
		if (model != null) model.dispose();
		if (phys != null) phys.dispose();
	}
}