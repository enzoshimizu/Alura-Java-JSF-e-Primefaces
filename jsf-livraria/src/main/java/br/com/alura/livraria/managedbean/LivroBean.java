package br.com.alura.livraria.managedbean;

import br.com.alura.livraria.bean.Autor;
import br.com.alura.livraria.bean.Livro;
import br.com.alura.livraria.dao.DAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class LivroBean implements Serializable {

    private final Livro livro = new Livro();
    private Integer autorId;

    public void gravar() {
        System.out.println("Gravando livro " + livro.getTitulo());

        if (livro.getAutores().isEmpty()) {
            //throw new RuntimeException("Livro deve ter pelo menos um Autor");
            FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um autor."));
            return;
        }

        new DAO<>(Livro.class).adiciona(this.livro);
    }

    public void remover(Integer id) {
        Livro item = new DAO<>(Livro.class).buscaPorId(id);
        new DAO<>(Livro.class).remove(item);
    }

    public void gravarAutor() {
        Autor autor = new DAO<>(Autor.class).buscaPorId(autorId);
        livro.adicionaAutor(autor);
    }

    public List<Autor> getAutores() {
        return new DAO<>(Autor.class).listaTodos();
    }

    public List<Autor> getAutoresDoLivro() {
        return livro.getAutores();
    }

    public List<Livro> getLivros() {
        return new DAO<>(Livro.class).listaTodos();
    }

    public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        String valor = value.toString();
        if (!valor.startsWith("1")) {
            throw new ValidatorException(new FacesMessage("Deve começar com 1."));
        }
    }

    public String formAutor() {
        System.out.println("Chamando formulário do autor");
        return "autor?faces-redirect=true";
    }

    public Livro getLivro() {
        return livro;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

}
