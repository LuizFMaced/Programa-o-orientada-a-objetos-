import java.awt.event.ComponentAdapter;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;


public class ClientesView extends JInternalFrame implements ActionListener{
    //declarações dos objetos
    public static Container ctnTelaClientes;
    //barra de ferramentas
    public static JToolBar tbrClientes;
    public static ImageIcon icnNovo, icnConsulta, icnEditar, icnSalvar, icnExcluir, icnRelatorio, icnFechar;
    public static BotoesAcao btaNovo, btaConsulta, btaEditar, btaSalvar, btaExcluir, btaRelatorio, btaFechar;
    //menu
    public static JMenuBar mbrClientes;
    public static JMenu mnuArquivo, mnuConsultas, mnuAjuda;
    public static JMenuItem mniNovo, mniEditar, mniSalvar, mniFechar, mniConsCod, mniConsNome, mniSobre;
    //nome dos campos
    public static String strCampos[]=
    {"CPF","RG","Nome","Data de Nascimento","Endereço","Bairro","Cidade",
        "Estado","CEP","Telefone","Email"};
    public static JLabel lblCampos[];//texto
    public static JTextField txtCampos[];//caixa de texto
    
    public static JButton btnImagem; 
    public static ImageIcon imgFoto;
    public static JLabel lblFoto;
   
    public static JLabel lblBusca;//texto escrito "Busca"
    public static JTextField txtBusca;//caixa de texto da busca
    public static JButton btnBusca;//botão de "Buscar"
    public ImageIcon imgBusca;//imagem dentro do botão de busca
   
    //painel com barra de rolagem  
    public static JScrollPane scrClientes;
    //classe que vai gerenciar conteudo na tabela de Clientes
    public static DefaultTableModel mdlClientes;
    public static JTable tblClientes;
    
    public static int acao = 1;// 1- cadastar, 2- alterar
    public static boolean selecao = false;
    
    // metodo construtor
    public ClientesView (){ 
        //construção da janela de clientes
        super("Módulo Clientes");
        
        ctnTelaClientes = new Container();
        ctnTelaClientes.setLayout(null);
        this.add(ctnTelaClientes);
        
        //adicionando botoes e imagens na barra de tarefa
        tbrClientes = new JToolBar();
        tbrClientes.setBounds(150,10,327,40);
        ctnTelaClientes.add(tbrClientes);
        icnNovo = new ImageIcon("img/icnNovo.png");
        btaNovo = new BotoesAcao(0,icnNovo, "Adicionar cliente");
        tbrClientes.add(btaNovo);
        icnConsulta = new ImageIcon("img/icnConsulta.png");
        btaConsulta = new BotoesAcao(1,icnConsulta, "Consultar cliente");
        tbrClientes.add(btaConsulta);
        icnEditar = new ImageIcon("img/icnEditar.png");
        btaEditar = new BotoesAcao(2,icnEditar, "Editar cliente");
        tbrClientes.add(btaEditar);
        icnSalvar = new ImageIcon("img/icnSalvar.png");
        btaSalvar = new BotoesAcao(3,icnSalvar, "Salvar no banco");
        tbrClientes.add(btaSalvar);
        icnExcluir = new ImageIcon("img/icnExcluir.png");
        btaExcluir = new BotoesAcao(4,icnExcluir, "Excluir cliente");
        tbrClientes.add(btaExcluir);
        icnRelatorio = new ImageIcon("img/icnRelatorio.png");
        btaRelatorio = new BotoesAcao(5,icnRelatorio, "Imprimir relatório");
        tbrClientes.add(btaRelatorio);
        icnFechar = new ImageIcon("img/icnFechar.png");
        btaFechar = new BotoesAcao(6,icnFechar, "Fechar cliente");
        tbrClientes.add(btaFechar);
        
        //add barra de menu
        mbrClientes = new JMenuBar();
        this.setJMenuBar(mbrClientes);
        mnuArquivo = new JMenu("Arquivo");
        mnuArquivo.setMnemonic('a');
        mbrClientes.add(mnuArquivo);
        mnuConsultas = new JMenu("Consultas");
        mnuConsultas.setMnemonic('c');
        mbrClientes.add(mnuConsultas);
        mnuAjuda = new JMenu("Ajuda");
        mnuAjuda.setMnemonic('j');
        mbrClientes.add(mnuAjuda);
        //construindo itens do menu arquivo
        mniNovo = new JMenuItem("Novo Cliente");
        mnuArquivo.add(mniNovo);
        mniNovo.addActionListener(this);
        mniEditar = new JMenuItem("Editar dados");
        mnuArquivo.add(mniEditar);
        mniEditar.addActionListener(this);
        mniSalvar = new JMenuItem("Salvar alterações");
        mniSalvar.setEnabled(false);
        mnuArquivo.add(mniSalvar);
        mniSalvar.addActionListener(this);
        mnuArquivo.add(new JSeparator());
        mniFechar = new JMenuItem("Fechar módulo de clientes");
        mnuArquivo.add(mniFechar);
        mniFechar.addActionListener(this);
        mniConsCod = new JMenuItem("Por cpf");
        mniConsCod.addActionListener(this);
        mnuConsultas.add(mniConsCod);
        mniConsNome = new JMenuItem("Por nome");
        mniConsNome.addActionListener(this);
        mnuConsultas.add(mniConsNome);
        mniSobre = new JMenuItem("Sobre");
        mniSobre.addActionListener(this);
        mnuAjuda.add(mniSobre);
        
        
        lblCampos = new JLabel[strCampos.length];
        txtCampos = new JTextField[strCampos.length];
        for(int i= 0; i<strCampos.length; i++){
            lblCampos[i] = new JLabel(strCampos[i]);
            txtCampos[i] = new JTextField();
            lblCampos[i].setBounds(30,35+(i+1)*25,150,20);
            txtCampos[i].setBounds(150,35+(i+1)*25,350,20);
            ctnTelaClientes.add(lblCampos[i]);
            ctnTelaClientes.add(txtCampos[i]);
        }//fechando for
        
        //imagem de usuario
        imgFoto = new ImageIcon("img/users.png");
        lblFoto = new JLabel(imgFoto);
        lblFoto.setBounds(670,50,128,128);
        ctnTelaClientes.add(lblFoto);
        
        //construção da barra de busca
        lblBusca = new JLabel ("Busca Rápida");
        txtBusca = new JTextField();
        imgBusca = new ImageIcon("img/icnConsulta.png");
        btnBusca = new JButton("Buscar", imgBusca);
        btnBusca.addActionListener(this);
        ctnTelaClientes.add(lblBusca); 
        lblBusca.setBounds(850,35,100,20);
        ctnTelaClientes.add(txtBusca);
        txtBusca.setBounds(935,35,160,20);
        ctnTelaClientes.add(btnBusca);
        btnBusca.setBounds(1100,35,150,20);
        
        //construção da tabela com scroll
        tblClientes = new JTable();
        scrClientes = new JScrollPane(tblClientes);
        mdlClientes = (DefaultTableModel) tblClientes.getModel();
        String strDados[]={"CPF","Nome","Cidade","Telefone"};
        for(int i=0; i<strDados.length; i++){
            mdlClientes.addColumn(strDados[i]);
        }
        ctnTelaClientes.add(scrClientes);
        scrClientes.setBounds(850,60,400,280);
        
        btnImagem = new JButton("Selecionar imagem");
        btnImagem.addActionListener(this);
        btnImagem.setBounds(650,200,150,20);
        ctnTelaClientes.add(btnImagem);
        
        //bloqueando que o usuario possa movimentar as janelas dentro dos containers        
        this.addInternalFrameListener( new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent evt){
            SistemaView.btnModulos[0].setEnabled(true);}
            }//fechando InternalFrameAdapter
            );//fechando addInternalFramaListener
        this.addComponentListener((new ComponentAdapter() {
            public void componentMoved(ComponentEvent evt){
            SistemaView.telaClientes.setLocation(0,0);}
            }));
        
        desbloquearCampos(false);
        tblClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
             String cpf=tblClientes.getValueAt(tblClientes.getSelectedRow(),0).toString();
             try{   
                 ClientesVO tmpClinte = new ClientesVO();
                 tmpClinte=ClientesDAO.consultaCliente(cpf,1);
                 preencheCampos(tmpClinte);
                }catch(Exception erro){
                JOptionPane.showMessageDialog(null,erro.getMessage());
                }
            } 
        });
        
        carregarLista();
        
        this.setSize((int)SistemaView.dskJanelas.getWidth(),
                     (int)SistemaView.dskJanelas.getHeight());
        this.setIconifiable(true);
        this.setResizable(false);
        this.setClosable(true);
        this.show();
    }//fechando construtor
    
    public void actionPerformed (ActionEvent evt){
        
        if(evt.getSource() == mniNovo){
           desbloquearCampos(true);//desbloqueando
           limparCampos();
           acao = 1;
        }
        
        if(evt.getSource() == mniEditar){
           if(selecao == false)
           JOptionPane.showMessageDialog(null,"Não há nenhum cliente selecionado");
           else{
           desbloquearCampos(true);//desbloqueando
           acao = 2;
           }
        }
        
        if(evt.getSource() == mniSalvar){
            if(acao == 1)
            executaCadastro();
            else if(acao == 2)
            //System.out.println("Aqui vai alterar");//alterar cadastro
                alteraCadastro(txtCampos[0].getText());
        }
        
        if(evt.getSource()==mniConsCod){
            executaConsulta(1);
        }
        
        if(evt.getSource() == mniConsNome){
            executaConsulta(2);
        }
        
    }//fechando ActionPerformed
    
    public static void executaExclusao(String tmpCpf){
        try{
            ClientesDAO.excluiCliente(txtCampos[0].getText());
            JOptionPane.showMessageDialog(null,"Cliente "+txtCampos[2].getText().toUpperCase()+" excluído.");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null,"Falha na exclusão\n"+erro.getMessage());
        }
    }
   
    public static void executaConsulta(int tmpTipo){
        String tmpCpf="";
        if(tmpTipo == 1){
        tmpCpf = JOptionPane.showInputDialog("Entre com o cpf do cliente:");
            /*if(tmpCpf == null)
            JOptionPane.showMessageDialog(null,"Cancelando...", "Fechar", 
                    JOptionPane.INFORMATION_MESSAGE);*/
        }else if(tmpTipo == 2){
        tmpCpf = JOptionPane.showInputDialog("Entre com o nome do cliente:");
        }
      
        ClientesVO  tmpCliente = new ClientesVO();
        
        try{
            tmpCliente = ClientesDAO.consultaCliente(tmpCpf, tmpTipo);
            
            if(tmpCliente == null){//se o resultado do consultaCliente for em branco
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            }else{
                //mostra na caixa de texto
                preencheCampos(tmpCliente);
                desbloquearCampos(false);
                selecao = true;
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        
        
    }
    
    public static void executaCadastro(){
        ClientesVO tmpCliente = new ClientesVO();
      /*TMPCLIENTE deve receber os dados que o usuário digitar
        nas caixas de texto para que sejam cadastrados  */  
     
      //pegando texto texto da caixa e guardando no objeto TmpCliente
        preencheObjeto(tmpCliente);
        try{
            if(ClientesDAO.consultaCliente(tmpCliente.getCpf(), 1)!=null){
                JOptionPane.showMessageDialog(null,"CPF já existente");
            }else{//se ninguém com esse cpf, pode cadastrar
                if(validaCampos()==true){
            ClientesDAO.cadastraCliente(tmpCliente);
            carregarLista();
            JOptionPane.showMessageDialog(null, "Cliente Cadastrado.");
            desbloquearCampos(false);//bloqueando
                }
            }    
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null,"Falha no cadastro\n"+erro.getMessage());
        }
    }//fechando executaCadastro
    
    public static void preencheCampos(ClientesVO tmpCliente){
        //tmpCliente.setCpf(txtCampos[0].getText());
        txtCampos[0].setText(tmpCliente.getCpf());
        txtCampos[1].setText(tmpCliente.getRg());
        txtCampos[2].setText(tmpCliente.getNome());
        txtCampos[3].setText(tmpCliente.getNascimento());
        txtCampos[4].setText(tmpCliente.getEndereco());
        txtCampos[5].setText(tmpCliente.getBairro());
        txtCampos[6].setText(tmpCliente.getCidade());
        txtCampos[7].setText(tmpCliente.getEstado());
        txtCampos[8].setText(tmpCliente.getCep());
        txtCampos[9].setText(tmpCliente.getTel());
        txtCampos[10].setText(tmpCliente.getEmail());
        lblFoto.setIcon(new ImageIcon("img/" + tmpCliente.getFoto()));      
    }
    
    public static void preencheObjeto(ClientesVO tmpCliente){
        tmpCliente.setCpf(txtCampos[0].getText());
        tmpCliente.setRg(txtCampos[1].getText());
        tmpCliente.setNome(txtCampos[2].getText());
        tmpCliente.setNascimento(txtCampos[3].getText());
        tmpCliente.setEndereco(txtCampos[4].getText());
        tmpCliente.setBairro(txtCampos[5].getText());
        tmpCliente.setCidade(txtCampos[6].getText());
        tmpCliente.setEstado(txtCampos[7].getText());
        tmpCliente.setCep(txtCampos[8].getText());
        tmpCliente.setTel(txtCampos[9].getText());
        tmpCliente.setEmail(txtCampos[10].getText());
    }
    
    public static void limparCampos(){
        for(int i=0; i<txtCampos.length; i++){
            txtCampos[i].setText("");
        }
    }//fechando limparCampos
    
    public static void desbloquearCampos(boolean tmpStatus){
       for(int i=0; i<txtCampos.length; i++){
            txtCampos[i].setEditable(tmpStatus);
        }
       if(tmpStatus == true) mniSalvar.setEnabled(true);
       else mniSalvar.setEnabled(false);
       
    }//fechando bloquearCampos
    
    public static void carregarLista(){
        List<ClientesVO> vetorClientes = new ArrayList<ClientesVO>();
        for(int i=0;i<mdlClientes.getRowCount();i++){//limpando lista
            mdlClientes.removeRow(i);
        }
        try{
          vetorClientes = ClientesDAO.listaClientes();
          for(ClientesVO tmpCliente : vetorClientes){//para cada cliente existente no vetor
              String dados[] = new String[4];
              dados[0]= tmpCliente.getCpf();
              dados[1]= tmpCliente.getNome();
              dados[2]= tmpCliente.getCidade();
              dados[3]= tmpCliente.getTel();
          mdlClientes.addRow(dados);//add linha na tabela
          }           
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
    
    public static boolean validaCampos(){
        for(int i=0; i<txtCampos.length;i++){
            if(txtCampos[i].getText().compareTo("") == 0){
                JOptionPane.showMessageDialog(null,"Preencha o campo "+strCampos[i]);
                txtCampos[i].grabFocus();
                txtCampos[i].setBackground(Color.pink);
                return false;
            }   
        }
        return true; 
    }
    
    public static void alteraCadastro(String tmpCpf){
      ClientesVO tmpCliente = new ClientesVO();
      /*TMPCLIENTE deve receber os dados que o usuário digitar
        nas caixas de texto para que sejam cadastrados  */  
      
    //pegando texto texto da caixa e guardandono objeto TmpCliente
        preencheObjeto(tmpCliente);
      try{
          ClientesDAO.alteraCliente(tmpCpf, tmpCliente);
          JOptionPane.showMessageDialog(null,"Dados alterados.");
          carregarLista();
          desbloquearCampos(false);
          selecao= true;
      }catch(Exception erro){
       JOptionPane.showMessageDialog(null,"Falha na alteração\n"+erro.getMessage());
       }
    }
    
    public static boolean confirmaExclusao(){
        int resp = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir?","Atenção",
                JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION) return true;
        return false;
    }
    
}//fechando classe

