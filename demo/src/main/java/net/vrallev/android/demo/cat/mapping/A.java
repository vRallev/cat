package net.vrallev.android.demo.cat.mapping;

import net.vrallev.android.cat.Cat;

/**
 * @author rwondratschek
 */
public final class A {
    public static void log() {
        Cat.d("Class A");
    }

    private A() {

    }

    public static class Inner {
        public static void log() {
            Cat.d("Class A Inner");
        }
    }
}
