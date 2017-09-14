/* importando pacotes graficos e eventos*/
import javax.swing.*;
import java.awt.event.*;


/*Abstract Action é uma classe de botões que serão utilizados na barra de ferramentas*/
public class BotoesAcao extends AbstractAction{

/*Esta classe, recebe 3 parametros: indice, icone e descrição(TOOLTIPTEXT)*/
    public BotoesAcao(int indiceBotao, ImageIcon imgIcone, String strDescricao) {

		/*Definindo descrição e icone do botão*/
		super(strDescricao, imgIcone);

		/*Atribuindo id do botão e implementando a descrição no TOOLTIPTEXT
		 ****obs: ToolTipText é a rapida descrição do nome do botão ao manter o mouse
		 *sobre ele*/
		this.putValue("id", indiceBotao);
		this.putValue(SHORT_DESCRIPTION, strDescricao);

    }

    public BotoesAcao(int indiceBotao, String strDescricao) {

		/*Definindo descrição e icone do botão*/
		super(strDescricao);

		/*Atribuindo id do botão e implementando a descrição no TOOLTIPTEXT
		 ****obs: ToolTipText é a rapida descrição do nome do botão ao manter o mouse
		 *sobre ele*/
		this.putValue("id", indiceBotao);
		this.putValue(SHORT_DESCRIPTION, strDescricao);

    }

/*Definindo ações para cada botão*/
    public void actionPerformed(ActionEvent evt){
            if((int)(getValue("id"))==0){//botão novo de clientes
              ClientesView.desbloquearCampos(true);
              ClientesView.limparCampos();
              ClientesView.acao = 1;
            }
            
            if((int)(getValue("id"))==1){//botão buscar de clientes
                ClientesView.executaConsulta(1);
            }
            
            if((int)(getValue("id"))==2){//botão editar de clientes
                 if(ClientesView.selecao == false)
                    JOptionPane.showMessageDialog(null,"Não há nenhum cliente selecionado");
                 else{
                    ClientesView.desbloquearCampos(true);//desbloqueando
                    ClientesView.acao = 2;
                 }
            }
            
            if((int)(getValue("id"))==3){
                if(ClientesView.acao == 1)
                ClientesView.executaCadastro();
                else if(ClientesView.acao == 2)
                //System.out.println("Aqui vai alterar");//alterar cadastro
                ClientesView.alteraCadastro(ClientesView.txtCampos[0].getText()); 
            }
            
            if((int)(getValue("id"))==4){//botão excluir
               if(ClientesView.confirmaExclusao() == true){ 
                    ClientesView.executaExclusao(ClientesView.txtCampos[0].getText());
                    ClientesView.limparCampos();
                    ClientesView.desbloquearCampos(false);
                    ClientesView.carregarLista();
               }    
            }
            
            if((int)(getValue("id"))==6){//botão sair
                SistemaView.telaClientes.dispose();
                SistemaView.btnModulos[0].setEnabled(true);
            }
            
            if((int)(getValue("id"))==7){//botão novo
                SistemaView.telaFuncionarios.desbloquearCampos(true);  
                SistemaView.telaFuncionarios.limparCampos();
                FuncionariosView.controlaSelecao(false);
                FuncionariosView.controlaAcao(1);
            }
            
            if((int)(getValue("id"))==8){//botão apagar
                SistemaView.telaFuncionarios.limparCampos();
            }
            
            if((int)(getValue("id"))==9){//botão consultar
                    SistemaView.telaFuncionarios.executaConsulta(1);
            }
            
            if((int)(getValue("id"))==10){//botão editar
                  if(FuncionariosView.retornaSelecao() == false)
                    JOptionPane.showMessageDialog(null,"Não há nenhum funcionário selecionado.",
                    "Atenção",JOptionPane.WARNING_MESSAGE);
                else 
                SistemaView.telaFuncionarios.desbloquearCampos(true);
                FuncionariosView.controlaAcao(2);
            }
            
            if((int)(getValue("id"))==11){//botão salvar
                if(FuncionariosView.retornaAcao() == 1)
                    SistemaView.telaFuncionarios.executaCadastro();
               else if(FuncionariosView.retornaAcao() == 2){
                    FuncionariosView.validaCampos();
                    FuncionariosView.alteraCadastro(FuncionariosView.txtCamposUm[4].getText());
                }  
            }
            
            if((int)(getValue("id")) == 12){//botão excluir
                if(FuncionariosView.confirmaExclusao() == true){
                    FuncionariosView.executaExclusao(FuncionariosView.txtCamposUm[4].getText());
                    FuncionariosView.limparCampos();
                    FuncionariosView.desbloquearCampos(true);
                    FuncionariosView.controlaSelecao(false);
                    FuncionariosView.carregarLista();
                }
            }
            
            if((int)(getValue("id"))==14){//botão sair
                SistemaView.telaFuncionarios.dispose();
                SistemaView.btnModulos[1].setEnabled(true);
            }

    	
    }//fechando actionPerformed


}
    

