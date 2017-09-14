
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.*;
public class FornecedoresView extends JInternalFrame implements ActionListener{
    
    public static Container cntFornecedor;
    public static JToolBar tbrFornecedor;
    public static JLabel lblFornedor[];
    public static JTextField txtFornecedor[];
    public static ImageIcon icnNovo, icnConsulta, icnEditar, icnSalvar, icnExcluir, icnRelatorio, icnFechar;
    public static BotoesAcao btaNovo, btaConsulta, btaEditar, btaSalvar, btaExcluir, btaRelatorio, btaFechar;
    public static JMenuBar mbrFornecedor;
    public static JMenu mnuArquivo, mnuConsultas, mnuAjuda;
    public static JMenuItem mniNovo, mniEditar, mniSalvar, mniFechar, mniConsCod, mniConsNome, mniSobre;
    public static JLabel lblBusca;//texto escrito "Busca"
    public static JTextField txtBusca;//caixa de texto da busca
    public static JButton btnBusca;//botão de "Buscar"
    public static ImageIcon imgFoto;
    public static JLabel lblFoto;
    public ImageIcon imgBusca;//imagem dentro do botão de busca
    public static JScrollPane scrFornecedores;
    public static JTable tblFornecedores;
    public static DefaultTableModel mdlFornecedores;
    public String strCamposF[]={ "cnpj","nomeF","nomeR", "cepF", "enderecoF","cidade","municipio","estado","pais","telefoneF"};
   
    public  FornecedoresView(){
        super("Modulo Fornecedor");
        cntFornecedor = new Container();
        cntFornecedor.setLayout(null);
        this.add(cntFornecedor);
        //barra de atalho 
        tbrFornecedor = new JToolBar();
        tbrFornecedor.setBounds(150,10,327,40);
        cntFornecedor.add(tbrFornecedor);
        icnNovo = new ImageIcon("img/icnNovo.png");
        btaNovo = new BotoesAcao(0,icnNovo, "Adicionar cliente");
        tbrFornecedor.add(btaNovo);
        icnConsulta = new ImageIcon("img/icnConsulta.png");
        btaConsulta = new BotoesAcao(1,icnConsulta, "Consultar cliente");
        tbrFornecedor.add(btaConsulta);
        icnEditar = new ImageIcon("img/icnEditar.png");
        btaEditar = new BotoesAcao(2,icnEditar, "Editar cliente");
        tbrFornecedor.add(btaEditar);
        icnSalvar = new ImageIcon("img/icnSalvar.png");
        btaSalvar = new BotoesAcao(3,icnSalvar, "Salvar no banco");
        tbrFornecedor.add(btaSalvar);
        icnExcluir = new ImageIcon("img/icnExcluir.png");
        btaExcluir = new BotoesAcao(4,icnExcluir, "Excluir cliente");
        tbrFornecedor.add(btaExcluir);
        icnRelatorio = new ImageIcon("img/icnRelatorio.png");
        btaRelatorio = new BotoesAcao(5,icnRelatorio, "Imprimir relatório");
        tbrFornecedor.add(btaRelatorio);
        icnFechar = new ImageIcon("img/icnFechar.png");
        btaFechar = new BotoesAcao(6,icnFechar, "Fechar cliente");
        tbrFornecedor.add(btaFechar);
        
        //barra de menu
         mbrFornecedor = new JMenuBar();
        this.setJMenuBar(mbrFornecedor);
        mnuArquivo = new JMenu("Arquivo");
        mnuArquivo.setMnemonic('a');
        mbrFornecedor.add(mnuArquivo);
        mnuConsultas = new JMenu("Consultas");
        mnuConsultas.setMnemonic('c');
        mbrFornecedor.add(mnuConsultas);
        mnuAjuda = new JMenu("Ajuda");
        mnuAjuda.setMnemonic('j');
        mbrFornecedor.add(mnuAjuda);
        //construindo itens do menu arquivo
        mniNovo = new JMenuItem("Novo Fornecedor");
        mnuArquivo.add(mniNovo);
        mniNovo.addActionListener(this);
        mniEditar = new JMenuItem("Editar dados");
        mnuArquivo.add(mniEditar);
        mniEditar.addActionListener(this);
        mniSalvar = new JMenuItem("Salvar alterações");
        mnuArquivo.add(mniSalvar);
        mniSalvar.addActionListener(this);
        mnuArquivo.add(new JSeparator());
        mniFechar = new JMenuItem("Fechar módulo de Fornecedores");
        mnuArquivo.add(mniFechar);
        mniFechar.addActionListener(this);
        mniConsCod = new JMenuItem("Por CNPJ");
        mniConsCod.addActionListener(this);
        mnuConsultas.add(mniConsCod);
        mniConsNome = new JMenuItem("Por nomeF");
        mniConsNome.addActionListener(this);
        mnuConsultas.add(mniConsNome);
        mniSobre = new JMenuItem("Sobre");
        mniSobre.addActionListener(this);
        mnuAjuda.add(mniSobre);

        //adc label e txt
        lblFornedor = new JLabel[strCamposF.length];
        txtFornecedor = new JTextField[strCamposF.length];
        lblBusca = new JLabel ("Busca Rápida");
        txtBusca = new JTextField();
        imgBusca = new ImageIcon("img/icnConsulta.png");
        
        //busca
        btnBusca = new JButton("Buscar", imgBusca);
        btnBusca.addActionListener(this);
        cntFornecedor.add(lblBusca); 
        lblBusca.setBounds(1000,35,100,20);
        cntFornecedor.add(txtBusca);
        txtBusca.setBounds(1085,35,160,20);
        cntFornecedor.add(btnBusca);
        btnBusca.setBounds(1250,35,150,20);
       // scroll
       tblFornecedores = new JTable();
        scrFornecedores = new JScrollPane(tblFornecedores);
        mdlFornecedores = (DefaultTableModel) tblFornecedores.getModel();
        String strDados[]={"CNPJ","NomeF","Cidade","Telefone"};
        for(int i=0; i<strDados.length; i++){
            mdlFornecedores.addColumn(strDados[i]);
        }
        cntFornecedor.add(scrFornecedores);
        scrFornecedores.setBounds(1000,60,400,280);
        for(int i= 0; i<strCamposF.length; i++){
            lblFornedor[i] = new JLabel(strCamposF[i]);
            txtFornecedor[i] = new JTextField();
            lblFornedor[i].setBounds(30,35+(i+1)*25,150,20);
            txtFornecedor[i].setBounds(150,35+(i+1)*25,350,20);
            cntFornecedor.add(lblFornedor[i]);
            cntFornecedor.add(txtFornecedor[i]);
        }//fechando for
        //imagem de usuario
        imgFoto = new ImageIcon("img/users.png");
        lblFoto = new JLabel(imgFoto);
        lblFoto.setBounds(670,50,128,128);
        cntFornecedor.add(lblFoto);
               this.addInternalFrameListener( new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent evt){
            SistemaView.btnModulos[2].setEnabled(true);}
            }//fechando InternalFrameAdapter
            );//fechando addInternalFramaListener
        
        this.setSize((int)SistemaView.dskJanelas.getWidth(),
                     (int)SistemaView.dskJanelas.getHeight());
        this.setIconifiable(true);
        this.setResizable(false);
        this.setClosable(true);
        this.show();    
    }
    
     
    public void actionPerformed(ActionEvent e) {
   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource() == mniSalvar){
           executaCadastro();
        }
    }
    
    public static void executaCadastro(){
        FornecedoresVO tmpFor = new FornecedoresVO();
      /*TMPFUNC deve receber os dados que o usuário digitar
        nas caixas de texto para que sejam cadastrados  */  
     
      //pegando texto da caixa e guardandono objeto TmpFunc
        preencheObjeto(tmpFor);
        try{
                FornecedoresDAO.cadastraFornecedores(tmpFor);
                JOptionPane.showMessageDialog(null, "Funcionário Cadastrado.");
                
        }catch(Exception erro){
            JOptionPane.showMessageDialog(null,erro.getMessage());
        }
    }

    public static void preencheObjeto(FornecedoresVO tmpFor){
      tmpFor.setCnpj(txtFornecedor[0].getText());
      tmpFor.setNomeF(txtFornecedor[1].getText());
      tmpFor.setNomeR(txtFornecedor[2].getText());
      tmpFor.setCepF(txtFornecedor[3].getText());
      tmpFor.setEnderecoF(txtFornecedor[4].getText());
      tmpFor.setCidade(txtFornecedor[5].getText());
      tmpFor.setMunicipio(txtFornecedor[6].getText());
      tmpFor.setEstado(txtFornecedor[7].getText());
      tmpFor.setPais(txtFornecedor[8].getText());
      tmpFor.setTelefoneF(txtFornecedor[9].getText());
    }



}
