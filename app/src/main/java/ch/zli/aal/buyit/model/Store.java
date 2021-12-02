package ch.zli.aal.buyit.model;

import android.os.Parcel;

import java.io.Serializable;
import java.util.List;

public class Store implements Serializable {

    private String storeName;
    private List<Product> products;
    public Store(String storeName, List<Product> products) {
        this.storeName = storeName;
        this.products = products;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
