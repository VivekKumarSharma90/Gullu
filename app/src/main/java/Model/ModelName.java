package Model;

/**
 * Created by android2 on 18/4/16.
 */
public class ModelName {
    String name;
    String address;
    String product_name;
    String product_old_price;
    String product_new_price;
    String image_url;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_old_price() {
        return product_old_price;
    }

    public void setProduct_old_price(String product_old_price) {
        this.product_old_price = product_old_price;
    }

    public String getProduct_new_price() {
        return product_new_price;
    }

    public void setProduct_new_price(String product_new_price) {
        this.product_new_price = product_new_price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
