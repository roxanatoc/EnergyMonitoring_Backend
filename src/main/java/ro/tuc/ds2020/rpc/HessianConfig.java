package ro.tuc.ds2020.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;
import ro.tuc.ds2020.services.MonitoredValueService;

@Configuration
public class HessianConfig {

    private final MonitoredValueService monitoredValueService;

    public HessianConfig(MonitoredValueService monitoredValueService) {
        this.monitoredValueService = monitoredValueService;
    }

    @Bean(name = "/hessian")
    RemoteExporter sayHelloServiceHessian() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(new DeviceImpl(monitoredValueService));
        exporter.setServiceInterface(DeviceInterface.class);
        return exporter;
    }
}
