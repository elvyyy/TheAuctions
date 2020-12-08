# DataBase script and Diagram

# Diagram

![Diagram](https://i.ibb.co/WvV89p1/dbimage.pngg)

# Script

```mysql
-- MySQL Script generated by MySQL Workbench
-- Mon 07 Dec 2020 06:08:57 PM MSK
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema jwd_auctions
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jwd_auctions
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jwd_auctions` DEFAULT CHARACTER SET utf8 ;
USE `jwd_auctions` ;

-- -----------------------------------------------------
-- Table `jwd_auctions`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jwd_auctions`.`user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jwd_auctions`.`user_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jwd_auctions`.`user_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jwd_auctions`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jwd_auctions`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` CHAR(60) BINARY NOT NULL,
  `first_name` VARCHAR(150) NOT NULL,
  `middle_name` VARCHAR(150) NULL,
  `last_name` VARCHAR(150) NOT NULL,
  `user_role_id` INT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  `updated_at` DATETIME NOT NULL DEFAULT NOW(),
  `user_status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_users_user_role_idx` (`user_role_id` ASC),
  INDEX `fk_user_status_table11_idx` (`user_status_id` ASC),
  CONSTRAINT `fk_users_user_role`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `jwd_auctions`.`user_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_status_table11`
    FOREIGN KEY (`user_status_id`)
    REFERENCES `jwd_auctions`.`user_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jwd_auctions`.`lot_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jwd_auctions`.`lot_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `status_UNIQUE` (`status` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jwd_auctions`.`lots`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jwd_auctions`.`lots` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL DEFAULT NOW(),
  `created_by` INT NOT NULL,
  `verified_by` INT NULL,
  `minimal_bid` DECIMAL(7,2) NOT NULL,
  `lot_status_id` INT NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lots_lot_status1_idx` (`lot_status_id` ASC),
  INDEX `fk_lots_verified_by_idx` (`verified_by` ASC),
  INDEX `fk_lots_created_by_idx` (`created_by` ASC),
  CONSTRAINT `fk_lots_lot_status`
    FOREIGN KEY (`lot_status_id`)
    REFERENCES `jwd_auctions`.`lot_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lots_verified_by`
    FOREIGN KEY (`verified_by`)
    REFERENCES `jwd_auctions`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_lots_created_by`
    FOREIGN KEY (`created_by`)
    REFERENCES `jwd_auctions`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jwd_auctions`.`lot_content_text`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jwd_auctions`.`lot_content_text` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_url` VARCHAR(255) NOT NULL,
  `lots_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lot_content_image_lots1_idx` (`lots_id` ASC),
  CONSTRAINT `fk_lot_content_image_lots1`
    FOREIGN KEY (`lots_id`)
    REFERENCES `jwd_auctions`.`lots` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
```

