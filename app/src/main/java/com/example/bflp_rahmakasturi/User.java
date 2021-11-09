package com.example.bflp_rahmakasturi;

public class User {
    public  String nama,username,alamat,tglLahir,tiket,harga;

    public User(){

    }
    public User(String nama, String username,String alamat, String tglLahir){
        this.nama=nama;
        this.username=username;
        this.alamat=alamat;
        this.tglLahir=tglLahir;
    }
    public User(String nama, String username,String alamat, String tglLahir,String tiket,String harga){
        this.nama=nama;
        this.username=username;
        this.alamat=alamat;
        this.tglLahir=tglLahir;
        this.tiket=tiket;
        this.harga=harga;
    }


}
