package app;

import app.coffeeshop.CoffeeShopController;
import app.coffeeshop.CoffeeShopDao;

public class Main {
    public static CoffeeShopDao coffeeShops;

    public static void main(String[] args) {
        spark.SparkBase.port(4567);

        coffeeShops = new CoffeeShopDao();
        new CoffeeShopController(coffeeShops);
    }
}

