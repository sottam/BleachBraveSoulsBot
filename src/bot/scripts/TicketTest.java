package bot.scripts;
import org.sikuli.script.Region;

import bot.scripts.RegionHandler;
import pacote.principal.BotLaunch;
import pacote.principal.Principal;

public final class TicketTest {
	private static boolean checkTicket(Region janela) {
		
		RegionHandler.setTemp(janela);
		RegionHandler.createTempRegion(470, 40, 200, 60, janela);
		
		if (janela.exists("ZeroTickets.PNG") != null) {
			return false;
		}
		else if (janela.exists("1Tickets.PNG") != null) {
			RegionHandler.restoreRegion(janela);
			return true;
		}
		else if (janela.exists("5PlusTickets.PNG") != null) {
			RegionHandler.restoreRegion(janela);
			return true;
		}
		else if (janela.exists("4Tickets.PNG") != null) {
			RegionHandler.restoreRegion(janela);
			return true;
		}
		else if (janela.exists("3Tickets.PNG") != null) {
			RegionHandler.restoreRegion(janela);
			return true;
		}
		else if (janela.exists("2Tickets.PNG") != null) {
			RegionHandler.restoreRegion(janela);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public static boolean playMatch(Region janela) throws InterruptedException{
		if (checkTicket(janela)){
			Principal.getInterface().atualizaConsole("Ticket OK");
			return true;
		}
		else {
			Principal.getInterface().atualizaConsole("Aguardando Tickets...");
			BotLaunch.sleep(60 * 1000);
			return false;
		}
	}
}
