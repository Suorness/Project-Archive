package com.bsuir.archive.server;

import com.bsuir.archive.server.controller.ServerController;


public class ServerMain {

    public static void main(String[] args) {
        ServerController controller = new ServerController();
        controller.Start();
//        DossierService dossierService = DossierServiceImplementation.getInstance();
//        UserService userService = UserServiceImplementation.getInstance();
//        User user = new User();
//        user.setId(0);
//        user.setAccessChange(true);
//        user.setAccessSee(false);
//        user.setAccessWrite(true);
//
//        User user2 = new User();
//        user2.setId(11);
//        user2.setAccessChange(false);
//        user2.setAccessSee(false);
//        user2.setAccessWrite(false);
//
//        Dossier dossier = new Dossier();
//        dossier.setGroupNumber("5555");
//        dossier.setFirstName("testFName");
//        dossier.setLastName("testLName");
//
//        Dossier dossier2 = new Dossier();
//        dossier2.setGroupNumber("25555");
//        dossier2.setFirstName("2testFName");
//        dossier2.setLastName("2testLName");
//        try {
//            dossierService.addDossier(dossier);
//            dossierService.clear();
//            dossierService.addDossier(dossier);
//            dossierService.delDossier(dossier);
//            dossierService.addDossier(dossier);
//            dossierService.changeDossier(dossier,dossier2);
//            dossierService.addDossier(dossier);
//        }catch (Exception ex){
//            System.out.println("EX");
//        }
//
//        boolean result = false;
//        try {
//            userService.clear();
//            result = userService.addUser(user);
//            result = userService.delUser(user);
//            result = userService.addUser(user);
//            result = userService.addUser(user);
//            result = userService.changeUser(user,user2);
//            result = userService.addUser(user);
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        System.out.println("OK");
//        System.out.println(result);
    }
}
