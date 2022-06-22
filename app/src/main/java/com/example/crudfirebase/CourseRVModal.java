package com.example.crudfirebase;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseRVModal implements Parcelable {
    private String name;
    private String description;
    private String price;
    private String suitedFor;
    private String imageLink;
    private String link;
    private String ID;

    public CourseRVModal(){

    }

    public CourseRVModal(String name, String description, String price, String suitedFor, String imageLink, String link, String ID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.suitedFor = suitedFor;
        this.imageLink = imageLink;
        this.link = link;
        this.ID = ID;
    }

    protected CourseRVModal(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readString();
        suitedFor = in.readString();
        imageLink = in.readString();
        link = in.readString();
        ID = in.readString();
    }

    public static final Creator<CourseRVModal> CREATOR = new Creator<CourseRVModal>() {
        @Override
        public CourseRVModal createFromParcel(Parcel in) {
            return new CourseRVModal(in);
        }

        @Override
        public CourseRVModal[] newArray(int size) {
            return new CourseRVModal[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSuitedFor() {
        return suitedFor;
    }

    public void setSuitedFor(String suitedFor) {
        this.suitedFor = suitedFor;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(suitedFor);
        parcel.writeString(imageLink);
        parcel.writeString(link);
        parcel.writeString(ID);
    }
}
