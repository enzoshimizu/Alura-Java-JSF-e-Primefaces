package br.com.alura.livraria.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue
    private Integer id;

    private String titulo;
    private String isbn;
    private double preco;
    @Temporal(TemporalType.DATE)
    private Calendar dataLancamento;

    @ManyToMany
    private final List<Autor> autores;

    public Livro() {
        this.autores = new ArrayList<>();
        this.dataLancamento = Calendar.getInstance();
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void adicionaAutor(Autor autor) {
        this.autores.add(autor);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Calendar getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Calendar dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

}
