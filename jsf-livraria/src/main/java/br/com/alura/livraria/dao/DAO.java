package br.com.alura.livraria.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.metamodel.EntityType;
import java.util.List;
import java.util.Set;

public class DAO<T> {

    private final Class<T> classe;

    public DAO(Class<T> classe) {
        this.classe = classe;
    }

    public void adiciona(T t) {

        // abre transacao
        try ( // consegue a entity manager
                EntityManager em = new JPAUtil().getEntityManager()) {
            // abre transacao
            em.getTransaction().begin();
            // persiste o objeto
            em.persist(t);
            // commita a transacao
            em.getTransaction().commit();
            // fecha a entity manager
        }
    }

    public void remove(T t) {
        try (EntityManager em = new JPAUtil().getEntityManager()) {
            em.getTransaction().begin();

            em.remove(em.merge(t));

            em.getTransaction().commit();
        }
    }

    public void atualiza(T t) {
        try (EntityManager em = new JPAUtil().getEntityManager()) {
            em.getTransaction().begin();

            em.merge(t);

            em.getTransaction().commit();
        }
    }

    public List<T> listaTodos() {
        List<T> lista;
        try (EntityManager em = new JPAUtil().getEntityManager()) {
            CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
            query.select(query.from(classe));
            lista = em.createQuery(query).getResultList();
        }
        return lista;
    }

    public T buscaPorId(Integer id) {
        T instancia;
        try (EntityManager em = new JPAUtil().getEntityManager()) {
            instancia = em.find(classe, id);
        }
        return instancia;
    }

    public int contaTodos() {
        long result;
        try (EntityManager em = new JPAUtil().getEntityManager()) {
            result = (Long) em.createQuery("select count(n) from livro n")
                    .getSingleResult();
        }

        return (int) result;
    }

    public List<T> listaTodosPaginada(int firstResult, int maxResults) {
        List<T> lista;
        try (EntityManager em = new JPAUtil().getEntityManager()) {
            CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
            query.select(query.from(classe));
            lista = em.createQuery(query).setFirstResult(firstResult)
                    .setMaxResults(maxResults).getResultList();
        }
        return lista;
    }

}
