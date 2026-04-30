package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.basic.DeviceObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class AlarmSubject extends AbstractProp{

    public AlarmSubject(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    protected List<DeviceObserver> devices = new ArrayList<>();

    public void addDevice(List<DeviceObserver> new_devices) {
        devices.addAll(new_devices);
    }

    public void removeDevice(DeviceObserver device) {
        devices.remove(device);
    }

    public abstract void notifyDevices();

    public abstract void trigger();
}

