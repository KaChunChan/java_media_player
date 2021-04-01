package com.kachunchan.javamediaplayer.model;

import java.util.Scanner;

public class CommandLineEntry {

    private Scanner scanner = new Scanner(System.in);

    public String getCommandDialog() {
        System.out.println("Enter your command (enter 'help' for more info)");
        return scanner.nextLine();
    }

    public void showHelpDialog() {
        System.out.println("Available commands are: 'ROOT', 'SHOW', 'ADD', 'REMOVE'");
        System.out.println("ROOT - Sets the root of the media library");
        System.out.println("Syntax: ROOT <path>");
        System.out.println();
        System.out.println("SHOW - Outputs the media library or the playlist");
        System.out.println("Syntax: SHOW <library|playlist>");
        System.out.println();
        System.out.println("ADD - Adds a file from the media library to the playlist");
        System.out.println("Syntax: ADD <number>");
        System.out.println();
        System.out.println("REMOVE - Remove a file from the playlist");
        System.out.println("Syntax: REMOVE <number>");
        System.out.println();
        System.out.println("CLEAR - Remove a file from the playlist");
        System.out.println("Syntax: REMOVE <number>");
        System.out.println();
    }


    public String getCommand(String string) {
        String[] command = string.split(" ", 2);
        return command[0];
    }

    public String getPath(String string) {
        String[] command = string.split(" ", 2);
        return command[1];
    }

    public int getIndex(String string) {
        return parseToInt(extractSecondString(string));
    }

    private String extractSecondString(String string) {
        String[] command = string.split(" ", 3);
        return command[1];
    }

    private int parseToInt(String index) {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getShowParameter(String string) {
        return extractSecondString(string);
    }


}
