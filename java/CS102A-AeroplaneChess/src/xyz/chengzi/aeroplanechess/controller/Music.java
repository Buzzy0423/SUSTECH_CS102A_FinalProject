package xyz.chengzi.aeroplanechess.controller;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Music {
    //	private AudioInputStream audioIn;
//	private SourceDataLine sourceDataLine;
    // wav文件的路径
    private File file;
    // 是否循环播放
    private boolean isLoop = false;
    // 是否正在播放
    private boolean isPlaying;
    // FloatControl.Type.MASTER_GAIN的值(调节音量)
    private float newVolumn = 7;

    private PlayThread playThread;

    public Music(File f) {
        file = f;
    }
    public void play() {
        playThread = new PlayThread();
        playThread.start();
    }

    public void over() {
        isPlaying = false;
        if (playThread != null) {
            playThread = null;
        }
    }

    public Music setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        return this;
    }
    
    private class PlayThread extends Thread {

        @Override
        public void run() {
            isPlaying = true;
            do {
//				isPlaying = true;

                SourceDataLine sourceDataLine = null;
                BufferedInputStream bufIn = null;
                AudioInputStream audioIn = null;
                try {
                    bufIn = new BufferedInputStream(new FileInputStream(file));
                    audioIn = AudioSystem.getAudioInputStream(bufIn); // 可直接传入file

                    AudioFormat format = audioIn.getFormat();
                    sourceDataLine = AudioSystem.getSourceDataLine(format);
                    sourceDataLine.open();
                    // 必须open之后
                    if (newVolumn != 7) {
                        FloatControl control = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
//						System.out.println(control.getMaximum());
//						System.out.println(control.getMinimum());
                        control.setValue(newVolumn);
                    }

                    sourceDataLine.start();
                    byte[] buf = new byte[512];
//					System.out.println(audioIn.available());
                    int len = -1;
                    while (isPlaying && (len = audioIn.read(buf)) != -1) {
                        sourceDataLine.write(buf, 0, len);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    if (sourceDataLine != null) {
                        sourceDataLine.drain();
                        sourceDataLine.close();
                    }
                    try {
                        if (bufIn != null) {
                            bufIn.close();
                        }
                        if (audioIn != null) {
                            audioIn.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } while (isPlaying && isLoop);
        }
    }


}