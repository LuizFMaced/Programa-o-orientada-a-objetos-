import java.sql.*;
public class BancoDAO {
    
    public static Connection conn;
    
    public static void abreConexao() throws Exception{
        
        try{
          //comandos de abertura da conexão  
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection("jdbc:mysql://localhost/bdsistema","root","");
          
        }catch(Exception erro){
            throw new Exception (erro.getMessage());
        }           
    }//fechando abreConexao
    
    public static void fechaConexao() throws Exception{
        try{
            conn.close();
        }catch(Exception erro){
            throw new Exception (erro.getMessage());
        }
        
    }

}
