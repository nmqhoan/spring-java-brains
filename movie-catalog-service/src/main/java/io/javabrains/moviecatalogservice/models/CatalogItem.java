package io.javabrains.moviecatalogservice.models;

public class CatalogItem {
    private  String name;
    private  String desc;
    private  int rating;
    private String serviceId;

    public CatalogItem(String name, String desc, int rating) {
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }

    public CatalogItem(String name, String desc, int rating, String serviceId) {
        this.name = name;
        this.desc = desc;
        this.rating = rating;
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
