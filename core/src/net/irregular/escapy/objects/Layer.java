package net.irregular.escapy.objects;

import java.util.List;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.objects
 * At 29.06.17
 */
public class Layer implements Loadable, Comparable<Layer> {

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

    public boolean removeAttachedObject(String name) {
        for (GameObject object :
                attachedObjects) {
            if (object.getName().equals(name)) {
                return attachedObjects.remove(object);
            }
        }
        return false;
    }

    public GameObject findGameObject(String name) {
        for (GameObject object :
                attachedObjects) {
            if(object.getName().equals(name)) return object;
        }
        return null;
    }

    @Override
    public int compareTo(Layer o) {
        return Float.compare(zAxis, o.getzAxis());
    }
}
