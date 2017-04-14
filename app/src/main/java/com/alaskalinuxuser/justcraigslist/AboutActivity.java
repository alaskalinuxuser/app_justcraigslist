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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.alaskalinuxuser.justcraigslist.MainActivity.colorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.fabColorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.textColorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.backChoice;

public class AboutActivity extends AppCompatActivity {

    // Declare views...
    FloatingActionButton fabGit, fabWord, fabBack;
    Toolbar toolbar;
    TextView atv1, atv2, atv3;
    LinearLayout llAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Define views.
        atv1 = (TextView)findViewById(R.id.atv1);
        atv2 = (TextView)findViewById(R.id.atv2);
        atv3 = (TextView)findViewById(R.id.atv3);
        llAbout = (LinearLayout)findViewById(R.id.LLAbout);

        // The github floating button.
        fabGit = (FloatingActionButton) findViewById(R.id.fabGithub);
        fabGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myHubGit();

            }
        });

        // The wordpress floating button.
        fabWord = (FloatingActionButton) findViewById(R.id.fabWord);
        fabWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myWebsite();

            }
        });

        // The back floating button.
        fabBack = (FloatingActionButton) findViewById(R.id.fabReturn);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goBack();

            }
        });

        // And set our colors.
        selectAboutColors();

    }

        // When they click on the github icon.
        public void myHubGit () {

            goToUrl ( "https://github.com/alaskalinuxuser");

        }

        // When they click on the wordpress icon.
        public void myWebsite () {

            goToUrl ( "https://thealaskalinuxuser.wordpress.com");

        }

        // When they click on the how to text.
        public void howL (View view) {

            goToUrl ( "http://www.apache.org/licenses/LICENSE-2.0");

        }

        // To launch one of the above URL's.
        private void goToUrl (String url) {
            Uri uriUrl = Uri.parse(url);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        }

        // To kill this activity and go back to Activity Main.
        public void goBack () {
            finish();
        }


    // My method to set all the colors.
    public void selectAboutColors () {

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

        // Set the fab color.
        switch (fabColorChoice) {

            case 0:
                fabBack.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                fabGit.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                fabWord.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                break;

            case 1:
                fabBack.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                fabGit.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                fabWord.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                break;

            case 2:
                fabBack.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                fabGit.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                fabWord.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;

            case 3:
                fabBack.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                fabGit.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                fabWord.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                break;

            case 4:
                fabBack.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                fabGit.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                fabWord.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                break;

            case 5:
                fabBack.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                fabGit.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                fabWord.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                break;

            case 6:
                fabBack.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
                fabGit.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
                fabWord.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
                break;

            case 7:
                fabBack.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                fabGit.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                fabWord.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                break;

        }

        // Set the text color.
        switch (textColorChoice) {

            case 0:

                atv1.setTextColor(ColorStateList.valueOf(Color.CYAN));
                atv2.setTextColor(ColorStateList.valueOf(Color.CYAN));
                atv3.setTextColor(ColorStateList.valueOf(Color.CYAN));

                break;

            case 1:
                atv1.setTextColor(ColorStateList.valueOf(Color.RED));
                atv2.setTextColor(ColorStateList.valueOf(Color.RED));
                atv3.setTextColor(ColorStateList.valueOf(Color.RED));
                break;

            case 2:
                atv1.setTextColor(ColorStateList.valueOf(Color.GREEN));
                atv2.setTextColor(ColorStateList.valueOf(Color.GREEN));
                atv3.setTextColor(ColorStateList.valueOf(Color.GREEN));
                break;

            case 3:
                atv1.setTextColor(ColorStateList.valueOf(Color.GRAY));
                atv2.setTextColor(ColorStateList.valueOf(Color.GRAY));
                atv3.setTextColor(ColorStateList.valueOf(Color.GRAY));
                break;

            case 4:
                atv1.setTextColor(ColorStateList.valueOf(Color.BLACK));
                atv2.setTextColor(ColorStateList.valueOf(Color.BLACK));
                atv3.setTextColor(ColorStateList.valueOf(Color.BLACK));
                break;

            case 5:
                atv1.setTextColor(ColorStateList.valueOf(Color.WHITE));
                atv2.setTextColor(ColorStateList.valueOf(Color.WHITE));
                atv3.setTextColor(ColorStateList.valueOf(Color.WHITE));
                break;

            case 6:
                atv1.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                atv2.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                atv3.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                break;

            case 7:
                atv1.setTextColor(ColorStateList.valueOf(Color.BLUE));
                atv2.setTextColor(ColorStateList.valueOf(Color.BLUE));
                atv3.setTextColor(ColorStateList.valueOf(Color.BLUE));
                break;

        }

        /* Set the Background color.
        switch (backChoice) {

            case 0:

                if(Build.VERSION.SDK_INT > 16) {

                    llAbout.setBackground(getResources().getDrawable(R.drawable.jindong));

                } else {

                    llAbout.setBackgroundDrawable(getResources().getDrawable(R.drawable.jindong));

                }

                break;

            case 1:

                if(Build.VERSION.SDK_INT > 16) {

                    llAbout.setBackground(getResources().getDrawable(R.drawable.plymouth));

                } else {

                    llAbout.setBackgroundDrawable(getResources().getDrawable(R.drawable.plymouth));

                }

                break;

            case 2:

                if(Build.VERSION.SDK_INT > 16) {

                    llAbout.setBackground(getResources().getDrawable(R.drawable.chair));

                } else {

                    llAbout.setBackgroundDrawable(getResources().getDrawable(R.drawable.chair));

                }

                break;

            case 3:

                if(Build.VERSION.SDK_INT > 16) {

                    llAbout.setBackground(getResources().getDrawable(R.drawable.collie));

                } else {

                    llAbout.setBackgroundDrawable(getResources().getDrawable(R.drawable.collie));

                }

                break;

            case 4:

                if(Build.VERSION.SDK_INT > 16) {

                    llAbout.setBackground(getResources().getDrawable(R.drawable.flower));

                } else {

                    llAbout.setBackgroundDrawable(getResources().getDrawable(R.drawable.flower));

                }

                break;

            case 5:

                llAbout.setBackgroundColor(Color.GRAY);

                break;

            case 6:

                llAbout.setBackgroundColor(Color.BLACK);

                break;

            case 7:

                llAbout.setBackgroundColor(Color.WHITE);

                break;

        } */

    }

}