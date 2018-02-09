
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema KOPR_evidencia_test
-- -----------------------------------------------------

USE `KOPR_evidencia_test` ;

-- -----------------------------------------------------
-- Table `KOPR_evidencia_test`.`ucastnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KOPR_evidencia_test`.`ucastnik` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `meno` VARCHAR(45) NOT NULL,
  `priezvisko` VARCHAR(45) NOT NULL,  
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `KOPR_evidencia_test`.`predmet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KOPR_evidencia_test`.`predmet` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nazov` VARCHAR(100) NOT NULL,   
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `KOPR_evidencia_test`.`prezencna_listina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KOPR_evidencia_test`.`prezencna_listina` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `predmet_id` INT(11) NOT NULL,
  `datum` DATETIME NOT NULL,  
  PRIMARY KEY (`id`),  
  CONSTRAINT `fk_prezencna_listina_predmet`
    FOREIGN KEY (`predmet_id`)
    REFERENCES `KOPR_evidencia_test`.`predmet` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `KOPR_evidencia_test`.`ucast`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KOPR_evidencia_test`.`ucast` (
  `ucastnik_id` INT(11) NOT NULL,
  `prezencna_listina_id` INT(11) NOT NULL,   
  PRIMARY KEY (`ucastnik_id`, `prezencna_listina_id`),  
  CONSTRAINT `fk_ucast_ucastnik`
    FOREIGN KEY (`ucastnik_id`)
    REFERENCES `KOPR_evidencia_test`.`ucastnik` (`id`),
  CONSTRAINT `fk_ucast_prezencna_listina`
    FOREIGN KEY (`prezencna_listina_id`)
    REFERENCES `KOPR_evidencia_test`.`prezencna_listina` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `KOPR_evidencia_test`.`ucastnik` (meno, priezvisko) VALUES ('Jan','Kapusta'),('Eva','Mudra'),('Igor','Tichi');
INSERT INTO `KOPR_evidencia_test`.`predmet` (nazov) VALUES ('Automaty a formalne jazyky'),('Diskretna matematika'),('Funkcionalne programovanie');
INSERT INTO `KOPR_evidencia_test`.`prezencna_listina` (predmet_id, datum) VALUES (2,'2017-11-05 14:29:36'),(1,'2017-08-12 18:45:28'),(1,'2017-02-22 13:12:38');
INSERT INTO `KOPR_evidencia_test`.`ucast` (ucastnik_id, prezencna_listina_id) VALUES (1,3),(1,2),(2,3),(1,1),(2,1);

