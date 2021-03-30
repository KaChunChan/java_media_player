package com.chan.kachun.javamediaplayer;

import java.io.File;

public class MediaFile {
    private File file;

    public MediaFile(File file) {
        this.file = file;
    }

    public String toString() {
        return file.getName();
    }

    public String getPath() {
        return file.getPath();
    }
}
