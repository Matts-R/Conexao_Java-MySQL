
package Main;

import DataBase.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) throws SQLException {
        
        Connection conexao = ConnectionFactory.getConnection(); //ESTABELECENDO A CONEXÃO COM O BANCO DE DADOS 
       
        ConnectionFactory.closeConnection();// FECHANDO A CONEXÃO COM O BANCO DE DADOS
        
        if (conexao.isClosed()){
            System.out.println("A conexão com o banco de Dados foi encerrada com sucesso. ");
        }
    }
}
