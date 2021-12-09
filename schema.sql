-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: proj
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` int NOT NULL,
  `date` varchar(255) NOT NULL,
  `status` bit(1) NOT NULL,
  `emp_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK59ughl3n5vxs1xop5eiurkc66` (`emp_id`),
  CONSTRAINT `FK59ughl3n5vxs1xop5eiurkc66` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (209,'2021-11-17',_binary '',1),(210,'2021-11-17',_binary '',2),(211,'2021-11-17',_binary '\0',3),(212,'2021-11-17',_binary '',4),(213,'2021-11-17',_binary '\0',72),(214,'2021-11-17',_binary '',134),(215,'2021-11-17',_binary '\0',154),(216,'2021-11-17',_binary '',176),(217,'2021-11-17',_binary '\0',178),(234,'2021-11-18',_binary '',1),(235,'2021-11-18',_binary '\0',2),(236,'2021-11-18',_binary '',3),(237,'2021-11-18',_binary '',4),(238,'2021-11-18',_binary '',72),(239,'2021-11-18',_binary '\0',134),(240,'2021-11-18',_binary '',154),(241,'2021-11-18',_binary '\0',176),(242,'2021-11-18',_binary '',178);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (13,'ADIDAS'),(15,'EA SPORTS'),(16,'REEBOK'),(17,'PUMA'),(18,'ESPN'),(19,'ASICS'),(20,'MRF'),(26,'AVM Sports'),(27,'COSCO CRICKET'),(28,'SOLIMO'),(31,'YONEX'),(32,'CARLTON'),(33,'ASHAWAY'),(36,'COSCO MILANO'),(37,'COSCO CHALLENGE'),(38,'WILSON ULTIMATE'),(39,'SPALDING'),(40,'COSCO'),(223,'NO_BRAND'),(246,'new_brand');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `gender` int NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (81,'-',0,'dhyaneshwar@gmail.com','dhyaneshwari','9445628713'),(149,'a',0,'saketh@gmail.com','saketh','9445628713'),(169,'asd',0,'dhyaneshwar@gmail.com','customer','9445628713'),(172,'gajwel',0,'dhyaneshwar1@gmail.com','customer3','9445628713'),(248,'',0,'customer@gmail.ccom','new_customer','9445638713');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp_salaries`
--

DROP TABLE IF EXISTS `emp_salaries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp_salaries` (
  `id` int NOT NULL,
  `emp_id` int NOT NULL,
  `transaction_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs9xq7hwa0o4e5dpsy4lcpihfx` (`emp_id`),
  KEY `FKndcqfy9x4iv2se0mq08jyvi4j` (`transaction_id`),
  CONSTRAINT `FKndcqfy9x4iv2se0mq08jyvi4j` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`),
  CONSTRAINT `FKs9xq7hwa0o4e5dpsy4lcpihfx` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp_salaries`
--

LOCK TABLES `emp_salaries` WRITE;
/*!40000 ALTER TABLE `emp_salaries` DISABLE KEYS */;
INSERT INTO `emp_salaries` VALUES (157,154,156),(183,2,182),(189,3,188),(233,154,232);
/*!40000 ALTER TABLE `emp_salaries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL,
  `aadhar` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `age` int NOT NULL,
  `gender` int NOT NULL,
  `join_date` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `wage` int NOT NULL,
  `work_hours` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'7269 4195 7395','Gajwel',19,0,'09-11-2020','saketh','8688653650',50000,8),(2,'7269 4187 7395','Hyderabad',45,0,'01-08-2021','shekar','9440068002',35000,8),(3,'8369 4187 7395','Siddipet',43,1,'02-08-2021','aruna','9490094131',10000,8),(4,'8366 4187 7395','Pregnapur',16,1,'03-10-2021','sreeja','123456789',5000,4),(72,'726941877395','',45,0,'2020-11-09','Manager','7894561235',35000,8),(134,'','',18,0,'','Employee','',0,8),(154,'123145657898','',18,0,'2021-11-05','employee2','9865327845',1500,8),(176,'123145657898','',18,0,'2021-11-11','manager2','9865327845',50000,8),(178,'123145657898','',18,0,'2021-11-11','manager3','9865327845',15000,8);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` int NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpi2y2j7n01ypo49fone3knjry` (`customer_id`),
  CONSTRAINT `FKpi2y2j7n01ypo49fone3knjry` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (175,'2021-11-15','Good',172),(228,'2021-11-11','qww',81),(252,'2021-11-18','Good Service',81);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (253);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maintenance`
--

DROP TABLE IF EXISTS `maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maintenance` (
  `id` int NOT NULL,
  `towards` varchar(255) DEFAULT NULL,
  `transactionid` int NOT NULL,
  `tow` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeq06pvvltqw2xl2k87ly3odk1` (`transactionid`),
  CONSTRAINT `FKeq06pvvltqw2xl2k87ly3odk1` FOREIGN KEY (`transactionid`) REFERENCES `transaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maintenance`
--

LOCK TABLES `maintenance` WRITE;
/*!40000 ALTER TABLE `maintenance` DISABLE KEYS */;
/*!40000 ALTER TABLE `maintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL,
  `cost` float NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `brand_id` int NOT NULL,
  `warranty_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  KEY `FKta0mw3dplwkiadhr3l59hqhxt` (`warranty_id`),
  CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `FKta0mw3dplwkiadhr3l59hqhxt` FOREIGN KEY (`warranty_id`) REFERENCES `warranty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (24,500,'Cricket Bat',2,20,23),(29,47,'Cricket Ball',0,26,3),(41,300,'Badminton Racquets',25,31,3),(43,1439,'Football',34,17,42),(46,1600,'Basketball',16,37,44),(47,540,'Volleyball',8,40,3),(49,700,'Cricket Bat',4,13,30),(50,750,'Cricket Bat',5,17,23),(51,900,'Cricket Bat',3,16,45),(52,75,'Cricket Ball',9,28,3),(53,68,'Cricket Ball',5,27,3),(54,450,'Badminton Racquets',5,32,30),(55,700,'Badminton Racquets',4,33,23),(56,1117,'Basketball',4,38,23),(57,1049,'Basketball',4,39,30),(58,1849,'Football',4,36,42),(59,499,'Football',7,13,30),(60,750,'Volleyball',4,39,30),(61,800,'Volleyball',6,38,30),(168,150,'football',154,13,25),(247,140,'new_product',1000,246,3);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `id` int NOT NULL,
  `price` float NOT NULL,
  `quantity` int NOT NULL,
  `customer_id` int DEFAULT NULL,
  `emp_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `transaction_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK72ep16wuoj7nllumicmk2ie3s` (`customer_id`),
  KEY `FKimfpjltahiiqaoip59fn7e4dl` (`emp_id`),
  KEY `FKfcs4mjmgry6xchs16dv03eclp` (`product_id`),
  KEY `FKlbfykny93qu1va6kn1as5a55` (`transaction_id`),
  CONSTRAINT `FK72ep16wuoj7nllumicmk2ie3s` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKfcs4mjmgry6xchs16dv03eclp` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKimfpjltahiiqaoip59fn7e4dl` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKlbfykny93qu1va6kn1as5a55` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (83,0,0,81,1,24,82),(85,0,0,81,1,24,84),(86,0,0,81,1,24,84),(88,0,0,81,1,24,87),(90,0,10,81,72,54,89),(93,0,14,81,1,24,92),(94,0,10,81,1,29,92),(116,0,2,81,1,24,115),(118,0,1,81,1,24,117),(120,0,1,81,1,24,119),(121,0,0,81,1,24,119),(139,0,5,81,1,24,138),(141,0,0,81,1,24,140),(143,47,1,81,1,29,142),(147,0,0,81,1,24,146),(148,0,0,81,1,24,146),(171,1000,2,149,4,24,170),(174,2500,5,172,1,24,173),(181,500,1,81,1,24,180),(227,1000,2,81,1,24,226),(250,23024,16,248,154,43,249),(251,500,1,81,154,24,249);
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint NOT NULL,
  `LAST_ACCESS_TIME` bigint NOT NULL,
  `MAX_INACTIVE_INTERVAL` int NOT NULL,
  `EXPIRY_TIME` bigint NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session`
--

LOCK TABLES `spring_session` WRITE;
/*!40000 ALTER TABLE `spring_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spring_session_attributes`
--

LOCK TABLES `spring_session_attributes` WRITE;
/*!40000 ALTER TABLE `spring_session_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `spring_session_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` int NOT NULL,
  `addr` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (96,'Alekhya towers','1234567895','nikhithaa'),(97,'haritha homes','4567891235','Bro'),(152,'qwerty','9567891235','Supplier1'),(153,'gajwel','9467891235','Supplier2');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplierproducts`
--

DROP TABLE IF EXISTS `supplierproducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplierproducts` (
  `id` int NOT NULL,
  `invoice` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `quantity` int NOT NULL,
  `recieved` varchar(255) DEFAULT NULL,
  `productid` int DEFAULT NULL,
  `supplierid` int DEFAULT NULL,
  `transactionid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrqth9pw1aejy7my298ie72842` (`productid`),
  KEY `FKrckpkf8bq4f9olhgypt8lc0eg` (`supplierid`),
  KEY `FKlra5a0gl5h9woh370ixpata6m` (`transactionid`),
  CONSTRAINT `FKlra5a0gl5h9woh370ixpata6m` FOREIGN KEY (`transactionid`) REFERENCES `transaction` (`id`),
  CONSTRAINT `FKrckpkf8bq4f9olhgypt8lc0eg` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`id`),
  CONSTRAINT `FKrqth9pw1aejy7my298ie72842` FOREIGN KEY (`productid`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplierproducts`
--

LOCK TABLES `supplierproducts` WRITE;
/*!40000 ALTER TABLE `supplierproducts` DISABLE KEYS */;
INSERT INTO `supplierproducts` VALUES (101,'2',0,4,NULL,24,96,100),(103,'5',0,100,NULL,46,97,106),(108,'11',0,8,NULL,43,96,109),(111,'1111',0,50,NULL,24,96,110),(113,'5',0,1,NULL,24,96,114),(151,'11',0,1,NULL,24,96,150),(167,'65',0,15,NULL,43,152,166),(245,'4',0,16,NULL,43,96,244);
/*!40000 ALTER TABLE `supplierproducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL,
  `amount` float NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (69,4094,'SOLD 2 PRODUCTS','2021-11-10'),(82,0,'SOLD 1 PRODUCTS',''),(84,0,'SOLD 2 PRODUCTS',''),(87,0,'SOLD 1 PRODUCTS',''),(89,4500,'SOLD 1 PRODUCTS','2021-11-13'),(92,7470,'SOLD 2 PRODUCTS',''),(98,2000,'SUPPLY DEAL WITH nikhitha','2021-11-14'),(100,1200,'SUPPLY DEAL WITH nikhitha','2021-11-14'),(102,500,'SUPPLY DEAL WITH Bro','2021-11-17'),(104,2000,'*edited from old transaction (id-98\nSUPPLY DEAL WITH nikhitha','2021-11-14'),(105,500,'*edited from old transaction (id-102\nSUPPLY DEAL WITH Bro','2021-11-17'),(106,500,'*edited from old transaction (id-105\nSUPPLY DEAL WITH Bro','2021-11-17'),(107,1000,'SUPPLY DEAL WITH nikhitha','2021-11-11'),(109,1000,'*edited from old transaction (id-107\nSUPPLY DEAL WITH nikhitha','2021-11-11'),(110,0,'SUPPLY DEAL WITH nikhitha','2021-11-03'),(112,1000,'SUPPLY DEAL WITH nikhitha','2021-11-10'),(114,1000,'*edited from old transaction (id-112\nSUPPLY DEAL WITH nikhitha','2021-11-10'),(115,1000,'SOLD 1 PRODUCTS',''),(117,500,'SOLD 1 PRODUCTS','2021-11-13'),(119,500,'SOLD 2 PRODUCTS','2021-11-13'),(138,2500,'SOLD 1 PRODUCTS','2021-11-15'),(140,0,'SOLD 1 PRODUCTS',''),(142,47,'SOLD 1 PRODUCTS',''),(146,0,'SOLD 2 PRODUCTS',''),(150,550,'SUPPLY DEAL WITH nikhithaa','2021-11-15'),(156,1500,' SALARY PAID TO employee2 ( Good ) ','2021-11-11'),(166,1500,'SUPPLY DEAL WITH Supplier1','2021-11-17'),(170,1000,'SOLD 1 PRODUCTS','2021-11-11'),(173,2500,'SOLD 1 PRODUCTS','2021-11-10'),(180,500,'SOLD 1 PRODUCTS','2021-11-15'),(182,1550,' SALARY PAID TO shekar ( Best Employee ) ','2021-11-10'),(188,154,' SALARY PAID TO aruna ( ad ) ','2021-11-10'),(192,500,'SOLD 1 PRODUCTS','2021-11-15'),(226,1000,'SOLD 1 PRODUCTS','2021-11-16'),(232,1500,' SALARY PAID TO employee2 (  ) ','2021-11-10'),(244,1500,'SUPPLY DEAL WITH nikhithaa','2021-11-13'),(249,23524,'SOLD 2 PRODUCTS','2021-11-18');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL,
  `emp_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpgwd5fnhtf1rxclepvtva55rp` (`emp_id`),
  CONSTRAINT `FKpgwd5fnhtf1rxclepvtva55rp` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'admin','$2a$10$WUSP5Xay4GJZiHECtJCcpuj5FWhMv5yC1Rmfz7fls0f1Vb1aDbVRK',2,NULL),(73,'manager','$2a$10$CcgvmZNSpte0mBxUgJ8RTOzlT7B2QbLkOYX.Vrhb.SmGeoLZFa53S',1,72),(135,'employee','$2a$10$Vxc/oNEVUbxL7y33tC62bOyoBGKNW7EvHPPUYkqkmaKYLY0N.RToq',0,134),(155,'employee2','$2a$10$RLCZGlPqgMphhVxvOjdxveroreGByZZJem0KXOLCR0Onvt32PpsPy',0,154),(177,'manger2','$2a$10$nj0J.Avlmqf7Exwn/bIsn.TCyqkoez2/2n02zrI1rkvAIzcT6Pp9W',0,176),(179,'manager3','$2a$10$tBt0MiVMw2zNYBbm.ZTVzehoJ8GGJO9dtvP1i1vPIbyTK0W126kGW',0,178);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty`
--

DROP TABLE IF EXISTS `warranty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warranty` (
  `id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `num_of_months` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty`
--

LOCK TABLES `warranty` WRITE;
/*!40000 ALTER TABLE `warranty` DISABLE KEYS */;
INSERT INTO `warranty` VALUES (3,'NO WARRANTY AVAILABLE',0),(23,'Warranty Available',3),(25,'Warranty Available',1),(30,'Warranty Available',2),(42,'Warranty Available',4),(44,'Warranty Available',5),(45,'Warranty Available',6),(220,'Warranty Available',12);
/*!40000 ALTER TABLE `warranty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warranty_claims`
--

DROP TABLE IF EXISTS `warranty_claims`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warranty_claims` (
  `id` int NOT NULL,
  `claim_date` varchar(255) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `sale_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7nml6d0je8w8badgf0tjjai40` (`customer_id`),
  KEY `FKmmo310jkss9h6l4qqn9ews6km` (`product_id`),
  KEY `FKb4in9lwjsrl4dnwrj8dpdbns7` (`sale_id`),
  CONSTRAINT `FK7nml6d0je8w8badgf0tjjai40` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKb4in9lwjsrl4dnwrj8dpdbns7` FOREIGN KEY (`sale_id`) REFERENCES `sales` (`id`),
  CONSTRAINT `FKmmo310jkss9h6l4qqn9ews6km` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warranty_claims`
--

LOCK TABLES `warranty_claims` WRITE;
/*!40000 ALTER TABLE `warranty_claims` DISABLE KEYS */;
INSERT INTO `warranty_claims` VALUES (95,'2021-11-14','I want to claim warranty',81,24,83),(229,'2021-11-04','claimed warranty',81,29,143);
/*!40000 ALTER TABLE `warranty_claims` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-18 15:56:33
