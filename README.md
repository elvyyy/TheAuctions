# DataBase script and Diagram

# Diagram

![Diagram](https://i.ibb.co/nwpjqw6/dbimage.png)

# Script

```mysql
--
-- Table structure for table `lot_history`
--

DROP TABLE IF EXISTS `lot_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lot_history` (
                               `lot_id` int(11) NOT NULL,
                               `current_bid` decimal(7,2) NOT NULL,
                               `created_by` varchar(100) NOT NULL,
                               `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               KEY `fk_lots_history_lots1_idx` (`lot_id`),
                               KEY `fk_lots_history_users1_idx` (`created_by`),
                               KEY `lots_history_bid_idx` (`current_bid`),
                               CONSTRAINT `fk_lots_history_lots1` FOREIGN KEY (`lot_id`) REFERENCES `lots` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
                               CONSTRAINT `lot_history_users_username_fk` FOREIGN KEY (`created_by`) REFERENCES `users` (`username`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lot_status`
--

DROP TABLE IF EXISTS `lot_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lot_status` (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `status` varchar(30) NOT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `status_UNIQUE` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lots`
--

DROP TABLE IF EXISTS `lots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lots` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `created_by` int(11) NOT NULL,
                        `verified_by` int(11) DEFAULT NULL,
                        `minimal_bid` decimal(7,2) NOT NULL,
                        `lot_status_id` int(11) NOT NULL,
                        `description` text NOT NULL,
                        `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `sold_to` int(11) DEFAULT NULL,
                        `image_url` varchar(255) DEFAULT NULL,
                        `end_at` datetime DEFAULT NULL,
                        `start_at` datetime DEFAULT NULL,
                        `duration` int(10) unsigned NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `fk_lots_lot_status1_idx` (`lot_status_id`),
                        KEY `fk_lots_verified_by_idx` (`verified_by`),
                        KEY `fk_lots_created_by_idx` (`created_by`),
                        KEY `lots_users_id_fk_3` (`sold_to`),
                        CONSTRAINT `fk_lots_lot_status` FOREIGN KEY (`lot_status_id`) REFERENCES `lot_status` (`id`),
                        CONSTRAINT `lots_users_id_fk` FOREIGN KEY (`verified_by`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                        CONSTRAINT `lots_users_id_fk_2` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                        CONSTRAINT `lots_users_id_fk_3` FOREIGN KEY (`sold_to`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `role_name` varchar(100) NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_status`
--

DROP TABLE IF EXISTS `user_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_status` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `status` varchar(45) NOT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `status_UNIQUE` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `username` varchar(100) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `password` char(60) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                         `first_name` varchar(150) NOT NULL,
                         `middle_name` varchar(150) DEFAULT NULL,
                         `last_name` varchar(150) NOT NULL,
                         `user_role_id` int(11) NOT NULL,
                         `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `user_status_id` int(11) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `username_UNIQUE` (`username`),
                         UNIQUE KEY `email_UNIQUE` (`email`),
                         KEY `fk_users_user_role_idx` (`user_role_id`),
                         KEY `fk_user_status_table11_idx` (`user_status_id`),
                         CONSTRAINT `fk_user_status_table11` FOREIGN KEY (`user_status_id`) REFERENCES `user_status` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
                         CONSTRAINT `fk_users_user_role` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

