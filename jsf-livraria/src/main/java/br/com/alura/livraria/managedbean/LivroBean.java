package br.com.alura.livraria.managedbean;

import br.com.alura.livraria.bean.Autor;
import br.com.alura.livraria.bean.Livro;
import br.com.alura.livraria.dao.DAO;
import jakarta.annotation.PostConstruct;
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

    private Livro livro = new Livro();
    private Integer autorId;
    private List<Livro> livros;

    @PostConstruct
    public void init() {
        livros = new DAO<>(Livro.class).listaTodos();
    }

    public void gravar() {
        System.out.println("Gravando livro " + livro.getTitulo());

        if (livro.getAutores().isEmpty()) {
            //throw new RuntimeException("Livro deve ter pelo menos um Autor");
            FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um autor."));
            return;
        }

        if(this.livro.getId() == null){
            new DAO<>(Livro.class).adiciona(this.livro);
        } else {
            new DAO<>(Livro.class).atualiza(this.livro);
        }
        
        livros = new DAO<>(Livro.class).listaTodos();
    }

    public void removerAutorDoLivro(Autor autor){
        this.livro.getAutores().remove(autor);
    }
    
    public void remover(Integer id) {
        Livro item = new DAO<>(Livro.class).buscaPorId(id);
        new DAO<>(Livro.class).remove(item);
        livros = new DAO<>(Livro.class).listaTodos();
    }

    public void editar(Integer id) {
        this.livro = new DAO<>(Livro.class).buscaPorId(id);
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

    public List<Livro> getLivros() {
        return livros;
    }

}
