/*
 * MIT License
 *
 * Copyright (c) 2018 Gokul Swaminathan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.gsnathan.pdfviewer;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
//import com.jaredrummler.cyanea.prefs.CyaneaThemePickerActivity;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class MainIntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        int bg = ContextCompat.getColor(this, R.color.color_default_background);
        int divider = ContextCompat.getColor(this, R.color.color_default_divider);
        int primaryText = ContextCompat.getColor(this, R.color.color_default_primary_text);
        int secondaryText = ContextCompat.getColor(this, R.color.color_default_secondary_text);
        int accent = ContextCompat.getColor(this, R.color.color_default_accent);
        int primaryDark = ContextCompat.getColor(this, R.color.color_default_primary_dark);

        SliderPage first = new SliderPage();
        first.setTitle(getString(R.string.title_intro));
        first.setDescription(getString(R.string.description__intro));
        first.setImageDrawable(R.mipmap.ic_launcher);
        first.setBgColor(bg);
        first.setTitleColor(primaryText);
        first.setDescColor(secondaryText);
        addSlide(AppIntroFragment.newInstance(first));

        SliderPage second = new SliderPage();
        second.setTitle(getString(R.string.title_open));
        second.setDescription(getString(R.string.description_open));
        second.setImageDrawable(R.drawable.opensource_wide);
        second.setBgColor(bg);
        second.setTitleColor(primaryText);
        second.setDescColor(secondaryText);
        addSlide(AppIntroFragment.newInstance(second));

        SliderPage third = new SliderPage();
        third.setTitle(getString(R.string.title_permission));
        third.setDescription(getString(R.string.description__permission));
        third.setImageDrawable(R.drawable.patterns_permissions);
        third.setBgColor(bg);
        third.setTitleColor(primaryText);
        third.setDescColor(secondaryText);
        addSlide(AppIntroFragment.newInstance(third));

        setNextArrowColor(accent);
        setSeparatorColor(divider);
        setColorDoneText(accent);
        setIndicatorColor(accent, accent);
        showSkipButton(false);
        setNavBarColor(R.color.color_default_primary_dark);

        askForPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        showStatusBar(true);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }
}