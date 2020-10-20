CREATE TABLE `enderecos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cep` VARCHAR(9) NOT NULL,
  `bairro` VARCHAR(100) NOT NULL,
  `complemento` VARCHAR(40) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
 ) CHARSET=latin1;
