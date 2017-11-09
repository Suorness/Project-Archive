package com.bsuir.archive.client.view.implementation;

import com.bsuir.archive.client.view.View;

import java.util.List;

public class ConsoleView implements View {

//    @Override
//    public void showList(List<Book> list){
//        String resultOutPut = "";
//        for (Book book: list){
//            resultOutPut +=  book.getTitle() +  " " + book.getAuthor() + " " +
//                    book.getCount() + "\r\n";
//        }
//        outputLine(resultOutPut);
//
//    }

//    @Override
//    public void showBookInfo(Book book){
//        String resultOutPut = "";
//        resultOutPut +=  book.getTitle() +  " " + book.getAuthor() + "  count: " +
//                book.getCount() + "\r\n";
//        outputLine(resultOutPut);
//    }


    @Override
    public void outputLine(String str){
        System.out.println(str);
    }

    @Override
    public void showErrorInfo(String message ){
        System.err.println(message);
    }
}