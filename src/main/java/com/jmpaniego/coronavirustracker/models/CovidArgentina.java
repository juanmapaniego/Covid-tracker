package com.jmpaniego.coronavirustracker.models;

import java.io.Serializable;
import java.util.Date;

public class CovidArgentina {
    private Integer id; //Numero de caso
    private Sexo sexo; //Sexo
    private String edad; //Edad_actual
    private String pais_residencia; //País de residencia
    private String provincia_residencia; //Provincia de residencia
    private Integer prov_residencia_id; //Código de Indec de la provincia
    private String provincia_carga; //Provincia de establecimiento de carga
    private Integer prov_carga_id; //Código de Indec provincia de establecimiento de carga
    private String fis; //Fecha de inicio de síntomas
    private String antecedente_epidemiologico; //Antecedente Epidemiológico
    private Date fecha_apertura; //Fecha de apertura del caso
    private Integer sepi_apertura; //Semana Epidemiológica de fecha de apertura
    private Date fecha_internacion; //Fecha de internación
    private String cuidado_intensivo; //Indicación si estuvo en cuidado intensivo
    private Date fecha_cui_intensivo; //Fecha de ingreso a cuidado intensivo en el caso de corresponder
    private String fallecido; //Indicación de fallecido
    private Date fecha_fallecimiento; //Fecha de fallecimiento en el caso de corresponder
    private String asist_resp_mecanica; //Indicación si requirió asistencia respiratoria mecánica
    private String origen_financiamiento; //Origen de financiamiento
    private String clasificacion; //Clasificación manual del registro
    private String clasificacion_resumen; //Texto (string)Clasificación del caso
    private Date fecha_diagnostico;  //Fecha de diagnóstico

    public CovidArgentina() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPais_residencia() {
        return pais_residencia;
    }

    public void setPais_residencia(String pais_residencia) {
        this.pais_residencia = pais_residencia;
    }

    public String getProvincia_residencia() {
        return provincia_residencia;
    }

    public void setProvincia_residencia(String provincia_residencia) {
        this.provincia_residencia = provincia_residencia;
    }

    public Integer getProv_residencia_id() {
        return prov_residencia_id;
    }

    public void setProv_residencia_id(Integer prov_residencia_id) {
        this.prov_residencia_id = prov_residencia_id;
    }

    public String getProvincia_carga() {
        return provincia_carga;
    }

    public void setProvincia_carga(String provincia_carga) {
        this.provincia_carga = provincia_carga;
    }

    public Integer getProv_carga_id() {
        return prov_carga_id;
    }

    public void setProv_carga_id(Integer prov_carga_id) {
        this.prov_carga_id = prov_carga_id;
    }

    public String getFis() {
        return fis;
    }

    public void setFis(String fis) {
        this.fis = fis;
    }

    public String getAntecedente_epidemiologico() {
        return antecedente_epidemiologico;
    }

    public void setAntecedente_epidemiologico(String antecedente_epidemiologico) {
        this.antecedente_epidemiologico = antecedente_epidemiologico;
    }

    public Date getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(Date fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public Integer getSepi_apertura() {
        return sepi_apertura;
    }

    public void setSepi_apertura(Integer sepi_apertura) {
        this.sepi_apertura = sepi_apertura;
    }

    public Date getFecha_internacion() {
        return fecha_internacion;
    }

    public void setFecha_internacion(Date fecha_internacion) {
        this.fecha_internacion = fecha_internacion;
    }

    public String getCuidado_intensivo() {
        return cuidado_intensivo;
    }

    public void setCuidado_intensivo(String cuidado_intensivo) {
        this.cuidado_intensivo = cuidado_intensivo;
    }

    public Date getFecha_cui_intensivo() {
        return fecha_cui_intensivo;
    }

    public void setFecha_cui_intensivo(Date fecha_cui_intensivo) {
        this.fecha_cui_intensivo = fecha_cui_intensivo;
    }

    public String getFallecido() {
        return fallecido;
    }

    public void setFallecido(String fallecido) {
        this.fallecido = fallecido;
    }

    public Date getFecha_fallecimiento() {
        return fecha_fallecimiento;
    }

    public void setFecha_fallecimiento(Date fecha_fallecimiento) {
        this.fecha_fallecimiento = fecha_fallecimiento;
    }

    public String getAsist_resp_mecanica() {
        return asist_resp_mecanica;
    }

    public void setAsist_resp_mecanica(String asist_resp_mecanica) {
        this.asist_resp_mecanica = asist_resp_mecanica;
    }

    public String getOrigen_financiamiento() {
        return origen_financiamiento;
    }

    public void setOrigen_financiamiento(String origen_financiamiento) {
        this.origen_financiamiento = origen_financiamiento;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getClasificacion_resumen() {
        return clasificacion_resumen;
    }

    public void setClasificacion_resumen(String clasificacion_resumen) {
        this.clasificacion_resumen = clasificacion_resumen;
    }

    public Date getFecha_diagnostico() {
        return fecha_diagnostico;
    }

    public void setFecha_diagnostico(Date fecha_diagnostico) {
        this.fecha_diagnostico = fecha_diagnostico;
    }

    @Override
    public String toString() {
        return this.id + " " + this.sexo;
    }
}
