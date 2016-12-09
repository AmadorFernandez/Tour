package com.amador.tour.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by amador on 8/12/16.
 */

public class InterestPoint implements Parcelable {

    private int id;
    private String name;
    private String category;
    private int icon;
    private float score;
    public static final Comparator<InterestPoint> ORDERBY_CATEGORY = new Comparator<InterestPoint>() {
        @Override
        public int compare(InterestPoint interestPoint, InterestPoint t1) {
            return interestPoint.getCategory().compareToIgnoreCase(t1.getCategory());
        }
    };
    public static final Comparator<InterestPoint> ORDERBY_NAME = new Comparator<InterestPoint>() {
        @Override
        public int compare(InterestPoint interestPoint, InterestPoint t1) {

            return interestPoint.getName().compareToIgnoreCase(t1.getName());

        }
    };
    public static final Comparator<InterestPoint> ORDERBY_SCORE = new Comparator<InterestPoint>() {
        @Override
        public int compare(InterestPoint interestPoint, InterestPoint t1) {

            int result = 0;

            if(interestPoint.getScore() == t1.getScore()) {

                result = interestPoint.getName().compareToIgnoreCase(t1.getName());

            }else if(interestPoint.getScore() < t1.getScore()){

                result = 1;

            }else {

                result = -1;
            }

            return result;
        }
    };


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public InterestPoint(int id, String name, String category, int icon, float score) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.icon = icon;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {

        if(score > 5){

            score = 5;

        }else if(score < 0){

            score = 0;
        }

        this.score = score;
    }


    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {

        boolean result = false;
        InterestPoint point;

        if(obj != null){

            if(obj instanceof InterestPoint){

                point = (InterestPoint)obj;

                result = this.id == point.getId() && this.name == point.name;

            }
        }

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.category);
        dest.writeInt(this.icon);
        dest.writeFloat(this.score);
    }

    protected InterestPoint(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.category = in.readString();
        this.icon = in.readInt();
        this.score = in.readFloat();
    }

    public static final Creator<InterestPoint> CREATOR = new Creator<InterestPoint>() {
        @Override
        public InterestPoint createFromParcel(Parcel source) {
            return new InterestPoint(source);
        }

        @Override
        public InterestPoint[] newArray(int size) {
            return new InterestPoint[size];
        }
    };
}
