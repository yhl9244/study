package designmodel.adapter;

public class TestAudioPlayer {

    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "aa");
        audioPlayer.play("vlc", "bb");
        audioPlayer.play("mp4", "cc");
        audioPlayer.play("voc", "dd");
    }
}
