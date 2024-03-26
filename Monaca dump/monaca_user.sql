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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `activated` bit(1) DEFAULT NULL,
  `birth_date` varchar(255) DEFAULT NULL,
  `join_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `login_id` varchar(15) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_num` varchar(30) DEFAULT NULL,
  `professor_intro` varchar(1500) DEFAULT NULL,
  `professor_resume` varchar(50) DEFAULT NULL,
  `characters_character_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_6ntlp6n5ltjg6hhxl66jj5u0l` (`login_id`),
  KEY `FKlm45vv26tjkivx9ff6p8fup7x` (`characters_character_id`),
  CONSTRAINT `FKlm45vv26tjkivx9ff6p8fup7x` FOREIGN KEY (`characters_character_id`) REFERENCES `characters` (`character_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,_binary '','1982-04-23','2024-02-07 20:27:16.854806','hj04@gmail.com','WOMAN','https://i.ibb.co/5rsF8vM/image.png','hyeonjooLee123','이현주','이현주','$2a$10$JPKrFVhvUZiixiiIWlFAgu71C4aaL8v5b0skAPjie.HBtBgLJuTTa','01198765432','안녕하세요. 저는 키오스크 사용법 강의를 맡은 이현주입니다. 저는 항상 기술과 사람 사이의 교감에 큰 관심이 있었습니다. 특히, 디지털 기술이 우리의 일상에 더욱 밀접하게 녹아들면서 사용자 경험이 얼마나 중요한지를 절실히 느끼게 되었습니다. 이러한 관심과 열정으로 강의를 맡게 되었고, 여러분께 가르치는 것에 대해 큰 즐거움을 느끼고 있습니다. 제 강의를 통해 여러분이 디지털 환경에서 더욱 자신감을 느끼고 키오스크를 단순히 사용하는 것을 넘어서, 그 기술이 제공하는 가능성과 편의성을 깨닫는 데 도움을 드리고 싶습니다. 함께 키오스크의 세계를 탐험하여 새로운 지식과 경험을 만들어 나가는 여정이 되기를 진심으로 바랍니다.','소프트웨어 공학 박사 학위',NULL),(10,_binary '','1995-10-08','2024-02-07 20:27:45.123619','jh10@dw.com','MAN','https://i.ibb.co/BwCCBky/image.png','junhoLee123','이준호','이준호','$2a$10$uionrMUyVhZVpuyESlvKeewzmoUMda9Mb0AwDDbDjXjxwrlR25gDi','01056781234','안녕하세요 저는 이준호입니다.  저는 기술적인 이해를 향상하는 것에 열정을 가지고  있습니다.  저는 기술적인 이해를 향상시키는 것에 열정을 가지고 있습니다.  디지털 시대에 능숙하게 대응할 수 있도록 기대하고 있습니다.  제 경력과 역할에 대해 추가로 알려 드리겠습니다.  컴퓨터 공학을 전공하였으며, 컴퓨터의 기능, 상호작용에 관심이 많았습니다.  여러 해 동안 다양한 프로젝트에 참여하여 디지털 인터페이스를 간소화하고, 모든 연령대의 사람들에게 기술에 보다 접근하기 쉽게  만들려고 노력해왔습니다.   수업을 통해 여러분께 제 지식과 경험을 나눌 수 있어서 기쁩니다. 앞으로 함께 성장할 수 있도록 응원하겠습니다.','전자 공학 박사 학위',NULL),(11,_binary '','1975-08-15','2024-02-07 20:31:58.062419','jy08@dw.com','WOMAN',' https://i.ibb.co/n1NZr5t/1.png','JiyoungKim123','김지영','김지영','$2a$10$6bl1/ZsSrCGGfY.esIrm0eaegH82U4bQUhZEk54lCvfkqlkShTdZq','01012345679','반갑습니다. 김지영입니다. 학생분들이 ATM을 사용함으로써 생활에서의 불편함을 줄이고 효율성을 높일 수 있기를 바라고 있습니다. 제 수업을 듣고 난 후 학생분들의 자신감 넘치는 멋진 모습을 기대하고 있습니다. 실제로 변화를 느끼시면서 얼마나 기쁠지 상상해 보시면 더욱 좋을 것 같습니다. 제 수업은 현대 사회에서 매우 중요한 생활 기술 중 하나를 가르치기 때문에 여러분이 이를 습득하면서 자신의 경제적 자립성을 향상시킬 수 있을 것입니다.','소프트웨어 공학 박사 학위',NULL),(12,_binary '','1985-07-18','2024-02-07 20:32:17.828100','ys07@dw.com','WOMAN','https://i.ibb.co/YbkS4td/2-PNG.jpg','yunseoChoi123','최윤서','최윤서','$2a$10$xcN.ew1ITYdxuPQIy7Z3W.G7XGBzgNhFOZ484Su8fnXb3FQmJKi2.','01923456789','안녕하세요 최윤서입니다. 웹사이트 사용법 강의에 참여해주셔서 감사합니다. 이 강의는 웹사이트를 보다 효과적으로 활용할 수 있는 다양한 전략과 팁을 제공합니다. 제가 전달하는 내용이 여러분의 디지털 생활에 도움이 되기를 진심으로 바랍니다. 강의 내용은 실용적이고 유익하며, 여러분의 스킬을 향상시킬 것입니다. 학생들의 성장을 위해 항상 노력하고 있으며, 여러분의 질문과 의견을 적극적으로 반영하고자 합니다. 함께 하면서 새로운 지식을 습득하고, 디지털 환경에서 더욱 능숙해지는 여정을 함께하길 바랍니다. 지금 함께해요!','디자인 박사 학위',NULL),(13,_binary '','1992-08-07','2024-02-07 20:32:38.611199','hy09@dw.com','MAN','https://i.ibb.co/KVZqd8z/3.jpg','hyeonwoo123','정현우','정현우','$2a$10$UWdtfkeFSfA7yVPLK8Uki.SQlhMH2KZ7TqgVh9M4jILsPGruTiqQO','01654321098','안녕하세요 웹사이트 강의 교수 정현우입니다. 본 강의를 통해서 웹사이트의 다양한 기능과 활용 방법을 배울 수 있습니다.본 강의는 여러분이 웹사이트를 효과적으로 활용할 수 있는 능력을 키우는 것이 중점입니다. 디지털 시대에 필수적인 기술인 웹사이트 사용법을 익히며, 여러분의 미래를 준비해보세요. 이 강의를 통해 여러분은 웹사이트를 활용하는 능력을 기를 수 있을 것입니다. 함께 성장하고 발전하는 과정을 경험해보시길 바랍니다. 지금 바로 참여해보세요!','데이터베이스 석사 학위',NULL),(14,_binary '','1990-03-12','2024-02-07 20:32:56.229110','sb03@dw.com','WOMAN','https://i.ibb.co/DrqSDtL/4.jpg','soobinJeong123','정수빈','정수빈','$2a$10$q03K3ixdYqepWU7qRmYaLuA2Vygfimkb14fagbwRDXdcMcVfSp6YG','01054321897','안녕하세요. 정수빈입니다. 이 강의는 여러분이 스마트폰을 더욱 효율적으로 활용할 수 있도록 다양한 기능과 팁을 제공합니다. 저는 항상 사용자들의 요구에 귀를 기울이고, 최신 트렌드에 대한 이해를 바탕으로 강의를 진행하고 있습니다. 이 강의를 통해 더욱 편리하고 즐거운 일상을 누릴 수 있도록 도와드리겠습니다. 또한, 이 강의를 통해 디지털 기술에 대한 자신감을 키우고, 더 나은 미래를 준비할 수 있는 능력을 키울 수 있을 것입니다. 지금 바로 함께하고 새로운 세계를 탐험해보세요!.','인터렉션 디자인 박사 학위',NULL),(15,_binary '','1974-09-22','2024-02-07 20:33:09.021785','ty08@dw.com','MAN','https://i.ibb.co/71DZtQF/4.jpg','taeyoungKang123','강태영','강태영','$2a$10$3/cEa7AwmMaM.Tehg.37kOlSKTLHdtPHR6lQ4KfnsivRKsdjfaDai','01654321081','안녕하세요 저는 모바일기기 사용법 강의를 맡고 있는 강태영입니다. 이 강의는 여러분이 스마트폰을 더욱 효율적으로  활용할 수 있는 다양한 방법을 제시합니다.  스마트폰은 현대 사회에서 필수불가결한 도구이며,   이를 올바르게 활용하는 데에는 몇 가지 요령이 필요합니다.  제 강의를 통해 여러분은 이러한 요령을 배우고,  스마트폰을 더욱 능숙하게 다룰 수 있게 될 것입니다.  함께하면서 여러분의 디지털 라이프를 업그레이드하고,  더 나은 미래를 준비하는 데 도움이 될   것입니다.  지금 신청하고 새로운 기술을 배워보세요.','인터렉션 디자인 박사 학위',NULL),(16,_binary '','1971-12-10','2024-02-07 20:33:20.961958','mk12@dw.com','WOMAN','https://i.ibb.co/1dKVscz/5.jpg','mikyungSon123','손미경','손미경','$2a$10$blg8cWdjtwY7CZ5LokluPOwtVhZGAfP.PKjmv0SCpfQTVgPgW7J6.','01787654323','안녕하세요 손미경입니다. 국가복지 신청과 민원신청 관련 강의를 개강하여 매우 기쁩니다. 본 강의는 국가 서비스를 온라인으로 이용하는 방법과 절차를 자세히 다룹니다. 국가의 다양한 혜택을 효과적으로 신청할 수 있음에 도움이 되었으면 좋겠습니다. 이러한 강의를 온라인으로 제공하는 것은 현 사회에서 매우 중요하다고 생각하며 이에 대한 지식을 습득하는 것은 필수입니다. 함께 공부하며 실생활에 유용한 기술을 배우고 활용해봅시다. 지금 바로 강의를 등록하세요!','공공정책 박사 학위',NULL),(17,_binary '','1974-09-22','2024-02-07 20:33:30.725367','ts06@dw.com','MAN','https://i.ibb.co/N7JTQJy/image.png ','taeseokHan123','한태석','한태석','$2a$10$P02MaFnGkmymomyL8gX2YuVKqHAQmULI2wN.QLqXZ55Gbn0ccURfG','01567894321','반갑습니다. 한태석입니다. 우선 여러분을 강의를 통해 만나뵐 수 있게 되어 기쁩니다. 본 강의는 국가서비스를 온라인으로 이용하는 방법과 절차를 알려 드립니다. 디지털 시대에 들어서며, 온라인으로 이러한 서비스를 이용하는 것이 중요시되었습니다. 함께하며 디지털 시대에 필요한 기능을 키워나갈 수 있도록 최선을 다하겠습니다. 지금 바로 강의에 참여하여 미래를 준비하세요!','사회복지 학위',NULL),(31,_binary '','2001-03-14','2024-02-09 15:36:27.832831',NULL,'WOMAN',NULL,'rladpdms123','김예은','김예은','$2a$10$MLrKvTnPaXkPJPGFPajk6.QYABlOKAo4ey7XgtlAsHBa7QzGc3IfW','01012341234',NULL,NULL,NULL),(32,_binary '','1999-03-26','2024-02-09 15:37:25.855918',NULL,'WOMAN',NULL,'duagpwjd123','염혜정','염혜정','$2a$10$wgK8ZnRj8hAXMnD5BCzgCOwzmA1Z1K20p5b5nesFZctMpYVAFM4Bm','01012341234',NULL,NULL,NULL),(33,_binary '','2001-01-02','2024-02-09 15:38:06.614142',NULL,'MAN',NULL,'rlatjsdnr123','김선욱','김선욱','$2a$10$RmCZbJtaoUemaCnrUbYyDeit3Bw4GXkDkW2ZBUHwLCnpDcEzcRFOe','01012341234',NULL,NULL,NULL),(34,_binary '','2001-01-01','2024-02-09 15:45:01.261388',NULL,'MAN',NULL,'monaca12','김학생','김학생','$2a$10$knVyaIATS7h70f8jM7uHbun/WUyiz5iP1Zzqo5/vev4tmPM2hLdgq','01012341234',NULL,NULL,NULL),(35,_binary '','2001-02-01','2024-01-09 15:46:27.448426',NULL,'WOMAN',NULL,'monaca123','이학생','이학생','$2a$10$RNqvHhMbrgysumV06mIN8.Eo5Bo5U3G9regAUrcx20.epD1vNg2aC','01012341234',NULL,NULL,NULL),(36,_binary '','2001-03-01','2024-01-09 15:47:14.316937',NULL,'MAN',NULL,'monaca124','박학생','박학생','$2a$10$r6UcBMsS3EFxQVGAopUcgOCx8fnMFDHfFMAItGWk6tvoP1y7yM/62','01012341234',NULL,NULL,NULL),(37,_binary '','2001-04-01','2024-02-09 15:49:21.738547',NULL,'WOMAN',NULL,'monaca125','하학생','하학생','$2a$10$E7vexUKa3CKJavSBqyhDFuEiL0U0KghjgxU4AH4HQa7vZpaJ1RoMi','01012341234',NULL,NULL,NULL),(38,_binary '','2001-05-01','2024-03-09 15:50:30.021430',NULL,'MAN',NULL,'monaca126','정학생','정학생','$2a$10$mk5oPcJI52eM1qT6YAI18urSxkfl.XEUWpZz7eXIswLVQck.9WVCC','01012341234',NULL,NULL,NULL),(39,_binary '','2001-06-01','2024-04-09 15:51:13.136973',NULL,'WOMAN',NULL,'monaca127','최학생','최학생','$2a$10$Ei4l2vXv1yUPE89g2JvoLuxc/5z5UJypNS.r.IrUDe.8xWZ8ssZKm','01012341234',NULL,NULL,NULL),(40,_binary '','2001-07-01','2024-05-09 15:51:54.758547',NULL,'MAN',NULL,'monaca128','조학생','조학생','$2a$10$haVCio8S47nXtp/eBHkAJOsR6t9sNsW5ZvwJrNcyZeLLNyyI2qktS','01012341234',NULL,NULL,NULL),(41,_binary '','2001-08-01','2024-06-20 14:33:37.929537',NULL,'WOMAN',NULL,'monaca129','학생일','학생일','$2a$10$QjnPAkNh1WnKf1L460BZjuSS3eTDqXV6942JLdDD5TGy2yck.fVNW','01012341234',NULL,NULL,NULL),(42,_binary '','2001-09-01','2024-07-20 14:34:28.932380',NULL,'MAN',NULL,'monaca130','학생이','학생이','$2a$10$kF8nyHyLPtVKVSew/GY.OeCCfO/OI8EuT272.hpAfd68Emtls0lSW','01012341234',NULL,NULL,NULL),(43,_binary '','2001-10-01','2024-08-20 14:35:05.987878',NULL,'WOMAN',NULL,'monaca131','학생삼','학생삼','$2a$10$Jf/tB/JxYNnSdQyYoNgxrufAyNxq01v./2mTTAMFBujuT4dT4zA5y','01012341234',NULL,NULL,NULL),(44,_binary '','2001-11-01','2024-09-20 14:35:40.891693',NULL,'MAN',NULL,'monaca132','학생사','학생사','$2a$10$pj.ExvMfoj7u5tUInLINqu5L6Uk1GLrCPxbWqD7xRz8hCIbH1KcKm','01012341234',NULL,NULL,NULL),(45,_binary '','2001-12-01','2024-10-20 14:36:24.155750',NULL,'WOMAN',NULL,'monaca133','학생오','학생오','$2a$10$e02ZILB.ctlOLtLI2NSA2Ozc.fhjNrbjMEQbmP4RZE7H8Yr1g.J5S','01012341234',NULL,NULL,NULL),(46,_binary '','2002-01-01','2024-11-20 14:37:06.067843',NULL,'MAN',NULL,'monaca134','학생육','학생육','$2a$10$JC6VMMAqEzyRare96Bic2OxBttjpTngXiDH0bzGrS9nA2OBsY94TO','01012341234',NULL,NULL,NULL),(47,_binary '','2002-02-01','2024-12-20 14:37:50.475924',NULL,'WOMAN',NULL,'monaca135','학생칠','학생칠','$2a$10$EEG4Z6vQ/Tfq.rTRWgkrVusLeJYKFXs0u7GBg4XxpgGoSTFILoFKW','01012341234',NULL,NULL,NULL),(48,_binary '','2002-03-01','2024-01-20 14:38:57.134055',NULL,'MAN',NULL,'monaca136','학생팔','학생팔','$2a$10$4SwAmN69q5Z6JctjtBUsr..u8C2hjym6Qq5QytmbBfEe1uE8jvj92','01012341234',NULL,NULL,NULL),(49,_binary '','2002-04-01','2024-03-20 14:39:57.436183',NULL,'WOMAN',NULL,'monaca137','학생구','학생구','$2a$10$uFTAbwuwvQLYqxd.Xg0YBOWe7qgtelDUwnjazfiiHHw.2joFdMw3S','01012341234',NULL,NULL,NULL),(51,_binary '','2002-05-01','2024-03-21 15:12:17.282017',NULL,'MAN',NULL,'monaca138','학생십','학생십','$2a$10$pmxabtUExIeXVCoKyrKrkOi7FSRfLohx9T1yB0KWI6.TF7t3Orez2','01012341234',NULL,NULL,NULL),(52,_binary '','2002-06-01','2024-12-21 15:12:57.258730',NULL,'WOMAN',NULL,'monaca139','십일번','십일번','$2a$10$2g.HWg5Rts2hCO2qjpFqNOn9krC9hwaklrHb9TCGJFLneQzQUM4/S','01012341234',NULL,NULL,NULL),(53,_binary '','2002-07-01','2024-01-21 15:14:35.162293',NULL,'MAN',NULL,'monaca140','십이번','십이번','$2a$10$K/Z9fw07fqvtyf7hSSUuRusdlyUIB9NAcjAW5UBnGfDSI0CBERn2W','01012341234',NULL,NULL,NULL),(54,_binary '','2002-08-01','2024-04-21 15:15:23.417913',NULL,'WOMAN',NULL,'monaca141','십삼번','십삼번','$2a$10$.UxdyebwUlZMnAUH34OynurXZG89va9jREqz3aXJ.i3zRm2JTLmGG','01012341234',NULL,NULL,NULL),(55,_binary '','2002-09-01','2024-03-21 15:16:34.778549',NULL,'MAN',NULL,'monaca142','십사번','십사번','$2a$10$e8g.MVKany9fL63f3.QXVuyMvr0IB7ApdYV.oxzbW.yJ4dyiovPrm','01012341234',NULL,NULL,NULL);
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

-- Dump completed on 2024-03-21 16:14:10
