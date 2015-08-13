package net.vrallev.android.cat.print;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author rwondratschek
 */
public interface CatPrinter {

    void println(int priority, @NonNull String tag, @NonNull String message, @Nullable Throwable t);
}
