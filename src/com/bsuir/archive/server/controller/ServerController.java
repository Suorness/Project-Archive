package com.bsuir.archive.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    private int port = 1212;
    public void Start(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(!serverSocket.isClosed()) {
                System.out.println("Waiting for connection");
                Socket socket = serverSocket.accept();
                Thread thread =  new Thread(new ThreadController(socket));
                thread.start();
                System.out.println("Client connected");
            }

        }catch (IOException ex){
//TODO
        }
    }
}
