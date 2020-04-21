package com.ddt.main.service.sdk;

import android.content.Context;

import com.ddt.main.service.sdk.CarStatus.Device;
import com.ddt.main.service.sdk.CarStatus.Navigation;
import com.ddt.main.service.sdk.CarStatus.Radio;
import com.ddt.main.service.sdk.CarStatus.Status;
import com.ddt.main.service.sdk.CarStatus.Call;
import com.ddt.main.service.sdk.CarStatus.SteeringWheel;
import com.ddt.main.service.sdk.CarStatus.Peripherals;

public class CarDataManager {
    public static final Device DEVICE = new Device();
    public static final Radio RADIO = new Radio();
    public static final Call CALL = new Call();
    public static final SteeringWheel STEERING_WHEEL = new SteeringWheel();
    public static final Peripherals PERIPHERALS = new Peripherals();
    public static final Navigation NAVIGATION = new Navigation();

    private CarDataManager() {
    }

    public static DataParams getDataParams(Context context, Status status) {
        return new DataParams(context, status);
    }
}
