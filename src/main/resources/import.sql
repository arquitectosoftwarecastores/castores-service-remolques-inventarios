INSERT INTO ingreso (id, nombre) VALUES (1, 'Nuevo');
INSERT INTO ingreso (id, nombre) VALUES (2, 'Existente');

INSERT INTO estatus (id, nombre) VALUES (1, 'Cargado');
INSERT INTO estatus (id, nombre) VALUES (2, 'Vacio');

INSERT INTO estatus_remolque (id, nombre) VALUES (1, 'Activo');
INSERT INTO estatus_remolque (id, nombre) VALUES (2, 'Inactivo');

INSERT INTO tarjeta (id, nombre) VALUES (1, 'SI');
INSERT INTO tarjeta (id, nombre) VALUES (2, 'NO');

INSERT INTO estatus_inventario (id, nombre) VALUES (1, 'Activo');
INSERT INTO estatus_inventario (id, nombre) VALUES (2, 'Inactivo');

INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1100', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0001', '2020',13069, '0312', '50UM6L', 'corporativo', '1', 'nuevo', 1, 2, 1, 1);
INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1100', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0002', '2023',11015, '0313', '51UM6L', 'corporativo', '2', 'nuevo', 2, 2, 1, 1);
INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1100', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0003', '2022',11016, '0314', '52UM6L', 'corporativo', '1', 'nuevo', 1, 2, 1, 1);
INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1101', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0004', '2010',11017, '0315', '53UM6L', 'leon', '2', 'nuevo', 2, 2, 1, 1);
INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1100', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0005', '2011',11018, '0316', '54UM6L', 'corporativo', '1', 'nuevo', 1, 2, 1, 1);
INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1100', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0006', '2012',11019, '0317', '55UM6L', 'corporativo', '2', 'nuevo', 2, 2, 1, 1);
INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1101', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0007', '2013',11020, '0318', '56UM6L', 'leon', '2', 'nuevo', 2, 2, 1, 1);
INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1100', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0008', '2014',11021, '0319', '57UM6L', 'corporativo', '2', 'nuevo', 2, 2, 1, 1);
INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1100', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0009', '2015',11022, '0320', '58UM6L', 'corporativo', '2', 'nuevo', 2, 2, 1, 1);
INSERT INTO inventarios (clave, estado, fecha_entrada, fecha_salida, inspector, folio, modelo, num_remolque, num_serie, placa, sucursal, id_tarjeta, tipo_inventario, id_estatus, id_ingreso, estatus_id, estatus_inv) VALUES ('1101', 'Por inspeccionar', NOW(), '2023-02-28', 'Zyan Tavera', 'RM0010', '2023',11023, '0322', '59UM6L', 'leon', '2', 'nuevo', 2, 2, 1, 1);