package br.com.professorisidro.isilanguage.exceptions;

public class IsiSemanticException extends RuntimeException {
    private static final String ANSI_RED = "\u001B[31m	";
    private static final String ANSI_RESET = "\u001B[0m";

    public IsiSemanticException(String msg) {
        super(ANSI_RED + msg + ANSI_RESET);
    }

}
