package fun.ychen.iot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.ychen.iot.entity.DeviceData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceDataMapper extends BaseMapper<DeviceData> {
}
