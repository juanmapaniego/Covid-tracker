package com.jmpaniego.coronavirustracker.models;

public class CovidProvincia {
    private String nombre;
    private Integer sospechosos;
    private Integer confirmados;
    private Integer fallecidos;

    public CovidProvincia() {
    }

    public Integer getConfirmados() {
        return confirmados;
    }

    public void setConfirmados(Integer confirmados) {
        this.confirmados = confirmados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSospechosos() {
        return sospechosos;
    }

    public void setSospechosos(Integer sospechosos) {
        this.sospechosos = sospechosos;
    }

    public Integer getFallecidos() {
        return fallecidos;
    }

    public void setFallecidos(Integer fallecidos) {
        this.fallecidos = fallecidos;
    }
}
