package service;

import resources.Strings;
import util.Log;

import javax.sound.sampled.*;


public class AudioManager implements Strings {

    private static final Log log = Log.getInstance(AudioManager.class);

    private static String audioName;

    private static AudioInputStream ais;

    public static void play() {
        log.log("开始播放 " + audioName);
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
            }, ais, 0, "正在播放: " + audioName);
        }
        else log.error(audio_not_load_error);
    }

    public static void load(String name, boolean isAutoPlay) {
        log.log("加载 " + name + " 音乐.");
        audioName = name;
        ais = AssetManager.getAudio(name);
        if(isAutoPlay) play();
    }

    public static void load(String name) {
        load(name, false);
    }

}
