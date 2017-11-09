package com.bsuir.archive.server;

import com.bsuir.archive.server.controller.ServerController;



public class ServerMain {

    public static void main(String[] args) {
        ServerController controller = new ServerController();
        controller.Start();

    }
}
