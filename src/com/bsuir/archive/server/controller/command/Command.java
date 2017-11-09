package com.bsuir.archive.server.controller.command;

public interface Command {
    String execute();
    Boolean isAccessSee();
    Boolean isAccessWrite();
    Boolean isAccessChange();
    String getDescriptionCommand();
}
