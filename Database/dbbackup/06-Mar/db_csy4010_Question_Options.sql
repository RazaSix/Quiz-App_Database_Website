CREATE DATABASE  IF NOT EXISTS `db_csy4010` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db_csy4010`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 194.81.104.22    Database: db_csy4010
-- ------------------------------------------------------
-- Server version	5.6.35

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
-- Table structure for table `Question_Options`
--

DROP TABLE IF EXISTS `Question_Options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Question_Options` (
  `Q_Option_ID` int(6) NOT NULL AUTO_INCREMENT,
  `Text` varchar(70) DEFAULT NULL,
  `Question_ID` int(6) NOT NULL,
  `Option_Letter` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Q_Option_ID`),
  KEY `fk_Question_Options_Questions1_idx` (`Question_ID`),
  CONSTRAINT `fk_Question_Options_Questions1` FOREIGN KEY (`Question_ID`) REFERENCES `Questions` (`Question_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Question_Options`
--

LOCK TABLES `Question_Options` WRITE;
/*!40000 ALTER TABLE `Question_Options` DISABLE KEYS */;
INSERT INTO `Question_Options` VALUES (21,'1A',28,'A'),(22,'1B',28,'B'),(23,'1C',28,'C'),(24,'1D',28,'D'),(25,'1E',28,'E'),(26,'2A',29,'A'),(27,'2B',29,'B'),(28,'2C',29,'C'),(29,'2D',29,'D'),(30,'2E',29,'E'),(31,'3A',30,'A'),(32,'3B',30,'B'),(33,'3C',30,'C'),(34,'3D',30,'D'),(35,'3E',30,'E'),(36,'Daily',31,'A'),(37,'Weekly',31,'B'),(38,'Bi-Weekly',31,'C'),(39,'Monthly',31,'D'),(40,'Never',31,'E'),(41,'Home',32,'A'),(42,'Maidwell Library',32,'B'),(43,'Maidwell General',32,'C'),(44,'Park Library',32,'D'),(45,'Park General',32,'E'),(46,'Yes',33,'A'),(47,'No',33,'B'),(48,'-',33,'C'),(49,'-',33,'D'),(50,'-',33,'E');
/*!40000 ALTER TABLE `Question_Options` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-06  0:20:08
