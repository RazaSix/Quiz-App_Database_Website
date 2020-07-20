SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema db_csy4010
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_csy4010` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `db_csy4010` ;

-- -----------------------------------------------------
-- Table `db_csy4010`.`Creators`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_csy4010`.`Creators` ;

CREATE TABLE IF NOT EXISTS `db_csy4010`.`Creators` (
  `Creator_ID` INT(6) NOT NULL AUTO_INCREMENT,
  `First_Name` VARCHAR(45) NULL,
  `Last_Name` VARCHAR(45) NULL,
  `Password` VARCHAR(300) NULL,
  `Email` VARCHAR(45) NULL,
  `Admin` VARCHAR(1) NULL DEFAULT 'N',
  PRIMARY KEY (`Creator_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_csy4010`.`Questionnaires`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_csy4010`.`Questionnaires` ;

CREATE TABLE IF NOT EXISTS `db_csy4010`.`Questionnaires` (
  `Questionnaire_ID` INT(6) NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(45) NULL,
  `Description` VARCHAR(45) NULL,
  `Date_Created` VARCHAR(45) NULL,
  `Creator_ID` INT(6) NOT NULL,
  PRIMARY KEY (`Questionnaire_ID`),
  INDEX `fk_Questionnaires_Creators1_idx` (`Creator_ID` ASC),
  CONSTRAINT `fk_Questionnaires_Creators1`
    FOREIGN KEY (`Creator_ID`)
    REFERENCES `db_csy4010`.`Creators` (`Creator_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_csy4010`.`Questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_csy4010`.`Questions` ;

CREATE TABLE IF NOT EXISTS `db_csy4010`.`Questions` (
  `Question_ID` INT(6) NOT NULL AUTO_INCREMENT,
  `Text` VARCHAR(100) NULL,
  `Questionnaire_ID` INT(6) NOT NULL,
  PRIMARY KEY (`Question_ID`),
  INDEX `fk_Questions_Questionnaires_idx` (`Questionnaire_ID` ASC),
  CONSTRAINT `fk_Questions_Questionnaires`
    FOREIGN KEY (`Questionnaire_ID`)
    REFERENCES `db_csy4010`.`Questionnaires` (`Questionnaire_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_csy4010`.`Question_Options`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_csy4010`.`Question_Options` ;

CREATE TABLE IF NOT EXISTS `db_csy4010`.`Question_Options` (
  `Q_Option_ID` INT(6) NOT NULL AUTO_INCREMENT,
  `Text` VARCHAR(70) NULL,
  `Question_ID` INT(6) NOT NULL,
  `Option` VARCHAR(45) NULL,
  PRIMARY KEY (`Q_Option_ID`),
  INDEX `fk_Question_Options_Questions1_idx` (`Question_ID` ASC),
  CONSTRAINT `fk_Question_Options_Questions1`
    FOREIGN KEY (`Question_ID`)
    REFERENCES `db_csy4010`.`Questions` (`Question_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_csy4010`.`Answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_csy4010`.`Answers` ;

CREATE TABLE IF NOT EXISTS `db_csy4010`.`Answers` (
  `Answer_ID` INT(6) NOT NULL AUTO_INCREMENT,
  `Q_Option_ID` INT(6) NOT NULL,
  `Question_ID` INT(6) NOT NULL,
  `Questionnaire_ID` INT(6) NOT NULL,
  PRIMARY KEY (`Answer_ID`),
  INDEX `fk_Answers_Question_Options1_idx` (`Q_Option_ID` ASC),
  INDEX `fk_Answers_Questions1_idx` (`Question_ID` ASC),
  INDEX `fk_Answers_Questionnaires1_idx` (`Questionnaire_ID` ASC),
  CONSTRAINT `fk_Answers_Question_Options1`
    FOREIGN KEY (`Q_Option_ID`)
    REFERENCES `db_csy4010`.`Question_Options` (`Q_Option_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answers_Questions1`
    FOREIGN KEY (`Question_ID`)
    REFERENCES `db_csy4010`.`Questions` (`Question_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Answers_Questionnaires1`
    FOREIGN KEY (`Questionnaire_ID`)
    REFERENCES `db_csy4010`.`Questionnaires` (`Questionnaire_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_csy4010`.`Admins`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_csy4010`.`Admins` ;

CREATE TABLE IF NOT EXISTS `db_csy4010`.`Admins` (
  `Admin_ID` INT(6) NOT NULL AUTO_INCREMENT,
  `First_Name` VARCHAR(45) NULL,
  `Last_Name` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  PRIMARY KEY (`Admin_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_csy4010`.`Chart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_csy4010`.`Chart` ;

CREATE TABLE IF NOT EXISTS `db_csy4010`.`Chart` (
  `Chart_ID` INT(6) NOT NULL AUTO_INCREMENT,
  `Chart_Name` VARCHAR(45) NULL,
  `Chart_Type` VARCHAR(45) NULL,
  `Chart_Description` VARCHAR(45) NULL,
  PRIMARY KEY (`Chart_ID`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
