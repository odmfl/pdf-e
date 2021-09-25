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

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {

    public static boolean tempBool = false;

    static void showLog(AppCompatActivity context) {
        Intent intent = new Intent(context, ChangeLogActivity.class);
        context.startActivity(intent);
        /*WhatsNew log = WhatsNew.newInstance(
                new WhatsNewItem("Bottom Bar", "Removed FAB and replaced it with bottom bar", R.drawable.star_icon),
                new WhatsNewItem("Bug Fixes", "Fixed crashes on Android Q", R.drawable.thumbs_icon)
                );
        log.setTitleColor(ContextCompat.getColor(context, R.color.color_default_accent));
        log.setTitleText(context.getResources().getString(R.string.appChangelog));
        log.setButtonText(context.getResources().getString(R.string.buttonLog));
        log.setButtonBackground(ContextCompat.getColor(context, R.color.color_default_accent));
        log.setButtonTextColor(ContextCompat.getColor(context, R.color.color_default_view_on_accent));
        log.setItemTitleColor(ContextCompat.getColor(context, R.color.color_default_accent));
        log.setItemContentColor(ContextCompat.getColor(context, R.color.color_default_background));

        log.show(context.getSupportFragmentManager(), "Log");*/
    }

    public static String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release + ")";
    }

    static Intent emailIntent(String emailAddress, String subject, String text, String title) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("text/email");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, text);
        return Intent.createChooser(email, title);
    }

    static Intent emailIntent(String subject, String text, String title, Uri filePath) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("text/email");
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, text);
        email.putExtra(Intent.EXTRA_STREAM, filePath);
        return Intent.createChooser(email, title);
    }

    static Intent linkIntent(String url) {
        Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        return link;
    }

    static Intent navIntent(Context context, Class activity) {
        Intent navigate = new Intent(context, activity);
        return navigate;
    }

    static String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }

    static void readFromInputStreamToOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[8 * 1024];
        int bytesRead = inputStream.read(buffer);
        while (bytesRead > -1) {
            outputStream.write(buffer, 0, bytesRead);
            bytesRead = inputStream.read(buffer);
        }

        outputStream.flush();
        outputStream.close();
    }

    static File createFileFromInputStream(File cacheDir, String fileName, InputStream inputStream) throws IOException {
        File file = File.createTempFile(fileName, null, cacheDir);
        OutputStream outputStream = new FileOutputStream(file);
        Utils.readFromInputStreamToOutputStream(inputStream, outputStream);
        return file;
    }
}
