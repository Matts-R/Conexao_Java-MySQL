
package DataBase;

public class DbException extends RuntimeException {

    public DbException(String message) {
        super(message); 
        /*Criando uma exceção personalizada em caso de algum envolvendo o banco de dados
        */
    }   
}
