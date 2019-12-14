package com.example.eduvite;

public class ModelClass {
    private String mCourseName;
    private String mImage;

    public ModelClass() {
    }

    public ModelClass(String CourseName, String Image) {
        this.mCourseName = CourseName;
        this.mImage = Image;
    }

    public String getmCourseName() {
        return mCourseName;
    }

    public void setmCourseName(String mCourseName) {
        this.mCourseName = mCourseName;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
