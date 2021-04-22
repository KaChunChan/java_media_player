# Java Media Player
A learning project aimed to create an audio file player using JavaFx for Windows 10.
This Java Media Player project currently supports .mp3 and .wav file format.
The Java Media Player allows the user to select a directory containing the audio files and then scans 
the directory for supported audio format files. The paths of any valid audio files will be stored in the 
Java Media Player. The Java Media Player does not scan for sub folders.

# Build Environment
* IntelliJ IDEA Community Edition 2021.1
* OpenJDK 11
* Apache Maven 3.8.1

# Java Installation Steps
1. Download Java openjdk-11 archive and extract it to any directory. (E.g. c:\java\) (https://jdk.java.net/java-se-ri/11)
2. Append the file path of openjdk-11 to "Path" in "System Variables". (E.g. "c:\java\openjdk-11\bin" for Windows O.S.)
3. Add a new System Variable with the variable name "JAVA_HOME", and the variable value with the path of where openjdk-11 is stored. (E.g. "c:\java\openjdk-11" - exclude the bin.)
4. Open command prompt/terminal and run `java --version` to check if Java has been installed.

# Maven Installation Steps
1. Follow the provided link to install maven. (https://maven.apache.org/install.html)

# Build Steps
1. Open command prompt/terminal at the root of the project (i.e. /java_media_project) and execute:
  `mvn javafx:jlink`
2. Built files are located in ./target/JavaMediaPlayer (Alternatively ./target/JavaMediaPlayer.zip) and run `./target/JavaMediaPlayer/bin/JMP.bat` to start the Java Media Player.

# Application Instructions
* Load music library
1. Click the 'Open' button.
2. Select the directory that contains your audio files. A list of songs will then appear on the library.

* Play song 
1. Select a song using the 'up' and 'down' keys to select a song.
2. Press 'Enter' or 'Return' key to load and play the song.
Alternatively:
1. Double-click on a song from the list to load and play the song.

* Pause song
1. Click the 'Pause' button to pause the song. Press the 'Play' button to resume playing the song.

* Stop song
1. Click the 'Stop' button to stop the song. Press the 'Play' button to start playing the song again.
