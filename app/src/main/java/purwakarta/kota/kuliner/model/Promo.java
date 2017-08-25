package purwakarta.kota.kuliner.model;

/**
 * Created by aomcenter on 8/25/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promo {

    @SerializedName("id_promo")
    @Expose
    private String idPromo;
    @SerializedName("judul_promo")
    @Expose
    private String judulPromo;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(String idPromo) {
        this.idPromo = idPromo;
    }

    public String getJudulPromo() {
        return judulPromo;
    }

    public void setJudulPromo(String judulPromo) {
        this.judulPromo = judulPromo;
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