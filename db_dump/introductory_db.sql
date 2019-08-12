-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema introductory_campaign_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema introductory_campaign_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `introductory_campaign_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `introductory_campaign_db` ;

-- -----------------------------------------------------
-- Table `introductory_campaign_db`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `introductory_campaign_db`.`subject` (
  `subject_id` INT(11) NOT NULL AUTO_INCREMENT,
  `subject_name` VARCHAR(45) NOT NULL,
  `subject_name_ukr` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE INDEX `subject_id_UNIQUE` (`subject_id` ASC) VISIBLE,
  UNIQUE INDEX `subject_name_UNIQUE` (`subject_name` ASC) VISIBLE,
  UNIQUE INDEX `subject_name_ukr_UNIQUE` (`subject_name_ukr` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `introductory_campaign_db`.`specialty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `introductory_campaign_db`.`specialty` (
  `specialty_id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `title_ukr` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`specialty_id`),
  UNIQUE INDEX `specialty_id_UNIQUE` (`specialty_id` ASC) VISIBLE,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  UNIQUE INDEX `title_ukr_UNIQUE` (`title_ukr` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `introductory_campaign_db`.`university`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `introductory_campaign_db`.`university` (
  `university_id` INT(11) NOT NULL AUTO_INCREMENT,
  `university_name` VARCHAR(200) NOT NULL,
  `university_name_ukr` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`university_id`),
  UNIQUE INDEX `university_id_UNIQUE` (`university_id` ASC) VISIBLE,
  UNIQUE INDEX `university_name_UNIQUE` (`university_name` ASC) VISIBLE,
  UNIQUE INDEX `university_name_ukr_UNIQUE` (`university_name_ukr` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `introductory_campaign_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `introductory_campaign_db`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `name_ukr` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `last_name_ukr` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `rating` INT(11) NULL DEFAULT '0',
  `university_id` INT(11) NULL DEFAULT NULL,
  `specialty_id` INT(11) NULL DEFAULT NULL,
  `student` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_university_idx` (`university_id` ASC) VISIBLE,
  INDEX `fk_user_specialty_idx` (`specialty_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_specialty`
    FOREIGN KEY (`specialty_id`)
    REFERENCES `introductory_campaign_db`.`specialty` (`specialty_id`)
    ON DELETE SET NULL,
  CONSTRAINT `fk_user_university`
    FOREIGN KEY (`university_id`)
    REFERENCES `introductory_campaign_db`.`university` (`university_id`)
    ON DELETE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 46
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `introductory_campaign_db`.`marks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `introductory_campaign_db`.`marks` (
  `user_id` INT(11) NOT NULL,
  `subject_id` INT(11) NOT NULL,
  `subject_number` INT(11) NULL DEFAULT NULL,
  `mark` INT(11) NULL DEFAULT '0',
  INDEX `fk_subject_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_subject_has_user_subject1_idx` (`subject_id` ASC) VISIBLE,
  CONSTRAINT `fk_subject_has_user_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `introductory_campaign_db`.`subject` (`subject_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_subject_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `introductory_campaign_db`.`user` (`user_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `introductory_campaign_db`.`specialty_has_subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `introductory_campaign_db`.`specialty_has_subject` (
  `specialty_id` INT(11) NOT NULL,
  `subject_id` INT(11) NOT NULL,
  `subject_number` INT(11) NOT NULL,
  INDEX `fk_specialty_has_subject_subject1_idx` (`subject_id` ASC) VISIBLE,
  INDEX `fk_specialty_has_subject_specialty1_idx` (`specialty_id` ASC) VISIBLE,
  CONSTRAINT `fk_specialty_has_subject_specialty1`
    FOREIGN KEY (`specialty_id`)
    REFERENCES `introductory_campaign_db`.`specialty` (`specialty_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_specialty_has_subject_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `introductory_campaign_db`.`subject` (`subject_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `introductory_campaign_db`.`university_has_specialty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `introductory_campaign_db`.`university_has_specialty` (
  `university_id` INT(11) NOT NULL,
  `specialty_id` INT(11) NOT NULL,
  INDEX `fk_university_has_specialty_specialty1_idx` (`specialty_id` ASC) VISIBLE,
  INDEX `fk_university_has_specialty_university1_idx` (`university_id` ASC) VISIBLE,
  CONSTRAINT `fk_university_has_specialty_specialty1`
    FOREIGN KEY (`specialty_id`)
    REFERENCES `introductory_campaign_db`.`specialty` (`specialty_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_university_has_specialty_university1`
    FOREIGN KEY (`university_id`)
    REFERENCES `introductory_campaign_db`.`university` (`university_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
