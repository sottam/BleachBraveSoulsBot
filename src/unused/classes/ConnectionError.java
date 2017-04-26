package unused.classes;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;

import bot.scripts.Menus;
import pacote.principal.Principal;

public class ConnectionError extends Thread {
	private Thread t;
	private String threadName;
	private Region janela;

	public ConnectionError(String name) {
		threadName = name;
		Principal.getInterface().atualizaConsole("Creating " + threadName);
		janela = App.focusedWindow();
	}

	@Override
	public void run() {
		Principal.getInterface().atualizaConsole("Running " + threadName);

		try {
			boolean bool = false;
			while (!bool) {
				if (Menus.checkConnectionErrorDialog(janela)) {
					Menus.ConnectionError(janela);
				} else {
					System.out.println("Dialogo de conexao nao encontrado, sem problemas de conexao");
					ConnectionError.sleep(30 * 1000);
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
		Principal.getInterface().atualizaConsole("Con-Error Parado");
		t.stop();
	}
}
