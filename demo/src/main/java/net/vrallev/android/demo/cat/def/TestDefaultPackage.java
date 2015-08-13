package net.vrallev.android.demo.cat.def;

import net.vrallev.android.cat.Cat;

/**
 * @author rwondratschek
 */
public final class TestDefaultPackage {

    private TestDefaultPackage() {
        // no op
    }

    public static void log() {
        Cat.d("Hello from the default instance");
    }

}
