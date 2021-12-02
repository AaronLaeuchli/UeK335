package ch.zli.aal.buyit.model;

import android.os.Parcel;

import java.io.Serializable;

public class Store implements Serializable {

    private String storeName;

    public Store(){

    }

    public Store(String storeName){
        this.storeName = storeName;
    }

    protected Store(Parcel in) {
        storeName = in.readString();
    }



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


}
