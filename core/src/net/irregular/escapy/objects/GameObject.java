package net.irregular.escapy.objects;

import java.io.File;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.objects
 * At 26.06.17
 */
public class GameObject implements Loadable {

    private float depth;
    private float x;
    private float y;

    private File[] textures;

    private float scale;

    private String name;

    public GameObject(String name,  float depth, float x, float y, File[] textures, float scale) {
        this.name = name;
        this.depth = depth;
        this.x = x;
        this.y = y;
        this.textures = textures;
        this.scale = scale;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
