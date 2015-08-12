package net.vrallev.android.cat.instance;

import net.vrallev.android.cat.CatLog;

/**
 * @author rwondratschek
 */
@SuppressWarnings("UnusedDeclaration")
public class CatSimple extends CatLog {

    private final String mTag;

    public CatSimple(Class<?> clazz) {
        this(clazz.getSimpleName());
    }

    public CatSimple(String tag) {
        mTag = tag == null ? "" : tag;
    }

    @Override
    protected String getTag() {
        return mTag;
    }
}
