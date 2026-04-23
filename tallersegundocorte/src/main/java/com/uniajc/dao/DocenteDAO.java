package com.uniajc.dao;

import com.uniajc.config.ConexionDB;
import com.uniajc.modelo.Docente;
import java.sql.*;
import java.util.*;

public class DocenteDAO {

    public void guardar(Docente d) {
        try (Connection c = ConexionDB.getConexion()) {
            PreparedStatement ps =
              c.prepareStatement("INSERT INTO docente VALUES(?,?,?)");
            ps.setInt(1, d.id);
            ps.setString(2, d.nombre);
            ps.setString(3, d.especialidad);
            ps.execute();
        } catch (Exception e) {}
    }

    
    public List<Docente> listar() {
        List<Docente> lista = new ArrayList<>();
        try (Connection c = ConexionDB.getConexion()) {
            ResultSet rs =
              c.createStatement().executeQuery("SELECT * FROM docente");
            while (rs.next()) {
                lista.add(new Docente(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
                ));
            }
        } catch (Exception e) {}
        return lista;
    }
}