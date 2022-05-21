******************************************************************************
            DDL INICIAL - UNA TABLA PARA REPRESENTAR TRANSPORTES
******************************************************************************

CREATE TABLE IF NOT EXISTS transporte (
  id              INT NOT NULL AUTO_INCREMENT,
  numero          VARCHAR(15) NOT NULL,
  activa          BOOLEAN DEFAULT 1,
  color           VARCHAR(50),
  marca           VARCHAR(100) NOT NULL,    
  encendido       BOOLEAN DEFAULT 1,    
  licencia        VARCHAR(10),      -- ATRIBUTO DE TRANSPORTE
  tipo            VARCHAR(3),       -- ATRIBUTO DE TRANSPORTE
  ejes            INT,              -- ATRIBUTO DE CAMION
  capacidad       DOUBLE,           -- ATRIBUTO DE CAMION
  accesoEspecial  BOOLEAN,          -- ATRIBUTO DE PASAJEROS
  discriminador   CHAR(1) NOT NULL, -- C(CAMION), P(PASAJEROS)
  PRIMARY KEY(id)
);



******************************************************************************
								DDL INICIAL - PERSONAS - UNA TABLA POR JERARQUIA
******************************************************************************

CREATE TABLE director (
  id                  int NOT NULL AUTO_INCREMENT,
  nombre              varchar(30) DEFAULT NULL,
  apellido            varchar(20) DEFAULT NULL,
  tipoDocumento       varchar(3) DEFAULT NULL,
  numeroDocumento     int DEFAULT NULL,
  fechaNacimiento     date DEFAULT NULL,
  fechaCargo          date DEFAULT NULL,
  sueldo              double DEFAULT NULL,
  carrera             varchar(30) DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS administrativo (
  id                  int NOT NULL AUTO_INCREMENT,
  nombre              varchar(45) DEFAULT NULL,
  apellido            varchar(45) DEFAULT NULL,
  tipoDocumento       varchar(3) DEFAULT NULL,
  numeroDocumento     int DEFAULT NULL,
  fechaNacimiento     date DEFAULT NULL,
  fechaCargo          date DEFAULT NULL,
  sueldo              double DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS profesor (
  id                  int NOT NULL AUTO_INCREMENT,
  nombre              varchar(45) DEFAULT NULL,
  apellido            varchar(45) DEFAULT NULL,
  tipoDocumento       varchar(3) DEFAULT NULL,
  numeroDocumento     int DEFAULT NULL,
  fechaNacimiento     date DEFAULT NULL,
  fechaCargo          date DEFAULT NULL,
  sueldo              double DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE alumno (
  id                int NOT NULL AUTO_INCREMENT,
  nombre            varchar(45) NOT NULL,
  apellido          varchar(45) NOT NULL,
  tipoDocumento     varchar(3) DEFAULT NULL,
  numeroDocumento   int DEFAULT NULL,
  fechaNacimiento   date DEFAULT NULL,
  PRIMARY KEY (id)
);



******************************************************************************
	DDL INICIAL - PERSONAS - UNA SOLA TABLA PARA REPRESENTAR EMPLEADOS
******************************************************************************

CREATE DATABASE IF NOT EXISTS personas;

USE personas;

CREATE TABLE IF NOT EXISTS alumno (
  tipoDocumento     char(3) NOT NULL,
  numeroDocumento   int(11) NOT NULL,
  nombre            varchar(100) DEFAULT NULL,
  apellido          varchar(100) DEFAULT NULL,
  fechaNacimiento   date DEFAULT NULL,
  PRIMARY KEY (numeroDocumento, tipoDocumento)
);


CREATE TABLE IF NOT EXISTS curso (
  id            int(11) NOT NULL AUTO_INCREMENT,
  descripcion   varchar(100) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS cursos_alumnos (
  tipoDocumento     char(3) NOT NULL,
  numeroDocumento   int(11) NOT NULL,
  idCurso           int(11) DEFAULT NULL,
  FOREIGN KEY (numeroDocumento, tipoDocumento) REFERENCES alumno (numeroDocumento, tipoDocumento),
  FOREIGN KEY (idCurso) REFERENCES curso (id)
);


-------------------------------------------------------------------------


CREATE TABLE IF NOT EXISTS tipoempleado (
  id            int(11) NOT NULL AUTO_INCREMENT,
  descripcion   varchar(100) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS empleado (
  tipoDocumento     char(3) NOT NULL,
  numeroDocumento   int(11) NOT NULL,
  nombre            varchar(100) DEFAULT NULL,
  apellido          varchar(100) DEFAULT NULL,
  fechaNacimiento   date DEFAULT NULL,
  fechaCargo        date DEFAULT NULL,
  sueldo            double DEFAULT NULL,
  carrera           varchar(50) DEFAULT NULL,
  tipo              int(11) DEFAULT NULL,
  PRIMARY KEY (numeroDocumento, tipoDocumento)
);


CREATE TABLE IF NOT EXISTS cursos_profesor (
  tipoDocumento char(3) NOT NULL,
  numeroDocumento int(11) NOT NULL,
  idCurso int(11) DEFAULT NULL,
  FOREIGN KEY (numeroDocumento, tipoDocumento) REFERENCES empleados (numeroDocumento, tipoDocumento),
  FOREIGN KEY (idCurso) REFERENCES cursos (id)
);



******************************************************************************
								DML INICIAL
******************************************************************************


-- USAR UNA BASE DE DATOS
USE personas;


-- INSERTAMOS LA INFORMACION
INSERT INTO curso (Descripcion) VALUES ('JAVA'),('PYTHON'),('SQL'),('JAVASCRIPT'),('REACT');

INSERT INTO tipoempleado (Descripcion) VALUES ('Alumno'),('Director'),('Profesor'),('Administrativo');

INSERT INTO empleado (TipoDocumento, NumeroDocumento, Nombre, Apellido, FechaNacimiento, FechaCargo, sueldo, Tipo) 
VALUES ('DNI', 95853354, Octavio', 'Robleto', '1983-03-15', '2021-01-01', 25, 4),
('DNI', 95877200, 'Mariana', 'Bracho', '1989-06-06', '2021-01-01', 25, 4);