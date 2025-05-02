package br.com.alura.livraria.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("livraria");
        } catch (Throwable ex) {
            System.err.println(">>> Erro ao criar EntityManagerFactory:");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public EntityManager getEntityManager() {
        try {
            return emf.createEntityManager();
        } catch (Throwable ex) {
            System.err.println(">>> Erro ao criar EntityManager:");
            ex.printStackTrace();
            throw ex;
        }
    }

    public void close(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
