package net.vrallev.android.cat.print;

import android.util.Log;

/**
 * @author rwondratschek
 */
public class AndroidLog implements CatPrinter {

    @Override
    public void println(int priority, String tag, String msg) {
        Log.println(priority, tag, msg);
    }
}
