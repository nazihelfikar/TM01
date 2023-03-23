package Tm01;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Connect {

    private final String USER_AGENT = "Mozilla/0,5";

    public static URL buildURL(String urlQuerry) {
        URL url = null;
        try {
            url = new URL(urlQuerry.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner Scanner = new Scanner(in);
            Scanner.useDelimiter("\\A");
            boolean hasInput = Scanner.hasNext();
            if (hasInput) {
                return Scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
            }
        }
    }

