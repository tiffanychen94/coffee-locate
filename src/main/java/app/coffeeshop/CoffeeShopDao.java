package app.coffeeshop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class CoffeeShopDao {
    private static HashMap<String, CoffeeShop> coffeeshops = new HashMap<>();

    static {
        List<String[]> content = readData();
        for (int i = 0; i < content.size(); i++) {
            String[] column = content.get(i);
            String shopID = column[0];
            CoffeeShop shop = new CoffeeShop(column[0], column[1], column[2], column[3], column[4]);
            coffeeshops.put(shopID, shop);
        };
        System.out.print(coffeeshops);
    }

    public static List<String[]> readData() {
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("/src/main/java/app/locations.csv");
        List<String[]> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            //Some error logging
        } catch (IOException e) {

        }
        System.out.println(content.size());
        return content;
    }

    public CoffeeShop addShop(String name, String address, String latitude, String longitude) {
        int currLargestID = Integer.valueOf(Collections.max(coffeeshops.keySet(), getLargestId));
        String newID =  Integer.toString(currLargestID+ 1);
        CoffeeShop newShop = new CoffeeShop(newID, name, address, latitude, longitude);
        coffeeshops.put(newID, newShop);
        return newShop;
    }

    public CoffeeShop updateShop(String id, String name, String address, String latitude, String longitude) {
        CoffeeShop currShop = getShopInfo(id);
        if (currShop == null) {
            return null;
        }
        currShop.name = name;
        currShop.address = address;
        currShop.latitude = latitude;
        currShop.longitude = longitude;
        coffeeshops.put(id, currShop);
        return currShop;
    }

    public CoffeeShop deleteShop(String id) {
        CoffeeShop currShop = getShopInfo(id);
        if (currShop == null) {
            return null;
        }
        coffeeshops.remove(id);
        return currShop;
    }


    public CoffeeShop getShopInfo(String id) {
        return coffeeshops.get(id);
    }

    public CoffeeShop getNearest(String address) {
        int minDistance = Integer.MAX_VALUE;
        CoffeeShop nearest = null;
        for (CoffeeShop cs : coffeeshops.values()) {
            int distance = app.util.geoLocator.getDistance(cs.latitude, cs.longitude, address);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = cs;
                System.out.println(nearest.name);
            }
        }
        if (nearest == null) {
            return null;
        }
        return nearest;
    }

    Comparator<String> getLargestId = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
        }
    };


}
