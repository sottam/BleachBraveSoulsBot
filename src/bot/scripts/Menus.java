package bot.scripts;

import org.sikuli.script.App;
import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Region;

import pacote.principal.InterfaceUI;
import pacote.principal.Principal;

public class Menus {
	
	private static int timeOLD = 0;
	
	public static void failSafe(Region janela) throws FindFailed {
		fechaJogo(janela);
		entraJogo(janela);
		goEventPage(janela);
		selectEvent(janela);
		selectDifficulty(janela);
		InterfaceUI.failSafeRestart();
	}

	public static void entraJogo(Region janela) throws FindFailed {

		Principal.getEmulator().focus();
		janela = App.focusedWindow();

		janela.wait("LauncherIco.PNG", 4000);
		janela.click();

		janela.wait("TitleMenu.PNG", 4000);
		janela.click();
		
		if(janela.exists("OnGoingQuest.PNG",10000) != null){
			janela.click("Yes.PNG");
		}
		else {
			janela.wait("CloseNewsPage.PNG", 4000);
			janela.click();
		}
		// TODO implementar receber premio diario

	}

	public static void recebePremioDiario(Region janela) throws FindFailed {
		// TODO implementar...

	}

	public static void fechaJogo(Region janela) throws FindFailed {

		Principal.getEmulator().focus();
		janela = App.focusedWindow();
		
		//se o icone ja estiver na tela, nao faz nada
		if(janela.exists("LauncherIco.PNG") != null){	}
		else {
			janela.wait("AlternarTarefasDroid4X.PNG", 4000);
			janela.click();
			
			janela.wait("LauncherIcoHalf.PNG", 4000);
			janela.hover();
			
			janela.mouseDown(Button.LEFT);
			janela.wait("RemoverDaListaDroid4X.PNG", 4000);
			janela.mouseUp(Button.LEFT);
			janela.click();
		}
		
			

	}

	public static void goEventPage(Region janela) throws FindFailed {

		Principal.getEmulator().focus();
		janela = App.focusedWindow();

		janela.wait("StoryButton.PNG", 4000);
		janela.click();

		janela.wait("EventButton.PNG", 4000);
		janela.click();
	}
	
	public static void selectEvent(Region janela) throws FindFailed {
		Principal.getEmulator().focus();
		janela = App.focusedWindow();
		
		org.sikuli.basics.Settings.MinSimilarity = 1.00;
		//look for it
		Principal.getInterface().atualizaConsole("Procurando evento...");
		boolean bool = false;
		int cont = 0;
		while(!bool) {
			if(janela.exists("WeeklyTechnique.PNG") != null){
				janela.wait("WeeklyTechnique.PNG",2000);
				janela.click();
				bool = true;
			}
			else {
				rolaEventPage(janela);
				cont++;
			}
			//seleciona qualquer weekly disponivel, caso n encontre o especificado
			if(cont > 7)
				org.sikuli.basics.Settings.MinSimilarity = 0.7;
				
		}
		
		Mouse.move(new Location(janela.getX() + 360 , janela.getY() + 330));
		janela.click();
		
		org.sikuli.basics.Settings.MinSimilarity = 0.7;
	}
	
	public static void selectDifficulty(Region janela) throws FindFailed{
		Principal.getEmulator().focus();
		janela = App.focusedWindow();
		
		org.sikuli.basics.Settings.MinSimilarity = 1.00;
		switch(Principal.getSelectedDifficulty()){
			case 0:
				janela.wait("DNormal.PNG",2000);
				janela.click();
				break;
			case 1:
				janela.wait("DHard.PNG",2000);
				janela.click();
				break;
			case 2:
				janela.wait("DVeryHard.PNG",2000);
				janela.click();
				break;
		}
		org.sikuli.basics.Settings.MinSimilarity = 0.7;
	}

	public static void startEvent(Region janela) throws FindFailed{
		//InterfaceUI.bot.shutdownBaby();
		//Principal.setQuestMODE(0);
		//InterfaceUI.bot.start();
		InterfaceUI.failSafeRestart();
	}

	public static void selectBosters(Region janela) throws FindFailed {

		Principal.getEmulator().focus();
		janela = App.focusedWindow();

		if (Principal.booster[1]) {
			janela.wait("StaBonus.PNG");
			janela.click();
		}
		if (Principal.booster[2]) {
			janela.wait("AtkBonus.PNG");
			janela.click();
		}
		if (Principal.booster[3]) {
			janela.wait("DefBonus.PNG");
			janela.click();
		}
		if (Principal.booster[4]) {
			janela.wait("FcsBonus.PNG");
			janela.click();
		}

	}
	
	public static void selectTime(Region janela) throws FindFailed {

		Principal.getEmulator().focus();
		janela = App.focusedWindow();
		int y = janela.getY() + 140;
		
		if(Principal.getSelectedTIME() != timeOLD) {
			if (Principal.getSelectedTIME() == 1) 
				janela.click(new Location(janela.getX() + 200, y));
	
			if (Principal.getSelectedTIME() == 2)
				janela.click(new Location(janela.getX() + 350, y));
	
			if (Principal.getSelectedTIME() == 3)
				janela.click(new Location(janela.getX() + 500, y));
			
			if (Principal.getSelectedTIME() == 4)
				janela.click(new Location(janela.getX() + 650, y));
			
			if (Principal.getSelectedTIME() == 5)
				janela.click(new Location(janela.getX() + 800, y));
			
			timeOLD = Principal.getSelectedTIME();
		}

	}

	@Deprecated
	public static void selectTimeOLD(Region janela) throws FindFailed {

		Principal.getEmulator().focus();
		janela = App.focusedWindow();
		
		if(Principal.getSelectedTIME() != timeOLD) {

			RegionHandler.setTemp(janela);

			janela = RegionHandler.createTempRegion(135, 110, 250, 70, janela);
	
			if (Principal.getSelectedTIME() == 1 && janela.exists("Team1Off.PNG") != null) {
				System.out.println("entrou 1");
				// janela.wait("Team1Off.PNG");
				janela.click();
	
			}
	
			janela = RegionHandler.createTempRegion(385, 110, 250, 70, janela);
	
			if (Principal.getSelectedTIME() == 2 && janela.exists("Team2Off.PNG") != null) {
				System.out.println("entrou 2");
				//janela.wait("Team2Off.PNG");
				janela.click();
			}
	
			janela = RegionHandler.createTempRegion(630, 110, 250, 70, janela);
	
			if (Principal.getSelectedTIME() == 3 && janela.exists("Team3Off.PNG") != null) {
				System.out.println("entrou 3");
				//janela.wait("Team3Off.PNG");
				janela.click();
	
			}
			
			if (Principal.getSelectedTIME() == 4 && janela.exists("Team4Off.PNG") != null) {
				System.out.println("entrou 4");
				//janela.wait("Team3Off.PNG");
				janela.click();
	
			}
			
			if (Principal.getSelectedTIME() == 5 && janela.exists("Team5Off.PNG") != null) {
				System.out.println("entrou 5");
				//janela.wait("Team3Off.PNG");
				janela.click();
	
			}
			timeOLD = Principal.getSelectedTIME();
			RegionHandler.restoreRegion(janela);
		}

	}

	public static void ConnectionError(Region janela) throws FindFailed {
		janela.wait("ConnectionErrorDialog.PNG",5000);
		janela.click("RetryRed.PNG");
		
	}
	
	public static boolean checkConnectionErrorDialog(Region janela){
		if(janela.exists("ConnectionErrorDialog.PNG") != null)
			return true;
		else
			return false;
		
	}
	
	private static void rolaEventPage(Region janela) {
		Mouse.move(new Location(janela.getX() + 360 , janela.getY() + 490));
		Mouse.down(Mouse.LEFT);
		Mouse.move(new Location(janela.getX() + 360 , janela.getY()));
		Mouse.up(Mouse.LEFT);
	}
}
