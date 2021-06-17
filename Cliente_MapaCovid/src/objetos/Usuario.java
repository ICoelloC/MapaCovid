/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKey;

/**
 *
 * @author ivanc
 */
public class Usuario implements Serializable{

    private String email, nombre;
    private int rol;
    private byte[] passresumida;
    PrivateKey clavePrivadaUsuario;
    PublicKey clavePublicaUsuario;
    boolean activo;

    public Usuario(String email, String nombre, int rol, byte[] passresumida, PrivateKey clavePrivadaUsuario, PublicKey clavePublicaUsuario, Boolean activo) {
        this.email = email;
        this.nombre = nombre;
        this.rol = rol;
        this.passresumida = passresumida;
        this.clavePrivadaUsuario = clavePrivadaUsuario;
        this.clavePublicaUsuario = clavePublicaUsuario;
        this.activo = activo;
    }

    public Usuario(String email, byte[] passresumida) {
        this.email = email;
        this.passresumida = passresumida;
    }

    public Usuario() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public byte[] getPassresumida() {
        return passresumida;
    }

    public void setPassresumida(byte[] passresumida) {
        this.passresumida = passresumida;
    }

    public PrivateKey getClavePrivadaUsuario() {
        return clavePrivadaUsuario;
    }

    public void setClavePrivadaUsuario(PrivateKey clavePrivadaUsuario) {
        this.clavePrivadaUsuario = clavePrivadaUsuario;
    }

    public PublicKey getClavePublicaUsuario() {
        return clavePublicaUsuario;
    }

    public void setClavePublicaUsuario(PublicKey clavePublicaUsuario) {
        this.clavePublicaUsuario = clavePublicaUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
