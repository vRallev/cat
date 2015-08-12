package net.vrallev.android.cat.instance;

import net.vrallev.android.cat.CatLog;
import net.vrallev.android.cat.CatUtil;

/**
 * @author rwondratschek
 */
public class CatLazy extends CatLog {

    @Override
    protected String getTag() {
        return CatUtil.getCallingClassNameSimple();
    }
}
