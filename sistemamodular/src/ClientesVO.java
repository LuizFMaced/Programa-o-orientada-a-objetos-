
public class ClientesVO {
    //declaração dos atributos
    public String cpf, rg, nome,dataNascimento, endereco, bairro, cidade, 
                  estado, cep, telefone, email, foto; 
    
    //abrindo construtor com estado incial dos objetos 
    public ClientesVO(){
       this.setCpf(null);
       
    }//fechando construtor

    /*métodos SET dito modificadores, requer passagem de parametro 
    não retornam valores, portanto são declarados como void.*/
    public void setCpf (String tmpCpf){
        cpf = tmpCpf;
    }
    
    /*métodos GET dito acessadores, não requer passagem de parametro
    e retorna valores, portanto são declaros como o tipo de dado que retornam./
    */
    public String getCpf (){
        return cpf;
    }
    
    /***esse padrão se repete com os demais atributos***/
    
//RG
    public void setRg (String tmpRg){
        rg = tmpRg; 
    }
    public String getRg (){
        return rg;
    }  
//Nome    
    public void setNome (String tmpNome){
        nome = tmpNome; 
    }
    public String getNome (){
        return nome;
    }  
//Data de Nascimento    
    public void setNascimento (String tmpNasc){
        dataNascimento = tmpNasc; 
    }
    public String getNascimento (){
        return dataNascimento;
    } 
//Endereço    
     public void setEndereco (String tmpEndereco){
        endereco = tmpEndereco; 
    }
    public String getEndereco(){
        return endereco;
    } 
    
//Email    
    public void setEmail (String tmpEmail){
        email = tmpEmail; 
    }
    public String getEmail (){
        return email;
    }
//Telefone    
     public void setTel (String tmpTel){
        telefone = tmpTel; 
    }     
    public String getTel (){
        return telefone;
    }
//Cep    
     public void setCep (String tmpCep){
        cep = tmpCep; 
    }
    public String getCep (){
        return cep;
    }
//Cidade    
     public void setCidade(String tmpCidade){
        cidade = tmpCidade; 
    }
    public String getCidade(){
        return cidade;
    }
//Estado
     public void setEstado (String tmpEstado){
        estado = tmpEstado; 
    }
    public String getEstado (){
        return estado;
    }  
//Bairro    
     public void setBairro (String tmpBair){
        bairro = tmpBair; 
    }
    public String getBairro (){
        return bairro;
    }  
//Foto
     public void setFoto (String tmpFoto){
        foto = tmpFoto; 
    }
    public String getFoto (){
        return foto;
    }     
}//fechando classe
