package com.chan.kachun.javamediaplayer;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class ContentPlayer {

    private static ContentPlayer contentPlayer;
    private final ArrayList<MediaFile> loadedMedias;
    private MediaFile selectedSong;


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
        this.selectedSong = mediaFile;
    }

    

}
