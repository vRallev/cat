package net.vrallev.android.cat;

import android.support.annotation.NonNull;

import net.vrallev.android.cat.instance.CatLazy;
import net.vrallev.android.cat.print.AndroidLog;
import net.vrallev.android.cat.print.CatPrinter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rwondratschek
 */
@SuppressWarnings("unused")
public final class CatGlobal {

    private CatGlobal() {
        // no op
    }

    private static final List<CatPrinter> PRINTERS = new ArrayList<CatPrinter>() {{
        add(new AndroidLog());
    }};

    private static final Set<String> DISABLED_TAGS = new HashSet<>();
    private static final List<String> DISABLED_PACKAGES = new ArrayList<>();

    private static CatLog defaultCatLog = new CatLazy();

    public static synchronized void addPrinter(@NonNull CatPrinter printer) {
        PRINTERS.add(printer);
    }

    public static synchronized void clearPrinters() {
        PRINTERS.clear();
    }

    /*package*/ static synchronized void print(int priority, String tag, String msg, List<CatPrinter> localPrinters) {
        if (!DISABLED_TAGS.isEmpty() && DISABLED_TAGS.contains(tag)) {
            return;
        }

        if (!DISABLED_PACKAGES.isEmpty() && isCallingClassDisabled()) {
            return;
        }

        for (int i = 0; i < PRINTERS.size(); i++) {
            PRINTERS.get(i).println(priority, tag, msg);
        }

        if (localPrinters != null && !localPrinters.isEmpty()) {
            for (int i = 0; i < localPrinters.size(); i++) {
                localPrinters.get(i).println(priority, tag, msg);
            }
        }
    }

    public static synchronized void setTagEnabled(String tag, boolean enabled) {
        if (enabled) {
            DISABLED_TAGS.remove(tag);
        } else {
            DISABLED_TAGS.add(tag);
        }
    }

    public static synchronized void setPackageEnabled(String packageString, boolean enabled) {
        if (enabled) {
            DISABLED_PACKAGES.remove(packageString);
        } else {
            DISABLED_PACKAGES.add(packageString);
        }
    }

    private static boolean isCallingClassDisabled() {
        String className = CatUtil.getCallingClassName();
        String[] split = className.split("\\.");
        if (split.length <= 1) {
            return false;
        }

        String packageName = className.substring(0, className.length() - 1 - split[split.length - 1].length());

        for (int i = 0; i < DISABLED_PACKAGES.size(); i++) {
            String disabledPackage = DISABLED_PACKAGES.get(i);
            if (packageName.startsWith(disabledPackage)) {
                return true;
            }
        }

        return false;
    }

    public static synchronized void setDefaultCatLog(@NonNull CatLog catLog) {
        defaultCatLog = catLog;
    }

    /*package*/ static synchronized CatLog getDefaultCatLog() {
        return defaultCatLog;
    }
}
