package com.bsuir.archive.server;

import com.bsuir.archive.client.controller.Controller;

public class ClientMain {
    public static void main(String[] args) {
        System.out.println("Клиент");
        Controller controller = new Controller(1212,null);
        controller.Start();
    }
}
