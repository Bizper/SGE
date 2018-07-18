package service;

import util.Log;

import javax.sound.sampled.*;


public class AudioManager {

    private static final Log log = Log.getInstance(AudioManager.class);

    private static String audioName;

    private static AudioInputStream ais;

    public static void play() {
        log.log("start to play " + audioName);
        if(ais != null) {
            TaskManager.getInstance().addTimedTask((ais) -> {
                try {
                    SourceDataLine sourceDataLine;
                    AudioFormat audioFormat = ais.getFormat();
                    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
                    sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                    sourceDataLine.open(audioFormat);
                    sourceDataLine.start();
                    int count;
                    byte tempBuffer[] = new byte[1024];
                    while((count = ais.read(tempBuffer, 0, tempBuffer.length)) != -1){
                        sourceDataLine.write(tempBuffer, 0, count);
                    }
                } catch(Exception e) {
                    log.error(e);
                }
            }, ais, 0, "PLAYING " + audioName);
        }
        else log.error("no audio loaded. please load audio firstly.");
    }

    public static void load(String name, boolean isAutoPlay) {
        log.log("loading " + name + " audio.");
        audioName = name;
        ais = AssetManager.getAudio(name);
        if(isAutoPlay) play();
    }

}
