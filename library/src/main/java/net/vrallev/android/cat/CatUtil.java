package net.vrallev.android.cat;

/**
 * @author rwondratschek
 */
public final class CatUtil {

    private static final String PACKAGE = Cat.class.getPackage().getName();

    public static boolean isValidClass(String className) {
        return !className.startsWith(PACKAGE);
    }

    public static String getCallingClassNameSimple() {
        String className = getCallingClassName();
        String[] split = className.split("\\.");
        if (split.length == 0) {
            return className;
        } else {
            return split[split.length - 1];
        }
    }

    public static String getCallingClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (int i = 3; i < stackTrace.length; i++) {
            if (isValidClass(stackTrace[i].getClassName())) {
                return stackTrace[i].getClassName();
            }
        }

        return "Cat";
    }

    private CatUtil() {
        // no op
    }
}
