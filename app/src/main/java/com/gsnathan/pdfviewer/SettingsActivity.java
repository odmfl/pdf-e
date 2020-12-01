package com.gsnathan.pdfviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.app.ActionBar;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.core.app.NavUtils;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupActionBar();
        addPreferencesFromResource(R.xml.preferences);
        //getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

        int horizontalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2, getResources().getDisplayMetrics());

        int verticalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2, getResources().getDisplayMetrics());

        int topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10,
                getResources().getDisplayMetrics());

        getListView().setPadding(horizontalMargin, topMargin, horizontalMargin, verticalMargin);

        Preference button = findPreference("reload_pref");
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String uriString = "";
                try {
                    SharedPreferences prefManager = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    uriString = prefManager.getString("uri", "");
                    Log.d("Hello", "Uri = " + uriString);
                    if (uriString != null) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity_.class);
                        intent.putExtra("uri", uriString);
                        startActivity(intent);
                    } else {
                        Intent i = getBaseContext().getPackageManager().
                                getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return true;
            }
        });

        updateActionBar();

    }

    private void setupActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

//    @Override
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            if (!super.onMenuItemSelected(featureId, item)) {
//                NavUtils.navigateUpFromSameTask(this);
//            }
//            return true;
//        }
//        return super.onMenuItemSelected(featureId, item);
//    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(getApplicationContext());
    }

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    private void updateActionBar() {

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public static Drawable changeBackArrowColor(Context context, int color) {
        String resName;
        int res;

        resName = Build.VERSION.SDK_INT >= 23 ? "abc_ic_ab_back_material" : "abc_ic_ab_back_mtrl_am_alpha";
        res = context.getResources().getIdentifier(resName, "drawable", context.getPackageName());

        final Drawable upArrow = context.getResources().getDrawable(res);
        upArrow.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        return upArrow;
    }
}
