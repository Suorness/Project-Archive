package com.bsuir.archive.client.controller;

import com.bsuir.archive.client.view.Reader;
import com.bsuir.archive.client.view.View;
import com.bsuir.archive.client.view.implementation.ConsoleReader;
import com.bsuir.archive.client.view.implementation.ConsoleView;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Controller {
    public Controller(int serverPort, String address) {

        view = new ConsoleView();
        reader = new ConsoleReader();
        this.serverPort = serverPort;
        this.address = address;
    }

    private View view;
    private Reader reader;
    private int serverPort;
    private String address;
    private Socket socket;

    public void Start() {
        try {
            connection();
        } catch (Exception ex) {
            view.showErrorInfo("Could not connect to the server");
        }
    }

    private void connection() {
        Boolean working = true;
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            socket = new Socket(ipAddress, serverPort);
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            while (working) {
                line = in.readUTF();
                view.outputLine("Server:\r\n" + line);
                line = reader.dataInputString();
                if (!checkCompletion(line)) {
                    out.writeUTF(line);
                    out.flush();
                    line = in.readUTF();
                    view.outputLine("Server:\r\n" + line);
                } else {
                    working = false;
                }
            }

        } catch (IOException ex) {
            view.showErrorInfo(ex.getMessage());
        }
        try {
            socket.shutdownOutput();
        } catch (IOException ex) {
            view.showErrorInfo(ex.getMessage());
        }
        try {
            socket.shutdownInput();
        } catch (IOException ex) {
            view.showErrorInfo(ex.getMessage());
        }
        try {
            socket.close();
        } catch (IOException ex) {
            view.showErrorInfo(ex.getMessage());
        }
    }

    boolean checkCompletion(String str) {
        Boolean result = false;
        if (str.equals("exit")) {
            result = true;
        }
        return result;
    }
}
