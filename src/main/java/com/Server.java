package com;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {

    public static void main(String[] args) throws Exception{


        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("服务器启动成功；port 8888");
            while (true){
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                HttpRequest httpRequest = new HttpRequest(is);
                String url = httpRequest.getUrl();

                OutputStream os = socket.getOutputStream();
                HttpResponse httpResponse = new HttpResponse(os);
                handle(url, httpRequest, httpResponse);
            }

        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            socket.close();
            serverSocket.close();
        }
    }

    public static void handle(String url, final HttpRequest httpRequest, final HttpResponse httpResponse) throws Exception{
        Map<String,String> map =   ConfigUtil.getWebConfig();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (url.equals(entry.getKey())) {
                try {
                    HttpServlet httpServlet = (HttpServlet) Class.forName(entry.getValue()).newInstance();
                    httpServlet.server(httpRequest, httpResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
