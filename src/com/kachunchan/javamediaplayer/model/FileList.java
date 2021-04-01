package com.kachunchan.javamediaplayer.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileList {

    private File directory;
    private final List<File> files;

    public FileList() {
        this.files = new ArrayList<>();
    }

    public void setDirectory(File directory) {
        if (directory.isDirectory()) {
            this.directory = directory;
        }
    }

    public List<File> getFiles() {
        return files;
    }

    public void populateFiles() {
        files.addAll(Arrays.asList(directory.listFiles()));
    }
}
