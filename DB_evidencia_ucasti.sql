
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema KOPR_evidencia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `KOPR_evidencia` DEFAULT CHARACTER SET utf8 ;
USE `KOPR_evidencia` ;

CREATE USER 'administrator'@'localhost' IDENTIFIED BY 'admin1';
GRANT ALL ON KOPR_evidencia.* TO 'administrator'@'localhost';

-- -----------------------------------------------------
-- Table `KOPR_evidencia`.`ucastnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KOPR_evidencia`.`ucastnik` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `meno` VARCHAR(45) NOT NULL,
  `priezvisko` VARCHAR(45) NOT NULL,  
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `KOPR_evidencia`.`predmet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KOPR_evidencia`.`predmet` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nazov` VARCHAR(100) NOT NULL,   
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `KOPR_evidencia`.`prezencna_listina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KOPR_evidencia`.`prezencna_listina` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `predmet_id` INT(11) NOT NULL,
  `datum` DATETIME NOT NULL,  
  PRIMARY KEY (`id`),  
  CONSTRAINT `fk_prezencna_listina_predmet`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `KOPR_evidencia`.`predmet` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `KOPR_evidencia`.`ucast`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KOPR_evidencia`.`ucast` (
  `ucastnik_id` INT(11) NOT NULL,
  `prezencna_listina_id` INT(11) NOT NULL,   
  PRIMARY KEY (`ucastnik_id`, `prezencna_listina_id`),  
  CONSTRAINT `fk_ucast_ucastnik`
    FOREIGN KEY (`ucastnik_id`)
    REFERENCES `KOPR_evidencia`.`ucastnik` (`id`),
  CONSTRAINT `fk_ucast_prezencna_listina`
    FOREIGN KEY (`prezencna_listina_id`)
    REFERENCES `KOPR_evidencia`.`prezencna_listina` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
