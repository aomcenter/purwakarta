package purwakarta.kota.kuliner.model;

/**
 * Created by aomcenter on 8/25/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id_event")
    @Expose
    private String idevent;
    @SerializedName("judul_event")
    @Expose
    private String judulevent;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("image")
    @Expose
    private String image;

    public String getIdevent() {
        return idevent;
    }

    public void setIdevent(String idevent) {
        this.idevent = idevent;
    }

    public String getJudulevent() {
        return judulevent;
    }

    public void setJudulevent(String judulevent) {
        this.judulevent = judulevent;
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