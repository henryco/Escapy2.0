package net.irregular.escapy.loader;

import net.irregular.escapy.objects.Layer;
import net.irregular.escapy.objects.Loadable;

/**
 * Created by weirdguy in Escapy2.0
 * net.irregular.escapy.loader
 * At 30.06.17
 */
public class LayerLoader implements Loader {


    @Override
    public Loadable loadObject(String file) {

        return new Layer(file);

    }
}
