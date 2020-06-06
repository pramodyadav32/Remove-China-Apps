package com.example.apps;

public class AppsEvent {

    public static final int HEADER_TYPE = 0;
    public static final int ITEM_TYPE = 1;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private String name;
    private String path;
    private int image;
    private int mType;

    public AppsEvent(String name, String path, int image, int type) {
        this.name = name;
        this.path = path;
        this.image = image;
        this.mType = type;
    }


    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }
}
