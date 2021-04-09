package com.kachunchan.javamediaplayer.controller;

import com.kachunchan.javamediaplayer.model.ContentPlayer;
import com.kachunchan.javamediaplayer.model.MediaFile;
import com.kachunchan.javamediaplayer.model.MediaLibrary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class Controller {

    private final MediaLibrary mediaLibrary = new MediaLibrary();
    private final ObservableList<MediaFile> viewableMediaLibrary = FXCollections.observableArrayList();
    private ContentPlayer contentPlayer;
    private MediaFile selectedFile;
    private MediaFile loadedFile;
    private String acceptedFileType = ".mp3";

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label selectedSongName;
    @FXML
    private ListView<MediaFile> libraryListView;

    public void initialize() {
        libraryListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                MediaFile currentItemSelected = libraryListView.getSelectionModel().getSelectedItem();
                if (currentItemSelected == null) {
                    return;
                }
                if (click.getClickCount() == 1) {
                    if (currentItemSelected.equals(selectedFile)) {
                        selectedFile = null;
                        libraryListView.getSelectionModel().clearSelection();
                    } else {
                        selectedFile = currentItemSelected;
                    }
                }
                if (click.getClickCount() == 2) {
                    selectedFile = libraryListView.getSelectionModel().getSelectedItem();
                    loadedFile = selectedFile;
                    forcePlayMedia();
                }
            }
        });

        libraryListView.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                MediaFile currentItemSelected = libraryListView.getSelectionModel().getSelectedItem();
                if (currentItemSelected == null) {
                    return;
                }
                if (event.getCode().equals(KeyCode.ENTER)) {
                    selectedFile = currentItemSelected;
                    loadedFile = selectedFile;
                    forcePlayMedia();
                }
            }
        });
    }

    public void handleOpenDirectory() {
        File directory = getDirectoryEntry();
        if (directory != null) {
            mediaLibrary.loadItemsToFileList(directory, acceptedFileType);
            viewableMediaLibrary.clear();
            viewableMediaLibrary.addAll(mediaLibrary.getItemsFromFileList());
            libraryListView.getItems().setAll(viewableMediaLibrary);
        }
    }

    public void forcePlayMedia() {
        if (contentPlayer != null) {
            contentPlayer.stopMedia();
        }
        contentPlayer = new ContentPlayer(new MediaPlayer(new Media(loadedFile.getURI())));
        playMedia();
    }

    public void playMedia() {
        if (loadedFile == null) {
            return;
        }
        contentPlayer.playMedia();
        statusMessage("Playing " + loadedFile.toString());
    }

    public void pauseMedia() {
        contentPlayer.pauseMedia();
        statusMessage("Paused playing " + loadedFile.toString());
    }

    public void stopMedia() {
        contentPlayer.stopMedia();
        statusMessage("Stopped playing " + loadedFile.toString());
    }

    private void statusMessage(String message) {
        selectedSongName.setText(message);
    }

    private File getDirectoryEntry() {
        return (new DirectoryChooser()).showDialog(borderPane.getScene().getWindow());
    }

}
