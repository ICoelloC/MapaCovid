/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ivanc
 */
public class Servidor {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(1050);
        System.out.println("Iniciando el servidor...");
        while(true){
            Socket cliente;
            cliente = servidor.accept();
            HiloServidor h = new HiloServidor(cliente);
            h.start();
        }
    }
}
