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
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.alaskalinuxuser.justcraigslist.MainActivity.colorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.fabColorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.textColorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.backChoice;
import static com.alaskalinuxuser.justcraigslist.R.id.searchList;

public class PickCityActivity extends AppCompatActivity {

    // Declare my variables.
    ListView theList;
    ArrayList<String> cityList, urlList, searchCityList, searchCityUrl;
    ArrayAdapter<String> addaptedAray;
    EditText searchMyLocal;
    ImageView searchIv;
    Boolean searchYes;
    Drawable drawableMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout llPicCity = (LinearLayout) findViewById(R.id.LLPickCity);
        searchIv = (ImageView)findViewById(R.id.searchIV);
        searchMyLocal = (EditText)findViewById(R.id.searchForCity);


        // Set the Background color.
        switch (backChoice) {

            case 0:

                if(Build.VERSION.SDK_INT > 16) {

                    llPicCity.setBackground(getResources().getDrawable(R.drawable.jindong));

                } else {

                    llPicCity.setBackgroundDrawable(getResources().getDrawable(R.drawable.jindong));

                }

                break;

            case 1:

                if(Build.VERSION.SDK_INT > 16) {

                    llPicCity.setBackground(getResources().getDrawable(R.drawable.plymouth));

                } else {

                    llPicCity.setBackgroundDrawable(getResources().getDrawable(R.drawable.plymouth));

                }

                break;

            case 2:

                if(Build.VERSION.SDK_INT > 16) {

                    llPicCity.setBackground(getResources().getDrawable(R.drawable.chair));

                } else {

                    llPicCity.setBackgroundDrawable(getResources().getDrawable(R.drawable.chair));

                }

                break;

            case 3:

                if(Build.VERSION.SDK_INT > 16) {

                    llPicCity.setBackground(getResources().getDrawable(R.drawable.collie));

                } else {

                    llPicCity.setBackgroundDrawable(getResources().getDrawable(R.drawable.collie));

                }

                break;

            case 4:

                if(Build.VERSION.SDK_INT > 16) {

                    llPicCity.setBackground(getResources().getDrawable(R.drawable.flower));

                } else {

                    llPicCity.setBackgroundDrawable(getResources().getDrawable(R.drawable.flower));

                }

                break;

            case 5:

                llPicCity.setBackgroundColor(Color.GRAY);

                break;

            case 6:

                llPicCity.setBackgroundColor(Color.BLACK);

                break;

            case 7:

                llPicCity.setBackgroundColor(Color.WHITE);

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

        // Defining the list view that I want by id.
        theList = (ListView) findViewById(R.id.theList);

        // Defining an array of titles and urls.
        cityList = new ArrayList<String>();
        urlList = new ArrayList<String>();

        // To add the cities to the list.
        addCities();

        // Using the adapter to adapt my array list to the defined list view that I declared already.
        theList.setAdapter(addaptedAray);

        // Setting up a listener to "listen" for me to click on something in the list.
        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Overriding the generic code that Android uses for list views to do something specific.
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int z, long l) {

                // Convert the int z into the strings we need.
                String a = (String) cityList.get(z);
                String b =  (String) urlList.get(z);

                // Logging that I tapped said item in the list.
                // Logging only, not needed. // Log.i("tapped", a);

                // Send my intent with my variables.
                Intent returnIntent = getIntent();
                returnIntent.putExtra("cityIntent",a);
                returnIntent.putExtra("urlIntent",b);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }
        });

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
                Intent returnIntent = getIntent();
                setResult(Activity.RESULT_CANCELED,returnIntent);
                finish();
            }
        });



    } // On create end.


    // When we click the search button.
    public void searchACity (View searchView) {

        // Set our boolean to false.
        searchYes = false;

        // Defining an array of titles and urls.
        searchCityList = new ArrayList<String>();
        searchCityUrl = new ArrayList<String>();

        // Clear the lists, so we don't get duplicates.
        searchCityList.clear();
        searchCityUrl.clear();

        // Get our text and turn it into a string that is lowercase.
        String search = searchMyLocal.getText().toString().toLowerCase();

        // Testing only // Log.i("WJH", search);

        // Okay, now search the citylist for that string.
        for (int i = 0; i < cityList.size(); i++) {

            // If it contains the search string....
            if (cityList.get(i).contains(search)) {

                // Testing only // Log.i("WJH", cityList.get(i) + urlList.get(i));

                // Then add it to our new search lists.
                searchCityList.add(cityList.get(i));
                searchCityUrl.add(urlList.get(i));

                // And set our search boolean to true, because we found something.
                searchYes = true;

            }
        }

        // So, if we found something....
        if (searchYes) {

            // Clear the old lists.
            cityList.clear();
            urlList.clear();

            // For each item in our new search lists....
            for (int g = 0; g < searchCityList.size(); g++) {

                // Add them to our lists.
                cityList.add(searchCityList.get(g));
                urlList.add(searchCityUrl.get(g));

                // And notify of an update and update the list view.
                addaptedAray.notifyDataSetChanged();
                theList.invalidateViews();

            }

        } else { // But if nothing found....

            // The only way to see this, is if the search yielded no results.
            Toast.makeText(getApplicationContext(), "Nothing found that matches your search.", Toast.LENGTH_SHORT).show();

            // Reset the list
            addCities();

            // Update the list view.
            addaptedAray.notifyDataSetChanged();
            theList.invalidateViews();

        }

        // Get rid of the keyboard, so they can see the results...
        InputMethodManager imm = (InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

    } // searchACity end.

    public void addCities () {

        // Clear the lists, so we don't get duplicates.
        cityList.clear();
        urlList.clear();

        // and add the cities and urls.
        urlList.add("https://auburn.craigslist.org/"); cityList.add("auburn, alabama");
        urlList.add("https://bham.craigslist.org/"); cityList.add("birmingham, alabama");
        urlList.add("https://dothan.craigslist.org/"); cityList.add("dothan, alabama");
        urlList.add("https://shoals.craigslist.org/"); cityList.add("florence / muscle shoals, alabama");
        urlList.add("https://gadsden.craigslist.org/"); cityList.add("gadsden-anniston, alabama");
        urlList.add("https://huntsville.craigslist.org/"); cityList.add("huntsville / decatur, alabama");
        urlList.add("https://mobile.craigslist.org/"); cityList.add("mobile, alabama");
        urlList.add("https://montgomery.craigslist.org/"); cityList.add("montgomery, alabama");
        urlList.add("https://tuscaloosa.craigslist.org/"); cityList.add("tuscaloosa, alabama");
        urlList.add("https://anchorage.craigslist.org/"); cityList.add("anchorage / mat-su, alaska");
        urlList.add("https://fairbanks.craigslist.org/"); cityList.add("fairbanks, alaska");
        urlList.add("https://kenai.craigslist.org/"); cityList.add("kenai peninsula, alaska");
        urlList.add("https://juneau.craigslist.org/"); cityList.add("southeast alaska, alaska");
        urlList.add("https://flagstaff.craigslist.org/"); cityList.add("flagstaff / sedona, arizona");
        urlList.add("https://mohave.craigslist.org/"); cityList.add("mohave county, arizona");
        urlList.add("https://phoenix.craigslist.org/"); cityList.add("phoenix, arizona");
        urlList.add("https://prescott.craigslist.org/"); cityList.add("prescott, arizona");
        urlList.add("https://showlow.craigslist.org/"); cityList.add("show low, arizona");
        urlList.add("https://sierravista.craigslist.org/"); cityList.add("sierra vista, arizona");
        urlList.add("https://tucson.craigslist.org/"); cityList.add("tucson, arizona");
        urlList.add("https://yuma.craigslist.org/"); cityList.add("yuma, arizona");
        urlList.add("https://fayar.craigslist.org/"); cityList.add("fayetteville, arkansas");
        urlList.add("https://fortsmith.craigslist.org/"); cityList.add("fort smith, arkansas");
        urlList.add("https://jonesboro.craigslist.org/"); cityList.add("jonesboro, arkansas");
        urlList.add("https://littlerock.craigslist.org/"); cityList.add("little rock, arkansas");
        urlList.add("https://texarkana.craigslist.org/"); cityList.add("texarkana, arkansas");
        urlList.add("https://bakersfield.craigslist.org/"); cityList.add("bakersfield, california");
        urlList.add("https://chico.craigslist.org/"); cityList.add("chico, california");
        urlList.add("https://fresno.craigslist.org/"); cityList.add("fresno / madera, california");
        urlList.add("https://goldcountry.craigslist.org/"); cityList.add("gold country, california");
        urlList.add("https://hanford.craigslist.org/"); cityList.add("hanford-corcoran, california");
        urlList.add("https://humboldt.craigslist.org/"); cityList.add("humboldt county, california");
        urlList.add("https://imperial.craigslist.org/"); cityList.add("imperial county, california");
        urlList.add("https://inlandempire.craigslist.org/"); cityList.add("inland empire, california");
        urlList.add("https://losangeles.craigslist.org/"); cityList.add("los angeles, california");
        urlList.add("https://mendocino.craigslist.org/"); cityList.add("mendocino county, california");
        urlList.add("https://merced.craigslist.org/"); cityList.add("merced, california");
        urlList.add("https://modesto.craigslist.org/"); cityList.add("modesto, california");
        urlList.add("https://monterey.craigslist.org/"); cityList.add("monterey bay, california");
        urlList.add("https://orangecounty.craigslist.org/"); cityList.add("orange county, california");
        urlList.add("https://palmsprings.craigslist.org/"); cityList.add("palm springs, california");
        urlList.add("https://redding.craigslist.org/"); cityList.add("redding, california");
        urlList.add("https://sacramento.craigslist.org/"); cityList.add("sacramento, california");
        urlList.add("https://sandiego.craigslist.org/"); cityList.add("san diego, california");
        urlList.add("https://sfbay.craigslist.org/"); cityList.add("san francisco bay area, california");
        urlList.add("https://slo.craigslist.org/"); cityList.add("san luis obispo, california");
        urlList.add("https://santabarbara.craigslist.org/"); cityList.add("santa barbara, california");
        urlList.add("https://santamaria.craigslist.org/"); cityList.add("santa maria, california");
        urlList.add("https://siskiyou.craigslist.org/"); cityList.add("siskiyou county, california");
        urlList.add("https://stockton.craigslist.org/"); cityList.add("stockton, california");
        urlList.add("https://susanville.craigslist.org/"); cityList.add("susanville, california");
        urlList.add("https://ventura.craigslist.org/"); cityList.add("ventura county, california");
        urlList.add("https://visalia.craigslist.org/"); cityList.add("visalia-tulare, california");
        urlList.add("https://yubasutter.craigslist.org/"); cityList.add("yuba-sutter, california");
        urlList.add("https://boulder.craigslist.org/"); cityList.add("boulder, colorado");
        urlList.add("https://cosprings.craigslist.org/"); cityList.add("colorado springs, colorado");
        urlList.add("https://denver.craigslist.org/"); cityList.add("denver, colorado");
        urlList.add("https://eastco.craigslist.org/"); cityList.add("eastern co, colorado");
        urlList.add("https://fortcollins.craigslist.org/"); cityList.add("fort collins / north co, colorado");
        urlList.add("https://rockies.craigslist.org/"); cityList.add("high rockies, colorado");
        urlList.add("https://pueblo.craigslist.org/"); cityList.add("pueblo, colorado");
        urlList.add("https://westslope.craigslist.org/"); cityList.add("western slope, colorado");
        urlList.add("https://newlondon.craigslist.org/"); cityList.add("eastern ct, connecticut");
        urlList.add("https://hartford.craigslist.org/"); cityList.add("hartford, connecticut");
        urlList.add("https://newhaven.craigslist.org/"); cityList.add("new haven, connecticut");
        urlList.add("https://nwct.craigslist.org/"); cityList.add("northwest ct, connecticut");
        urlList.add("https://delaware.craigslist.org/"); cityList.add("delaware, delaware");
        urlList.add("https://washingtondc.craigslist.org/"); cityList.add("washington, district of columbia");
        urlList.add("http://miami.craigslist.org/brw/"); cityList.add("broward county, florida");
        urlList.add("https://daytona.craigslist.org/"); cityList.add("daytona beach, florida");
        urlList.add("https://keys.craigslist.org/"); cityList.add("florida keys, florida");
        urlList.add("https://fortlauderdale.craigslist.org/"); cityList.add("fort lauderdale, florida");
        urlList.add("https://fortmyers.craigslist.org/"); cityList.add("ft myers / sw florida, florida");
        urlList.add("https://gainesville.craigslist.org/"); cityList.add("gainesville, florida");
        urlList.add("https://cfl.craigslist.org/"); cityList.add("heartland florida, florida");
        urlList.add("https://jacksonville.craigslist.org/"); cityList.add("jacksonville, florida");
        urlList.add("https://lakeland.craigslist.org/"); cityList.add("lakeland, florida");
        urlList.add("http://miami.craigslist.org/mdc/"); cityList.add("miami / dade, florida");
        urlList.add("https://lakecity.craigslist.org/"); cityList.add("north central fl, florida");
        urlList.add("https://ocala.craigslist.org/"); cityList.add("ocala, florida");
        urlList.add("https://okaloosa.craigslist.org/"); cityList.add("okaloosa / walton, florida");
        urlList.add("https://orlando.craigslist.org/"); cityList.add("orlando, florida");
        urlList.add("https://panamacity.craigslist.org/"); cityList.add("panama city, florida");
        urlList.add("https://pensacola.craigslist.org/"); cityList.add("pensacola, florida");
        urlList.add("https://sarasota.craigslist.org/"); cityList.add("sarasota-bradenton, florida");
        urlList.add("https://miami.craigslist.org/"); cityList.add("south florida, florida");
        urlList.add("https://spacecoast.craigslist.org/"); cityList.add("space coast, florida");
        urlList.add("https://staugustine.craigslist.org/"); cityList.add("st augustine, florida");
        urlList.add("https://tallahassee.craigslist.org/"); cityList.add("tallahassee, florida");
        urlList.add("https://tampa.craigslist.org/"); cityList.add("tampa bay area, florida");
        urlList.add("https://treasure.craigslist.org/"); cityList.add("treasure coast, florida");
        urlList.add("http://miami.craigslist.org/pbc/"); cityList.add("palm beach county, florida");
        urlList.add("https://albanyga.craigslist.org/"); cityList.add("albany, georgia");
        urlList.add("https://athensga.craigslist.org/"); cityList.add("athens, georgia");
        urlList.add("https://atlanta.craigslist.org/"); cityList.add("atlanta, georgia");
        urlList.add("https://augusta.craigslist.org/"); cityList.add("augusta, georgia");
        urlList.add("https://brunswick.craigslist.org/"); cityList.add("brunswick, georgia");
        urlList.add("https://columbusga.craigslist.org/"); cityList.add("columbus, georgia");
        urlList.add("https://macon.craigslist.org/"); cityList.add("macon / warner robins, georgia");
        urlList.add("https://nwga.craigslist.org/"); cityList.add("northwest ga, georgia");
        urlList.add("https://savannah.craigslist.org/"); cityList.add("savannah / hinesville, georgia");
        urlList.add("https://statesboro.craigslist.org/"); cityList.add("statesboro, georgia");
        urlList.add("https://valdosta.craigslist.org/"); cityList.add("valdosta, georgia");
        urlList.add("https://honolulu.craigslist.org/"); cityList.add("hawaii, hawaii");
        urlList.add("https://boise.craigslist.org/"); cityList.add("boise, idaho");
        urlList.add("https://eastidaho.craigslist.org/"); cityList.add("east idaho, idaho");
        urlList.add("https://lewiston.craigslist.org/"); cityList.add("lewiston / clarkston, idaho");
        urlList.add("https://twinfalls.craigslist.org/"); cityList.add("twin falls, idaho");
        urlList.add("https://bn.craigslist.org/"); cityList.add("bloomington-normal, illinois");
        urlList.add("https://chambana.craigslist.org/"); cityList.add("champaign urbana, illinois");
        urlList.add("https://chicago.craigslist.org/"); cityList.add("chicago, illinois");
        urlList.add("https://decatur.craigslist.org/"); cityList.add("decatur, illinois");
        urlList.add("https://lasalle.craigslist.org/"); cityList.add("la salle co, illinois");
        urlList.add("https://mattoon.craigslist.org/"); cityList.add("mattoon-charleston, illinois");
        urlList.add("https://peoria.craigslist.org/"); cityList.add("peoria, illinois");
        urlList.add("https://rockford.craigslist.org/"); cityList.add("rockford, illinois");
        urlList.add("https://carbondale.craigslist.org/"); cityList.add("southern illinois, illinois");
        urlList.add("https://springfieldil.craigslist.org/"); cityList.add("springfield, illinois");
        urlList.add("https://quincy.craigslist.org/"); cityList.add("western il, illinois");
        urlList.add("https://bloomington.craigslist.org/"); cityList.add("bloomington, indiana");
        urlList.add("https://evansville.craigslist.org/"); cityList.add("evansville, indiana");
        urlList.add("https://fortwayne.craigslist.org/"); cityList.add("fort wayne, indiana");
        urlList.add("https://indianapolis.craigslist.org/"); cityList.add("indianapolis, indiana");
        urlList.add("https://kokomo.craigslist.org/"); cityList.add("kokomo, indiana");
        urlList.add("https://tippecanoe.craigslist.org/"); cityList.add("lafayette / west lafayette, indiana");
        urlList.add("https://muncie.craigslist.org/"); cityList.add("muncie / anderson, indiana");
        urlList.add("https://richmondin.craigslist.org/"); cityList.add("richmond , indiana");
        urlList.add("https://southbend.craigslist.org/"); cityList.add("south bend / michiana, indiana");
        urlList.add("https://terrehaute.craigslist.org/"); cityList.add("terre haute, indiana");
        urlList.add("https://ames.craigslist.org/"); cityList.add("ames, iowa");
        urlList.add("https://cedarrapids.craigslist.org/"); cityList.add("cedar rapids, iowa");
        urlList.add("https://desmoines.craigslist.org/"); cityList.add("des moines, iowa");
        urlList.add("https://dubuque.craigslist.org/"); cityList.add("dubuque, iowa");
        urlList.add("https://fortdodge.craigslist.org/"); cityList.add("fort dodge, iowa");
        urlList.add("https://iowacity.craigslist.org/"); cityList.add("iowa city, iowa");
        urlList.add("https://masoncity.craigslist.org/"); cityList.add("mason city, iowa");
        urlList.add("https://quadcities.craigslist.org/"); cityList.add("quad cities, iowa");
        urlList.add("https://siouxcity.craigslist.org/"); cityList.add("sioux city, iowa");
        urlList.add("https://ottumwa.craigslist.org/"); cityList.add("southeast ia, iowa");
        urlList.add("https://waterloo.craigslist.org/"); cityList.add("waterloo / cedar falls, iowa");
        urlList.add("https://lawrence.craigslist.org/"); cityList.add("lawrence, kansas");
        urlList.add("https://ksu.craigslist.org/"); cityList.add("manhattan, kansas");
        urlList.add("https://nwks.craigslist.org/"); cityList.add("northwest ks, kansas");
        urlList.add("https://salina.craigslist.org/"); cityList.add("salina, kansas");
        urlList.add("https://seks.craigslist.org/"); cityList.add("southeast ks, kansas");
        urlList.add("https://swks.craigslist.org/"); cityList.add("southwest ks, kansas");
        urlList.add("https://topeka.craigslist.org/"); cityList.add("topeka, kansas");
        urlList.add("https://wichita.craigslist.org/"); cityList.add("wichita, kansas");
        urlList.add("https://bgky.craigslist.org/"); cityList.add("bowling green, kentucky");
        urlList.add("https://eastky.craigslist.org/"); cityList.add("eastern kentucky, kentucky");
        urlList.add("https://lexington.craigslist.org/"); cityList.add("lexington, kentucky");
        urlList.add("https://louisville.craigslist.org/"); cityList.add("louisville, kentucky");
        urlList.add("https://owensboro.craigslist.org/"); cityList.add("owensboro, kentucky");
        urlList.add("https://westky.craigslist.org/"); cityList.add("western ky, kentucky");
        urlList.add("https://batonrouge.craigslist.org/"); cityList.add("baton rouge, louisiana");
        urlList.add("https://cenla.craigslist.org/"); cityList.add("central louisiana, louisiana");
        urlList.add("https://houma.craigslist.org/"); cityList.add("houma, louisiana");
        urlList.add("https://lafayette.craigslist.org/"); cityList.add("lafayette, louisiana");
        urlList.add("https://lakecharles.craigslist.org/"); cityList.add("lake charles, louisiana");
        urlList.add("https://monroe.craigslist.org/"); cityList.add("monroe, louisiana");
        urlList.add("https://neworleans.craigslist.org/"); cityList.add("new orleans, louisiana");
        urlList.add("https://shreveport.craigslist.org/"); cityList.add("shreveport, louisiana");
        urlList.add("https://maine.craigslist.org/"); cityList.add("maine, maine");
        urlList.add("https://annapolis.craigslist.org/"); cityList.add("annapolis, maryland");
        urlList.add("https://baltimore.craigslist.org/"); cityList.add("baltimore, maryland");
        urlList.add("https://easternshore.craigslist.org/"); cityList.add("eastern shore, maryland");
        urlList.add("https://frederick.craigslist.org/"); cityList.add("frederick, maryland");
        urlList.add("https://smd.craigslist.org/"); cityList.add("southern maryland, maryland");
        urlList.add("https://westmd.craigslist.org/"); cityList.add("western maryland, maryland");
        urlList.add("https://boston.craigslist.org/"); cityList.add("boston, massachusetts");
        urlList.add("https://capecod.craigslist.org/"); cityList.add("cape cod / islands, massachusetts");
        urlList.add("https://southcoast.craigslist.org/"); cityList.add("south coast, massachusetts");
        urlList.add("https://westernmass.craigslist.org/"); cityList.add("western massachusetts, massachusetts");
        urlList.add("https://worcester.craigslist.org/"); cityList.add("worcester / central ma, massachusetts");
        urlList.add("https://annarbor.craigslist.org/"); cityList.add("ann arbor, michigan");
        urlList.add("https://battlecreek.craigslist.org/"); cityList.add("battle creek, michigan");
        urlList.add("https://centralmich.craigslist.org/"); cityList.add("central michigan, michigan");
        urlList.add("https://detroit.craigslist.org/"); cityList.add("detroit metro, michigan");
        urlList.add("https://flint.craigslist.org/"); cityList.add("flint, michigan");
        urlList.add("https://grandrapids.craigslist.org/"); cityList.add("grand rapids, michigan");
        urlList.add("https://holland.craigslist.org/"); cityList.add("holland, michigan");
        urlList.add("https://jxn.craigslist.org/"); cityList.add("jackson, michigan");
        urlList.add("https://kalamazoo.craigslist.org/"); cityList.add("kalamazoo, michigan");
        urlList.add("https://lansing.craigslist.org/"); cityList.add("lansing, michigan");
        urlList.add("https://monroemi.craigslist.org/"); cityList.add("monroe, michigan");
        urlList.add("https://muskegon.craigslist.org/"); cityList.add("muskegon, michigan");
        urlList.add("https://nmi.craigslist.org/"); cityList.add("northern michigan, michigan");
        urlList.add("https://porthuron.craigslist.org/"); cityList.add("port huron, michigan");
        urlList.add("https://saginaw.craigslist.org/"); cityList.add("saginaw-midland-baycity, michigan");
        urlList.add("https://swmi.craigslist.org/"); cityList.add("southwest michigan, michigan");
        urlList.add("https://thumb.craigslist.org/"); cityList.add("the thumb, michigan");
        urlList.add("https://up.craigslist.org/"); cityList.add("upper peninsula, michigan");
        urlList.add("https://bemidji.craigslist.org/"); cityList.add("bemidji, minnesota");
        urlList.add("https://brainerd.craigslist.org/"); cityList.add("brainerd, minnesota");
        urlList.add("https://duluth.craigslist.org/"); cityList.add("duluth / superior, minnesota");
        urlList.add("https://mankato.craigslist.org/"); cityList.add("mankato, minnesota");
        urlList.add("https://minneapolis.craigslist.org/"); cityList.add("minneapolis / st paul, minnesota");
        urlList.add("https://rmn.craigslist.org/"); cityList.add("rochester, minnesota");
        urlList.add("https://marshall.craigslist.org/"); cityList.add("southwest mn, minnesota");
        urlList.add("https://stcloud.craigslist.org/"); cityList.add("st cloud, minnesota");
        urlList.add("https://gulfport.craigslist.org/"); cityList.add("gulfport / biloxi, mississippi");
        urlList.add("https://hattiesburg.craigslist.org/"); cityList.add("hattiesburg, mississippi");
        urlList.add("https://jackson.craigslist.org/"); cityList.add("jackson, mississippi");
        urlList.add("https://meridian.craigslist.org/"); cityList.add("meridian, mississippi");
        urlList.add("https://northmiss.craigslist.org/"); cityList.add("north mississippi, mississippi");
        urlList.add("https://natchez.craigslist.org/"); cityList.add("southwest ms, mississippi");
        urlList.add("https://columbiamo.craigslist.org/"); cityList.add("columbia / jeff city, missouri");
        urlList.add("https://joplin.craigslist.org/"); cityList.add("joplin, missouri");
        urlList.add("https://kansascity.craigslist.org/"); cityList.add("kansas city, missouri");
        urlList.add("https://kirksville.craigslist.org/"); cityList.add("kirksville, missouri");
        urlList.add("https://loz.craigslist.org/"); cityList.add("lake of the ozarks, missouri");
        urlList.add("https://semo.craigslist.org/"); cityList.add("southeast missouri, missouri");
        urlList.add("https://springfield.craigslist.org/"); cityList.add("springfield, missouri");
        urlList.add("https://stjoseph.craigslist.org/"); cityList.add("st joseph, missouri");
        urlList.add("https://stlouis.craigslist.org/"); cityList.add("st louis, missouri");
        urlList.add("https://billings.craigslist.org/"); cityList.add("billings, montana");
        urlList.add("https://bozeman.craigslist.org/"); cityList.add("bozeman, montana");
        urlList.add("https://butte.craigslist.org/"); cityList.add("butte, montana");
        urlList.add("https://greatfalls.craigslist.org/"); cityList.add("great falls, montana");
        urlList.add("https://helena.craigslist.org/"); cityList.add("helena, montana");
        urlList.add("https://kalispell.craigslist.org/"); cityList.add("kalispell, montana");
        urlList.add("https://missoula.craigslist.org/"); cityList.add("missoula, montana");
        urlList.add("https://montana.craigslist.org/"); cityList.add("eastern montana, montana");
        urlList.add("https://grandisland.craigslist.org/"); cityList.add("grand island, nebraska");
        urlList.add("https://lincoln.craigslist.org/"); cityList.add("lincoln, nebraska");
        urlList.add("https://northplatte.craigslist.org/"); cityList.add("north platte, nebraska");
        urlList.add("https://omaha.craigslist.org/"); cityList.add("omaha / council bluffs, nebraska");
        urlList.add("https://scottsbluff.craigslist.org/"); cityList.add("scottsbluff / panhandle, nebraska");
        urlList.add("https://elko.craigslist.org/"); cityList.add("elko, nevada");
        urlList.add("https://lasvegas.craigslist.org/"); cityList.add("las vegas, nevada");
        urlList.add("https://reno.craigslist.org/"); cityList.add("reno / tahoe, nevada");
        urlList.add("https://nh.craigslist.org/"); cityList.add("new hampshire, new hampshire");
        urlList.add("https://cnj.craigslist.org/"); cityList.add("central nj, new jersey");
        urlList.add("https://jerseyshore.craigslist.org/"); cityList.add("jersey shore, new jersey");
        urlList.add("https://newjersey.craigslist.org/"); cityList.add("north jersey, new jersey");
        urlList.add("https://southjersey.craigslist.org/"); cityList.add("south jersey, new jersey");
        urlList.add("https://albuquerque.craigslist.org/"); cityList.add("albuquerque, new mexico");
        urlList.add("https://clovis.craigslist.org/"); cityList.add("clovis / portales, new mexico");
        urlList.add("https://farmington.craigslist.org/"); cityList.add("farmington, new mexico");
        urlList.add("https://lascruces.craigslist.org/"); cityList.add("las cruces, new mexico");
        urlList.add("https://roswell.craigslist.org/"); cityList.add("roswell / carlsbad, new mexico");
        urlList.add("https://santafe.craigslist.org/"); cityList.add("santa fe / taos, new mexico");
        urlList.add("https://albany.craigslist.org/"); cityList.add("albany, new york");
        urlList.add("https://binghamton.craigslist.org/"); cityList.add("binghamton, new york");
        urlList.add("https://buffalo.craigslist.org/"); cityList.add("buffalo, new york");
        urlList.add("https://catskills.craigslist.org/"); cityList.add("catskills, new york");
        urlList.add("https://chautauqua.craigslist.org/"); cityList.add("chautauqua, new york");
        urlList.add("https://elmira.craigslist.org/"); cityList.add("elmira-corning, new york");
        urlList.add("https://fingerlakes.craigslist.org/"); cityList.add("finger lakes, new york");
        urlList.add("https://glensfalls.craigslist.org/"); cityList.add("glens falls, new york");
        urlList.add("https://hudsonvalley.craigslist.org/"); cityList.add("hudson valley, new york");
        urlList.add("https://ithaca.craigslist.org/"); cityList.add("ithaca, new york");
        urlList.add("https://longisland.craigslist.org/"); cityList.add("long island, new york");
        urlList.add("https://newyork.craigslist.org/"); cityList.add("new york city, new york");
        urlList.add("https://oneonta.craigslist.org/"); cityList.add("oneonta, new york");
        urlList.add("https://plattsburgh.craigslist.org/"); cityList.add("plattsburgh-adirondacks, new york");
        urlList.add("https://potsdam.craigslist.org/"); cityList.add("potsdam-canton-massena, new york");
        urlList.add("https://rochester.craigslist.org/"); cityList.add("rochester, new york");
        urlList.add("https://syracuse.craigslist.org/"); cityList.add("syracuse, new york");
        urlList.add("https://twintiers.craigslist.org/"); cityList.add("twin tiers ny/pa, new york");
        urlList.add("https://utica.craigslist.org/"); cityList.add("utica-rome-oneida, new york");
        urlList.add("https://watertown.craigslist.org/"); cityList.add("watertown, new york");
        urlList.add("https://asheville.craigslist.org/"); cityList.add("asheville, north carolina");
        urlList.add("https://boone.craigslist.org/"); cityList.add("boone, north carolina");
        urlList.add("https://charlotte.craigslist.org/"); cityList.add("charlotte, north carolina");
        urlList.add("https://eastnc.craigslist.org/"); cityList.add("eastern nc, north carolina");
        urlList.add("https://fayetteville.craigslist.org/"); cityList.add("fayetteville, north carolina");
        urlList.add("https://greensboro.craigslist.org/"); cityList.add("greensboro, north carolina");
        urlList.add("https://hickory.craigslist.org/"); cityList.add("hickory / lenoir, north carolina");
        urlList.add("https://onslow.craigslist.org/"); cityList.add("jacksonville, north carolina");
        urlList.add("https://outerbanks.craigslist.org/"); cityList.add("outer banks, north carolina");
        urlList.add("https://raleigh.craigslist.org/"); cityList.add("raleigh / durham / ch, north carolina");
        urlList.add("https://wilmington.craigslist.org/"); cityList.add("wilmington, north carolina");
        urlList.add("https://winstonsalem.craigslist.org/"); cityList.add("winston-salem, north carolina");
        urlList.add("https://bismarck.craigslist.org/"); cityList.add("bismarck, north dakota");
        urlList.add("https://fargo.craigslist.org/"); cityList.add("fargo / moorhead, north dakota");
        urlList.add("https://grandforks.craigslist.org/"); cityList.add("grand forks, north dakota");
        urlList.add("https://nd.craigslist.org/"); cityList.add("north dakota, north dakota");
        urlList.add("https://akroncanton.craigslist.org/"); cityList.add("akron / canton, ohio");
        urlList.add("https://ashtabula.craigslist.org/"); cityList.add("ashtabula, ohio");
        urlList.add("https://athensohio.craigslist.org/"); cityList.add("athens, ohio");
        urlList.add("https://chillicothe.craigslist.org/"); cityList.add("chillicothe, ohio");
        urlList.add("https://cincinnati.craigslist.org/"); cityList.add("cincinnati, ohio");
        urlList.add("https://cleveland.craigslist.org/"); cityList.add("cleveland, ohio");
        urlList.add("https://columbus.craigslist.org/"); cityList.add("columbus, ohio");
        urlList.add("https://dayton.craigslist.org/"); cityList.add("dayton / springfield, ohio");
        urlList.add("https://limaohio.craigslist.org/"); cityList.add("lima / findlay, ohio");
        urlList.add("https://mansfield.craigslist.org/"); cityList.add("mansfield, ohio");
        urlList.add("https://sandusky.craigslist.org/"); cityList.add("sandusky, ohio");
        urlList.add("https://toledo.craigslist.org/"); cityList.add("toledo, ohio");
        urlList.add("https://tuscarawas.craigslist.org/"); cityList.add("tuscarawas co, ohio");
        urlList.add("https://youngstown.craigslist.org/"); cityList.add("youngstown, ohio");
        urlList.add("https://zanesville.craigslist.org/"); cityList.add("zanesville / cambridge, ohio");
        urlList.add("https://lawton.craigslist.org/"); cityList.add("lawton, oklahoma");
        urlList.add("https://enid.craigslist.org/"); cityList.add("northwest ok, oklahoma");
        urlList.add("https://oklahomacity.craigslist.org/"); cityList.add("oklahoma city, oklahoma");
        urlList.add("https://stillwater.craigslist.org/"); cityList.add("stillwater, oklahoma");
        urlList.add("https://tulsa.craigslist.org/"); cityList.add("tulsa, oklahoma");
        urlList.add("https://bend.craigslist.org/"); cityList.add("bend, oregon");
        urlList.add("https://corvallis.craigslist.org/"); cityList.add("corvallis/albany, oregon");
        urlList.add("https://eastoregon.craigslist.org/"); cityList.add("east oregon, oregon");
        urlList.add("https://eugene.craigslist.org/"); cityList.add("eugene, oregon");
        urlList.add("https://klamath.craigslist.org/"); cityList.add("klamath falls, oregon");
        urlList.add("https://medford.craigslist.org/"); cityList.add("medford-ashland, oregon");
        urlList.add("https://oregoncoast.craigslist.org/"); cityList.add("oregon coast, oregon");
        urlList.add("https://portland.craigslist.org/"); cityList.add("portland, oregon");
        urlList.add("https://roseburg.craigslist.org/"); cityList.add("roseburg, oregon");
        urlList.add("https://salem.craigslist.org/"); cityList.add("salem, oregon");
        urlList.add("https://altoona.craigslist.org/"); cityList.add("altoona-johnstown, pennsylvania");
        urlList.add("https://chambersburg.craigslist.org/"); cityList.add("cumberland valley, pennsylvania");
        urlList.add("https://erie.craigslist.org/"); cityList.add("erie, pennsylvania");
        urlList.add("https://harrisburg.craigslist.org/"); cityList.add("harrisburg, pennsylvania");
        urlList.add("https://lancaster.craigslist.org/"); cityList.add("lancaster, pennsylvania");
        urlList.add("https://allentown.craigslist.org/"); cityList.add("lehigh valley, pennsylvania");
        urlList.add("https://meadville.craigslist.org/"); cityList.add("meadville, pennsylvania");
        urlList.add("https://philadelphia.craigslist.org/"); cityList.add("philadelphia, pennsylvania");
        urlList.add("https://pittsburgh.craigslist.org/"); cityList.add("pittsburgh, pennsylvania");
        urlList.add("https://poconos.craigslist.org/"); cityList.add("poconos, pennsylvania");
        urlList.add("https://reading.craigslist.org/"); cityList.add("reading, pennsylvania");
        urlList.add("https://scranton.craigslist.org/"); cityList.add("scranton / wilkes-barre, pennsylvania");
        urlList.add("https://pennstate.craigslist.org/"); cityList.add("state college, pennsylvania");
        urlList.add("https://williamsport.craigslist.org/"); cityList.add("williamsport, pennsylvania");
        urlList.add("https://york.craigslist.org/"); cityList.add("york, pennsylvania");
        urlList.add("https://providence.craigslist.org/"); cityList.add("rhode island, rhode island");
        urlList.add("https://charleston.craigslist.org/"); cityList.add("charleston, south carolina");
        urlList.add("https://columbia.craigslist.org/"); cityList.add("columbia, south carolina");
        urlList.add("https://florencesc.craigslist.org/"); cityList.add("florence, south carolina");
        urlList.add("https://greenville.craigslist.org/"); cityList.add("greenville / upstate, south carolina");
        urlList.add("https://hiltonhead.craigslist.org/"); cityList.add("hilton head, south carolina");
        urlList.add("https://myrtlebeach.craigslist.org/"); cityList.add("myrtle beach, south carolina");
        urlList.add("https://nesd.craigslist.org/"); cityList.add("northeast sd, south dakota");
        urlList.add("https://csd.craigslist.org/"); cityList.add("pierre / central sd, south dakota");
        urlList.add("https://rapidcity.craigslist.org/"); cityList.add("rapid city / west sd, south dakota");
        urlList.add("https://siouxfalls.craigslist.org/"); cityList.add("sioux falls / se sd, south dakota");
        urlList.add("https://sd.craigslist.org/"); cityList.add("south dakota");
        urlList.add("https://chattanooga.craigslist.org/"); cityList.add("chattanooga, tennessee");
        urlList.add("https://clarksville.craigslist.org/"); cityList.add("clarksville, tennessee");
        urlList.add("https://cookeville.craigslist.org/"); cityList.add("cookeville, tennessee");
        urlList.add("https://jacksontn.craigslist.org/"); cityList.add("jackson, tennessee");
        urlList.add("https://knoxville.craigslist.org/"); cityList.add("knoxville, tennessee");
        urlList.add("https://memphis.craigslist.org/"); cityList.add("memphis, tennessee");
        urlList.add("https://nashville.craigslist.org/"); cityList.add("nashville, tennessee");
        urlList.add("https://tricities.craigslist.org/"); cityList.add("tri-cities, tennessee");
        urlList.add("https://abilene.craigslist.org/"); cityList.add("abilene, texas");
        urlList.add("https://amarillo.craigslist.org/"); cityList.add("amarillo, texas");
        urlList.add("https://austin.craigslist.org/"); cityList.add("austin, texas");
        urlList.add("https://beaumont.craigslist.org/"); cityList.add("beaumont / port arthur, texas");
        urlList.add("https://brownsville.craigslist.org/"); cityList.add("brownsville, texas");
        urlList.add("https://collegestation.craigslist.org/"); cityList.add("college station, texas");
        urlList.add("https://corpuschristi.craigslist.org/"); cityList.add("corpus christi, texas");
        urlList.add("https://dallas.craigslist.org/"); cityList.add("dallas / fort worth, texas");
        urlList.add("https://nacogdoches.craigslist.org/"); cityList.add("deep east texas, texas");
        urlList.add("https://delrio.craigslist.org/"); cityList.add("del rio / eagle pass, texas");
        urlList.add("https://elpaso.craigslist.org/"); cityList.add("el paso, texas");
        urlList.add("https://galveston.craigslist.org/"); cityList.add("galveston, texas");
        urlList.add("https://houston.craigslist.org/"); cityList.add("houston, texas");
        urlList.add("https://killeen.craigslist.org/"); cityList.add("killeen / temple / ft hood, texas");
        urlList.add("https://laredo.craigslist.org/"); cityList.add("laredo, texas");
        urlList.add("https://lubbock.craigslist.org/"); cityList.add("lubbock, texas");
        urlList.add("https://mcallen.craigslist.org/"); cityList.add("mcallen / edinburg, texas");
        urlList.add("https://odessa.craigslist.org/"); cityList.add("odessa / midland, texas");
        urlList.add("https://sanangelo.craigslist.org/"); cityList.add("san angelo, texas");
        urlList.add("https://sanantonio.craigslist.org/"); cityList.add("san antonio, texas");
        urlList.add("https://sanmarcos.craigslist.org/"); cityList.add("san marcos, texas");
        urlList.add("https://bigbend.craigslist.org/"); cityList.add("southwest tx, texas");
        urlList.add("https://texoma.craigslist.org/"); cityList.add("texoma, texas");
        urlList.add("https://easttexas.craigslist.org/"); cityList.add("tyler / east tx, texas");
        urlList.add("https://victoriatx.craigslist.org/"); cityList.add("victoria, texas");
        urlList.add("https://waco.craigslist.org/"); cityList.add("waco, texas");
        urlList.add("https://wichitafalls.craigslist.org/"); cityList.add("wichita falls, texas");
        urlList.add("https://logan.craigslist.org/"); cityList.add("logan, utah");
        urlList.add("https://ogden.craigslist.org/"); cityList.add("ogden-clearfield, utah");
        urlList.add("https://provo.craigslist.org/"); cityList.add("provo / orem, utah");
        urlList.add("https://saltlakecity.craigslist.org/"); cityList.add("salt lake city, utah");
        urlList.add("https://stgeorge.craigslist.org/"); cityList.add("st george, utah");
        urlList.add("https://vermont.craigslist.org/"); cityList.add("vermont, vermont");
        urlList.add("https://charlottesville.craigslist.org/"); cityList.add("charlottesville, virginia");
        urlList.add("https://danville.craigslist.org/"); cityList.add("danville, virginia");
        urlList.add("https://fredericksburg.craigslist.org/"); cityList.add("fredericksburg, virginia");
        urlList.add("https://norfolk.craigslist.org/"); cityList.add("hampton roads, virginia");
        urlList.add("https://harrisonburg.craigslist.org/"); cityList.add("harrisonburg, virginia");
        urlList.add("https://lynchburg.craigslist.org/"); cityList.add("lynchburg, virginia");
        urlList.add("https://blacksburg.craigslist.org/"); cityList.add("new river valley, virginia");
        urlList.add("https://richmond.craigslist.org/"); cityList.add("richmond, virginia");
        urlList.add("https://roanoke.craigslist.org/"); cityList.add("roanoke, virginia");
        urlList.add("https://swva.craigslist.org/"); cityList.add("southwest va, virginia");
        urlList.add("https://winchester.craigslist.org/"); cityList.add("winchester, virginia");
        urlList.add("https://bellingham.craigslist.org/"); cityList.add("bellingham, washington");
        urlList.add("https://kpr.craigslist.org/"); cityList.add("kennewick-pasco-richland, washington");
        urlList.add("https://moseslake.craigslist.org/"); cityList.add("moses lake, washington");
        urlList.add("https://olympic.craigslist.org/"); cityList.add("olympic peninsula, washington");
        urlList.add("https://pullman.craigslist.org/"); cityList.add("pullman / moscow, washington");
        urlList.add("https://seattle.craigslist.org/"); cityList.add("seattle-tacoma, washington");
        urlList.add("https://skagit.craigslist.org/"); cityList.add("skagit / island / sji, washington");
        urlList.add("https://spokane.craigslist.org/"); cityList.add("spokane / coeur d'alene, washington");
        urlList.add("https://wenatchee.craigslist.org/"); cityList.add("wenatchee, washington");
        urlList.add("https://yakima.craigslist.org/"); cityList.add("yakima, washington");
        urlList.add("https://charlestonwv.craigslist.org/"); cityList.add("charleston, west virginia");
        urlList.add("https://martinsburg.craigslist.org/"); cityList.add("eastern panhandle, west virginia");
        urlList.add("https://huntington.craigslist.org/"); cityList.add("huntington-ashland, west virginia");
        urlList.add("https://morgantown.craigslist.org/"); cityList.add("morgantown, west virginia");
        urlList.add("https://wheeling.craigslist.org/"); cityList.add("northern panhandle, west virginia");
        urlList.add("https://parkersburg.craigslist.org/"); cityList.add("parkersburg-marietta, west virginia");
        urlList.add("https://swv.craigslist.org/"); cityList.add("southern wv, west virginia");
        urlList.add("https://wv.craigslist.org/"); cityList.add("west virginia (old), west virginia");
        urlList.add("https://appleton.craigslist.org/"); cityList.add("appleton-oshkosh-fdl, wisconsin");
        urlList.add("https://eauclaire.craigslist.org/"); cityList.add("eau claire, wisconsin");
        urlList.add("https://greenbay.craigslist.org/"); cityList.add("green bay, wisconsin");
        urlList.add("https://janesville.craigslist.org/"); cityList.add("janesville, wisconsin");
        urlList.add("https://racine.craigslist.org/"); cityList.add("kenosha-racine, wisconsin");
        urlList.add("https://lacrosse.craigslist.org/"); cityList.add("la crosse, wisconsin");
        urlList.add("https://madison.craigslist.org/"); cityList.add("madison, wisconsin");
        urlList.add("https://milwaukee.craigslist.org/"); cityList.add("milwaukee, wisconsin");
        urlList.add("https://northernwi.craigslist.org/"); cityList.add("northern wi, wisconsin");
        urlList.add("https://sheboygan.craigslist.org/"); cityList.add("sheboygan, wisconsin");
        urlList.add("https://wausau.craigslist.org/"); cityList.add("wausau, wisconsin");
        urlList.add("https://wyoming.craigslist.org/"); cityList.add("wyoming, wyoming");
        urlList.add("https://micronesia.craigslist.org/"); cityList.add("guam-micronesia, territories");
        urlList.add("https://puertorico.craigslist.org/"); cityList.add("puerto rico, territories");
        urlList.add("https://virgin.craigslist.org/"); cityList.add("u.s. virgin islands, territories");
        urlList.add("https://calgary.craigslist.ca/"); cityList.add("calgary, alberta");
        urlList.add("https://edmonton.craigslist.ca/"); cityList.add("edmonton, alberta");
        urlList.add("https://ftmcmurray.craigslist.ca/"); cityList.add("ft mcmurray, alberta");
        urlList.add("https://lethbridge.craigslist.ca/"); cityList.add("lethbridge, alberta");
        urlList.add("https://hat.craigslist.ca/"); cityList.add("medicine hat, alberta");
        urlList.add("https://peace.craigslist.ca/"); cityList.add("peace river country, alberta");
        urlList.add("https://reddeer.craigslist.ca/"); cityList.add("red deer, alberta");
        urlList.add("https://cariboo.craigslist.ca/"); cityList.add("cariboo, british columbia");
        urlList.add("https://comoxvalley.craigslist.ca/"); cityList.add("comox valley, british columbia");
        urlList.add("https://abbotsford.craigslist.ca/"); cityList.add("fraser valley, british columbia");
        urlList.add("https://kamloops.craigslist.ca/"); cityList.add("kamloops, british columbia");
        urlList.add("https://kelowna.craigslist.ca/"); cityList.add("kelowna / okanagan, british columbia");
        urlList.add("https://kootenays.craigslist.ca/"); cityList.add("kootenays, british columbia");
        urlList.add("https://nanaimo.craigslist.ca/"); cityList.add("nanaimo, british columbia");
        urlList.add("https://princegeorge.craigslist.ca/"); cityList.add("prince george, british columbia");
        urlList.add("https://skeena.craigslist.ca/"); cityList.add("skeena-bulkley, british columbia");
        urlList.add("https://sunshine.craigslist.ca/"); cityList.add("sunshine coast, british columbia");
        urlList.add("https://vancouver.craigslist.ca/"); cityList.add("vancouver, british columbia");
        urlList.add("https://victoria.craigslist.ca/"); cityList.add("victoria, british columbia");
        urlList.add("https://whistler.craigslist.ca/"); cityList.add("whistler, british columbia");
        urlList.add("https://winnipeg.craigslist.ca/"); cityList.add("winnipeg, manitoba");
        urlList.add("https://newbrunswick.craigslist.ca/"); cityList.add("new brunswick, new brunswick");
        urlList.add("https://newfoundland.craigslist.ca/"); cityList.add("st john's, newfoundland and labrador");
        urlList.add("https://territories.craigslist.ca/"); cityList.add("territories, northwest territories");
        urlList.add("https://yellowknife.craigslist.ca/"); cityList.add("yellowknife, northwest territories");
        urlList.add("https://halifax.craigslist.ca/"); cityList.add("halifax, nova scotia");
        urlList.add("https://barrie.craigslist.ca/"); cityList.add("barrie, ontario");
        urlList.add("https://belleville.craigslist.ca/"); cityList.add("belleville, ontario");
        urlList.add("https://brantford.craigslist.ca/"); cityList.add("brantford-woodstock, ontario");
        urlList.add("https://chatham.craigslist.ca/"); cityList.add("chatham-kent, ontario");
        urlList.add("https://cornwall.craigslist.ca/"); cityList.add("cornwall, ontario");
        urlList.add("https://guelph.craigslist.ca/"); cityList.add("guelph, ontario");
        urlList.add("https://hamilton.craigslist.ca/"); cityList.add("hamilton-burlington, ontario");
        urlList.add("https://kingston.craigslist.ca/"); cityList.add("kingston, ontario");
        urlList.add("https://kitchener.craigslist.ca/"); cityList.add("kitchener-waterloo-cambridge, ontario");
        urlList.add("https://londonon.craigslist.ca/"); cityList.add("london, ontario");
        urlList.add("https://niagara.craigslist.ca/"); cityList.add("niagara region, ontario");
        urlList.add("https://ottawa.craigslist.ca/"); cityList.add("ottawa-hull-gatineau, ontario");
        urlList.add("https://owensound.craigslist.ca/"); cityList.add("owen sound, ontario");
        urlList.add("https://peterborough.craigslist.ca/"); cityList.add("peterborough, ontario");
        urlList.add("https://sarnia.craigslist.ca/"); cityList.add("sarnia, ontario");
        urlList.add("https://soo.craigslist.ca/"); cityList.add("sault ste marie, ontario");
        urlList.add("https://sudbury.craigslist.ca/"); cityList.add("sudbury, ontario");
        urlList.add("https://thunderbay.craigslist.ca/"); cityList.add("thunder bay, ontario");
        urlList.add("https://toronto.craigslist.ca/"); cityList.add("toronto, ontario");
        urlList.add("https://windsor.craigslist.ca/"); cityList.add("windsor, ontario");
        urlList.add("https://pei.craigslist.ca/"); cityList.add("prince edward island, prince edward island");
        urlList.add("https://montreal.craigslist.ca/"); cityList.add("montreal, quebec");
        urlList.add("https://quebec.craigslist.ca/"); cityList.add("quebec city, quebec");
        urlList.add("https://saguenay.craigslist.ca/"); cityList.add("saguenay, quebec");
        urlList.add("https://sherbrooke.craigslist.ca/"); cityList.add("sherbrooke, quebec");
        urlList.add("https://troisrivieres.craigslist.ca/"); cityList.add("trois-rivieres, quebec");
        urlList.add("https://regina.craigslist.ca/"); cityList.add("regina, saskatchewan");
        urlList.add("https://saskatoon.craigslist.ca/"); cityList.add("saskatoon, saskatchewan");
        urlList.add("https://whitehorse.craigslist.ca/"); cityList.add("whitehorse, yukon territory");
        urlList.add("https://vienna.craigslist.at/"); cityList.add("vienna, austria");
        urlList.add("https://brussels.craigslist.org/"); cityList.add("belgium");
        urlList.add("https://bulgaria.craigslist.org/"); cityList.add("bulgaria");
        urlList.add("https://zagreb.craigslist.org/"); cityList.add("croatia");
        urlList.add("http://prague.craigslist.cz/"); cityList.add("prague, czech republic");
        urlList.add("https://copenhagen.craigslist.org/"); cityList.add("copenhagen, denmark");
        urlList.add("http://helsinki.craigslist.fi/"); cityList.add("finland");
        urlList.add("https://bordeaux.craigslist.org/"); cityList.add("bordeaux, france");
        urlList.add("https://rennes.craigslist.org/"); cityList.add("brittany, france");
        urlList.add("https://grenoble.craigslist.org/"); cityList.add("grenoble, france");
        urlList.add("https://lille.craigslist.org/"); cityList.add("lille, france");
        urlList.add("https://loire.craigslist.org/"); cityList.add("loire valley, france");
        urlList.add("https://lyon.craigslist.org/"); cityList.add("lyon, france");
        urlList.add("https://marseilles.craigslist.org/"); cityList.add("marseille, france");
        urlList.add("https://montpellier.craigslist.org/"); cityList.add("montpellier, france");
        urlList.add("https://cotedazur.craigslist.org/"); cityList.add("nice / cote d'azur, france");
        urlList.add("https://rouen.craigslist.org/"); cityList.add("normandy, france");
        urlList.add("https://paris.craigslist.org/"); cityList.add("paris, france");
        urlList.add("https://strasbourg.craigslist.org/"); cityList.add("strasbourg, france");
        urlList.add("https://toulouse.craigslist.org/"); cityList.add("toulouse, france");
        urlList.add("https://berlin.craigslist.de/"); cityList.add("berlin, germany");
        urlList.add("https://bremen.craigslist.de/"); cityList.add("bremen, germany");
        urlList.add("https://cologne.craigslist.de/"); cityList.add("cologne, germany");
        urlList.add("https://dresden.craigslist.de/"); cityList.add("dresden, germany");
        urlList.add("https://dusseldorf.craigslist.de/"); cityList.add("dusseldorf, germany");
        urlList.add("https://essen.craigslist.de/"); cityList.add("essen / ruhr, germany");
        urlList.add("https://frankfurt.craigslist.de/"); cityList.add("frankfurt, germany");
        urlList.add("https://hamburg.craigslist.de/"); cityList.add("hamburg, germany");
        urlList.add("https://hannover.craigslist.de/"); cityList.add("hannover, germany");
        urlList.add("https://heidelberg.craigslist.de/"); cityList.add("heidelberg, germany");
        urlList.add("https://kaiserslautern.craigslist.de/"); cityList.add("kaiserslautern, germany");
        urlList.add("https://leipzig.craigslist.de/"); cityList.add("leipzig, germany");
        urlList.add("https://munich.craigslist.de/"); cityList.add("munich, germany");
        urlList.add("https://nuremberg.craigslist.de/"); cityList.add("nuremberg, germany");
        urlList.add("https://stuttgart.craigslist.de/"); cityList.add("stuttgart, germany");
        urlList.add("http://athens.craigslist.gr/"); cityList.add("greece");
        urlList.add("https://budapest.craigslist.org/"); cityList.add("budapest, hungary");
        urlList.add("https://reykjavik.craigslist.org/"); cityList.add("reykjavik, iceland");
        urlList.add("https://dublin.craigslist.org/"); cityList.add("dublin, ireland");
        urlList.add("https://bologna.craigslist.it/"); cityList.add("bologna, italy");
        urlList.add("https://florence.craigslist.it/"); cityList.add("florence / tuscany, italy");
        urlList.add("https://genoa.craigslist.it/"); cityList.add("genoa, italy");
        urlList.add("https://milan.craigslist.it/"); cityList.add("milan, italy");
        urlList.add("https://naples.craigslist.it/"); cityList.add("napoli / campania, italy");
        urlList.add("https://perugia.craigslist.it/"); cityList.add("perugia, italy");
        urlList.add("https://rome.craigslist.it/"); cityList.add("rome, italy");
        urlList.add("https://sardinia.craigslist.it/"); cityList.add("sardinia, italy");
        urlList.add("https://sicily.craigslist.it/"); cityList.add("sicilia, italy");
        urlList.add("https://torino.craigslist.it/"); cityList.add("torino, italy");
        urlList.add("https://venice.craigslist.it/"); cityList.add("venice / veneto, italy");
        urlList.add("https://luxembourg.craigslist.org/"); cityList.add("luxembourg");
        urlList.add("https://amsterdam.craigslist.org/"); cityList.add("amsterdam / randstad, netherlands");
        urlList.add("https://oslo.craigslist.org/"); cityList.add("norway");
        urlList.add("http://warsaw.craigslist.pl/"); cityList.add("poland");
        urlList.add("http://faro.craigslist.pt/"); cityList.add("faro / algarve, portugal");
        urlList.add("http://lisbon.craigslist.pt/"); cityList.add("lisbon, portugal");
        urlList.add("http://porto.craigslist.pt/"); cityList.add("porto, portugal");
        urlList.add("https://bucharest.craigslist.org/"); cityList.add("romania");
        urlList.add("https://moscow.craigslist.org/"); cityList.add("moscow, russian federation");
        urlList.add("https://stpetersburg.craigslist.org/"); cityList.add("st petersburg, russian federation");
        urlList.add("https://alicante.craigslist.es/"); cityList.add("alicante, spain");
        urlList.add("https://baleares.craigslist.es/"); cityList.add("baleares, spain");
        urlList.add("https://barcelona.craigslist.es/"); cityList.add("barcelona, spain");
        urlList.add("https://bilbao.craigslist.es/"); cityList.add("bilbao, spain");
        urlList.add("https://cadiz.craigslist.es/"); cityList.add("cadiz, spain");
        urlList.add("https://canarias.craigslist.es/"); cityList.add("canarias, spain");
        urlList.add("https://granada.craigslist.es/"); cityList.add("granada, spain");
        urlList.add("https://madrid.craigslist.es/"); cityList.add("madrid, spain");
        urlList.add("https://malaga.craigslist.es/"); cityList.add("malaga, spain");
        urlList.add("https://sevilla.craigslist.es/"); cityList.add("sevilla, spain");
        urlList.add("https://valencia.craigslist.es/"); cityList.add("valencia, spain");
        urlList.add("https://stockholm.craigslist.se/"); cityList.add("sweden");
        urlList.add("https://basel.craigslist.ch/"); cityList.add("basel, switzerland");
        urlList.add("https://bern.craigslist.ch/"); cityList.add("bern, switzerland");
        urlList.add("https://geneva.craigslist.ch/"); cityList.add("geneva, switzerland");
        urlList.add("https://lausanne.craigslist.ch/"); cityList.add("lausanne, switzerland");
        urlList.add("https://zurich.craigslist.ch/"); cityList.add("zurich, switzerland");
        urlList.add("https://istanbul.craigslist.com.tr/"); cityList.add("turkey");
        urlList.add("https://ukraine.craigslist.org/"); cityList.add("ukraine");
        urlList.add("https://aberdeen.craigslist.co.uk/"); cityList.add("aberdeen, united kingdom");
        urlList.add("https://bath.craigslist.co.uk/"); cityList.add("bath, united kingdom");
        urlList.add("https://belfast.craigslist.co.uk/"); cityList.add("belfast, united kingdom");
        urlList.add("https://birmingham.craigslist.co.uk/"); cityList.add("birmingham / west mids, united kingdom");
        urlList.add("https://brighton.craigslist.co.uk/"); cityList.add("brighton, united kingdom");
        urlList.add("https://bristol.craigslist.co.uk/"); cityList.add("bristol, united kingdom");
        urlList.add("https://cambridge.craigslist.co.uk/"); cityList.add("cambridge, uk, united kingdom");
        urlList.add("https://cardiff.craigslist.co.uk/"); cityList.add("cardiff / wales, united kingdom");
        urlList.add("https://coventry.craigslist.co.uk/"); cityList.add("coventry, united kingdom");
        urlList.add("https://derby.craigslist.co.uk/"); cityList.add("derby, united kingdom");
        urlList.add("https://devon.craigslist.co.uk/"); cityList.add("devon &amp; cornwall, united kingdom");
        urlList.add("https://dundee.craigslist.co.uk/"); cityList.add("dundee, united kingdom");
        urlList.add("https://norwich.craigslist.co.uk/"); cityList.add("east anglia, united kingdom");
        urlList.add("https://eastmids.craigslist.co.uk/"); cityList.add("east midlands, united kingdom");
        urlList.add("https://edinburgh.craigslist.co.uk/"); cityList.add("edinburgh, united kingdom");
        urlList.add("https://essex.craigslist.co.uk/"); cityList.add("essex, united kingdom");
        urlList.add("https://glasgow.craigslist.co.uk/"); cityList.add("glasgow, united kingdom");
        urlList.add("https://hampshire.craigslist.co.uk/"); cityList.add("hampshire, united kingdom");
        urlList.add("https://kent.craigslist.co.uk/"); cityList.add("kent, united kingdom");
        urlList.add("https://leeds.craigslist.co.uk/"); cityList.add("leeds, united kingdom");
        urlList.add("https://liverpool.craigslist.co.uk/"); cityList.add("liverpool, united kingdom");
        urlList.add("https://london.craigslist.co.uk/"); cityList.add("london, united kingdom");
        urlList.add("https://manchester.craigslist.co.uk/"); cityList.add("manchester, united kingdom");
        urlList.add("https://newcastle.craigslist.co.uk/"); cityList.add("newcastle / ne england, united kingdom");
        urlList.add("https://nottingham.craigslist.co.uk/"); cityList.add("nottingham, united kingdom");
        urlList.add("https://oxford.craigslist.co.uk/"); cityList.add("oxford, united kingdom");
        urlList.add("https://sheffield.craigslist.co.uk/"); cityList.add("sheffield, united kingdom");
        urlList.add("https://bangladesh.craigslist.org/"); cityList.add("bangladesh");
        urlList.add("http://beijing.craigslist.com.cn/"); cityList.add("beijing, china");
        urlList.add("http://chengdu.craigslist.com.cn/"); cityList.add("chengdu, china");
        urlList.add("http://chongqing.craigslist.com.cn/"); cityList.add("chongqing, china");
        urlList.add("http://dalian.craigslist.com.cn/"); cityList.add("dalian, china");
        urlList.add("http://guangzhou.craigslist.com.cn/"); cityList.add("guangzhou, china");
        urlList.add("http://hangzhou.craigslist.com.cn/"); cityList.add("hangzhou, china");
        urlList.add("http://nanjing.craigslist.com.cn/"); cityList.add("nanjing, china");
        urlList.add("http://shanghai.craigslist.com.cn/"); cityList.add("shanghai, china");
        urlList.add("http://shenyang.craigslist.com.cn/"); cityList.add("shenyang, china");
        urlList.add("http://shenzhen.craigslist.com.cn/"); cityList.add("shenzhen, china");
        urlList.add("http://wuhan.craigslist.com.cn/"); cityList.add("wuhan, china");
        urlList.add("http://xian.craigslist.com.cn/"); cityList.add("xi'an, china");
        urlList.add("http://hongkong.craigslist.hk/"); cityList.add("hong kong");
        urlList.add("http://ahmedabad.craigslist.co.in/"); cityList.add("ahmedabad, india");
        urlList.add("http://bangalore.craigslist.co.in/"); cityList.add("bangalore, india");
        urlList.add("http://bhubaneswar.craigslist.co.in/"); cityList.add("bhubaneswar, india");
        urlList.add("http://chandigarh.craigslist.co.in/"); cityList.add("chandigarh, india");
        urlList.add("http://chennai.craigslist.co.in/"); cityList.add("chennai (madras), india");
        urlList.add("http://delhi.craigslist.co.in/"); cityList.add("delhi, india");
        urlList.add("http://goa.craigslist.co.in/"); cityList.add("goa, india");
        urlList.add("http://hyderabad.craigslist.co.in/"); cityList.add("hyderabad, india");
        urlList.add("http://indore.craigslist.co.in/"); cityList.add("indore, india");
        urlList.add("http://jaipur.craigslist.co.in/"); cityList.add("jaipur, india");
        urlList.add("http://kerala.craigslist.co.in/"); cityList.add("kerala, india");
        urlList.add("http://kolkata.craigslist.co.in/"); cityList.add("kolkata (calcutta), india");
        urlList.add("http://lucknow.craigslist.co.in/"); cityList.add("lucknow, india");
        urlList.add("http://mumbai.craigslist.co.in/"); cityList.add("mumbai, india");
        urlList.add("http://pune.craigslist.co.in/"); cityList.add("pune, india");
        urlList.add("http://surat.craigslist.co.in/"); cityList.add("surat surat, india");
        urlList.add("https://jakarta.craigslist.org/"); cityList.add("indonesia");
        urlList.add("https://tehran.craigslist.org/"); cityList.add("iran");
        urlList.add("https://baghdad.craigslist.org/"); cityList.add("iraq");
        urlList.add("https://haifa.craigslist.org/"); cityList.add("haifa, israel and palestine");
        urlList.add("https://jerusalem.craigslist.org/"); cityList.add("jerusalem, israel and palestine");
        urlList.add("https://telaviv.craigslist.org/"); cityList.add("tel aviv, israel and palestine");
        urlList.add("https://ramallah.craigslist.org/"); cityList.add("west bank, israel and palestine");
        urlList.add("https://fukuoka.craigslist.jp/"); cityList.add("fukuoka, japan");
        urlList.add("https://hiroshima.craigslist.jp/"); cityList.add("hiroshima, japan");
        urlList.add("https://nagoya.craigslist.jp/"); cityList.add("nagoya, japan");
        urlList.add("https://okinawa.craigslist.jp/"); cityList.add("okinawa, japan");
        urlList.add("https://osaka.craigslist.jp/"); cityList.add("osaka-kobe-kyoto, japan");
        urlList.add("https://sapporo.craigslist.jp/"); cityList.add("sapporo, japan");
        urlList.add("https://sendai.craigslist.jp/"); cityList.add("sendai, japan");
        urlList.add("https://tokyo.craigslist.jp/"); cityList.add("tokyo, japan");
        urlList.add("http://seoul.craigslist.co.kr/"); cityList.add("seoul, korea");
        urlList.add("https://kuwait.craigslist.org/"); cityList.add("kuwait");
        urlList.add("https://beirut.craigslist.org/"); cityList.add("beirut, lebanon");
        urlList.add("https://malaysia.craigslist.org/"); cityList.add("malaysia");
        urlList.add("https://pakistan.craigslist.org/"); cityList.add("pakistan");
        urlList.add("https://bacolod.craigslist.com.ph/"); cityList.add("bacolod, philippines");
        urlList.add("https://naga.craigslist.com.ph/"); cityList.add("bicol region, philippines");
        urlList.add("https://cdo.craigslist.com.ph/"); cityList.add("cagayan de oro, philippines");
        urlList.add("https://cebu.craigslist.com.ph/"); cityList.add("cebu, philippines");
        urlList.add("https://davaocity.craigslist.com.ph/"); cityList.add("davao city, philippines");
        urlList.add("https://iloilo.craigslist.com.ph/"); cityList.add("iloilo, philippines");
        urlList.add("https://manila.craigslist.com.ph/"); cityList.add("manila, philippines");
        urlList.add("https://pampanga.craigslist.com.ph/"); cityList.add("pampanga, philippines");
        urlList.add("https://zamboanga.craigslist.com.ph/"); cityList.add("zamboanga, philippines");
        urlList.add("http://singapore.craigslist.com.sg/"); cityList.add("singapore");
        urlList.add("http://taipei.craigslist.com.tw/"); cityList.add("taiwan");
        urlList.add("http://bangkok.craigslist.co.th/"); cityList.add("thailand");
        urlList.add("https://dubai.craigslist.org/"); cityList.add("united arab emirates");
        urlList.add("https://vietnam.craigslist.org/"); cityList.add("vietnam");
        urlList.add("https://adelaide.craigslist.com.au/"); cityList.add("adelaide, australia");
        urlList.add("https://brisbane.craigslist.com.au/"); cityList.add("brisbane, australia");
        urlList.add("https://cairns.craigslist.com.au/"); cityList.add("cairns, australia");
        urlList.add("https://canberra.craigslist.com.au/"); cityList.add("canberra, australia");
        urlList.add("https://darwin.craigslist.com.au/"); cityList.add("darwin, australia");
        urlList.add("https://goldcoast.craigslist.com.au/"); cityList.add("gold coast, australia");
        urlList.add("https://melbourne.craigslist.com.au/"); cityList.add("melbourne, australia");
        urlList.add("https://ntl.craigslist.com.au/"); cityList.add("newcastle, nsw, australia");
        urlList.add("https://perth.craigslist.com.au/"); cityList.add("perth, australia");
        urlList.add("https://sydney.craigslist.com.au/"); cityList.add("sydney, australia");
        urlList.add("https://hobart.craigslist.com.au/"); cityList.add("tasmania, australia");
        urlList.add("https://wollongong.craigslist.com.au/"); cityList.add("wollongong, australia");
        urlList.add("https://auckland.craigslist.org/"); cityList.add("auckland, new zealand");
        urlList.add("https://christchurch.craigslist.org/"); cityList.add("christchurch, new zealand");
        urlList.add("http://dunedin.craigslist.co.nz/"); cityList.add("dunedin, new zealand");
        urlList.add("https://wellington.craigslist.org/"); cityList.add("wellington, new zealand");
        urlList.add("https://buenosaires.craigslist.org/"); cityList.add("buenos aires, argentina");
        urlList.add("https://lapaz.craigslist.org/"); cityList.add("bolivia");
        urlList.add("https://belohorizonte.craigslist.org/"); cityList.add("belo horizonte, brazil");
        urlList.add("https://brasilia.craigslist.org/"); cityList.add("brasilia, brazil");
        urlList.add("https://curitiba.craigslist.org/"); cityList.add("curitiba, brazil");
        urlList.add("https://fortaleza.craigslist.org/"); cityList.add("fortaleza, brazil");
        urlList.add("https://portoalegre.craigslist.org/"); cityList.add("porto alegre, brazil");
        urlList.add("https://recife.craigslist.org/"); cityList.add("recife, brazil");
        urlList.add("https://rio.craigslist.org/"); cityList.add("rio de janeiro, brazil");
        urlList.add("https://salvador.craigslist.org/"); cityList.add("salvador, bahia, brazil");
        urlList.add("https://saopaulo.craigslist.org/"); cityList.add("sao paulo, brazil");
        urlList.add("https://caribbean.craigslist.org/"); cityList.add("caribbean islands");
        urlList.add("https://santiago.craigslist.org/"); cityList.add("chile");
        urlList.add("https://colombia.craigslist.org/"); cityList.add("colombia");
        urlList.add("https://costarica.craigslist.org/"); cityList.add("costa rica");
        urlList.add("https://santodomingo.craigslist.org/"); cityList.add("dominican republic");
        urlList.add("https://quito.craigslist.org/"); cityList.add("ecuador");
        urlList.add("https://elsalvador.craigslist.org/"); cityList.add("el salvador");
        urlList.add("https://guatemala.craigslist.org/"); cityList.add("guatemala");
        urlList.add("https://acapulco.craigslist.com.mx/"); cityList.add("acapulco, mexico");
        urlList.add("https://bajasur.craigslist.com.mx/"); cityList.add("baja california sur, mexico");
        urlList.add("https://chihuahua.craigslist.com.mx/"); cityList.add("chihuahua, mexico");
        urlList.add("https://juarez.craigslist.com.mx/"); cityList.add("ciudad juarez, mexico");
        urlList.add("https://guadalajara.craigslist.com.mx/"); cityList.add("guadalajara, mexico");
        urlList.add("https://guanajuato.craigslist.com.mx/"); cityList.add("guanajuato, mexico");
        urlList.add("https://hermosillo.craigslist.com.mx/"); cityList.add("hermosillo, mexico");
        urlList.add("https://mazatlan.craigslist.com.mx/"); cityList.add("mazatlan, mexico");
        urlList.add("https://mexicocity.craigslist.com.mx/"); cityList.add("mexico city, mexico");
        urlList.add("https://monterrey.craigslist.com.mx/"); cityList.add("monterrey, mexico");
        urlList.add("https://oaxaca.craigslist.com.mx/"); cityList.add("oaxaca, mexico");
        urlList.add("https://puebla.craigslist.com.mx/"); cityList.add("puebla, mexico");
        urlList.add("https://pv.craigslist.com.mx/"); cityList.add("puerto vallarta, mexico");
        urlList.add("https://tijuana.craigslist.com.mx/"); cityList.add("tijuana, mexico");
        urlList.add("https://veracruz.craigslist.com.mx/"); cityList.add("veracruz, mexico");
        urlList.add("https://yucatan.craigslist.com.mx/"); cityList.add("yucatan, mexico");
        urlList.add("https://managua.craigslist.org/"); cityList.add("nicaragua");
        urlList.add("https://panama.craigslist.org/"); cityList.add("panama");
        urlList.add("https://lima.craigslist.org/"); cityList.add("peru");
        urlList.add("https://puertorico.craigslist.org/"); cityList.add("puerto rico");
        urlList.add("https://montevideo.craigslist.org/"); cityList.add("montevideo, uruguay");
        urlList.add("https://caracas.craigslist.org/"); cityList.add("venezuela");
        urlList.add("https://cairo.craigslist.org/"); cityList.add("egypt");
        urlList.add("https://addisababa.craigslist.org/"); cityList.add("ethiopia");
        urlList.add("https://accra.craigslist.org/"); cityList.add("ghana");
        urlList.add("https://kenya.craigslist.org/"); cityList.add("kenya");
        urlList.add("https://casablanca.craigslist.org/"); cityList.add("morocco");
        urlList.add("https://capetown.craigslist.co.za/"); cityList.add("cape town, south africa");
        urlList.add("https://durban.craigslist.co.za/"); cityList.add("durban, south africa");
        urlList.add("https://johannesburg.craigslist.co.za/"); cityList.add("johannesburg, south africa");
        urlList.add("https://pretoria.craigslist.co.za/"); cityList.add("pretoria, south africa");
        urlList.add("https://tunis.craigslist.org/"); cityList.add("tunisia");

        // Defining an adapter, to adapt my array list to the correct format.
        addaptedAray = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityList){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

            /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.CYAN);

                // Set the text color.
                switch (textColorChoice) {

                    case 0:

                        textView.setTextColor(ColorStateList.valueOf(Color.CYAN));
                        searchMyLocal.setTextColor(ColorStateList.valueOf(Color.CYAN));
                        searchIv.setBackgroundColor(Color.CYAN);

                        drawableMain = searchMyLocal.getBackground();
                        drawableMain.setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_ATOP);

                        break;

                    case 1:
                        textView.setTextColor(ColorStateList.valueOf(Color.RED));
                        searchMyLocal.setTextColor(ColorStateList.valueOf(Color.RED));
                        searchIv.setBackgroundColor(Color.RED);

                        drawableMain = searchMyLocal.getBackground();
                        drawableMain.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                        break;

                    case 2:
                        textView.setTextColor(ColorStateList.valueOf(Color.GREEN));
                        searchMyLocal.setTextColor(ColorStateList.valueOf(Color.GREEN));
                        searchIv.setBackgroundColor(Color.GREEN);

                        drawableMain = searchMyLocal.getBackground();
                        drawableMain.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                        break;

                    case 3:
                        textView.setTextColor(ColorStateList.valueOf(Color.GRAY));
                        searchMyLocal.setTextColor(ColorStateList.valueOf(Color.GRAY));
                        searchIv.setBackgroundColor(Color.GRAY);

                        drawableMain = searchMyLocal.getBackground();
                        drawableMain.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                        break;

                    case 4:
                        textView.setTextColor(ColorStateList.valueOf(Color.BLACK));
                        searchMyLocal.setTextColor(ColorStateList.valueOf(Color.BLACK));
                        searchIv.setBackgroundColor(Color.BLACK);

                        drawableMain = searchMyLocal.getBackground();
                        drawableMain.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                        break;

                    case 5:
                        textView.setTextColor(ColorStateList.valueOf(Color.WHITE));
                        searchMyLocal.setTextColor(ColorStateList.valueOf(Color.WHITE));
                        searchIv.setBackgroundColor(Color.WHITE);

                        drawableMain = searchMyLocal.getBackground();
                        drawableMain.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                        break;

                    case 6:
                        textView.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                        searchMyLocal.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                        searchIv.setBackgroundColor(Color.MAGENTA);

                        drawableMain = searchMyLocal.getBackground();
                        drawableMain.setColorFilter(Color.MAGENTA, PorterDuff.Mode.SRC_ATOP);
                        break;

                    case 7:
                        textView.setTextColor(ColorStateList.valueOf(Color.BLUE));
                        searchMyLocal.setTextColor(ColorStateList.valueOf(Color.BLUE));
                        searchIv.setBackgroundColor(Color.BLUE);

                        drawableMain = searchMyLocal.getBackground();
                        drawableMain.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
                        break;

                }

                if(Build.VERSION.SDK_INT > 16) {

                    searchMyLocal.setBackground(drawableMain); // set the new drawable to EditText

                }else{

                    searchMyLocal.setBackgroundDrawable(drawableMain); // use setBackgroundDrawable because setBackground required API 16

                }

                return view;
            }
        };

        // Update the addapter.
        addaptedAray.notifyDataSetChanged();

    }

}
