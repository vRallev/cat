package net.vrallev.android.demo.cat;

import android.app.Application;

import net.vrallev.android.cat.CatGlobal;
import net.vrallev.android.demo.cat.disabled.TestDisabled;

/**
 * @author rwondratschek
 */
public class App extends Application {

    private static App instance;

    public static App instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;

        super.onCreate();

        CatGlobal.setPackageEnabled(TestDisabled.class.getPackage().getName(), false);
        CatGlobal.setTagEnabled("Disabled", false);
    }
}
