package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.domain.Dossier;
import com.bsuir.archive.server.service.DossierService;
import com.bsuir.archive.server.service.ServiceFactory;
import com.bsuir.archive.server.service.exception.ServiceException;

public class ChangeDossierCommand implements Command {


    public ChangeDossierCommand() {

        ServiceFactory factory = ServiceFactory.getInstance();
        dossierService = factory.getDossierService();
    }

    DossierService dossierService;
    @Override
    public String execute(String[] param) {
        String response = "Изменено";
        Dossier dossier = new Dossier();
        Dossier newDossier = new Dossier();
        dossier.setFirstName(param[1]);
        dossier.setLastName(param[2]);
        dossier.setGroupNumber(param[3]);

        newDossier.setFirstName(param[4]);
        newDossier.setLastName(param[5]);
        newDossier.setGroupNumber(param[6]);

        Boolean result = true;
        try {
            result = dossierService.changeDossier(dossier,newDossier);
        } catch (ServiceException ex) {
            //TODO
            response = "Ошибка при изменении";
        }
        if (!result){
            response = "Не удалось изменить";
        }
        return  response;
    }

    @Override
    public Boolean isAccessSee() {
        return AccessSee;
    }

    @Override
    public Boolean isAccessWrite() {
        return AccessWrite;
    }

    @Override
    public Boolean isAccessChange() {
        return AccessChange;
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
        return AccessAdmin;
    }


    private static final int countParam = 7;
    private String description = "Change a dossier: change|old firstname|old lastname|old group number| new firstname|" +
            "new lastname| new group number";
    Boolean AccessSee = false;
    Boolean AccessWrite = true;
    Boolean AccessChange = true;
    Boolean AccessAdmin = false;
}
