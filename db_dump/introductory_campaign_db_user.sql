-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: introductory_campaign_db
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `name_ukr` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `last_name_ukr` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `login` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `rating` int(11) DEFAULT '0',
  `university_id` int(11) DEFAULT NULL,
  `specialty_id` int(11) DEFAULT NULL,
  `student` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_user_university_idx` (`university_id`),
  KEY `fk_user_specialty_idx` (`specialty_id`),
  CONSTRAINT `fk_user_specialty` FOREIGN KEY (`specialty_id`) REFERENCES `specialty` (`specialty_id`) ON DELETE SET NULL,
  CONSTRAINT `fk_user_university` FOREIGN KEY (`university_id`) REFERENCES `university` (`university_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'APPLICANT','Alex','Алекс','Kidov','Кiдов','AlexK','289dff07669d7a23de0ef88d2f7129e7','kidovalex@gmail.com',126,1,3,0),(2,'ADMIN','Admin','Адмiн','Adminov','Адмiнов','Admin','202cb962ac59075b964b07152d234b70','admin@gmail.com',0,NULL,NULL,0),(3,'APPLICANT','Mark','Марк','Sober','Собер','MarkS','289dff07669d7a23de0ef88d2f7129e7','ukrposhta@bigmir.net',117,1,3,0),(15,'APPLICANT','Alex','Алекс','Kidov','Кідов','Alex','289dff07669d7a23de0ef88d2f7129e7','alexkidov@ukr.net',0,NULL,NULL,0),(28,'APPLICANT','Jack','Джек','Back-Flop','Бек-флоп','JackB','289dff07669d7a23de0ef88d2f7129e7','jackback@gmail.com',0,9,3,0),(29,'APPLICANT','Artur','Артур','Duncan','Дункан','ArturD','289dff07669d7a23de0ef88d2f7129e7','arturduncan@gmail.com',0,11,3,0),(30,'APPLICANT','Robert','Роберт','Stark','Старк','RobertS','289dff07669d7a23de0ef88d2f7129e7','robertstark@gmail.com',0,9,2,0),(31,'APPLICANT','Anna','Анна','Bond','Бонд','AnnaB','289dff07669d7a23de0ef88d2f7129e7','annabond@gmail.com',0,14,12,0),(32,'APPLICANT','Don','Дон','Rot','Рот','DonR','289dff07669d7a23de0ef88d2f7129e7','donrot@gmail.com',122,10,4,0),(33,'APPLICANT','Rod','Род','Vannell','Ваннелл','RodV','289dff07669d7a23de0ef88d2f7129e7','rodvanhell@gmail.com',0,10,6,0),(34,'APPLICANT','Ted','Тед','Talk - Back','Ток - Бек','TedT','289dff07669d7a23de0ef88d2f7129e7','tedtalk@gmail.com',99,8,6,0),(35,'APPLICANT','Vlad','Влад','Stan','Стен','VladS','289dff07669d7a23de0ef88d2f7129e7','vladstan@gmail.com',95,11,9,0),(36,'APPLICANT','Ron','Рон','Stuart','Стюарт','RonS','289dff07669d7a23de0ef88d2f7129e7','ronstuart@gmail.com',0,2,7,0),(37,'APPLICANT','John','Джон','Dodd','Дод','JohnD','289dff07669d7a23de0ef88d2f7129e7','johndodd@gmail.net',0,NULL,NULL,0),(38,'APPLICANT','Dan','Ден','Colt','Кольт','DanC','289dff07669d7a23de0ef88d2f7129e7','dancolt@gmial.com',0,NULL,NULL,0),(42,'APPLICANT','Masha','Маша','Lessner','Лесснер','MashaL','289dff07669d7a23de0ef88d2f7129e7','ukrposhta@gmail.com',0,NULL,NULL,0);
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

-- Dump completed on 2019-08-12  0:57:50
