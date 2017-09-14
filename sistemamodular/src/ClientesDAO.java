import java.sql.*;
import java.util.*;

public class ClientesDAO {
    //objeto Statement permite instruções SQL no programa
    public static Statement stClientes;
    //objeto Resultset responsavel por armazenar resultados de uma consulta SELECT no BD
    public static ResultSet rsClientes;
    
    public static void cadastraCliente(ClientesVO tmpCliente) throws Exception{
        try{
            //abrindo banco de dados
            BancoDAO.abreConexao();
            //vinculando o Statement ao bdSistema
            //(configurado no conn´- BancoDAO)
            stClientes=BancoDAO.conn.createStatement();
            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }      
        try{
            //INSERT NO BANCO;
            String sqlCadastra="";
            sqlCadastra+= "Insert into clientes(";
            sqlCadastra+="cpf, rg, nome, datanascimento,";
            sqlCadastra+="endereco, bairro, cidade, estado,";
            sqlCadastra+="cep, telefone, email, foto)";
            sqlCadastra+="VALUES(";
            sqlCadastra+="'"+ tmpCliente.getCpf() +"',";
            sqlCadastra+="'"+ tmpCliente.getRg()+"',";
            sqlCadastra+="'"+ tmpCliente.getNome()+"',"; 
            sqlCadastra+="'"+ tmpCliente.getNascimento()+"',"; 
            sqlCadastra+="'"+ tmpCliente.getEndereco()+"',";
            sqlCadastra+="'"+ tmpCliente.getBairro()+"',";
            sqlCadastra+="'"+ tmpCliente.getCidade()+"',";
            sqlCadastra+="'"+ tmpCliente.getEstado()+"',";
            sqlCadastra+="'"+ tmpCliente.getCep()+"',";
            sqlCadastra+="'"+ tmpCliente.getTel()+"',";
            sqlCadastra+="'"+ tmpCliente.getEmail()+"',";
            sqlCadastra+="'"+ tmpCliente.getFoto()+"')";
            
            System.out.println(sqlCadastra);
            //executando o Insert
            /*método executeUpdate - exectuta instruções SQL
            que nao retornam valores: insert, delete, update*/
            stClientes.executeUpdate(sqlCadastra);
            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //fechando BD
            BancoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
    }
    
    public static void excluiCliente(String tmpCpf) throws Exception{
        try{
            //abreConexao();
            BancoDAO.abreConexao();
            stClientes=BancoDAO.conn.createStatement();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //DELETE NO BANCO;
            String sqlExclui="Delete from clientes where cpf like '"+tmpCpf+"'";
            stClientes.executeQuery(sqlExclui);
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //fechaConexao();
            BancoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
    }
    
    public static ClientesVO consultaCliente(String tmpBusca, int tmpTipo) throws Exception{
        try{
            BancoDAO.abreConexao();
            stClientes = BancoDAO.conn.createStatement();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //SELECT NO BANCO;  
            String sqlConsulta="";
            
            if(tmpTipo == 1){
                //Consulta por cpf
                sqlConsulta = "Select * from clientes ";
                sqlConsulta += "where cpf like '" + tmpBusca + "'";
                
            }else if(tmpTipo == 2){
                //Consulta por nome
                sqlConsulta = "Select * from CLIENTES ";
                sqlConsulta += "where nome like '" + tmpBusca + "'";
            }
            
            //Executando select
            //método executeQuery - executa instruções
            //do tipo SELECT no BD, retornando dados
            //que devem ser armazenados no ResultSet(rsCLientes)
            rsClientes = stClientes.executeQuery(sqlConsulta);
            
            //se houver dados no rsClientes
            if(rsClientes.next()){
                //preenchendo objVo com os dados do Resultset
                ClientesVO tmpCliente = new ClientesVO();
                
                //método getString pega valor texto do ResultSet
                //dentro dos parenteses deve ir o nome do respectivo
                //campo do BD
                tmpCliente.setCpf(rsClientes.getString("cpf"));
                tmpCliente.setRg(rsClientes.getString("rg"));
                tmpCliente.setNome(rsClientes.getString("nome"));
                tmpCliente.setNascimento(rsClientes.getString("datanascimento"));
                tmpCliente.setEndereco(rsClientes.getString("endereco"));
                tmpCliente.setBairro(rsClientes.getString("bairro"));
                tmpCliente.setCidade(rsClientes.getString("cidade"));
                tmpCliente.setEstado(rsClientes.getString("estado"));
                tmpCliente.setCep(rsClientes.getString("cep"));
                tmpCliente.setTel(rsClientes.getString("telefone"));
                tmpCliente.setEmail(rsClientes.getString("email"));
                tmpCliente.setFoto(rsClientes.getString("foto"));
                
                //retornando objVO para classe View
                return tmpCliente;
            }
            
        }catch(Exception erro){
            throw new Exception("Verifique os campos e sintaxe da instrução SELECT.\n" + erro.getMessage());
        }
        
        try{
            BancoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        return null;
    }
    
    public static List<ClientesVO> listaClientes() throws Exception{
        List<ClientesVO> vetorClientes = new ArrayList<ClientesVO>();
        try{
            //abreConexao(); 
            BancoDAO.abreConexao();
            stClientes=BancoDAO.conn.createStatement();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //SELECT NO BANCO;
            String sqlClientes="select * from clientes";
            rsClientes=stClientes.executeQuery(sqlClientes);
            while(rsClientes.next()){
                ClientesVO tmpCliente = new ClientesVO();
                tmpCliente.setCpf(rsClientes.getString("cpf"));
                tmpCliente.setNome(rsClientes.getString("nome"));
                tmpCliente.setCidade(rsClientes.getString("cidade"));
                tmpCliente.setTel(rsClientes.getString("telefone"));
            vetorClientes.add(tmpCliente);//add clientes ao vetor
            }
            
        }catch(Exception erro){
            throw new Exception("Falha ao carregar lista!"+erro.getMessage());
        }
        
        try{
            BancoDAO.fechaConexao();            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        return vetorClientes;   
    }
    
    public static void alteraCliente(String tmpCpf, ClientesVO tmpCliente) throws Exception{
        try{
            BancoDAO.abreConexao();
            stClientes = BancoDAO.conn.createStatement();           
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //UPDATE NO BANCO;
            String sqlAlteraCliente;
            sqlAlteraCliente="Update CLIENTES set ";
            sqlAlteraCliente+="cpf = '"+tmpCliente.getCpf()+"',";
            sqlAlteraCliente+="rg = '"+tmpCliente.getRg()+"',";
            sqlAlteraCliente+="nome = '"+tmpCliente.getNome()+"',";
            sqlAlteraCliente+="datanascimento = '"+tmpCliente.getNascimento()+"',";
            sqlAlteraCliente+="endereco = '"+tmpCliente.getEndereco()+"',";
            sqlAlteraCliente+="bairro = '"+tmpCliente.getBairro()+"',";
            sqlAlteraCliente+="cidade = '"+tmpCliente.getCidade()+"',";
            sqlAlteraCliente+="estado = '"+tmpCliente.getEstado()+"',";
            sqlAlteraCliente+="cep = '"+tmpCliente.getCep()+"',";
            sqlAlteraCliente+="telefone = '"+tmpCliente.getTel()+"',";
            sqlAlteraCliente+="email = '"+tmpCliente.getEmail()+"' ";
            sqlAlteraCliente+="where cpf like'"+tmpCpf+"'";
            
            stClientes.executeUpdate(sqlAlteraCliente);
            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            BancoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
    }   
}
