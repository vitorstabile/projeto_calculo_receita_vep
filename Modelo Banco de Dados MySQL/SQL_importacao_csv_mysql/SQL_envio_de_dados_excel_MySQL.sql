SET GLOBAL local_infile = 1;

LOAD DATA LOCAL INFILE 'C:\\Users\\vep08.FESTPANDOMAIN\\Desktop\\Teste.csv'
INTO TABLE calculo_receita.mp
CHARACTER SET latin1
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
(idMP, codigoMP, descricaoMP, custoMP);

SELECT * FROM calculo_receita.mp;