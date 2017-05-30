/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db.controller;

import com.kaab.proyecto.db.Usuario;
import com.kaab.proyecto.db.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author esperanzahigareda
 */
public class UsuarioJpaController implements Serializable {
    /**
     * @param pEmf Entity Manager Factory
     */
    public UsuarioJpaController(final EntityManagerFactory pEmf) {
        this.emf = pEmf;
    }
    /**
     * Interfaz para hacer consultas en la base de datos.
     */
    private EntityManagerFactory emf = null;
    /**
     * @return emf
     */
    public final EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    /**
     * @param usuario el usuario a crear
     */
    public final void create(final Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @param usuario el usuario a editar
     * @throws NonexistentEntityException excepcion
     * @throws Exception excepcion
     */
    public final void edit(Usuario usuario) throws NonexistentEntityException,
            Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id "
                            + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @param id del Usuario
     * @throws NonexistentEntityException excepcion
     */
    public final void destroy(final Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id "
                    + id + " no longer exists.", enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @return UsuarioEntities
     */
    public final List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }
    /**
     * @param maxResults resultados
     * @param firstResult   resultado
     * @return  UsuarioEntities
     */
    public final List<Usuario> findUsuarioEntities(final int maxResults,
            final int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }
    /**
     * @param all all
     * @param maxResults resultados
     * @param firstResult resultado
     * @return ResultList
     */
    private List<Usuario> findUsuarioEntities(final boolean all,
            final int maxResults, final int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    /**
     * @param id id del Usuario
     * @return  Usuario
     */
    public final Usuario findUsuario(final Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }
    /**
     * @return numero
     */
    public final int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    /**
     * @return ResultList
     */
    public final List<Usuario> getUsuarios() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q = em.createQuery("select idUsuario,"
       + " concat(nombre, \" \", app) as nombre_completo, activo from Usuario");
            return (List<Usuario>) q.getResultList();
        } finally {
            em.close();
        }
    }
}
