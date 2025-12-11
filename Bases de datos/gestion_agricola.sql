
DROP DATABASE IF EXISTS gestion_agricola; 
CREATE DATABASE IF NOT EXISTS gestion_agricola;
USE gestion_agricola;

-- 2. CREACIÓN DE TABLAS

-- Tabla de Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    estado VARCHAR(20) DEFAULT 'activo'
);

-- Tabla de Trabajadores
CREATE TABLE IF NOT EXISTS trabajadores (
    id_trabajador INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100),
    telefono VARCHAR(20), 
    puesto VARCHAR(50),
    horario VARCHAR(100),
    salario DECIMAL(10, 2)
);

-- Tabla de Cultivos (CORREGIDA: Eliminado 'unidad_area')
CREATE TABLE IF NOT EXISTS cultivos (
    id_cultivo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    area_sembrada DECIMAL(10, 2), -- CORREGIDO: De int a DECIMAL(10, 2)
    estado_crecimiento VARCHAR(50),
    fecha_siembra DATE,
    fecha_cosecha DATE
);

CREATE TABLE IF NOT EXISTS almacenes (
  id_almacen INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  capacidad_kg DECIMAL(10, 2),
  tipo VARCHAR(50) 
);

CREATE TABLE IF NOT EXISTS productos_almacenados (
  id_producto_almacenado INT AUTO_INCREMENT PRIMARY KEY,
  id_almacen INT NOT NULL,
  id_cultivo INT NOT NULL,
  cantidad_kg DECIMAL(10, 2) NOT NULL,
  fecha_ingreso DATE NOT NULL,
  fecha_egreso DATE,
  
  FOREIGN KEY (id_almacen) REFERENCES almacenes(id_almacen),
  FOREIGN KEY (id_cultivo) REFERENCES cultivos(id_cultivo),
  
  UNIQUE (id_almacen, id_cultivo)
);


-- Tabla de Distribuciones (CORREGIDA: Se usa id_trabajador_responsable)
CREATE TABLE IF NOT EXISTS distribuciones (
    id_distribucion INT AUTO_INCREMENT PRIMARY KEY,
    id_producto_almacenado INT NOT NULL,
    cantidad DECIMAL(10, 2) NOT NULL,
    destino VARCHAR(200) NOT NULL,
    fecha_distribucion DATE NOT NULL,
    -- Se asume que el ID del trabajador responsable en esta tabla es la CÉDULA
    id_trabajador_responsable VARCHAR(20) NOT NULL, 
    
    FOREIGN KEY (id_producto_almacenado) REFERENCES productos_almacenados(id_producto_almacenado),
    FOREIGN KEY (id_trabajador_responsable) REFERENCES trabajadores(cedula)
);

-- Tabla de Datos de Producción (Cosecha)
CREATE TABLE IF NOT EXISTS datos_produccion (
    id_produccion INT AUTO_INCREMENT PRIMARY KEY,
    id_cultivo INT NOT NULL,
    fecha_cosecha DATE NOT NULL,
    cantidad_recolectada_kg INT NOT NULL,
    calidad INT, -- Escala de 1 a 10
    destino VARCHAR(200),
    Rendimiento double(10,2),
    
    FOREIGN KEY (id_cultivo) REFERENCES cultivos(id_cultivo)
);

-- 3. DATOS DE PRUEBA (Ejemplos)

-- Usuarios
INSERT INTO usuarios (nombre_usuario, contrasena, rol, estado) VALUES 
('admin', 'admin123', 'administrador', 'activo'),
('user', 'User123', 'usuario', 'activo');

-- Trabajadores
INSERT INTO trabajadores (cedula, nombre, correo, telefono, puesto, horario, salario) VALUES 
('101110111', 'Juan Perez', 'juan@agricola.com', '8888-8888', 'Agricultor', 'L-V 6am-2pm', 450000.00),
('202220222', 'Maria Rodriguez', 'maria@agricola.com', '9999-9999', 'Gerente', 'L-V 8am-5pm', 850000.00);

-- Cultivos (CORREGIDO: Eliminado 'm2')
INSERT INTO cultivos (nombre, tipo, area_sembrada, estado_crecimiento, fecha_siembra, fecha_cosecha) VALUES 
('Tomate', 'Hortaliza', 500.00, 'Cosechado', '2023-01-15', '2023-05-20'),
('Cafe', 'Grano', 1000.00, 'Floracion', '2023-02-10', '2024-11-15');

-- Almacenes (CORREGIDO: Se añade el campo 'tipo')
INSERT INTO almacenes (nombre, capacidad_kg, tipo) VALUES 
('Bodega Principal', 5000.00, 'General'),
('Cuarto Frio', 2000.00, 'Refrigerado');

-- Productos Almacenados (Asumiendo que tomate es id=1, café es id=2. Bodega Principal es id=1, Cuarto Frio es id=2)
INSERT INTO productos_almacenados (id_almacen, id_cultivo, cantidad_kg, fecha_ingreso, fecha_egreso) VALUES
(1, 1, 1500.50, '2023-05-21', NULL), -- 1500.5 kg de Tomate en Bodega Principal
(2, 2, 500.00, '2024-01-10', NULL); -- 500 kg de Café en Cuarto Frío

-- Producción
INSERT INTO datos_produccion (id_cultivo, fecha_cosecha, cantidad_recolectada_kg, calidad, destino) VALUES
(1, '2023-05-20', 1600, 9, 'Almacén 1'),
(2, '2024-01-09', 550, 8, 'Almacén 2');

-- Distribuciones (Juan Perez: '101110111'. Tomate: id=1. Cafe: id=2)
INSERT INTO distribuciones (id_producto_almacenado, cantidad, destino, fecha_distribucion, id_trabajador_responsable) VALUES
(1, 100.00, 'Mercado Central', '2023-05-22', '101110111'); -- 100 kg de Tomate
ALTER TABLE trabajadores MODIFY COLUMN contrasena VARCHAR(255);
UPDATE trabajadores SET contrasena = '$2a$12$ABC123...' WHERE cedula = 101110111;
UPDATE trabajadores SET contrasena = '$2a$12$DEF456...' WHERE cedula = 202220222;