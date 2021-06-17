/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es;

import ayuda.Constantes;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Claves;
import objetos.Escritor;
import objetos.Incidencia;
import objetos.Region;
import objetos.Usuario;
import objetos.Usuario_b;
import seguridad.Seguridad;

/**
 *
 * @author ivanc
 */
class HiloServidor extends Thread {

    private Socket cliente;
    private ConexionBBDD conexion;
    private Escritor e;
    private Claves claves;
    //private Usuario conectado;
    private Usuario_b conectado;

    public HiloServidor(Socket cliente) throws Exception {
        this.cliente = cliente;
        this.conexion = new ConexionBBDD();
        Claves clvs = new Claves();
        Escritor e = new Escritor(cliente, clvs);
        gestionClaves(clvs, e);
        this.claves = clvs;
        this.e = e;
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
                        //registrar();
                        registrarB();
                        break;
                    case Constantes.LOGEAR:
                        //loggear();
                        loggearB();
                        break;
                    case Constantes.GET_USER:
                        //getUser();
                        getUserB();
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
                        //cargarUsuarios();
                        cargarUsuariosB();
                        break;
                    case Constantes.BORRAR_USUARIO:
                        borrarUsuario();
                        break;
                    case Constantes.ACTIVAR_USUARIO:
                        activarDesactivar(true);
                        break;
                    case Constantes.DESACTIVAR_USUARIO:
                        activarDesactivar(false);
                        break;
                    case Constantes.CARGAR_REGIONES:
                        cargarRegiones();
                        break;
                    case Constantes.ADD_REGION:
                        addRegion();
                        break;
                    case Constantes.BORRAR_REGION:
                        delRegion();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                cliente.close();
            }catch (IOException ex){
                Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void registrar() throws Exception {
        Usuario u = (Usuario) e.leer();
        conexion.abrirConexion();
        conexion.insertarUsuario(u.getEmail(), u.getNombre(), u.getRol(), u.getPassresumida(), u.getClavePrivadaUsuario(), u.getClavePublicaUsuario(), u.isActivo());
        conexion.cerrarConexion();
        e.escribir(true);
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
    
    private String where(String campo, String comparador, String valor){
        return campo + " " + comparador + " '" + valor + "' ";
    }
    private String whereBoolean(String campo, String comparador, boolean valor){
        return campo + " " + comparador + " '" + valor + "' ";
    }
    
    private void loggear() throws Exception {
        Usuario u = (Usuario) e.leer();
        String email = u.getEmail();
        String pass = Seguridad.desencriptarAsimetrico(u.getPassresumida(), u.getClavePrivadaUsuario());
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
        //this.conectado = u;
        e.escribir(u);
    }

    private void saveRegion() throws Exception {
        Region r = (Region) e.leer();
        conexion.abrirConexion();
        if (!conexion.existeRegion(where(Constantes.regionesRegion, "=", r.getRegion()))) {
            conexion.insertarRegion(r.getRegion());
        }else{
            conexion.modRegion(r);
        }
        conexion.cerrarConexion();
        e.escribir(true);
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
    
    private void activarUsuario() throws Exception {
        Usuario u = (Usuario) e.leer();
        boolean activo = u.isActivo();
        conexion.abrirConexion();
        if (activo){
            conexion.modificarDato(Constantes.TablaUsuarios, Constantes.usuariosActivo, whereBoolean(Constantes.usuariosActivo, "=", activo), "false");
        }else{
            conexion.modificarDato(Constantes.TablaUsuarios, Constantes.usuariosActivo, whereBoolean(Constantes.usuariosActivo, "=", activo), "true");
        }
        
        conexion.cerrarConexion();
    }

    private void desactivarUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void cargarRegiones() throws Exception {
        conexion.abrirConexion();
        ArrayList<Region> regiones = conexion.mostrarTodasLasRegiones();
        for(Region inc: regiones){
            e.escribir(true);
            e.escribir(inc);
        }
        e.escribir(false);
        conexion.cerrarConexion();
    }

    private void addRegion() throws Exception {
        Region r = (Region) e.leer();
        conexion.abrirConexion();
        conexion.insertarRegion(r.getRegion());
        conexion.cerrarConexion();
        e.escribir(true);
    }

    private void registrarB() throws Exception {
        Usuario_b u = (Usuario_b) e.leer();
        conexion.abrirConexion();
        conexion.insertarUsuarioB(u.getNombre(), u.getEmail(),u.getRol(), Seguridad.Hexadecimal(u.getPassresumida()), u.isActivo());
        conexion.cerrarConexion();
        e.escribir(true);
    }

    private void getUserB() throws Exception {
        String id = (String) e.leer();
        conexion.abrirConexion();
        Usuario_b u = conexion.getUsuarioB(where(Constantes.usuariosBEmail, "=", id));
        conexion.cerrarConexion();
        this.conectado = u;
        e.escribir(u);
    }

    private void loggearB() throws Exception {
        Usuario_b u = (Usuario_b) e.leer();
        String email = u.getEmail();
        String pass = Seguridad.Hexadecimal(u.getPassresumida());
        conexion.abrirConexion();
        String passEnBDD = conexion.obtenerValor(Constantes.TablaUsuariosB, where(Constantes.usuariosBEmail, "=", email), Constantes.usuariosBPass);
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

    private void cargarUsuariosB() throws Exception {
        conexion.abrirConexion();
        ArrayList<Usuario_b> usuarios = conexion.listaUsuariosB(conectado);
        for (Usuario_b u : usuarios) {
            e.escribir(true);
            e.escribir(u);
        }
        e.escribir(false);
        conexion.cerrarConexion();
    }

    private void activarDesactivar(boolean activar) throws Exception {
        conexion.abrirConexion();
        String email = (String) e.leer();
        String activo;
        if (activar){
            activo = "true";
        }else{
            activo = "false";
        }
        conexion.modificarDato(Constantes.TablaUsuariosB, Constantes.usuariosBActivo, where(Constantes.usuariosBEmail, "=", email), activo);
        conexion.cerrarConexion();
    }

    private void delRegion() throws Exception {
        String nombreREgion = (String) e.leer();
        conexion.abrirConexion();
        conexion.borrarDato(Constantes.TablaRegiones, where(Constantes.regionesRegion, "=", nombreREgion));
        conexion.cerrarConexion();
    }

}
