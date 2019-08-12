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
-- Table structure for table `specialty_has_subject`
--

DROP TABLE IF EXISTS `specialty_has_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `specialty_has_subject` (
  `specialty_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `subject_number` int(11) NOT NULL,
  KEY `fk_specialty_has_subject_subject1_idx` (`subject_id`),
  KEY `fk_specialty_has_subject_specialty1_idx` (`specialty_id`),
  CONSTRAINT `fk_specialty_has_subject_specialty1` FOREIGN KEY (`specialty_id`) REFERENCES `specialty` (`specialty_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_specialty_has_subject_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty_has_subject`
--

LOCK TABLES `specialty_has_subject` WRITE;
/*!40000 ALTER TABLE `specialty_has_subject` DISABLE KEYS */;
INSERT INTO `specialty_has_subject` VALUES (4,3,2),(4,2,3),(4,8,3),(5,3,2),(5,2,3),(5,4,3),(6,3,2),(6,2,3),(6,8,3),(7,4,2),(7,2,3),(7,5,3),(8,3,2),(8,2,3),(8,8,3),(9,3,2),(9,2,3),(9,8,3),(10,7,2),(10,2,3),(10,6,3),(11,3,2),(11,2,3),(11,8,3),(12,6,2),(12,7,3),(2,3,2),(2,2,3),(2,8,3),(3,4,2),(3,2,3),(3,5,3),(1,2,2),(1,3,2),(1,4,3);
/*!40000 ALTER TABLE `specialty_has_subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-12  0:57:51
