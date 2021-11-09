package com.example.bflp_rahmakasturi.Model;

public class DataArtis {




    String profilband;
    String image;
    String tentang;
    String videoyt;
     public DataArtis(){

     }
    public DataArtis(String profilband,String image,String videoyt){
        this.profilband=profilband;
        this.image=image;
        this.videoyt=videoyt;
    }
    public String getProfilband() {
        return profilband;
    }

    public void setProfilband(String profilband) {
        this.profilband = profilband;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getVideoyt() {
        return videoyt;
    }

    public void setVideoyt(String videoyt) {
        this.videoyt = videoyt;
    }

    public String getTentang() {
        return tentang;
    }

    public void setTentang(String tentang) {
        this.tentang = tentang;
    }


}
