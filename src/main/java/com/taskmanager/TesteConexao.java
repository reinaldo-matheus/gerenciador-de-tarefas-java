package com.taskmanager;

import com.taskmanager.model.Task;
import com.taskmanager.util.JPAUtil;
import javax.persistence.EntityManager;

public class TesteConexao {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Task task = new Task();
            task.setTitle("Teste de Conexão");
            task.setDescription("Testando a conexão com o banco");
            task.setPriority(Task.Priority.MEDIUM);

            em.persist(task);
            em.getTransaction().commit();

            System.out.println("Tarefa salva com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}