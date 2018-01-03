package com.ddt.main.service;

interface DdtMainCallback{
    void onSoundVolume(int volume, int streamType);
    void onSoundEffectGet(int effect);
	void onSoundGainGet(in byte[] gains);
	void onSoundBassGet(int bass);
	void onSoundBassSet(int bass);
	void onSoundGainSet(int index, int gain);
	void onSoundLoudnessGet(int loudness);
	void onSoundLoudnessSet(int loudness);
	void onSoundFieldGet(int x, int y);
	void onSoundFieldSet(int x, int y);

	void onRadioStatusGet(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioStatusSet(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioChannelSet(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioChannelSeek(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioChannelScan(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioSensitivity(int sensitivity);
	void onRadioChannelStared(in byte[] channels);
	void onRadioChannelSearched(in byte[] channels);

	void onSteeringWheelStudied(int status);

	void onGetMcuVersion(String version);

	void onGetLedBrightness(int brightness);

	void onReceiveObd(in byte[] data);

	void onMcuUpgradeStatus(int status, int value);
}