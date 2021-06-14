/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.crypto.SealedObject;
import seguridad.Seguridad;

/**
 *
 * @author ivanc
 */
public class Escritor {
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Claves claves;

    public Escritor(Socket socket, Claves claves) throws IOException {  
        InputStream in=socket.getInputStream();
        OutputStream out=socket.getOutputStream();
        this.dis = new DataInputStream(in);        
        this.dos = new DataOutputStream(out);        
        this.oos = new ObjectOutputStream(out);
        this.ois = new ObjectInputStream(in);    
        this.claves=claves;
    }

    public Object leer() throws Exception {
       return Seguridad.desencriptarObjeto((SealedObject) ois.readObject(), claves.getPrivada());
    }

    public void escribir(Object obj) throws Exception {
        oos.writeObject(Seguridad.cifrarObjeto(obj, claves.getOtroExtremo()));
    }

    public ObjectInputStream ois() {
        return ois;
    }

    public ObjectOutputStream oos() {
        return oos;
    }
}
