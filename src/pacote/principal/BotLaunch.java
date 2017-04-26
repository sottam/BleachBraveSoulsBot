package pacote.principal;

import bot.scripts.LotteryEvent;
import bot.scripts.NormalQuest;
import bot.scripts.PointEvent;

public class BotLaunch extends Thread {
	private Thread t;
	private String threadName;

	bot.scripts.PointEvent pe = new PointEvent();
	bot.scripts.NormalQuest nq = new NormalQuest();
	bot.scripts.LotteryEvent le = new LotteryEvent();
	
	public BotLaunch(String name) {
		threadName = name;
		Principal.getInterface().atualizaConsole("Creating " + threadName);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		Principal.getInterface().atualizaConsole("Running " + threadName);
		try {
			
			switch (Principal.getQuestMODE()) {
				case 0:
					Principal.getInterface().atualizaConsole("Running Normal Quest");
					nq.normalQuests(Principal.getJanela());
					break;
				case 1:
					Principal.getInterface().atualizaConsole("Running Lottery Event");
					le.lotteryEvent(Principal.getJanela());
					break;
				case 2:
					Principal.getInterface().atualizaConsole("Lottery & Points Event");
					pe.pointEventsQuests(Principal.getJanela());
					break;
				default:
					Principal.getInterface().atualizaConsole("Modo Invalido");
					t.stop();
				
			}
			

		} catch (InterruptedException e) {
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
		Principal.getInterface().atualizaConsole("Parado");
		t.stop();
	}
}
