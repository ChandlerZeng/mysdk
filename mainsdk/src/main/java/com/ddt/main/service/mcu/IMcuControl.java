package com.ddt.main.service.mcu;

import android.content.Context;

import com.ddt.main.service.key.listener.IKeyListener;
import com.ddt.main.service.led.listener.ILedListener;
import com.ddt.main.service.mcu.listener.IMcuListener;
import com.ddt.main.service.mcu.listener.IServiceListener;
import com.ddt.main.service.obd.listener.IObdListener;
import com.ddt.main.service.radio.listener.IRadioStatusListener;
import com.ddt.main.service.audio.listener.IEQListener;
import com.ddt.main.service.audio.listener.IVolumeListener;

public interface IMcuControl {

    void init(Context context);

    void deInit(Context context);

    void setServiceListener(IServiceListener listener);

    void registerRadioStatusListener(IRadioStatusListener listener);

    void unregisterRadioStatusListener(IRadioStatusListener listener);

    void registerKeyListener(IKeyListener listener);

    void unregisterKeyListener(IKeyListener listener);

    void registerSoundListener(IVolumeListener listener);

    void unregisterSoundListener(IVolumeListener listener);

    void registerMcuListener(IMcuListener listener);

    void unregisterMcuListener(IMcuListener listener);

    void registerEQListener(IEQListener listener);

    void unregisterEQListener(IEQListener listener);

    void registerLedListener(ILedListener listener);

    void unregisterLedListener(ILedListener listener);

    void registerObdListener(IObdListener listener);

    void unregisterObdListener(IObdListener listener);

    /**
     * Get the status of radio
     */
    void getRadioStatus();

    /**
     * Enter the radio channel
     * @param enter whether to enter the radio application
     */
    void enterRadio(boolean enter);

    /**
     * Exit the radio channel
     * @param exit whether to exit the radio application.
     */
    void exitRadio(boolean exit);

    void setRadioChannel(int channel);

    void setRadioStatus(int status);

    void seekUpRadio(boolean stereo, boolean loc);

    void seekDownRadio(boolean stereo, boolean loc);

    void stepUpRadio();

    void stepDownRadio();

    void autoScanRadio();

    void startScanRadio();

    void stopScanRadio();

    void setRadioVolumeFactor(int factor);

    void getRadioSensitivity();

    void setRadioSensitivity(int sensitivity);

    void starRadioChannel(int index);

    void getRadioChannelStared();

    void getRadioChannelSearched();

    void getStreamVolume();

    void setStreamVolume(int volume, int streamType);

    void studySteeringWheel(int key);

    void requestMcuVersion();

    void getSoundEffect();

    void setSoundEffect(int effect);

    void setSoundGain(int index, int gain);

    void getSoundIF();

    void getSoundGain();

    void getSoundBass();

    void getSoundLoudness();

    void setSoundLoudness(int loudness);

    void resetSoundEffect();

    void setSoundField(int x, int y);

    void getSoundField();

    void resetSoundField();

    void getLedBrightness();

    void setLedBrightness(int brightness);

    void mute(boolean on);

    void switchToAux();

    void switchOutAux();

    void getObdInfo();

    void enterMcuBootloadMode();

    int mcuUpgrade(String path, String fileName);

    void mix(boolean mix);

    void resetMcu();

    void setSoundStream(int streamType);
}