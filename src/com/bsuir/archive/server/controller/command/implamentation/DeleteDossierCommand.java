package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.domain.Dossier;
import com.bsuir.archive.server.service.DossierService;
import com.bsuir.archive.server.service.ServiceFactory;
import com.bsuir.archive.server.service.exception.ServiceException;

public class DeleteDossierCommand implements Command {

    public DeleteDossierCommand() {

        ServiceFactory factory = ServiceFactory.getInstance();
        dossierService = factory.getDossierService();
    }

    DossierService dossierService;
    @Override
    public String execute(String[] param) {
        String response = "Удалено";
        Dossier dossier = new Dossier();
        dossier.setFirstName(param[1]);
        dossier.setLastName(param[2]);
        dossier.setGroupNumber(param[3]);
        Boolean result = true;
        try {
            result = dossierService.delDossier(dossier);
        } catch (ServiceException ex) {
            //TODO
            response = "Ошибка добавления";
        }
        if (!result){
            response = "Не удалось удалить";
        }
        return response;
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

    private static final int countParam = 4;
    private String description = "Delete a dossier: delete|firstname|lastname|group number";
    Boolean AccessSee = false;
    Boolean AccessWrite = true;
    Boolean AccessChange = true;
    Boolean AccessAdmin = false;
}
