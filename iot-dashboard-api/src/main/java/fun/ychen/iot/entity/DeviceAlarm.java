package fun.ychen.iot.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("device_alarm")
public class DeviceAlarm {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long deviceId;
    private String alarmType;
    private Double value;
    private Double threshold;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    // 0: 未处理 1: 已处理
    private Integer isHandled;
}
