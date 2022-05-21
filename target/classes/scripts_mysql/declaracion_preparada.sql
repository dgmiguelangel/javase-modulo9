USE curso_javase;

PREPARE INSERTAR_COMPACTO FROM "INSERT INTO compacto 
(numero, activa, color, marca, encendido, puestos)
VALUES  (?, ?, ?, ?, ?, ?);";

SET @numero  = 'ABC-001';
SET @activa = 1;
SET @Color = 'ROJO';
SET @marca = 'Audi';
SET @encendido =  1;
SET @puestos = 2;

EXECUTE INSERTAR_COMPACTO USING @numero, @activa, @Color, @marca, @encendido, @puestos;

SET @numero  = 'ZXM-901';
SET @activa = 1;
SET @Color = 'NEGRO';
SET @marca = 'Ford';
SET @encendido =  1;
SET @puestos = 2;

EXECUTE INSERTAR_COMPACTO USING @numero, @activa, @Color, @marca, @encendido, @puestos;