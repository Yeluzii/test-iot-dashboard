package fun.ychen.iot.service;

import fun.ychen.iot.entity.DeviceAlarm;
import fun.ychen.iot.entity.DeviceData;
import fun.ychen.iot.mapper.DeviceAlarmMapper;
import fun.ychen.iot.mapper.DeviceDataMapper;
import fun.ychen.iot.service.impl.DeviceDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@ExtendWith(MockitoExtension.class)
public class DeviceDataServiceTest {
    @InjectMocks
    private DeviceDataServiceImpl deviceDataService;

    @Mock
    private DeviceDataMapper deviceDataMapper;

    @Mock
    private DeviceAlarmMapper deviceAlarmMapper;

    @Mock
    private RedisTemplate<String,String> redisTemplate;

    @Mock
    private HashOperations<String,Object,Object> hashOperations;

    @BeforeEach
    void setUp() {
        Mockito.when(redisTemplate.opsForHash()).thenReturn(hashOperations);
    }

    @Test
    void upload_normalData_shouldInsertOnlyDeviceData() {
        DeviceData data = new DeviceData();
        data.setDeviceId(2001L);
        data.setTemperature(35.0);
        data.setHumidity(60.0);
        deviceDataService.upload(data);
        Mockito.verify(deviceDataMapper).insert(Mockito.any(DeviceData.class));
        Mockito.verify(deviceAlarmMapper, Mockito.never()).insert(Mockito.any(DeviceAlarm.class));
    }

    @Test
    void upload_highTemperature_shouldInsertAlarm() {
        DeviceData data = new DeviceData();
        data.setDeviceId(2001L);
        data.setTemperature(42.5); // 超过阈值
        deviceDataService.upload(data);
        Mockito.verify(deviceAlarmMapper).insert(
                (DeviceAlarm) Mockito.argThat(alarm ->
                        alarm instanceof DeviceAlarm &&
                                ((DeviceAlarm) alarm).getAlarmType().equals("高温") &&
                                ((DeviceAlarm) alarm).getValue().equals(42.5)
                )
        );
    }

}
