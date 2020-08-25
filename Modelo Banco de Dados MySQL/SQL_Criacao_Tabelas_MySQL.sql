CREATE DATABASE `calculo_receita`;

CREATE TABLE IF NOT EXISTS `calculo_receita`.`mp` (
  `idMP` INT NOT NULL AUTO_INCREMENT,
  `codigoMP` VARCHAR(45) NULL DEFAULT NULL,
  `descricaoMP` VARCHAR(45) NULL DEFAULT NULL,
  `custoMP` FLOAT(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`idMP`))
ENGINE = InnoDB
AUTO_INCREMENT = 158
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

CREATE TABLE IF NOT EXISTS `calculo_receita`.`recurso` (
  `idRecurso` INT NOT NULL AUTO_INCREMENT,
  `descricaoRecurso` VARCHAR(45) NULL DEFAULT NULL,
  `custoRecurso` FLOAT(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`idRecurso`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `calculo_receita`.`teste` (
  `idTeste` INT NOT NULL AUTO_INCREMENT,
  `descricaoTeste` VARCHAR(45) NULL DEFAULT NULL,
  `baseCalcTeste` FLOAT(10,2) NULL DEFAULT NULL,
  `gramaturaTeste` FLOAT(10,2) NULL DEFAULT NULL,
  `rendBrutoTeste` FLOAT(10,2) NULL DEFAULT NULL,
  `custoTeste` FLOAT(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`idTeste`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `calculo_receita`.`receita` (
  `idReceita` INT NOT NULL AUTO_INCREMENT,
  `descricaoReceita` VARCHAR(45) NULL DEFAULT NULL,
  `rendLiqReceita` FLOAT(10,2) NULL DEFAULT NULL,
  `gramaturaReceita` FLOAT(10,2) NULL DEFAULT NULL,
  `rendBrutoReceita` FLOAT(10,2) NULL DEFAULT NULL,
  `perdaReceita` FLOAT(10,2) NULL DEFAULT NULL,
  `custoReceita` FLOAT(10,2) NULL DEFAULT NULL,
  `idTeste` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idReceita`),
  INDEX `PK_TESTE_RECEITA_idx` (`idTeste` ASC) VISIBLE,
  CONSTRAINT `PK_TESTE_RECEITA`
    FOREIGN KEY (`idTeste`)
    REFERENCES `calculo_receita`.`teste` (`idTeste`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `calculo_receita`.`ingrediente` (
  `id_MP_Receita_PK` INT NOT NULL AUTO_INCREMENT,
  `idMP` INT NULL DEFAULT NULL,
  `idReceita` INT NULL DEFAULT NULL,
  `custoMP` FLOAT(10,2) NULL DEFAULT NULL,
  `qtIngrediente` FLOAT(10,2) NULL DEFAULT NULL,
  `custoIngrediente` FLOAT(10,2) NULL DEFAULT NULL,
  `porcenIngrediente` FLOAT(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_MP_Receita_PK`),
  INDEX `FK_ID_MP_idx` (`idMP` ASC) VISIBLE,
  INDEX `FK_ID_RECEITA_idx` (`idReceita` ASC) VISIBLE,
  CONSTRAINT `FK_INGREDIENTE_MP`
    FOREIGN KEY (`idMP`)
    REFERENCES `calculo_receita`.`mp` (`idMP`),
  CONSTRAINT `FK_INGREDIENTE_RECEITA`
    FOREIGN KEY (`idReceita`)
    REFERENCES `calculo_receita`.`receita` (`idReceita`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `calculo_receita`.`embalagem` (
  `id_Teste_Embalagem_PK` INT NOT NULL AUTO_INCREMENT,
  `idMP` INT NULL DEFAULT NULL,
  `idTeste` INT NULL DEFAULT NULL,
  `custoMP` FLOAT(10,2) NULL DEFAULT NULL,
  `qtEmbalagem` FLOAT(10,2) NULL DEFAULT NULL,
  `custoEmbalagem` FLOAT(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_Teste_Embalagem_PK`),
  INDEX `idMP_idx` (`idMP` ASC) VISIBLE,
  INDEX `idTeste_idx` (`idTeste` ASC) VISIBLE,
  CONSTRAINT `FK_EMBALAGEM_MP`
    FOREIGN KEY (`idMP`)
    REFERENCES `calculo_receita`.`mp` (`idMP`),
  CONSTRAINT `FK_EMBALAGEM_TESTE`
    FOREIGN KEY (`idTeste`)
    REFERENCES `calculo_receita`.`teste` (`idTeste`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `calculo_receita`.`utilidade` (
  `id_Teste_Recurso_PK` INT NOT NULL AUTO_INCREMENT,
  `idRecurso` INT NULL DEFAULT NULL,
  `idTeste` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_Teste_Recurso_PK`),
  INDEX `FK_UTILIDADE_TESTE` (`idTeste` ASC) VISIBLE,
  INDEX `idRecurso_idx` (`idRecurso` ASC) VISIBLE,
  CONSTRAINT `FK_UTILIDADE_RECURSO`
    FOREIGN KEY (`idRecurso`)
    REFERENCES `calculo_receita`.`recurso` (`idRecurso`),
  CONSTRAINT `FK_UTILIDADE_TESTE`
    FOREIGN KEY (`idTeste`)
    REFERENCES `calculo_receita`.`teste` (`idTeste`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;