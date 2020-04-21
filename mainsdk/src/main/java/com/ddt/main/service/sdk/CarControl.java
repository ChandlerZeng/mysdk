package com.ddt.main.service.sdk;

import android.content.Context;

import com.ddt.main.service.key.listener.IKeyListener;
import com.ddt.main.service.led.listener.ILedListener;
import com.ddt.main.service.mcu.IMcuControl;
import com.ddt.main.service.mcu.McuControl;
import com.ddt.main.service.mcu.listener.IMcuListener;
import com.ddt.main.service.mcu.listener.IServiceListener;
import com.ddt.main.service.obd.listener.IObdListener;
import com.ddt.main.service.radio.listener.IRadioStatusListener;
import com.ddt.main.service.audio.listener.IEQListener;
import com.ddt.main.service.audio.listener.IVolumeListener;

/**
 * @author zhaowanxing
 * @date 2017/8/4
 */
public class CarControl implements IMcuControl {

    private McuControl mMcuControl;

    private CarControl() {
        mMcuControl = new McuControl();
    }

    public static CarControl getInstance() {
        return Holder.CONTROL;
    }

    @Override
    public void init(Context context) {
        mMcuControl.init(context);
    }

    @Override
    public void deInit(Context context) {
        mMcuControl.deInit(context);
    }

    @Override
    public void setServiceListener(IServiceListener iServiceListener) {
        mMcuControl.setServiceListener(iServiceListener);
    }

    @Override
    public void registerRadioStatusListener(IRadioStatusListener iRadioStatusListener) {
        mMcuControl.registerRadioStatusListener(iRadioStatusListener);
    }

    @Override
    public void unregisterRadioStatusListener(IRadioStatusListener iRadioStatusListener) {
        mMcuControl.unregisterRadioStatusListener(iRadioStatusListener);
    }

    @Override
    public void registerKeyListener(IKeyListener iKeyListener) {
        mMcuControl.registerKeyListener(iKeyListener);
    }

    @Override
    public void registerSoundListener(IVolumeListener iVolumeListener) {
        mMcuControl.registerSoundListener(iVolumeListener);
    }

    @Override
    public void unregisterSoundListener(IVolumeListener iVolumeListener) {
        mMcuControl.unregisterSoundListener(iVolumeListener);
    }

    @Override
    public void unregisterKeyListener(IKeyListener iKeyListener) {
        mMcuControl.unregisterKeyListener(iKeyListener);
    }

    @Override
    public void registerMcuListener(IMcuListener iMcuListener) {
        mMcuControl.registerMcuListener(iMcuListener);
    }

    @Override
    public void unregisterMcuListener(IMcuListener iMcuListener) {
        mMcuControl.unregisterMcuListener(iMcuListener);
    }

    @Override
    public void registerEQListener(IEQListener ieqListener) {
        mMcuControl.registerEQListener(ieqListener);
    }

    @Override
    public void unregisterEQListener(IEQListener ieqListener) {
        mMcuControl.unregisterEQListener(ieqListener);
    }

    @Override
    public void registerLedListener(ILedListener iLedListener) {
        mMcuControl.registerLedListener(iLedListener);
    }

    @Override
    public void unregisterLedListener(ILedListener iLedListener) {
        mMcuControl.unregisterLedListener(iLedListener);
    }

    @Override
    public void registerObdListener(IObdListener listener) {
        mMcuControl.registerObdListener(listener);
    }

    @Override
    public void unregisterObdListener(IObdListener listener) {
        mMcuControl.unregisterObdListener(listener);
    }

    @Override
    public void getRadioStatus() {
        mMcuControl.getRadioStatus();
    }

    @Override
    public void enterRadio(boolean enter) {
        mMcuControl.enterRadio(enter);
    }

    @Override
    public void exitRadio(boolean exit) {
        mMcuControl.exitRadio(exit);
    }

    @Override
    public void setRadioChannel(int i) {
        mMcuControl.setRadioChannel(i);
    }

    @Override
    public void setRadioStatus(int i) {
        mMcuControl.setRadioStatus(i);
    }

    @Override
    public void seekUpRadio(boolean b, boolean b1) {
        mMcuControl.seekUpRadio(b, b1);
    }

    @Override
    public void seekDownRadio(boolean b, boolean b1) {
        mMcuControl.seekDownRadio(b, b1);
    }

    @Override
    public void stepUpRadio() {
        mMcuControl.stepUpRadio();
    }

    @Override
    public void stepDownRadio() {
        mMcuControl.stepDownRadio();
    }

    @Override
    public void autoScanRadio() {
        mMcuControl.autoScanRadio();
    }

    @Override
    public void startScanRadio() {
        mMcuControl.startScanRadio();
    }

    @Override
    public void stopScanRadio() {
        mMcuControl.stopScanRadio();
    }

    @Override
    public void setRadioVolumeFactor(int i) {
        mMcuControl.setRadioVolumeFactor(i);
    }

    @Override
    public void getRadioSensitivity() {
        mMcuControl.getRadioSensitivity();
    }

    @Override
    public void setRadioSensitivity(int i) {
        mMcuControl.setRadioSensitivity(i);
    }

    @Override
    public void starRadioChannel(int i) {
        mMcuControl.starRadioChannel(i);
    }

    @Override
    public void getRadioChannelStared() {
        mMcuControl.getRadioChannelStared();
    }

    @Override
    public void getRadioChannelSearched() {
        mMcuControl.getRadioChannelSearched();
    }

    @Override
    public void getStreamVolume() {
        mMcuControl.getStreamVolume();
    }

    @Override
    public void setStreamVolume(int i, int j) {
        mMcuControl.setStreamVolume(i, j);
    }

    @Override
    public void studySteeringWheel(int i) {
        mMcuControl.studySteeringWheel(i);
    }

    @Override
    public void requestMcuVersion() {
        mMcuControl.requestMcuVersion();
    }

    @Override
    public void getSoundEffect() {
        mMcuControl.getSoundEffect();
    }

    @Override
    public void setSoundEffect(int i) {
        mMcuControl.setSoundEffect(i);
    }

    @Override
    public void setSoundGain(int i, int i1) {
        mMcuControl.setSoundGain(i, i1);
    }

    @Override
    public void getSoundIF() {
        mMcuControl.getSoundIF();
    }

    @Override
    public void getSoundGain() {
        mMcuControl.getSoundGain();
    }

    @Override
    public void getSoundBass() {
        mMcuControl.getSoundBass();
    }

    @Override
    public void getSoundLoudness() {
        mMcuControl.getSoundLoudness();
    }

    @Override
    public void setSoundLoudness(int i) {
        mMcuControl.setSoundLoudness(i);
    }

    @Override
    public void resetSoundEffect() {
        mMcuControl.resetSoundEffect();
    }

    @Override
    public void setSoundField(int i, int i1) {
        mMcuControl.setSoundField(i, i1);
    }

    @Override
    public void getSoundField() {
        mMcuControl.getSoundField();
    }

    @Override
    public void resetSoundField() {
        mMcuControl.resetSoundField();
    }

    @Override
    public void getLedBrightness() {
        mMcuControl.getLedBrightness();
    }

    @Override
    public void setLedBrightness(int i) {
        mMcuControl.setLedBrightness(i);
    }

    @Override
    public void mute(boolean b) {
        mMcuControl.mute(b);
    }

    @Override
    public void switchToAux() {
        mMcuControl.switchToAux();
    }

    @Override
    public void switchOutAux() {
        mMcuControl.switchOutAux();
    }

    @Override
    public void getObdInfo() {
        mMcuControl.getObdInfo();
    }

    @Override
    public void enterMcuBootloadMode() {
        mMcuControl.enterMcuBootloadMode();
    }

    @Override
    public int mcuUpgrade(String path, String fileName) {
        return mMcuControl.mcuUpgrade(path, fileName);
    }

    @Override
    public void mix(boolean mix) {
        mMcuControl.mix(mix);
    }

    @Override
    public void resetMcu() {
        mMcuControl.resetMcu();
    }

    @Override
    public void setSoundStream(int streamType) {
        mMcuControl.setSoundStream(streamType);
    }

    private static class Holder {
        private static final CarControl CONTROL = new CarControl();
    }
}
