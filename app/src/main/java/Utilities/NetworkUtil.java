package Utilities;

import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by acer on 26/04/2017.
 */

public class NetworkUtil {
    private  static final String TAG = "NETWORK UTIL";
    public  static final String KAJIANKU = "http://kajianku.herokuapp.com/kajianku.json";

    public static URL buildUrl(String urlKajian){
        Uri builtUri = Uri.parse(urlKajian).buildUpon().build();
        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG,"Build URL "+ url);
        return url;

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput  = scanner.hasNext();
            if (hasInput)
            {
                return scanner.next();
            }
            else{
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }


}

