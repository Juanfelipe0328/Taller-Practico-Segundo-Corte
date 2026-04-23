package com.uniajc.dao;

import com.uniajc.config.ConexionDB;
import com.uniajc.modelo.Estudiante;
import java.sql.*;
import java.util.*;

public class EstudianteDAO {

    public void guardar(Estudiante e) {
        try (Connection c = ConexionDB.getConexion()) {
            PreparedStatement ps =
              c.prepareStatement("INSERT INTO estudiante VALUES(?,?,?,?)");
            ps.setInt(1, e.id);
            ps.setString(2, e.nombre);
            ps.setString(3, e.apellido);
            ps.setString(4, e.email);
            ps.execute();
        } catch (Exception ex) {
            System.out.println("Error EstudianteDAO");
        }
    }

    public List<Estudiante> listar() {
        List<Estudiante> lista = new ArrayList<>();
        try (Connection c = ConexionDB.getConexion()) {
            ResultSet rs =
              c.createStatement().executeQuery("SELECT * FROM estudiante");
            while (rs.next()) {
                lista.add(new Estudiante(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
                ));
            }
        } catch (Exception e) {}
        return lista;
    }
}
