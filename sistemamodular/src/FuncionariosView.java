import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.InternalFrameAdapter;
import java.awt.event.ComponentAdapter;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.Locale;

public class FuncionariosView extends JInternalFrame implements ActionListener{
    public static Container ctnTelaFuncionarios;
    public static ImageIcon imgBannerFunc;
    //data e hora do sistema
    public static Date data;
    public static DateFormat dateForm;
    public static String recebeData, recebeHora;
    public static JLabel exibeData, exibeHora;
    public static Calendar calendario;
    //banner
    public static JLabel lblBannerFunc;
    public static JPanel cdtFunc,cstFunc;
    //barra de tarefas
    public static JTabbedPane tabs;
    public static JToolBar tbrFunc;
    public static ImageIcon icnNovo, icnLimpar, icnConsulta, icnEditar, icnSalvar, icnExcluir, 
                            icnRelatorio,icnFechar;
    public static BotoesAcao btaNovo, btaLimpar, btaConsulta, btaEditar, btaSalvar, btaExcluir, 
                            btaRelatorio, btaFechar;        
    //barra de menu
    public static JMenuBar mbrFunc;
    public static JMenu mnuArquivo, mnuConsultas, mnuAjuda;
    public static JMenuItem mniNovo, mniEditar, mniSalvar, mniFechar, mniConsCod, 
                            mniConsNome, mniSobre;
    
    //vetores responsaveis por criar os formulários
    public static JLabel lblCamposUm[], lblCamposDois[];
    public static JTextField txtCamposUm[],txtCamposDois[];
   
    public static String strCamposUm[]= { "Código:","Nome:", "Endereço:","Telefone:",
    "CPF:","Celular:","RG:","Sexo:","Cargo:","Setor:","Estado Civil:", "Graduação:",
    "Carga Horária:","Data de Nascimento:","Data de Admissão:","Carteira de Trabalho:"};
    
    public static String strCamposDois[]={"Salário:","Modo de Pagamento:",
    "Tipo de Conta:", "Banco:","Conta Bancário:", "Obeservações:"};
    
    //area de texto do campo observações
    public static JTextArea txtArea;
    public static JScrollPane scrlPainel;
    //radio box do campo sexo
    public static ButtonGroup radioGroup;
    public static JRadioButton radio1, radio2;
    //combos de seleção
    public static JComboBox cmbCargo,cmbSetor,cmbCivil,cmbConta,cmbBanco;
    //painel com barra de rolagem  
    public static JScrollPane scrFunc, scrFuncCons;
    //classe que vai gerenciar conteudo na tabela de Clientes
    public static DefaultTableModel mdlFunc, mdlFuncCons;
    public static JTable tblFunc, tblFuncCons;
    
    //controlando se cadastra ou altera o funcionario
    public static int acao = 1; //1- cadastar, 2- alterar
    public static boolean selecao = false;/* false- campos desbloqueado 
                                             true- campos bloqueado*/
    
    //método construtor
    public FuncionariosView(){
     super("Módulo Funcionários");
        
        ctnTelaFuncionarios = new Container();
        ctnTelaFuncionarios.setLayout(null);
        this.add(ctnTelaFuncionarios);
       
        imgBannerFunc = new ImageIcon("img/bannerFunc.png");
        lblBannerFunc = new JLabel(imgBannerFunc);
        lblBannerFunc.setBounds(0,0,1366,50);
        ctnTelaFuncionarios.add(lblBannerFunc);
       
        //add a data 
        data = new Date();
        dateForm = DateFormat.getDateInstance(DateFormat.FULL);
        recebeData = dateForm.format(data);
        exibeData = new JLabel(recebeData);
        exibeData.setBounds(950, 52, 300, 20);
        ctnTelaFuncionarios.add(exibeData);
        //add a hora
        calendario = Calendar.getInstance();
        calendario.setTime(data);
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int min = calendario.get(Calendar.MINUTE);
        int seg = calendario.get(Calendar.SECOND);
        recebeHora = (hora+"h:"+min+"m:"+seg+"s");
        exibeHora = new JLabel(recebeHora);
        exibeHora.setBounds(1260, 52, 106, 20);
        ctnTelaFuncionarios.add(exibeHora);
        
        
        //add barra de menu
        mbrFunc = new JMenuBar();
        mbrFunc.setBounds(0,0,1366,20);
        this.setJMenuBar(mbrFunc);
        mnuArquivo = new JMenu("Arquivo");
        mnuArquivo.setMnemonic('a');
        mbrFunc.add(mnuArquivo);
        mnuConsultas = new JMenu("Consultas");
        mnuConsultas.setMnemonic('c');
        mbrFunc.add(mnuConsultas);
        mnuAjuda = new JMenu("Ajuda");
        mnuAjuda.setMnemonic('j');
        mbrFunc.add(mnuAjuda);
        //construindo itens do menu arquivo
        mniNovo = new JMenuItem("Novo Cliente");
        mnuArquivo.add(mniNovo);
        mniNovo.addActionListener(this);
        mniEditar = new JMenuItem("Editar dados");
        mnuArquivo.add(mniEditar);
        mniEditar.addActionListener(this);
        mniSalvar = new JMenuItem("Salvar alterações");
        mnuArquivo.add(mniSalvar);
        mniSalvar.addActionListener(this);
        mnuArquivo.add(new JSeparator());
        mniFechar = new JMenuItem("Fechar módulo de funcionários");
        mnuArquivo.add(mniFechar);
        mniFechar.addActionListener(this);
        //construindo itens do menu consulta
        mniConsCod = new JMenuItem("Por cpf");
        mniConsCod.addActionListener(this);
        mnuConsultas.add(mniConsCod);
        mniConsNome = new JMenuItem("Por nome");
        mniConsNome.addActionListener(this);
        mnuConsultas.add(mniConsNome);
        //construindo itens do menu ajuda
        mnuAjuda.add(new JSeparator());
        mniSobre = new JMenuItem("Sobre");
        mniSobre.addActionListener(this);
        mnuAjuda.add(mniSobre);
        
        cdtFunc = new JPanel();
        cdtFunc.setLayout(null);
        cstFunc = new JPanel();
        cstFunc.setLayout(null);   
        tabs = new JTabbedPane();
        tabs.setBounds(0,50,1366,390);
        tabs.addTab("Cadastrar",cdtFunc);
        tabs.addTab("Consultar",cstFunc);
        ctnTelaFuncionarios.add(tabs);
        
        tbrFunc = new JToolBar();
        tbrFunc.setBounds(550,282,370,40);
        cdtFunc.add(tbrFunc);
        
        icnNovo = new ImageIcon("img/icnNovo.png");
        btaNovo = new BotoesAcao(7,icnNovo, "Adicionar cliente");
        tbrFunc.add(btaNovo);
        icnLimpar = new ImageIcon("img/icnLimpar.png");
        btaLimpar = new BotoesAcao(8,icnLimpar, "Limpar campos");
        tbrFunc.add(btaLimpar);
        icnConsulta = new ImageIcon("img/icnConsulta.png");
        btaConsulta = new BotoesAcao(9,icnConsulta, "Consultar cliente");
        tbrFunc.add(btaConsulta);
        icnEditar = new ImageIcon("img/icnEditar.png");
        btaEditar = new BotoesAcao(10,icnEditar, "Editar cliente");
        tbrFunc.add(btaEditar);
        icnSalvar = new ImageIcon("img/icnSalvar.png");
        btaSalvar = new BotoesAcao(11,icnSalvar, "Salvar no banco");
        tbrFunc.add(btaSalvar);
        icnExcluir = new ImageIcon("img/icnExcluir.png");
        btaExcluir = new BotoesAcao(12,icnExcluir, "Excluir cliente");
        tbrFunc.add(btaExcluir);
        icnRelatorio = new ImageIcon("img/icnRelatorio.png");
        btaRelatorio = new BotoesAcao(13,icnRelatorio, "Imprimir relatório");
        tbrFunc.add(btaRelatorio);
        icnFechar = new ImageIcon("img/icnFechar.png");
        btaFechar = new BotoesAcao(14,icnFechar, "Fechar funcionários");
        tbrFunc.add(btaFechar);
        
      /*depois de criar o 1°primeiro vetor, add os nomes das etiquetas e seu campo
        de texto na tela*/ 
      lblCamposUm = new JLabel[strCamposUm.length];
      txtCamposUm = new JTextField[strCamposUm.length];
      for(int i=0; i<strCamposUm.length; i++){
        if(i==0){
            lblCamposUm[i]= new JLabel(strCamposUm[i]);
            txtCamposUm[i]= new JTextField();
            //txtCamposUm[i].setEditable(false);
            lblCamposUm[i].setBounds(15,(i+1)*25, 150, 20);
            txtCamposUm[i].setBounds(180,(i+1)*25, 50, 20);
            cdtFunc.add(lblCamposUm[i]);
            cdtFunc.add(txtCamposUm[i]);
        }else if(i<=2){
            lblCamposUm[i] = new JLabel(strCamposUm[i]);
            txtCamposUm[i] = new JTextField();
            //txtCamposUm[i].setEditable(false);
            lblCamposUm[i].setBounds(15,(i+1)*25,150,20);
            txtCamposUm[i].setBounds(180,(i+1)*25,350,20);
            cdtFunc.add(lblCamposUm[i]);
            cdtFunc.add(txtCamposUm[i]);
        }else if(i>=3 & i<=6){
            if(i>=3 & i<=4){
            lblCamposUm[i] = new JLabel(strCamposUm[i]);
            txtCamposUm[i] = new JTextField();
            //txtCamposUm[i].setEditable(false);
            lblCamposUm[i].setBounds(15,(i+1)*25,70,20);
            txtCamposUm[i].setBounds(180,(i+1)*25,100,20);
            cdtFunc.add(lblCamposUm[i]);
            cdtFunc.add(txtCamposUm[i]);
            }else if(i == 5){
            lblCamposUm[i] = new JLabel(strCamposUm[i]);
            txtCamposUm[i] = new JTextField();
            //txtCamposUm[i].setEditable(false);
            lblCamposUm[i].setBounds(300,(3+1)*25,70,20);
            txtCamposUm[i].setBounds(380,(3+1)*25,150,20);
            cdtFunc.add(lblCamposUm[i]);
            cdtFunc.add(txtCamposUm[i]);
            }else if (i==6){
            lblCamposUm[i] = new JLabel(strCamposUm[i]);
            txtCamposUm[i] = new JTextField();
            //txtCamposUm[i].setEditable(false);
            lblCamposUm[i].setBounds(300,(4+1)*25,70,20);
            txtCamposUm[i].setBounds(380,(4+1)*25,150,20);
            cdtFunc.add(lblCamposUm[i]);
            cdtFunc.add(txtCamposUm[i]);    
            }
        }else if(i==7){
            lblCamposUm[i] = new JLabel(strCamposUm[i]);
            lblCamposUm[i].setBounds(15,(i+1)*19,50,20);
            cdtFunc.add(lblCamposUm[i]);
            radio1 = new JRadioButton("Masculino");
             radio1.setSelected(true);
       
            radio1.setBounds(180,(i+1)*19,100,20);
            radio2 = new JRadioButton("Feminino");
           
            radio2.setBounds(280,(i+1)*19,100,20);
            radioGroup = new ButtonGroup();
            radioGroup.add(radio1); 
            radioGroup.add(radio2);
            cdtFunc.add(radio1);
            cdtFunc.add(radio2);
            
           
            
        }else if(i>=8 & i<=10){
            lblCamposUm[i] = new JLabel(strCamposUm[i]);
            lblCamposUm[i].setBounds(15,6+(i+1)*19,220,20);
            cdtFunc.add(lblCamposUm[i]);
             /*dependendo da posição do vetor, será add uma caixa de seleção 
             diferente*/
                if(i==8){  
                    String dados[]={"Estagiário", "Administrativo", "Operário", "Limpeza",
                    "Programador","Segurança"};
                    cmbCargo = new JComboBox(dados);
                    cmbCargo.setBounds(180,6+(i+1)*19,350,20);
                    //cmbCargo.setEnabled(false);
                    cdtFunc.add(cmbCargo);
                }else if(i==9){
                    String dados[]={"Suporte", "Comercial", "Industrial", "Almoxarifado",
                     "Tecnologia","Tercerizado"};
                    cmbSetor = new JComboBox(dados);   
                    cmbSetor.setBounds(180,6+(i+1)*19,350,20);
                    //cmbSetor.setEnabled(false);
                    cdtFunc.add(cmbSetor);
                }else if(i==10){
                    String dados[]={"Solteiro", "Casado", "Viúvo"};
                    cmbCivil = new JComboBox(dados);
                    cmbCivil.setBounds(180,6+(i+1)*19,350,20);
                    //cmbCivil.setEnabled(false);
                    cdtFunc.add(cmbCivil);    
                }
            }else if(i>=11 & i<=12){
                if(i==11){
                lblCamposUm[i] = new JLabel(strCamposUm[i]);
                txtCamposUm[i] = new JTextField();  
                //txtCamposUm[i].setEditable(false);
                lblCamposUm[i].setBounds(15,12+(i+1)*19,130,20);
                txtCamposUm[i].setBounds(180,12+(i+1)*19,350,20);
                cdtFunc.add(lblCamposUm[i]);
                cdtFunc.add(txtCamposUm[i]);
                }else if(i==12){
                lblCamposUm[i] = new JLabel(strCamposUm[i]);
                txtCamposUm[i] = new JTextField();
                //txtCamposUm[i].setEditable(false);
                lblCamposUm[i].setBounds(15,14+(i+1)*19,130,20);
                txtCamposUm[i].setBounds(180,14+(i+1)*19,350,20);
                cdtFunc.add(lblCamposUm[i]);
                cdtFunc.add(txtCamposUm[i]);    
                }
            }else if(i>=13 & i<=15){
                if(i==13){
                lblCamposUm[i] = new JLabel(strCamposUm[i]);
                txtCamposUm[i] = new JTextField(); 
                //txtCamposUm[i].setEditable(false);
                lblCamposUm[i].setBounds(15,16+(i+1)*19,190,20);
                txtCamposUm[i].setBounds(180,16+(i+1)*19,90,20);
                cdtFunc.add(lblCamposUm[i]);
                cdtFunc.add(txtCamposUm[i]); 
                }else if(i==14){
                lblCamposUm[i] = new JLabel(strCamposUm[i]);
                txtCamposUm[i] = new JTextField();
                //txtCamposUm[i].setEditable(false);
                lblCamposUm[i].setBounds(280,16+(13+1)*19,190,20);
                txtCamposUm[i].setBounds(440,16+(13+1)*19,90,20);
                cdtFunc.add(lblCamposUm[i]);
                cdtFunc.add(txtCamposUm[i]);    
                }else if(i==15){
                lblCamposUm[i] = new JLabel(strCamposUm[i]);
                txtCamposUm[i] = new JTextField(); 
                //txtCamposUm[i].setEditable(false);
                lblCamposUm[i].setBounds(15,(i+1)*19,180,20);
                txtCamposUm[i].setBounds(180,(i+1)*19,350,20);
                cdtFunc.add(lblCamposUm[i]);
                cdtFunc.add(txtCamposUm[i]); 
                }
            }
               
      } 

        /*depois de criar o 2°segundo vetor, add os nomes das etiquetas e seu campo
        de texto na tela*/ 
     lblCamposDois = new JLabel[strCamposDois.length];
     txtCamposDois = new JTextField[strCamposDois.length];
      for(int i=0; i<strCamposDois.length; i++){
          /*as etiquetas são colocasdas dentro do objeto vetor lblCampos,
          e loco depois posicionado  e add na tela*/
             
             /*dependendo da posição do vetor, será add uma caixa de seleção 
             diferente*/
            if(i==2){
                lblCamposDois[i] = new JLabel(strCamposDois[i]);
                lblCamposDois[i].setBounds(550,(i+1)*25,120,20);
                cdtFunc.add(lblCamposDois[i]);
                String dados[]={"Poupança","Corrente"};
                cmbConta = new JComboBox(dados);
                cmbConta.setBounds(700,(i+1)*25,220,20);
                //cmbConta.setEnabled(false);
                cdtFunc.add(cmbConta);
            }else if(i==3){
                lblCamposDois[i] = new JLabel(strCamposDois[i]);
                lblCamposDois[i].setBounds(550,(i+1)*25,160,20);
                cdtFunc.add(lblCamposDois[i]);
                String dados[]={"Caixa","Banco do Brasil","Bradesco","Itaú"};
                cmbBanco= new JComboBox(dados);
                cmbBanco.setBounds(700,(i+1)*25,220,20);
                //cmbBanco.setEnabled(false);
                cdtFunc.add(cmbBanco);
            }else if(i!=2 & i!=3 & i!=5){
                lblCamposDois[i] = new JLabel(strCamposDois[i]);
                lblCamposDois[i].setBounds(550,(i+1)*25,220,20);
                txtCamposDois[i] = new JTextField();
                //txtCamposDois[i].setEditable(false);
                txtCamposDois[i].setBounds(700,(i+1)*25,220,20);
                cdtFunc.add(lblCamposDois[i]);
                cdtFunc.add(txtCamposDois[i]);
            }else if(i==5){
               lblCamposDois[i] = new JLabel(strCamposDois[i]); 
               lblCamposDois[i].setBounds(550,(i+1)*25,220,20);
               cdtFunc.add(lblCamposDois[i]);
               txtArea = new JTextArea();
               //txtArea.setEnabled(false);
                txtArea.setLineWrap(true);
                txtArea.setWrapStyleWord(true);
                txtArea.setBounds(550,175,380,100);
               /*scrlPainel = new JScrollPane(txtArea);
                scrlPainel.setLayout(null);
                scrlPainel.setBounds(750,175,380,100);
              scrlPainel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
              scrlPainel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
              */  
               cdtFunc.add(txtArea);
            }
            
        }   
            
      //construção da tabela de consulta com scroll
        tblFuncCons = new JTable();
        scrFuncCons = new JScrollPane(tblFuncCons);
        mdlFuncCons = (DefaultTableModel) tblFuncCons.getModel();
        /*for(int i=0; i<strCamposUm.length; i++){
            mdlFuncCons.addColumn(strCamposUm[i]);
        }*/
        scrFuncCons.setBounds(0,50,1350,300);
        cstFunc.add(scrFuncCons);
        
        //construção da tabela comum com scroll
        tblFunc = new JTable();
        scrFunc = new JScrollPane(tblFunc);
        mdlFunc = (DefaultTableModel) tblFunc.getModel();
        String strDados[]={"CPF","Nome","RG","Telefone"};
        for(int i=0; i<strDados.length; i++){
            mdlFunc.addColumn(strDados[i]);
        }
        scrFunc.setBounds(940,25,400,300);
        cdtFunc.add(scrFunc);
        
        carregarLista();
         
        //bloqueando que o usuario possa movimentar as janelas dentro dos containers        
        this.addInternalFrameListener( new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent evt){
            SistemaView.btnModulos[1].setEnabled(true);}
            }//fechando InternalFrameAdapter
            );//fechando addInternalFramaListener
        this.addComponentListener((new ComponentAdapter() {
            public void componentMoved(ComponentEvent evt){
            SistemaView.telaFuncionarios.setLocation(0,0);}
            }));
        
        
        this.setSize((int)SistemaView.dskJanelas.getWidth(),
                     (int)SistemaView.dskJanelas.getHeight());
        this.setIconifiable(true);
        this.setResizable(false);
        this.setClosable(true);
        this.show();
    }
    
    public void actionPerformed (ActionEvent evt){
        
       if(evt.getSource() == mniSalvar){
           if(retornaAcao() == 1) 
           executaCadastro();
           else //se caso acao for igual a 2
           alteraCadastro(txtCamposUm[4].getText());    
       }
       
       if(evt.getSource() == mniNovo){
           limparCampos();
           controlaAcao(1);
           controlaSelecao(false);
       }
       
       if(evt.getSource() == mniEditar){
           if(retornaSelecao() == false) 
        JOptionPane.showMessageDialog(null,"Não há nenhum funcionário selecionado.",
                "Atenção",JOptionPane.WARNING_MESSAGE);
           else{
           desbloquearCampos(true);
               controlaAcao(2);
           }
       }
      
       if(evt.getSource() == mniConsCod){
           executaConsulta(1);
       }
       
       if(evt.getSource() == mniConsNome){
           executaConsulta(2);
       }
    }
       
    public static void executaCadastro(){
        FuncionariosVO tmpFunc = new FuncionariosVO();
      /*TMPFUNC deve receber os dados que o usuário digitar
        nas caixas de texto para que sejam cadastrados  */  
     
      //pegando texto da caixa e guardandono objeto TmpFunc
        preencheObjeto(tmpFunc);
        try{
            if(FuncionariosDAO.consultaFuncionarios(tmpFunc.getCpf(), 1)!=null){
                JOptionPane.showMessageDialog(null,"CPF já existente");
            }else{//se ninguém com esse cpf, pode cadastrar
                if(validaCampos()==true){
                FuncionariosDAO.cadastraFuncionarios(tmpFunc);
                JOptionPane.showMessageDialog(null, "Funcionário Cadastrado.");
                carregarLista();
                desbloquearCampos(false);//bloqueando     
                }    
            }    
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null,erro.getMessage());
        }
    }

    public static void executaConsulta(int tmpTipo){
        String tmpCpf="";
        if(tmpTipo == 1){
        tmpCpf = JOptionPane.showInputDialog("Entre com o cpf do funcionário:");
            /*if(tmpCpf == null)
            JOptionPane.showMessageDialog(null,"Cancelando...", "Fechar", 
                    JOptionPane.INFORMATION_MESSAGE);*/
        }else if(tmpTipo == 2){
        tmpCpf = JOptionPane.showInputDialog("Entre com o nome do funcionário:");
        }
      
        FuncionariosVO tmpFunc = new FuncionariosVO(); 
        try{
            tmpFunc = FuncionariosDAO.consultaFuncionarios(tmpCpf, tmpTipo);
            
            if(tmpFunc == null){//se o resultado do consultaCliente for em branco
                JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
            }else{
                //mostra na caixa de texto
                preencheCampos(tmpFunc);
                desbloquearCampos(false);
                controlaSelecao(true);
            }
            
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        
        
    }
    
    public static void alteraCadastro(String tmpCpf){
        FuncionariosVO tmpFunc = new FuncionariosVO();
        preencheObjeto(tmpFunc);
        try{
            FuncionariosDAO.alteraFuncionarios(tmpCpf, tmpFunc);
            JOptionPane.showMessageDialog(null,"Dados alterados.");
            carregarLista();
            desbloquearCampos(false);
            selecao= true;      
        }catch(Exception erro){
        JOptionPane.showMessageDialog(null,"Falha na alteração\n"+erro.getMessage());   
        }   
    }
    
    public static void executaExclusao(String tmpCpf){
        try{
        FuncionariosDAO.excluiFuncionarios(tmpCpf);
        JOptionPane.showMessageDialog(null,"Funcionário "+
            txtCamposUm[1].getText().toUpperCase()+" excluído.");
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null,"Falha na exclusão\n"+erro.getMessage());
        }
    }
    
    public static boolean confirmaExclusao(){
        if(retornaSelecao() == false)
          JOptionPane.showMessageDialog(null,"Não há nada a ser excluído.",
                "Atenção",JOptionPane.WARNING_MESSAGE);  
        else{
        int resp = JOptionPane.showConfirmDialog(null,"Deseja realmente excluir?","Atenção",
                JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION) return true;
        }
       return false;
    }

    public static void preencheCampos(FuncionariosVO tmpFunc){
        //tmpCliente.setCpf(txtCampos[0].getText());
        txtCamposUm[0].setText(tmpFunc.getCodigo());
        txtCamposUm[1].setText(tmpFunc.getNome());
        txtCamposUm[2].setText(tmpFunc.getEndereco());
        txtCamposUm[3].setText(tmpFunc.getTelefone());
        txtCamposUm[4].setText(tmpFunc.getCpf());
        txtCamposUm[5].setText(tmpFunc.getCelular());
        txtCamposUm[6].setText(tmpFunc.getRg());
        switch (tmpFunc.getSexo()){
            case "Masculino": radio1.setSelected(true); break; 
            case "Feminino":  radio2.setSelected(true); break;
        }        
        System.out.println(tmpFunc.getSexo());
        cmbCargo.setSelectedItem(tmpFunc.getCargo());
        cmbSetor.setSelectedItem(tmpFunc.getSetor());
        cmbCivil.setSelectedItem(tmpFunc.getEstadoCivil());
        txtCamposUm[11].setText(tmpFunc.getGraduacao());
        txtCamposUm[12].setText(tmpFunc.getCargaHoraria());
        txtCamposUm[13].setText(tmpFunc.getDataNascimento());
        txtCamposUm[14].setText(tmpFunc.getDataAdmissao());
        txtCamposUm[15].setText(tmpFunc.getCarteiraTrabalho());
        txtCamposDois[0].setText(tmpFunc.getSalario());
        txtCamposDois[1].setText(tmpFunc.getModoPagamento());
        cmbConta.setSelectedItem(tmpFunc.getTipoConta());
        cmbBanco.setSelectedItem(tmpFunc.getBanco());
        txtCamposDois[4].setText(tmpFunc.getContaBancaria());
        txtArea.setText(tmpFunc.getObservacoes()); 
    }  
    
    public static void preencheObjeto(FuncionariosVO tmpFunc){
      tmpFunc.setCodigo(txtCamposUm[0].getText());
      tmpFunc.setNome(txtCamposUm[1].getText());
      tmpFunc.setEndereco(txtCamposUm[2].getText());
      tmpFunc.setTelefone(txtCamposUm[3].getText());
      tmpFunc.setCpf(txtCamposUm[4].getText());
      tmpFunc.setCelular(txtCamposUm[5].getText());
      tmpFunc.setRg(txtCamposUm[6].getText());
      
      if(radio1.isSelected())
            tmpFunc.setSexo("Masculino");
      else
            tmpFunc.setSexo("Feminino");
       
      tmpFunc.setCargo(String.valueOf(cmbCargo.getSelectedItem()));
      tmpFunc.setSetor(String.valueOf(cmbSetor.getSelectedItem())); 
      tmpFunc.setEstadoCivil(String.valueOf(cmbCivil.getSelectedItem())); 
      tmpFunc.setGraduacao(txtCamposUm[11].getText());
      tmpFunc.setCargaHoraria(txtCamposUm[12].getText());
      tmpFunc.setDataNascimento(txtCamposUm[13].getText());
      tmpFunc.setDataAdmissao(txtCamposUm[14].getText());
      tmpFunc.setCarteiraTrabalho(txtCamposUm[15].getText());
      tmpFunc.setSalario(txtCamposDois[0].getText());
      tmpFunc.setModoPagamento(txtCamposDois[1].getText());
      tmpFunc.setTipoConta(String.valueOf(cmbConta.getSelectedItem()));
      tmpFunc.setBanco(String.valueOf(cmbBanco.getSelectedItem()));
      tmpFunc.setContaBancaria(txtCamposDois[4].getText());
      tmpFunc.setObservacoes(txtArea.getText());
    }
    
    public static boolean validaCampos(){
    for(int i=0; i<txtCamposUm.length;i++){
        if(i!=7 & i!=8 & i!=9 & i!=10){
            if(txtCamposUm[i].getText().compareTo("") == 0){
                JOptionPane.showMessageDialog(null,"Preencha o campo "+strCamposUm[i].toUpperCase());
                txtCamposUm[i].grabFocus();
                txtCamposUm[i].setBackground(Color.pink);
                return false;   
            }         
            if(i==0){
                try{
                int valida = Integer.parseInt(txtCamposUm[0].getText());
                }catch(NumberFormatException erro){
                    txtCamposUm[i].grabFocus();
                    txtCamposUm[i].setBackground(Color.pink);
                JOptionPane.showMessageDialog(null,"Preencha o campo "+strCamposUm[0].toUpperCase()+
                "\n apenas com caracteres númericos.","Atenção",JOptionPane.ERROR_MESSAGE);
                return false;
                }
            }
        }  
         
      } 
        for(int i=0; i<txtCamposDois.length;i++){
          if(i!=2 & i!=3 & i!=5){ 
              if(txtCamposDois[i].getText().compareTo("") == 0){
                  JOptionPane.showMessageDialog(null,"Preencha o campo "+strCamposDois[i].toUpperCase());
                  txtCamposDois[i].grabFocus();
                  txtCamposDois[i].setBackground(Color.pink);
                  return false;   
              }
          }    
        }
        return true; 
    }
    
    public static void carregarLista(){
        List<FuncionariosVO> vetorFunc = new ArrayList<FuncionariosVO>();
        limparLista();
        try{
          vetorFunc = FuncionariosDAO.listaFuncionarios();
          for(FuncionariosVO tmpFunc : vetorFunc){//para cada cliente existente no vetor
              String dados[] = new String[4];
              dados[0]= tmpFunc.getCpf();
              dados[1]= tmpFunc.getNome();
              dados[2]= tmpFunc.getRg();
              dados[3]= tmpFunc.getTelefone();
          mdlFunc.addRow(dados);//add linha na tabela
          }           
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
    
    public static void limparLista(){
        while(mdlFunc.getRowCount()>0){//limpando lista
            mdlFunc.removeRow(0);
        }
    }
        
    public static void desbloquearCampos(boolean tmpStatus){
       for(int i=0; i<7; i++){
            txtCamposUm[i].setEditable(tmpStatus);
       }
         cmbCargo.setEnabled(tmpStatus);
         cmbSetor.setEnabled(tmpStatus);
         cmbCivil.setEnabled(tmpStatus);
            for(int i=11; i<16; i++){
            txtCamposUm[i].setEditable(tmpStatus);
       }
             for(int i=0; i<2; i++){
            txtCamposDois[i].setEditable(tmpStatus);
             }
             txtCamposDois[4].setEditable(tmpStatus);
            cmbConta.setEnabled(tmpStatus);
            cmbBanco.setEnabled(tmpStatus);
            
                radio1.setEnabled(tmpStatus);
                radio2.setEnabled(tmpStatus);
                txtArea.setEnabled(tmpStatus);
    }        
       
    public static void limparCampos(){
        for(int i=0; i<txtCamposUm.length; i++){
            if(i<=6){
            txtCamposUm[i].setText("");
            txtCamposUm[i].setBackground(Color.white);
            }else if(i>=11){
            txtCamposUm[i].setText("");
            txtCamposUm[i].setBackground(Color.white);
            }
        }
         for(int i=0; i<txtCamposDois.length; i++){
            if(i<=1){
             txtCamposDois[i].setText("");
             txtCamposDois[i].setBackground(Color.white);
            }else if(i==4){
              txtCamposDois[i].setText("");
              txtCamposDois[i].setBackground(Color.white);
            }
        }
         txtArea.setText("");
         desbloquearCampos(true);
         controlaAcao(1);
         controlaSelecao(false);
    }

    public static void controlaSelecao(Boolean tmpSelecao){
        selecao = tmpSelecao;
    }
    
    public static boolean retornaSelecao(){
        return selecao;
    }
    
    public static void controlaAcao(int tmpAcao){
        acao = tmpAcao;
    }
    
    public static int retornaAcao(){
        return acao;
    } 
}        

