package net.vrallev.android.demo.cat.mapping;

import net.vrallev.android.cat.Cat;

/**
 * @author rwondratschek
 */
public final class B {
    public static void log() {
        Cat.d("Class B");
    }

    private B() {

    }

    public static class Inner {
        public static void log() {
            Cat.d("Class B Inner");
        }
    }
}
