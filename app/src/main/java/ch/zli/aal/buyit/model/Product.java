package ch.zli.aal.buyit.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private String productName;
    private Store storeName;

    public Product(){

    }

    public Product(String productName, Store storeName) {
        this.productName = productName;
        this.storeName = storeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Store getStoreName() {
        return storeName;
    }

    public void setStoreName(Store storeName) {
        this.storeName = storeName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productName);
        dest.writeParcelable(this.storeName, flags);
    }

    public void readFromParcel(Parcel source) {
        this.productName = source.readString();
        this.storeName = source.readParcelable(Store.class.getClassLoader());
    }

    protected Product(Parcel in) {
        this.productName = in.readString();
        this.storeName = in.readParcelable(Store.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
