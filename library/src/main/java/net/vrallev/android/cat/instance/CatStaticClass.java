package net.vrallev.android.cat.instance;

import net.vrallev.android.cat.CatUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rwondratschek
 */
public class CatStaticClass extends CatLazy {

    private final Map<String, String> mClassTags;
    private final boolean mStripInnerClass;

    public CatStaticClass() {
        this(false);
    }

    public CatStaticClass(boolean stripInnerClass) {
        mClassTags = new HashMap<>();
        mStripInnerClass = stripInnerClass;
    }

    public CatStaticClass addMapping(Class<?> clazz, String tag) {
        mClassTags.put(clazz.getName(), tag);
        return this;
    }

    @Override
    protected String getTag() {
        String callingClassName = CatUtil.getCallingClassName();
        if (mStripInnerClass) {
            callingClassName = CatUtil.stripInnerClass(callingClassName);
        }

        String tag = mClassTags.get(callingClassName);
        return tag == null ? CatUtil.simpleClassName(callingClassName) : tag;
    }
}
