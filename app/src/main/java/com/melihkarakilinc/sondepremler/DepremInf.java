package com.melihkarakilinc.sondepremler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DepremInf implements Serializable {

    @SerializedName("tarih")
    @Expose
    private String tarih;
    @SerializedName("saat")
    @Expose
    private String saat;
    @SerializedName("enlem")
    @Expose
    private String enlem;
    @SerializedName("boylam")
    @Expose
    private String boylam;
    @SerializedName("derinlik")
    @Expose
    private String derinlik;
    @SerializedName("siddet")
    @Expose
    private String siddet;
    @SerializedName("yer")
    @Expose
    private String yer;
    @SerializedName("tip")
    @Expose
    private String tip;

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getEnlem() {
        return enlem;
    }

    public void setEnlem(String enlem) {
        this.enlem = enlem;
    }

    public String getBoylam() {
        return boylam;
    }

    public void setBoylam(String boylam) {
        this.boylam = boylam;
    }

    public String getDerinlik() {
        return derinlik;
    }

    public void setDerinlik(String derinlik) {
        this.derinlik = derinlik;
    }

    public String getSiddet() {
        return siddet;
    }

    public void setSiddet(String siddet) {
        this.siddet = siddet;
    }

    public String getYer() {
        return yer;
    }

    public void setYer(String yer) {
        this.yer = yer;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

}