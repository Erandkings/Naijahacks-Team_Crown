package com.example.eduvite;

public class ModelClass {
    public String mCourseName;
    public String mImage;

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
