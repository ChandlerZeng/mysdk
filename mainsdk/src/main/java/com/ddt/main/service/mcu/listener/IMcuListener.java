package com.ddt.main.service.mcu.listener;

public interface IMcuListener {

    void onGetMcuVersion(String version);

    void onMcuUpgradeStatus(int status, int value);
}