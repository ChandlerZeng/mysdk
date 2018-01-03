package com.ddt.main.service.audio.listener;

/**
 * @author zhaowanxing
 * @date 2017/8/30
 */
public interface IVolumeListener {
    void onVolumeChanged(int volume, int streamType);
}
