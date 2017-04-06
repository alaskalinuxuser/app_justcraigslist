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
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabGit = (FloatingActionButton) findViewById(R.id.fabGithub);
        fabGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myHubGit();

            }
        });

        FloatingActionButton fabWord = (FloatingActionButton) findViewById(R.id.fabWord);
        fabWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myWebsite();

            }
        });

        FloatingActionButton fabBack = (FloatingActionButton) findViewById(R.id.fabReturn);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goBack();

            }
        });
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


    }