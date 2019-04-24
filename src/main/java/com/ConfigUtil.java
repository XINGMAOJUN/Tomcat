package com;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConfigUtil {

    public static Map<String, String> getWebConfig() throws Exception {

        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        File file = new File("WEB-INF/web.xml");
        Document document = reader.read(file);

        Element element = document.getRootElement();
        Iterator iterator = element.elementIterator("servlet");
        while (iterator.hasNext()) {
            Element element2 = (Element) iterator.next();
            String key = element2.element("url-pattern").getText();
            String value = element2.element("servlet-class").getText();
            map.put(key, value);
        }
        return map;

    }
}
