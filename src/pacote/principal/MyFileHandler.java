package pacote.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MyFileHandler {

	public void criaArquivo(String fileName) {
		FileWriter arquivo;

		try {
			arquivo = new FileWriter(new File(fileName));
			arquivo.close();
		} catch (IOException e) {}
	}

	public String[] obterKey(String fileName) {
		Scanner ler = new Scanner(fileName);
		String nome = ler.nextLine();
		String[] saida = new String[2];

		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			saida[0] = linha.trim();
			linha = lerArq.readLine();
			saida[1] = linha.trim();
			arq.close();
			return saida;
		} catch (IOException | NullPointerException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
			System.err.println("Criando novo arquivo...");
			criaArquivo(fileName);
		} finally {
			ler.close();
		}

		return null;
	}
}
