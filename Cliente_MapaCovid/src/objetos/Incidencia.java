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
    String infectado, fecha;

    public Incidencia(int region, String infectado, String fecha) {
        this.region = region;
        this.infectado = infectado;
        this.fecha = fecha;
    }

    public Incidencia() {
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getInfectado() {
        return infectado;
    }

    public void setInfectado(String infectado) {
        this.infectado = infectado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
