package com.ddt.main.service.audio.listener;

public interface IEQListener {

    void onSoundEffectGet(int effect);
    void onSoundGainGet(byte[] gains);
    void onSoundBassGet(int bass);
    void onSoundBassSet(int bass);
    void onSoundGainSet(int index, int gain);
    void onSoundLoudnessGet(int loudness);
    void onSoundLoudnessSet(int loudness);
    void onSoundFieldGet(int x, int y);
    void onSoundFieldSet(int x, int y);
}