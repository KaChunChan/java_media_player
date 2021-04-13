module com.kachunchan.javamediaplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.kachunchan.javamediaplayer;
    opens com.kachunchan.javamediaplayer.controller;
    exports com.kachunchan.javamediaplayer.controller;
}