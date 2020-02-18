package com.dscunair.rc02;

import android.os.Parcel;
import android.os.Parcelable;

public class modelData implements Parcelable {
    private String nama;
    private String sinopsis;
    private int poster;
    private String photo;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.sinopsis);
        dest.writeInt(this.poster);
        dest.writeString(this.photo);
    }

    public modelData() {
    }

    protected modelData(Parcel in) {
        this.nama = in.readString();
        this.sinopsis = in.readString();
        this.poster = in.readInt();
        this.photo = in.readString();
    }

    public static final Creator<modelData> CREATOR = new Creator<modelData>() {
        @Override
        public modelData createFromParcel(Parcel source) {
            return new modelData(source);
        }

        @Override
        public modelData[] newArray(int size) {
            return new modelData[size];
        }
    };
}
