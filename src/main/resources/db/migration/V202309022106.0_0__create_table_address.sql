CREATE TABLE `bookstore`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `zip_code` VARCHAR(10) NULL,
  `public_place` VARCHAR(255) NULL,
  `complement` VARCHAR(255) NULL,
  `neighborhood` VARCHAR(255) NULL,
  `locality` VARCHAR(200) NULL,
  `uf` VARCHAR(3) NULL,
  `gia` VARCHAR(10) NULL,
  `ddd` VARCHAR(15) NULL,
  `siafi` VARCHAR(10) NULL,
  PRIMARY KEY (`id`)
  );