SELECT * FROM calculo_receita.mp 
INTO OUTFILE 'C:\\Temp\\teste.csv'
FIELDS ENCLOSED BY '"' 
TERMINATED BY ';' 
ESCAPED BY '"' 
LINES TERMINATED BY '\r\n';