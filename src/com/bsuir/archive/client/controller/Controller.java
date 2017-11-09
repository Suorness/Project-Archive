package com.bsuir.archive.client.controller;

import com.bsuir.archive.client.view.Reader;
import com.bsuir.archive.client.view.View;
import com.bsuir.archive.client.view.implementation.ConsoleReader;
import com.bsuir.archive.client.view.implementation.ConsoleView;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Controller {
    public Controller() {

        view = new ConsoleView();
        reader = new ConsoleReader();
    }
    private View view;
    private Reader reader;
    private  int serverPort = 1212;
    private String address;
    private Socket socket;
    public void Start(){
        connection(1212);
    }
    private void connection(int port){
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            socket = new Socket(ipAddress, serverPort);
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            while (true) {
                line = in.readUTF();
                view.outputLine("Сервер: "+line);
                line = reader.dataInputString();
                out.writeUTF(line);
                out.flush();
                line = in.readUTF();
                view.outputLine("Сервер: "+line);

            }

        }catch (IOException ex){
            view.showErrorInfo(ex.getMessage());
        }
    }
}
