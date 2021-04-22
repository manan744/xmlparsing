package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Async starts");
        DownloadData downloadData = new DownloadData();
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");
        Log.d(TAG, "onCreate: done");
    }

    private class DownloadData extends AsyncTask<String,Void,String>
    {
        private static final String TAG = "DownloadData";
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "onPostExecute: parameter passed "+ s);
            ParseXml parseXml = new ParseXml();
            parseXml.parse(s);

        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: doInBackground starts with"+ strings[0]);
            String RssFeed = DownloadXml(strings[0]);
            return RssFeed;
        }
        private String DownloadXml(String urlpath)
        {
            StringBuilder xmlresult = new StringBuilder();
            try {
                URL url = new URL(urlpath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                Log.d(TAG, "DownloadXml: response code is " + response);
                //InputStream inputStream = connection.getInputStream();
                //InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int charsread;
                char[] inputbuffer = new char[500];
                while (true) {
                    charsread = bufferedReader.read(inputbuffer);
                    if (charsread < 0) {
                        break;
                    }
                    if (charsread > 0) {
                        xmlresult.append(String.copyValueOf(inputbuffer, 0, charsread));
                    }
                    bufferedReader.close();

                    return (xmlresult.toString());
                }
            }
                catch (MalformedURLException e)
            {
                Log.e(TAG, "DownloadXml: INVALID URL" + e.getMessage());
            }
            catch(IOException e)
            {
                Log.e(TAG, "DownloadXml: IO exception reading "+ e.getMessage());
            }
            return null;

        }
    }
}

