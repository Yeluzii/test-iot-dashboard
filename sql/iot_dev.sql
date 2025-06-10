/*
 Navicat Premium Dump SQL

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80400 (8.4.0)
 Source Host           : localhost:3306
 Source Schema         : iot_dev

 Target Server Type    : MySQL
 Target Server Version : 80400 (8.4.0)
 File Encoding         : 65001

 Date: 10/06/2025 20:16:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` bigint NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `longitude` decimal(10, 6) NULL DEFAULT NULL,
  `latitude` decimal(10, 6) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (2001, '设备A', 'temp_sensor', 1, 116.407396, 39.904200, '2025-06-10 20:15:22');
INSERT INTO `device` VALUES (2002, '设备B', 'smoke_detector', 1, 121.473701, 31.230416, '2025-06-10 20:15:22');

-- ----------------------------
-- Table structure for device_alarm
-- ----------------------------
DROP TABLE IF EXISTS `device_alarm`;
CREATE TABLE `device_alarm`  (
  `id` bigint NOT NULL,
  `device_id` bigint NULL DEFAULT NULL,
  `alarm_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `value` double NULL DEFAULT NULL,
  `threshold` double NULL DEFAULT NULL,
  `timestamp` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `is_handled` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `device_id`(`device_id` ASC) USING BTREE,
  CONSTRAINT `device_alarm_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_alarm
-- ----------------------------
INSERT INTO `device_alarm` VALUES (4001, 2001, '高温', 41.2, 40, '2025-06-10 20:15:22', 0);

-- ----------------------------
-- Table structure for device_data
-- ----------------------------
DROP TABLE IF EXISTS `device_data`;
CREATE TABLE `device_data`  (
  `id` bigint NOT NULL,
  `device_id` bigint NULL DEFAULT NULL,
  `temperature` double NULL DEFAULT NULL,
  `humidity` double NULL DEFAULT NULL,
  `smoke_level` double NULL DEFAULT NULL,
  `water_level` double NULL DEFAULT NULL,
  `timestamp` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `device_id`(`device_id` ASC) USING BTREE,
  CONSTRAINT `device_data_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_data
-- ----------------------------
INSERT INTO `device_data` VALUES (3001, 2001, 38.5, 65, NULL, NULL, '2025-06-10 20:15:22');
INSERT INTO `device_data` VALUES (3002, 2001, 41.2, 60.5, NULL, NULL, '2025-06-10 20:15:22');

-- ----------------------------
-- Table structure for device_type_dict
-- ----------------------------
DROP TABLE IF EXISTS `device_type_dict`;
CREATE TABLE `device_type_dict`  (
  `id` bigint NOT NULL,
  `type_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `icon_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_type_dict
-- ----------------------------
INSERT INTO `device_type_dict` VALUES (1001, 'temp_sensor', '温湿度传感器', 'https://mqxu-oss.oss-cn-hangzhou.aliyuncs.com/iot/1.jpeg');
INSERT INTO `device_type_dict` VALUES (1002, 'smoke_detector', '烟雾探测器', 'https://mqxu-oss.oss-cn-hangzhou.aliyuncs.com/iot/2.jpeg');

SET FOREIGN_KEY_CHECKS = 1;
