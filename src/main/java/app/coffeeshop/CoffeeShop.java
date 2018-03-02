package app.coffeeshop;

public class CoffeeShop {
    public String id;
    public String name;
    public String address;
    public String latitude;
    public String longitude;

    public CoffeeShop(String id, String name, String addr, String lat, String longitude) {
        this.id = id;
        this.name = name;
        this.address = addr;
        this.latitude = lat;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

