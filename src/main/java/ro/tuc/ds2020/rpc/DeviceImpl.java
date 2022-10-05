package ro.tuc.ds2020.rpc;

import ro.tuc.ds2020.dtos.MonitoredValueDTO;
import ro.tuc.ds2020.services.MonitoredValueService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DeviceImpl implements DeviceInterface {

    private final MonitoredValueService monitoredValueService;

    public DeviceImpl(MonitoredValueService monitoredValueService) {
        this.monitoredValueService = monitoredValueService;
    }


    @Override
    public double[][] historicalEnergyConsumption(Date consumptionDate, UUID idClient, int nrDays) {

        List<MonitoredValueDTO> monitoredValueDTOList = monitoredValueService.findMonitoredValues();

        consumptionDate.setHours(0);
        consumptionDate.setHours(consumptionDate.getHours() - 1);
        Date newData = new Date(consumptionDate.getTime());
        Date endData = new Date(newData.getTime());
        endData.setDate(endData.getDate() - nrDays);

        double[][] measurementValues = new double[nrDays][24];
        int days = 0;
        while(newData.getTime() > endData.getTime()){
            for (int j = 23; j >= 0; j--) {
                for(MonitoredValueDTO m: monitoredValueDTOList){
                    if((m.getTimestamp().getYear() == newData.getYear()) && (m.getTimestamp().getMonth() == newData.getMonth()) &&
                            (m.getTimestamp().getDay() == newData.getDay()) && (m.getTimestamp().getHours() == newData.getHours()))
                    {
                        measurementValues[days][j] += m.getValue();
                    }

                }
                newData.setHours(newData.getHours() - 1);
            }
            days++;
        }

        return measurementValues;
    }
}
