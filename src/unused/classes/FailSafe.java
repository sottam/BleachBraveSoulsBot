package unused.classes;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;

import bot.scripts.Menus;
import pacote.principal.Principal;

public class FailSafe extends Thread {
	private Thread t;
	private String threadName;
	private Region janela;

	public FailSafe(String name) {
		threadName = name;
		Principal.getInterface().atualizaConsole("Creating " + threadName);
		janela = App.focusedWindow();
	}

	@Override
	public void run() {
		Principal.getInterface().atualizaConsole("Running " + threadName);

		try {
			boolean bool = false;
			
			while (!bool ) {
				//if (DataHandler.comparaData(new Date(), 1200) == true) {
				if (!bool) {
					Menus.failSafe(janela);
				} else {
					System.out.println("Nada de errado, rodando como esperado.");
					FailSafe.sleep(60 * 1000);
				}

			}

		} catch (FindFailed | InterruptedException e) {
			Principal.getInterface().atualizaConsole("Thread " + threadName + " interrupted.");
		}
		Principal.getInterface().atualizaConsole("Thread " + threadName + " exiting.");

	}

	public void start() {
		Principal.getInterface().atualizaConsole("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();

		}
	}

	@SuppressWarnings("deprecation")
	public void shutdownBaby() {
		Principal.getInterface().atualizaConsole("Fail-Safe Parado");
		t.stop();
	}
}
