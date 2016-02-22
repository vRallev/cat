package net.vrallev.android.demo.cat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.vrallev.android.cat.Cat;
import net.vrallev.android.cat.CatLog;
import net.vrallev.android.cat.instance.CatLazy;
import net.vrallev.android.cat.instance.CatSimple;
import net.vrallev.android.demo.cat.def.TestDefaultPackage;
import net.vrallev.android.demo.cat.disabled.TestDisabled;
import net.vrallev.android.demo.cat.mapping.A;
import net.vrallev.android.demo.cat.mapping.B;

/**
 * @author rwondratschek
 */
public class MainActivity extends Activity {

    private static final CatLog CAT = new CatSimple(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_simple_test:
                testSimple();
                break;

            case R.id.button_lazy:
                testLazy();
                break;

            case R.id.button_disabled_tag:
                testDisabledTag();
                break;

            case R.id.button_disabled_package:
                testDisabledPackage();
                break;

            case R.id.button_default_package:
                testDefaultPackage();
                break;

            case R.id.button_inner_class:
                testInnerClass();
                break;

            case R.id.button_custom_cat:
                testCatCustom();
                break;

            case R.id.button_open_file_activity:
                startActivity(new Intent(this, FileActivity.class));
                break;

            case R.id.button_static_mapping:
                testStaticMapping();
                break;

            default:
                throw new IllegalStateException("not implemented");
        }
    }

    private void testSimple() {
        CAT.d("CatSimple");
    }

    private void testLazy() {
        Cat.d("lazy and default instance");
    }

    private void testDisabledTag() {
        new CatSimple("Disabled").e("should not appear");
    }

    private void testDisabledPackage() {
        TestDisabled.log();
    }

    private void testDefaultPackage() {
        TestDefaultPackage.log();
    }

    private void testInnerClass() {
        InnerClassTest.log();
    }

    private void testCatCustom() {
        new CatCustom().d("Hello world");
    }

    private void testStaticMapping() {
        A.log();
        A.Inner.log();
        B.log();
        B.Inner.log();
    }

    private static class InnerClassTest {
        public static void log() {
            new CatLazy().d("Inner class");
        }
    }

    private static class CatCustom extends CatLazy {
        @Override
        public void d(String message) {
            String tag = getTag();
            super.d(tag + message);
        }
    }
}
