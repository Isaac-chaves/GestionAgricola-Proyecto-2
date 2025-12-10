CREATE DATABASE IF NOT EXISTS gestion_agricola;
USE gestion_agricola;

-- ---------------------------------
-- 1. TABLA USUARIOS
-- ---------------------------------
CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    estado VARCHAR(20) DEFAULT 'activo'
);

-- ---------------------------------
-- 2. TABLA TRABAJADORES
-- ---------------------------------
CREATE TABLE IF NOT EXISTS trabajadores (
    id_trabajador INT AUTO_INCREMENT PRIMARY KEY,
    cedula INT NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100),
    telefono VARCHAR(20), 
    puesto VARCHAR(50),
    horario VARCHAR(100),
    salario DECIMAL(10, 2)
);

-- ---------------------------------
-- 3. TABLA CULTIVOS
-- ---------------------------------
CREATE TABLE IF NOT EXISTS cultivos (
    id_cultivo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    area_sembrada INT,
    estado_crecimiento VARCHAR(50),
    fecha_siembra DATE,
    fecha_cosecha DATE
);

-- ---------------------------------
-- 4. TABLA PRODUCCION
-- ---------------------------------
CREATE TABLE IF NOT EXISTS produccion (
    id_produccion INT AUTO_INCREMENT PRIMARY KEY,
    id_cultivo INT NOT NULL,
    fecha_cosecha DATE,
    cantidad_recolectada_kg INT DEFAULT 0,
    calidad_kg INT,
    destino VARCHAR(100),
    FOREIGN KEY (id_cultivo) REFERENCES cultivos(id_cultivo) ON DELETE RESTRICT
);

-- ---------------------------------
-- 5. TABLA ALMACENES
-- ---------------------------------
CREATE TABLE IF NOT EXISTS almacenes (
    id_almacen INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    capacidad_kg DECIMAL(10, 2) NOT NULL
);

-- ---------------------------------
-- 6. TABLA PRODUCTOS_ALMACENADOS
-- ---------------------------------
CREATE TABLE IF NOT EXISTS productos_almacenados (
    id_producto_almacenado INT AUTO_INCREMENT PRIMARY KEY,
    id_almacen INT NOT NULL,
    id_cultivo INT NOT NULL,
    cantidad_kg DECIMAL(10, 2) DEFAULT 0,
    fecha_ingreso DATE,
    fecha_egreso DATE,
    FOREIGN KEY (id_almacen) REFERENCES almacenes(id_almacen) ON DELETE CASCADE,
    FOREIGN KEY (id_cultivo) REFERENCES cultivos(id_cultivo) ON DELETE RESTRICT
);

-- ----------------------------------------------------
-- 7. TABLA DISTRIBUCIONES (MODIFICADA)
--    Se ha añadido id_producto_almacenado
-- ----------------------------------------------------
CREATE TABLE IF NOT EXISTS distribuciones (
    id_distribucion INT AUTO_INCREMENT PRIMARY KEY,
    id_cultivo INT NOT NULL,
    id_producto_almacenado INT, -- <--- COLUMNA AÑADIDA PARA RESOLVER EL ERROR
    cedula_responsable INT,
    cantidad DECIMAL(10, 2),
    destino VARCHAR(150),
    fecha_distribucion DATE,
    FOREIGN KEY (id_cultivo) REFERENCES cultivos(id_cultivo),
    FOREIGN KEY (id_producto_almacenado) REFERENCES productos_almacenados(id_producto_almacenado), -- <--- LLAVE FORÁNEA AÑADIDA
    FOREIGN KEY (cedula_responsable) REFERENCES trabajadores(cedula)
);


-- ---------------------------------
-- INSERCIÓN DE DATOS
-- ---------------------------------

INSERT INTO usuarios (nombre_usuario, contrasena, rol, estado) VALUES 
('admin', 'Admin123', 'administrador', 'activo'),
('user', 'User123', 'usuario', 'activo');

-- Insertar trabajadores
INSERT INTO trabajadores (cedula, nombre, correo, telefono, puesto, horario, salario) VALUES 
(101110111, 'Juan Perez', 'juan@agricola.com', '8888-8888', 'Agricultor', 'L-V 6am-2pm', 450000.00),
(202220222, 'Maria Rodriguez', 'maria@agricola.com', '9999-9999', 'Gerente', 'L-V 8am-5pm', 850000.00);

-- Insertar cultivos
INSERT INTO cultivos (nombre, tipo, area_sembrada, estado_crecimiento, fecha_siembra) VALUES 
('Tomate', 'Hortaliza', 500, 'Maduracion', '2023-01-15'),
('Cafe', 'Grano', 1000, 'Floracion', '2023-02-10');

-- Insertar almacenes
INSERT INTO almacenes (nombre, capacidad_kg) VALUES 
('Bodega Principal', 5000),
('Cuarto Frio', 2000),
('Granero', 3000);