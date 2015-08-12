package net.vrallev.android.demo.cat.disabled;

import net.vrallev.android.cat.Cat;

/**
 * @author rwondratschek
 */
public final class TestDisabled {

    private TestDisabled() {
        // no op
    }

    public static void log() {
        Cat.e("should not be logged");
    }
}
