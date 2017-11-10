package com.bsuir.archive.server;

import com.bsuir.archive.server.controller.ServerController;



public class ServerMain {

    public static void main(String[] args) {
        ServerController controller = new ServerController();
        controller.Start();
//        UserServiceImplementation user = UserServiceImplementation.getInstance();
//        try {
//            user.addUser(new User("admin","admin",true,true,true,true));
//        }
//        catch (Exception ex){}
    }
}
