//version 1.1.2

public class SistemaControl {
    
    public static void main(String[] args) {
     /*   ClientesVO iniciar = new ClientesVO();
     exemplo, pq o programa deveria começar por uma classe View */
     SistemaView telaPrincipal = new SistemaView();
     //testaConexao();
        //FuncionariosVO telaVO = new FuncionariosVO();
       // System.out.println("inicio "+telaVO.getCargo());
    }//fechando void main
    
    public static void testaConexao(){
        try{
            BancoDAO.abreConexao();
            System.out.println("Conexão realizada com sucesso.");
        }catch(Exception erro){
            System.out.println(erro.getMessage());
        }
        
        try{
            BancoDAO.fechaConexao();
            System.out.println("Conexão encerrada.");
        }catch(Exception erro){
            System.out.println(erro.getMessage());
        }
    }
    
}
