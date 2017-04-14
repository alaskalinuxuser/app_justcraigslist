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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.alaskalinuxuser.justcraigslist.MainActivity.backChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.colorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.fabColorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.textColorChoice;

public class JCView extends AppCompatActivity {

    // Declare our variables.
    String jcTitle, jcURL, jcResult, jcSearch, jcPost, jcDate, jcDesc, jcPic;
    TextView titleViewjc, descripViewjc, dateViewjc;
    ImageView imageViewjc;
    Bitmap myBit, jcBit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jcview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout lljcview = (LinearLayout) findViewById(R.id.LLjcview);
        try {
            lljcview.setBackgroundResource(R.drawable.plymouth);
            setContentView(lljcview);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinearLayout theLayout = (LinearLayout) findViewById(R.id.theLayout);

        // Set the Background color.

        switch (backChoice) {

            case 0:

                if(Build.VERSION.SDK_INT > 16) {

                    theLayout.setBackground(getResources().getDrawable(R.drawable.jindong));

                } else {

                    theLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.jindong));

                }

                break;

            case 1:

                if(Build.VERSION.SDK_INT > 16) {

                    theLayout.setBackground(getResources().getDrawable(R.drawable.plymouth));

                } else {

                    theLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.plymouth));

                }

                break;

            case 2:

                if(Build.VERSION.SDK_INT > 16) {

                    theLayout.setBackground(getResources().getDrawable(R.drawable.chair));

                } else {

                    theLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.chair));

                }

                break;

            case 3:

                if(Build.VERSION.SDK_INT > 16) {

                    theLayout.setBackground(getResources().getDrawable(R.drawable.collie));

                } else {

                    theLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.collie));

                }

                break;

            case 4:

                if(Build.VERSION.SDK_INT > 16) {

                    theLayout.setBackground(getResources().getDrawable(R.drawable.flower));

                } else {

                    theLayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.flower));

                }

                break;

            case 5:

                theLayout.setBackgroundColor(Color.GRAY);

                break;

            case 6:

                theLayout.setBackgroundColor(Color.BLACK);

                break;

            case 7:

                theLayout.setBackgroundColor(Color.WHITE);

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

        // Define our views.
        titleViewjc = (TextView)findViewById(R.id.titleViewJC);
        descripViewjc = (TextView)findViewById(R.id.descriptViewJC);
        dateViewjc = (TextView)findViewById(R.id.dateViewJC);
        imageViewjc = (ImageView)findViewById(R.id.imageViewJC);

        // Since we may have made it invisable earlier when browsing....
        imageViewjc.setVisibility(View.VISIBLE);

        // Now we get that extra information.
        Intent i = getIntent();
        jcTitle = i.getStringExtra("titleIntent");
        jcURL = i.getStringExtra("urlIntent");
        jcResult = i.getStringExtra("resultAll");
        jcSearch = i.getStringExtra("searchTerm");

        // Testing only // Log.i("WJH", jcTitle + " " + jcURL + " " + jcSearch + " " + jcResult);

        // Let's split out just the portion we want.
        String[] jcSplit = jcResult.split(jcURL);
        String[] jcLimit = jcSplit[4].split("item>");

        // Now let's put it together.
        jcPost = jcSplit[2] + jcSplit [3] + jcLimit[0];

        // Let's look at what we have to work with.
        Log.i("WJH", jcPost);

        // Let's get the data we need from the RSS feed we already downloaded.
        mineInformation();

        // And let's write it on the screen.
        updatejcTexts();

        // The help button.
        FloatingActionButton fabjcHelp = (FloatingActionButton) findViewById(R.id.fabjcHelp);
        fabjcHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,
                        "Click the back button to return to the list, or the web button to view the full webpage",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // The web view button.
        FloatingActionButton fabjcWeb = (FloatingActionButton) findViewById(R.id.fabjcWeb);
        fabjcWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // First you define it.
                Intent webIntent = new Intent(JCView.this, WebViewActivity.class);
                // Put our newly defined title and url in there.
                webIntent.putExtra("titleIntent", jcTitle);
                webIntent.putExtra("urlIntent", jcURL);
                // Now you call it.
                startActivity(webIntent);

            }
        });

        // The back button.
        FloatingActionButton fabjcBack = (FloatingActionButton) findViewById(R.id.fabjcBack);
        fabjcBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // All done here, return to the list...
                finish();

            }
        });

        switch (fabColorChoice) {

            case 0:
                fabjcBack.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                fabjcHelp.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                fabjcWeb.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
                break;

            case 1:
                fabjcBack.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                fabjcHelp.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                fabjcWeb.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                break;

            case 2:
                fabjcBack.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                fabjcHelp.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                fabjcWeb.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;

            case 3:
                fabjcBack.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                fabjcHelp.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                fabjcWeb.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                break;

            case 4:
                fabjcBack.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                fabjcHelp.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                fabjcWeb.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
                break;

            case 5:
                fabjcBack.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                fabjcHelp.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                fabjcWeb.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                break;

            case 6:
                fabjcBack.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
                fabjcHelp.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
                fabjcWeb.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA));
                break;

            case 7:
                fabjcBack.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                fabjcHelp.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                fabjcWeb.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
                break;

        }

    }

    // This method gets our information.
    public void mineInformation () {

        // For testing only //
        Log.i("WJH", "started mining");

        int aDate = 0;
        int aDesc = 0;
        int aPic = 0;

        // Now regex the date with this pattern.
        Pattern patDate = Pattern.compile("date>(.*?)</dc");

        // And search for matches in the results.
        Matcher matDate = patDate.matcher(jcPost);

        // For every one you find, do this.
        while (matDate.find() && aDate == 0) {

            // Add to my magic number.
            aDate++;

            // The search results.
            String foundDate = (matDate.group(0));

            //Log.i("WJH", foundDate); // Log to see if this is working.

            int tempIntDate = foundDate.length() - 4;

            String tempDate = foundDate.substring(5, tempIntDate );

            // Tie the search results to a public variable we can use outside of this method.
            jcDate = tempDate;

            //Log.i("WJH", jcDate); // Log to see if this is working.

        }

        // Now to get the description. Sometimes users or posters put in funny characters, which
        // causes a problem when searching, so I just want it straight up, as is, like so:
        String[] tempSplitDesc = jcPost.split("description>");

            // Find the total length and cut out the trailing garbage.
            int tempIntDesc = tempSplitDesc[1].length() - 5;
            // Tie the search results to a public variable we can use outside of this method.
            jcDesc = tempSplitDesc[1].substring(9, tempIntDesc);

            //Log.i("WJH", jcDesc); // Log to see if this is working.



        // Now regex the date with this pattern.
        Pattern patPic = Pattern.compile("enclosure resource=\"(.*?)/>");

        // And search for matches in the results.
        Matcher matPic = patPic.matcher(jcPost);

        // For every one you find, do this.
        while (matPic.find() && aPic == 0) {

            // Add to my magic number.
            aPic++;

            // The search results.
            String foundPic = (matPic.group(0));

            // Log.i("WJH", foundPic); // Log to see if this is working.

            int tempIntPic = foundPic.length() - 21;

            String tempPic = foundPic.substring(20, tempIntPic );

            // Tie the search results to a public variable we can use outside of this method.
            jcPic = tempPic;

            String testPicExist = jcPic.substring(0,4);

            // Log.i("WJH", testPicExist);

            // Did the string look like a picture?
            if (testPicExist.equals("http")) {

                // Since we found a picture, let's use it.
                getPictures();

            } else {

                // Since there is no picture, let's get rid of the image.
                imageViewjc.setVisibility(View.INVISIBLE);

            }

            // Log.i("WJH", jcPic); // Log to see if this is working.

        }

    }

    public void updatejcTexts () {

        // For testing only //Log.i("WJH", "update texts");

        // Set the text views.
        titleViewjc.setText(jcTitle);
        dateViewjc.setText(jcDate);
        descripViewjc.setText(jcDesc);

        // Set the text color.
        switch (textColorChoice) {

            case 0:

                titleViewjc.setTextColor(ColorStateList.valueOf(Color.CYAN));
                descripViewjc.setTextColor(ColorStateList.valueOf(Color.CYAN));
                dateViewjc.setTextColor(ColorStateList.valueOf(Color.CYAN));

                break;

            case 1:
                titleViewjc.setTextColor(ColorStateList.valueOf(Color.RED));
                descripViewjc.setTextColor(ColorStateList.valueOf(Color.RED));
                dateViewjc.setTextColor(ColorStateList.valueOf(Color.RED));
                break;

            case 2:
                titleViewjc.setTextColor(ColorStateList.valueOf(Color.GREEN));
                descripViewjc.setTextColor(ColorStateList.valueOf(Color.GREEN));
                dateViewjc.setTextColor(ColorStateList.valueOf(Color.GREEN));
                break;

            case 3:
                titleViewjc.setTextColor(ColorStateList.valueOf(Color.GRAY));
                descripViewjc.setTextColor(ColorStateList.valueOf(Color.GRAY));
                dateViewjc.setTextColor(ColorStateList.valueOf(Color.GRAY));
                break;

            case 4:
                titleViewjc.setTextColor(ColorStateList.valueOf(Color.BLACK));
                descripViewjc.setTextColor(ColorStateList.valueOf(Color.BLACK));
                dateViewjc.setTextColor(ColorStateList.valueOf(Color.BLACK));
                break;

            case 5:
                titleViewjc.setTextColor(ColorStateList.valueOf(Color.WHITE));
                descripViewjc.setTextColor(ColorStateList.valueOf(Color.WHITE));
                dateViewjc.setTextColor(ColorStateList.valueOf(Color.WHITE));
                break;

            case 6:
                titleViewjc.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                descripViewjc.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                dateViewjc.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                break;

            case 7:
                titleViewjc.setTextColor(ColorStateList.valueOf(Color.BLUE));
                descripViewjc.setTextColor(ColorStateList.valueOf(Color.BLUE));
                dateViewjc.setTextColor(ColorStateList.valueOf(Color.BLUE));
                break;

        }

    }

    public void getPictures() {

        // A little fun graphic to keep them busy....
        imageViewjc.animate().rotation(10800f).setDuration(30000);

        // Testing only // Log.i("WJH", "Building list.");

        // Start a new asyncronous/background download task....
        DownloadPic jctask = new DownloadPic();
        String result = null;


        // Try this in case it fails.
        try {

            // Grab this result as a string.
            //result = task.execute(theSearchTerm).get();
            jctask.execute(jcPic);

            // Testing only // Log.i("WJH", result);

            // And a catch when/if it does fail.
        } catch (Exception e) {

            // What if there is a problem with the download?
            Log.i("WJH", "There was an exception with the download.");

        }

        // Testing only // Log.i("WJH", searchArrayList.toString());
        // Testing only // Log.i("WJH", searchURLList.toString());

    }

    // Our new class to download the picture in the background.
    public class DownloadPic extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection;

            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                // To download in one "go" as Rob says.
                urlConnection.connect();

                // To grab the whole thing at once.
                InputStream inStream = urlConnection.getInputStream();

                // Turn that data into a bitmap.
                myBit = BitmapFactory.decodeStream(inStream);

                jcBit = myBit;
                // Return that bitmap.
                return myBit;

                // Have an exception if there is a failure.
            } catch (Exception e) {

                e.printStackTrace();

                Log.i("WJH", "Failed"); // You can log this to see the failure if needed.

                // Since it fails, return nothing.
                return null;

            }


        }

        @Override // What to do when we get the picture from the web.
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            // Do this when done. In our case, set the bitmap to our image view.
            imageViewjc.animate().cancel();
            imageViewjc.setRotation(0f);
            imageViewjc.setImageBitmap(jcBit);

        }
    }

}
