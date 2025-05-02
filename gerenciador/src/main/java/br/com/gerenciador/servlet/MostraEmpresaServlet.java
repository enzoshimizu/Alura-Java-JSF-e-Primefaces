package br.com.gerenciador.servlet;

import br.com.gerenciador.modelos.Banco;
import br.com.gerenciador.modelos.Empresa;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MostraEmpresaServlet", urlPatterns = {"/mostraEmpresa"})
public class MostraEmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramId = req.getParameter("id");
        Integer id = Integer.valueOf(paramId);
        
        Banco banco = new Banco();
        Empresa empresa = banco.buscaPorId(id);
        
        req.setAttribute("empresa", empresa);
        RequestDispatcher rd = req.getRequestDispatcher("/formAlteraEmpresa.jsp");
        rd.forward(req, resp);
    }

}
