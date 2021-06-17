/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ayuda;

/**
 *
 * @author ivanc
 */
public class Constantes {

    //--------------- ACCIONES ----------------------
    public static final int REGISTRAR = 0;
    public static final int LOGEAR = 1;
    public static final int GET_USER = 2;
    public static final int SAVE_REGION = 3;
    public static final int GET_REGIONES = 4;
    public static final int COMPROBAR_PRIMERA = 5;
    public static final int CAMBIAR_PASSWORD = 6;
    public static final int MODIFICAR_USUARIO = 7;
    public static final int CARGAR_INCIDENCIAS = 8;
    public static final int ADD_INCIDENCIA = 9;
    public static final int CARGAR_USUARIOS = 10;
    public static final int BORRAR_USUARIO = 11;
    public static final int DESACTIVAR_USUARIO = 12;
    public static final int ACTIVAR_USUARIO = 13;
    public static final int CARGAR_REGIONES = 14;
    public static final int ADD_REGION = 15;
    public static final int BORRAR_REGION = 16;

    //--------------- valores ----------------------
    public static final int ROL_ADMINISTRADOR = 1;
    public static final int ROL_USUARIO = 2;

    //--------------- BASE DE DATOS ----------------------
    public static final String bbdd = "mapacovid";
    public static final String usuariobd = "root";
    public static final String passwdbd = "";

    //--------------- TABLA USUARIOS ----------------------
    public static final String TablaUsuarios = "usuarios";
    public static final String usuariosEmail = "email";
    public static final String usuariosNombre = "nick";
    public static final String usuariosPass = "pass";
    public static final String usuariosClave = "clave";
    public static final String usuariosRol = "rol";
    public static final String usuarioClavePublica = "clave_publica";
    public static final String usuariosClavePrivada = "clave_privada";
    public static final String usuariosActivo = "activo";

    //--------------- TABLA REGIONES ----------------------
    public static final String TablaRegiones = "regiones";
    public static final String regionesRegion = "region";

    //--------------- TABLA ROLES ----------------------
    public static final String TablaRoles = "roles";
    public static final String rolesTipo = "tipo";

    //--------------- TABLA INCIDENCIAS ----------------------
    public static final String TablaIncidencias = "incidencias";
    public static final String incidenciasRegion = "region";
    public static final String incidenciasFecha = "fecha";
    public static final String incidenciasInfectados = "infectados";
    public static final String incidenciasFallecidos = "fallecidos";
    public static final String incidenciasDadosAlta = "dadosAlta";
    
    //--------------- TABLA USUARIOS_B ----------------------
    public static final String TablaUsuariosB = "usuarios_b";
    public static final String usuariosBEmail = "email";
    public static final String usuariosBNombre = "nick";
    public static final String usuariosBPass = "pass";
    public static final String usuariosBRol = "rol";
    public static final String usuariosBActivo = "activo";
    
}
