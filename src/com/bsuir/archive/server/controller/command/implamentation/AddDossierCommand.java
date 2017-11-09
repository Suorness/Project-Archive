package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.command.Command;

public class AddDossierCommand implements Command {

    @Override
    public String execute() {
        return null;
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
    private String description ="Adding a dossier";
    Boolean AccessSee = false;
    Boolean AccessWrite = true;
    Boolean AccessChange = false;
}
