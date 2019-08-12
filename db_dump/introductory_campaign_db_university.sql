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
-- Table structure for table `university`
--

DROP TABLE IF EXISTS `university`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `university` (
  `university_id` int(11) NOT NULL AUTO_INCREMENT,
  `university_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `university_name_ukr` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`university_id`),
  UNIQUE KEY `university_id_UNIQUE` (`university_id`),
  UNIQUE KEY `university_name_UNIQUE` (`university_name`),
  UNIQUE KEY `university_name_ukr_UNIQUE` (`university_name_ukr`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university`
--

LOCK TABLES `university` WRITE;
/*!40000 ALTER TABLE `university` DISABLE KEYS */;
INSERT INTO `university` VALUES (1,'National Technical University of Ukraine ”Igor Sikorsky Kyiv Polytechnic Institute”','Національний технічний університет України «Київський політехнічний інститут»'),(2,'Taras Shevchenko National University of Kyiv','Київський національний університет імені Тараса Шевченка'),(3,'National University of \"Kyiv-Mohyla Academy\"','Національний університет «Києво-Могилянська академія»'),(8,'Kyiv National University of Technologies and Design','Київський національний університет технологій та дизайну'),(9,'Kyiv Medical University of UAFM','Київський медичний університет УАНМ'),(10,'Kyiv National University of Trade and Economics','Київський національний торговельно-економічний університет'),(11,'Kyiv National University of Construction and Architecture','Київський національний університет будівництва і архітектури'),(12,'Borys Hrinchenko Kyiv University','Київський університет імені Бориса Грінченка'),(13,'National Aviation University','Національний авіаційний університет'),(14,'National Pedagogical Dragomanov University','Національний педагогічний університет імені М. П. Драгоманова');
/*!40000 ALTER TABLE `university` ENABLE KEYS */;
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
