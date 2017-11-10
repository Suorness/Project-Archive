package com.bsuir.archive.client.view.implementation;

import com.bsuir.archive.client.view.Reader;

import java.util.Scanner;

/**
 * Class that reads data through the console
 */
public class ConsoleReader implements Reader {

    @Override
    public String dataInputString() {
        String result;
        result = in.nextLine().trim();
        return result;
    }

    @Override
    public int dataInputInt() {
        int result;
        String str = in.nextLine().trim();
        try {
            result = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }

    public ConsoleReader() {
        in = new Scanner(System.in);
    }

    private Scanner in;
}