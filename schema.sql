CREATE DATABASE inf600c;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(250) NOT NULL,
  `lastname` varchar(250) NOT NULL,
  `img` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'Donald','Duck',NULL),(2,'Professor','Farnsworth',NULL),(3,'John','Zoidberg',NULL),(4,'Charlie','Brown',NULL);
UNLOCK TABLES;
