package DataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection conexao = null;

    public static Connection getConnection() {
        /*MÉTODO CRIADO PARA PODER REALIZAR A CONEXÃO COM O  BANCO DE DADOS DE MANEIRA SEGURA
         */
        if (conexao == null) {
            try {
                Properties propriedades = carregaPropriedades(); // PEGANDO AS PROPRIEDADES DO BANCO DE DADOS
                String url = propriedades.getProperty("dburl"); // PEGANDO O ENDEREÇO DO BANCO

                conexao = DriverManager.getConnection(url, propriedades); //REALIZANDO A CONEXÃO COM O BANCO DE DADOS

            } catch (SQLException sqle) { // CAPTURANDO ALGUMA POSSÍVEL EXCEÇÃO
                throw new DbException("Erro na conexão com o banco de dados!! \n" + sqle.getMessage());
                /*EXIBINDO A EXCEÇÃO PERSONALIZADA EM CASO DE ERRO NA CONEXÃO
                 */
            }
        }
        return conexao;
    }

    public static void closeConnection() {
        /*MÉTODO CRIADO PARA PODER O FECHAMENTO DA CONEXÃO COM O  BANCO DE DADOS DE MANEIRA SEGURA
         */
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException sqle) {
                throw new DbException("Erro ao fechar a conexão com o banco de Dados!!\n" + sqle.getMessage());
                /*EXIBINDO A EXCEÇÃO PERSONALIZADA EM CASO DE ERRO NO FECHAMENTO DA CONEXÃO COM 
                O BANCO DE DADOS
                 */
            }
        }
    }

    private static Properties carregaPropriedades() {
        /*MÉTODO CRIADO PARA PODER EXTRAIR AS PROPRIEDADES 
        DO BANCO DE DADOS DE UM ARQUIVO PRESENTE NO PROJETO
         */

        try (FileInputStream fs = new FileInputStream("db.properties")) {
            // COMO O ARQUIVO ESTÁ NA FONTE DO PROJETO ELA VAI ECONTRA-LO SEM A NECESSIDADE DO PATH
            Properties propriedades = new Properties();

            propriedades.load(fs);
            /*CARREGANDO OS VALORES PRESENTES NO ARQUIVO PARA O OBJETO propriedades DO TIPO PROPERTIES
             */

            return propriedades;
        } catch (IOException ioe) {
            throw new DbException("Problema com as propriedades!!");
        }
    }
}
