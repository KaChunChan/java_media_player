package com.kachunchan.javamediaplayer.model;

import javafx.scene.media.MediaPlayer;

public class ContentPlayer {

    private MediaPlayer mediaPlayer;

    public ContentPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void playMedia() {
        mediaPlayer.play();
    }

    public void pauseMedia() {
        mediaPlayer.pause();
    }

    public void stopMedia() {
        mediaPlayer.stop();
    }

}
