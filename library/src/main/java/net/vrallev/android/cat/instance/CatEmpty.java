package net.vrallev.android.cat.instance;

import net.vrallev.android.cat.CatLog;

/**
 * @author rwondratschek
 */
public class CatEmpty extends CatLog {

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void d(String message) {
        // no op
    }

    @Override
    public void d(String message, Object... args) {
        // no op
    }

    @Override
    public void d(Throwable t, String message, Object... args) {
        // no op
    }

    @Override
    public void i(String message) {
        // no op
    }

    @Override
    public void i(String message, Object... args) {
        // no op
    }

    @Override
    public void i(Throwable t, String message, Object... args) {
        // no op
    }

    @Override
    public void w(String message) {
        // no op
    }

    @Override
    public void w(String message, Object... args) {
        // no op
    }

    @Override
    public void w(Throwable t, String message, Object... args) {
        // no op
    }

    @Override
    public void w(Throwable t) {
        // no op
    }

    @Override
    public void e(Throwable t) {
        // no op
    }

    @Override
    public void e(String message) {
        // no op
    }

    @Override
    public void e(String message, Object... args) {
        // no op
    }

    @Override
    public void e(Throwable t, String message, Object... args) {
        // no op
    }

    @Override
    public void v(String message) {
        // no op
    }

    @Override
    public void v(String message, Object... args) {
        // no op
    }

    @Override
    public void v(Throwable t, String message, Object... args) {
        // no op
    }
}
