package com.mhraju.deviceinfo.Model;

public class Memory {

    private String title;
    private String usedSpace;
    private String freeSpace;
    private String totalSpace;
    private int image;

    public Memory(String title, String usedSpace, String freeSpace, String totalSpace, int image) {
        this.title = title;
        this.usedSpace = usedSpace;
        this.freeSpace = freeSpace;
        this.totalSpace = totalSpace;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getUsedSpace() {
        return usedSpace;
    }

    public String getFreeSpace() {
        return freeSpace;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public int getImage() {
        return image;
    }
}
