package br.com.gerenciador.servlet;

import br.com.gerenciador.modelos.Banco;
import br.com.gerenciador.modelos.Empresa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaEmpresasServlet", urlPatterns = {"/listaEmpresas"})
public class ListaEmpresasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Banco banco = new Banco();
        List<Empresa> listaEmpresas = banco.getEmpresas();

        req.setAttribute("listaEmpresas", listaEmpresas);
        RequestDispatcher rd = req.getRequestDispatcher("/listaEmpresas.jsp");
        rd.forward(req, resp);

    }

}
