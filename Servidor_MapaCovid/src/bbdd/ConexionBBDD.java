/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
            String controlador = "org.mariadb.jdbc.Driver";
            Class.forName(controlador).newInstance();
            String URL_BD = "jdbc:mysql://localhost/" + Constantes.bbdd;
            Conexion = java.sql.DriverManager.getConnection(URL_BD, Constantes.usuariobd, Constantes.passwbd);
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
    
}
