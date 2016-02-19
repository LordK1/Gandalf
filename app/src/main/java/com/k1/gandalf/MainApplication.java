package com.k1.gandalf;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.Locale;


/**
 * Created by k1 on 12/27/15.
 */
public class MainApplication extends Application {

    private static final String TAG = MainApplication.class.getSimpleName();
    private static final String FA_LANG = "fa";


    @Override
    public void onCreate() {
        super.onCreate();
        updateLocale(this, FA_LANG);

        // Iconify setup
        Iconify.with(new FontAwesomeModule());
    }

    /**
     * update Locale of application in to FA
     *
     * @param context
     * @param lang
     */
    private static void updateLocale(Context context, String lang) {
        Configuration configuration = new Configuration();
        if (!TextUtils.isEmpty(lang)) {
            configuration.locale = new Locale(lang);
        } else {
            configuration.locale = Locale.getDefault();
        }
        context.getResources().updateConfiguration(configuration,
                context.getResources().getDisplayMetrics());
    }

    /**
     * Check the Locale of application is Persian or RTL
     *
     * @param context
     * @return
     */
    public static boolean isLocaleFa(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().equals(FA_LANG);
    }


}
