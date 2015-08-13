package net.vrallev.android.demo.cat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.vrallev.android.cat.Cat;
import net.vrallev.android.cat.CatLog;
import net.vrallev.android.cat.instance.CatSimple;
import net.vrallev.android.cat.print.CatPrinter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author rwondratschek
 */
public class FileActivity extends Activity {

    private static final FilePrinter FILE_PRINTER = new FilePrinter(App.instance());

    private static final CatLog CAT = new CatSimple(FileActivity.class) {

        private final List<? extends CatPrinter> mPrinters = Collections.singletonList(FILE_PRINTER);

        @Override
        protected List<? extends CatPrinter> getLocalPrinters() {
            return mPrinters;
        }
    };

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        mTextView = (TextView) findViewById(R.id.textView_log);
        refreshView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_log:
                CAT.d("menu clicked");
                refreshView();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refreshView() {
        try {
            byte[] content = FileUtils.readFile(FILE_PRINTER.getLogFile());
            if (content != null) {
                mTextView.setText(new String(content, "UTF-8"));
            }

        } catch (IOException e) {
            Cat.e(e);
        }
    }
}
