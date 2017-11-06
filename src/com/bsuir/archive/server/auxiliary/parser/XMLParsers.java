package com.bsuir.archive.server.auxiliary.parser;

import com.bsuir.archive.server.auxiliary.parser.exception.ParserException;

import java.util.List;

public interface XMLParsers {
    <H> String dataToXML(Class clasStudied, List<H> list) throws ParserException;

    <H> List<H> XMLToData(String xml, H someObject) throws ParserException;
}
