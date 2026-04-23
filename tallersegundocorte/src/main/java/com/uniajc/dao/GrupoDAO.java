package com.uniajc.dao;

import com.uniajc.config.ConexionDB;
import com.uniajc.modelo.Grupo;
import java.sql.*;
import java.util.*;

public class GrupoDAO {

    public void guardar(Grupo g) {
        try (Connection c = ConexionDB.getConexion()) {
            PreparedStatement ps =
              c.prepareStatement("INSERT INTO grupo VALUES(?,?,?,?,?)");
            ps.setInt(1, g.id);
            ps.setInt(2, g.idMateria);
            ps.setInt(3, g.idDocente);
            ps.setString(4, g.aula);
            ps.setString(5, g.horario);
            ps.execute();
        } catch (Exception e) {}
    }

    public List<Grupo> listar() {
        List<Grupo> lista = new ArrayList<>();
        try (Connection c = ConexionDB.getConexion()) {
            ResultSet rs =
              c.createStatement().executeQuery("SELECT * FROM grupo");
            while (rs.next()) {
                lista.add(new Grupo(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5)
                ));
            }
        } catch (Exception e) {}
        return lista;
    }
}