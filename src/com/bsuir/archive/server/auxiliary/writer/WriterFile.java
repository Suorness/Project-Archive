package com.bsuir.archive.server.auxiliary.writer;

import com.bsuir.archive.server.auxiliary.writer.exception.FileWriterException;

public interface WriterFile {

    void writeData(String path, String data) throws FileWriterException;
    String readData (String path) throws FileWriterException;
}
