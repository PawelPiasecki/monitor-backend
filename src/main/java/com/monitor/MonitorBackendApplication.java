package com.monitor;

import com.monitor.cache.SensorHistoryCache;
import com.monitor.model.Room;
import com.monitor.model.Sensor;
import com.monitor.model.User;
import com.monitor.repository.RoomRepository;
import com.monitor.repository.SensorRepository;
import com.monitor.repository.SystemRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import com.monitor.model.User;

import javax.websocket.MessageHandler;
import java.security.SecureRandom;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan(basePackages = "com.monitor")
@Configuration
public class MonitorBackendApplication {

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    SensorHistoryCache sensorHistoryCache;

    @Autowired
    SystemRepository systemRepository;

    @Autowired
    RoomRepository roomRepository;

    public static void main(String[] args) {
        SpringApplication.run(MonitorBackendApplication.class, args);
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }


    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("tcp://localhost:1883", "pahomqttpublish1",
                        "#");

        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        adapter.addTopic();
        return adapter;
    }


    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {


            public void handleMessage(Message<?> message) throws org.springframework.messaging.MessagingException {
                System.out.println(message.getPayload());
                if (message.getHeaders().containsValue("configuration")) {
                    try {
                        JSONObject obj = new JSONObject(message.getPayload().toString());
                        JSONArray arr = obj.getJSONArray("sensor");

                        String topic = arr.getJSONObject(1).getString("state_topic");
                        StringTokenizer stringTokenizer = new StringTokenizer(topic, "/");

                        String systemName = (String) stringTokenizer.nextElement();
                        System.out.println(systemName + " this is system name");

                        com.monitor.model.System system;

                        if (systemRepository.findByName(systemName).isEmpty()) {
                            Room room = new Room();
                            room.setName(systemName + "default");

                            system = new com.monitor.model.System();
                            system.setName(systemName);
                            system.setConfigPath(systemName);
                            room.setSystem(system);
                            system.addRoom(room);
                            System.out.println(system.getRooms().get(0).getName());
                            systemRepository.save(system);
                            roomRepository.save(room);

                        } else {
                            system = systemRepository.findByName(systemName).get(0);
                        }


                        List<Sensor> sensorList = (List<Sensor>) sensorRepository.findAll();
                        sensorList.forEach(s -> s.setActive(false));


                        Room room = roomRepository.findByName(systemName + "default").get(0);
                        for (int i = 0; i < arr.length(); i++) {
                            String state_topic = arr.getJSONObject(i).getString("state_topic");


                            Sensor sensor = new Sensor();
                            sensor.setActive(true);
                            sensor.setName(arr.getJSONObject(i).getString("name"));
                            sensor.setSocketUrl(state_topic);
                            sensor.setSystem(system);
                            sensor.setRoom(room);
                            sensorRepository.save(sensor);

                        }


                        systemRepository.save(system);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else

                {
                    System.out.println(message.getHeaders().get("mqtt_topic"));
                    sensorHistoryCache.addHistory((String) message.getHeaders().get("mqtt_topic"), message.getPayload().toString());
                }

            }

        }

                ;

    }

    @Bean
    public SecureRandom secureRandom() {
        return new SecureRandom();
    }

    @Bean
    public SensorHistoryCache sensorHistoryCache() {
        return new SensorHistoryCache();
    }
}
