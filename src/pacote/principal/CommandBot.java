package pacote.principal;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

public class CommandBot extends Thread {

	private Thread t;
	private String threadName;
	TelegramBot bot;
	private static int LONG_POOLING_TIMEOUT_IN_SECONDS = 7;
	private AtomicInteger updateId = new AtomicInteger(0);
	boolean controle;
	int chatid;

	Keyboard replyKeyboardMarkup;

	public CommandBot(String name, String[] token) {
		threadName = name;
		bot = TelegramBotAdapter.build(token[0]);
		controle = false;
		this.chatid = Integer.parseInt(token[1]);

		replyKeyboardMarkup = new ReplyKeyboardMarkup(new String[] { "/lastrun", "/print", "/reset" })
							  .oneTimeKeyboard(false)
							  .resizeKeyboard(true).selective(true);
	}

	public void run() {
		controle = false;

		while (true) {
			//System.out.println("Checking for updates...");
			bot.execute(new GetUpdates().limit(1).offset(updateId.get()).timeout(LONG_POOLING_TIMEOUT_IN_SECONDS),
					new Callback<GetUpdates, GetUpdatesResponse>() {

						public void onResponse(GetUpdates request, GetUpdatesResponse response) {
							try {
								for (Update update : response.updates()) {
									System.err.println("Telegram update Received");
									if (update.message().text().equals("/start")) {
										updateId.set(update.updateId() + 1);
										bot.execute(new SendMessage(update.message().from().id(), "Seu ID: " + update.message().from().id()));
										bot.execute(new SendMessage(update.message().from().id(), "Options:")
												.replyMarkup(replyKeyboardMarkup));
									}
									if (update.message().text().equals("/reset")) {
										updateId.set(update.updateId() + 1);
										controle = false;
										bot.execute(new SendMessage(update.message().from().id(), "Reseted.")
												.replyMarkup(replyKeyboardMarkup));
									}
									
									if (update.message().text().equals("/lastrun")) {
										//System.out.print(update.message().from().id());
										updateId.set(update.updateId() + 1);
										bot.execute(new SendMessage(update.message().from().id(),
													"Last Run: " 
													+ DataHandler.getLastRun() 
													+ DataHandler.DiferencaMinutos())
													.replyMarkup(replyKeyboardMarkup));
									}
									if (update.message().text().equals("/print")) {
										updateId.set(update.updateId() + 1);
										bot.execute(new SendMessage(update.message().from().id(),
													"Um print de tela será enviado")
													.replyMarkup(replyKeyboardMarkup));
										try {
										bot.execute(new SendPhoto(update.message().from().id(),
													new File(SSRobot.takeSS(Principal.getJanela()))));
										} catch(SocketTimeoutException e){System.out.println("Socket TimeOut");}
									}
								}
								//Envio de mensagem caso o BOT pare
								if (DataHandler.stopTest(20 * 60) && !controle) {
									Principal.getInterface().atualizaConsole("O BOT precisa de atenção.");
									controle = true;
									//updateId.set(update.updateId() + 1);
									bot.execute(new SendMessage(chatid,
												"O BOT precisa da sua atenção.")
												.replyMarkup(replyKeyboardMarkup));
									
									bot.execute(new SendPhoto(chatid,
												new File(SSRobot.takeSS(Principal.getJanela()))));
								}
							} catch (NullPointerException | IOException | AWTException e) {}
						}

						@Override
						public void onFailure(GetUpdates request, IOException e) {}
					});

			try {
				Thread.sleep(LONG_POOLING_TIMEOUT_IN_SECONDS * 1000);
			} catch (InterruptedException e) {}
		}
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	@SuppressWarnings("deprecation")
	public void shutdownBaby() {
		System.out.println("Parado");
		t.stop();
	}

}
