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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    // Let's declare our variables, texts, and etc.
    String cityChoice, urlChoice, minChoice, maxchoice, searchChoice, searchTerm, catChoice, codeChoice;
    int minInt, maxInt;
    TextView cityView, catView, tv3, tv6, tv8;
    EditText minPriceEdit, maxPriceEdit, searchNowEdit;
    static SharedPreferences myPrefs;
    static int textColorChoice, colorChoice, backChoice, fabColorChoice;
    FloatingActionButton fab;
    Toolbar toolbar;
    ImageView iV1, iV2, iV3;
    Drawable drawableMain;
    LinearLayout llMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Now let's define and identify our views and texts.
        llMain = (LinearLayout)findViewById(R.id.LLMain);
        cityView = (TextView)findViewById(R.id.cityView);
        catView = (TextView)findViewById(R.id.catView);
        minPriceEdit = (EditText)findViewById(R.id.minPriceEdit);
        maxPriceEdit = (EditText)findViewById(R.id.maxPriceEdit);
        searchNowEdit = (EditText)findViewById(R.id.searchNowEdit);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv6 = (TextView)findViewById(R.id.textView6);
        tv8 = (TextView)findViewById(R.id.textView8);
        iV1 = (ImageView)findViewById(R.id.imageView);
        iV2 = (ImageView)findViewById(R.id.imageView2);
        iV3 = (ImageView)findViewById(R.id.pickLocation);

        // Set up my shared preferences, to save our user's prefered search...
        myPrefs = this.getSharedPreferences("com.alaskalinuxuser.justcraigslist", Context.MODE_PRIVATE);

        // Wrap this in try in case it fails.
        try {

            // Since the app just opened, we are checking if there are some preferences from previous use.
            searchChoice = myPrefs.getString("searchPref", null);
            minChoice = myPrefs.getString("minPref", null);
            maxchoice = myPrefs.getString("maxPref", null);
            urlChoice = myPrefs.getString("urlPref", null);
            cityChoice = myPrefs.getString("cityPref", null);
            catChoice = myPrefs.getString("catPref", null);
            codeChoice = myPrefs.getString("codePref", null);
            // Let's import our preference for colors and texts...
            colorChoice = Integer.parseInt(myPrefs.getString("colorPref", null));
            textColorChoice = Integer.parseInt(myPrefs.getString("textColorPref", null));
            fabColorChoice = Integer.parseInt(myPrefs.getString("fabColorPref", null));
            backChoice = Integer.parseInt(myPrefs.getString("backPref", null));

            Log.i("WJH", String.valueOf(colorChoice));
            Log.i("WJH", String.valueOf(textColorChoice));
            Log.i("WJH", String.valueOf(fabColorChoice));
            Log.i("WJH", String.valueOf(backChoice));

            //Testing only, not needed//Log.i("WJH", "Got saved preferences.");

            // If there is a problem....
        } catch (Exception e) {

            // Oops, there is no preferences, or they are malformed.
            Log.i("WJH", "No preferences.");

        }

        // If after loading the preferences, we find none, then:
        if (cityChoice == null) {

            // Give some default values.
            cityChoice = "auburn, Alabama";
            urlChoice = "https://auburn.craigslist.org/";
            minChoice = "0";
            maxchoice = "1000000";
            searchChoice = "";
            textColorChoice = 5;
            fabColorChoice = 3;
            backChoice = 1;
            colorChoice = 4;

        }

        if (catChoice == null) {

            // This is seperate due to the upgrade. Those who were on the old version would have
            // a null value for code, but a full value for citychoice.
            codeChoice = "sss";
            catChoice = "All categories...";

        }

        // Now set all of our texts in accordance with our preferences.
        setAllTexts();

        // Floating action button to be a help button for the user.
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When clicked, show this handy little help/tip.
                Snackbar.make(view, "Click the map to choose a location. "
                        + "Then type in your search terms and click the search button.",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Now to set up our color scheme.
        selectColors();

    }

    // Method to set the text views.
    public void setAllTexts () {

        cityView.setText(cityChoice);
        minPriceEdit.setText(minChoice);
        maxPriceEdit.setText(maxchoice);
        searchNowEdit.setText(searchChoice);
        catView.setText(catChoice);

    }

    // Method for the user to pick a location.
    public void pickLocation (View pickView) {

        // And call our intent.
        Intent myIntent = new Intent(getApplicationContext(), PickCityActivity.class);
        // and start that activity expecting a result.
        startActivityForResult(myIntent, 1);

    }

    // Method for the user to pick a category.
    public void pickCat (View pickView) {

        // And call our intent.
        Intent myIntent = new Intent(getApplicationContext(), PickCatActivity.class);
        // and start that activity expecting a result.
        startActivityForResult(myIntent, 2);

    }

    // Method to save the user's preferences.
    public void saveMyPrefs () {

        // Testing only, not needed.// Log.i("WJH", "Saving prefs.");

        try {

            minInt = Integer.parseInt(minPriceEdit.getText().toString());

        } catch (Exception e) {

            minInt = 0;

        }

        try {

            maxInt = Integer.parseInt(maxPriceEdit.getText().toString());

        } catch (Exception e) {

            maxInt = 1000000000;

        }

        // Let's convert every text field to a string that we can save.
        searchChoice = searchNowEdit.getText().toString();
        minChoice = String.valueOf(minInt);
        maxchoice = String.valueOf(maxInt);
        cityChoice = cityView.getText().toString();

        // Testing only.// Log.i("WJH", searchChoice + minChoice + maxchoice + cityChoice + urlChoice);

        // So, now that the user has chosen something, let's save their prefs for next time.
        myPrefs.edit ().putString("searchPref", searchChoice).apply();
        myPrefs.edit ().putString("minPref", minChoice).apply();
        myPrefs.edit ().putString("maxPref", maxchoice).apply();
        myPrefs.edit ().putString("urlPref", urlChoice).apply();
        myPrefs.edit ().putString("cityPref", cityChoice).apply();
        myPrefs.edit ().putString("catPref", catChoice).apply();
        myPrefs.edit ().putString("codePref", codeChoice).apply();

    }

    @Override // So, what to do when we get the result back from saving our new or edited note.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // If it was code 1 (from our intent).
        if (requestCode == 1) { // And if it was OK, not a fail.
            if(resultCode == Activity.RESULT_OK){

                // Then, go ahead and grab the city they just chose.
                cityChoice = data.getStringExtra("cityIntent");
                urlChoice = data.getStringExtra("urlIntent");

                // Set our city text view to match.
                cityView.setText(cityChoice);

                // Testing only.// Log.i("WJH", cityChoice + "/" + urlChoice);

                // Let's update our saved preferences with the user's choice of city.
                saveMyPrefs();

            }

            // If the result wan not okay...
            if (resultCode == Activity.RESULT_CANCELED) {

                // Just log that it didn't return a result.
                Log.i("WJH", "There was no result.");

            }
        } else if (requestCode == 2) { // Our choice of category, if it was OK, not a fail.
            if(resultCode == Activity.RESULT_OK){

                // Then, go ahead and grab the city they just chose.
                codeChoice = data.getStringExtra("codeIntent");
                catChoice = data.getStringExtra("catIntent");

                // Set our city text view to match.
                catView.setText(catChoice);

                // Testing only.// Log.i("WJH", cityChoice + "/" + urlChoice);

                // Let's update our saved preferences with the user's choice of category.
                saveMyPrefs();

            }

            // If the result wan not okay...
            if (resultCode == Activity.RESULT_CANCELED) {

                // Just log that it didn't return a result.
                Log.i("WJH", "There was no result.");

            }
        } else if (requestCode == 219) { // From settings, and if it was OK, not a fail.
            if(resultCode == Activity.RESULT_OK){



                colorChoice = Integer.parseInt(data.getStringExtra("colorChoice"));
                textColorChoice = Integer.parseInt(data.getStringExtra("textColorChoice"));
                fabColorChoice = Integer.parseInt(data.getStringExtra("fabColorChoice"));
                backChoice = Integer.parseInt(data.getStringExtra("backChoice"));

                Log.i("WJH", String.valueOf(colorChoice));
                Log.i("WJH", String.valueOf(textColorChoice));
                Log.i("WJH", String.valueOf(fabColorChoice));
                Log.i("WJH", String.valueOf(backChoice));

                //And let's save our new preferences.
                myPrefs.edit ().putString("colorPref", String.valueOf(colorChoice)).apply();
                myPrefs.edit ().putString("textColorPref", String.valueOf(textColorChoice)).apply();
                myPrefs.edit ().putString("fabColorPref", String.valueOf(fabColorChoice)).apply();
                myPrefs.edit ().putString("backPref", String.valueOf(backChoice)).apply();

                selectColors();

            }

            // If the result wan not okay...
            if (resultCode == Activity.RESULT_CANCELED) {

                // Just log that it didn't return a result.
                Log.i("WJH", "There was no two result.");

            }
        }
    }

    public void searchNow (View searchView) {

        // Since we like to search for this, let's save that search for later.
        saveMyPrefs();

        // We should encode their search terms so we can get a workable URL.
        String whatSearch = URLEncoder.encode(searchChoice);

        //https://fairbanks.craigslist.org/search/sss?format=rss&query=chev*&min_price=0&max_price=999999999999
        searchTerm = urlChoice + "search/" + codeChoice + "?format=rss&query="
                + whatSearch + "&min_price="
                + minChoice + "&max_price="
                + maxchoice;

        //Testing only, not needed.// Log.i("WJH", searchTerm);

        // Call an intent to go to the weblist screen when you click the about button.
        // First you define it.
        Intent searchIntent = new Intent(MainActivity.this, WebListActivity.class);
        // Put our newly defined search terms in there.
        searchIntent.putExtra("searchTerm", searchTerm);
        // Now you call it.
        startActivity(searchIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            // Call an intent to go to the settings screen.
            // First you define it.
            Intent settingIntent = new Intent(MainActivity.this, SettingsActivity.class);
            // Now you call it.
            startActivityForResult(settingIntent, 219);

            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {

            // Of course, we need to tell people about our open source app, the github link for source, etc.

            // Call an intent to go to the second screen when you click the about button.
            // First you define it.
            Intent myintent = new Intent(MainActivity.this, AboutActivity.class);
            // Now you call it.
            startActivity(myintent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // My method to set all the colors.
    public void selectColors () {

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

        // Set the text color.
        switch (textColorChoice) {

            case 0:
                cityView.setTextColor(ColorStateList.valueOf(Color.CYAN));
                catView.setTextColor(ColorStateList.valueOf(Color.CYAN));
                minPriceEdit.setTextColor(ColorStateList.valueOf(Color.CYAN));
                maxPriceEdit.setTextColor(ColorStateList.valueOf(Color.CYAN));
                searchNowEdit.setTextColor(ColorStateList.valueOf(Color.CYAN));
                drawableMain = searchNowEdit.getBackground();
                drawableMain.setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_ATOP);
                if(Build.VERSION.SDK_INT > 16) {
                    searchNowEdit.setBackground(drawableMain); // set the new drawable to EditText
                    minPriceEdit.setBackground(drawableMain);
                    maxPriceEdit.setBackground(drawableMain);
                }else{
                    searchNowEdit.setBackgroundDrawable(drawableMain); // use setBackgroundDrawable because setBackground required API 16
                    minPriceEdit.setBackgroundDrawable(drawableMain);
                    maxPriceEdit.setBackgroundDrawable(drawableMain);}
                tv3.setTextColor(ColorStateList.valueOf(Color.CYAN));
                tv6.setTextColor(ColorStateList.valueOf(Color.CYAN));
                tv8.setTextColor(ColorStateList.valueOf(Color.CYAN));
                iV1.setBackgroundColor(Color.CYAN);
                iV2.setBackgroundColor(Color.CYAN);
                iV3.setBackgroundColor(Color.CYAN);
                break;

            case 1:
                cityView.setTextColor(ColorStateList.valueOf(Color.RED));
                catView.setTextColor(ColorStateList.valueOf(Color.RED));
                minPriceEdit.setTextColor(ColorStateList.valueOf(Color.RED));
                maxPriceEdit.setTextColor(ColorStateList.valueOf(Color.RED));
                searchNowEdit.setTextColor(ColorStateList.valueOf(Color.RED));
                drawableMain = searchNowEdit.getBackground();
                drawableMain.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                if(Build.VERSION.SDK_INT > 16) {
                    searchNowEdit.setBackground(drawableMain); // set the new drawable to EditText
                    minPriceEdit.setBackground(drawableMain);
                    maxPriceEdit.setBackground(drawableMain);
                }else{
                    searchNowEdit.setBackgroundDrawable(drawableMain); // use setBackgroundDrawable because setBackground required API 16
                    minPriceEdit.setBackgroundDrawable(drawableMain);
                    maxPriceEdit.setBackgroundDrawable(drawableMain);}
                tv3.setTextColor(ColorStateList.valueOf(Color.RED));
                tv6.setTextColor(ColorStateList.valueOf(Color.RED));
                tv8.setTextColor(ColorStateList.valueOf(Color.RED));
                iV1.setBackgroundColor(Color.RED);
                iV2.setBackgroundColor(Color.RED);
                iV3.setBackgroundColor(Color.RED);
                break;

            case 2:
                cityView.setTextColor(ColorStateList.valueOf(Color.GREEN));
                catView.setTextColor(ColorStateList.valueOf(Color.GREEN));
                minPriceEdit.setTextColor(ColorStateList.valueOf(Color.GREEN));
                maxPriceEdit.setTextColor(ColorStateList.valueOf(Color.GREEN));
                searchNowEdit.setTextColor(ColorStateList.valueOf(Color.GREEN));
                drawableMain = searchNowEdit.getBackground();
                drawableMain.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                if(Build.VERSION.SDK_INT > 16) {
                    searchNowEdit.setBackground(drawableMain); // set the new drawable to EditText
                    minPriceEdit.setBackground(drawableMain);
                    maxPriceEdit.setBackground(drawableMain);
                }else{
                    searchNowEdit.setBackgroundDrawable(drawableMain); // use setBackgroundDrawable because setBackground required API 16
                    minPriceEdit.setBackgroundDrawable(drawableMain);
                    maxPriceEdit.setBackgroundDrawable(drawableMain);}
                tv3.setTextColor(ColorStateList.valueOf(Color.GREEN));
                tv6.setTextColor(ColorStateList.valueOf(Color.GREEN));
                tv8.setTextColor(ColorStateList.valueOf(Color.GREEN));
                iV1.setBackgroundColor(Color.GREEN);
                iV2.setBackgroundColor(Color.GREEN);
                iV3.setBackgroundColor(Color.GREEN);
                break;

            case 3:
                cityView.setTextColor(ColorStateList.valueOf(Color.GRAY));
                catView.setTextColor(ColorStateList.valueOf(Color.GRAY));
                minPriceEdit.setTextColor(ColorStateList.valueOf(Color.GRAY));
                maxPriceEdit.setTextColor(ColorStateList.valueOf(Color.GRAY));
                searchNowEdit.setTextColor(ColorStateList.valueOf(Color.GRAY));
                drawableMain = searchNowEdit.getBackground();
                drawableMain.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                if(Build.VERSION.SDK_INT > 16) {
                    searchNowEdit.setBackground(drawableMain); // set the new drawable to EditText
                    minPriceEdit.setBackground(drawableMain);
                    maxPriceEdit.setBackground(drawableMain);
                }else{
                    searchNowEdit.setBackgroundDrawable(drawableMain); // use setBackgroundDrawable because setBackground required API 16
                    minPriceEdit.setBackgroundDrawable(drawableMain);
                    maxPriceEdit.setBackgroundDrawable(drawableMain);}
                tv3.setTextColor(ColorStateList.valueOf(Color.GRAY));
                tv6.setTextColor(ColorStateList.valueOf(Color.GRAY));
                tv8.setTextColor(ColorStateList.valueOf(Color.GRAY));
                iV1.setBackgroundColor(Color.GRAY);
                iV2.setBackgroundColor(Color.GRAY);
                iV3.setBackgroundColor(Color.GRAY);
                break;

            case 4:
                cityView.setTextColor(ColorStateList.valueOf(Color.BLACK));
                catView.setTextColor(ColorStateList.valueOf(Color.BLACK));
                minPriceEdit.setTextColor(ColorStateList.valueOf(Color.BLACK));
                maxPriceEdit.setTextColor(ColorStateList.valueOf(Color.BLACK));
                searchNowEdit.setTextColor(ColorStateList.valueOf(Color.BLACK));
                drawableMain = searchNowEdit.getBackground();
                drawableMain.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                if(Build.VERSION.SDK_INT > 16) {
                    searchNowEdit.setBackground(drawableMain); // set the new drawable to EditText
                    minPriceEdit.setBackground(drawableMain);
                    maxPriceEdit.setBackground(drawableMain);
                }else{
                    searchNowEdit.setBackgroundDrawable(drawableMain); // use setBackgroundDrawable because setBackground required API 16
                    minPriceEdit.setBackgroundDrawable(drawableMain);
                    maxPriceEdit.setBackgroundDrawable(drawableMain);}
                tv3.setTextColor(ColorStateList.valueOf(Color.BLACK));
                tv6.setTextColor(ColorStateList.valueOf(Color.BLACK));
                tv8.setTextColor(ColorStateList.valueOf(Color.BLACK));
                iV1.setBackgroundColor(Color.BLACK);
                iV2.setBackgroundColor(Color.BLACK);
                iV3.setBackgroundColor(Color.BLACK);
                break;

            case 5:
                cityView.setTextColor(ColorStateList.valueOf(Color.WHITE));
                catView.setTextColor(ColorStateList.valueOf(Color.WHITE));
                minPriceEdit.setTextColor(ColorStateList.valueOf(Color.WHITE));
                maxPriceEdit.setTextColor(ColorStateList.valueOf(Color.WHITE));
                searchNowEdit.setTextColor(ColorStateList.valueOf(Color.WHITE));
                drawableMain = searchNowEdit.getBackground();
                drawableMain.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                if(Build.VERSION.SDK_INT > 16) {
                    searchNowEdit.setBackground(drawableMain); // set the new drawable to EditText
                    minPriceEdit.setBackground(drawableMain);
                    maxPriceEdit.setBackground(drawableMain);
                }else{
                    searchNowEdit.setBackgroundDrawable(drawableMain); // use setBackgroundDrawable because setBackground required API 16
                    minPriceEdit.setBackgroundDrawable(drawableMain);
                    maxPriceEdit.setBackgroundDrawable(drawableMain);}
                tv3.setTextColor(ColorStateList.valueOf(Color.WHITE));
                tv6.setTextColor(ColorStateList.valueOf(Color.WHITE));
                tv8.setTextColor(ColorStateList.valueOf(Color.WHITE));
                iV1.setBackgroundColor(Color.WHITE);
                iV2.setBackgroundColor(Color.WHITE);
                iV3.setBackgroundColor(Color.WHITE);
                break;

            case 6:
                cityView.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                catView.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                minPriceEdit.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                maxPriceEdit.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                searchNowEdit.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                drawableMain = searchNowEdit.getBackground();
                drawableMain.setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_ATOP);
                if(Build.VERSION.SDK_INT > 16) {
                    searchNowEdit.setBackground(drawableMain); // set the new drawable to EditText
                    minPriceEdit.setBackground(drawableMain);
                    maxPriceEdit.setBackground(drawableMain);
                }else{
                    searchNowEdit.setBackgroundDrawable(drawableMain); // use setBackgroundDrawable because setBackground required API 16
                    minPriceEdit.setBackgroundDrawable(drawableMain);
                    maxPriceEdit.setBackgroundDrawable(drawableMain);}
                tv3.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                tv6.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                tv8.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                iV1.setBackgroundColor(Color.MAGENTA);
                iV2.setBackgroundColor(Color.MAGENTA);
                iV3.setBackgroundColor(Color.MAGENTA);
                break;

            case 7:
                cityView.setTextColor(ColorStateList.valueOf(Color.BLUE));
                catView.setTextColor(ColorStateList.valueOf(Color.BLUE));
                minPriceEdit.setTextColor(ColorStateList.valueOf(Color.BLUE));
                maxPriceEdit.setTextColor(ColorStateList.valueOf(Color.BLUE));
                searchNowEdit.setTextColor(ColorStateList.valueOf(Color.BLUE));
                drawableMain = searchNowEdit.getBackground();
                drawableMain.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
                if(Build.VERSION.SDK_INT > 16) {
                    searchNowEdit.setBackground(drawableMain); // set the new drawable to EditText
                    minPriceEdit.setBackground(drawableMain);
                    maxPriceEdit.setBackground(drawableMain);
                }else{
                    searchNowEdit.setBackgroundDrawable(drawableMain); // use setBackgroundDrawable because setBackground required API 16
                    minPriceEdit.setBackgroundDrawable(drawableMain);
                    maxPriceEdit.setBackgroundDrawable(drawableMain);
                }
                tv3.setTextColor(ColorStateList.valueOf(Color.BLUE));
                tv6.setTextColor(ColorStateList.valueOf(Color.BLUE));
                tv8.setTextColor(ColorStateList.valueOf(Color.BLUE));
                iV1.setBackgroundColor(Color.BLUE);
                iV2.setBackgroundColor(Color.BLUE);
                iV3.setBackgroundColor(Color.BLUE);
                break;

        }

        // Set the Background color.
        switch (backChoice) {

            case 0:

                if(Build.VERSION.SDK_INT > 16) {

                    llMain.setBackground(getResources().getDrawable(R.drawable.jindong));

                } else {

                    llMain.setBackgroundDrawable(getResources().getDrawable(R.drawable.jindong));

                }

                break;

            case 1:

                if(Build.VERSION.SDK_INT > 16) {

                    llMain.setBackground(getResources().getDrawable(R.drawable.plymouth));

                } else {

                    llMain.setBackgroundDrawable(getResources().getDrawable(R.drawable.plymouth));

                }

                break;

            case 2:

                if(Build.VERSION.SDK_INT > 16) {

                    llMain.setBackground(getResources().getDrawable(R.drawable.chair));

                } else {

                    llMain.setBackgroundDrawable(getResources().getDrawable(R.drawable.chair));

                }

                break;

            case 3:

                if(Build.VERSION.SDK_INT > 16) {

                    llMain.setBackground(getResources().getDrawable(R.drawable.collie));

                } else {

                    llMain.setBackgroundDrawable(getResources().getDrawable(R.drawable.collie));

                }

                break;

            case 4:

                if(Build.VERSION.SDK_INT > 16) {

                    llMain.setBackground(getResources().getDrawable(R.drawable.flower));

                } else {

                    llMain.setBackgroundDrawable(getResources().getDrawable(R.drawable.flower));

                }

                break;

            case 5:

                llMain.setBackgroundColor(Color.GRAY);

                break;

            case 6:

                llMain.setBackgroundColor(Color.BLACK);

                break;

            case 7:

                llMain.setBackgroundColor(Color.WHITE);

                break;

        }

    }

}
