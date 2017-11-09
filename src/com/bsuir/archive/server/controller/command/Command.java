package com.bsuir.archive.server.controller.command;

public interface Command {
    String execute(String[] param);
    Boolean isAccessSee();
    Boolean isAccessWrite();
    Boolean isAccessChange();
    String getDescriptionCommand();
    Integer getCountParam();
    Boolean isAccessAdmin();
}
