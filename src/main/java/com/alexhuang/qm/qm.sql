CREATE TABLE `tt_queue_machine_data` (
  `id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `key_value` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `context` mediumtext COLLATE utf8mb4_bin NOT NULL,
  `version` int(11) NOT NULL,
  `isUsed` tinyint(4) NOT NULL,
  `created_time` datetime NOT NULL,
  `modifed_time` datetime NOT NULL,
  `DATA_ENV_VERSION` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_value_version_idx` (`key_value`,`version`),
  KEY `modifed_time_idx` (`modifed_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

