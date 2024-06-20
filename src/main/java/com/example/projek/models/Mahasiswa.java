package com.example.projek.models;

public class Mahasiswa {
    private String nim;
    private String nama;
    private int usia;

    public Mahasiswa() {

    }

    public Mahasiswa(String nim, String nama, int usia) {
        this.nim = nim;
        this.nama = nama;
        this.usia = usia;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public int getUsia() {
        return usia;
    }
}
