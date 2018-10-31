package designmodel.adapter;

public class VlcPlayer implements AdvanceMediaPlayer {
    @Override
    public void playVlc(String filename) {
        System.out.println("Playing vlc file name:" + filename);
    }

    @Override
    public void playMp4(String filename) {

    }
}
