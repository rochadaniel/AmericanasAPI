package danielrocha.americanasapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danielrocha on 27/11/16.
 */

public class ProductModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("seller")
    @Expose
    private String seller;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("price")
    @Expose
    private Float price;
    @SerializedName("queryString")
    @Expose
    private String queryString;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The seller
     */
    public String getSeller() {
        return seller;
    }

    /**
     *
     * @param seller
     * The seller
     */
    public void setSeller(String seller) {
        this.seller = seller;
    }

    /**
     *
     * @return
     * The img
     */
    public String getImg() {
        return img;
    }

    /**
     *
     * @param img
     * The img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     *
     * @return
     * The price
     */
    public Float getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The queryString
     */
    public String getQueryString() {
        return queryString;
    }

    /**
     *
     * @param queryString
     * The queryString
     */
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

}
