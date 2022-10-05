package ro.tuc.ds2020.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.MonitoredValue;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.SensorValue;
import ro.tuc.ds2020.repositories.MonitoredValueRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class MonitoredValueListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private final MonitoredValueRepository monitoredValueRepository;

    public MonitoredValueListener(MonitoredValueRepository monitoredValueRepository) {
        this.monitoredValueRepository = monitoredValueRepository;
    }

    @RabbitListener(queues = MQConfig.queue)
    @Transactional
    public void consumeMessage(SensorValue message) {
        MonitoredValue[] monitoredValues = monitoredValueRepository.findAll().toArray(new MonitoredValue[0]);
        System.out.println("Received from the queue : " + message.toString());
        Sensor sensor = new Sensor();
        sensor.setId(UUID.fromString("2e42c6f0-bda2-4e7d-977e-10f52548955c"));
        sensor.setDescription("Fire sensor");
        sensor.setMaxValue(1000000);
        MonitoredValue monitoredValue = new MonitoredValue(message.getTimestamp(), message.getMeasurement_value(), sensor.getId());
        if((message.getMeasurement_value() - monitoredValues[monitoredValues.length-1].getValue()) > sensor.getMaxValue())
        {
            System.out.println("Maximum value exceeded!");
            simpMessagingTemplate.convertAndSend("/topic/socket/monitoredValue/values", "Maximum value exceeded!");
        }
        else{
            monitoredValueRepository.save(monitoredValue);
        }
    }
}

