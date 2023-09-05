ALTER TABLE `bookstore`.`address`
ADD COLUMN `client_fk` INT NULL AFTER `siafi`,
ADD INDEX `fk_client_idx` (`client_fk` ASC) VISIBLE;
;
ALTER TABLE `bookstore`.`address`
ADD CONSTRAINT `fk_client`
  FOREIGN KEY (`client_fk`)
  REFERENCES `bookstore`.`client` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;