import java.sql.*;
import java.util.*;

public class FuncionariosDAO {
    //objeto Statement permite instruções SQL no programa
    public static Statement stFuncionarios;
    //objeto Resultset responsavel por armazenar resultados de uma consulta SELECT no BD
    public static ResultSet rsFuncionarios;
    
    public static void cadastraFuncionarios(FuncionariosVO tmpFunc) throws Exception{
        try{
            //abrindo banco de dados
            BancoDAO.abreConexao();
            //vinculando o Statement ao bdSistema
            //(configurado no conn´- BancoDAO)
            stFuncionarios=BancoDAO.conn.createStatement();
            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //INSERT NO BANCO;
            String sqlCadastra="";
            sqlCadastra+="Insert into funcionarios(";
            sqlCadastra+="codigo, nome, endereco, telefone,";
            sqlCadastra+="cpf, celular, rg, sexo,";
            sqlCadastra+="cargo, setor, estadoCivil,";
            sqlCadastra+="graduacao, cargaHoraria, dataNascimento,";
            sqlCadastra+="dataAdmissao, carteiraTrabalho,";
            sqlCadastra+="salario, modoPagamento, tipoConta, banco,";
            sqlCadastra+="contaBancaria, observacoes)";
            sqlCadastra+="VALUES(";
            sqlCadastra+="'"+ tmpFunc.getCodigo()+"',";
            sqlCadastra+="'"+ tmpFunc.getNome()+"',";
            sqlCadastra+="'"+ tmpFunc.getEndereco()+"',"; 
            sqlCadastra+="'"+ tmpFunc.getTelefone()+"',"; 
            sqlCadastra+="'"+ tmpFunc.getCpf()+"',";
            sqlCadastra+="'"+ tmpFunc.getCelular()+"',";
            sqlCadastra+="'"+ tmpFunc.getRg()+"',";
            sqlCadastra+="'"+ tmpFunc.getSexo()+"',";
            sqlCadastra+="'"+ tmpFunc.getCargo()+"',";
            sqlCadastra+="'"+ tmpFunc.getSetor()+"',";
            sqlCadastra+="'"+ tmpFunc.getEstadoCivil()+"',";
            sqlCadastra+="'"+ tmpFunc.getGraduacao()+"',";
            sqlCadastra+="'"+ tmpFunc.getCargaHoraria()+"',";
            sqlCadastra+="'"+ tmpFunc.getDataNascimento()+"',";
            sqlCadastra+="'"+ tmpFunc.getDataAdmissao()+"',";
            sqlCadastra+="'"+ tmpFunc.getCarteiraTrabalho()+"',";
            sqlCadastra+="'"+ tmpFunc.getSalario()+"',";
            sqlCadastra+="'"+ tmpFunc.getModoPagamento()+"',";
            sqlCadastra+="'"+ tmpFunc.getTipoConta()+"',";
            sqlCadastra+="'"+ tmpFunc.getBanco()+"',";
            sqlCadastra+="'"+ tmpFunc.getContaBancaria()+"',";
            sqlCadastra+="'"+ tmpFunc.getObservacoes()+"')";
            
            
            System.out.println(sqlCadastra);
            //executando o Insert
            /*método executeUpdate - exectuta instruções SQL
            que nao retornam valores: insert, delete, update*/
            stFuncionarios.executeUpdate(sqlCadastra);
            
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
    
    public static void excluiFuncionarios(String tmpCod) throws Exception{
        try{
            //abreConexao();    
            BancoDAO.abreConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //DELETE NO BANCO;      
            String sqlExclui="Delete from funcionarios where cpf like '"+tmpCod+"'";
            stFuncionarios.executeUpdate(sqlExclui);
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
    
    public static FuncionariosVO consultaFuncionarios(String tmpBusca, int tmpTipo) throws Exception{
        try{
            BancoDAO.abreConexao();
            stFuncionarios = BancoDAO.conn.createStatement();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //SELECT NO BANCO;  
            String sqlConsulta="";
            
            if(tmpTipo == 1){
                //Consulta por cpf
                sqlConsulta = "Select * from funcionarios ";
                sqlConsulta += "where cpf like '" + tmpBusca + "'";
                
            }else if(tmpTipo == 2){
                //Consulta por nome
                sqlConsulta = "Select * from funcionarios ";
                sqlConsulta += "where nome like '" + tmpBusca + "'";
            }
            
            //Executando select
            //método executeQuery - executa instruções
            //do tipo SELECT no BD, retornando dados
            //que devem ser armazenados no ResultSet(rsCLientes)
            rsFuncionarios = stFuncionarios.executeQuery(sqlConsulta);
            
            //se houver dados no rsClientes
            if(rsFuncionarios.next()){
                //preenchendo objVo com os dados do Resultset
                FuncionariosVO tmpFunc = new FuncionariosVO();
                
                //método getString pega valor texto do ResultSet
                //dentro dos parenteses deve ir o nome do respectivo
                //campo do BD
                tmpFunc.setCodigo(rsFuncionarios.getString("codigo"));
                tmpFunc.setNome(rsFuncionarios.getString("nome"));
                tmpFunc.setEndereco(rsFuncionarios.getString("endereco"));
                tmpFunc.setTelefone(rsFuncionarios.getString("telefone"));
                tmpFunc.setCpf(rsFuncionarios.getString("cpf"));
                tmpFunc.setCelular(rsFuncionarios.getString("celular"));
                tmpFunc.setRg(rsFuncionarios.getString("rg"));
                tmpFunc.setSexo(rsFuncionarios.getString("sexo"));
                System.out.println("camada DAO consultaFunc "+tmpFunc.getSexo());
                tmpFunc.setCargo(rsFuncionarios.getString("cargo"));
                tmpFunc.setSetor(rsFuncionarios.getString("setor")); 
                tmpFunc.setEstadoCivil(rsFuncionarios.getString("estadoCivil")); 
                tmpFunc.setGraduacao(rsFuncionarios.getString("graduacao"));
                tmpFunc.setCargaHoraria(rsFuncionarios.getString("cargaHoraria"));
                tmpFunc.setDataNascimento(rsFuncionarios.getString("dataNascimento"));
                tmpFunc.setDataAdmissao(rsFuncionarios.getString("dataAdmissao"));
                tmpFunc.setCarteiraTrabalho(rsFuncionarios.getString("carteiraTrabalho"));
                tmpFunc.setSalario(rsFuncionarios.getString("salario"));
                tmpFunc.setModoPagamento(rsFuncionarios.getString("modoPagamento"));
                tmpFunc.setTipoConta(rsFuncionarios.getString("tipoConta"));
                tmpFunc.setBanco(rsFuncionarios.getString("banco"));
                tmpFunc.setContaBancaria(rsFuncionarios.getString("contaBancaria"));
                tmpFunc.setObservacoes(rsFuncionarios.getString("observacoes"));
                
                //retornando objVO para classe View
                return tmpFunc;
            }
            
        }catch(Exception erro){
            throw new Exception("Verifique os campos e a sintaxe da instrução SELECT.\n" + erro.getMessage());
        }
        
        try{
            BancoDAO.fechaConexao();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        return null;
    }
    
    public static List<FuncionariosVO> listaFuncionarios() throws Exception{
        List<FuncionariosVO> vetorFunc = new ArrayList<FuncionariosVO>();
        try{
            //abreConexao();
            BancoDAO.abreConexao();
            stFuncionarios=BancoDAO.conn.createStatement();            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //SELECT NO BANCO;
             String sqlFunc="select * from funcionarios order by nome";
            rsFuncionarios=stFuncionarios.executeQuery(sqlFunc);
            while(rsFuncionarios.next()){
                FuncionariosVO tmpFunc = new FuncionariosVO();
                tmpFunc.setCpf(rsFuncionarios.getString("cpf"));
                tmpFunc.setNome(rsFuncionarios.getString("nome"));
                tmpFunc.setRg(rsFuncionarios.getString("rg"));
                tmpFunc.setTelefone(rsFuncionarios.getString("telefone"));
            vetorFunc.add(tmpFunc);//add clientes ao vetor
            }
            
        }catch(Exception erro){
            throw new Exception("Falha ao carregar lista!"+erro.getMessage());
        }
        
        try{
            BancoDAO.fechaConexao();            
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        return vetorFunc;    
    }
    
    public static void alteraFuncionarios(String tmpCpf, FuncionariosVO tmpFunc) throws Exception{
        try{
            //abreConexao();
            BancoDAO.abreConexao();
            stFuncionarios = BancoDAO.conn.createStatement();
        }catch(Exception erro){
            throw new Exception(erro.getMessage());
        }
        
        try{
            //UPDATE NO BANCO;
            String sqlAlteraFunc;
            sqlAlteraFunc="Update funcionarios set ";
            sqlAlteraFunc+="codigo = '"+ tmpFunc.getCodigo()+"',";
            sqlAlteraFunc+="nome = '"+ tmpFunc.getNome()+"',";
            sqlAlteraFunc+="endereco = '"+ tmpFunc.getEndereco()+"',"; 
            sqlAlteraFunc+="telefone = '"+ tmpFunc.getTelefone()+"',"; 
            sqlAlteraFunc+="cpf = '"+ tmpFunc.getCpf()+"',";
            sqlAlteraFunc+="celular = '"+ tmpFunc.getCelular()+"',";
            sqlAlteraFunc+="rg = '"+ tmpFunc.getRg()+"',";
            sqlAlteraFunc+="sexo = '"+ tmpFunc.getSexo()+"',";
            sqlAlteraFunc+="cargo = '"+ tmpFunc.getCargo()+"',";
            sqlAlteraFunc+="setor = '"+ tmpFunc.getSetor()+"',";
            sqlAlteraFunc+="estadoCivil = '"+ tmpFunc.getEstadoCivil()+"',";
            sqlAlteraFunc+="graduacao = '"+ tmpFunc.getGraduacao()+"',";
            sqlAlteraFunc+="cargaHoraria = '"+ tmpFunc.getCargaHoraria()+"',";
            sqlAlteraFunc+="dataNascimento = '"+ tmpFunc.getDataNascimento()+"',";
            sqlAlteraFunc+="dataAdmissao = '"+ tmpFunc.getDataAdmissao()+"',";
            sqlAlteraFunc+="carteiraTrabalho = '"+ tmpFunc.getCarteiraTrabalho()+"',";
            sqlAlteraFunc+="salario = '"+ tmpFunc.getSalario()+"',";
            sqlAlteraFunc+="modoPagamento = '"+ tmpFunc.getModoPagamento()+"',";
            sqlAlteraFunc+="tipoConta = '"+ tmpFunc.getTipoConta()+"',";
            sqlAlteraFunc+="banco = '"+ tmpFunc.getBanco()+"',";
            sqlAlteraFunc+="contaBancaria = '"+ tmpFunc.getContaBancaria()+"',";
            sqlAlteraFunc+="observacoes = '"+ tmpFunc.getObservacoes()+"' ";
            sqlAlteraFunc+="where cpf like '"+tmpCpf+"'";
            
            stFuncionarios.executeUpdate(sqlAlteraFunc);
            System.out.println(sqlAlteraFunc);
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
    
}
