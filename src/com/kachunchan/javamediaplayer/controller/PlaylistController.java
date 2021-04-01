package com.kachunchan.javamediaplayer.controller;

import com.kachunchan.javamediaplayer.model.CommandLineEntry;
import com.kachunchan.javamediaplayer.model.FileList;
import com.kachunchan.javamediaplayer.model.Playlist;
import com.kachunchan.javamediaplayer.view.PlaylistView;

import java.io.File;

public class PlaylistController {

    private FileList fileList;
    private Playlist playlist;
    private PlaylistView view;

    public PlaylistController(FileList fileList, Playlist playlist) {
        this.fileList = fileList;
        this.playlist = playlist;
        this.view = new PlaylistView(this, new CommandLineEntry());
    }

    public void initializeRootDirectory(File file) {
        fileList.setDirectory(file);
        fileList.populateFiles();
    }

    public void displayPlaylist() {
        view.showPlaylist(playlist.getPlaylist());
    }

    public void displayFileList() {
        view.showFilesInDirectory(fileList.getFiles());
    }

    public void addFileToPlaylist(int index) {
        playlist.addToPlaylist(fileList.getFiles().get(index));
    }

    public void removeFileFromPlaylist(int index) {
        playlist.removeFromPlaylist(index);
    }

    public void clearPlaylist() {
        playlist.clearPlaylist();
    }

    public void run() {
        view.run();
    }

}
