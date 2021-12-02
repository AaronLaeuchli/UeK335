package ch.zli.aal.buyit.model;

import java.io.Serializable;

public class Product implements Serializable {

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



}
