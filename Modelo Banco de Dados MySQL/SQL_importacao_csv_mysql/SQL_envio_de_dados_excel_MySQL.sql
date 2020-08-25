SET GLOBAL local_infile = 1;

LOAD DATA LOCAL INFILE 'C:\\Users\\vep08.FESTPANDOMAIN\\Desktop\\Teste.csv'
INTO TABLE calculo_receita.ingrediente
CHARACTER SET latin1
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
(id, codigo, descricao, custo);

SELECT * FROM calculo_receita.ingrediente;