package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.domain.Dossier;
import com.bsuir.archive.server.service.DossierService;
import com.bsuir.archive.server.service.ServiceFactory;
import com.bsuir.archive.server.service.exception.ServiceException;

import java.util.List;

public class GetAllDossierCommand implements Command {

    public GetAllDossierCommand() {

        ServiceFactory factory = ServiceFactory.getInstance();
        dossierService = factory.getDossierService();
    }

    DossierService dossierService;


    @Override
    public String execute(String[] param) {
        StringBuffer buffer = new StringBuffer();
        List<Dossier> list = null;
        try {
            list = dossierService.getList();
        } catch (ServiceException ex) {
            //TODO
        }
        String response = "List is empty";
        if (list != null) {
            int index = 1;
            if (list.size() != 0) {
                for (Dossier item : list) {
                    buffer.append(index++ + ": " + item.getFirstName() + " " + item.getLastName() +
                            " " + item.getGroupNumber() + "\r\n");
                }
                response = buffer.toString();
            }
        } else {
            response = "Error";
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

    private static final int countParam = 1;
    private String description = "Displaying the list with the dossier: show";
    Boolean AccessSee = true;
    Boolean AccessWrite = false;
    Boolean AccessChange = false;
    Boolean AccessAdmin = false;
}
