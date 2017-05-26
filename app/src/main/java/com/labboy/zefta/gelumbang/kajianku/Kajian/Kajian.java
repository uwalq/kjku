package com.labboy.zefta.gelumbang.kajianku.Kajian;

/**
 * Created by acer on 28/09/2016.
 */
public class Kajian {
    private String id_kajian;
    private String jdl_kajian;
    private String pengisi;
    private String tanggal;
    private String waktu;
    private String longitude;
    private String latitude;
    private String alamat;

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    private String kecamatan;
    private String deskripsi;
    private String penyelenggara;
    private String foto;
    private String email;
    private String nohp;

    public String getId_kajian() {
        return id_kajian;
    }

    public void setId_kajian(String id_kajian) {
        this.id_kajian = id_kajian;
    }

    public String getJdl_kajian() {
        return jdl_kajian;
    }

    public void setJdl_kajian(String jdl_kajian) {
        this.jdl_kajian = jdl_kajian;
    }

    public String getPengisi() {
        return pengisi;
    }

    public void setPengisi(String pengisi) {
        this.pengisi = pengisi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }

    public void setPenyelenggara(String penyelenggara) {
        this.penyelenggara = penyelenggara;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }
}
