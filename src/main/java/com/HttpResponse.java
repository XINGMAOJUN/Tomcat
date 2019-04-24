package com;

import java.io.FileInputStream;
import java.io.OutputStream;

public class HttpResponse{

    private OutputStream os;

    public HttpResponse(OutputStream os) {
        this.os = os;
    }

    public void writeFile(String path) throws Exception{

        FileInputStream fis = new FileInputStream(path);
        byte[] bytes = new byte[1024];
        StringBuffer sb=new StringBuffer();
        //响应头信息
        sb.append("HTTP/1.1 200 OK\n");
        sb.append("Content-Type: text/html;charset=UTF-8\n");
        sb.append("\r\n");
        //响应头信息写出去
        os.write(sb.toString().getBytes());

        int len = 0;
        while ((len=fis.read(bytes))!=-1) {
            //还有信息没有写完
            os.write(bytes,0,len);
        }
        fis.close();
        os.flush();
        os.close();
    }
}
