SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Alumnos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Alumnos` (
  `secAlumno` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `telefono` VARCHAR(9) NULL,
  `email` VARCHAR(45) NULL,
  `direccion` VARCHAR(100) NULL,
  `username` VARCHAR(30) NULL,
  `password` VARCHAR(20) NULL,
  PRIMARY KEY (`secAlumno`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cursos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cursos` (
  `secCurso` INT NOT NULL AUTO_INCREMENT,
  `nombrecurso` VARCHAR(50) NULL,
  `fechaInicio` DATE NULL,
  `fechaFinal` DATE NULL,
  `horario` VARCHAR(50) NULL,
  `duracion` FLOAT NULL,
  `precio` FLOAT NULL,
  `plazas` INT NULL,
  `inscritos` INT NULL,
  `imagen` VARCHAR(10) NULL,
  PRIMARY KEY (`secCurso`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Productos` (
  `secProducto` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `nombre` VARCHAR(20) NULL,
  `numUnidades` INT NULL,
  `precio` FLOAT NULL,
  `descripcion` VARCHAR(140) NULL,
  `imagen` VARCHAR(10) NULL,
  PRIMARY KEY (`secProducto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ProductosAlumnos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ProductosAlumnos` (
  `secProducto` INT NOT NULL,
  `secAlumno` INT NOT NULL,
  `fecha` DATE NULL,
  `importe` FLOAT NULL,
  PRIMARY KEY (`secProducto`, `secAlumno`),
  INDEX `fk_Productos_has_Alumnos_Alumnos1_idx` (`secAlumno` ASC),
  INDEX `fk_Productos_has_Alumnos_Productos_idx` (`secProducto` ASC),
  CONSTRAINT `fk_Productos_has_Alumnos_Productos`
    FOREIGN KEY (`secProducto`)
    REFERENCES `mydb`.`Productos` (`secProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Productos_has_Alumnos_Alumnos1`
    FOREIGN KEY (`secAlumno`)
    REFERENCES `mydb`.`Alumnos` (`secAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CursosAlumnos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CursosAlumnos` (
  `secCurso` INT NOT NULL AUTO_INCREMENT,
  `secAlumno` INT NOT NULL,
  `fechaInscripcion` DATE NULL,
  `importe` FLOAT NULL,
  PRIMARY KEY (`secCurso`, `secAlumno`),
  INDEX `fk_Cursos_has_Alumnos_Alumnos1_idx` (`secAlumno` ASC),
  INDEX `fk_Cursos_has_Alumnos_Cursos1_idx` (`secCurso` ASC),
  CONSTRAINT `fk_Cursos_has_Alumnos_Cursos1`
    FOREIGN KEY (`secCurso`)
    REFERENCES `mydb`.`Cursos` (`secCurso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cursos_has_Alumnos_Alumnos1`
    FOREIGN KEY (`secAlumno`)
    REFERENCES `mydb`.`Alumnos` (`secAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`roles` (
  `rolename` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`rolename`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users_roles` (
  `secAlumno` INT NOT NULL AUTO_INCREMENT,
  `rolename` VARCHAR(45) NOT NULL,
  `username` VARCHAR(30) NULL,
  PRIMARY KEY (`secAlumno`, `rolename`),
  INDEX `fk_Alumnos_has_roles_roles1_idx` (`rolename` ASC),
  INDEX `fk_Alumnos_has_roles_Alumnos1_idx` (`secAlumno` ASC),
  CONSTRAINT `fk_Alumnos_has_roles_Alumnos1`
    FOREIGN KEY (`secAlumno`)
    REFERENCES `mydb`.`Alumnos` (`secAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumnos_has_roles_roles1`
    FOREIGN KEY (`rolename`)
    REFERENCES `mydb`.`roles` (`rolename`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `mydb`.`alumnos` (`secAlumno`, `nombre`, `apellidos`, `telefono`, `email`, `direccion`, `username`, `password`) VALUES ('1', 'gabriel', 'garcia', '654654654', 'ggarciaseco@gmail.com', 'leopoldo alas clarin,10,2,33040,el entrego,Asturias', 'gabriel', 'temporal');
INSERT INTO `mydb`.`roles` (`rolename`) VALUES ('admin');
INSERT INTO `mydb`.`roles` (`rolename`) VALUES ('user');
INSERT INTO `mydb`.`users_roles` (`secAlumno`, `rolename`, `username`) VALUES ('1', 'admin', 'gabriel');


