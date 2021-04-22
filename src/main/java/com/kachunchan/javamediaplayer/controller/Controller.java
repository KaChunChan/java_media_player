package com.kachunchan.javamediaplayer.controller;

import com.kachunchan.javamediaplayer.model.MediaLibrary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;

import java.io.File;
import java.nio.file.Path;

/*
 * This class will respond to the user interactions.
 */
public class Controller {

    // These instance variables are used to hold the loaded files and the list that is displayed on the GUI.
    private final MediaLibrary mediaLibrary;
    private final ObservableList<Path> viewableMediaLibrary;
    // An array of strings is used to hold the supported file extensions.
    private final String[] acceptedFileType;
    // These instance variables are used to hold the media player, the selected file from the GUI, and the
    // current file loaded to the media player.
    private MediaPlayer mediaPlayer;
    private Path selectedFile;
    private Path loadedFile;

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label selectedSongName;
    @FXML
    private ListView<Path> libraryListView;

    public Controller() {
        mediaLibrary = new MediaLibrary();
        viewableMediaLibrary = FXCollections.observableArrayList();
        acceptedFileType = new String[]{".mp3", ".wav"};
    }

    // Initialises the GUI screen.
    public void initialize() {
        initialiseLibraryListView();
        initializeListener();
    }

    // Initialises GUI library list view
    private void initialiseLibraryListView() {
        libraryListView.setItems(viewableMediaLibrary);
        // Cell factory added in to display only the filename rather than the whole path of the file.
        libraryListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Path> call(ListView<Path> param) {
                ListCell<Path> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(Path item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getFileName().toString());
                        }
                    }
                };
                return cell;
            }
        });
    }

    // Initialises the listeners for mouse clicks and key presses for the library list view.
    private void initializeListener() {
        libraryListView.setOnMouseClicked(click -> {
            if (libraryListView.getSelectionModel().getSelectedItem() == null) {
                return;
            }
            if (click.getButton().equals(MouseButton.PRIMARY)) {
                processPrimaryMouseClicked(click);
            }
        });

        libraryListView.setOnKeyPressed(event -> {
            Path currentItemSelected = libraryListView.getSelectionModel().getSelectedItem();
            if (currentItemSelected == null) {
                return;
            }
            if (event.getCode().equals(KeyCode.ENTER)) {
                selectedFile = currentItemSelected;
                loadedFile = selectedFile;
                forcePlayMedia();
            }
        });
    }

    // Add in what primary mouse click will perform in here.
    private void processPrimaryMouseClicked(MouseEvent click) {
        if (click.getClickCount() == 2) {
            processDoublePrimaryMouseClick(click);
        } else {
            processSinglePrimaryMouseClick(click);
        }
    }

    // An item on the library list view can be selected when it has be clicked on and
    // can be deselected when it has been clicked on again with a brief pause in between each clicks.
    private void processSinglePrimaryMouseClick(MouseEvent click) {
        Path currentItemSelected = libraryListView.getSelectionModel().getSelectedItem();
        if (currentItemSelected.equals(selectedFile)) {
            selectedFile = null;
            libraryListView.getSelectionModel().clearSelection();
        } else {
            selectedFile = currentItemSelected;
        }
    }

    // Double clicking an item on the library list view will play the selected item.
    // Double clicking on the same item that is currently playing will restart the media.
    private void processDoublePrimaryMouseClick(MouseEvent click) {
        selectedFile = libraryListView.getSelectionModel().getSelectedItem();
        loadedFile = selectedFile;
        forcePlayMedia();
    }

    public void handleOpenDirectory() {
        File directory = getDirectoryEntry();
        if (directory != null) {
            mediaLibrary.loadItemsToMediaLibrary(directory.toPath(), acceptedFileType);
            viewableMediaLibrary.clear();
            viewableMediaLibrary.addAll(mediaLibrary.getMediaLibrary());
        }
    }

    public void forcePlayMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        try {
           if (loadedFile != null) {
               mediaPlayer = new MediaPlayer(new Media(loadedFile.toUri().toString()));
           }
        } catch (MediaException e) {
            System.out.println("Media cannot be opened.");
            e.printStackTrace();
        }
        playMedia();
    }

    public void playMedia() {
        if (loadedFile == null) {
            return;
        }
        mediaPlayer.play();
        statusMessage("Playing " + loadedFile.toString());
    }

    public void pauseMedia() {
        if (loadedFile == null) {
            return;
        }
        mediaPlayer.pause();
        statusMessage("Paused playing " + loadedFile.toString());
    }

    public void stopMedia() {
        if (loadedFile == null) {
            return;
        }
        mediaPlayer.stop();
        statusMessage("Stopped playing " + loadedFile.toString());
    }

    // Updates the status message on the GUI.
    private void statusMessage(String message) {
        selectedSongName.setText(message);
    }

    // Returns the directory selected by the user
    private File getDirectoryEntry() {
        return (new DirectoryChooser()).showDialog(borderPane.getScene().getWindow());
    }
}