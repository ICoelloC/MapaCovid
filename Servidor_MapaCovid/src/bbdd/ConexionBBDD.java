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
import ayuda.Constantes;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import objetos.Usuario_b;

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

    public void abrirConexion() {
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

    public void cerrarConexion() {
        try {
            this.Conexion.close();
            System.out.println("BBDD conexion cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int obtenerDatosTabla(String nombre_tabla) {
        int codigo = 0;
        String select = "SELECT * FROM " + nombre_tabla;
        try {
            Conj_registros = Senntencia_SQL.executeQuery(select);
        } catch (SQLException ex) {
            codigo = ex.getErrorCode();
        }
        return codigo;
    }

    public void mostrarFilaActual() {
        int i, Num_Cols;
        try {
            Num_Cols = Conj_registros.getMetaData().getColumnCount();
            for (i = 1; i <= Num_Cols; i++) {
                System.out.println(Conj_registros.getString(i));
            }
        } catch (SQLException ex) {
        }
    }

    public void mostrarTabla(String tabla) {
        try {
            obtenerDatosTabla(tabla);
            while (Conj_registros.next()) {
                mostrarFilaActual();
            }
        } catch (SQLException ex) {
        }
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

    public int insertarUsuario(String nick, String email, int rol, byte[] pass, PrivateKey clavePriv, PublicKey clavePub, boolean activo) {
        String insert = "INSERT INTO " + Constantes.TablaUsuarios + " VALUES (" + null + "'" + nick + "'," + "'" + email + "'," + "" + rol + "," + "'" + Arrays.toString(pass) + "'," + "'" + clavePriv + "', " + "'" + clavePriv + "', " + activo + ")";
        int cod = 0;
        try {
            Senntencia_SQL.executeUpdate(insert);
        } catch (SQLException sq) {
            cod = sq.getErrorCode();
        }
        return cod;
    }
    
    public int insertarIncidencia(int region, String fecha, int infectados, int fallecidos, int dadosAlta) {
        String insert = "INSERT INTO " + Constantes.TablaIncidencias + " VALUES (" + null + ", " + region +", '"+ fecha +"', "+infectados+", " + fallecidos +", "+dadosAlta+")";
        int cod = 0;
        try{
            Senntencia_SQL.executeUpdate(insert);
        }catch(SQLException sq){
            cod = sq.getErrorCode();
        }
        return cod;
    }

    public int insertarUsuarioB(String nick, String email, int rol, String pass, boolean activo) {
        if (rol == 2){
            activo = false;
        }else{
            activo = true;
        }   
        String insert = "INSERT INTO " + Constantes.TablaUsuariosB + " VALUES (" + null + ", '" + nick + "', '" + email + "', '" +pass+ "'," +rol+ ", "+activo+ ")";
        int cod = 0;
        try {
            Senntencia_SQL.executeUpdate(insert);
        } catch (SQLException sq) {
            cod = sq.getErrorCode();
        }
        return cod;
    }

    public int borrarDato(String tabla, String where) {
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

    public Region getRegion(String where) throws SQLException {
        String sentencia = "SELECT * from " + Constantes.TablaRegiones + " WHERE " + where;
        ResultSet regiones = Senntencia_SQL.executeQuery(sentencia);
        if (regiones.next()) {
            Region r = new Region();
            r.setRegion(regiones.getString(Constantes.regionesRegion));
            return r;
        } else {
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

    public int insertarRegion(String nombreRegion) {
        String Sentencia = "INSERT INTO " + Constantes.TablaRegiones + " VALUES ("+null+", '" + nombreRegion + "')";
        int cod = 0;
        try {
            Senntencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException sql) {
            cod = sql.getErrorCode();
        }
        return cod;
    }

    public boolean existeRegion(String where) throws SQLException {
        String sentencia = "SELECT * from " + Constantes.TablaRegiones + " WHERE " + where;
        Conj_registros = Senntencia_SQL.executeQuery(sentencia);
        if (Conj_registros.next()) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Incidencia> mostrarTodasLasIncidencias() throws SQLException {
        ArrayList<Incidencia> listaIncidencias = new ArrayList<>();
        String sentencia = "SELECT * from " + Constantes.TablaIncidencias;
        ResultSet incidencias = Senntencia_SQL.executeQuery(sentencia);
        while (incidencias.next()) {
            Incidencia i = new Incidencia();
            i.setRegion(incidencias.getInt(Constantes.incidenciasRegion));
            i.setFecha(incidencias.getString(Constantes.incidenciasFecha));
            i.setInfectados(incidencias.getInt(Constantes.incidenciasInfectados));
            i.setFallecidos(incidencias.getInt(Constantes.incidenciasFallecidos));
            i.setDadosAlta(incidencias.getInt(Constantes.incidenciasDadosAlta));
            listaIncidencias.add(i);
        }
        return listaIncidencias;
    }

    public void modRegion(Region r) {
        borrarDato(Constantes.TablaRegiones, (Constantes.regionesRegion + " = '" + r.getRegion() + "'"));
        insertarRegion(r.getRegion());
    }

    public ArrayList<Region> mostrarTodasLasRegiones() throws SQLException {
        ArrayList<Region> listaRegions = new ArrayList<>();
        String sentencia = "SELECT * from " + Constantes.TablaRegiones;
        ResultSet regiones = Senntencia_SQL.executeQuery(sentencia);
        while (regiones.next()) {
            Region r = new Region();
            r.setRegion(regiones.getString(Constantes.regionesRegion));
            listaRegions.add(r);
        }
        return listaRegions;
    }

    public Usuario_b getUsuarioB(String where) throws SQLException {
        String sentencia = "SELECT * from " + Constantes.TablaUsuariosB + " WHERE " + where;        
        ResultSet usuarios = Senntencia_SQL.executeQuery(sentencia);
        if (usuarios.next()) {
            Usuario_b u = new Usuario_b();
            u.setEmail(usuarios.getString(Constantes.usuariosBEmail));
            u.setNombre(usuarios.getString(Constantes.usuariosBNombre));
            u.setPassresumida(usuarios.getBytes(Constantes.usuariosBPass));
            u.setRol(usuarios.getInt(Constantes.usuariosRol));
            u.setActivo(usuarios.getBoolean(Constantes.usuariosBActivo));
            return u;
        } else {
            return null;
        }
    }

    public ArrayList<Usuario_b> listaUsuariosB(Usuario_b conectado) throws SQLException {
        ArrayList<Usuario_b> ListaUsuario_b = new ArrayList<>();
        String sentencia = "SELECT * from "+Constantes.TablaUsuariosB+" WHERE "+Constantes.usuariosBNombre+" not like '"+conectado.getNombre()+"'";
        //String sentencia = "SELECT * FROM " + Constantes.TablaUsuariosB;
        ResultSet usuarios = Senntencia_SQL.executeQuery(sentencia);
        while(usuarios.next()){
            Usuario_b u= new Usuario_b();
            u.setEmail(usuarios.getString(Constantes.usuariosBEmail));
            u.setNombre(usuarios.getString(Constantes.usuariosBNombre));
            u.setRol(usuarios.getInt(Constantes.usuariosBRol));
            u.setActivo(usuarios.getBoolean(Constantes.usuariosBActivo));
            ListaUsuario_b.add(u);
        }
        return ListaUsuario_b;
    }

    public int modificarDato(String tabla, String campo, String where, boolean activo) {
        int cod = 0;
        String Sentencia = "UPDATE " + tabla + " SET " + campo + " = '" + activo + "' WHERE " + where;
        try {
            Senntencia_SQL.executeUpdate(Sentencia);
        } catch (SQLException ex) {
            cod = ex.getErrorCode();
        }
        return cod;
    }

    public ArrayList<Incidencia> listaIncidencias(Incidencia i) throws SQLException {
        ArrayList<Incidencia> ListaIncidencias = new ArrayList<>();
        String sentencia = "SELECT * from "+Constantes.TablaIncidencias+" WHERE "+Constantes.incidenciasFecha+" = '"+i.getFecha()+"' AND "+Constantes.incidenciasRegion+" = "+i.getRegion();
        //String sentencia = "SELECT * FROM " + Constantes.TablaUsuariosB;
        ResultSet incidencias = Senntencia_SQL.executeQuery(sentencia);
        while(incidencias.next()){
            Incidencia inci= new Incidencia();
            inci.setFecha(incidencias.getString(Constantes.incidenciasFecha));
            inci.setRegion(incidencias.getInt(Constantes.incidenciasRegion));
            inci.setInfectados(incidencias.getInt(Constantes.incidenciasInfectados));
            inci.setFallecidos(incidencias.getInt(Constantes.incidenciasFallecidos));
            inci.setDadosAlta(incidencias.getInt(Constantes.incidenciasDadosAlta));
            ListaIncidencias.add(inci);
        }
        return ListaIncidencias;
    }

}
