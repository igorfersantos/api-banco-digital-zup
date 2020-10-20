CREATE TABLE `cpf_foto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `foto` TEXT NOT NULL,
  `id_cliente` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) CHARSET=latin1;
