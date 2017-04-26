package pacote.principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;



public class InterfaceUI {
	private JFrame janela;
	private JPanel painelPrincipal;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension d = tk.getScreenSize();

	//threads
	private static BotLaunch bot;
	private CommandBot cb;
	
	// console
	private JTextArea console;
	private JTextArea info; 
	private String display;

	// caixinhas
	static JRadioButton nq;
	JRadioButton le;
	JRadioButton lp;
	JRadioButton t1, t2, t3, t4, t5;
	JCheckBox b1, b2, b3, b4;

	// Botoes
	JButton Start;
	JButton Stop;

	//telegram
	private static MyFileHandler mfh = new MyFileHandler();
	
	
	private ButtonGroup grupoModo, grupoTime;

	public void montaTela() throws ParseException, BadLocationException {
		preparaJanela();
		preparaPainelPrincipal();
		preparaBotoes();
		mostraJanela();
	}

	private void preparaJanela() {
		janela = new JFrame("BleachBot");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(null);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}

		janela.add(painelPrincipal);

	}

	private void preparaBotoes() {

		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(Principal.class.getResource("/resources/BleachBBLogo.PNG")));
		label.setBounds(0, 0, 400, 200);

		t1 = new JRadioButton("Team 1");
		t1.setBounds(120, 251, 70, 23);
		t1.doClick();

		t2 = new JRadioButton("Team 2");
		t2.setBounds(120, 277, 70, 23);

		t3 = new JRadioButton("Team 3");
		t3.setBounds(120, 303, 70, 23);
		
		t4 = new JRadioButton("Team 4");
		t4.setBounds(120, 329, 70, 23);
		
		t5 = new JRadioButton("Team 5");
		t5.setBounds(120, 355, 70, 23);

		b1 = new JCheckBox("Stamina");
		b1.setBounds(192, 251, 70, 23);

		b2 = new JCheckBox("Attack");
		b2.setBounds(192, 277, 70, 23);

		b3 = new JCheckBox("Defense");
		b3.setBounds(192, 303, 70, 23);

		b4 = new JCheckBox("Focus");
		b4.setBounds(192, 329, 70, 23);

		JLabel lblTeams = new JLabel("Teams:");
		lblTeams.setBounds(124, 230, 46, 14);

		JLabel lblBoosters = new JLabel("Boosters:");
		lblBoosters.setBounds(192, 230, 46, 14);

		JLabel label_1 = new JLabel("Modes:");
		label_1.setBounds(10, 230, 46, 14);

		nq = new JRadioButton("Normal");
		nq.setMnemonic('N');
		nq.setBounds(6, 251, 109, 23);
		nq.doClick();

		le = new JRadioButton("Lottery");
		le.setMnemonic('L');
		le.setBounds(6, 277, 109, 23);

		lp = new JRadioButton("Lottery & Points");
		lp.setMnemonic('P');
		lp.setBounds(6, 303, 109, 23);

		grupoModo = new ButtonGroup();
		grupoModo.add(nq);
		grupoModo.add(le);
		grupoModo.add(lp);

		grupoTime = new ButtonGroup();
		grupoTime.add(t1);
		grupoTime.add(t2);
		grupoTime.add(t3);
		grupoTime.add(t4);
		grupoTime.add(t5);

		RadioButtonHandler handler = new RadioButtonHandler();
		nq.addItemListener(handler);
		le.addItemListener(handler);
		lp.addItemListener(handler);

		t1.addItemListener(handler);
		t2.addItemListener(handler);
		t3.addItemListener(handler);
		t4.addItemListener(handler);
		t5.addItemListener(handler);

		b1.addItemListener(handler);
		b2.addItemListener(handler);
		b3.addItemListener(handler);
		b4.addItemListener(handler);

		Start = new JButton("Start");
		Start.setBounds(284, 251, 89, 23);
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bot = new BotLaunch("Bot");
				if (mfh.obterKey("token.txt") != null){
					cb = new CommandBot("commandbot", mfh.obterKey("token.txt"));
					cb.start();
				}
				bot.start();

				Start.setEnabled(false);
				Stop.setEnabled(true);
				nq.setEnabled(false);
				le.setEnabled(false);
				lp.setEnabled(false);

			}
		});

		Stop = new JButton("Stop");
		Stop.setBounds(284, 303, 89, 23);
		Stop.setEnabled(false);
		Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bot.shutdownBaby();
				if (mfh.obterKey("token.txt") != null){
					cb.shutdownBaby();
				}
				Start.setEnabled(true);
				Stop.setEnabled(false);
				nq.setEnabled(true);
				le.setEnabled(true);
				lp.setEnabled(true);
			}
		});

		console = new JTextArea(display);
		console.setLocation(0, 200);
		console.setSize(400, 20);
		console.setText("Bem Vindo ao BleachBB BOT.");
		console.setEditable(false);
		console.setBackground(Color.BLACK);
		console.setForeground(Color.GREEN);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 385, 394, 2);
		
		info = new JTextArea();
		info.setFont(new Font("Monospaced", Font.ITALIC, 13));
		info.setEditable(false);
		info.setText("Inicializado: " + DataHandler.dataAtual());
		info.setBackground(SystemColor.menu);
		info.setForeground(Color.GRAY);
		info.setBounds(0, 385, 400, 20);

		painelPrincipal.add(console);
		painelPrincipal.add(label);

		painelPrincipal.add(label_1);
		painelPrincipal.add(nq);
		painelPrincipal.add(le);
		painelPrincipal.add(lp);

		painelPrincipal.add(lblTeams);
		painelPrincipal.add(t1);
		painelPrincipal.add(t2);
		painelPrincipal.add(t3);
		painelPrincipal.add(t4);
		painelPrincipal.add(t5);
		
		painelPrincipal.add(lblBoosters);
		painelPrincipal.add(b1);
		painelPrincipal.add(b2);
		painelPrincipal.add(b3);
		painelPrincipal.add(b4);

		painelPrincipal.add(Start);
		painelPrincipal.add(Stop);
		
		painelPrincipal.add(separator);
		painelPrincipal.add(info);
	}

	private void mostraJanela() {

		janela.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/resources/FrameIcon32.PNG")));
		janela.pack();
		janela.setSize(390, 440);
		janela.setResizable(false);
		janela.setLocation(50, (d.height / 2 - 250));
		janela.setVisible(true);

	}

	public void atualizaConsole(String s) {
		console.setText(s);
		Principal.logger.info(s);
	}
	
	public void atualizaInfo(String s, int tipo){
		switch(tipo) {
			case 0:
				info.setText("Last Run: " + s);
				Principal.logger.info("Last Run: " + s);
				break;
			default:
				info.setText("Info: " + s);
				Principal.logger.info("Info: " + s);
		}
		
	}

	@SuppressWarnings("unused")
	private JLabel createImageIcon(String path) {
		try {
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			JLabel logoImage = new JLabel(new ImageIcon(image));
			return logoImage;
		} catch (IOException e) {
			System.out.printf("image not found: %s", path);
		}
		return new JLabel("IMAGEM NAO ENCONTRADA");
	}
	
	public static void failSafeRestart(){
		bot.shutdownBaby();
		nq.doClick();
		bot.start();
	}
	
	
}
