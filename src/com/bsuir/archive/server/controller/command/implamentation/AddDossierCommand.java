package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.domain.Dossier;
import com.bsuir.archive.server.service.DossierService;
import com.bsuir.archive.server.service.ServiceFactory;
import com.bsuir.archive.server.service.exception.ServiceException;

public class AddDossierCommand implements Command {

    public AddDossierCommand() {

        ServiceFactory factory = ServiceFactory.getInstance();
        dossierService = factory.getDossierService();
    }

    DossierService dossierService;

    @Override
    public String execute(String[] param) {
        String response = "Dossier added";
        Dossier dossier = new Dossier();
        dossier.setFirstName(param[1]);
        dossier.setLastName(param[2]);
        dossier.setGroupNumber(param[3]);
        try {
            dossierService.addDossier(dossier);
        } catch (ServiceException ex) {
            response = "Error adding";
        }

        return response;
    }

    @Override
    public Boolean isAccessSee() {
        return accessSee;
    }

    @Override
    public Boolean isAccessWrite() {
        return accessWrite;
    }

    @Override
    public Boolean isAccessChange() {
        return accessChange;
    }

    @Override
    public String getDescriptionCommand() {
        return description;
    }

    @Override
    public Integer getCountParam() {
        return countParam;
    }

    @Override
    public Boolean isAccessAdmin() {
        return accessAdmin;
    }



    private static final int countParam = 4;
    private String description = "Adding a dossier: add|firstname|lastname|group number";
    Boolean accessSee = false;
    Boolean accessWrite = true;
    Boolean accessChange = false;
    Boolean accessAdmin = false;
}
