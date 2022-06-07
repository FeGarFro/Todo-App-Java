package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Felipe Garcia
 */
public class TaskController {

    public void save(Task task) {
        String sql = "INSERT INTO "
                + "tasks("
                + "projectId, "
                + "name, "
                + "description, "
                + "completed, "
                + "notes, "
                + "deadline, "
                + "createdAt, "
                + "updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection c = null;
        PreparedStatement s = null;

        try {
            //Cria uma conexão com o banco
            c = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            s = c.prepareStatement(sql);

            s.setInt(1, task.getprojectId());
            s.setString(2, task.getName());
            s.setString(3, task.getDescription());
            s.setBoolean(4, task.isCompleted());
            s.setString(5, task.getNotes());
            s.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
            s.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
            s.setDate(8, new java.sql.Date(task.getUpdatedAt().getTime()));

            //Executa a sql para inserï¿½ï¿½o dos dados
            s.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
        } finally {
            //Fecha as conexï¿½es
            try {
                if (s != null) {
                    s.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }

    }

    public void update(Task task) {

        String sql = "UPDATE tasks SET projectId = ?, "
                + "name = ?, "
                + "description = ?, "
                + "completed = ?, "
                + "notes = ?, "
                + "deadline = ?, "
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";

        Connection c = null;
        PreparedStatement s = null;

        try {
            //Cria uma conexão com o banco
            c = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            s = c.prepareStatement(sql);

            s.setInt     (1, task.getprojectId());
            s.setString  (2, task.getName());
            s.setString  (3, task.getDescription());
            s.setBoolean (4, task.isCompleted());
            s.setString  (5, task.getNotes());
            s.setDate    (6, new java.sql.Date(task.getDeadline().getTime()));
            s.setDate    (7, new java.sql.Date(task.getCreatedAt().getTime()));
            s.setDate    (8, new java.sql.Date(task.getUpdatedAt().getTime()));
            s.setInt     (9, task.getId());

            //Executa a sql para inserção dos dados
            s.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro em atualizar a tarefa", ex);
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
    }

    public List<Task> getAll() {
        String sql = "SELECT * FROM tasks";

        List<Task> tasks = new ArrayList<>();

        Connection c = null;
        PreparedStatement s = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet r = null;

        try {
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);

            r = s.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (r.next()) {

                Task task = new Task();

                task.setId(r.getInt("id"));
                task.setprojectId(r.getInt("projectId"));
                task.setName(r.getString("name"));
                task.setDescription(r.getString("description"));
                task.setNotes(r.getString("notes"));
                task.setCompleted(r.getBoolean("completed"));
                task.setDeadline(r.getDate("deadline"));
                task.setCreatedAt(r.getDate("createdAt"));
                task.setCreatedAt(r.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
        } finally {
            try {
                if (r != null) {
                    r.close();
                }
                if (s != null) {
                    s.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexÃ£o", ex);
            }
        }
        return tasks;
    }

    public List<Task> getByProjectId(int id) {
        String sql = "SELECT * FROM tasks where projectId = ?";

        List<Task> tasks = new ArrayList<>();

        Connection c = null;
        PreparedStatement s = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet r = null;

        try {
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);

            s.setInt(1, id);

            r = s.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (r.next()) {

                Task task = new Task();

                task.setId(r.getInt("id"));
                task.setprojectId(r.getInt("projectId"));
                task.setName(r.getString("name"));
                task.setDescription(r.getString("description"));
                task.setDeadline(r.getDate("deadline"));
                task.setNotes(r.getString("notes"));
                task.setCompleted(r.getBoolean("completed"));
                task.setCreatedAt(r.getDate("createdAt"));
                task.setCreatedAt(r.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                tasks.add(task);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
        } finally {
            ConnectionFactory.closeConnection(c, s, r);
        }
        return tasks;
    }

    public void removeById(int id) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection c = null;
        PreparedStatement s = null;

        try {
            c = ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tarefa", ex);
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }

    }

}
