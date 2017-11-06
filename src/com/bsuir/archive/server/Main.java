package com.bsuir.archive.server;

import com.bsuir.archive.server.auxiliary.parser.implamentation.XMLParser;
import com.bsuir.archive.server.dao.implamentation.XMLDossierDAO;
import com.bsuir.archive.server.domain.Dossier;


public class Main {

    public static void main(String[] args) {


        XMLDossierDAO dao  = XMLDossierDAO.getInstance();
        Dossier dossier = new Dossier();
        dossier.setGroupNumber("5555");
        dossier.setFirstName("testFName");
        dossier.setLastName("testLName");
        try {
            dao.addDossier(dossier);
        }catch (Exception ex){
            System.out.println("EX");
        }
        System.out.println("OK");


    }
}
