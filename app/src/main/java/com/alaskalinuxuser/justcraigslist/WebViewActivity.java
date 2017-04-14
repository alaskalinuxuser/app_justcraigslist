/*  Copyright 2017 by AlaskaLinuxUser (https://thealaskalinuxuser.wordpress.com)
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
package com.alaskalinuxuser.justcraigslist;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.alaskalinuxuser.justcraigslist.MainActivity.backChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.colorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.fabColorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.textColorChoice;

public class WebViewActivity extends AppCompatActivity {

    // Let's declare some variables.
    WebView webView;
    TextView titleText;
    ImageView waitingImage;
    String theTitle, theURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout llwebview = (LinearLayout) findViewById(R.id.LLwebview);

        // Set the Background color.
        switch (backChoice) {

            case 0:

                if(Build.VERSION.SDK_INT > 16) {

                    llwebview.setBackground(getResources().getDrawable(R.drawable.jindong));

                } else {

                    llwebview.setBackgroundDrawable(getResources().getDrawable(R.drawable.jindong));

                }

                break;

            case 1:

                if(Build.VERSION.SDK_INT > 16) {

                    llwebview.setBackground(getResources().getDrawable(R.drawable.plymouth));

                } else {

                    llwebview.setBackgroundDrawable(getResources().getDrawable(R.drawable.plymouth));

                }

                break;

            case 2:

                if(Build.VERSION.SDK_INT > 16) {

                    llwebview.setBackground(getResources().getDrawable(R.drawable.chair));

                } else {

                    llwebview.setBackgroundDrawable(getResources().getDrawable(R.drawable.chair));

                }

                break;

            case 3:

                if(Build.VERSION.SDK_INT > 16) {

                    llwebview.setBackground(getResources().getDrawable(R.drawable.collie));

                } else {

                    llwebview.setBackgroundDrawable(getResources().getDrawable(R.drawable.collie));

                }

                break;

            case 4:

                if(Build.VERSION.SDK_INT > 16) {

                    llwebview.setBackground(getResources().getDrawable(R.drawable.flower));

                } else {

                    llwebview.setBackgroundDrawable(getResources().getDrawable(R.drawable.flower));

                }

                break;

            case 5:

                llwebview.setBackgroundColor(Color.GRAY);

                break;

            case 6:

                llwebview.setBackgroundColor(Color.BLACK);

                break;

            case 7:

                llwebview.setBackgroundColor(Color.WHITE);

                break;

        }

        // Set the title bar color.
        switch (colorChoice) {

            case 0:
                toolbar.setBackgroundColor(Color.BLUE);
                break;

            case 1:
                toolbar.setBackgroundColor(Color.RED);
                break;

            case 2:
                toolbar.setBackgroundColor(Color.GREEN);
                break;

            case 3:
                toolbar.setBackgroundColor(Color.GRAY);
                break;

            case 4:
                toolbar.setBackgroundColor(Color.BLACK);
                break;

            case 5:
                toolbar.setBackgroundColor(Color.WHITE);
                break;

            case 6:
                toolbar.setBackgroundColor(Color.MAGENTA);
                break;

            case 7:
                toolbar.setBackgroundColor(Color.CYAN);
                break;

        }

        // Now we get that extra information.
        Intent i = getIntent();
        theTitle = i.getStringExtra("titleIntent");
        theURL = i.getStringExtra("urlIntent");

        // Testing only // Log.i("WJH", theTitle +" " + theURL);

        // Let's define that stuff we declared.
        webView = (WebView)findViewById(R.id.webView);
        titleText = (TextView)findViewById(R.id.titleView);
        waitingImage = (ImageView)findViewById(R.id.waitingImage);

        // And we should set the title text.
        titleText.setText(theTitle);

        // Set the text color.
        switch (textColorChoice) {

            case 0:

                titleText.setTextColor(ColorStateList.valueOf(Color.CYAN));

                break;

            case 1:
                titleText.setTextColor(ColorStateList.valueOf(Color.RED));
                break;

            case 2:
                titleText.setTextColor(ColorStateList.valueOf(Color.GREEN));
                break;

            case 3:
                titleText.setTextColor(ColorStateList.valueOf(Color.GRAY));
                break;

            case 4:
                titleText.setTextColor(ColorStateList.valueOf(Color.BLACK));
                break;

            case 5:
                titleText.setTextColor(ColorStateList.valueOf(Color.WHITE));
                break;

            case 6:
                titleText.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                break;

            case 7:
                titleText.setTextColor(ColorStateList.valueOf(Color.BLUE));
                break;

        }

        // A little fun graphic to keep them busy....
        waitingImage.animate().rotation(10800f).setDuration(45000);

        // To enable javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // To keep it here in our mini webview browser
        webView.setWebViewClient(new WebViewClient(){

            // This will happen when it is done loading.
            public void onPageFinished(WebView view, String url) {

                // Now that it is done loading, let's make it visable.
                webView.setVisibility(View.VISIBLE);

            }
        });

        // To load which webpage, in this case, mine.
        webView.loadUrl(theURL);

        // Let's use a floating button for a back button.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        switch (fabColorChoice) {

            case 0:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                break;

            case 1:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                break;

            case 2:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;

            case 3:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                break;

            case 4:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                break;

            case 5:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                break;

            case 6:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
                break;

            case 7:
                fab.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                break;

        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go back to the previous activity.
                finish();

            }
        });
    }

}
