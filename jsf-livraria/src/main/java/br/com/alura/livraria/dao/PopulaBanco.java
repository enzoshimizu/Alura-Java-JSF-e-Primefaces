package br.com.alura.livraria.dao;

import br.com.alura.livraria.bean.Autor;
import br.com.alura.livraria.bean.Livro;
import jakarta.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PopulaBanco {

    public static void main(String[] args) {
        try (EntityManager em = new JPAUtil().getEntityManager()) {
            // Verifica e cria as tabelas necessárias se não existirem
            criarTabelasSeNaoExistirem(em);

            // Inicia a transação para inserção dos dados
            em.getTransaction().begin();

            Autor assis = geraAutor("Machado de Assis");
            em.persist(assis);

            Autor amado = geraAutor("Jorge Amado");
            em.persist(amado);

            Autor coelho = geraAutor("Paulo Coelho");
            em.persist(coelho);

            Livro casmurro = geraLivro("978-8-52-504464-8", "Dom Casmurro",
                    "10/01/1899", 24.90, assis);
            Livro memorias = geraLivro("978-8-50-815415-9",
                    "Memorias Postumas de Bras Cubas", "01/01/1881", 19.90, assis);
            Livro quincas = geraLivro("978-8-50-804084-1", "Quincas Borba",
                    "01/01/1891", 16.90, assis);

            em.persist(casmurro);
            em.persist(memorias);
            em.persist(quincas);

            Livro alquemista = geraLivro("978-8-57-542758-3", "O Alquimista",
                    "01/01/1988", 19.90, coelho);
            Livro brida = geraLivro("978-8-50-567258-7", "Brida", "01/01/1990",
                    12.90, coelho);
            Livro valkirias = geraLivro("978-8-52-812458-8", "As Valkirias",
                    "01/01/1992", 29.90, coelho);
            Livro maao = geraLivro("978-8-51-892238-9", "O Diario de um Mago",
                    "01/01/1987", 9.90, coelho);

            em.persist(alquemista);
            em.persist(brida);
            em.persist(valkirias);
            em.persist(maao);

            Livro capitaes = geraLivro("978-8-50-831169-1", "Capitaes da Areia",
                    "01/01/1937", 6.90, amado);
            Livro flor = geraLivro("978-8-53-592569-9",
                    "Dona Flor e Seus Dois Maridos", "01/01/1966", 18.90, amado);

            em.persist(capitaes);
            em.persist(flor);

            em.getTransaction().commit();
            System.out.println("Banco populado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao popular o banco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Verifica se as tabelas SEQUENCE, AUTOR, LIVRO e LIVRO_AUTOR existem e, se
     * não existirem, as cria.
     */
    private static void criarTabelasSeNaoExistirem(EntityManager em) {
        // Verifica a existência de cada tabela e cria se necessário
        boolean sequenceExists = tabelaExiste(em, "SEQUENCE");
        boolean autorExists = tabelaExiste(em, "AUTOR");
        boolean livroExists = tabelaExiste(em, "LIVRO");
        boolean livroAutorExists = tabelaExiste(em, "LIVRO_AUTOR");

        // Inicia a transação apenas para criar as tabelas que não existem
        em.getTransaction().begin();
        try {
            if (!sequenceExists) {
                em.createNativeQuery(
                        "CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(15), PRIMARY KEY (SEQ_NAME))"
                ).executeUpdate();
                em.createNativeQuery(
                        "INSERT INTO SEQUENCE (SEQ_NAME, SEQ_COUNT) VALUES ('SEQ_GEN', 0)"
                ).executeUpdate();
                System.out.println("Tabela SEQUENCE criada.");
            } else {
                System.out.println("Tabela SEQUENCE já existe.");
            }

            if (!autorExists) {
                em.createNativeQuery(
                        "CREATE TABLE AUTOR (ID INTEGER NOT NULL, NOME VARCHAR(255), PRIMARY KEY (ID))"
                ).executeUpdate();
                System.out.println("Tabela AUTOR criada.");
            } else {
                System.out.println("Tabela AUTOR já existe.");
            }

            if (!livroExists) {
                em.createNativeQuery(
                        "CREATE TABLE LIVRO (ID INTEGER NOT NULL, ISBN VARCHAR(255), TITULO VARCHAR(255), PRECO DECIMAL(10,2), DATALANCAMENTO DATE, PRIMARY KEY (ID))"
                ).executeUpdate();
                System.out.println("Tabela LIVRO criada.");
            } else {
                System.out.println("Tabela LIVRO já existe.");
            }

            if (!livroAutorExists) {
                em.createNativeQuery(
                        "CREATE TABLE LIVRO_AUTOR (Livro_ID INTEGER NOT NULL, autores_ID INTEGER NOT NULL, "
                        + "PRIMARY KEY (Livro_ID, autores_ID), "
                        + "FOREIGN KEY (Livro_ID) REFERENCES LIVRO(ID), "
                        + "FOREIGN KEY (autores_ID) REFERENCES AUTOR(ID))"
                ).executeUpdate();
                System.out.println("Tabela LIVRO_AUTOR criada.");
            } else {
                System.out.println("Tabela LIVRO_AUTOR já existe.");
            }

            em.getTransaction().commit();
            System.out.println("Tabelas verificadas/criadas com sucesso.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Verifica se uma tabela existe no banco de dados sem afetar a transação.
     */
    private static boolean tabelaExiste(EntityManager em, String tableName) {
        try {
            em.createNativeQuery("SELECT 1 FROM " + tableName + " LIMIT 1").getSingleResult();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Autor geraAutor(String nome) {
        Autor autor = new Autor();
        autor.setNome(nome);
        return autor;
    }

    private static Livro geraLivro(String isbn, String titulo, String data,
            double preco, Autor autor) {
        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro.setTitulo(titulo);
        livro.setDataLancamento(parseData(data));
        livro.setPreco(preco);
        livro.adicionaAutor(autor);
        return livro;
    }

    @SuppressWarnings("unused")
    private static Calendar parseData(String data) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
