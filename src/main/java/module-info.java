module com.kachunchan.javamediaplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    opens com.kachunchan.javamediaplayer;
    exports com.kachunchan.javamediaplayer;
}