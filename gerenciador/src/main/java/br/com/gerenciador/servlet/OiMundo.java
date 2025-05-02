package br.com.gerenciador.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/oi")
public class OiMundo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        
        out.println("<html>");
        out.println("<body>");
        out.println("oi mundo, teste de servlet");        
        out.println("</body>");
        out.println("</html>");
        
        System.out.println("o servlet OiMundo foi chamado");
    }

}
