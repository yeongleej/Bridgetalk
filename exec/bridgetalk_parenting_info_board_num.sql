-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: k10b303.p.ssafy.io    Database: bridgetalk
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `parenting_info_board_num`
--

DROP TABLE IF EXISTS `parenting_info_board_num`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parenting_info_board_num` (
  `board_num_id` bigint NOT NULL AUTO_INCREMENT,
  `age` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `num` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`board_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parenting_info_board_num`
--

LOCK TABLES `parenting_info_board_num` WRITE;
/*!40000 ALTER TABLE `parenting_info_board_num` DISABLE KEYS */;
INSERT INTO `parenting_info_board_num` VALUES (1,'prospective','707973'),(2,'prospective','707765'),(3,'prospective','707677'),(4,'prospective','707582'),(5,'prospective','707508'),(6,'prospective','707397'),(7,'prospective','707219'),(8,'prospective','707154'),(9,'prospective','707064'),(10,'prospective','706783'),(11,'prospective','706767'),(12,'prospective','706670'),(13,'prospective','706579'),(14,'prospective','706541'),(15,'prospective','706443'),(16,'prospective','706403'),(17,'prospective','706279'),(18,'prospective','706233'),(19,'prospective','706194'),(20,'prospective','705814'),(21,'prospective','705146'),(22,'prospective','705096'),(23,'prospective','705033'),(24,'prospective','125761'),(25,'prospective','129388'),(26,'prospective','125874'),(27,'infant_and_toddler','707941'),(28,'infant_and_toddler','707916'),(29,'infant_and_toddler','707794'),(30,'infant_and_toddler','707724'),(31,'infant_and_toddler','707620'),(32,'infant_and_toddler','707519'),(33,'infant_and_toddler','707434'),(34,'infant_and_toddler','707302'),(35,'infant_and_toddler','707191'),(36,'infant_and_toddler','707094'),(37,'infant_and_toddler','707057'),(38,'infant_and_toddler','706782'),(39,'infant_and_toddler','706766'),(40,'infant_and_toddler','706669'),(41,'infant_and_toddler','706578'),(42,'infant_and_toddler','706540'),(43,'infant_and_toddler','706442'),(44,'infant_and_toddler','706402'),(45,'infant_and_toddler','706277'),(46,'infant_and_toddler','706234'),(47,'infant_and_toddler','706195'),(48,'infant_and_toddler','705655'),(49,'infant_and_toddler','705578'),(50,'infant_and_toddler','705432'),(51,'infant_and_toddler','705387'),(52,'infant_and_toddler','705340'),(53,'infant_and_toddler','705320'),(54,'infant_and_toddler','705274'),(55,'infant_and_toddler','705244'),(56,'infant_and_toddler','705136'),(57,'infant_and_toddler','126335'),(58,'infant_and_toddler','126317'),(59,'infant_and_toddler','129730'),(60,'infant_and_toddler','126616'),(61,'infant_and_toddler','126557'),(62,'infant_and_toddler','129470'),(63,'infant_and_toddler','129418'),(64,'infant_and_toddler','128791'),(65,'infant_and_toddler','128787'),(66,'infant_and_toddler','127864'),(67,'school','707943'),(68,'school','707917'),(69,'school','707798'),(70,'school','707725'),(71,'school','707621'),(72,'school','707520'),(73,'school','707435'),(74,'school','707303'),(75,'school','707193'),(76,'school','707095'),(77,'school','706983'),(78,'school','706757'),(79,'school','706671'),(80,'school','706580'),(81,'school','706539'),(82,'school','706441'),(83,'school','706395'),(84,'school','706280'),(85,'school','706276'),(86,'school','706197'),(87,'school','706196'),(88,'school','705750'),(89,'school','705715'),(90,'school','705631'),(91,'school','705561'),(92,'school','705205'),(93,'school','705101'),(94,'school','129933'),(95,'school','129905'),(96,'school','129846'),(97,'school','129812'),(98,'school','125557'),(99,'school','129565'),(100,'school','129491'),(101,'school','129265'),(102,'school','129245'),(103,'school','129077'),(104,'school','128920'),(105,'school','128919'),(106,'school','128918'),(107,'school','128866'),(108,'school','128851'),(109,'school','128841'),(110,'school','127710'),(111,'school','127301'),(112,'school','127232'),(113,'school','126941'),(114,'school','126807'),(115,'school','125436'),(116,'school','122045'),(117,'puberty','707975'),(118,'puberty','707767'),(119,'puberty','707678'),(120,'puberty','707583'),(121,'puberty','707509'),(122,'puberty','707396'),(123,'puberty','707220'),(124,'puberty','707155'),(125,'puberty','707065'),(126,'puberty','706477'),(127,'puberty','706444'),(128,'puberty','706416'),(129,'puberty','706366'),(130,'puberty','706322'),(131,'puberty','706281'),(132,'puberty','706260'),(133,'puberty','706235'),(134,'puberty','706199'),(135,'puberty','706190');
/*!40000 ALTER TABLE `parenting_info_board_num` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-13  3:33:50
