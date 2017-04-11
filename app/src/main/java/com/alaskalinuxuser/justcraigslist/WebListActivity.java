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
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebListActivity extends AppCompatActivity {

    String theSearchTerm, result, myResult;
    ListView searchListView;
    ArrayList<String> searchArrayList, searchURLList;
    ArrayAdapter<String> SearchAdapter;
    ImageView waitImage;
    Boolean notdone;

    // Set up my background asyncronous task....
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override // Do this in the background.
        protected String doInBackground(String... urls) {

            // Set my local variables.
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            // Try this because it may fail.
            try {

                // Use the first string passed as the URL.
                url = new URL(urls[0]);

                // Make a url connection.
                urlConnection = (HttpURLConnection)url.openConnection();

                // Start an input stream.
                InputStream in = urlConnection.getInputStream();

                // Start a reader.
                InputStreamReader reader = new InputStreamReader(in);

                // Set the data reader.
                int data = reader.read();

                // While there is still something to read, e.g., not the end.
                while (data != -1) {

                    // Set our current character.
                    char current = (char) data;

                    // Append our current char to the result.
                    result += current;

                    // Keep on reading.
                    data = reader.read();

                }

                // Let's copy the results so we can pass them on.
                myResult = result;

                // Now that we have the info, let's split it into useful chunks.
                String[] splitString = result.split("item rdf:about=");

                // Testing only // Log.i("WJH", String.valueOf(splitString.length));

                /* How many chunks of splits were there? */
                int totalMatches = splitString.length;

                // Clear the lists, so we don't get duplicates or items from the last search.
                searchArrayList.clear();
                searchURLList.clear();

                // Testing only // Log.i("WJH", String.valueOf(totalMatches));

        /* Okay, so for each chunk, minus the first one, which has no post,
         * we want to do this:
         */
                for (int k=1; k <= totalMatches; k++) {

                    // Now regex the data with this pattern.
                    Pattern pat = Pattern.compile("<link>(.*?)<");

                    // And search for matches in the results.
                    Matcher mat = pat.matcher(splitString[k]);

                    // This could fail, so wrap in try....
                    try {
                        // For every one you find, do this.
                        while (mat.find()) {

                            // The search results.
                            String foundurlString = (mat.group(1));

                            // Testing only // Log.i("WJH", foundurlString);

                            searchURLList.add(foundurlString);

                        } // And catch if it fails.
                    } catch (Exception e) {

                        Log.i("WJH", "No URL.");

                    }

                    // Now regex the data with this pattern.
                    Pattern patTwo = Pattern.compile("dc:title(.*?)dc:title");

                    // And search for matches in the results.
                    Matcher matTwo = patTwo.matcher(splitString[k]);

                    try {
                        // For every one you find, do this.
                        while (matTwo.find()) {

                            // The search results.
                            String foundNameString = (matTwo.group(1));

                            // Testing only // Log.i("WJH", foundNameString);

                            // To get the length, so we can cut off the last 5 characters, which
                            // look like garble.
                            int tempLength = foundNameString.length() - 5;

                            // But we don't want the first 10 characters of garble...
                            String subString = foundNameString.substring(10, tempLength);

                            // But the dollar sign is garble, so let's replace it.
                            String replacedPart = subString.replace("&#x0024;", "$");

                            // And add it to the list!
                            searchArrayList.add(replacedPart);

                        }
                    } catch (Exception e) {

                        Log.i("WJH", "No Name.");

                    }

            /* A method for getting the pictures, but due
             * to problems with the way the pictures are there,
             * I decided to table this portion for now.
             *
            // Now regex the data with this pattern.
            Pattern patThree = Pattern.compile("enclosure resource=\"(.*?)\"");

            // And search for matches in the results.
            Matcher matThree = patThree.matcher(splitString[k]);

            try {
                // For every one you find, do this.
                while (matThree.find()) {

                    // The search results.
                    String foundpicString = (matTwo.group(1));

                    Log.i("WJH", foundpicString);

                    searchPicList.add(foundpicString);

                }
            } catch (Exception e) {

                Log.i("WJH", "No picture.");

            } */
                }

                // Return what the results were.
                return result;

            } // And have a catch clause.
            catch(Exception e) {

                e.printStackTrace();

                return "Failed";

            }


        }

        @Override // When you are done downloading, do this:
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // Build the list!
            buildList();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Define our imageview...
        waitImage = (ImageView)findViewById(R.id.waitImage);

        // And set our boolean for not being done downloading yet.
        notdone = true;

        // Defining the list view that I want by id.
        searchListView = (ListView) findViewById(R.id.searchList);

        // Defining an array of titles and urls.
        searchArrayList = new ArrayList<String>();
        searchURLList = new ArrayList<String>();

        // Clear the lists, so we don't get duplicates or items from the last search.
        searchArrayList.clear();
        searchURLList.clear();

        // Now we get that extra information.
        Intent i = getIntent();
        theSearchTerm = i.getStringExtra("searchTerm");

        // Testing only //
        Log.i("WJH", theSearchTerm);


        // Defining an adapter, to adapt my array list to the correct format.
        SearchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searchArrayList){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                    /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.CYAN);

                return view;

            }
        };

        // Using the adapter to adapt my array list to the defined list view that I declared already.
        searchListView.setAdapter(SearchAdapter);

        // Setting up a listener to "listen" for me to click on something in the list.
        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Overriding the generic code that Android uses for list views to do something specific.
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int z, long l) {

                // Convert the int z into the strings we need.
                String a = (String) searchArrayList.get(z);
                String b =  (String) searchURLList.get(z);

                // Logging that I tapped said item in the list.
                // Logging only, not needed. // Log.i("tapped", a);

                // Call an intent to go to the weblist screen when you click the about button.
                // First you define it.
                Intent jcIntent = new Intent(WebListActivity.this, JCView.class);
                // Put our newly defined title and url in there.
                jcIntent.putExtra("titleIntent", a);
                jcIntent.putExtra("urlIntent", b);
                jcIntent.putExtra("resultAll", myResult);
                jcIntent.putExtra("searchTerm", theSearchTerm);
                // Now you call it.
                startActivity(jcIntent);


            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go back to the previous activity.
                finish();

            }
        });

        // Now let's get and build the list!
        getList();

    }

    public void getList() {

        // A little fun graphic to keep them busy....
        waitImage.animate().rotation(10800f).setDuration(30000);

        // Testing only // Log.i("WJH", "Building list.");

        // Start a new asyncronous/background download task....
        DownloadTask task = new DownloadTask();
        result = null;


        // Try this in case it fails.
        try {

            // Grab this result as a string.
            //result = task.execute(theSearchTerm).get();
            task.execute(theSearchTerm);

            // Testing only // Log.i("WJH", result);

            // And a catch when/if it does fail.
        } catch (Exception e) {

            // What if there is a problem with the download?
            Log.i("WJH", "There was an exception with the download.");

        }

        // Testing only // Log.i("WJH", searchArrayList.toString());
        // Testing only // Log.i("WJH", searchURLList.toString());

    }

    public void buildList() {

        notdone = false;

        searchListView.setVisibility(View.VISIBLE);
        SearchAdapter.notifyDataSetChanged();

    }
}
