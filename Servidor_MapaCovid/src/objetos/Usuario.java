/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ivanc
 */
public class Usuario {
    
    private String email, nick;
    private byte[] passResumida, clave;
    private int rol;

    public Usuario(String email, String nick, byte[] passResumida, byte[] clave, int rol) {
        this.email = email;
        this.nick = nick;
        this.passResumida = passResumida;
        this.clave = clave;
        this.rol = rol;
    }

    public Usuario(String email, byte[] passResumida) {
        this.email = email;
        this.passResumida = passResumida;
    }

    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public byte[] getPassResumida() {
        return passResumida;
    }

    public void setPassResumida(byte[] passResumida) {
        this.passResumida = passResumida;
    }

    public byte[] getClave() {
        return clave;
    }

    public void setClave(byte[] clave) {
        this.clave = clave;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{email = "+email+" nick = "+nick+" passCifrada = "+passResumida+" rol = "+rol+"}";
    }
    
    public boolean validarEmail(String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }
    
}
