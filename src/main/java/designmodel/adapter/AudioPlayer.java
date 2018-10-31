package designmodel.adapter;

public class AudioPlayer implements MediaPlayer {

    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String filename) {
        if("mp3".equalsIgnoreCase(audioType)) {
            System.out.println("Playing mp3 file name:" + filename);
        } else if("vlc".equalsIgnoreCase(audioType) || "mp4".equalsIgnoreCase(audioType)) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType,filename);
        } else {
            System.out.println("Invalid media." + audioType + "   format  not supported");
        }
    }
}
