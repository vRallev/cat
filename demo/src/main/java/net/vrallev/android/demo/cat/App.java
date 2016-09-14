package net.vrallev.android.demo.cat;

import android.app.Application;

import net.vrallev.android.cat.Cat;
import net.vrallev.android.cat.CatGlobal;
import net.vrallev.android.cat.instance.CatSimple;
import net.vrallev.android.cat.instance.CatStaticClass;
import net.vrallev.android.demo.cat.def.TestDefaultPackage;
import net.vrallev.android.demo.cat.disabled.TestDisabled;
import net.vrallev.android.demo.cat.mapping.A;

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

        try {
            CatGlobal.setPackageEnabled(TestDisabled.class.getPackage().getName(), false);
            CatGlobal.setTagEnabled("Disabled", false);

            CatGlobal.setDefaultCatLogPackage(TestDefaultPackage.class.getPackage().getName(), new CatSimple("DefaultPackage"));

            CatStaticClass catStaticClass = new CatStaticClass(true).addMapping(A.class, "CustomTagA");
            CatGlobal.setDefaultCatLogPackage(A.class.getPackage().getName(), catStaticClass);

        } catch (Exception e) {
            Cat.e(e);
        }
    }
}
