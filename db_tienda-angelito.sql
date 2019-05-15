-- SCRIPT BASE DE DATOS TIENDA ANGELITO--;
CREATE DATABASE tiendaAngelito;
USE tiendaAngelito;

-- TABLAS--;
CREATE TABLE persona(
    per_idPersona INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    per_dui VARCHAR(10) UNIQUE,
    per_nit VARCHAR(17) UNIQUE,
    per_nup VARCHAR(12) UNIQUE,
    per_isss VARCHAR(9) UNIQUE,
    per_nombre VARCHAR(255) NOT NULL,
    per_apellido VARCHAR(255),
    per_direccion VARCHAR(255) NOT NULL UNIQUE,
    per_telefono VARCHAR(9) UNIQUE,
    per_email VARCHAR(100) UNIQUE,
    per_natural BOOLEAN NULL DEFAULT TRUE
);

CREATE TABLE cliente(
    cli_idPersona INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (cli_idPersona) REFERENCES persona (per_idPersona)
);

CREATE TABLE empleado(
    emp_idPersona INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (emp_idPersona) REFERENCES persona (per_idPersona)
);

CREATE TABLE proveedor(
    prov_idPersona INT NOT NULL PRIMARY KEY,
    FOREIGN KEY (prov_idPersona) REFERENCES persona (per_idPersona)
);

CREATE TABLE tipoUsuario(
    tus_idTipoUsuario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tus_tipoUsuario VARCHAR(100) NOT NULL
);

CREATE TABLE usuario(
    usu_idUsuario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    usu_idPersona INT NOT NULL,
    usu_idTipoUsuario INT NOT NULL,
    usu_alias VARCHAR(100) NOT NULL UNIQUE,
    usu_contrasenia VARCHAR(100) NOT NULL,
    FOREIGN KEY (usu_idPersona) REFERENCES empleado (emp_idPersona),
    FOREIGN KEY (usu_idTipoUsuario) REFERENCES tipoUsuario (tus_idTipoUsuario)
);

CREATE TABLE factura(
    fac_idFactura INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    fac_idPersona INT NULL DEFAULT '0',
    fac_idUsuario INT NOT NULL,
    fac_fecha DATE NOT NULL,
    FOREIGN KEY (fac_idPersona) REFERENCES cliente (cli_idPersona),
    FOREIGN KEY (fac_idUsuario) REFERENCES usuario (usu_idUsuario)
);

CREATE TABLE compra(
    com_idCompra INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    com_idUsuario INT NOT NULL,
    com_idPersona INT NULL DEFAULT '0',
    com_fecha DATE NOT NULL,
    FOREIGN KEY (com_idUsuario) REFERENCES usuario (usu_idUsuario),
    FOREIGN KEY (com_idPersona) REFERENCES proveedor (prov_idPersona)
);

CREATE TABLE categoria(
    cat_idCategoria INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cat_categoria VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE producto(
    prod_idProducto VARCHAR(100) NOT NULL PRIMARY KEY,
    prod_nombre VARCHAR(100) NOT NULL,
    prod_descripcion VARCHAR(255),
    prod_precio DECIMAL(5,2) NOT NULL,
    prod_precioMayoreo DECIMAL(5,2),
    prod_iva DECIMAL(7,4) NULL DEFAULT '13',
    prod_cantidadMayoreo INT NULL DEFAULT '0',
    prod_cantidad INT NULL DEFAULT '0',
    prod_cantidadMinAlerta INT NULL DEFAULT '0',
    prod_idCategoria INT NULL DEFAULT '0',
    FOREIGN KEY (prod_idCategoria) REFERENCES categoria (cat_idCategoria)
);

CREATE TABLE detalleFactura(
    dfa_idDetalleFactura INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    dfa_idFactura INT NOT NULL,
    dfa_idProducto VARCHAR(100) NULL DEFAULT '0',
    dfa_cantidad INT NULL DEFAULT '0',
    FOREIGN KEY (dfa_idFactura) REFERENCES factura (fac_idFactura),
    FOREIGN KEY (dfa_idProducto) REFERENCES producto (prod_idProducto)
);

CREATE TABLE lote(
    lot_idLote INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    lot_codLote VARCHAR(100) NOT NULL,
    lot_idProducto VARCHAR(100) NULL DEFAULT '0',
    lot_fechaFabricacion DATE,
    lot_fechaVencimiento DATE NOT NULL,
    lot_cantidadContenido INT NOT NULL,
    lot_precio DECIMAL(8,2) NOT NULL,
    FOREIGN KEY (lot_idProducto) REFERENCES producto(prod_idProducto)
);

CREATE TABLE detalleCompra(
    dco_idLote INT NOT NULL PRIMARY KEY,
    dco_idCompra INT NULL DEFAULT '0',
    FOREIGN KEY (dco_idLote) REFERENCES lote (lot_idLote),
    FOREIGN KEY (dco_idCompra) REFERENCES compra (com_idCompra)
);

-- Valores iniciales--;

INSERT INTO tipoUsuario(tus_tipoUsuario) VALUES('Administrador');
INSERT INTO tipoUsuario(tus_tipoUsuario) VALUES('Vendedor');

-- Valores 0 por defecto--;

INSERT INTO persona VALUES (NULL, '00000000-0', '0000-000000-000-0', '000000000000', '000000000', 'Sin registrar', 'null', 'n/a 2', '0000-0000', 'n/a', true);
UPDATE persona SET per_idPersona=0;
INSERT INTO persona(per_idPersona, per_nombre, per_direccion) VALUES (0, 'Sin registrar', 'n/a');
INSERT INTO cliente(cli_idPersona) VALUES(0);
INSERT INTO cliente(cli_idPersona) VALUES(2);
INSERT INTO empleado(emp_idPersona) VALUES(0);
INSERT INTO proveedor(prov_idPersona) VALUES(0);
INSERT INTO usuario(usu_idUsuario,usu_idPersona,usu_idTipoUsuario,usu_alias,usu_contrasenia) VALUES (-1,0,1,'admin',MD5('1234'));
INSERT INTO usuario(usu_idUsuario,usu_idPersona,usu_idTipoUsuario,usu_alias,usu_contrasenia) VALUES (0,0,2,'usu',MD5('1234'));
UPDATE usuario SET usu_idUsuario = 0 WHERE usu_idUsuario = 1;
INSERT INTO factura VALUES(0,0,0,'1900-1-1');
UPDATE factura SET fac_idFactura = 0;
INSERT INTO compra VALUES(0,0,0,'1900-1-1');
UPDATE compra SET com_idCompra = 0;
INSERT INTO categoria VALUES(0,'Sin categoría');
UPDATE categoria SET cat_idCategoria = 0;
INSERT INTO producto VALUES(0,'Sin producto','n/a',0,0,0,0,0,0,0);
INSERT INTO detalleFactura(dfa_idDetalleFactura,dfa_idFactura) VALUES(0,0);
INSERT INTO lote VALUES(0,0,0,'1900-1-1','2999-12-31',0,0);
UPDATE lote SET lot_idLote = 0;
INSERT INTO detalleCompra VALUES(0,0);

-- Procedimientos almacenados--;
DELIMITER //
CREATE PROCEDURE proc_facturar(IN in_idFactura INT, IN in_idPersona INT, IN in_idUsuario INT, IN in_idProducto VARCHAR(100), IN in_cantidad INT)
BEGIN
    DECLARE cantidad INT;
    DECLARE idFactura INT;
    SET cantidad = (SELECT prod_cantidad FROM producto WHERE prod_idProducto = in_idProducto);
    SET idFactura = (SELECT fac_idFactura FROM factura WHERE fac_idFactura = in_idFactura);

    IF in_cantidad>@cantidad
        THEN
            SELECT "No hay producto suficiente";
        ELSE
            IF idFactura IS NULL
                THEN
                    INSERT INTO factura VALUES(in_idFactura, in_idPersona, in_idUsuario, NOW());
            END IF;            
            INSERT INTO detalleFactura VALUES (NULL, in_idFactura, in_idProducto, in_cantidad);
            UPDATE producto SET prod_cantidad = (cantidad - in_cantidad) WHERE prod_idProducto = in_idProducto;
            SELECT "Venta exitosa";
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE proc_comprar(
    IN in_codLote VARCHAR(100), IN in_idProducto VARCHAR(100),
    IN in_fecFab DATE, IN in_fecVen DATE, IN in_contenido INT,
    IN in_precio DECIMAL(8,2), IN in_idCompra INT, IN in_idUsuario INT, IN in_idProv INT)
BEGIN
    DECLARE idProducto VARCHAR(100);
    DECLARE cantidad INT;
    DECLARE idCompra INT;
    SET idProducto = (SELECT prod_idProducto FROM producto WHERE prod_idProducto = in_idProducto);
    SET cantidad = (SELECT prod_cantidad FROM producto WHERE prod_idProducto = in_idProducto);
    SET idCompra = (SELECT com_idCompra FROM compra WHERE com_idCompra=in_idCompra);

    IF idProducto IS NULL
        THEN
            SELECT "El producto no existe, debe registrarlo";
        ELSE
            INSERT INTO lote VALUES (NULL, in_codLote, in_idProducto, in_fecFab, in_fecVen, in_contenido, in_precio);
            IF idCompra IS NULL
                THEN
                    INSERT INTO compra VALUES (NULL, in_idUsuario, in_idProv, NOW());
            END IF;
            INSERT INTO detalleCompra VALUES ((SELECT lot_idLote FROM lote order by lot_idLote DESC LIMIT 1), in_idCompra);
            UPDATE producto SET prod_cantidad = (cantidad + in_contenido) WHERE prod_idProducto = in_idProducto;
            SELECT CONCAT('Compra exitosa ', in_contenido, ' unidades agregadas al producto ', in_idProducto);
    END IF;
END//
DELIMITER ;

-- Consultas--;
SELECT cli.per_nombre, cli.per_apellido, prod_idProducto, prod_nombre, prod_descripcion, dfa_cantidad,
    IF (dfa_cantidad >= prod_cantidadMayoreo,prod_precioMayoreo,prod_precio) AS 'precio_unitario',
    IF (dfa_cantidad >= prod_cantidadMayoreo,prod_precioMayoreo * dfa_cantidad,prod_precio * dfa_cantidad) AS 'subtotal',
    b.total, emp.per_nombre, emp.per_apellido
FROM detallefactura
INNER JOIN producto ON producto.prod_idProducto=dfa_idProducto
INNER JOIN factura ON detallefactura.dfa_idFactura=factura.fac_idFactura
INNER JOIN persona cli ON factura.fac_idPersona = cli.per_idPersona
INNER JOIN usuario ON factura.fac_idUsuario = usuario.usu_idUsuario
INNER JOIN empleado ON usuario.usu_idPersona = empleado.emp_idPersona
INNER JOIN persona emp ON empleado.emp_idPersona= emp.per_idPersona
CROSS JOIN (SELECT SUM(IF (dfa_cantidad >= prod_cantidadMayoreo,prod_precioMayoreo * dfa_cantidad,prod_precio * dfa_cantidad)) AS 'total' FROM detallefactura
INNER JOIN producto ON producto.prod_idProducto=dfa_idProducto
INNER JOIN factura ON detallefactura.dfa_idFactura=factura.fac_idFactura WHERE dfa_idFactura=0) b WHERE dfa_idFactura=0;

SELECT per_idPersona, per_nombre, per_apellido, per_direccion, per_telefono,
    IF(per_natural,'Natural','Jurídica') AS 'Tipo',
    IF(per_idPersona = cli_idPersona, 'SI', 'NO') AS 'cliente',
    IF(per_idPersona = prov_idPersona, 'SI', 'NO') AS 'proveedor',
    IF(per_idPersona = usu_idPersona, 'SI', 'NO') AS 'usuario',
    IF(per_idPersona = usu_idPersona, tus_tipoUsuario, NULL) AS 'Rol'
FROM persona
LEFT JOIN usuario ON usuario.usu_idPersona=persona.per_idPersona
LEFT JOIN cliente ON cliente.cli_idPersona=persona.per_idPersona
LEFT JOIN proveedor ON proveedor.prov_idPersona=persona.per_idPersona
LEFT JOIN tipoUsuario ON usuario.usu_idTipoUsuario=tipoUsuario.tus_idTipoUsuario;
