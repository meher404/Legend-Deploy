-- MySQL dump 10.13  Distrib 5.1.60, for Win64 (unknown)
--
-- Host: localhost    Database: eshopping
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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressID` int(11) NOT NULL DEFAULT '0',
  `doorno` varchar(100) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `pincode` decimal(6,0) NOT NULL,
  PRIMARY KEY (`addressID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1001,'6-96','gandhinagar','kalwakurthy','AndhraPradesh','509324'),(1002,'5-6-789','gandhinagar','kalwakurthy','andhrapradesh','50932'),(1004,'56-8','dsnr','hyd','ap','500004'),(1008,'195-7/56','gachibowli','hyderabad','Andhrapradesh','500008'),(1009,'//','gandhinagar','kalwakurthy','ap','500089'),(2001,'3-98/A','gandhinagar',' hyderabad','AndhraPradesh','500044'),(2002,'5-55','dsnr','hyderabad','AndhraPradesh','500004'),(51001,'4/89-9','PRnagar','Hyderabad','AndhraPradesh','500016'),(51008,'1-1-711/1','GandhiNagar','Hyderabad','AndhraPradesh','500080'),(51932,'6-96','Vidyanagar','Kalwakurthy','AndhraPradesh','509324'),(53200,'56/8','LakshmiPuran','Guntur','AndhraPradesh','522007'),(59963,'589-8','gandhinagar','hyd','ap','589632'),(500624,'58-9','IIIT Hyderabad','Hyderabad','Andhra Pradesh','500008'),(500657,'4-231','GandhiNagar','Hyderabad','AndhraPradesh','500080'),(500966,'4/89-9','GandhiNagar','Hyderabad','AndhraPradesh','500080'),(509679,'9-78','GandhiNagar','Kalwakurthy','AndhraPradesh','508963'),(590556,'15/8','gandhinagar','Mahaboobnagar','Andhra Pradesh','589632'),(610060,'6-97','Dundi Nagar','Vijayawada','Uttar Pradesh','609548'),(879354,'1/1-90','Himayathnagar','Mumbai','Maharashtra','879010');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bills` (
  `timeStamp` datetime DEFAULT NULL,
  `addressID` int(11) DEFAULT NULL,
  `saleid` varchar(10) DEFAULT NULL,
  `totalamount` decimal(22,2) DEFAULT NULL,
  `DeliveryStatus` varchar(20) NOT NULL,
  KEY `saleid` (`saleid`),
  KEY `addressID` (`addressID`),
  CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`saleid`) REFERENCES `orderdetails` (`saleid`),
  CONSTRAINT `bills_ibfk_2` FOREIGN KEY (`addressID`) REFERENCES `address` (`addressID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES ('1992-01-01 00:00:00',1001,'sale001','375.00','pending'),('2013-02-02 00:00:00',1001,'sale002','400.00','delivered'),('2014-02-13 13:18:47',610060,'sale00175','100400.00','pending'),('2014-02-13 14:51:17',610060,'sale0021','400.00','pending'),('2014-02-13 14:57:25',610060,'sale0038','400.00','pending'),('2014-02-13 15:55:21',51008,'sale0056','40000.00','pending'),('2014-02-13 16:00:49',51008,'sale00282','400.00','pending'),('2014-02-13 17:23:29',610060,'sale003','1600.00','pending'),('2014-02-14 10:27:25',500624,'sale00159','2000.00','pending'),('2014-02-14 11:33:44',590556,'sale00456','0.00','pending');
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `userID` varchar(10) DEFAULT NULL,
  `pid` varchar(100) DEFAULT NULL,
  `quantity` decimal(5,0) DEFAULT NULL,
  KEY `userID` (`userID`),
  KEY `PID` (`pid`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product` (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('binn9','book001','5'),('nikh9','100147','1');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL DEFAULT '0',
  `categoryName` varchar(20) NOT NULL,
  PRIMARY KEY (`categoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (100,'mobile'),(178,'computers'),(200,'camera'),(300,'books'),(400,'watches'),(1187,'cosmetics'),(1209,'handycam'),(1214,'clothes');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturer` (
  `manufactureID` int(11) NOT NULL DEFAULT '0',
  `manufactureName` varchar(20) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`manufactureID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (100,'samsung','Samsung is one of the biggest brand'),(147,'LG','GoogleAssosciation'),(200,'canon','famous camera manufacturer'),(206,'fastrack','watches,bags'),(210,'Oriflame','swedenGroup'),(213,'j.K.rowling','author'),(218,'liviya','bags'),(232,'Asus','Multinational'),(300,'tmh','book publisher'),(400,'oreilly','american book publisher'),(500,'police','leading watch manufacturer'),(1212,'titan','titan'),(1213,'panasonic','panasonic'),(1214,'apple','apple'),(1217,'mfgname','mfgname'),(1220,'Dell','Dell'),(1222,'patanjali','patanjali'),(1236,'orielly','orielly');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderdetails` (
  `saleid` varchar(10) NOT NULL DEFAULT '',
  `userID` varchar(10) DEFAULT NULL,
  `pid` varchar(100) NOT NULL DEFAULT '',
  `quantity` decimal(5,0) DEFAULT NULL,
  PRIMARY KEY (`saleid`,`pid`),
  KEY `PID` (`pid`),
  KEY `userID` (`userID`),
  CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product` (`PID`),
  CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES ('sale001','binn9','book001','2'),('sale001','binn9','book002','3'),('sale00159','nikh0','19142212424001212','1'),('sale00175','nikh0','178232','2'),('sale00175','nikh0','300213','1'),('sale002','binn9','book001','3'),('sale0021','nikh0','300213','1'),('sale00282','mehe8','300213','1'),('sale003','nikh0','300213','4'),('sale0038','nikh0','300213','1'),('sale00456','Susm6','100147','0'),('sale0056','mehe8','100147','1');
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `name` varchar(100) DEFAULT NULL,
  `PID` varchar(100) NOT NULL DEFAULT '',
  `image` varchar(100) DEFAULT NULL,
  `price` decimal(22,2) DEFAULT NULL,
  `quantity` decimal(5,0) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `manufactureID` int(11) DEFAULT NULL,
  `rating` decimal(2,0) DEFAULT NULL,
  `discount` decimal(5,2) DEFAULT NULL,
  `categoryID` int(11) DEFAULT NULL,
  `status` varchar(30) DEFAULT 'active',
  PRIMARY KEY (`PID`),
  KEY `categoryID` (`categoryID`),
  KEY `manufactureID` (`manufactureID`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`manufactureID`) REFERENCES `manufacturer` (`manufactureID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('Panasonic-Camcorder','-121568900312091213','img\\products\\handycam\\panasonic.jpg','25000.00','25','your world through our lens',1213,'4','0.00',1209,'active'),('iphone 5S','-20843289351001214','img/products/mobile/iphone5s.jpeg','50000.00','5','mobile that has great performance processor',1214,'4','0.00',100,'Inactive'),('Nexus','100147','img/products/mobile/nexus4.png','40000.00','78','SmartPhone',147,'4','0.00',100,'active'),('Optimals','1187210','img/products/cosmetics/oriflame.jpg','500.00','750','SmartPhone',210,'4','0.00',1187,'Inactive'),('patanjali keshKanthi','120838407211871222','img/products/cosmetics/patanjali-kesh.png','100.00','100','Swami baba ram dev Ayurvedic product',1222,'4','0.00',1187,'active'),('Dell inspiron 15R','1781220','img/products/computers/dell-Inspiron-15R-5521.jpg','45000.00','138','Dell best way of computing',1220,'4','0.00',178,'Inactive'),('Asus-zen','178232','img/products/computers/asus-zen.jpg','50000.00','39','touchScreen',232,'4','0.00',178,'active'),('Titan-Tawatec','19142212424001212','img/products/watches/titan.jpg','2000.00','498','World\'s best watch',1212,'4','0.00',400,'active'),('Dbms','3001236','img/products/books/dbms.jpg','500.00','123','very good book for dbms by raghu ',1236,'4','0.00',300,'Inactive'),('HarryPotter','300213','img/products/books/harrypotter.jpg','400.00','832','Novel',213,'5','0.00',300,'active'),('police','400500','img/products/watches/police2.jpg','5000.00','20','World\'s best watch',500,'4','0.00',400,'active'),('oriflame optimals','4320260841187210','img/products/cosmetics/oriflame.jpg','500.00','100','Oriflame is a leading beauty products manufacturer',210,'4','0.00',1187,'active'),('canonixus','828372540200200','img/products/camera/canonixus.jpeg','50000.00','10','Best camera to capture best moments',200,'4','0.00',200,'active'),('Dell XPS','8883060761781220','img/products/computers/Dell-XPS-M1530-Laptops.jpg','52800.00','200','Great way of computing',1220,'4','0.00',178,'Inactive'),('letusC','book001',NULL,'125.00','30','C-language',300,'4','0.00',300,'Inactive'),('headfirstJAVA','book002',NULL,'250.00','100','java',400,'3','0.00',300,'Inactive');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `userID` varchar(10) NOT NULL,
  `tempCode` decimal(10,0) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  KEY `userID` (`userID`),
  CONSTRAINT `registration_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('ravi3','193240','not-activated'),('nikh1','506024','not-activated'),('teja2','469250','not-activated'),('mehe8','291176','active'),('srir3','209121','not-activated'),('hari8','772881','active'),('adep6','743519','active'),('nikh9','366705','active'),('Rahu4','532500','not-activated'),('nikh0','720214','active'),('Susm6','511887','active');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping` (
  `saleid` varchar(10) DEFAULT NULL,
  `recipient` varchar(50) DEFAULT NULL,
  `contactNo` varchar(10) DEFAULT NULL,
  `addressID` int(11) DEFAULT NULL,
  `userid` varchar(10) DEFAULT NULL,
  `current` varchar(20) DEFAULT 'active',
  KEY `saleid` (`saleid`),
  KEY `addressID` (`addressID`),
  KEY `userid` (`userid`),
  CONSTRAINT `shipping_ibfk_1` FOREIGN KEY (`saleid`) REFERENCES `orderdetails` (`saleid`),
  CONSTRAINT `shipping_ibfk_2` FOREIGN KEY (`addressID`) REFERENCES `address` (`addressID`),
  CONSTRAINT `shipping_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping`
--

LOCK TABLES `shipping` WRITE;
/*!40000 ALTER TABLE `shipping` DISABLE KEYS */;
INSERT INTO `shipping` VALUES (NULL,'nikhitha','9494268893',509679,'nikh9','completed'),(NULL,'nikhitha','9494268893',509679,'binn9','completed'),('sale001','nikhitha','9494268893',509679,'nikh9','completed'),('sale00175','nikhitha','9493568965',610060,'nikh0','completed'),('sale00175','nikhitha','9493568965',610060,'nikh0','completed'),('sale00175','nikhitha','9493568965',610060,'nikh0','completed'),('sale00175','nikhitha','9493568965',610060,'nikh0','completed'),('sale0021','nikhitha','9493568965',610060,'nikh0','completed'),('sale0038','susmitha','9493568965',610060,'nikh0','completed'),('sale0056','meher','9573026690',51008,'mehe8','completed'),('sale00282','meher','9573026690',51008,'mehe8','completed'),('sale003','nikhitha Reddy','9493568965',610060,'nikh0','completed'),('sale00159','Susmitha','9945689654',500624,'nikh0','completed'),('sale00456','Susmitha','9494268893',590556,'Susm6','completed');
/*!40000 ALTER TABLE `shipping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `name` varchar(50) DEFAULT NULL,
  `userID` varchar(10) NOT NULL DEFAULT '',
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `phone` decimal(10,0) DEFAULT NULL,
  `addressID` int(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `dob` date NOT NULL,
  PRIMARY KEY (`userID`),
  KEY `addressID` (`addressID`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`addressID`) REFERENCES `address` (`addressID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('adepuprash','adep6','a.prashanth18@gmail.com','oukv345','9493568965',500966,'Male','1990-01-14'),('binni','binn9','binni@gmail.com','dkppk4','9494268893',1009,'female','1994-01-24'),('haritha','hari8','haritha207@gmail.com','oukv345','9866441003',51008,'Female','4444-01-04'),('meher','mehe8','meher.almighty@gmail.com','oukv345','9573026690',51008,'Male','1992-01-14'),('meher','meher01','meher@gmail.com','123456','9876543210',2001,'male','1992-01-01'),('nikhitha Reddy','nikh0','nikhithareddy02@gmail.com','oukv345','9493568965',610060,'Female','1992-01-02'),('nikhila','nikh1','mente.nikhila@gmail.com','pkmjknc24','9493568965',51001,'Female','1990-01-23'),('nikhitha','nikh9','nickyreddy02@gmail.com','oukv345','9494268894',509679,'Female','1992-01-01'),('Rahul Kumar','Rahu4','rahul@gmail.com','oukv345','9573026690',879354,'Male','1990-01-22'),('ravi','ravi01','ravi@gmail.com','123456','9876543210',2002,'male','1992-01-01'),('raviteja','ravi3','singam.999@gmail.com','345678','9866441003',59963,'Male','1989-01-21'),('sriram ','srir3','sriramprasad4u@gmail.com','oukv345','9573026690',59963,'Male','1998-01-04'),('Susmitha','Susm6','susmithareddy.prettygirl24@gmail.com','oukv345','9494268893',590556,'Female','1994-01-24'),('tejaswi','teja2','tejusingam@gmail.com','876543','9494247393',51932,'Female','1992-01-02');
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

-- Dump completed on 2014-06-09 14:54:43
