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
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

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

        // Now we get that extra information.
        Intent i = getIntent();
        theTitle = i.getStringExtra("titleIntent");
        theURL = i.getStringExtra("urlIntent");

        Log.i("WJH", theTitle +" " + theURL);

        // Let's define that stuff we declared.
        webView = (WebView)findViewById(R.id.webView);
        titleText = (TextView)findViewById(R.id.titleView);
        waitingImage = (ImageView)findViewById(R.id.waitingImage);

        // And we should set the title text.
        titleText.setText(theTitle);

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go back to the previous activity.
                finish();

            }
        });
    }

}
