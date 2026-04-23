package com.uniajc.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/uniajc",
                "root",
                "Juan0328"
            );
        } catch (Exception e) {
            System.out.println("Error de conexión a la BD");
            return null;
        }
    }
}