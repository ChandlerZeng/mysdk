package com.ddt.main.service.mcu;

import com.ddt.main.service.DdtMainCallback;
import com.ddt.main.service.config.MainIntent;
import com.ddt.main.service.DdtMainController;
import com.ddt.main.service.key.listener.IKeyListener;
import com.ddt.main.service.led.listener.ILedListener;
import com.ddt.main.service.mcu.listener.IMcuListener;
import com.ddt.main.service.mcu.listener.IServiceListener;
import com.ddt.main.service.obd.listener.IObdListener;
import com.ddt.main.service.radio.listener.IRadioStatusListener;
import com.ddt.main.service.audio.listener.IEQListener;
import com.ddt.main.service.audio.listener.IVolumeListener;
import com.ddt.main.service.util.IntentCompat;

import java.util.List;
import java.util.ArrayList;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

public class McuControl implements IMcuControl {

    private final String TAG = getClass().getSimpleName();

    private DdtMainController mMainController = null;
    private IServiceListener mServiceListener;
    private List<IKeyListener> mKeyListeners = new ArrayList<IKeyListener>();
    private Object mKeyLock = new Object();
    private List<IVolumeListener> mSoundListeners = new ArrayList<IVolumeListener>();
    private Object mSoundLock = new Object();
    private List<IRadioStatusListener> mRadioStatusListeners = new ArrayList<IRadioStatusListener>();
    private Object mRadioLock = new Object();
    private List<IEQListener> mEQListeners = new ArrayList<IEQListener>();
    private Object mEQLock = new Object();
    private List<IMcuListener> mMcuListeners = new ArrayList<IMcuListener>();
    private Object mMcuLock = new Object();
    private List<ILedListener> mLedListeners = new ArrayList<ILedListener>();
    private Object mLedLock = new Object();
    private List<IObdListener> mObdListeners = new ArrayList<IObdListener>();
    private Object mObdLock = new Object();

    private static final int MSG_SERVICE_SUCCEED = 0;
    private static final int MSG_SERVICE_DIED = 1;

    private DdtMainCallback.Stub mMcuCallback = new DdtMainCallback.Stub() {

        @Override
        public void onRadioStatusGet(int status, int channel, int state, int volumeFactor, int stereo) {
            synchronized (mRadioLock) {
                for (IRadioStatusListener listener : mRadioStatusListeners) {
                    listener.onRadioStatusGet(status, channel, state, volumeFactor, stereo);
                }
            }
        }

        @Override
        public void onRadioStatusSet(int status, int channel, int state, int volumeFactor, int stereo) {
            synchronized (mRadioLock) {
                for (IRadioStatusListener listener : mRadioStatusListeners) {
                    listener.onRadioStatusSet(status, channel, state, volumeFactor, stereo);
                }
            }
        }

        @Override
        public void onRadioChannelSet(int status, int channel, int state, int volumeFactor, int stereo) {
            synchronized (mRadioLock) {
                for (IRadioStatusListener listener : mRadioStatusListeners) {
                    listener.onRadioChannelSet(status, channel, state, volumeFactor, stereo);
                }
            }
        }

        @Override
        public void onRadioChannelSeek(int status, int channel, int state, int volumeFactor, int stereo) {
            synchronized (mRadioLock) {
                for (IRadioStatusListener listener : mRadioStatusListeners) {
                    listener.onRadioChannelSeek(status, channel, state, volumeFactor, stereo);
                }
            }
        }

        @Override
        public void onRadioChannelScan(int status, int channel, int state, int volumeFactor, int stereo) {
            synchronized (mRadioLock) {
                for (IRadioStatusListener listener : mRadioStatusListeners) {
                    listener.onRadioChannelScan(status, channel, state, volumeFactor, stereo);
                }
            }
        }

        @Override
        public void onRadioSensitivity(int sensitivity) {
            synchronized (mRadioLock) {
                for (IRadioStatusListener listener : mRadioStatusListeners) {
                    listener.onRadioSensitivity(sensitivity);
                }
            }
        }

        @Override
        public void onRadioChannelStared(byte[] channels) {
            synchronized (mRadioLock) {
                for (IRadioStatusListener listener : mRadioStatusListeners) {
                    listener.onRadioChannelStared(channels);
                }
            }
        }

        @Override
        public void onRadioChannelSearched(byte[] channels) {
            synchronized (mRadioLock) {
                for (IRadioStatusListener listener : mRadioStatusListeners) {
                    listener.onRadioChannelSearched(channels);
                }
            }
        }

        @Override
        public void onSoundGainGet(byte[] gains) {
            synchronized (mEQLock) {
                for (IEQListener listener : mEQListeners) {
                    listener.onSoundGainGet(gains);
                }
            }
        }

        @Override
        public void onSoundBassSet(int bass) throws RemoteException {
            synchronized (mEQLock) {
                for (IEQListener listener : mEQListeners) {
                    listener.onSoundBassSet(bass);
                }
            }
        }

        @Override
        public void onSoundVolume(int volume, int streamType) throws RemoteException {
            synchronized (mSoundLock) {
                for (IVolumeListener listener : mSoundListeners) {
                    listener.onVolumeChanged(volume, streamType);
                }
            }
        }

        @Override
        public void onSoundEffectGet(int effect) throws RemoteException {
            synchronized (mEQLock) {
                for (IEQListener listener : mEQListeners) {
                    listener.onSoundEffectGet(effect);
                }
            }
        }

        @Override
        public void onSoundBassGet(int bass) throws RemoteException {
            synchronized (mEQLock) {
                for (IEQListener listener : mEQListeners) {
                    listener.onSoundBassGet(bass);
                }
            }
        }

        @Override
        public void onSoundGainSet(int index, int gain) throws RemoteException {
            synchronized (mEQLock) {
                for (IEQListener listener : mEQListeners) {
                    listener.onSoundGainSet(index, gain);
                }
            }
        }

        @Override
        public void onSoundLoudnessGet(int loudness) throws RemoteException {
            synchronized (mEQLock) {
                for (IEQListener listener : mEQListeners) {
                    listener.onSoundLoudnessGet(loudness);
                }
            }
        }

        @Override
        public void onSoundLoudnessSet(int loudness) throws RemoteException {
            synchronized (mEQLock) {
                for (IEQListener listener : mEQListeners) {
                    listener.onSoundLoudnessSet(loudness);
                }
            }
        }

        @Override
        public void onSoundFieldGet(int x, int y) throws RemoteException {
            synchronized (mEQLock) {
                for (IEQListener listener : mEQListeners) {
                    listener.onSoundFieldGet(x, y);
                }
            }
        }

        @Override
        public void onSoundFieldSet(int x, int y) throws RemoteException {
            synchronized (mEQLock) {
                for (IEQListener listener : mEQListeners) {
                    listener.onSoundFieldSet(x, y);
                }
            }
        }

        @Override
        public void onSteeringWheelStudied(int status) {
            synchronized (mKeyLock) {
                for (IKeyListener listener : mKeyListeners) {
                    listener.onSteeringWheelStudied(status);
                }
            }
        }

        @Override
        public void onGetMcuVersion(String version) {
            synchronized (mMcuLock) {
                for (IMcuListener listener : mMcuListeners) {
                    listener.onGetMcuVersion(version);
                }
            }
        }

        @Override
        public void onMcuUpgradeStatus(int status, int value) throws RemoteException {
            synchronized (mMcuLock) {
                for (IMcuListener listener : mMcuListeners) {
                    listener.onMcuUpgradeStatus(status, value);
                }
            }
        }

        @Override
        public void onGetLedBrightness(int brightness) {
            synchronized (mLedLock) {
                for (ILedListener listener : mLedListeners) {
                    listener.onGetLedBrightness(brightness);
                }
            }
        }

        @Override
        public void onReceiveObd(byte[] data) throws RemoteException {
            synchronized (mObdLock) {
                for (IObdListener listener : mObdListeners) {
                    listener.onReceive(data);
                }
            }
        }
    };

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case MSG_SERVICE_SUCCEED:
                    if (mServiceListener != null) {
                        mServiceListener.onServiceConnected();
                    }
                    break;
                case MSG_SERVICE_DIED:
                    if (mServiceListener != null) {
                        mServiceListener.onServiceDied();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private class MyServiceDeathHandler implements IBinder.DeathRecipient {

        public MyServiceDeathHandler() {

        }

        @Override
        public void binderDied() {
            // release resource when host process died.
            Log.e(TAG, "binderDied");
            mHandler.sendEmptyMessage(MSG_SERVICE_DIED);
        }
    }

    private MyServiceDeathHandler mServiceDeathHandler = new MyServiceDeathHandler();

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMainController = DdtMainController.Stub.asInterface(service);
            Log.i(TAG, "onServiceConnected " + name);
            try {
                mMainController.asBinder().linkToDeath(mServiceDeathHandler, 0);
                mMainController.registerMcuCallback(mMcuCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            mHandler.sendEmptyMessage(MSG_SERVICE_SUCCEED);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected " + name);
            mMainController = null;
        }
    };

    @Override
    public void setServiceListener(IServiceListener listener) {
        mServiceListener = listener;
    }

    @Override
    public void registerRadioStatusListener(IRadioStatusListener listener) {
        synchronized (mRadioLock) {
            if (!mRadioStatusListeners.contains(listener)) {
                mRadioStatusListeners.add(listener);
            }
        }
    }

    @Override
    public void unregisterRadioStatusListener(IRadioStatusListener listener) {
        synchronized (mRadioLock) {
            mRadioStatusListeners.remove(listener);
        }
    }

    @Override
    public void registerKeyListener(IKeyListener listener) {
        synchronized (mKeyLock) {
            if (!mKeyListeners.contains(listener)) {
                mKeyListeners.add(listener);
            }
        }
    }

    @Override
    public void unregisterKeyListener(IKeyListener listener) {
        synchronized (mKeyLock) {
            mKeyListeners.remove(listener);
        }
    }

    @Override
    public void registerSoundListener(IVolumeListener listener) {
        synchronized (mSoundLock) {
            if (!mSoundListeners.contains(listener)) {
                mSoundListeners.add(listener);
            }
        }
    }

    @Override
    public void unregisterSoundListener(IVolumeListener listener) {
        synchronized (mSoundLock) {
            mSoundListeners.remove(listener);
        }
    }

    @Override
    public void registerMcuListener(IMcuListener listener) {
        synchronized (mMcuLock) {
            if (!mMcuListeners.contains(listener)) {
                mMcuListeners.add(listener);
            }
        }
    }

    @Override
    public void unregisterMcuListener(IMcuListener listener) {
        synchronized (mMcuLock) {
            mMcuListeners.remove(listener);
        }
    }

    @Override
    public void registerEQListener(IEQListener listener) {
        synchronized (mEQLock) {
            if (!mEQListeners.contains(listener)) {
                mEQListeners.add(listener);
            }
        }
    }

    @Override
    public void unregisterEQListener(IEQListener listener) {
        synchronized (mEQLock) {
            mEQListeners.remove(listener);
        }
    }

    @Override
    public void registerLedListener(ILedListener listener) {
        synchronized (mLedLock) {
            if (!mLedListeners.contains(listener)) {
                mLedListeners.add(listener);
            }
        }
    }

    @Override
    public void unregisterLedListener(ILedListener listener) {
        synchronized (mLedLock) {
            mLedListeners.remove(listener);
        }
    }

    @Override
    public void registerObdListener(IObdListener listener) {
        synchronized (mObdLock) {
            if (!mObdListeners.contains(listener)) {
                mObdListeners.add(listener);
            }
        }
    }

    @Override
    public void unregisterObdListener(IObdListener listener) {
        synchronized (mObdLock) {
            mObdListeners.remove(listener);
        }
    }

    @Override
    public void init(Context context) {
        Log.i("McuControl", "init " + context);
        Intent intent = new Intent(MainIntent.INTENT_MAIN_SERVICE);
        Intent explicitIntent = new Intent(IntentCompat.getExplicitIntent(context, intent));
        context.bindService(explicitIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void deInit(Context context) {
        if (mMainController != null) {
            try {
                mMainController.unregisterMcuCallback(mMcuCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            mMainController.asBinder().unlinkToDeath(mServiceDeathHandler, 0);

            try {
                context.unbindService(mServiceConnection);
            } catch (IllegalArgumentException e) {

            }
            mMainController = null;
        }
    }

    @Override
    public void getRadioStatus() {
        if (mMainController != null) {
            try {
                mMainController.getRadioStatus();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int mcuUpgrade(String path, String fileName) {
        if (mMainController != null) {
            try {
                mMainController.mcuUpgrade(path, fileName);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public void mix(boolean mix) {
        if (mMainController != null) {
            try {
                mMainController.mix(mix);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void resetMcu() {
        if (mMainController != null) {
            try {
                mMainController.resetMcu();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setSoundStream(int streamType) {
        if (mMainController != null) {
            try {
                mMainController.setSoundStream(streamType);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void enterRadio(boolean enter) {
        if (mMainController != null) {
            try {
                mMainController.enterRadio(enter);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void exitRadio(boolean exit) {
        if (mMainController != null) {
            try {
                mMainController.exitRadio(exit);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setRadioChannel(int channel) {
        if (mMainController != null) {
            try {
                mMainController.setRadioChannel(channel);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setRadioStatus(int status) {
        if (mMainController != null) {
            try {
                mMainController.setRadioStatus(status);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void seekUpRadio(boolean stereo, boolean loc) {
        if (mMainController != null) {
            try {
                mMainController.seekUpRadio(stereo, loc);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void seekDownRadio(boolean stereo, boolean loc) {
        if (mMainController != null) {
            try {
                mMainController.seekDownRadio(stereo, loc);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stepUpRadio() {
        if (mMainController != null) {
            try {
                mMainController.stepUpRadio();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stepDownRadio() {
        if (mMainController != null) {
            try {
                mMainController.stepDownRadio();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void autoScanRadio() {
        if (mMainController != null) {
            try {
                mMainController.autoScanRadio();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void startScanRadio() {
        if (mMainController != null) {
            try {
                mMainController.startScanRadio();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopScanRadio() {
        if (mMainController != null) {
            try {
                mMainController.stopScanRadio();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setRadioVolumeFactor(int factor) {
        if (mMainController != null) {
            try {
                mMainController.setRadioVolumeFactor(factor);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getRadioSensitivity() {
        if (mMainController != null) {
            try {
                mMainController.getRadioSensitivity();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setRadioSensitivity(int sensitivity) {
        if (mMainController != null) {
            try {
                mMainController.setRadioSensitivity(sensitivity);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void starRadioChannel(int index) {
        if (mMainController != null) {
            try {
                mMainController.starRadioChannel(index);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getRadioChannelStared() {
        if (mMainController != null) {
            try {
                mMainController.getRadioChannelStared();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getRadioChannelSearched() {
        if (mMainController != null) {
            try {
                mMainController.getRadioChannelSearched();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getStreamVolume() {
        if (mMainController != null) {
            try {
                mMainController.getStreamVolume();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setStreamVolume(int volume, int streamType) {
        if (mMainController != null) {
            try {
                mMainController.setStreamVolume(volume, streamType);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void studySteeringWheel(int key) {
        if (mMainController != null) {
            try {
                mMainController.studySteeringWheel(key);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void requestMcuVersion() {
        if (mMainController != null) {
            try {
                mMainController.requestMcuVersion();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getSoundEffect() {
        if (mMainController != null) {
            try {
                mMainController.getSoundEffect();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setSoundEffect(int effect) {
        if (mMainController != null) {
            try {
                mMainController.setSoundEffect(effect);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setSoundGain(int index, int gain) {
        if (mMainController != null) {
            try {
                mMainController.setSoundGain(index, gain);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getSoundIF() {
        if (mMainController != null) {
            try {
                mMainController.getSoundIF();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getSoundGain() {
        if (mMainController != null) {
            try {
                mMainController.getSoundGain();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getSoundBass() {
        if (mMainController != null) {
            try {
                mMainController.getSoundBass();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getSoundLoudness() {
        if (mMainController != null) {
            try {
                mMainController.getSoundLoudness();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setSoundLoudness(int loudness) {
        if (mMainController != null) {
            try {
                mMainController.setSoundLoudness(loudness);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void resetSoundEffect() {
        if (mMainController != null) {
            try {
                mMainController.resetSoundEffect();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setSoundField(int x, int y) {
        if (mMainController != null) {
            try {
                mMainController.setSoundField(x, y);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getSoundField() {
        if (mMainController != null) {
            try {
                mMainController.getSoundField();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void resetSoundField() {
        if (mMainController != null) {
            try {
                mMainController.resetSoundField();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getLedBrightness() {
        if (mMainController != null) {
            try {
                mMainController.getLedBrightness();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setLedBrightness(int brightness) {
        if (mMainController != null) {
            try {
                mMainController.setLedBrightness(brightness);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mute(boolean on) {
        if (mMainController != null) {
            try {
                mMainController.mute(on);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void switchToAux() {
        if (mMainController != null) {
            try {
                mMainController.switchToAux();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void switchOutAux() {
        if (mMainController != null) {
            try {
                mMainController.switchOutAux();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getObdInfo() {
        if (mMainController != null) {
            try {
                mMainController.getObdInfo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void enterMcuBootloadMode() {
        if (mMainController != null) {
            try {
                mMainController.enterMcuBootloadMode();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}