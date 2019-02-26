CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.1.45-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `search`
--

DROP TABLE IF EXISTS `search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search` (
  `search_id` int(11) NOT NULL,
  `search_string` varchar(255) DEFAULT NULL,
  `search_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`search_id`),
  KEY `FKoyxrmfm9j49fuvmq45lchq6o5` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search`
--

LOCK TABLES `search` WRITE;
/*!40000 ALTER TABLE `search` DISABLE KEYS */;
INSERT INTO `search` VALUES (138,'kolkata','2019-02-18 14:05:31',137),(141,'srk','2019-02-18 14:08:06',137),(140,'TOI','2019-02-18 14:05:56',137),(169,'cricket','2019-02-19 15:58:15',168),(220,'use','2019-02-22 16:56:53',217),(204,'all','2019-02-21 17:49:48',98),(209,'all','2019-02-21 19:30:42',98),(210,'all','2019-02-22 11:07:21',98),(211,'all','2019-02-22 11:16:51',98),(222,'india surgical strike in pakistan today','2019-02-26 09:39:24',98),(2,'all','2019-02-26 09:39:29',98),(3,'all','2019-02-26 09:39:20',98),(4,'all','2019-02-26 09:39:32',98),(5,'all','2019-02-26 09:39:33',98),(6,'all','2019-02-26 09:39:34',98),(7,'all','2019-02-26 09:39:35',98),(8,'all','2019-02-26 09:39:36',98),(9,'all','2019-02-26 09:39:39',98),(10,'all','2019-02-26 09:39:42',98),(11,'all','2019-02-26 09:39:45',98),(12,'all','2019-02-26 09:39:48',98),(13,'all','2019-02-26 09:39:49',98),(14,'all','2019-02-26 09:39:50',98),(15,'all','2019-02-26 09:39:51',98),(16,'all','2019-02-26 09:39:52',98),(17,'all','2019-02-26 09:39:53',98),(18,'all','2019-02-26 09:39:54',98),(19,'all','2019-02-26 09:39:55',98),(20,'all','2019-02-26 09:39:56',98),(21,'all','2019-02-26 09:39:57',98),(22,'all','2019-02-26 09:39:58',98),(23,'all','2019-02-26 09:39:59',98),(24,'all','2019-02-26 09:40:24',98);
/*!40000 ALTER TABLE `search` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (96,'ROLE_ADMIN','admin@admin.com','admin','$2a$10$2OMXJ.PbQS4EAKffX/xm9.yow3IdkDbRFwV5mVdsACe1YpXEGmvvu',''),(98,'ROLE_USER','user@user.com','user','$2a$10$EY2u0PTxo/011MpQSNjdS.ZL.jKSxvW3EckmIBfOY4NhfZ8ODF7Ua',''),(99,'ROLE_USER','use1r@user.com','user','$2a$10$/jiZ8Mb.QyCh48g0mLWD8.8kzIt6ZAg.7v4nX2wMveh9UjS9aLi2i',''),(100,'ROLE_USER','user1@user.com','user','$2a$10$Yvc.gh.RQ8IhO8SElPMCl.EvRfuzSiO4quhTA61kV8xHvmX3upLtO',''),(111,'ROLE_USER','useg@user.com','usertt','$2a$10$Ke.yx.lgPNZILLjHqlx6/OKfIaZhU4/36HI3utFniNU3spPCLvw1C','\0'),(113,'ROLE_USER','userttttt@user.com','userttttt','$2a$10$z8IsBA.kYgNJ8qDb.eYWI.1I7Aq55DveDQ41lBOD.GIY3K.ShQ8je','\0'),(118,'ROLE_USER','das@gmail.com','wewewe','$2a$10$t.mTWHKLkDQkCdjRrysqK.Qo408.CQHZI7tqmnmOHwM7dBolPy/T2',''),(123,'ROLE_USER','sourav@gmail.com','Sourav Ghosh','$2a$10$AFiNT1eCYRs1nQy57tIVveWNcdR47pguM6..HZnGeShK0UiAKxTMK',''),(137,'ROLE_USER','user2@user.com','anik','$2a$10$21w6khBapq3Gmis99bHmquz09qIWH142wrTbeNuCvCuNxswfU82ae','\0'),(168,'ROLE_USER','ankur@anku.com','ankur','$2a$10$MS49ZMcDU0zB5tSaNbSVN.L9KaSZIF5CrnY402ZmptglT1llf7MPS',''),(146,'ROLE_USER','amit@gmail.com','amit','$2a$10$WobMh3sIekjeCD6lCuG8X.qw7Y5XQHDmuXMNHrzbwRHxYfqzgBsTS',''),(147,'ROLE_USER','user333@user.com','dsadasd','$2a$10$nTXb78ktbgm/uDwMDg6y9uMUq6iIykqYos1FuaCjpHVNcOKlzrKeG',''),(148,'ROLE_USER','user12@user.com','aaasws','$2a$10$fZlHc276/14vj17z7TJ7w.y90Da4Vnh/ztC5wajm5oXJqiwXIvGqy',''),(188,'ROLE_USER','user101@user.com','Avishek','$2a$10$R6V4qaTgt4DmHcMyvnGeDeh59XwGAI7E.Fzo2EjRmYIRLh/nLk0G6',''),(195,'ROLE_USER','userSourav@user.com','sourav','$2a$10$XdXn2OguW2Sv9kzNR.9ereasSPrlCURboSVi0ZWGdi55POeVBrlEG',''),(196,'ROLE_USER','userSourav1@user.com','sourav','$2a$10$zilfawH3TpfGU9ASnreIquUHrujJvLcIwbaMTjhsuywkUN0Q9CnwW',''),(197,'ROLE_USER','userSourav2@user.com','sourav','$2a$10$IshDbVwDkKEHZwAEbX5WDe.bznu1YXW9FfF.C1nhY9JFTAG/jhvU6',''),(199,'ROLE_USER','userSourav3@user.com','sourav','$2a$10$JfNqL161amftRLaJ98fkjuN6AavaaltLE.JmrFM930QoNAXG6S1k2',''),(201,'ROLE_USER','userSourav4@user.com','sourav','$2a$10$6Dk2KRqiRec/pCAyzfzswOn/vb0WPub2QiYyvsIxpVOI4YtwlNz6.',''),(203,'ROLE_USER','sudipq12@gmail.com','asdfgr','$2a$10$o9UHf5pAgQ.5PhnFp4H42.K2p0Ri4VFP0W.HjIhalbEFgwEYaQwei',''),(217,'ROLE_USER','ankur@gmail.com','Ankur Bose','$2a$10$SZqFn9hrfyQ.74UWOZMiputP5wNgFJW2zUuZ4ZCcguALb47eNyybq',''),(216,'ROLE_USER','user1111@gmail.com','asdfgr','$2a$10$y4NvbLzDxh8TfgWoeZsNDeZREiOgRwz2LsARhChFVYo8CS1miRYfq',''),(218,'ROLE_USER','ankur123@gmail.com','ankur','$2a$10$HNQhVswrvgdDbUnlSWDYhOcZ/.wnliUt/Xpml3b7h.WROw71iSEne','\0'),(223,'ROLE_USER','useuse@user.com','aaaa','$2a$10$JM67nFiIplKl5NB.2QGqD.fTJjk9C5cyIOSvJPpuAmsxgW7rWjFf.','\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-26 15:32:03
