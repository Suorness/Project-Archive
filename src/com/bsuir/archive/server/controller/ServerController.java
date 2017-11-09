package com.bsuir.archive.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    private int port = 1212;
    public void Start(){
        try {
            ServerSocket ss = new ServerSocket(port);
            while(true) {
                System.out.println("Ожидание подключения");
                Socket socket = ss.accept();
                Thread thread =  new Thread(new Controller(socket));
                thread.start();
                System.out.println("Клиент подключен");
            }

        }catch (IOException ex){

        }
    }
}
