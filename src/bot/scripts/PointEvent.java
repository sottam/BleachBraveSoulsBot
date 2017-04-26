package bot.scripts;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;

import pacote.principal.BotLaunch;
import pacote.principal.Principal;

public class PointEvent extends Quests {

	public void pointEventsQuests(Region janela) throws InterruptedException {

		try {

			inicio(janela);

		} catch (FindFailed e) {}
	}

	private void meio(Region janela) throws FindFailed, InterruptedException {
		Principal.getInterface().atualizaConsole("Aguardando fim da fase...");
		while (bool == false) {
			
			if (janela.exists("Close.PNG") != null) {
				janela.wait("Close.PNG");
				janela.click();
				janela.click();
				janela.click();
				janela.click();
				janela.click();
				
			}
			
			if (janela.exists("TapScreen.PNG") != null) {
				janela.wait("TapScreen.PNG");
				janela.click();
				break;
			}
			else
				BotLaunch.sleep(10 * 1000);
		}
	}

	@Override
	public void fim(Region janela) throws FindFailed, InterruptedException {

		meio(janela);

		Principal.getInterface().atualizaConsole("Recebendo premios...");

		Principal.getEmulator().focus();
		janela = App.focusedWindow();

		while (exitSuc == false) {

			if (janela.exists("Close.PNG", 2000) != null) {
				janela.wait("Close.PNG");
				janela.click();
				janela.waitVanish("Close.PNG");
				janela.click();
				janela.click();
				janela.click();
				janela.click();
				janela.click();
			}

			Principal.getInterface().atualizaConsole("Esperando Retry");
			if (janela.exists("Retry.PNG", 2000) != null) {
				janela.wait("Retry.PNG");
				janela.click();
				janela.waitVanish("Retry.PNG");
				exitSuc = true;
			}
		}
		exitSuc = false;
		Principal.getInterface().atualizaConsole("Recomecando...");
		BotLaunch.sleep(7 * 1000);
		inicio(janela);

	}

}
