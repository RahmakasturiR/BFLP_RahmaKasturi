package com.example.bflp_rahmakasturi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
private static final  String USERNAME = "username";
private static final String NAMA = "nama";
private static final String UUID = "uuid";
private static final String ALAMAT = "alamat";
private static final String TGLLAHIR = "tgllahir";
private static final String TIKET = "tiket";
private static final String HARGA = "harga";


    private static SharedPreferences getSharedReferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void SetNama(Context context, String nama) {
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putString(NAMA, nama);
        editor.apply();
    }
    public static void SetUsername(Context context, String username) {
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putString(USERNAME, username);
        editor.apply();
    }
    public static void setUuid(Context context, String uuid) {
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putString(UUID, uuid);
        editor.apply();
    }
    public static void setAlamat(Context context, String alamat) {
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putString(ALAMAT, alamat);
        editor.apply();
    }
    public static void setTgllahir(Context context, String tgllahir) {
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putString(TGLLAHIR, tgllahir);
        editor.apply();
    }
    public static void setTiket(Context context, String tiket) {
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putString(TIKET, tiket);
        editor.apply();
    }
    public static void setHarga(Context context, String harga) {
        SharedPreferences.Editor editor = getSharedReferences(context).edit();
        editor.putString(HARGA, harga);
        editor.apply();
    }

    public static String getUSERNAME(Context context) {
        return getSharedReferences(context).getString(USERNAME, "");
    }

    public static String getNAMA(Context context) {
        return getSharedReferences(context).getString(NAMA, "");
    }
    public static String getUuid(Context context) {
        return getSharedReferences(context).getString(UUID, "");
    }
    public static String getAlamat(Context context) {
        return getSharedReferences(context).getString(ALAMAT, "");
    }
    public static String getTgllahir(Context context) {
        return getSharedReferences(context).getString(TGLLAHIR, "");
    }
    public static String getTiket(Context context) {
        return getSharedReferences(context).getString(TIKET, "");
    }
    public static String getHarga(Context context) {
        return getSharedReferences(context).getString(HARGA, "");
    }
}
