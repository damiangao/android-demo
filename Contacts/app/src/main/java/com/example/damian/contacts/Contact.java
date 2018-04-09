package com.example.damian.contacts;

public class Contact {
    private String name;
    private int imageId;
    private String phone;
    private String email;

    public Contact(String name,String phone, String email, int imageId) {
        this.name = name;
        this.phone = phone;
        this.email =email;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
