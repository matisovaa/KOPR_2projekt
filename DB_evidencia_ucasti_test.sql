
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema KOPR_evidencia_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `KOPR_evidencia_test` DEFAULT CHARACTER SET utf8 ;
USE `KOPR_evidencia_test` ;

CREATE USER 'administratorTest'@'localhost' IDENTIFIED BY 'admintest';
GRANT ALL ON KOPR_evidencia_test.* TO 'administratorTest'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
