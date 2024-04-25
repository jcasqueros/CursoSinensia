DROP TABLE CLIENTE_PRODUCTO IF EXISTS;
DROP TABLE CLIENTE IF EXISTS;
DROP TABLE SEQUENCE IF EXISTS;
DROP TABLE PRODUCTO IF EXISTS;
DROP TABLE COMERCIAL IF EXISTS;

CREATE TABLE SEQUENCE(
	nombre					VARCHAR(50),
	valor					BIGINT NOT NULL,
	PRIMARY KEY (nombre)
);

CREATE TABLE PRODUCTO(
	codigo					BIGINT,
	nombre					VARCHAR(20),
	precio					DOUBLE NOT NULL,
	fecha_alta				DATE,
	familia					ENUM('HARDWARE', 'SOFTWARE', 'CONSUMIBLE'),
	descatalogado			BOOLEAN,
	PRIMARY KEY (codigo)
);

CREATE TABLE COMERCIAL(
	codigo					BIGINT,
	nombre					VARCHAR(20),
	apellido1				VARCHAR(20),
	apellido2				VARCHAR(20),
	PRIMARY KEY (codigo)
);

CREATE TABLE CLIENTE(
	identificador_fiscal	VARCHAR(20),
	nombre_comercial		VARCHAR(20),
	nombre					VARCHAR(20),
	apellido1				VARCHAR(20),
	apellido2				VARCHAR(20),
	direccion				VARCHAR(20),
	poblacion				VARCHAR(20),
	cpostal					VARCHAR(20),
	provincia				VARCHAR(20),
	pais					VARCHAR(20),
	telefono1				VARCHAR(20),
	telefono2				VARCHAR(20),
	email					VARCHAR(50),
	codigo					BIGINT,
	CONSTRAINT comercialFK FOREIGN KEY (codigo) REFERENCES COMERCIAL(codigo),
	PRIMARY KEY (identificador_fiscal)
);


CREATE TABLE CLIENTE_PRODUCTO(
	identificador_fiscal	VARCHAR(20),
	codigo					BIGINT,
	CONSTRAINT clienteFK FOREIGN KEY (identificador_fiscal) REFERENCES CLIENTE(identificador_fiscal),
	CONSTRAINT productoFK FOREIGN KEY (codigo) REFERENCES PRODUCTO(codigo),
	PRIMARY KEY (identificador_fiscal, codigo)
);