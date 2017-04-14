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
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static com.alaskalinuxuser.justcraigslist.MainActivity.colorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.fabColorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.textColorChoice;
import static com.alaskalinuxuser.justcraigslist.MainActivity.backChoice;

public class SettingsActivity extends AppCompatActivity {

    // Declare all my buttons and groups.
    RadioGroup radioTextColor, radioTitleColor, radioFabColor, radioBackColor;
    RadioButton fblue,fred,fgreen,fgray,fblack,fwhite,fmag,fcyan;
    RadioButton tblue,tred,tgreen,tgray,tblack,twhite,tmag,tcyan;
    RadioButton xblue,xred,xgreen,xgray,xblack,xwhite,xmag,xcyan;
    RadioButton bpicone,bpictwo,bpicthree,bgray,bblack,bpicfour,bpicfive,bwhite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Define all of my buttons and groups.
        radioFabColor = (RadioGroup)findViewById(R.id.radioFabColor);
        radioTextColor = (RadioGroup)findViewById(R.id.radioTextColor);
        radioTitleColor = (RadioGroup)findViewById(R.id.radioTitleColor);
        radioBackColor = (RadioGroup)findViewById(R.id.radioBackColor);

        fblue = (RadioButton)findViewById(R.id.fblue);
        fblack = (RadioButton)findViewById(R.id.fblack);
        fred = (RadioButton)findViewById(R.id.fred);
        fgreen = (RadioButton)findViewById(R.id.fgreen);
        fgray = (RadioButton)findViewById(R.id.fgray);
        fwhite = (RadioButton)findViewById(R.id.fwhite);
        fmag = (RadioButton)findViewById(R.id.fmag);
        fcyan = (RadioButton)findViewById(R.id.fcyan);

        xblue = (RadioButton)findViewById(R.id.xblue);
        xblack = (RadioButton)findViewById(R.id.xblack);
        xred = (RadioButton)findViewById(R.id.xred);
        xgreen = (RadioButton)findViewById(R.id.xgreen);
        xgray = (RadioButton)findViewById(R.id.xgray);
        xwhite = (RadioButton)findViewById(R.id.xwhite);
        xmag = (RadioButton)findViewById(R.id.xmag);
        xcyan = (RadioButton)findViewById(R.id.xcyan);

        tblue = (RadioButton)findViewById(R.id.tblue);
        tblack = (RadioButton)findViewById(R.id.tblack);
        tred = (RadioButton)findViewById(R.id.tred);
        tgreen = (RadioButton)findViewById(R.id.tgreen);
        tgray = (RadioButton)findViewById(R.id.tgray);
        twhite = (RadioButton)findViewById(R.id.twhite);
        tmag = (RadioButton)findViewById(R.id.tmag);
        tcyan = (RadioButton)findViewById(R.id.tcyan);

        bpicone = (RadioButton)findViewById(R.id.bpicone);
        bblack = (RadioButton)findViewById(R.id.bblack);
        bpictwo = (RadioButton)findViewById(R.id.bpictwo);
        bpicthree = (RadioButton)findViewById(R.id.bpicthree);
        bgray = (RadioButton)findViewById(R.id.bgray);
        bpicfour = (RadioButton)findViewById(R.id.bpicfour);
        bpicfive = (RadioButton)findViewById(R.id.bpicfive);
        bwhite = (RadioButton)findViewById(R.id.bwhite);

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

                // Go back to the main activity with the data so we can save it.
                Intent returnIntent = getIntent();
                returnIntent.putExtra("colorChoice", String.valueOf(colorChoice));
                returnIntent.putExtra("textColorChoice", String.valueOf(textColorChoice));
                returnIntent.putExtra("fabColorChoice", String.valueOf(fabColorChoice));
                returnIntent.putExtra("backChoice", String.valueOf(backChoice));
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }
        });

        // Set up our listener for the title color.
        radioBackColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.bwhite) {

                    backChoice = 7;

                } else if(checkedId == R.id.bpictwo) {

                    backChoice = 1;

                } else if(checkedId == R.id.bpicthree) {

                    backChoice = 2;

                } else if(checkedId == R.id.bgray) {

                    backChoice = 5;

                } else if(checkedId == R.id.bblack) {

                    backChoice = 6;

                } else if(checkedId == R.id.bpicfour) {

                    backChoice = 3;

                } else if(checkedId == R.id.bpicfive) {

                    backChoice = 4;

                } else {

                    backChoice = 0;

                }

                // Log for testing.
                Log.i("WJH", String.valueOf(backChoice));

            }
        });

        // Set up our listener for the title color.
        radioTitleColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.tblue) {

                    colorChoice = 0;

                } else if(checkedId == R.id.tred) {

                    colorChoice = 1;

                } else if(checkedId == R.id.tgreen) {

                    colorChoice = 2;

                } else if(checkedId == R.id.tgray) {

                    colorChoice = 3;

                } else if(checkedId == R.id.tblack) {

                    colorChoice = 4;

                } else if(checkedId == R.id.twhite) {

                    colorChoice = 5;

                } else if(checkedId == R.id.tmag) {

                    colorChoice = 6;

                } else {

                    colorChoice = 7;

                }

                // Log for testing.
                Log.i("WJH", String.valueOf(colorChoice));

            }
        });

        // Set up our listener for the title color.
        radioFabColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.fblue) {

                    fabColorChoice = 0;

                } else if(checkedId == R.id.fred) {

                    fabColorChoice = 1;

                } else if(checkedId == R.id.fgreen) {

                    fabColorChoice = 2;

                } else if(checkedId == R.id.fgray) {

                    fabColorChoice = 3;

                } else if(checkedId == R.id.fblack) {

                    fabColorChoice = 4;

                } else if(checkedId == R.id.fwhite) {

                    fabColorChoice = 5;

                } else if(checkedId == R.id.fmag) {

                    fabColorChoice = 6;

                } else {

                    fabColorChoice = 7;

                }

                //Log for testing.
                Log.i("WJH", String.valueOf(fabColorChoice));
            }
        });

        // Set up our listener for the title color.
        radioTextColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.xblue) {

                    textColorChoice = 7;

                } else if(checkedId == R.id.xred) {

                    textColorChoice = 1;

                } else if(checkedId == R.id.xgreen) {

                    textColorChoice = 2;

                } else if(checkedId == R.id.xgray) {

                    textColorChoice = 3;

                } else if(checkedId == R.id.xblack) {

                    textColorChoice = 4;

                } else if(checkedId == R.id.xwhite) {

                    textColorChoice = 5;

                } else if(checkedId == R.id.xmag) {

                    textColorChoice = 6;

                } else {

                    textColorChoice = 0;

                }

                //Log for testing.
                Log.i("WJH", String.valueOf(textColorChoice));

            }
        });


    }

}
