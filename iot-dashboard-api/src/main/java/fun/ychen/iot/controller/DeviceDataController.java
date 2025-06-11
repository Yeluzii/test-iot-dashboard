package fun.ychen.iot.controller;

import fun.ychen.iot.common.R;
import fun.ychen.iot.entity.DeviceData;
import fun.ychen.iot.service.DeviceDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/device-data")
@RequiredArgsConstructor
@Tag(name = "设备数据上报")
public class DeviceDataController {
    private final DeviceDataService deviceDataService;
    @PostMapping("/upload")
    @Operation(summary = "设备数据上报", description = "设备提交实时数据，系统自动判断是否触发告警")
    public R<String> upload(@RequestBody DeviceData data) {
        deviceDataService.upload(data);
        return R.success("数据上传成功");
    }
}
