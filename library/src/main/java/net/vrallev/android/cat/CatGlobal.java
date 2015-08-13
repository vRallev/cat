package net.vrallev.android.cat;

import android.support.annotation.NonNull;

import net.vrallev.android.cat.instance.CatLazy;
import net.vrallev.android.cat.print.CatPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author rwondratschek
 */
@SuppressWarnings("unused")
public final class CatGlobal {

    private CatGlobal() {
        // no op
    }

    private static final Set<String> DISABLED_TAGS = new HashSet<>();
    private static final List<String> DISABLED_PACKAGES = new ArrayList<>();

    private static CatLog defaultCatLog = new CatLazy();
    private static final Map<String, CatLog> PACKAGE_CAT_LOGS = new HashMap<>();

    /*package*/ static synchronized void print(int priority, String tag, String message, Throwable t, List<? extends CatPrinter> printers) {
        if (!DISABLED_TAGS.isEmpty() && DISABLED_TAGS.contains(tag)) {
            return;
        }

        if (!DISABLED_PACKAGES.isEmpty() && isCallingClassDisabled()) {
            return;
        }

        if (printers != null && !printers.isEmpty()) {
            for (int i = 0; i < printers.size(); i++) {
                printers.get(i).println(priority, tag, message, t);
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
        String packageName = CatUtil.getCallingPackage();

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
        if (!PACKAGE_CAT_LOGS.isEmpty()) {
            String callingPackage = CatUtil.getCallingPackage();
            for (String catLogPackage : PACKAGE_CAT_LOGS.keySet()) {
                if (callingPackage.startsWith(callingPackage)) {
                    return PACKAGE_CAT_LOGS.get(catLogPackage);
                }
            }
        }

        return defaultCatLog;
    }

    public static synchronized void setDefaultCatLogPackage(@NonNull String catLogPackage, CatLog catLog) {
        PACKAGE_CAT_LOGS.put(catLogPackage, catLog);
    }

    public static synchronized void removeDefaultCatLogPackage(@NonNull String catLogPackage) {
        PACKAGE_CAT_LOGS.remove(catLogPackage);
    }
}
