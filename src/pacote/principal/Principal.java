package pacote.principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.text.BadLocationException;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.basics.Settings;
import org.sikuli.natives.Vision;
import org.sikuli.basics.Debug;


@SuppressWarnings("unused")
public class Principal {
	
	private static App emulator = new App("Droid4X");
	private static Region janela;
	private static int questMODE = 0;
	private static int selectedTIME = 1;
	private static int selectedDifficulty = 2;
	private static InterfaceUI i = new InterfaceUI();
	public static boolean[] booster = new boolean[5];
	static Calendar calendar = Calendar.getInstance();
	
	//TelegramBot
	static CommandBot CommandBot;
	
	//logger
	private static FilelogHandler flh = new FilelogHandler();
	public static Logger logger;
	private static FileHandler fh;
	
	
	public static void main(String[] args) throws FindFailed, InterruptedException, IOException {
		
		//logger
		logger = Logger.getLogger("MyLog");
		flh.inicializaLog(logger, fh);
		
		//sikuliX
		ImagePath.add(Principal.class.getCanonicalName() + "/resources");
		Vision.setParameter("MinTargetSize", 18);
		Debug.setDebugLevel(3);
		
		//interface...
		try {
			getInterface().montaTela();
		} catch (ParseException | BadLocationException e) {}		
	}
	
	public static int getQuestMODE() {
		return questMODE;
	}

	public static void setQuestMODE(int questMODE) {
		Principal.questMODE = questMODE;
	}
	
	public static void setSelectedTIME(int selectedTIME) {
		Principal.selectedTIME = selectedTIME;
	}
	
	public static int getSelectedTIME() {
		return selectedTIME;
	}

	public static Region getJanela() {
		return janela;
	}


	public static void setJanela(Region janela) {
		Principal.janela = janela;
	}


	public static InterfaceUI getInterface() {
		return i;
	}


	public static void setInterface(InterfaceUI i) {
		Principal.i = i;
	}


	public static App getEmulator() {
		return emulator;
	}


	public static void setEmulator(App emulator) {
		Principal.emulator = emulator;
	}

	public static int getSelectedDifficulty() {
		return selectedDifficulty;
	}

	public static void setSelectedDifficulty(int selectedDifficulty) {
		Principal.selectedDifficulty = selectedDifficulty;
	}
	
	public static String print() throws IOException{
		return janela.getLastScreenImageFile();
	}

}
