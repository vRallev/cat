package net.vrallev.android.cat;

import android.text.TextUtils;
import android.util.Log;

import net.vrallev.android.cat.print.CatPrinter;

import java.util.List;
import java.util.Locale;

/**
 * @author rwondratschek
 */
@SuppressWarnings("UnusedDeclaration")
public abstract class CatLog {

    protected abstract String getTag();

    protected List<? extends CatPrinter> getLocalPrinters() {
        return null;
    }

    public void d(String message) {
        println(Log.DEBUG, message, null);
    }

    public void d(String message, Object... args) {
        println(Log.DEBUG, format(message, args), null);
    }

    public void d(Throwable t, String message, Object... args) {
        println(Log.DEBUG, format(message, args), t);
    }

    public void i(String message) {
        println(Log.INFO, message, null);
    }

    public void i(String message, Object... args) {
        println(Log.INFO, format(message, args), null);
    }

    public void i(Throwable t, String message, Object... args) {
        println(Log.INFO, format(message, args), t);
    }

    public void w(String message) {
        println(Log.WARN, message, null);
    }

    public void w(String message, Object... args) {
        println(Log.WARN, format(message, args), null);
    }

    public void w(Throwable t, String message, Object... args) {
        println(Log.WARN, format(message, args), t);
    }

    public void w(Throwable t) {
        if (t == null) {
            t = new Exception("null exception logged");
        }
        println(Log.WARN, t.getMessage(), t);
    }

    public void e(Throwable t) {
        if (t == null) {
            t = new Exception("null exception logged");
        }
        println(Log.ERROR, t.getMessage(), t);
    }

    public void e(String message) {
        println(Log.ERROR, message, null);
    }

    public void e(String message, Object... args) {
        println(Log.ERROR, format(message, args), null);
    }

    public void e(Throwable t, String message, Object... args) {
        println(Log.ERROR, format(message, args), t);
    }

    public void v(String message) {
        println(Log.VERBOSE, message, null);
    }

    public void v(String message, Object... args) {
        println(Log.VERBOSE, format(message, args), null);
    }

    public void v(Throwable t, String message, Object... args) {
        println(Log.VERBOSE, format(message, args), t);
    }

    protected void println(int priority, String message, Throwable t) {
        if (TextUtils.isEmpty(message)) {
            message = "empty message";
        }

        CatGlobal.print(priority, getTag(), message, t, getLocalPrinters());
    }

    private static String format(String message, Object[] args) {
        if (message == null) {
            return "null";
        } else {
            return String.format(Locale.US, message, args);
        }
    }
}
