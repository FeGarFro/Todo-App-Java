package model;

import java.util.Date;

/**
 *
 * @author Felipe Garcia
 */
public class Task {

    private int id;
    private int projectId;
    private String name;
    private String description;
    private String notes;
    private Date deadline;
    private boolean completed;
    private Date createdAt;
    private Date updatedAt;

    public Task() {
        this.completed = false;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Task(String name, String description, String notes, Date deadline, boolean isCompleted, Date createdAt, Date updatedAt) {
        this.name = name;
        this.description = description;
        this.notes = notes;
        this.deadline = deadline;
        this.completed = isCompleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getprojectId() {
        return projectId;
    }

    public void setprojectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
