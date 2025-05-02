package br.com.alura.livraria.managedbean;

import br.com.alura.livraria.bean.Autor;
import br.com.alura.livraria.dao.DAO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class AutorBean {

    private Autor autor = new Autor();

    public Autor getAutor() {
        return autor;
    }

    public void gravar() {
        System.out.println("Gravando autor " + this.autor.getNome());

        new DAO<>(Autor.class).adiciona(this.autor);
        
        this.autor = new Autor();
    }
}
