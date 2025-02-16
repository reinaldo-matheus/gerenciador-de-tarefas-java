package com.taskmanager.dao;

import com.taskmanager.model.Task;
import com.taskmanager.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TaskDAOImpl implements TaskDAO {

    @Override
    public void save(Task task) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Task task) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Task task) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            task = em.find(Task.class, task.getId());
            em.remove(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Task findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Task> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t", Task.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Task> findByTitle(String title) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Task> query = em.createQuery(
                    "SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(:title)",
                    Task.class
            );
            query.setParameter("title", "%" + title + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Task> findByResponsible(String responsible) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Task> query = em.createQuery(
                    "SELECT t FROM Task t WHERE LOWER(t.responsible) LIKE LOWER(:responsible)",
                    Task.class
            );
            query.setParameter("responsible", "%" + responsible + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Task> findByPriority(Task.Priority priority) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Task> query = em.createQuery(
                    "SELECT t FROM Task t WHERE t.priority = :priority",
                    Task.class
            );
            query.setParameter("priority", priority);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}