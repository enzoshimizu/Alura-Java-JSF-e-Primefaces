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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "NovaEmpresaServlet", urlPatterns = {"/novaEmpresa"})
public class NovaEmpresaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Cadastrando nova empresa");

        String paramNomeEmpresa = req.getParameter("nome");
        String paramDataEmpresa = req.getParameter("data");

        Date dataAbertura = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataAbertura = sdf.parse(paramDataEmpresa);
        } catch (ParseException ex) {
            throw new ServletException(ex);
        }

        Empresa empresa = new Empresa();
        empresa.setNome(paramNomeEmpresa);
        empresa.setDataAbertura(dataAbertura);

        Banco banco = new Banco();
        banco.adiciona(empresa);

        resp.sendRedirect("listaEmpresas");
        
        /*
        RequestDispatcher rd = req.getRequestDispatcher("/novaEmpresaCriada.jsp");
        req.setAttribute("nomeEmpresa", empresa.getNome());
        rd.forward(req, resp);
        */
    }

}
