/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author ivanc
 */
public class Claves {
    private PrivateKey privada;
    private PublicKey publica, agena;

    public Claves() {
    }

    public Claves(PrivateKey privada, PublicKey publica, PublicKey agena) {
        this.privada = privada;
        this.publica = publica;
        this.agena = agena;
    }

    public PrivateKey getPrivada() {
        return privada;
    }

    public void setPrivada(PrivateKey privada) {
        this.privada = privada;
    }

    public PublicKey getPublica() {
        return publica;
    }

    public void setPublica(PublicKey publica) {
        this.publica = publica;
    }

    public PublicKey getOtroExtremo() {
        return agena;
    }

    public void setOtroExtremo(PublicKey otroExtremo) {
        this.agena = otroExtremo;
    }
}
