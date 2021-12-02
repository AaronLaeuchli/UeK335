package ch.zli.aal.buyit.model;

import java.io.Serializable;

public class Product implements Serializable {

    private String productName;
    private String path;

    public Product(String productName, String path) {
        this.productName = productName;
        this.path = path;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
