/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Incidencia;
import objetos.Region;
import objetos.Usuario;
import utils.Constantes;

/**
 *
 * @author ivanc
 */
public class ConexionBBDD {
    
    private java.sql.Connection Conexion;
    private java.sql.Statement Senntencia_SQL;
    private java.sql.ResultSet Conj_registros;

    public ConexionBBDD() {
    }
    
    public void abrirConexion(){
        try {
            String controlador = "com.mysql.jdbc.Driver";
            Class.forName(controlador).newInstance();
            String URL_BD = "jdbc:mysql://localhost/" + Constantes.bbdd;
            Conexion = java.sql.DriverManager.getConnection(URL_BD, Constantes.usuariobd, Constantes.passwdbd);
            Senntencia_SQL = Conexion.createStatement();
            System.out.println("Conexion realizada con exito");
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void cerrarConexion(){
        try {
            this.Conexion.close();
            System.out.println("BBDD conexion cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int obtenerDatosTabla(String nombre_tabla){
        int codigo = 0;
        String select = "SELECT * FROM " +nombre_tabla;
        try {
            Conj_registros = Senntencia_SQL.executeQuery(select);
        } catch (SQLException ex) {
            codigo = ex.getErrorCode();
        }
        return codigo;
    }
    
    public void mostrarFilaActual(){
        int i, Num_Cols;
        try {
            Num_Cols = Conj_registros.getMetaData().getColumnCount();
            for (i = 1; i <= Num_Cols; i++) {
                System.out.println(Conj_registros.getString(i));
            }
        } catch (SQLException ex) {
        }
    }
    
    public void mostrarTabla(String tabla){
        try {
            obtenerDatosTabla(tabla);
            while (Conj_registros.next()){
                mostrarFilaActual();
            }
        } catch (SQLException ex) {}
    }
    
    public int modificarDato(String tabla, String campo, String where, String Nuevo_Nombre) {
        int cod = 0;
        String Sentencia = "UPDATE " + tabla + " SET " + campo + " = '" + Nuevo_Nombre + "' WHERE " + where;        
        try {
            Senntencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException ex) {
            cod = ex.getErrorCode();
        }
        return cod;
    }
    
    public int insertarUsuario(String nick, String email, String pass, String key, int rol){
        String insert = "INSERT INTO " + Constantes.TablaUsuarios + " VALUES ('" + nick + "'," + "'" + email + "'," + "'" + pass + "'," + "'" + key + "'," + "'" + rol + "')";
        int cod = 0;
        try {
            Senntencia_SQL.executeUpdate(insert);
        } catch (SQLException sq) {
            cod = sq.getErrorCode();
        }
        return cod;
    }
    
    public int borrarDato(String tabla, String where){
        int cod = 0;
        String Sentencia = "DELETE FROM " + tabla + " WHERE " + where;
        try {
            Senntencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException ex) {
            cod = ex.getErrorCode();
        }
        return cod;
    }
    
    public Usuario getUsuario(String where) throws SQLException {
        String sentencia = "SELECT * from " + Constantes.TablaUsuarios + " WHERE " + where;        
        ResultSet usuarios = Senntencia_SQL.executeQuery(sentencia);
        if (usuarios.next()) {
            Usuario u = new Usuario();
            u.setEmail(usuarios.getString(Constantes.usuariosEmail));
            u.setNombre(usuarios.getString(Constantes.usuariosNombre));
            u.setPassresumida(usuarios.getBytes(Constantes.usuariosPass));
            //u.setClavePublicaUsuario(usuarios.getBytes(Constantes.usuariosClave));
            u.setRol(usuarios.getInt(Constantes.usuariosRol));
            return u;
        } else {
            return null;
        }
    }
    
    public Region getRegion(String where) throws SQLException{
        String sentencia = "SELECT * from " + Constantes.TablaRegiones + " WHERE " + where;
        ResultSet regiones = Senntencia_SQL.executeQuery(sentencia);
        if (regiones.next()) {
            Region r = new Region();
            r.setRegion(regiones.getString(Constantes.regionesRegion));
            return r;
        }else{
            return null;
        }
    }

    public String obtenerValor(String tabla, String where, String campo) throws SQLException {
        String sentencia = "SELECT * from " + tabla + " WHERE " + where;
        Conj_registros = Senntencia_SQL.executeQuery(sentencia);
        if (Conj_registros.next()) {
            return Conj_registros.getString(campo);
        } else {
            return null;
        }
    }

    public boolean existeRegion(String where) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Incidencia> mostrarTodasLasIncidencias() throws SQLException{
        ArrayList<Incidencia> listaIncidencias=new ArrayList<>();
        String sentencia = "SELECT * from " + Constantes.TablaIncidencias;
        ResultSet incidencias = Senntencia_SQL.executeQuery(sentencia);
        while (incidencias.next()){
            Incidencia i = new Incidencia();
            i.setRegion(incidencias.getInt(Constantes.incidenciasRegion));
            i.setInfectado(incidencias.getString(Constantes.incidenciasInfectado));
            i.setFecha(incidencias.getString(Constantes.incidenciasFecha));
            listaIncidencias.add(i);
        }
        return listaIncidencias;
    }
    
}
