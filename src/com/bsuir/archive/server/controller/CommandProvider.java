package com.bsuir.archive.server.controller;

import com.bsuir.archive.server.auxiliary.manager.UserManager;
import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.controller.command.implamentation.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    public CommandProvider(UserManager manager) {
        commands.put("add", new AddDossierCommand());
        commands.put("signin", new AuthorizationCommand(manager));
        commands.put("logout", new LogOutCommand(manager));
        commands.put("show", new GetAllDossierCommand());
        commands.put("delete", new DeleteDossierCommand());
        commands.put("change", new ChangeDossierCommand());
        commands.put("exit", new ExitCommand());
        commands.put("adduser", new AddUserCommand());
        commands.put("deleteuser", new DeleteUserCommand());
    }


    Command getCommand(String commandName) {
        Command command;
        command = commands.get(commandName);
        return command;

    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
