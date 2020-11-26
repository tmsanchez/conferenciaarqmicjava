-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema erpmono
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema erpmono
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `erpmono` DEFAULT CHARACTER SET utf8 ;
USE `erpmono` ;

-- -----------------------------------------------------
-- Table `erpmono`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `erpmono`.`cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idcliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erpmono`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `erpmono`.`producto` (
  `idproducto` INT NOT NULL AUTO_INCREMENT,
  `nombre_producto` VARCHAR(45) NOT NULL,
  `precio` DECIMAL(12,3) NOT NULL,
  PRIMARY KEY (`idproducto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erpmono`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `erpmono`.`pedido` (
  `idpedido` INT NOT NULL AUTO_INCREMENT,
  `idcliente` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `comentarios` VARCHAR(255) NULL,
  `total` DECIMAL(12,3) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idpedido`),
  INDEX `fk_pedido_cliente_idx` (`idcliente` ASC),
  CONSTRAINT `fk_pedido_cliente`
    FOREIGN KEY (`idcliente`)
    REFERENCES `erpmono`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erpmono`.`productos_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `erpmono`.`productos_pedido` (
  `idproductos_pedido` INT NOT NULL AUTO_INCREMENT,
  `idpedido` INT NOT NULL,
  `idproducto` INT NOT NULL,
  `cantidad` DECIMAL(12,3) NOT NULL,
  `precio` DECIMAL(12,3) NOT NULL,
  `importe` DECIMAL(12,3) NOT NULL,
  PRIMARY KEY (`idproductos_pedido`),
  INDEX `fk_productos_pedido_producto1_idx` (`idproducto` ASC),
  INDEX `fk_productos_pedido_pedido1_idx` (`idpedido` ASC),
  CONSTRAINT `fk_productos_pedido_producto1`
    FOREIGN KEY (`idproducto`)
    REFERENCES `erpmono`.`producto` (`idproducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_productos_pedido_pedido1`
    FOREIGN KEY (`idpedido`)
    REFERENCES `erpmono`.`pedido` (`idpedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
