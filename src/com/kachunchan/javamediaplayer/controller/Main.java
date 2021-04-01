package com.kachunchan.javamediaplayer.controller;

import com.kachunchan.javamediaplayer.model.CommandLineEntry;
import com.kachunchan.javamediaplayer.model.FileList;
import com.kachunchan.javamediaplayer.model.Playlist;
import com.kachunchan.javamediaplayer.view.PlaylistView;

public class Main {

    public static void main(String[] args) {

        PlaylistController pc = new PlaylistController(new FileList(), new Playlist());
        pc.run();

    }
}
