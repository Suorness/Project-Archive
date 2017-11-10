package com.bsuir.archive.server.controller;

import com.bsuir.archive.server.auxiliary.manager.UserManager;
import com.bsuir.archive.server.controller.command.Command;
import com.bsuir.archive.server.domain.User;
import com.bsuir.archive.server.service.ServiceFactory;
import com.bsuir.archive.server.service.UserService;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadController implements Runnable {


    private CommandProvider provider;

    private UserService userService;

    private boolean working;
    private Socket socket;
    private UserManager userManager;

    private InputStream sIn;
    private OutputStream sOut;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ThreadController(Socket socket) {
        ServiceFactory factory = ServiceFactory.getInstance();
        userService = factory.getUserService();
        this.socket = socket;
        userManager = new UserManager();
        provider = new CommandProvider(userManager);
    }

    @Override
    public void run() {
        try {
            sIn = socket.getInputStream();
            sOut = socket.getOutputStream();
            inputStream = new DataInputStream(sIn);
            outputStream = new DataOutputStream(sOut);
            String line = null;
            String request = null;

            while (socket.isConnected()) {
                String response = null;
                line = formationCommandList();
                outputStream.writeUTF(line);
                request = inputStream.readUTF();
                response = doAction(request);
                outputStream.writeUTF(response);
                outputStream.flush();
            }
        } catch (IOException ex) {
            //TODO
        }
    }

    void Start() {
        working = true;

    }

    String doAction(String request) {
        String response = "Invalid command";
        String[] param = request.split("\\|");
        Command command = provider.getCommand(param[0]);
        if ((command != null) && (availabilityCheck(userManager.getUser(), command))) {
            if (command.getCountParam() == param.length) {
                response = command.execute(param);
            } else {
                response = "Wrong number of parameters";
            }
        }
        return response;
    }

    Boolean availabilityCheck(User user, Command command) {
        Boolean result = true;
        if (command.isAccessChange()) {
            if (!user.isAccessChange()) {
                result = false;
            }
        }
        if (command.isAccessSee()) {
            if (!user.isAccessSee()) {
                result = false;
            }
        }
        if (command.isAccessWrite()) {
            if (!user.isAccessWrite()) {
                result = false;
            }
        }
        if (command.isAccessAdmin()) {
            if (!user.getAccessAdmin()){
                result = false;
            }
        }
        return result;
    }

    String formationCommandList() {
        List<Command> list = new ArrayList<Command>(provider.getCommands().values());
        StringBuffer buffer = new StringBuffer();
        for (Command command : list) {
            if (availabilityCheck(userManager.getUser(), command)) {
                buffer.append(command.getDescriptionCommand() + "\r\n");
            }
        }
        return buffer.toString();
    }

}
