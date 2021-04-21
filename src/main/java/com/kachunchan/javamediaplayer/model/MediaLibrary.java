package com.kachunchan.javamediaplayer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * This class is used to load files from a selected directory if the files are supported, and stores the
 * location of each file in a list. *
 */
public class MediaLibrary {

    // The mediaLibrary field stores the list of files in a directory. Each time when a new path is given
    // a new list is instantiated.
    private List<Path> mediaLibrary;

    public MediaLibrary() {
        this.mediaLibrary = null;
    }

    // The loadItemsToMediaLibrary uses a predicate to filter out the files that are not supported.
    public void loadItemsToMediaLibrary(Path path, String[] suffixes) {
        mediaLibrary = new ArrayList<>();

        Predicate<Path> fileFilter = file -> {
            for (String suffix : suffixes) {
                if (file.getFileName().toString().endsWith(suffix)) {
                    return true;
                }
            }
            return false;
        };

        try {
            Stream<Path> files = Files.list(path);
            mediaLibrary = files
                    .filter(fileFilter)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error loading files");
            e.printStackTrace();
        }
    }

    public List<Path> getMediaLibrary() {
        return mediaLibrary;
    }
}
