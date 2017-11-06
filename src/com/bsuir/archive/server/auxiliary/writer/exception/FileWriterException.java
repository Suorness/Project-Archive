package com.bsuir.archive.server.auxiliary.writer.exception;

public class FileWriterException extends Exception {
    public FileWriterException() {
    }

    public FileWriterException(String message) {
        super(message);
    }

    public FileWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileWriterException(Throwable cause) {
        super(cause);
    }
}
