package edu.hitsz.application;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.DataLine.Info;

public class MusicThread extends Thread {

    //音频文件名
    private String filename;
    private AudioFormat audioFormat;
    private byte[] samples;

    // 控制播放状态
    private volatile boolean looping = false;   // 是否循环
    private volatile boolean stopFlag = false;  // 停止标记

    public MusicThread(String filename) {
        //初始化filename
        this.filename = filename;
        reverseMusic();
    }

    public void reverseMusic() {
        try {
            //定义一个AudioInputStream用于接收输入的音频数据，使用AudioSystem来获取音频的音频输入流
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
            //用AudioFormat来获取AudioInputStream的格式
            audioFormat = stream.getFormat();
            samples = getSamples(stream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getSamples(AudioInputStream stream) {
        int size = (int) (stream.getFrameLength() * audioFormat.getFrameSize());
        byte[] samples = new byte[size];
        DataInputStream dataInputStream = new DataInputStream(stream);
        try {
            dataInputStream.readFully(samples);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return samples;
    }

    public void play(InputStream source) {
        int size = (int) (audioFormat.getFrameSize() * audioFormat.getSampleRate());
        byte[] buffer = new byte[size];
        //源数据行SoureDataLine是可以写入数据的数据行
        SourceDataLine dataLine = null;
        //获取受数据行支持的音频格式DataLine.info
        Info info = new Info(SourceDataLine.class, audioFormat);

        try {
            dataLine = (SourceDataLine) AudioSystem.getLine(info);
            dataLine.open(audioFormat, size);
            dataLine.start();

            int numBytesRead;
            // 循环播放逻辑
            while (!stopFlag) {
                numBytesRead = source.read(buffer, 0, buffer.length);
                if (numBytesRead != -1) {
                    dataLine.write(buffer, 0, numBytesRead);
                } else {
                    // 播放完了
                    if (looping) {
                        // 循环
                        source.reset();
                    } else {
                        // 不循环
                        break;
                    }
                }
            }
        } catch (IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        } finally {
            if (dataLine != null) {
                dataLine.drain();
                dataLine.close();
            }
        }
    }

    @Override
    public void run() {
        if (samples == null) return;
        InputStream stream = new ByteArrayInputStream(samples);
        play(stream);
    }

    // 设置为循环
    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    // 终止音乐播放
    public void stopMusic() {
        this.stopFlag = true;
        // 中断线程，让阻塞的read退出
        this.interrupt();
    }
}


