package fun.ychen.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.ychen.iot.entity.DeviceAlarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceAlarmMapper extends BaseMapper<DeviceAlarm> {
}
