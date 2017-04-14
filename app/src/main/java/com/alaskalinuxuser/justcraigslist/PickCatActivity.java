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

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.alaskalinuxuser.justcraigslist.MainActivity.backChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.colorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.fabColorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.textColorChoice;

public class PickCatActivity extends AppCompatActivity {

    // Declare my variables.
    ListView theCatList;
    ArrayList<String> catList, codeList;
    ArrayAdapter<String> myAddaptedAray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_cat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout llpiccat = (LinearLayout) findViewById(R.id.LLpickcat);

        // Set the Background color.
        switch (backChoice) {

            case 0:

                if(Build.VERSION.SDK_INT > 16) {

                    llpiccat.setBackground(getResources().getDrawable(R.drawable.jindong));

                } else {

                    llpiccat.setBackgroundDrawable(getResources().getDrawable(R.drawable.jindong));

                }

                break;

            case 1:

                if(Build.VERSION.SDK_INT > 16) {

                    llpiccat.setBackground(getResources().getDrawable(R.drawable.plymouth));

                } else {

                    llpiccat.setBackgroundDrawable(getResources().getDrawable(R.drawable.plymouth));

                }

                break;

            case 2:

                if(Build.VERSION.SDK_INT > 16) {

                    llpiccat.setBackground(getResources().getDrawable(R.drawable.chair));

                } else {

                    llpiccat.setBackgroundDrawable(getResources().getDrawable(R.drawable.chair));

                }

                break;

            case 3:

                if(Build.VERSION.SDK_INT > 16) {

                    llpiccat.setBackground(getResources().getDrawable(R.drawable.collie));

                } else {

                    llpiccat.setBackgroundDrawable(getResources().getDrawable(R.drawable.collie));

                }

                break;

            case 4:

                if(Build.VERSION.SDK_INT > 16) {

                    llpiccat.setBackground(getResources().getDrawable(R.drawable.flower));

                } else {

                    llpiccat.setBackgroundDrawable(getResources().getDrawable(R.drawable.flower));

                }

                break;

            case 5:

                llpiccat.setBackgroundColor(Color.GRAY);

                break;

            case 6:

                llpiccat.setBackgroundColor(Color.BLACK);

                break;

            case 7:

                llpiccat.setBackgroundColor(Color.WHITE);

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
        theCatList = (ListView) findViewById(R.id.theCatList);

        // Defining an array of titles and urls.
        catList = new ArrayList<String>();
        codeList = new ArrayList<String>();

        // Clear the lists, so we don't get duplicates.
        catList.clear();
        codeList.clear();

        // Add these values to the lists....
        codeList.add("sss"); catList.add("All categories...");
        codeList.add("ata"); catList.add("Antiques");
        codeList.add("ppa"); catList.add("Appliances");
        codeList.add("ara"); catList.add("Arts & crafts");
        codeList.add("sna"); catList.add("Atvs, utvs, snowmobiles");
        codeList.add("pta"); catList.add("Auto parts");
        codeList.add("wta"); catList.add("Auto wheels & tires");
        codeList.add("baa"); catList.add("Baby & kid stuff");
        codeList.add("bar"); catList.add("Barter");
        codeList.add("haa"); catList.add("Beauty & health");
        codeList.add("bia"); catList.add("Bicycles");
        codeList.add("bip"); catList.add("Bicycle parts");
        codeList.add("boo"); catList.add("Boats");
        codeList.add("bpa"); catList.add("Boat parts");
        codeList.add("bka"); catList.add("Books & magazines");
        codeList.add("bfa"); catList.add("Business");
        codeList.add("cto"); catList.add("Cars & trucks - by owner");
        codeList.add("ctd"); catList.add("cars & trucks - by dealer");
        codeList.add("cta"); catList.add("cars & trucks - all");
        codeList.add("ema"); catList.add("CDs / DVDs / VHS");
        codeList.add("moa"); catList.add("Cell phones");
        codeList.add("cla"); catList.add("clothing & accessories");
        codeList.add("cba"); catList.add("Collectibles");
        codeList.add("sya"); catList.add("Computers");
        codeList.add("syp"); catList.add("Computer parts");
        codeList.add("ela"); catList.add("Electronics");
        codeList.add("gra"); catList.add("Farm & garden");
        codeList.add("zip"); catList.add("Free stuff");
        codeList.add("fua"); catList.add("Furniture");
        codeList.add("gms"); catList.add("Garage & moving sales");
        codeList.add("foa"); catList.add("General for sale");
        codeList.add("hva"); catList.add("Heavy equipment");
        codeList.add("hsa"); catList.add("Household items");
        codeList.add("jwa"); catList.add("Jewelry");
        codeList.add("maa"); catList.add("Materials");
        codeList.add("mca"); catList.add("Motorcycles/scooters");
        codeList.add("mpa"); catList.add("Motorcycle parts & accessories");
        codeList.add("msa"); catList.add("Musical instruments");
        codeList.add("pha"); catList.add("Photo & video equipment");
        codeList.add("rva"); catList.add("Recreational vehicles");
        codeList.add("sga"); catList.add("Sporting goods");
        codeList.add("tia"); catList.add("Tickets");
        codeList.add("tla"); catList.add("Tools");
        codeList.add("taa"); catList.add("Toys & games");
        codeList.add("tra"); catList.add("Trailers");
        codeList.add("vga"); catList.add("Video gaming");
        codeList.add("waa"); catList.add("Wanted");

        // Defining an adapter, to adapt my array list to the correct format.
        myAddaptedAray = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, catList){

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

                        break;

                    case 1:
                        textView.setTextColor(ColorStateList.valueOf(Color.RED));
                        break;

                    case 2:
                        textView.setTextColor(ColorStateList.valueOf(Color.GREEN));
                        break;

                    case 3:
                        textView.setTextColor(ColorStateList.valueOf(Color.GRAY));
                        break;

                    case 4:
                        textView.setTextColor(ColorStateList.valueOf(Color.BLACK));
                        break;

                    case 5:
                        textView.setTextColor(ColorStateList.valueOf(Color.WHITE));
                        break;

                    case 6:
                        textView.setTextColor(ColorStateList.valueOf(Color.MAGENTA));
                        break;

                    case 7:
                        textView.setTextColor(ColorStateList.valueOf(Color.BLUE));
                        break;

                }

                return view;
            }
        };

        // Using the adapter to adapt my array list to the defined list view that I declared already.
        theCatList.setAdapter(myAddaptedAray);

        // Setting up a listener to "listen" for me to click on something in the list.
        theCatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            // Overriding the generic code that Android uses for list views to do something specific.
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int z, long l) {

                // Convert the int z into the strings we need.
                String c = (String) catList.get(z);
                String d =  (String) codeList.get(z);

                // Logging that I tapped said item in the list.
                // Logging only, not needed. // Log.i("tapped", c + d);

                // Send my intent with my variables.
                Intent returnIntent = getIntent();
                returnIntent.putExtra("catIntent", c);
                returnIntent.putExtra("codeIntent", d);
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
    }

}