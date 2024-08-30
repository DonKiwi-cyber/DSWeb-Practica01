/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.dsweb.practica01.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ian
 */
public class DSWebPractica01Server {

    public static void main(String[] args) {
        
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Server iniciado... ");
            
            while(true){
                Socket cliente = server.accept();
                ClienteSocket clienteSocket = new ClienteSocket();
                clienteSocket.start();
                System.out.println("Cliente aceptado");
            }
            
//            InputStreamReader isr = new InputStreamReader(cliente.getInputStream());
//            BufferedReader reader = new BufferedReader(isr);
//            String msg = reader.readLine();
//            System.out.println("Cliente: "+ msg);
            
//            OutputStreamWriter osw = new OutputStreamWriter(cliente.getOutputStream());
//            BufferedWriter writter = new BufferedWriter(osw);
//            writter.write("Saludos desde cliente...");
//            writter.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(DSWebPractica01Server.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
