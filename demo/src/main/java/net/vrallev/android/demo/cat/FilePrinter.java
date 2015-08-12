package net.vrallev.android.demo.cat;

import android.content.Context;

import net.vrallev.android.cat.Cat;
import net.vrallev.android.cat.print.CatPrinter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author rwondratschek
 */
public class FilePrinter implements CatPrinter {

    private final Context mContext;
    private final DateFormat mDateFormat;

    public FilePrinter(Context context) {
        mContext = context;
        mDateFormat = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
    }

    @Override
    public void println(int priority, String tag, String msg) {
        msg = mDateFormat.format(new Date()) + "\t\t" + tag + "\t\t" + msg + '\n';

        try {
            FileUtils.writeFile(getLogFile(), msg, true);
        } catch (IOException e) {
            Cat.e(e);
        }
    }

    public synchronized File getLogFile() {
        return new File(mContext.getCacheDir(), "log.txt");
    }
}
