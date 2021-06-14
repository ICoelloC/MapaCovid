/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es;

import bbdd.ConexionBBDD;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;
import objetos.Claves;
import objetos.Escritor;
import objetos.Incidencia;
import objetos.Region;
import objetos.Usuario;
import seguridad.Seguridad;
import utils.Constantes;

/**
 *
 * @author ivanc
 */
class HiloServidor extends Thread {

    private Socket cliente;
    private ConexionBBDD conexion;
    private Escritor e;
    private Claves claves;
    private Usuario conectado;

    public HiloServidor(Socket cliente) throws Exception {
        this.cliente = cliente;
        this.conexion = new ConexionBBDD();
        Claves claves = new Claves();
        Escritor e = new Escritor(cliente, claves);
        gestionClaves(claves, e);
        this.claves = claves;
        this.e = e;
    }

    public HiloServidor() {
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        try {
            ConexionBBDD conex = new ConexionBBDD();
            Usuario u;
            while ((boolean) e.leer()) {
                int opc = (int) e.leer();
                switch (opc) {
                    case Constantes.REGISTRAR:
                        registrar();
                        break;
                    case Constantes.LOGEAR:
                        loggear();
                        break;
                    case Constantes.GET_USER:
                        getUser();
                        break;
                    case Constantes.SAVE_REGION:
                        saveRegion();
                        break;
                    case Constantes.GET_REGIONES:
                        getRegiones();
                        break;
                    case Constantes.COMPROBAR_PRIMERA:
                        comprobarPrimera();
                        break;
                    case Constantes.CAMBIAR_PASSWORD:
                        cambiarPassword();
                        break;
                    case Constantes.MODIFICAR_USUARIO:
                        modUsuario();
                        break;
                    case Constantes.CARGAR_INCIDENCIAS:
                        cargarIncidencias();
                        break;
                    case Constantes.ADD_INCIDENCIA:
                        addIncidencia();
                        break;
                    case Constantes.CARGAR_USUARIOS:
                        cargarUsuarios();
                        break;
                    case Constantes.BORRAR_USUARIO:
                        borrarUsuario();
                        break;
                    case Constantes.ACTIVAR_USUARIO:
                        activarUsuario();
                        break;
                    case Constantes.DESACTIVAR_USUARIO:
                        desactivarUsuario();
                        break;
                }
            }
        } catch (Exception e) {

        }
    }

    private void gestionClaves(Claves claves, Escritor e) throws NoSuchAlgorithmException, IOException, ClassNotFoundException {
        //Generamos ambas claves
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair par = keyGen.generateKeyPair();
        claves.setPrivada(par.getPrivate());
        claves.setPublica(par.getPublic());

        //Recibimos la clave del otro extremo
        claves.setOtroExtremo((PublicKey) e.ois().readObject());
        //Mandamos la clave publica al otro extremo
        e.oos().writeObject(claves.getPublica());
    }

    private void registrar() throws Exception {
        Usuario u = (Usuario) e.leer();
        conexion.abrirConexion();
        //conexion.insertarUsuario(u.getEmail(), u.getNombre(), Seguridad.Hexadecimal(u.getPassresumida()), u.getFechaNac(), u.getRol());
        conexion.cerrarConexion();
        e.escribir(true);
    }

    private void loggear() throws Exception {
        Usuario u = (Usuario) e.leer();
        String email = u.getEmail();
        String pass = Seguridad.Hexadecimal(u.getPassresumida());
        conexion.abrirConexion();
        String passEnBDD = conexion.obtenerValor(Constantes.TablaUsuarios, where(Constantes.usuariosEmail, "=", email), Constantes.usuariosPass);
        if (passEnBDD != null) {
            if (MessageDigest.isEqual(pass.getBytes(), passEnBDD.getBytes())) {
                e.escribir(true);
            } else {
                e.escribir(false);
            }
        } else {
            e.escribir(false);
        }
        conexion.cerrarConexion();
    }

    private void getUser() throws Exception {
        String id = (String) e.leer();
        conexion.abrirConexion();
        Usuario u = conexion.getUsuario(where(Constantes.usuariosEmail, "=", id));
        conexion.cerrarConexion();
        this.conectado = u;
        e.escribir(u);
    }

    private void saveRegion() throws Exception {
        Region r = (Region) e.leer();
        conexion.abrirConexion();
        if (!conexion.existeRegion(where(Constantes.regionesRegion, "=", r.getRegion()))) {

        }
    }

    private void getRegiones() throws Exception {
        String id = (String) e.leer();
        conexion.abrirConexion();
        Region r = conexion.getRegion(where(Constantes.regionesRegion, "=", id));
        conexion.cerrarConexion();
        e.escribir(r);
    }

    private void comprobarPrimera() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cambiarPassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void modUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarIncidencias() throws SQLException, Exception {
        conexion.abrirConexion();
        ArrayList<Incidencia> incidencias = conexion.mostrarTodasLasIncidencias();
        for(Incidencia inc: incidencias){
            e.escribir(true);
            e.escribir(inc);
        }
        e.escribir(false);
        conexion.cerrarConexion();
    }

    private void addIncidencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void borrarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String where(String campo, String comparador, String valor) {
        return campo + " " + comparador + " '" + valor + "'";
    }

    private void activarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void desactivarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
