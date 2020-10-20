CREATE TABLE `clientes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `sobrenome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `cpf` VARCHAR(15) NOT NULL,
  `id_endereco` INT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_id_endereco` FOREIGN KEY (`id_endereco`) REFERENCES `enderecos` (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cpf` (`cpf`)
) CHARSET=latin1;
