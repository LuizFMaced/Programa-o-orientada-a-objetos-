//pacotes de elementos gráficos
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SistemaView extends JFrame implements ActionListener {
    //declaração dos objetos:
    public static Container ctnPrincipal, ctnTopo, ctnMenu;
    public static ImageIcon imgBanner;//classe de imagens
    public static JLabel lblBanner;//JLabel - Rotulo fixo recebe texto ou imagem
    public static JDesktopPane dskJanelas;/*uma classe onde se abre janelas 
    dentro de containers*/
    //Criando para o menu vetores de String, Imagens e Botões:
    /*public static String strModulos[]= 
        {"CLIENTES","FUNCIONÁRIOS","FORNECEDORES","PRODUTOS","VENDAS",
        "USUÁRIOS","RELATÓRIOS","SAIR"};*/
    public static ImageIcon imgModulos[];
    public static JButton btnModulos[];
    public static ClientesView telaClientes;
    public static FuncionariosView telaFuncionarios;
    public static FornecedoresView telaFornecedores;
    
    //método construtor
    public SistemaView (){
        super("Sistema de Gerenciamento de Vendas - ERP");
        //construção e configuração dos objetos na tela:
        //layout null - é para posicionar livremente atravéz do método setBounds
        //border layout - ele divide o seu container em regiões norfh^, east>, west<, south\/ e center 
        //flow layout - permite colocar os objetos lado a lado, você não escolhe onde ele fica
        //grid layaut - é o layout que define com linhas e colunas exatamente onde você quiser
        ctnPrincipal = new Container();
        ctnPrincipal.setLayout(new BorderLayout());
        ctnTopo = new Container();
        ctnTopo.setLayout(new GridLayout(2,1));
        ctnMenu = new Container();
        ctnMenu.setLayout(new GridLayout(2,4));
        
        //definindo o Banner:
        imgBanner = new ImageIcon("img/banner.png");//caminho da imagem
        lblBanner = new JLabel(imgBanner);
        
        //definindo dimensões dos vetores:
        imgModulos = new ImageIcon[8];
        btnModulos = new JButton[8];
        for(int i=0; i<btnModulos.length; i++){
            imgModulos[i] = new ImageIcon("img/"+i+".jpg");
            btnModulos[i] = new JButton(imgModulos[i]);
            btnModulos[i].addActionListener(this);
            btnModulos[i].setBackground(Color.WHITE);// deixar o fundo do botão branco
        }
        
        //adicionando janelas dentro do container principal
        dskJanelas = new JDesktopPane();
        ctnPrincipal.add(dskJanelas,BorderLayout.CENTER);
        
        //adicionando Containers e itens na tela:
        this.add(ctnPrincipal);//add container principal na janela
        ctnPrincipal.add(ctnTopo,BorderLayout.NORTH);/*add container topo no 
        norte do principal*/
        ctnTopo.add(lblBanner);//add Banner na primeira linha do container topo
        ctnTopo.add(ctnMenu);/*add container menu na segunda linha 
        do container topo*/
        //adicioando botoões dentro dos módulos dos containers:
        for(int i=0; i<btnModulos.length; i++){
            ctnMenu.add(btnModulos[i]);
        }
        
        this.setSize(1366,768);
        this.getContentPane().setBackground(Color.WHITE);
        this.setVisible(true);
        //encerrar o processo quando fechar a janela (f6) 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//fechando construtor
    
    //funções:
    public void actionPerformed(ActionEvent evt){
        if (evt.getSource()==btnModulos[0]){
            telaClientes = new ClientesView();
            dskJanelas.add(telaClientes);
            btnModulos[0].setEnabled(false);
          
        }//fechando 
        else if(evt.getSource() == btnModulos[1]){
            telaFuncionarios = new FuncionariosView();
            dskJanelas.add(telaFuncionarios);
            btnModulos[1].setEnabled(false);
        }
        else if(evt.getSource() == btnModulos[2]){
            telaFornecedores = new FornecedoresView();
            dskJanelas.add(telaFornecedores);
            btnModulos[2].setEnabled(false);
        }
        else if (evt.getSource()==btnModulos[7]){
          System.exit(0);
        }
    }//fechando actionPerformed   
    
    //deixar a tela fullscreen
    public static Dimension montarTela(){
        Toolkit info = Toolkit.getDefaultToolkit();
        Dimension resolucao = info.getScreenSize();
        return resolucao;
    }//fechando Dimension montarTela
    
    
}
