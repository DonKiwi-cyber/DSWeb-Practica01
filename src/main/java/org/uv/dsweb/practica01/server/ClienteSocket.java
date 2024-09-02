/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dsweb.practica01.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ian
 */
public class ClienteSocket extends Thread{
    
    private Socket cliente;
    BufferedReader reader = null;
    BufferedWriter writer = null;
    
    public ClienteSocket(Socket cliente){
        InputStreamReader isr = null;
        try {
            this.cliente = cliente;
            isr = new InputStreamReader(cliente.getInputStream());
            reader = new BufferedReader(isr);
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        finally {
//            try {
//                isr.close();
//            } catch (IOException ex) {
//                Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(cliente.getOutputStream());
            writer = new BufferedWriter(osw);
        } catch (IOException ex) {
            Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        finally {
//            try{
//                osw.close();
//            } catch(IOException ex){
//                Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
    
    public int findOperator(String input){
        
        char[] options = {'+','x','-','/'};
        
        int index = -1;
        
        for (char option : options){
            index = input.indexOf(option);
            if (index != -1){
                break;
            }
        }
        
        return index;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                String operacion = reader.readLine();
                System.out.println("Operación-->"+operacion);
                
                String resultado = "";
                int index = findOperator(operacion);
                if(index != -1){
                    String a = operacion.substring(0, index);
                    String b = operacion.substring(index+1, operacion.length());
                    char op = operacion.charAt(index);
                    double cVal = 0;
                    
                    switch(op){
                        case '+' -> {
                            cVal = Integer.parseInt(a)+Integer.parseInt(b);
                        }
                        case '-' -> {
                            cVal = Integer.parseInt(a)-Integer.parseInt(b);
                        }
                        case 'x' -> {
                            cVal = Integer.parseInt(a)*Integer.parseInt(b);
                        }
                        case '/' -> {
                            cVal = Integer.parseInt(a)/Integer.parseInt(b);
                        }
                        default -> {
                            cVal = 0;
                        }
                    }
                    
                    resultado = String.valueOf(cVal)+"\n";
                } else{
                    resultado = "Error...\n";
                }
                
                writer.write(resultado);
                writer.flush();
            } catch (IOException ex) {
                Logger.getLogger(ClienteSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
