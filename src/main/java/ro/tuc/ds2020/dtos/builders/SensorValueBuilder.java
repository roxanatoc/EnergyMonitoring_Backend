package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.entities.MonitoredValue;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.SensorValue;

public class SensorValueBuilder {

    public static SensorValue toDTO(MonitoredValue monitoredValue) {
        return null;
    }

    public static MonitoredValue toEntity(SensorValue sensorValue, Sensor sensor) {
        MonitoredValue monitoredValue = new MonitoredValue();
        monitoredValue.setValue(sensorValue.getMeasurement_value());
        monitoredValue.setTimestamp(sensorValue.getTimestamp());
        return monitoredValue;
    }

    public static SensorValue clone(SensorValue sensorValue) {
        SensorValue value = new SensorValue();
        value.setMeasurement_value(sensorValue.getMeasurement_value());
        value.setTimestamp(sensorValue.getTimestamp());
        value.setSensor_id(sensorValue.getSensor_id());
        return value;
    }
}
