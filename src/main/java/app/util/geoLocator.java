package app.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;

public class geoLocator {
    public static int getDistance(String latitude, String longitude, String destination) {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&key=AIzaSyCTWywSdF6HnJ6JtO7oQpE2MHf75Rb7mBo";

        try {
            url += "&origins=" + URLEncoder.encode(latitude, "UTF-8")  + "," + URLEncoder.encode(longitude, "UTF-8");
            url += "&destinations=%s" + URLEncoder.encode(destination, "UTF-8");
        } catch (Exception e) {
        }

        System.out.println(url);
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(5*1000);
            con.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) con.getContent()));
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
            JsonArray rows = rootobj.getAsJsonArray("rows");
            JsonArray elements = rows.get(0).getAsJsonObject().getAsJsonArray("elements");
            System.out.println(rows.get(0).getAsJsonObject().getAsJsonArray("elements"));
            if (!elements.get(0).getAsJsonObject().has("distance")) {
                return Integer.MAX_VALUE;
            }
            JsonObject distance = elements.get(0).getAsJsonObject().get("distance").getAsJsonObject();
            int value = distance.get("value").getAsInt();
            System.out.println(value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return Integer.MAX_VALUE;

    }
}