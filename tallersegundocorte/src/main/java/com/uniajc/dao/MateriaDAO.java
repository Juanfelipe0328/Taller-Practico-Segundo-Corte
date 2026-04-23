package com.uniajc.dao;

import com.uniajc.config.ConexionDB;
import com.uniajc.modelo.Materia;
import java.sql.*;
import java.util.*;

public class MateriaDAO {

    public void guardar(Materia m) {
        try (Connection c = ConexionDB.getConexion()) {
            PreparedStatement ps =
              c.prepareStatement("INSERT INTO materia VALUES(?,?,?)");
            ps.setInt(1, m.id);
            ps.setString(2, m.nombre);
            ps.setInt(3, m.creditos);
            ps.execute();
        } catch (Exception e) {
            System.out.println("Error MateriaDAO");
        }
    }

    public List<Materia> listar() {
        List<Materia> lista = new ArrayList<>();
        try (Connection c = ConexionDB.getConexion()) {
            ResultSet rs =
              c.createStatement().executeQuery("SELECT * FROM materia");
            while (rs.next()) {
                lista.add(new Materia(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3)
                ));
            }
        } catch (Exception e) {}
        return lista;
    }
}