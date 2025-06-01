
package AudioPlayer;

import View.DaftarLagu.*;
import java.io.*;

//import library yang digunakan agar bisa memutar mp3
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;


public class AudioPlayer extends PlaybackListener{
        
    //menggunakan JLayer library agar bisa play audio
    private AdvancedPlayer advancedPlayer;
    private String filePath;

    
    public AudioPlayer(String filePath) {
        this.filePath = filePath;
    }
    
    //void untuk memutar lagu
    public void play() {
        if(filePath != null) {
            
            System.out.println(filePath + " is playing....");

            try{
                //membaca mp3 audio data
                FileInputStream link = new FileInputStream(filePath);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(link);
                
                //buat advanced player
                advancedPlayer = new AdvancedPlayer(bufferedInputStream);
                advancedPlayer.setPlayBackListener(this);
                
                //void untuk memulai lagu
                startMusicThread();
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startMusicThread() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    //putar lagu
                    advancedPlayer.play();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        
    }

    @Override
    public void playbackStarted(PlaybackEvent evt) {
        System.out.println("Playback Started");
    }
    
    @Override
    public void playbackFinished(PlaybackEvent evt) {
        System.out.println("Playback Finished");
    }
    
    //void untuk stop lagu yang sedang dimainkan
    public void stop() {
        if(advancedPlayer != null) {
            advancedPlayer.stop();
            advancedPlayer.close();
            advancedPlayer = null;
        }
    }
}
