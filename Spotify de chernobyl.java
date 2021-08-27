//import Classes.MusicasDisponiveis;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.concurrent.ThreadLocalRandom;

public class tocador {
    public static void main(String[] args){
        Musica[] Disponiveis = new Musica[13];
		Disponiveis[0] = new Musica("Esse cara sou eu versao ano novo 2011" , "Roberto Carlos",157);
        Disponiveis[1] = new Musica("Debaixo Dos Caracóis Dos Seus Cabelos.", "Roberto Carlos",112);
        Disponiveis[2] = new Musica("O Portão" , "Roberto Carlos",190);
		Disponiveis[3] = new Musica("Musica","Cantor",67);
		Disponiveis[4] = new Musica("Mais uma Musica","Mais um Cantor",215);
		Disponiveis[5] = new Musica("Outra Musica","Aquele Cara",325);
		Disponiveis[6] = new Musica("Musica Nova","Cantor Antigo",25);
		Disponiveis[7] = new Musica("Musica Velha","Cantora",255);
		Disponiveis[8] = new Musica("Mais uma outra Musica","Cantor q sabe cantar",99);
		Disponiveis[9] = new Musica("Musica 12","Cantora 32",34);
		Disponiveis[10] = new Musica("Musica Musica","Cantor q nao sabe cantar",900);
		Disponiveis[11] = new Musica("Musica Musica Musica","Cantora&Cantor",650);
		Disponiveis[12] = new Musica("Aquela que eu nao lembro o nome","Daquele Cara",430);
		new GUIAula(Disponiveis);
    }
}


public class Musica {
    private String Mus;
    private String Cantor;
	public int Duracao;

    public Musica(String A, String B,int C){

    //this.mus/this.cantor = referência a um objeto string A MESMA COISA PRO B 
        this.Mus = A;
        this.Cantor = B;
		this.Duracao = C;//EU duracao em segundos
    }

    //pegar atributos da classe
    public String getMus() {
        return Mus;
    }

    public String getCantor() {
        return Cantor;
    }


	public Musica getMus(int selectedMusicInd) {
        return null;
    }
}


/*public class musicasDisponiveis {
    public Musica[] musicas = new Musica[4];

   public musicasDisponiveis() {
       
        
        musicas[0] = new Musica("Esse cara sou eu versao ano novo 2011" + 0, "Roberto Carlos");
        musicas[1] = new Musica("Debaixo Dos Caracóis Dos Seus Cabelos." + 1, "Roberto Carlos");
        musicas[2] = new Musica("O Portão" + 2, "Roberto Carlos");
       
        }
   
   public Musica getMus(int indice){
    return this.musicas[indice];
   }
   public Musica getMusic(int indice){
       return this.musicas[indice].getMus(indice);
   }
}*/

public class playList {
    private final ArrayList<Musica>playlist;
   
    //construtor
    public playList(){ 
        this.playlist= new ArrayList<>();
    }

    //adicionar música da playlist
    public void addMusic(Musica musica){ 
        playlist.add(musica);
        System.out.println(musica.getMus()+" Adicionada com sucesso!");
        
    }
    
    public int getTamanho(){
        return this.playlist.size();
    }

    public Musica getMus(int indice){
        return this.playlist.get(indice);
    }
    //remover música  da playlist
    public void removeMusic (int i){ 
        if(playlist.isEmpty()){
            System.out.println("A playList está vazia");
        }

        // index passado pra remoção é válido??
        else if(i<0||i>playlist.size()-1){ 
            System.out.println("Música não encontrada!");
        }else{
            System.out.println(playlist.get(i).getMus()+" removida com sucesso!");
            playlist.remove(i);
        }

    }
    //printa a playList
    public String[] getPlaylistString() { 
        String[] AgoraEscolhidas = new String[playlist.size()];
        for (int i =0;i<playlist.size();i++){
            AgoraEscolhidas[i] = (playlist.get(i).getMus()+","+playlist.get(i).getCantor());
        }
        return AgoraEscolhidas;
    }}
   


public class adicionar extends lock implements Runnable {
    private final playList playlist ;
    private final Musica list ;
    private final JList Atualiza;

    public adicionar(playList playlist, Musica list, JList atualiza){   
        //playList e a lista de Música como parâmetro do construtor para playlist nao ficar travada..
        
        this.list = list;
        this.playlist=playlist; 
        this.Atualiza = atualiza;                            
    }
    public void run() {
        //faz o block desse objeto
        getAcessLock().lock(); 
        try {
            //espera a thrend
            while (isOcupied()) getCondition().await(); 
            setOcupied(true); //a ota thrend ta ocupada
            playlist.addMusic(list);    // FALTA A LISTA DE MUSICAS
            System.out.println("Uhuull");
            this.Atualiza.setListData(playlist.getPlaylistString());
            this.Atualiza.updateUI();
            setOcupied(false);
            getCondition().signalAll();//sinaliza que ta disponivel
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getAcessLock().unlock();
        }
    }
}


public class lock {
    private final Lock acessLock = new ReentrantLock();
    private final Condition condition = acessLock.newCondition();
    private boolean ocupado = false;

    public boolean isOcupied() {
        return ocupado;
    }

    public void setOcupied(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Condition getCondition() {
        return condition;
    }
    public Lock getAcessLock() {
        return acessLock;
    }
}

public class remover extends lock implements Runnable {
    private final playList playlist;
    private final int selectedMusicInd;
    private final JList Atualiza;

    public remover(int index, playList playlist, JList atualiza) {
        this.selectedMusicInd = index;               
        this.playlist = playlist;   
        this.Atualiza = atualiza;      
    }
    public void run() {
        getAcessLock().lock();
        try {
            while (isOcupied()) getCondition().await();
            setOcupied(true);
            playlist.removeMusic(selectedMusicInd);
            this.Atualiza.setListData(playlist.getPlaylistString());
            this.Atualiza.updateUI();
            setOcupied(false);
            getCondition().signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getAcessLock().unlock();
        }
    }
}

public class GUIAula implements ActionListener, ListSelectionListener{

	int totalAmount = 0;
	int handleDepositValue = 10;
	int tempo = 0;
	int dezenaMinuto = 0;
	int unidadeMinuto = 0;
	int dezenaSegundo = 0;
	int unidadeSegundo = 0;
	int totMin = 0;
	int totSeg = 0;
	int indiceDaMusica = 0;
	int estadoTocador = 0;
    int indicepraRemover = -1;
    int indicepraTocar = 0;
    playList alista = new playList();
    Thread Adicionando = new Thread();
    Thread Removendo = new Thread();
    String depositValues[] = {" "," "," "," "," "," "," "," "};
    Musica proxATocar ;
    int duracaoMusica = 0;

	//	Inicializa os componentes do JavaSwing
	private JButton depositButton;
	private JButton botaoPlayEPausa;
	private JButton botaoProxima;
	private JButton botaoAnterior;
	private JButton botaoAdicionarLista;
	private JButton botaoRemoverLista;
	private JLabel textAmount;
	private JLabel MusicaEscutando;
	private JLabel MusicaTocando;
	public JList depositValuesList;
	private JList MusicasDisponíveis;
	private JProgressBar depositProgressBar;
	private boolean alet;
	private Musica[] PralaPrabaixo;

	public GUIAula(Musica[] disponiveis) {

		alet = false;

		
		depositValuesList = new JList();
		depositValuesList.setSelectedIndex(0);
		depositValuesList.addListSelectionListener(this);
		depositValuesList.setBounds(365,210 , 260, 290);
        depositValuesList.setListData(depositValues);
        

		PralaPrabaixo = disponiveis;

		String[] listaMusDisp = new String[13];
		for(int k = 0;k < listaMusDisp.length; k ++){
			listaMusDisp[k] = disponiveis[k].getMus() + "," + disponiveis[k].getCantor();
		}
		MusicasDisponíveis = new JList(listaMusDisp);
		MusicasDisponíveis.setSelectedIndex(0);
		MusicasDisponíveis.addListSelectionListener(this);
		MusicasDisponíveis.setBounds(0,10 , 290, 470);
		
		botaoPlayEPausa = new JButton("||");
		botaoPlayEPausa.addActionListener(this);		
		botaoPlayEPausa.setActionCommand("play/pausa");		
		botaoPlayEPausa.setBounds(405, 160, 75, 40);
		botaoPlayEPausa.setForeground(Color.RED);
		
		
		
		botaoProxima = new JButton(">>");
		botaoProxima.addActionListener(this);
		botaoProxima.setActionCommand("ProximaMusica");
		botaoProxima.setBounds(515, 160, 75, 40);
		
		botaoAnterior = new JButton("<<");
		botaoAnterior.addActionListener(this);
		botaoAnterior.setActionCommand("MusicaAnterior");
		botaoAnterior.setBounds(295, 160, 75, 40);



		botaoAdicionarLista = new JButton("ADD");
		botaoAdicionarLista.addActionListener(this);
		botaoAdicionarLista.setActionCommand("AdicionarLista");
		botaoAdicionarLista.setBounds(305, 240, 50, 50);


		botaoRemoverLista = new JButton("REM");
		botaoRemoverLista.addActionListener(this);
		botaoRemoverLista.setActionCommand("RemoverLista");
		botaoRemoverLista.setBounds(305, 320, 50, 50);



		depositButton = new JButton("A");
		depositButton.addActionListener(this);
		depositButton.setActionCommand("aleatorio");
		depositButton.setBounds(305, 400, 50, 50);
		depositButton.setOpaque(false);
		depositButton.setBackground(Color.RED);
		
		

		depositProgressBar = new JProgressBar();
		depositProgressBar.setValue(0);
		depositProgressBar.setBounds(295, 10,300 , 70);
		depositProgressBar.setMaximum(60);


		// Inicializa um componente de texto com o valor "Amount: R$ 0".
		textAmount = new JLabel("00:00/00:00");
		textAmount.setBounds(295, 60, 130, 30);
		//EU  inicializa com isso
		MusicaEscutando = new JLabel("Você está escutando: ");
		MusicaEscutando.setBounds(295,90 , 180, 30);
		// Inicializa um componente de panel.
		MusicaTocando = new JLabel("------------");
		MusicaTocando.setBounds(295,120 , 280, 30);
		JPanel panel = new JPanel();
		// Cria margens em todas as direções.
		//panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		// Seta GridLayout em nosso panel. (Existe uma variedade de layout que podem ser utilizados)
		//panel.setLayout(new GridLayout(0,1));//EU depois tem que procurar um layout legal
		// Adiciona todos os nossos componentes ao painel.
		panel.setLayout(null);
		panel.add(depositProgressBar);
		panel.add(textAmount);
		panel.add(depositValuesList);
		panel.add(depositButton);
		panel.add(botaoAnterior);
		panel.add(botaoProxima);
		panel.add(botaoPlayEPausa);
		panel.add(MusicaTocando);
		panel.add(botaoAdicionarLista);
		panel.add(botaoRemoverLista);
		panel.add(MusicaEscutando);
		panel.add(MusicasDisponíveis);

		// Inicializa a janela da GUI.
		JFrame frame = new JFrame();
		frame.add(panel);
		// Seta o titulo da janela.
		frame.setTitle("Spotify");
		// Seta o tamanho da janela.
		frame.setSize(600, 500);
		frame.setVisible(true);

	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		

		// Caso a ação "deposit_act" seja detecada no clique de algum botão.
		if("play/pausa".equals(command)) {
			if(estadoTocador == 0){
				estadoTocador = 1;
				botaoPlayEPausa.setForeground(Color.BLACK);
				
                
				new Thread() {

					
					public void run() {
						while(estadoTocador == 1){
                            
                            proxATocar =  alista.getMus(indicepraTocar);
                            duracaoMusica = proxATocar.Duracao;
				            totMin = duracaoMusica/60;
				            totSeg = duracaoMusica%60;
				            dezenaMinuto = totMin/10;
				            unidadeMinuto = totMin%10;
				            dezenaSegundo = totSeg/10;
				            unidadeSegundo = totSeg%10;
				            depositProgressBar.setMaximum(duracaoMusica);
				            MusicaTocando.setText(proxATocar.getMus() + "," + proxATocar.getCantor());
                            
						    totalAmount += handleDepositValue;
            	            for (int i=tempo; i<= duracaoMusica; i++) {
						    	depositProgressBar.setValue(i);

						    	try {
						    		Thread.sleep(1000);
						    	} catch (InterruptedException e1) {
						    		// TODO Auto-generated catch block
						    		e1.printStackTrace();
						    	}
						    	textAmount.setText(((tempo/60)/10)+((tempo/60)%10)+":"+((tempo%60)/10)+((tempo%60)%10)+"/"+dezenaMinuto+unidadeMinuto+":"+dezenaSegundo+unidadeSegundo);
						    	tempo+=1;
						    	if((estadoTocador == 3) || (estadoTocador == 4)){
						    		break;
						    	}

						    }
                            if(estadoTocador == 1){
                                if(alet){
                                    indicepraTocar = ThreadLocalRandom.current().nextInt(0, alista.getTamanho());
                                }else{
                                    indicepraTocar = (indicepraTocar + 1)%alista.getTamanho();
                                }
                                tempo = 0;
                            }else if(estadoTocador == 3){
                                estadoTocador = 0;
                            }else{
                                estadoTocador = 1;
                                tempo = 0;
                            }
                        
                        }
						//textAmount.setText("Amount: R$ "+totalAmount);
						//if(estadoTocador == 1){	
						//	tempo = 0;
						//	depositProgressBar.setValue(0);
						//	estadoTocador = 0;
						//	textAmount.setText(((tempo/60)/10)+((tempo/60)%10)+":"+((tempo%60)/10)+((tempo%60)%10)+"/"+dezenaMinuto+unidadeMinuto+":"+dezenaSegundo+unidadeSegundo);
						//	MusicaTocando.setText("--------");
						//}else{
						//	estadoTocador = 0;
						//}

					}

				}.start();
			}else{
				estadoTocador = 3;
				botaoPlayEPausa.setForeground(Color.RED);
			}



		}else if("aleatorio".equals(command)){

			if(alet){
				alet = false;
				depositButton.setForeground(Color.BLACK);
				depositButton.setOpaque(false);

			}else{
				alet = true;
				depositButton.setForeground(Color.red);
				//depositButton.setOpaque(true);
			}
		}else if("RemoverLista".equals(command)){
            Removendo = new Thread(new remover(indicepraRemover,alista,depositValuesList));
            Removendo.start();
            
        }
		else if("AdicionarLista".equals(command)){
            Adicionando = new Thread(new adicionar(alista,PralaPrabaixo[indiceDaMusica],depositValuesList));
            Adicionando.start();
            
        }
		else if("MusicaAnterior".equals(command)){
			if(estadoTocador == 1){
                if(alet){
                    indicepraTocar = ThreadLocalRandom.current().nextInt(0, alista.getTamanho());
                }else{
                    
                    indicepraTocar = (indicepraTocar - 1)%alista.getTamanho();
                }
                estadoTocador = 4;
            }
		}
		else if("ProximaMusica".equals(command)){
			if(estadoTocador == 1){
                if(alet){
                    indicepraTocar = ThreadLocalRandom.current().nextInt(0, alista.getTamanho());
                }else{
                    
                    indicepraTocar = (indicepraTocar + 1)%alista.getTamanho();
                }
                estadoTocador = 4;
            }
		}


	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		/*
		 * Aqui criamos uma função, que é acionada todas as vezes que uma nova célula
		 * da lista é clicada. Quando isso acontece, o valor da célula é capturado,
		 * convertido e colocado em nossa variável handleDepositValue.
		 */
		indicepraRemover = depositValuesList.getSelectedIndex();


		indiceDaMusica = MusicasDisponíveis.getSelectedIndex();

	}

}