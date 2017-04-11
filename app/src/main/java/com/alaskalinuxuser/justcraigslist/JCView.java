package com.alaskalinuxuser.justcraigslist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
