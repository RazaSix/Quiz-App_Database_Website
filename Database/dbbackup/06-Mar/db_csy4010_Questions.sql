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
-- Table structure for table `Questions`
--

DROP TABLE IF EXISTS `Questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Questions` (
  `Question_ID` int(6) NOT NULL AUTO_INCREMENT,
  `Text` varchar(100) DEFAULT NULL,
  `Questionnaire_ID` int(6) NOT NULL,
  PRIMARY KEY (`Question_ID`),
  KEY `fk_Questions_Questionnaires_idx` (`Questionnaire_ID`),
  CONSTRAINT `fk_Questions_Questionnaires` FOREIGN KEY (`Questionnaire_ID`) REFERENCES `Questionnaires` (`Questionnaire_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Questions`
--

LOCK TABLES `Questions` WRITE;
/*!40000 ALTER TABLE `Questions` DISABLE KEYS */;
INSERT INTO `Questions` VALUES (28,'Question 1',20),(29,'Question 2',20),(30,'Question 3',20),(31,'How often do you print?',21),(32,'Where do you normally print?',21),(33,'Do you need more print locations?',21),(34,NULL,22),(35,NULL,22),(36,NULL,22);
/*!40000 ALTER TABLE `Questions` ENABLE KEYS */;
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
