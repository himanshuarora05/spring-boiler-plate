CREATE TABLE `cu_cp_session_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(100) NOT NULL,
  `created_at` bigint(45) NOT NULL,
  `modified_at` bigint(45) NOT NULL,
  `data` varchar(1000) NOT NULL,
  `expiry` bigint(45) NOT NULL,
  `stale` int(11) NOT NULL,
  `method` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `session_id_UNIQUE` (`session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=714 DEFAULT CHARSET=utf8;
