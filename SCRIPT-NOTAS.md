
CREATE TABLE Estudiante (
  Estudiante_id SERIAL PRIMARY KEY,
  Nombre VARCHAR(255) NOT NULL,
  Fecha_nacimiento DATE NOT NULL
);


CREATE TABLE Carrera (
  idCarrera SERIAL PRIMARY KEY,
  Nombre VARCHAR(255) NOT NULL
  
);


CREATE TABLE Materia (
  idMateria SERIAL PRIMARY KEY,
  Nombre VARCHAR(255) NOT NULL,
  Numero_creditos int NOT NULL
);
select * from materia  


CREATE TABLE Notas (
  idNotas SERIAL PRIMARY KEY,
  Estudiante_id int NOT NULL,
  Materia_id int NOT NULL,
  PrimerParcial DECIMAL(3,1) DEFAULT 0,
  SegundoParcial DECIMAL(3,1) DEFAULT 0,
  Final_examen DECIMAL(3,1) DEFAULT 0,
  FOREIGN KEY (Estudiante_id) REFERENCES Estudiante(Estudiante_id),
  FOREIGN KEY (Materia_id) REFERENCES Materia(idMateria)
);
select * from Notas 



CREATE TABLE Estudiante_Tiene_Carrera (
  Estudiante_Tiene_Carrera_id SERIAL PRIMARY KEY,
  Estudiante_id int NOT NULL,
  Carrera_idCarrera int NOT NULL,

  FOREIGN KEY (Estudiante_id) REFERENCES Estudiante(Estudiante_id),
  FOREIGN KEY (Carrera_idCarrera) REFERENCES Carrera(idCarrera)
);
select * from Estudiante_Tiene_Materia

CREATE TABLE Estudiante_Tiene_Materia (
  Estudiante_Tiene_Materia_id SERIAL PRIMARY KEY,
  Estudiante_id int NOT NULL,
  Materia_id int NOT NULL,

  FOREIGN KEY (Estudiante_id) REFERENCES Estudiante(Estudiante_id),
  FOREIGN KEY (Materia_id) REFERENCES Materia(idMateria)
);


INSERT INTO Estudiante (Nombre, Fecha_nacimiento) VALUES
('Juan Perez', '1998-05-15'),
('Maria Lopez', '2000-10-20'),
('Pedro Ramirez', '1999-03-08');


INSERT INTO Estudiante (Nombre, Fecha_nacimiento) VALUES
('Benito Lopez', '2000-05-15'),
('Helen Lopez', '2000-04-20'),
('Paul Garcia', '1998-03-08');

INSERT INTO Carrera (Nombre) VALUES
('Ingeniería Informática'),
('Administración de Empresas');

INSERT INTO Carrera (Nombre) VALUES
('Psicologia'),
('Financiera');

INSERT INTO Materia (Nombre, Numero_creditos) VALUES
('Programación', 5),
('Contabilidad', 4),
('Matemáticas', 6);
select * from materia 
INSERT INTO Materia (Nombre, Numero_creditos) VALUES
('Web', 5),
('Base de datos', 5),
('Contabilidad', 5),
('Administracion', 5),
('Expresion Oral', 5),
('Taller', 5);
select * from materia 


INSERT INTO Estudiante_Tiene_Carrera (Estudiante_id, Carrera_idCarrera) VALUES
(1, 1),
(2, 2),
(3, 1);
INSERT INTO Estudiante_Tiene_Carrera (Estudiante_id, Carrera_idCarrera) VALUES
(7, 8),
(8, 9),
(9, 9);


INSERT INTO Estudiante_Tiene_Materia (Estudiante_id, Materia_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 2);
INSERT INTO Estudiante_Tiene_Materia (Estudiante_id, Materia_id) VALUES
(7, 2),
(8, 2),
(9, 12),
(1, 9),
(1, 13);

INSERT INTO Notas (Estudiante_id, Materia_id, PrimerParcial, SegundoParcial, Final_examen) VALUES
(1, 1, 8.5, 7.0, 9.0),
(1, 2, 7.0, 8.0, 9.5),
(2, 1, 9.0, 8.5, 7.0),
(2, 3, 8.5, 9.0, 8.5),
(3, 2, 6.5, 7.0, 8.0);
INSERT INTO Notas (Estudiante_id, Materia_id, PrimerParcial, SegundoParcial, Final_examen) VALUES
(7, 2, 2.5, 4.0, 9.0),
(8, 2, 5.0, 7.0, 9.5),
(9, 12, 9.0, 8.0, 6.0);

select * from estudiante e 
INSERT INTO Estudiante (Nombre, Fecha_nacimiento) VALUES
('Pepe', '1998-04-15');

select * from carrera c 
select * from Materia 


CREATE OR REPLACE FUNCTION obtener_materias_de_estudiante(IN estudiante_id int)
RETURNS TABLE (
    Materia_id int,
    Nombre_materia VARCHAR(255),
    Numero_creditos int
) AS $$
BEGIN
    RETURN QUERY
    SELECT m.idMateria, m.Nombre, m.Numero_creditos
    FROM Estudiante_Tiene_Materia em
    INNER JOIN Materia m ON em.Materia_id = m.idMateria
    WHERE em.Estudiante_id = obtener_materias_de_estudiante.estudiante_id; 
END;
$$ LANGUAGE plpgsql;


SELECT * FROM obtener_materias_de_estudiante(1);



CREATE OR REPLACE FUNCTION calcular_promedio_nota(estudiante_id_param int, materia_id_param int)
RETURNS DECIMAL AS $$
DECLARE
    suma DECIMAL;
    cantidad_notas INT;
    promedio DECIMAL;
BEGIN
    
    SELECT SUM(PrimerParcial + SegundoParcial + Final_examen) INTO suma
    FROM Notas
    WHERE Estudiante_id = estudiante_id_param AND Materia_id = materia_id_param;

   
    SELECT COUNT(*) INTO cantidad_notas
    FROM Notas
    WHERE Estudiante_id = estudiante_id_param AND Materia_id = materia_id_param;


    IF cantidad_notas > 0 THEN
        promedio := suma / cantidad_notas;
    ELSE
        promedio := 0; 
    END IF;

    RETURN promedio;
END;
$$ LANGUAGE plpgsql;




SELECT calcular_promedio_nota(1, 1); 

CREATE OR REPLACE FUNCTION ver_notas_estudiante_materia(IN estudiante_id INT, IN materia_id INT)
RETURNS TABLE (
    PrimerParcial DECIMAL(3,1),
    SegundoParcial DECIMAL(3,1),
    Final_examen DECIMAL(3,1)
) AS $$
BEGIN
    RETURN QUERY
    SELECT Notas.PrimerParcial, Notas.SegundoParcial, Notas.Final_examen
    FROM Notas
    WHERE Notas.Estudiante_id = ver_notas_estudiante_materia.estudiante_id
    AND Notas.Materia_id = ver_notas_estudiante_materia.materia_id;
END;
$$ LANGUAGE plpgsql;

select ver_notas_estudiante_materia(1,1)








