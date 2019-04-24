package com;

public interface HttpServlet  {

    /**
     * d
     * @param httpRequest
     * @param httpResponse
     */
    void server(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception;
}
