-- MySQL dump 10.13  Distrib 5.1.60, for Win64 (unknown)
--
-- Host: localhost    Database: bankeshopping
-- ------------------------------------------------------
-- Server version	5.1.60-community

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
-- Table structure for table `accountdetails`
--

DROP TABLE IF EXISTS `accountdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountdetails` (
  `accno` decimal(15,0) DEFAULT NULL,
  `balance` decimal(20,0) DEFAULT NULL,
  KEY `accno` (`accno`),
  CONSTRAINT `accountdetails_ibfk_1` FOREIGN KEY (`accno`) REFERENCES `bankuser` (`accno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountdetails`
--

LOCK TABLES `accountdetails` WRITE;
/*!40000 ALTER TABLE `accountdetails` DISABLE KEYS */;
INSERT INTO `accountdetails` VALUES ('6987654321','500000'),('6987654322','498000'),('6987654323','498400');
/*!40000 ALTER TABLE `accountdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bankuser`
--

DROP TABLE IF EXISTS `bankuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bankuser` (
  `accno` decimal(15,0) NOT NULL DEFAULT '0',
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `phone` decimal(10,0) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `dob` date NOT NULL,
  `activestatus` tinyint(1) DEFAULT '0',
  `temppwd` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`accno`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankuser`
--

LOCK TABLES `bankuser` WRITE;
/*!40000 ALTER TABLE `bankuser` DISABLE KEYS */;
INSERT INTO `bankuser` VALUES ('6987654321','nikhitha','123456','kalwakurthy','nikhitha@gmail.com','9494268894','female','1992-02-01',1,'123456'),('6987654322','meher','msit123','hyderabad','meher.almighty@gmail.com','9874563210','male','1992-01-01',1,'125457'),('6987654323','Ravi','msit123','Himayathnagar','singam.999@gmail.com','9866441003','male','1990-02-01',1,'484758');
/*!40000 ALTER TABLE `bankuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `accno` decimal(15,0) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `amount` decimal(20,0) DEFAULT NULL,
  `vender` varchar(20) DEFAULT NULL,
  KEY `accno` (`accno`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`accno`) REFERENCES `bankuser` (`accno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES ('6987654322','2014-02-13 10:52:47','100','legend'),('6987654322','2014-02-13 10:54:57','100','legend'),('6987654323','2014-02-13 13:18:42','100400','Legend'),('6987654323','2014-02-13 14:51:11','400','Legend'),('6987654323','2014-02-13 14:57:18','400','Legend'),('6987654323','2014-02-13 15:55:15','40000','Legend'),('6987654322','2014-02-13 16:00:43','400','Legend'),('6987654323','2014-02-13 17:23:23','1600','Legend'),('6987654322','2014-02-14 10:27:20','2000','Legend'),('6987654322','2014-02-14 11:33:39','0','Legend');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-09 19:53:46
