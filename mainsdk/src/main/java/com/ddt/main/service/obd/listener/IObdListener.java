package com.ddt.main.service.obd.listener;

/**
 * Created by zhaowanxing on 2017/9/13.
 */

public interface IObdListener {

    void onReceive(byte[] data);
}
