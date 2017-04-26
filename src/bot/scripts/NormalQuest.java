package bot.scripts;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;

import pacote.principal.BotLaunch;
import pacote.principal.Principal;

public class NormalQuest extends Quests {

	public void normalQuests(Region janela) throws InterruptedException {

		try {
			
			inicio(janela);

		} catch (FindFailed e) {}
	}

	@Override
	public void fim(Region janela) throws FindFailed, InterruptedException {
		Principal.getInterface().atualizaConsole("Aguardando fim da fase...");
		while (exitSuc == false) {
			
			if (janela.exists("Close.PNG") != null) {
				janela.wait("Close.PNG");
				janela.click();
				janela.click();
				janela.click();
				janela.click();
				janela.click();
			}

			if (janela.exists("Retry.PNG") != null) {
				janela.wait("Retry.PNG");
				janela.click();
				janela.waitVanish("Retry.PNG");
				exitSuc = true;
				break;
			}
			else
				BotLaunch.sleep(10 * 1000);
		}
		exitSuc = false;

		Principal.getInterface().atualizaConsole("Recomecando...");
		BotLaunch.sleep(10 * 1000);
		inicio(janela);

	}

}
