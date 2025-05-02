package br.com.gerenciador.servlet;

import br.com.gerenciador.modelos.Banco;
import br.com.gerenciador.modelos.Empresa;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AlteraEmpresaServlet", urlPatterns = {"/alteraEmpresa"})
public class AlteraEmpresaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramIdEmpresa = req.getParameter("id");
        String paramNomeEmpresa = req.getParameter("nome");
        String paramDataEmpresa = req.getParameter("data");
        Integer id = Integer.valueOf(paramIdEmpresa);

        Date dataAbertura = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataAbertura = sdf.parse(paramDataEmpresa);
        } catch (ParseException ex) {
            throw new ServletException(ex);
        }
        
        Banco banco = new Banco();
        Empresa empresa = banco.buscaPorId(id);
        empresa.setNome(paramNomeEmpresa);
        empresa.setDataAbertura(dataAbertura);
        
        resp.sendRedirect("listaEmpresas");

    }

}
