package bot.scripts;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;

import pacote.principal.BotLaunch;
import pacote.principal.DataHandler;
import pacote.principal.Principal;

public abstract class Quests {
	protected boolean bool = false;
	protected boolean exitSuc = false;

	protected void inicio(Region janela) throws FindFailed, InterruptedException {
		
		Principal.getEmulator().focus();
		janela = App.focusedWindow();

		//janela.highlight();		
		
		Principal.getInterface().atualizaConsole("Esperando por Prepare for Quest");
		janela.wait("PrepareForQuest.PNG", 5000);
		
		janela.click();
		
		Principal.getInterface().atualizaConsole("Verificando Tickets...");
		while (TicketTest.playMatch(janela) == false) {

		}
		
		Menus.selectTime(janela);
		
		Menus.selectBosters(janela);
		
		Principal.getInterface().atualizaConsole("procurando Select");
		janela.wait("Select.PNG");
		janela.click();
		janela.wait("Cancel.PNG");

		Principal.getInterface().atualizaConsole("Procurando GO!");
		janela.click("Go.PNG");
		
		Principal.getInterface().atualizaInfo(DataHandler.dataAtual(), 0);
		
		Principal.getInterface().atualizaConsole("Esperando inicio da fase...");
		BotLaunch.sleep(10 * 1000);
		janela.wait("PauseButton.PNG",4000);

		fim(janela);
		
		
	}

	protected void fim(Region janela) throws FindFailed, InterruptedException {}

}
