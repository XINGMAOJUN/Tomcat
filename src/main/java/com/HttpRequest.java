package com;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {


    private String url;
    private Map<String, Object> paramMap = new HashMap<String, Object>();
    private InputStream is;

    public String getUrl() {
        return url;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public HttpRequest(InputStream is) throws Exception {
        this.is = is;
        resolverRequest();
    }

    private void resolverRequest() throws Exception {
        byte[] bytes = new byte[1024];
        int len = is.read(bytes);
        if (len > 0) {
            String str = new String(bytes, 0, len);
            System.out.println(str);
            int startIndex = 0;
            if (str.contains("GET")) {
                startIndex = str.indexOf("GET");
            } else if (str.contains("POST")) {
                startIndex = str.indexOf("POST");
            }
            int endIndex = str.indexOf("HTTP/1.1");

            url = str.substring(startIndex, endIndex).replace("GET","").replace("POST","").replace(" ","");

            if (str.contains("GET")) {
                System.out.println("GET请求" + url);
            } else if (str.contains("POST")) {
                System.out.println("POST请求" + url);
                int paramLen = str.lastIndexOf("\n") + 1;
                String paramStr = str.substring(paramLen, len);
                System.out.println(paramStr);
                paramMap.put("name","xing");
                paramMap.put("pwd","123");
            }
        }
    }
}
