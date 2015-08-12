package net.vrallev.android.cat;

/**
 * @author rwondratschek
 */
@SuppressWarnings("UnusedDeclaration")
public final class Cat {

    private Cat() {
        // no op
    }

    public static void d(String message) {
        CatGlobal.getDefaultCatLog().d(message);
    }

    public static void d(String message, Object... args) {
        CatGlobal.getDefaultCatLog().d(message, args);
    }

    public static void d(Throwable t, String message, Object... args) {
        CatGlobal.getDefaultCatLog().d(t, message, args);
    }

    public static void i(String message) {
        CatGlobal.getDefaultCatLog().i(message);
    }

    public static void i(String message, Object... args) {
        CatGlobal.getDefaultCatLog().i(message, args);
    }

    public static void i(Throwable t, String message, Object... args) {
        CatGlobal.getDefaultCatLog().i(t, message, args);
    }

    public static void w(String message) {
        CatGlobal.getDefaultCatLog().w(message);
    }

    public static void w(String message, Object... args) {
        CatGlobal.getDefaultCatLog().w(message, args);
    }

    public static void w(Throwable t, String message, Object... args) {
        CatGlobal.getDefaultCatLog().w(t, message, args);
    }

    public static void w(Throwable t) {
        CatGlobal.getDefaultCatLog().w(t);
    }

    public static void e(Throwable t) {
        CatGlobal.getDefaultCatLog().e(t);
    }

    public static void e(String message) {
        CatGlobal.getDefaultCatLog().e(message);
    }

    public static void e(String message, Object... args) {
        CatGlobal.getDefaultCatLog().e(message, args);
    }

    public static void e(Throwable t, String message, Object... args) {
        CatGlobal.getDefaultCatLog().e(t, message, args);
    }

    public static void v(String message) {
        CatGlobal.getDefaultCatLog().v(message);
    }

    public static void v(String message, Object... args) {
        CatGlobal.getDefaultCatLog().v(message, args);
    }

    public static void v(Throwable t, String message, Object... args) {
        CatGlobal.getDefaultCatLog().v(t, message, args);
    }
}
