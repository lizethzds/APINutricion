DROP DATABASE IF EXISTS nutricionBD;
CREATE DATABASE nutricionBD CHARACTER SET utf8mb4;
use nutricionBD;

CREATE USER 'adminGral'@'localhost' IDENTIFIED BY 'nutricionbd10';
GRANT ALL PRIVILEGES ON *.* TO 'adminGral'@'localhost';

CREATE TABLE catalogoPorciones(
idPorcion INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nombre VARCHAR(30) NOT NULL
);

CREATE TABLE catalogoRoles(
idRol INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nombre VARCHAR(30)  NOT NULL
);


CREATE TABLE estado(
idEstado INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
nombre VARCHAR(30)  NOT NULL
);


CREATE TABLE municipio(
idMunicipio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
idEstado INT ,
nombre VARCHAR(30)  NOT NULL,
FOREIGN KEY (idEstado) REFERENCES estado(idEstado)
);


CREATE TABLE medicos(
idMedico INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
idRol INT,
nombre VARCHAR(30) NOT NULL,
apellidoPaterno VARCHAR(30) NOT NULL,
apellidoMaterno VARCHAR(30) NOT NULL,
fechaNacimiento DATE NOT NULL,
sexo VARCHAR(1) NOT NULL,
numeroDePersonal VARCHAR(5) NOT NULL,
cedulaProfesional VARCHAR(10) NOT NULL,
password VARCHAR(16) NOT NULL,
fotografia LONGBLOB,
 FOREIGN KEY (idRol) REFERENCES catalogoRoles(idRol)
);

CREATE TABLE pacientes(
idPaciente INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
idDomicilio INT,
idMedico INT,
nombre VARCHAR(30) NOT NULL,
apellidoPat VARCHAR(20) NOT NULL,
apellidoMat VARCHAR(20),
fechaNacimiento VARCHAR(10) NOT NULL,
sexo VARCHAR(1) NOT NULL,
peso FLOAT NOT NULL,
estatura INT NOT NULL,
tallaInicial INT NOT NULL,
email VARCHAR(30) NOT NULL,
telefono VARCHAR(15) NOT NULL,
password VARCHAR(16) NOT NULL,
fotografia LONGBLOB,
FOREIGN KEY (idDomicilio) REFERENCES domicilio(idDomicilio),
FOREIGN KEY (idMedico) REFERENCES medicos(idMedico)
);

CREATE TABLE alimentos(
idAlimento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
idPorcion INT ,
nombreDelAlimento VARCHAR(30) NOT NULL,
caloriasPorcion INT NOT NULL,
cantidad INT NOT NULL,
FOREIGN KEY (idPorcion) REFERENCES catalogoPorciones(idPorcion)
);

CREATE TABLE pacienteAlimento(
idPaciente INT NOT NULL,
idAlimento INT NOT NULL,
PRIMARY KEY(idPaciente, idAlimento),
FOREIGN KEY (idPaciente) REFERENCES pacientes(idPaciente),
FOREIGN KEY (idAlimento) REFERENCES alimentos(idAlimento)
);


CREATE TABLE domicilio(
idDomicilio INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
idMunicipio INT,
calle VARCHAR(30) NOT NULL,
numero VARCHAR(4) NOT NULL,
colonia VARCHAR(30) NOT NULL,
codigoPostal VARCHAR(8) NOT NULL,
FOREIGN KEY (idMunicipio) REFERENCES municipio(idMunicipio)

);



insert into pacientes(idMedico,nombre,apellidoPat,apellidoMat,
fechaNacimiento,sexo, peso,estatura,tallaInicial,email,telefono,password) values (1,"Martha","Rosas", "West","2003-11-23","M",66.5,170, 40, "martha@gmail.com", "12837463492","martha111");

select * from pacientes where idMedico=1;

drop table pacientes;

drop table medicos;

drop table domicilio;



insert into catalogoRoles(nombre) values ("Administrador");

insert into medicos(idRol,nombre,apellidoPaterno,apellidoMaterno,
fechaNacimiento,sexo,
numeroDePersonal,cedulaProfesional, password) values (1,"Carmen","Guzman", "Enriquez","1977-01-13","F","20192","182739","monitos11");

insert into medicos(idRol,nombre,apellidoPaterno,apellidoMaterno,
fechaNacimiento,sexo,
numeroDePersonal,cedulaProfesional, password) values (2,"Karim","Abdul", "Martinez","1988-10-09","M","99283","1394783","112437823");





select * from medicos;
select * from catalogoRoles;
select * from pacientes;



FLUSH PRIVILEGES;