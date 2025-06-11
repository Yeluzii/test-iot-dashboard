package fun.ychen.iot.service.impl;

import fun.ychen.iot.entity.DeviceAlarm;
import fun.ychen.iot.entity.DeviceData;
import fun.ychen.iot.mapper.DeviceAlarmMapper;
import fun.ychen.iot.mapper.DeviceDataMapper;
import fun.ychen.iot.service.DeviceDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceDataServiceImpl implements DeviceDataService {
    private final DeviceDataMapper deviceDataMapper;
    private final DeviceAlarmMapper deviceAlarmMapper;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void upload(DeviceData data) {
        deviceDataMapper.insert(data);

        // 判断是否触发告警（阈值写死在此，后续可配置化）
        if (data.getTemperature() != null && data.getTemperature() > 40.0) {
            DeviceAlarm alarm = new DeviceAlarm();
            alarm.setDeviceId(data.getDeviceId());
            alarm.setAlarmType("高温");
            alarm.setValue(data.getTemperature());
            alarm.setThreshold(40.0);
            alarm.setIsHandled(0);
            deviceAlarmMapper.insert(alarm);
        }

        if (data.getSmokeLevel() != null && data.getSmokeLevel() > 100.0) {
            DeviceAlarm alarm = new DeviceAlarm();
            alarm.setDeviceId(data.getDeviceId());
            alarm.setAlarmType("烟雾超标");
            alarm.setValue(data.getSmokeLevel());
            alarm.setThreshold(100.0);
            alarm.setIsHandled(0);
            deviceAlarmMapper.insert(alarm);
        }

        // 更新设备状态到 Redis（在线状态、最后上报时间）
        String key = "device_status:" + data.getDeviceId();
        redisTemplate.opsForHash().put(key, "status", "online");
        redisTemplate.opsForHash().put(key, "last_upload", LocalDateTime.now().toString());
        // 自动过期判断离线
        redisTemplate.expire(key, Duration.ofMinutes(10));
    }
}
