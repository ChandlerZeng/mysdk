package com.ddt.main.service.radio.listener;

public interface IRadioStatusListener {

	void onRadioStatusGet(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioStatusSet(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioChannelSet(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioChannelSeek(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioChannelScan(int status, int channel, int state, int volumeFactor, int stereo);
	void onRadioSensitivity(int sensitivity);
	void onRadioChannelStared(byte[] channels);
	void onRadioChannelSearched(byte[] channels);
}
