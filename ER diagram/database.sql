-- MySQL Script generated by MySQL Workbench
-- Wed Apr 17 15:46:51 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(45) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`warehouse` (
  `warehouse_id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `refrigerated_shelf_volume` INT NOT NULL,
  `ordinary_shelf_volume` INT NOT NULL,
  `warehouse_long` DECIMAL(11,8) NOT NULL,
  `warehouse_lati` DECIMAL(10,8) NOT NULL,
  PRIMARY KEY (`warehouse_id`),
  UNIQUE INDEX `warehouse_id_UNIQUE` (`warehouse_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`manager` (
  `user_id` INT NOT NULL,
  `warehouse_warehouse_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_manager_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_manager_warehouse1_idx` (`warehouse_warehouse_id` ASC) VISIBLE,
  CONSTRAINT `fk_manager_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_manager_warehouse1`
    FOREIGN KEY (`warehouse_warehouse_id`)
    REFERENCES `mydb`.`warehouse` (`warehouse_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`customer` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `customer_long` DECIMAL(11,8) NOT NULL,
  `customer_lati` DECIMAL(10,8) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_customer_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`deliverer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`deliverer` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `status_on` CHAR(1) NOT NULL,
  `warehouse_warehouse_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_delivery_warehouse1_idx` (`warehouse_warehouse_id` ASC) VISIBLE,
  CONSTRAINT `fk_delivery_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_delivery_warehouse1`
    FOREIGN KEY (`warehouse_warehouse_id`)
    REFERENCES `mydb`.`warehouse` (`warehouse_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`goods` (
  `goods_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `origin_place` VARCHAR(45) NOT NULL,
  `preserve_time` INT NOT NULL,
  `volume` INT NOT NULL,
  `refrigirated_condition` CHAR(1) NOT NULL,
  `price` DECIMAL(4,1) NOT NULL,
  `discount` DECIMAL(2,1) NOT NULL,
  PRIMARY KEY (`goods_id`),
  UNIQUE INDEX `goods_id_UNIQUE` (`goods_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`store` (
  `store_id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `warehouse_warehouse_id` INT NOT NULL,
  PRIMARY KEY (`store_id`),
  INDEX `fk_store_warehouse1_idx` (`warehouse_warehouse_id` ASC) VISIBLE,
  UNIQUE INDEX `store_id_UNIQUE` (`store_id` ASC) VISIBLE,
  CONSTRAINT `fk_store_warehouse1`
    FOREIGN KEY (`warehouse_warehouse_id`)
    REFERENCES `mydb`.`warehouse` (`warehouse_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`order` (
  `order_id` INT NOT NULL,
  `arrival_time` TIMESTAMP NULL,
  `departure_time` TIMESTAMP NULL,
  `delivery_user_id` INT NOT NULL,
  `customer_user_id` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_order_delivery1_idx` (`delivery_user_id` ASC) VISIBLE,
  INDEX `fk_order_customer1_idx` (`customer_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_delivery1`
    FOREIGN KEY (`delivery_user_id`)
    REFERENCES `mydb`.`deliverer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_customer1`
    FOREIGN KEY (`customer_user_id`)
    REFERENCES `mydb`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`goods_in_warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`goods_in_warehouse` (
  `idgoods_in_warehouse` INT NOT NULL AUTO_INCREMENT,
  `total_volume` INT NOT NULL,
  `expired_day` DATE NOT NULL,
  `warehouse_warehouse_id` INT NOT NULL,
  `goods_goods_id` INT NOT NULL,
  `amount` DECIMAL(5,1) NOT NULL,
  PRIMARY KEY (`idgoods_in_warehouse`),
  INDEX `fk_goods_in_warehouse_warehouse1_idx` (`warehouse_warehouse_id` ASC) VISIBLE,
  UNIQUE INDEX `idgoods_in_warehouse_UNIQUE` (`idgoods_in_warehouse` ASC) VISIBLE,
  INDEX `fk_goods_in_warehouse_goods1_idx` (`goods_goods_id` ASC) VISIBLE,
  CONSTRAINT `fk_goods_in_warehouse_warehouse1`
    FOREIGN KEY (`warehouse_warehouse_id`)
    REFERENCES `mydb`.`warehouse` (`warehouse_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_goods_in_warehouse_goods1`
    FOREIGN KEY (`goods_goods_id`)
    REFERENCES `mydb`.`goods` (`goods_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`purchase` (
  `purchase_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `volume` INT NOT NULL,
  `price` INT NOT NULL,
  `warehouse_warehouse_id` INT NOT NULL,
  `production_date` DATE NOT NULL,
  `purchase_amount` INT NOT NULL,
  PRIMARY KEY (`purchase_id`),
  INDEX `fk_purchase_warehouse1_idx` (`warehouse_warehouse_id` ASC) VISIBLE,
  UNIQUE INDEX `purchase_id_UNIQUE` (`purchase_id` ASC) VISIBLE,
  CONSTRAINT `fk_purchase_warehouse1`
    FOREIGN KEY (`warehouse_warehouse_id`)
    REFERENCES `mydb`.`warehouse` (`warehouse_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`sales` (
  `serial_num` VARCHAR(45) NOT NULL,
  `warehouse_warehouse_id` INT NOT NULL,
  `order_order_id` INT NOT NULL,
  `payment` DECIMAL(7,2) NOT NULL,
  `amount` DECIMAL(4,1) NOT NULL,
  `goods_goods_id` INT NOT NULL,
  `customer_user_id` INT NOT NULL,
  `paid` CHAR(1) NOT NULL,
  PRIMARY KEY (`serial_num`),
  INDEX `fk_sales_warehouse1_idx` (`warehouse_warehouse_id` ASC) VISIBLE,
  INDEX `fk_sales_order1_idx` (`order_order_id` ASC) VISIBLE,
  INDEX `fk_sales_goods1_idx` (`goods_goods_id` ASC) VISIBLE,
  INDEX `fk_sales_customer1_idx` (`customer_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_sales_warehouse1`
    FOREIGN KEY (`warehouse_warehouse_id`)
    REFERENCES `mydb`.`warehouse` (`warehouse_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_order1`
    FOREIGN KEY (`order_order_id`)
    REFERENCES `mydb`.`order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_goods1`
    FOREIGN KEY (`goods_goods_id`)
    REFERENCES `mydb`.`goods` (`goods_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sales_customer1`
    FOREIGN KEY (`customer_user_id`)
    REFERENCES `mydb`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`goods_in_store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`goods_in_store` (
  `idgoods_in_store` INT NOT NULL AUTO_INCREMENT,
  `total_volume` INT NOT NULL,
  `expired_day` DATE NOT NULL,
  `amount` DECIMAL(5,1) NOT NULL,
  `store_store_id` INT NOT NULL,
  `goods_goods_id` INT NOT NULL,
  PRIMARY KEY (`idgoods_in_store`),
  INDEX `fk_goods_in_store_store1_idx` (`store_store_id` ASC) VISIBLE,
  INDEX `fk_goods_in_store_goods1_idx` (`goods_goods_id` ASC) VISIBLE,
  CONSTRAINT `fk_goods_in_store_store1`
    FOREIGN KEY (`store_store_id`)
    REFERENCES `mydb`.`store` (`store_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_goods_in_store_goods1`
    FOREIGN KEY (`goods_goods_id`)
    REFERENCES `mydb`.`goods` (`goods_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`offline_sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`offline_sales` (
  `idoffline_sales` INT NOT NULL AUTO_INCREMENT,
  `amount` DECIMAL(4,1) NOT NULL,
  `payment` DECIMAL(7,2) NOT NULL,
  `store_store_id` INT NOT NULL,
  `customer_user_id` INT NOT NULL,
  `goods_goods_id` INT NOT NULL,
  PRIMARY KEY (`idoffline_sales`),
  INDEX `fk_offline_sales_store1_idx` (`store_store_id` ASC) VISIBLE,
  INDEX `fk_offline_sales_customer1_idx` (`customer_user_id` ASC) VISIBLE,
  INDEX `fk_offline_sales_goods1_idx` (`goods_goods_id` ASC) VISIBLE,
  CONSTRAINT `fk_offline_sales_store1`
    FOREIGN KEY (`store_store_id`)
    REFERENCES `mydb`.`store` (`store_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_offline_sales_customer1`
    FOREIGN KEY (`customer_user_id`)
    REFERENCES `mydb`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_offline_sales_goods1`
    FOREIGN KEY (`goods_goods_id`)
    REFERENCES `mydb`.`goods` (`goods_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
