
interface MediaPlayer {
    void play(String fileType, String fileName);
}

class AdvancedMediaPlayer {
    void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
    void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }
}

class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer = new AdvancedMediaPlayer();

    public void play(String fileType, String fileName) {
        if(fileType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        } else if(fileType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else {
            System.out.println("Format not supported: " + fileType);
        }
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        MediaPlayer player = new MediaAdapter();

        player.play("mp4", "movie.mp4");
        player.play("vlc", "song.vlc");
        player.play("avi", "clip.avi");
    }
}
