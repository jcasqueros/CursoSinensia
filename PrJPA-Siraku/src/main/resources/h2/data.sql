INSERT INTO COMERCIAL (codigo, nombre, apellido1, apellido2)
VALUES (1, 'Mario', 'Sanchez', 'Pilar'), (2, 'Manuel', 'Mateos', 'de Torres'), (3, 'Francisco', 'Balonero', 'Olivera');

INSERT INTO CLIENTE (identificador_fiscal, nombre_comercial, nombre, apellido1, apellido2, direccion, poblacion, cpostal, provincia, pais, telefono1, telefono2, email, codigo) 
VALUES ('1', 'NombreC', 'Nombre', 'Apellido1', 'Apellido2', 'Direccion', 'Poblacion', 'CPostal', 'Provincia', 'Pais', '626053637', '626053638', 'mario@email.com', 1);

INSERT INTO PRODUCTO (codigo, nombre, precio, fecha_alta, familia, descatalogado)
VALUES (1, 'Producto', 1, '2024-01-01', 'HARDWARE', false);

INSERT INTO CLIENTE_PRODUCTO (identificador_fiscal, codigo) VALUES (1,1);

INSERT INTO SEQUENCE (nombre, valor) VALUES ('COMERCIAL_SEQ', 3), ('PRODUCTO_SEQ', 1);