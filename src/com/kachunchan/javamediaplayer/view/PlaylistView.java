package com.kachunchan.javamediaplayer.view;

import com.kachunchan.javamediaplayer.controller.PlaylistController;
import com.kachunchan.javamediaplayer.model.CommandLineEntry;

import java.io.File;
import java.util.List;


public class PlaylistView {

    CommandLineEntry commandLineEntry;
    PlaylistController playlistController;

    public PlaylistView(PlaylistController playlistController, CommandLineEntry commandLineEntry) {
        this.playlistController = playlistController;
        this.commandLineEntry = commandLineEntry;
    }


    // refactor this
    public void run() {
        while (true) {
            String input = commandLineEntry.getCommandDialog();
            String command = commandLineEntry.getCommand(input);
            if (command.equalsIgnoreCase("HELP")) {
                commandLineEntry.showHelpDialog();
            } else if (command.equalsIgnoreCase("ROOT")) {
                File root = new File(commandLineEntry.getPath(input));
                if (root.isDirectory()) {
                    playlistController.initializeRootDirectory(root);
                } else {
                    System.out.println(root.toString() + " is not a directory");
                }
            } else if (command.equalsIgnoreCase("SHOW")) {
                String parameter = commandLineEntry.getShowParameter(input);
                if (parameter.equalsIgnoreCase("LIBRARY")) {
                    playlistController.displayFileList();
                } else if (parameter.equalsIgnoreCase("PLAYLIST")) {
                    playlistController.displayPlaylist();
                }
            } else if (command.equalsIgnoreCase("ADD")) {
                int index = commandLineEntry.getIndex(input);
                if (index >= 0) {
                    playlistController.addFileToPlaylist(index);
                }
            } else if (command.equalsIgnoreCase("REMOVE")) {
                int index = commandLineEntry.getIndex(input);
                if (index >= 0) {
                    playlistController.removeFileFromPlaylist(index);
                }
            } else if (command.equalsIgnoreCase("CLEAR")) {
                playlistController.clearPlaylist();
            } else {
                System.out.println("Invalid parameter. Use 'help' for more options.");
            }
        }

    }

    public void showFilesInDirectory(List<File> files) {
        System.out.println("File List: \tIndex\t\tName");
        displayList(files);
    }

    public void showPlaylist(List<File> files) {
        System.out.println("Playlist: \tIndex\t\tName");
        displayList(files);
    }

    private void displayList(List<File> files) {

        for(int i = 0; i < files.size(); i++) {
            System.out.println("\t\t\t" + i + "\t\t\t" + files.get(i).getName());
        }
    }

}
