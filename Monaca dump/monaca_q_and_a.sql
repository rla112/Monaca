-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: monaca
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `q_and_a`
--

DROP TABLE IF EXISTS `q_and_a`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `q_and_a` (
  `q_and_a_id` bigint NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) NOT NULL,
  `disposable_pw` varchar(10) DEFAULT NULL,
  `new_status` bit(1) NOT NULL,
  `text` varchar(1000) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `author_user_id` bigint DEFAULT NULL,
  `lecture_lecture_id` bigint DEFAULT NULL,
  PRIMARY KEY (`q_and_a_id`),
  KEY `FKkemw6ufwwgfnrc7ng1b6uuxh4` (`author_user_id`),
  KEY `FKqq46symjxxhde4tgctk00juhn` (`lecture_lecture_id`),
  CONSTRAINT `FKkemw6ufwwgfnrc7ng1b6uuxh4` FOREIGN KEY (`author_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKqq46symjxxhde4tgctk00juhn` FOREIGN KEY (`lecture_lecture_id`) REFERENCES `lecture` (`lecture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `q_and_a`
--

LOCK TABLES `q_and_a` WRITE;
/*!40000 ALTER TABLE `q_and_a` DISABLE KEYS */;
INSERT INTO `q_and_a` VALUES (1,'2024-03-13 16:06:57.826585',NULL,_binary '','궁금해요','안녕하세요 ',31,3);
/*!40000 ALTER TABLE `q_and_a` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-21 16:14:12
