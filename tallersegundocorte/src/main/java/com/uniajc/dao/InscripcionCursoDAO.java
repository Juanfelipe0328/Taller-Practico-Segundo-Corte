package com.uniajc.dao;

import com.uniajc.config.ConexionDB;
import com.uniajc.modelo.InscripcionCurso;
import java.sql.*;
import java.util.*;

public class InscripcionCursoDAO {

    public void guardar(InscripcionCurso i) {
        try (Connection c = ConexionDB.getConexion()) {
            PreparedStatement ps =
              c.prepareStatement("INSERT INTO inscripcion_curso VALUES(?,?,?,?,?)");
            ps.setInt(1, i.id);
            ps.setInt(2, i.idEstudiante);
            ps.setInt(3, i.idGrupo);
            ps.setFloat(4, i.nota);
            ps.setString(5, i.estado);
            ps.execute();
        } catch (Exception e) {}
    }

    public List<InscripcionCurso> listar() {
        List<InscripcionCurso> lista = new ArrayList<>();
        try (Connection c = ConexionDB.getConexion()) {
            ResultSet rs =
              c.createStatement().executeQuery("SELECT * FROM inscripcion_curso");
            while (rs.next()) {
                lista.add(new InscripcionCurso(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getFloat(4),
                    rs.getString(5)
                ));
            }
        } catch (Exception e) {}
        return lista;
    }
}