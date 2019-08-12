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
-- Table structure for table `university_has_specialty`
--

DROP TABLE IF EXISTS `university_has_specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `university_has_specialty` (
  `university_id` int(11) NOT NULL,
  `specialty_id` int(11) NOT NULL,
  KEY `fk_university_has_specialty_specialty1_idx` (`specialty_id`),
  KEY `fk_university_has_specialty_university1_idx` (`university_id`),
  CONSTRAINT `fk_university_has_specialty_specialty1` FOREIGN KEY (`specialty_id`) REFERENCES `specialty` (`specialty_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_university_has_specialty_university1` FOREIGN KEY (`university_id`) REFERENCES `university` (`university_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `university_has_specialty`
--

LOCK TABLES `university_has_specialty` WRITE;
/*!40000 ALTER TABLE `university_has_specialty` DISABLE KEYS */;
INSERT INTO `university_has_specialty` VALUES (2,2),(2,4),(2,7),(9,1),(9,2),(9,3),(9,4),(9,5),(10,1),(10,3),(10,4),(10,5),(10,6),(12,1),(12,2),(12,3),(12,8),(12,9),(12,11),(12,5),(11,2),(11,8),(11,9),(11,3),(14,3),(14,4),(14,6),(14,7),(14,10),(14,12),(8,1),(8,2),(8,4),(8,6),(8,7),(8,8),(8,9),(8,10),(8,11),(8,12),(3,1),(3,2),(3,9),(3,12),(3,3),(3,5),(3,7),(3,8),(3,10),(3,11),(1,8),(1,9),(1,2),(1,3),(1,1),(1,6),(1,10);
/*!40000 ALTER TABLE `university_has_specialty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-12  0:57:49
