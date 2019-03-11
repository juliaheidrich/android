package com.tutorials.exceptionvariablenverarbeiten;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */
public class ExceptionVerarbeiten {

    public static void main(String[] args){
        // Einstiegspunkt
        String meineEingabe = "5a";

        try{
            Integer meineEingabeAlsInteger = new Integer(meineEingabe);
            System.out.println(meineEingabeAlsInteger);
        }catch(NumberFormatException e) {
            System.err.println("Fehler: "+e.getMessage() + " is not an integer.");
        }
    }
}
