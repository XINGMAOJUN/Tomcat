package com;

public class LoginServlet implements HttpServlet {
    /**
     * d
     *
     * @param httpRequest
     * @param httpResponse
     */
    public void server(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception{
        httpRequest.getParamMap();
        httpResponse.writeFile("web/index.jsp");
    }
}
