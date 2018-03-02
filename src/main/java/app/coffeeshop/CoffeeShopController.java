package app.coffeeshop;

import app.util.Path;
import spark.Request;
import spark.Response;
import spark.Route;

public class CoffeeShopController {

    public CoffeeShopController(final CoffeeShopDao coffeeShopDao) {
        spark.Spark.get(Path.Web.LOCATE, new Route() {
            @Override
            public Object handle(Request request, Response response) {
                CoffeeShop shop = coffeeShopDao.getNearest(request.queryParams("address"));
                if (shop == null) {
                    return String.format("Error finding nearest shop");
                }
                response.status(400);
                return shop.name;
            }
        }, app.util.JsonUtil.json());

        spark.Spark.get(Path.Web.READ, new Route() {
            @Override
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                CoffeeShop shop = coffeeShopDao.getShopInfo(id);
                if (shop != null) {
                    return shop;
                }
                response.status(400);
                return String.format("No shop found with id %s", id);
            }
        }, app.util.JsonUtil.json());

        spark.Spark.post(Path.Web.CREATE, new Route() {
            @Override
            public Object handle(Request request, Response response) {
                CoffeeShop shop = coffeeShopDao.addShop(
                    request.queryParams("name"),
                    request.queryParams("address"),
                    request.queryParams("latitude"),
                    request.queryParams("longitude")
                );
                if (shop != null) {
                    return shop.id;
                }
                response.status(500);
                return "Error creating coffee shop";
            }
        }, app.util.JsonUtil.json());

        spark.Spark.put(Path.Web.UPDATE, new Route() {
            @Override
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                CoffeeShop shop = coffeeShopDao.updateShop(
                        id,
                        request.queryParams("name"),
                        request.queryParams("address"),
                        request.queryParams("latitude"),
                        request.queryParams("longitude")
                );
                if (shop != null) {
                    return shop.id;
                }
                response.status(400);
                return String.format("No shop found with id %s", id);
            }
        }, app.util.JsonUtil.json());

        spark.Spark.delete(Path.Web.DELETE, new Route() {
            @Override
            public Object handle(Request request, Response response) {
                String id = request.params(":id");
                CoffeeShop shop = coffeeShopDao.deleteShop(id);
                if (shop != null) {
                    return String.format("Shop id %s deleted", id);
                }
                response.status(400);
                return String.format("No shop found with id %s", id);
            }
        }, app.util.JsonUtil.json());
    };

}
