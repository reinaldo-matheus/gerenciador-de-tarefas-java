package com.taskmanager.bean;

import com.taskmanager.dao.TaskDAO;
import com.taskmanager.dao.TaskDAOImpl;
import com.taskmanager.model.Task;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class TaskBean implements Serializable {

    private Task task = new Task();
    private List<Task> tasks;
    private String searchTitle;
    private String searchResponsible;
    private Task.Priority searchPriority;

    private final TaskDAO taskDAO = new TaskDAOImpl();

    public void save() {
        try {
            if (task.getId() == null) {
                taskDAO.save(task);
                addMessage("Sucesso", "Tarefa criada com sucesso!");
            } else {
                taskDAO.update(task);
                addMessage("Sucesso", "Tarefa atualizada com sucesso!");
            }
            task = new Task();
            tasks = null; // força recarregar a lista
        } catch (Exception e) {
            addMessage("Erro", "Erro ao salvar tarefa: " + e.getMessage());
        }
    }

    public void delete(Task task) {
        try {
            taskDAO.delete(task);
            tasks = null; // força recarregar a lista
            addMessage("Sucesso", "Tarefa removida com sucesso!");
        } catch (Exception e) {
            addMessage("Erro", "Erro ao remover tarefa: " + e.getMessage());
        }
    }

    public void edit(Task task) {
        this.task = task;
    }

    public void search() {
        if (searchTitle != null && !searchTitle.trim().isEmpty()) {
            tasks = taskDAO.findByTitle(searchTitle);
        } else if (searchResponsible != null && !searchResponsible.trim().isEmpty()) {
            tasks = taskDAO.findByResponsible(searchResponsible);
        } else if (searchPriority != null) {
            tasks = taskDAO.findByPriority(searchPriority);
        } else {
            tasks = taskDAO.findAll();
        }
    }

    public void clear() {
        searchTitle = null;
        searchResponsible = null;
        searchPriority = null;
        tasks = null;
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    // Getters and Setters
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getTasks() {
        if (tasks == null) {
            tasks = taskDAO.findAll();
        }
        return tasks;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchResponsible() {
        return searchResponsible;
    }

    public void setSearchResponsible(String searchResponsible) {
        this.searchResponsible = searchResponsible;
    }

    public Task.Priority getSearchPriority() {
        return searchPriority;
    }

    public void setSearchPriority(Task.Priority searchPriority) {
        this.searchPriority = searchPriority;
    }

    public Task.Priority[] getPriorities() {
        return Task.Priority.values();
    }
}