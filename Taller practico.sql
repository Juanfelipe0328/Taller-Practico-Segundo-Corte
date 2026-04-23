
CREATE DATABASE IF NOT EXISTS uniajc;

USE uniajc;

CREATE TABLE estudiante (
    id_estudiante INT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE materia (
    id_materia INT PRIMARY KEY,
    nombre_materia VARCHAR(100),
    creditos INT
);

CREATE TABLE docente (
    id_docente INT PRIMARY KEY,
    nombre VARCHAR(100),
    especialidad VARCHAR(100)
);

CREATE TABLE grupo (
    id_grupo INT PRIMARY KEY,
    id_materia INT,
    id_docente INT,
    aula VARCHAR(50),
    horario VARCHAR(50)
);

CREATE TABLE inscripcion_curso (
    id_inscripcion INT PRIMARY KEY,
    id_estudiante INT,
    id_grupo INT,
    nota_final FLOAT,
    estado VARCHAR(50)
);

show databases;