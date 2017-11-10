package com.bsuir.archive.client.view.implementation;

import com.bsuir.archive.client.view.View;

public class ConsoleView implements View {


    @Override
    public void outputLine(String str) {
        System.out.println(str);
    }

    @Override
    public void showErrorInfo(String message) {
        System.out.println(message);
    }
}