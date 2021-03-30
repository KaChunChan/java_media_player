package com.chan.kachun.javamediaplayer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class Controller {
    private File loadedSong;

    @FXML
    private BorderPane borderPane;
    @FXML
    private ListView<MediaFile> listOfSongView;
    @FXML
    private Label selectedSongName;

    public void initialize() {
        listOfSongView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MediaFile>() {
            @Override
            public void changed(ObservableValue<? extends MediaFile> observableValue, MediaFile oldValue, MediaFile newValue) {
                if(newValue != null) {
                    MediaFile mediaFile = listOfSongView.getSelectionModel().getSelectedItem();
                    selectedSongName.setText(mediaFile.toString());

                }
            }
        });

    }


    public void handleOpenDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(borderPane.getScene().getWindow());

        if (directory != null) {
            ContentPlayer.getInstance().loadMediaToList(directory);
            listOfSongView.getItems().setAll(ContentPlayer.getInstance().getLoadedMedias());
        } else {
            System.out.println("File selection canceled");
        }
    }



}
