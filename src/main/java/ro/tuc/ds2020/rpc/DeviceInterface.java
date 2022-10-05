package ro.tuc.ds2020.rpc;

import java.util.Date;
import java.util.UUID;

public interface DeviceInterface {

    double[][] historicalEnergyConsumption(Date deviceDate, UUID idClient, int nrDays);

}
