package fun.ychen.iot.controller;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeviceDataControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    void uploadDeviceData_shouldReturnSuccess() throws Exception {
        String json = """
                {
                    "deviceId": 2001,
                    "temperature": 45.5,
                    "humidity": 60.0
                }
                """;

        mockMvc.perform(post("/api/device-data/upload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("OK"));
    }

}