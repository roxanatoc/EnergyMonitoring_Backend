package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MonitoredValueDTO;
import ro.tuc.ds2020.entities.MonitoredValue;

public class MonitoredValueBuilder {

    private MonitoredValueBuilder() {
    }
    public static MonitoredValueDTO toMonitoredValueDTO(MonitoredValue monitoredValue) {
        return new MonitoredValueDTO(monitoredValue.getId(), monitoredValue.getTimestamp(), monitoredValue.getValue());
    }

    public static MonitoredValue toEntity(MonitoredValueDTO monitoredValueDTO) {
        return new MonitoredValue(monitoredValueDTO.getTimestamp(),
                monitoredValueDTO.getValue());
    }
}
