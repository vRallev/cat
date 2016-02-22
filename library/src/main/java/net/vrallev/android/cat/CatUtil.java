package net.vrallev.android.cat;

import android.support.v4.util.LruCache;

/**
 * @author rwondratschek
 */
public final class CatUtil {

    private static final String PACKAGE = Cat.class.getPackage().getName();

    private static final LruCache<String, Boolean> IGNORED_CLASS_NAMES = new LruCache<String, Boolean>(100) {
        @Override
        protected Boolean create(String className) {
            try {
                Class<?> clazz = Class.forName(className);

                if (hasInvalidInterfaces(clazz)) {
                    return true;
                }

                Class<?> superclass = clazz.getSuperclass();
                while (superclass != null) {
                    if (!isValidClass(superclass.getName()) || hasInvalidInterfaces(superclass)) {
                        return true;
                    }
                    superclass = superclass.getSuperclass();
                }

                return false;

            } catch (Exception e) {
                return false;
            }
        }

        private boolean hasInvalidInterfaces(Class<?> clazz) {
            if (clazz == null) {
                return false;
            }

            Class<?>[] interfaces = clazz.getInterfaces();
            if (interfaces == null || interfaces.length == 0) {
                return false;
            }

            for (Class<?> interfaceClass : interfaces) {
                if (!isValidClass(interfaceClass.getName())) {
                    return true;
                }
            }

            return false;
        }
    };

    public static boolean isValidClass(String className) {
        return !className.startsWith(PACKAGE);
    }

    private static boolean isClassNameIgnored(String className) {
        return IGNORED_CLASS_NAMES.get(className);
    }

    public static String getCallingClassName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (int i = 3; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (isValidClass(className) && !isClassNameIgnored(className)) {
                return className;
            }
        }

        return "Cat";
    }

    public static String getCallingClassNameSimple() {
        return simpleClassName(getCallingClassName());
    }

    public static String simpleClassName(String className) {
        String[] split = className.split("\\.");
        if (split.length == 0) {
            return className;
        } else {
            return split[split.length - 1];
        }
    }

    public static String stripInnerClass(String className) {
        String[] split = className.split("\\$");
        if (split.length == 0) {
            return className;
        } else {
            return split[0];
        }
    }

    public static String getCallingPackage() {
        String className = getCallingClassName();
        String[] split = className.split("\\.");
        if (split.length <= 1) {
            return className;
        }

        return className.substring(0, className.length() - 1 - split[split.length - 1].length());
    }

    private CatUtil() {
        // no op
    }
}
