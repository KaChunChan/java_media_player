package com.kachunchan.javamediaplayer.model;

import java.io.File;

public class MediaFile {
    private File file;

    public MediaFile(File file) {
        this.file = file;
    }

    public String toString() {
        return file.getName();
    }

    public String getURI() {
        return file.toURI().toString();
    }
}
