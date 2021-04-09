package com.kachunchan.javamediaplayer.model;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class MediaLibrary {

    private final List<MediaFile> mediaFileList;

    public MediaLibrary() {
        this.mediaFileList = new ArrayList<>();
    }

    public void loadItemsToFileList(File path) {
        loadItemsToFileList(path, "");
    }

    public void loadItemsToFileList(File path , String suffix) {
        if (!path.isDirectory()) {
            return;
        }
        mediaFileList.clear();
        FileFilter mp3Filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return (pathname.getPath().endsWith(suffix));
            }
        };
        File[] files = path.listFiles(mp3Filter);
        for (File file : files) {
            if (file.isFile()) {
                mediaFileList.add(new MediaFile(file));
            }
        }
    }

    public List<MediaFile> getItemsFromFileList() {
        return mediaFileList;
    }
}
