package com.bsuir.archive.server.auxiliary.writer.implamentation;

import com.bsuir.archive.server.auxiliary.writer.WriterFile;
import com.bsuir.archive.server.auxiliary.writer.exception.FileWriterException;

import java.io.*;
import java.util.Scanner;

public class FileWriters implements WriterFile {

    private static FileWriters insnace = new FileWriters();

    public static FileWriters getInstance() {
        return insnace;
    }

    @Override
    public void writeData(String path, String data) throws FileWriterException {
        try (Writer out = new FileWriter(path)) {
            out.write(data);
        } catch (IOException e) {
            throw new FileWriterException(e);
        }
    }

    @Override
    public String readData(String path) throws FileWriterException {
        StringBuffer data = new StringBuffer();
        try (Scanner in = new Scanner(new File(path))) {
            while (in.hasNextLine()) {
                data.append(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new FileWriterException(e);
        }

        return data.toString();
    }
}
