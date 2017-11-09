package com.bsuir.archive.server.controller.command.implamentation;

import com.bsuir.archive.server.controller.command.Command;

public class AuthorizationCommand implements Command {
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

    private String description ="Authorization";

    Boolean AccessSee = false;
    Boolean AccessWrite = false;
    Boolean AccessChange = false;
}
