import java.sql.*;
import java.util.*;
public class FornecedoresDAO {
    //objeto Statement permite instruções SQL no programa
    public static Statement stFornecedores;
    //objeto Resultset responsavel por armazenar resultados de uma consulta SELECT no BD
    public static ResultSet rsFornecedores;
    
    public static void cadastraFornecedores(FornecedoresVO tmpFornecedores) throws Exception{
        try{
            //abrindo banco de dados
            BancoDAO.abreConexao();
            //vinculando o Statement ao bdSistema
            //(configurado no conn´- BancoDAO)
            stFornecedores=BancoDAO.conn.createStatement();
            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //INSERT NO BANCO;
            String sqlCadastra="";
            sqlCadastra+= "Insert into Fornecedores(";
            sqlCadastra+="cnpj, nomeF, nomeR, cepF,";
            sqlCadastra+="enderecoF, cidade, municipio, estado,";
            sqlCadastra+="pais, telefone)";
            sqlCadastra+="VALUES(";
            sqlCadastra+="'"+ tmpFornecedores.getCnpj() +"',";
            sqlCadastra+="'"+ tmpFornecedores.getNomeF()+"',";
            sqlCadastra+="'"+ tmpFornecedores.getNomeR()+"',"; 
            sqlCadastra+="'"+ tmpFornecedores.getCepF()+"',"; 
            sqlCadastra+="'"+ tmpFornecedores.getEndereco()+"',";
            sqlCadastra+="'"+ tmpFornecedores.getCidade()+"',";
            sqlCadastra+="'"+ tmpFornecedores.getMunicipio()+"',";
            sqlCadastra+="'"+ tmpFornecedores.getEstado()+"',";
            sqlCadastra+="'"+ tmpFornecedores.getPis()+"',";
            sqlCadastra+="'"+ tmpFornecedores.getTelefoneF()+"')";
            System.out.println(sqlCadastra);
            //executando o Insert
            /*método executeUpdate - exectuta instruções SQL
            que n]ao retornam valores: insert, delete, update*/
            stFornecedores.executeUpdate(sqlCadastra);
            
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
    
    public static void excluiCliente() throws Exception{
        try{
            //abreConexao();            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //DELETE NO BANCO;            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //fechaConexao();            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
    }
    
    public static FornecedoresVO consultaFornecedores(String tmpBusca, int tmpTipo) throws Exception{
        try{
            BancoDAO.abreConexao();
            stFornecedores = BancoDAO.conn.createStatement();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //SELECT NO BANCO;  
            String sqlConsulta="";
            
            if(tmpTipo == 1){
                //Consulta por cpf
                sqlConsulta = "Select * from Fornecedores ";
                sqlConsulta += "where cpf like '" + tmpBusca + "'";
                
            }else if(tmpTipo == 2){
                //Consulta por nome
                sqlConsulta = "Select * from Fornecedores ";
                sqlConsulta += "where nome like '" + tmpBusca + "'";
            }
            
            //Executando select
            //método executeQuery - executa instruções
            //do tipo SELECT no BD, retornando dados
            //que devem ser armazenados no ResultSet(rsFornecedores)
            rsFornecedores = stFornecedores.executeQuery(sqlConsulta);
            
            //se houver dados no rsFornecedores
            if(rsFornecedores.next()){
                //preenchendo objVo com os dados do Resultset
                FornecedoresVO tmpFornecedores = new FornecedoresVO();
                
                //método getString pega valor texto do ResultSet
                //dentro dos parenteses deve ir o nome do respectivo
                //campo do BD
                tmpFornecedores.setCnpj(rsFornecedores.getString("Cnpj"));
                tmpFornecedores.setNomeF(rsFornecedores.getString("nomeF"));
                tmpFornecedores.setNomeR(rsFornecedores.getString("nomeR"));
                tmpFornecedores.setCepF(rsFornecedores.getString("CepF"));
                tmpFornecedores.setEnderecoF(rsFornecedores.getString("enderecoF"));
                tmpFornecedores.setCidade(rsFornecedores.getString("Cidade"));
                tmpFornecedores.setMunicipio(rsFornecedores.getString("Municipio"));
                tmpFornecedores.setEstado(rsFornecedores.getString("Estado"));
                tmpFornecedores.setPais(rsFornecedores.getString("Pais"));
                tmpFornecedores.setTelefoneF(rsFornecedores.getString("telefone"));
                
                //retornando objVO para classe View
                return tmpFornecedores;
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
    
    public static List<FornecedoresVO> listaFornecedores() throws Exception{
        try{
            //abreConexao();            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //SELECT NO BANCO;            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //fechaConexao();            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        return null;   
    }
    
    public static void alteraCliente() throws Exception{
        try{
            //abreConexao();            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //UPDATE NO BANCO;            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //fechaConexao();            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
    }
    
}
