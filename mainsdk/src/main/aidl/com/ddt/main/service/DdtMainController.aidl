package com.ddt.main.service;
import com.ddt.main.service.DdtMainCallback;

interface DdtMainController{

	void registerMcuCallback(DdtMainCallback callback);

	void unregisterMcuCallback(DdtMainCallback callback);
	
	void getRadioStatus();
	
	void enterRadio(boolean enter);

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