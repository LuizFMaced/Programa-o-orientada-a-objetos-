public class FuncionariosVO {
    public String codigo, nome, endereco,telefone,
            cpf, celular, rg, sexo, cargo, setor, estadoCivil, graduacao,
            cargaHoraria,dataNascimento,dataAdmissao,carteiraTrabalho,
            salario,modoPagamento,tipoConta,Banco,contaBancaria,observacoes;
                    
public FuncionariosVO(){
      //todas variaveis String se iniciam como nulas ""
   
    }//fechando construtor

    /*métodos SET dito modificadores, requer passagem de parametro 
    não retornam valores, portanto são declarados como void.*/
    /*métodos GET dito acessadores, não requer passagem de parametro
    e retorna valores, portanto são declaros como o tipo de dado que retornam./
    */

  //código 
    public void setCodigo (String tmpCod){
        codigo = tmpCod;
    }
    
    public String getCodigo (){
        return codigo;
    }
//nome
    public void setNome (String tmpnome){
        nome = tmpnome;
    }
    
    public String getNome (){
        return nome;
    }
//endereço  
    public void setEndereco (String tmpEnd){
        endereco = tmpEnd; 
    }
    public String getEndereco (){
        return endereco;
    }  
//telefone
    public void setTelefone (String tmptel){
        telefone = tmptel; 
    }
    public String getTelefone (){
        return telefone;
    } 
//cpf  
    public void setCpf (String tmpCpf){
        cpf = tmpCpf; 
    }
    public String getCpf(){
        return cpf;
    }
//celular
    public void setCelular (String tmpCelular){
        celular = tmpCelular; 
    }
    public String getCelular (){
        return celular;
    }  
//rg    
    public void setRg (String tmpRg){
        rg = tmpRg; 
    }
    public String getRg (){
        return rg;
    }  
//sexo      
    public void setSexo (String tmpSexo){
        sexo = tmpSexo; 
    }
    public String getSexo (){
        return sexo;
    }
//cargo   
    public void setCargo (String tmpCargo){
        cargo = tmpCargo; 
    }     
    public String getCargo (){
        return cargo;
    }
//setor   
    public void setSetor (String tmpSetor){
        setor = tmpSetor; 
    }
    public String getSetor (){
        return setor;
    }
//estado civil    
    public void setEstadoCivil(String tmpEstCivil){
        estadoCivil = tmpEstCivil; 
    }
    public String getEstadoCivil(){
        return estadoCivil;
    }
//graduação
    public void setGraduacao (String tmpGraduacao){
        graduacao = tmpGraduacao;
    }
    public String getGraduacao(){
        return graduacao;
    }
//carga horária
    public void setCargaHoraria (String tmpCargaHoraria){
        cargaHoraria=tmpCargaHoraria;
    }
    public String getCargaHoraria(){
        return cargaHoraria;
    }
//data de nascimento    
    public void setDataNascimento (String tmpdtNascimento){
        dataNascimento = tmpdtNascimento; 
    }
    public String getDataNascimento (){
        return dataNascimento;
    }  
//data de admissão
    public void setDataAdmissao (String tmpDataAdmissao){
        dataAdmissao=tmpDataAdmissao;
    }
    public String getDataAdmissao(){
        return dataAdmissao; 
    }
//carteira de trabalho
    public void setCarteiraTrabalho (String tmpctTrabalho){
        carteiraTrabalho = tmpctTrabalho; 
    }
    public String getCarteiraTrabalho (){
        return carteiraTrabalho;
    }
//salário    
    public void setSalario (String tmpSalario){
        salario = tmpSalario; 
    }
    public String getSalario (){
        return salario;
    }  
//modo de pagamento    
    public void setModoPagamento (String tmpmdPagamento){
        modoPagamento = tmpmdPagamento; 
    }
    public String getModoPagamento (){
        return modoPagamento;
    } 
//tipo de conta
    public void setTipoConta (String tmpTipoConta){
        tipoConta = tmpTipoConta; 
    }
    public String getTipoConta (){
        return tipoConta;
    }     
//banco    
    public void setBanco (String tmpBanco){
        Banco = tmpBanco; 
    }
    public String getBanco (){
        return Banco;
    }     
//conta bancária
    public void setContaBancaria (String tmpContaBancaria){
        contaBancaria = tmpContaBancaria; 
    }
    public String getContaBancaria (){
        return contaBancaria;
    }
//observações
    public void setObservacoes (String tmpObs){
        observacoes = tmpObs; 
    }
    public String getObservacoes (){
        return observacoes;
    }     
          
}//fechando classe