create database Arkham_Properties;

use Arkham_Properties;

/*
rol
id_rol
rol
*/
CREATE TABLE ROL(
ID_ROL BIGINT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
ROL VARCHAR(35) NOT NULL UNIQUE
);
/*
PROPIETARIO
id_propietario
nombre_propietario
apellido_propietario
telefono_propietario
email_propietario
*/
CREATE TABLE PROPIETARIO(
ID_PROPIETARIO BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
NOMBRE VARCHAR(35) NOT NULL,
APELLIDO VARCHAR(35) NOT NULL,
TELEFONO VARCHAR(11)  NOT NULL,
EMAIL NVARCHAR(50) DEFAULT 'NO REGISTRA' NOT NULL
);

/*
ADMINISTRADOR
id_administrador
nombre_administrador
apellido_administrador
telefono_administrador
email_administrador
*/
CREATE TABLE ADMINISTRADOR(
ID_ADMINISTRADOR BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
NOMBRE VARCHAR(35) NOT NULL,
APELLIDO VARCHAR(35) NOT NULL,
TELEFONO VARCHAR(11)  NOT NULL,
EMAIL NVARCHAR(50) DEFAULT 'NO REGISTRA' NOT NULL
);

/*
CLIENTE
id_cliente
nombre_cliente
apellido_cliente
telefono_cliente
email_cliente
*/
CREATE TABLE CLIENTE(
ID_CLIENTE BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
NOMBRE VARCHAR(35) NOT NULL,
APELLIDO VARCHAR(35) NOT NULL,
TELEFONO VARCHAR(11)  NOT NULL,
EMAIL NVARCHAR(50) DEFAULT 'NO REGISTRA' NOT NULL
);

/*
OFERTA
id_oferta
oferta
*/
CREATE TABLE OFERTA(
ID_OFERTA BIGINT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
OFERTA VARCHAR(35) NOT NULL
);
/*
TIPO_INMUEBLE
id_tipo_inmueble
tipo_inmueble
*/
CREATE TABLE TIPO_INMUEBLE(
ID_TIPO_INMUEBLE BIGINT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
TIPO_INMUEBLE VARCHAR(35) NOT NULL
);
/*
UBICACIÓN
id_ubicacion
ubicacion
*/
CREATE TABLE UBICACION(
ID_UBICACION BIGINT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
UBICACION VARCHAR(35) NOT NULL
);

CREATE TABLE INFORMACION(
ID_INFORMACION BIGINT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
NUMERO_HABITACIONES INT NOT NULL,
NUMERO_BANHOS INT NOT NULL,
AREA DECIMAL(18,1) NOT NULL,
PATIO BIT DEFAULT 0,
NIVEL INT DEFAULT 1 NOT NULL,
GAS_NATURAL BIT DEFAULT 0,
ZONA_DE_ROPAS BIT DEFAULT 0
);

/*
INMUEBLE
id_inmueble
ubicacion
direccion
precio
disponibilidad

id_propietario
id_oferta
id_tipo_inmueble
id_caracteristicas
*/
CREATE TABLE INMUEBLE(
ID_INMUEBLE BIGINT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
ID_UBICACION BIGINT NOT NULL,
DIRECCION NVARCHAR (60) NOT NULL,
PRECIO DECIMAL(18,1) NOT NULL,
DISPONIBILIDAD BIT DEFAULT 1 NOT NULL,
ID_PROPIETARIO BIGINT NOT NULL,
ID_OFERTA BIGINT NOT NULL,
ID_TIPO_INMUEBLE BIGINT NOT NULL,
ID_INFORMACION BIGINT UNIQUE NOT NULL,
FOREIGN KEY (ID_UBICACION) REFERENCES UBICACION(ID_UBICACION),
FOREIGN KEY (ID_PROPIETARIO) REFERENCES PROPIETARIO(ID_PROPIETARIO),
FOREIGN KEY (ID_OFERTA) REFERENCES OFERTA(ID_OFERTA),
FOREIGN KEY (ID_TIPO_INMUEBLE) REFERENCES TIPO_INMUEBLE(ID_TIPO_INMUEBLE),
FOREIGN KEY (ID_INFORMACION) REFERENCES INFORMACION(ID_INFORMACION)
);

/*
CARACTERISTICAS
id_caracteristicas
numero_habitaciones
numero_baños
area
patio
nivel
gas_natural
zona_ropa
*/

/*
FOREIGN KEY(ID_INMUEBLE) REFERENCES INMUEBLE(ID_INMUEBLE)
ON DELETE CASCADE
ON UPDATE CASCADE
*/


/*
USUARIO
id_usuario
login
contraseña
rol
*/
CREATE TABLE USUARIO(
ID_USUARIO BIGINT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
LOGIN VARCHAR(30) NOT NULL,
CONTRASENHA NVARCHAR(30) NOT NULL,
ID_ROL BIGINT NOT NULL,
FOREIGN KEY (ID_ROL) REFERENCES ROL(ID_ROL)
ON DELETE CASCADE
ON UPDATE CASCADE
);

/*
CONTRATO
id_contrato
id_usuario
id_inmueble

*/
CREATE TABLE CONTRATO(
ID_CONTRATO BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
ID_CLIENTE BIGINT NOT NULL,
ID_INMUEBLE BIGINT NOT NULL,
FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (ID_INMUEBLE) REFERENCES INMUEBLE(ID_INMUEBLE)
ON DELETE CASCADE
ON UPDATE CASCADE
);
/*
CITA
id_cita
hora_inicio
hora_final
fecha_cita
id_inmueble
id_usuario
*/
CREATE TABLE CITA(
ID_CITA BIGINT PRIMARY KEY  AUTO_INCREMENT NOT NULL,
HORA_INICIO TIME NOT NULL,
HORA_FINAL TIME NOT NULL,
FECHA_CITA DATE NOT NULL,

ID_INMUEBLE BIGINT NOT NULL,
ID_CLIENTE BIGINT NOT NULL,
ID_ADMINISTRADOR BIGINT NOT NULL,

FOREIGN KEY (ID_INMUEBLE) REFERENCES INMUEBLE(ID_INMUEBLE)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (ID_ADMINISTRADOR) REFERENCES ADMINISTRADOR(ID_ADMINISTRADOR)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE)
ON DELETE CASCADE
ON UPDATE CASCADE
);