package br.com.gerenciador.servlet;

import br.com.gerenciador.modelos.Banco;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "removeEmpresaServlet", urlPatterns = {"/removeEmpresa"})
public class removeEmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramId = req.getParameter("id");
        Integer id = Integer.valueOf(paramId);
        
        Banco banco = new Banco();
        banco.remove(id);
        resp.sendRedirect("listaEmpresas");
    }
    
}
