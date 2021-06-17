/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.Serializable;

/**
 *
 * @author ivanc
 */
public class Incidencia implements Serializable{
    int region;
    String fecha;
    int infectados, fallecidos, dadosAlta;

    public Incidencia(int region, String fecha, int infectados, int fallecidos, int dadosAlta) {
        this.region = region;
        this.fecha = fecha;
        this.infectados = infectados;
        this.fallecidos = fallecidos;
        this.dadosAlta = dadosAlta;
    }

    public Incidencia() {
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getInfectados() {
        return infectados;
    }

    public void setInfectados(int infectados) {
        this.infectados = infectados;
    }

    public int getFallecidos() {
        return fallecidos;
    }

    public void setFallecidos(int fallecidos) {
        this.fallecidos = fallecidos;
    }

    public int getDadosAlta() {
        return dadosAlta;
    }

    public void setDadosAlta(int dadosAlta) {
        this.dadosAlta = dadosAlta;
    }

    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
