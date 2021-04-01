package com.kachunchan.javamediaplayer.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private final List<File> playlist;

    public Playlist() {
        this.playlist = new ArrayList<>();
    }

    public List<File> getPlaylist() {
        return playlist;
    }

    public void addToPlaylist(File file) {
        if (file.isFile()) {
            playlist.add(file);
        }
    }

    public void removeFromPlaylist(int index) {
        playlist.remove(index);
    }

    public void clearPlaylist() {
        playlist.clear();
    }
}
