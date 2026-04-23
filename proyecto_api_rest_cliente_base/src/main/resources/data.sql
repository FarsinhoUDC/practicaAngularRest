INSERT INTO categorias (id, nombreCategoria) VALUES (1, 'Bronce');
INSERT INTO categorias (id, nombreCategoria) VALUES (2, 'Plata');
INSERT INTO categorias (id, nombreCategoria) VALUES (3, 'Oro');

INSERT INTO clientes (nombre, apellido, email, createAt, idCategoria) VALUES 
('Juan', 'Perez', 'juan@unicauca.edu.co', '2025-01-22', 1);

INSERT INTO clientes (nombre, apellido, email, createAt, idCategoria) VALUES 
('Catalina', 'Lopez', 'catalina@unicauca.edu.co', '2025-03-22', 2);

INSERT INTO clientes (nombre, apellido, email, createAt, idCategoria) VALUES 
('Sandra', 'Sanchez', 'sandra@unicauca.edu.co', '2025-06-22', 3);


INSERT INTO clientes (nombre, apellido, email, createAt, idCategoria) VALUES 
('Andres', 'Perez', 'andres@unicauca.edu.co', '2025-04-22', 1);

INSERT INTO medicos (nombre, apellido, email, createAt) VALUES 
('Alejandra', 'Naranjo', 'ale@unicauca.edu.co', '2025-04-9');

INSERT INTO medicos (nombre, apellido, email, createAt) VALUES 
('Yamilet', 'Bolanios', 'yami@unicauca.edu.co', '2025-04-9');

INSERT INTO franjas_horarias (horaInicio, horaFin, fecha, estado, idMedico) VALUES
('07:00:00', '07:30:00', '2025-05-10', 'DISPONIBLE', 1),
('07:30:00', '08:00:00', '2025-05-10', 'DISPONIBLE', 1),
('08:00:00', '08:30:00', '2025-05-10', 'OCUPADA',    1),
('08:30:00', '09:00:00', '2025-05-10', 'DISPONIBLE', 1),
('07:00:00', '07:30:00', '2025-05-10', 'DISPONIBLE', 2),
('07:30:00', '08:00:00', '2025-05-10', 'OCUPADA', 2);