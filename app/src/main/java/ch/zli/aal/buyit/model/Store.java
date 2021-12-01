package ch.zli.aal.buyit.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {

    private String storeName;

    public Store(){

    }

    public Store(String storeName){
        this.storeName = storeName;
    }

    protected Store(Parcel in) {
        storeName = in.readString();
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return storeName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(storeName);
    }
}
