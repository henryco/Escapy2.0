package net.irregular.escapy.objects;

import java.util.List;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.objects
 * At 29.06.17
 */
public class Layer implements Loadable {

    private String name;

    private float zAxis;
    private float xOffset;
    private float yOffset;

    private List<GameObject> attachedObjects;

    public Layer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getzAxis() {
        return zAxis;
    }

    public void setzAxis(float zAxis) {
        this.zAxis = zAxis;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public void setAttachedObjects(List<GameObject> attachedObjects) {
        this.attachedObjects = attachedObjects;
    }

    public List<GameObject> getAttachedObjects() {
        return this.attachedObjects;
    }

    public void attachObject(GameObject object) {
        attachedObjects.add(object);
    }

    public boolean removeAttachedObject() {
        //TODO: implement remove attached object method according to data struct.
        return false;
    }

    public GameObject findGameObject() {
        //TODO: implement search of game object by certain criteria.
        return null;
    }
}
