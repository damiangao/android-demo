package com.example.damian.nbtrade;

public class Goods {
    private String name;
    private int category;
    private Double price;
    private String describe;
    private int imageId;

    public Goods(String name, int category, Double price, String describe, int imageId) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.describe = describe;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
