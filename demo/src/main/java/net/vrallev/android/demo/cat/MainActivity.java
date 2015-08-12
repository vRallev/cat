package net.vrallev.android.demo.cat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.vrallev.android.cat.Cat;
import net.vrallev.android.cat.CatLog;
import net.vrallev.android.cat.instance.CatSimple;
import net.vrallev.android.demo.cat.disabled.TestDisabled;

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

            case R.id.button_open_file_activity:
                startActivity(new Intent(this, FileActivity.class));
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
}
