package br.com.professorisidro.isilanguage.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
import br.com.professorisidro.isilanguage.parser.IsiLangLexer;
import br.com.professorisidro.isilanguage.parser.IsiLangParser;

/* esta é a classe que é responsável por criar a interação com o usuário
 * instanciando nosso parser e apontando para o arquivo fonte
 * 
 * Arquivo fonte: extensao .isi
 * 
 */
public class MainClass {
	public static void main(String[] args) {
		final String ANSI_RED = "\u001B[31m	";
		final String ANSI_RESET = "\u001B[0m";
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;

			// leio o arquivo "input.isi" e isso é entrada para o Analisador Lexico
			// lexer = new IsiLangLexer(CharStreams.fromFileName("input.isi"));
			lexer = new IsiLangLexer(CharStreams.fromFileName("types.isi"));

			// crio um "fluxo de tokens" para passar para o PARSER
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);

			// crio meu parser a partir desse tokenStream
			parser = new IsiLangParser(tokenStream);

			parser.prog();

			System.out.println("Compilation Successful");

			// parser.exibeComandos();

			parser.generateJavaCode();

			parser.generateDartCode();

		} catch (IsiSemanticException ex) {
			System.err.println(ANSI_RED + "Semantic error - " + ANSI_RESET + ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ANSI_RED + "ERROR " + ex.getMessage() + ANSI_RESET);
		}

	}

}
