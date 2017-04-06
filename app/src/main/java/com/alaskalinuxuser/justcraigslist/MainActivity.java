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
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    // Let's declare our variables, texts, and etc.
    String cityChoice, urlChoice, minChoice, maxchoice, searchChoice, searchTerm;
    int minInt, maxInt;
    TextView cityView;
    EditText minPriceEdit, maxPriceEdit, searchNowEdit;
    SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Now let's define and identify our views and texts.
        cityView = (TextView)findViewById(R.id.cityView);
        minPriceEdit = (EditText)findViewById(R.id.minPriceEdit);
        maxPriceEdit = (EditText)findViewById(R.id.maxPriceEdit);
        searchNowEdit = (EditText)findViewById(R.id.searchNowEdit);

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

        }

        // Now set all of our texts in accordance with our preferences.
        setAllTexts();

        // Floating action button to be a help button for the user.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
    }

    // Method to set the text views.
    public void setAllTexts () {

        cityView.setText(cityChoice);
        minPriceEdit.setText(minChoice);
        maxPriceEdit.setText(maxchoice);
        searchNowEdit.setText(searchChoice);

    }

    // Method for the user to pick a location.
    public void pickLocation (View pickView) {

        // And call our intent.
        Intent myIntent = new Intent(getApplicationContext(), PickCityActivity.class);
        // and start that activity expecting a result.
        startActivityForResult(myIntent, 1);

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
        }
    }

    public void searchNow (View searchView) {

        // Since we like to search for this, let's save that search for later.
        saveMyPrefs();

        // We should encode their search terms so we can get a workable URL.
        String whatSearch = URLEncoder.encode(searchChoice);

        //https://fairbanks.craigslist.org/search/sss?format=rss&query=chev*&min_price=0&max_price=999999999999
        searchTerm = urlChoice + "search/sss?format=rss&query="
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
}
