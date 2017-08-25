package purwakarta.kota.kuliner.model;

/**
 * Created by aomcenter on 8/25/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Blog {

    @SerializedName("id_blog")
    @Expose
    private String idblog;
    @SerializedName("judul_blog")
    @Expose
    private String judulblog;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdblog() {
        return idblog;
    }

    public void setIdblog(String idblog) {
        this.idblog = idblog;
    }

    public String getJudulblog() {
        return judulblog;
    }

    public void setJudulblog(String judulblog) {
        this.judulblog = judulblog;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}