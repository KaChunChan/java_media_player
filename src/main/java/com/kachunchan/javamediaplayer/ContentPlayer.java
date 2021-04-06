package com.kachunchan.javamediaplayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class ContentPlayer {

    private static ContentPlayer contentPlayer;
    private final ArrayList<MediaFile> loadedMedias;
    private static MediaPlayer mediaPlayer;
    private static MediaFile selectedMedia;
    private static Media media;


    private ContentPlayer() {
        loadedMedias = new ArrayList<>();
    }

    public static ContentPlayer getInstance() {
        if (contentPlayer == null) {
            contentPlayer = new ContentPlayer();
        }
        return contentPlayer;
    }

    public void loadMediaToList(File directoryPath) {
        if (!directoryPath.isDirectory()) {
            return;
        }

        FileFilter mp3Filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return (pathname.getPath().endsWith(".mp3"));
            }
        };

        File[] mediaArray = directoryPath.listFiles(mp3Filter);
        if (mediaArray != null) {
            for (File file : mediaArray) {
                MediaFile mediaFile = new MediaFile(file);
                loadedMedias.add(mediaFile);
            }
        }
    }

    public ArrayList<MediaFile> getLoadedMedias() {
        return loadedMedias;
    }

    public void setSelectedSong(MediaFile mediaFile) {
        this.selectedMedia = mediaFile;
    }

    public void playMedia() {
        if (selectedMedia == null) return;
        if (!selectedMedia.getURI().equals(media)) {
            media = new Media(selectedMedia.getURI());
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void pauseMedia() {
        if (selectedMedia == null) return;
        mediaPlayer.pause();

    }

    public void stopMedia() {
        if (selectedMedia == null) return;
        mediaPlayer.stop();
    }

}