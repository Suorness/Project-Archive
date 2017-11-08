package com.bsuir.archive.server.auxiliary.parser.implamentation;

import com.bsuir.archive.server.auxiliary.parser.XMLParsers;
import com.bsuir.archive.server.auxiliary.parser.exception.ParserException;
import com.bsuir.archive.server.domain.Dossier;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser implements XMLParsers {
    private static final String HEADER ="<?xml version=\"1.0\" encoding=\"windows-1251\"?>";
    private static final String ATTRIBUTE_LIST = "<list>";
    private static final String ATTRIBUTE_LIST_END = "</list>";
    private static final String ATTRIBUTE_CELL = "<cell>";
    private static final String ATTRIBUTE_CELL_END = "</cell>";

    public void Test(){
        Dossier dossier = new Dossier();
        Dossier dossier2 = new Dossier();
        dossier.setGroupNumber("5555");
        dossier.setFirstName("testFName");
        dossier.setLastName("testLName");
        dossier2.setGroupNumber("25555");
        dossier2.setFirstName("2testFName");
        dossier2.setLastName("2testLName");
        Class classVar = dossier.getClass();
        List<Dossier> list = new ArrayList<Dossier>();
        list.add(dossier);
        list.add(dossier2);
        List<Dossier> list2 = null;
        try {
            String Xml = dataToXML(dossier.getClass(),list);
            list2 = XMLToData(Xml, dossier);
        }catch (Exception ex){}
        for (Dossier item: list2) {
            System.out.println(item.getFirstName());
            System.out.println(item.getLastName());
            System.out.println(item.getGroupNumber());
        }
        System.out.println();
    }

    private  static XMLParser instance = new XMLParser();
    public  static XMLParser getInstance(){
        return  instance;
    }
    @Override
    public <H> String dataToXML(Class clasStudied, List<H> list) throws ParserException{
        String Result = null;
//        if ( (list!=null) && (!list.isEmpty())) {
        if ( (list!=null)) {
            Field[] privateFields = clasStudied.getDeclaredFields();
            StringBuffer xml = new StringBuffer();
            xml.append(HEADER);
            xml.append(ATTRIBUTE_LIST);
            for (H item : list) {
                xml.append(ATTRIBUTE_CELL);
                for (Field field : privateFields) {
                    Object value = null;
                    try {
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        value = field.get(item);
                    } catch (Exception ex) {
                        throw new ParserException("Error getting field", ex);
                    }
                    String str = String.format("<%s>%s</%s>", field.getName(), value.toString(), field.getName());
                    xml.append(str);
                }
                xml.append(ATTRIBUTE_CELL_END);
            }
            xml.append(ATTRIBUTE_LIST_END);
            Result = xml.toString();
        }
        return Result;
    }
    @Override
    public  <H> List<H> XMLToData(String xml, H someObject) throws ParserException {
        List<H> list = new ArrayList<H>();
        String regEx = ATTRIBUTE_LIST + "(.*)" + ATTRIBUTE_LIST_END;
        String regExCell = ATTRIBUTE_CELL + "(.*?)"+ ATTRIBUTE_CELL_END;
        Pattern pattern  = Pattern.compile(regEx);
        Matcher match = pattern.matcher(xml);
        match.find();
        if (match.groupCount()==1){
            String items = match.group(1);
            pattern = Pattern.compile(regExCell);
            Matcher matchCell = pattern.matcher(items);
            while (matchCell.find()){
                list.add(parseCell(matchCell.group(1),someObject));
            }
        }
        return list;
    }
    private  <H> H parseCell(String xml,H someObject) throws ParserException {
        Class clasStudied = someObject.getClass();
        Field[] fields = clasStudied.getDeclaredFields();
        H dossier = null;
        try {
            Constructor constructor = clasStudied.getConstructor();
            dossier = (H)constructor.newInstance();
        }
        catch (Exception ex){
            throw  new ParserException("Error creating object", ex);
        }
        for (Field field : fields) {
            String regEx = String.format("<%s>(.*?)</%s>",field.getName(),field.getName());
            Pattern pattern  = Pattern.compile(regEx);
            Matcher match = pattern.matcher(xml);
            if(match.find()){
                try {
                    String methodName = String.format("set%s",firstUpperCase(field.getName()));
                    Class[] parameterTypes = definitionOfParameters(methodName, clasStudied);
                    Method method = clasStudied.getMethod(methodName, parameterTypes);
                    Object obj = cast(match.group(1),parameterTypes);
                    method.invoke(dossier,obj);
                }
                catch (Exception ex){
                    throw  new ParserException("Error setting field", ex);
                }
            }
        }
        return  dossier;
    }

    private Object cast(String param,Class[] parameterTypes) throws ParserException {
        Object obj = null;

        Class paramType = parameterTypes[0];
        if (paramType.equals(Integer.class)){
            obj = new Integer(0);
            obj = Integer.parseInt(param);
        }
        if (paramType.equals(Boolean.class)){
            obj = new Boolean(false);
            obj = Boolean.parseBoolean(param);
        }
        if (paramType.equals(String.class)){
            obj = new String(param);
        }
        return obj;

    }

    private Class[] definitionOfParameters(String methodName, Class clasStudied ) {
        //return  new Class[]{Integer.class};
        Class[] parameterTypes = null;
        Method[] methods = clasStudied.getMethods();

        for (Method method: methods) {
            if (method.getName().equals(methodName)){
                parameterTypes = method.getParameterTypes();
                break;
            }
        }
        return parameterTypes;
    }

    private String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}
