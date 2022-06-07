package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Felipe Garcia
 */
public class ProjectController {

    public void save(Project project) {
        String sql = "INSERT INTO projects(name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)";

        Connection c= null;
        PreparedStatement s = null;

        try {
            //Cria uma conexï¿½o com o banco
            c = ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            s = c.prepareStatement(sql);

            s.setString(1, project.getName());
            s.setString(2, project.getDescription());
            s.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            s.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));

            //Executa a sql para inserção dos dados
            s.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar o projeto", ex);
        } finally {
            ConnectionFactory.closeConnection(c, s);
        }
    }

    public void update(Project project) {

        String sql = "UPDATE projects SET name = ?, description = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection c= null;
        PreparedStatement s = null;

        try {
            //Cria uma conexão com o banco
            c= ConnectionFactory.getConnection();
            //Cria um PreparedStatment, classe usada para executar a query
            s = c.prepareStatement(sql);

            s.setString(1, project.getName());
            s.setString(2, project.getDescription());
            s.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            s.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
            s.setInt(4, project.getId());

            //Executa a sql para inserção dos dados
            s.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro em atualizar o projeto", ex);
        } finally {
                        ConnectionFactory.closeConnection(c, s);
        }
    }

    public List<Project> getAll() {
        String sql = "SELECT * FROM projects";

        List<Project> projects = new ArrayList<>();

        Connection c= null;
        PreparedStatement s = null;

        //Classe que vai recuperar os dados do banco de dados
        ResultSet r = null;

        try {
            c= ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            r = s.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (r.next()) {

                Project project = new Project();

                project.setId(r.getInt("id"));
                project.setName(r.getString("name"));
                project.setDescription(r.getString("description"));
                project.setCreatedAt(r.getDate("createdAt"));
                project.setCreatedAt(r.getDate("updatedAt"));

                //Adiciono o contato recuperado, a lista de contatos
                projects.add(project);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar os projetos", ex);
        } finally {
            ConnectionFactory.closeConnection(c, s, r);
        }
        return projects;
    }

    public void removeById(int id) {

        String sql = "DELETE FROM projects WHERE id = ?";

        Connection c= null;
        PreparedStatement s = null;

        try {
            c= ConnectionFactory.getConnection();
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar o projeto", ex);
        } finally {
            ConnectionFactory.closeConnection(c, s);
        }

    }

}
