Notas: 

Para funcionar na Faculdade, trocar as String's do DAOImpl. 
Atualizar sem funcionar. 
Medicamento não consegue trazer para a tela os dados quando seleciona na tableview. 
Configurar o Mariadb. 
Criar a base de dados antes de executar. 

CREATE DATABASE farmaciadb; 
USE farmaciadb;
CREATE TABLE farmacia (    
        id BIGINT PRIMARY KEY,    
        nome VARCHAR(255),    
        cidade VARCHAR(255),    
        bairro VARCHAR(255),    
        telefone VARCHAR(20),    
        proprietario VARCHAR(255));
    
CREATE TABLE medicamento (
         id BIGINT PRIMARY KEY AUTO_INCREMENT,
         nome VARCHAR(255) NOT NULL,
         categoria VARCHAR(255),
         fabricante VARCHAR(255),
         preco DECIMAL(10, 2) NOT NULL
     ); 

Para compilar e executar no CMD: 
javac --module-path C:\Users\kayla\javafx-sdk-21.0.5\lib --add-modules javafx.controls,javafx.fxml -cp ".;C:\Users\kayla\poo\trabalho\ProjetoFarmacia\lib\mariadb-java-client-3.5.1.jar" -d ../bin *.java

java --module-path C:\Users\kayla\javafx-sdk-21.0.5\lib --add-modules javafx.controls,javafx.fxml -cp ".;C:\Users\kayla\poo\trabalho\ProjetoFarmacia\lib\mariadb-java-client-3.5.1.jar;../bin" App

C:\Users\kayla\javafx-sdk-21.0.5\lib = caminho do javafx; 
C:\Users\kayla\poo\trabalho\ProjetoFarmacia\lib\mariadb-java-client-3.5.1.jar = caminho do mariadb; 