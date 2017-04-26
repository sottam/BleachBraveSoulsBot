package pacote.principal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataHandler {
	Calendar calendar;
	private static Date lastRun;
	
	public static String dataAtual(){
		Date hoje  = new Date();
		lastRun = hoje;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(hoje);
	}
	
	public static String dataAtualFileName(){
		Date hoje  = new Date();
		lastRun = hoje;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		return sdf.format(hoje);
	}
	
	public static boolean comparaData(Date agora, double dtSeg){
		long segundos = (agora.getTime() -  lastRun.getTime()) / 1000;
		// 60*20 = 20 min
		if (segundos > dtSeg)
			return true;
		else
			return false;	
	}
	
	public static boolean stopTest(double dtSeg){
		Date agora = new Date();
		long segundos = (agora.getTime() -  lastRun.getTime()) / 1000;
		// 60*20 = 20 min
		if (segundos > dtSeg)
			return true;
		else
			return false;
	}
	
	public static String DiferencaMinutos(){
		Date agora = new Date();
		long minutos_long = (agora.getTime() -  lastRun.getTime()) / 60000;
		int minutos = (int) minutos_long;
		if(minutos <= 1)
			return " (" + minutos + " minuto)";
		else
			return " (" + minutos + " minutos)";
	}
	
	public static String getLastRun() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm");
		return sdf.format(lastRun);
	}
}
