package net.irregular.escapy.map.object;


/**
 * @author Henry on 11/07/17.
 */
public class ObjectStatic implements GameObject {


	private final ObjectDetails objectDetails;


	public ObjectStatic() {
		this(new ObjectDetails());
	}
	public ObjectStatic(ObjectDetails objectDetails) {
		this.objectDetails = objectDetails;
	}


	private String textureNormal;
	private String textureLight;
	private String textureHeight;
	private String texture;




	@Override
	public String toString() {
		return "ObjectStatic{" +
				"objectDetails=" + objectDetails +
				", textureNormal='" + textureNormal + '\'' +
				", textureLight='" + textureLight + '\'' +
				", textureHeight='" + textureHeight + '\'' +
				", texture='" + texture + '\'' +
				'}';
	}


	@Override
	public ObjectDetails getObjectDetails() {
		return objectDetails;
	}



}
