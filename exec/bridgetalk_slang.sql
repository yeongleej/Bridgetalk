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
-- Table structure for table `slang`
--

DROP TABLE IF EXISTS `slang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slang` (
  `slang_id` bigint NOT NULL AUTO_INCREMENT,
  `slang_word` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `original_word` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `meaning` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vietnamese_pronunciation` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `vietnamese_translation` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `is_deleted` int DEFAULT '0',
  `modified_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`slang_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slang`
--

LOCK TABLES `slang` WRITE;
/*!40000 ALTER TABLE `slang` DISABLE KEYS */;
INSERT INTO `slang` VALUES (1,'자분','자연분만','일반적인 방식으로 태아를 산도를 통해 분만하는 것.','Cha-bun','Sinh thường là phương pháp sinh thông qua âm đạo để thai nhi ra ngoài một cách tự nhiên.',NULL,0,NULL),(2,'제왕','제왕절개','수술을 통해 태아를 자궁에서 직접 꺼내는 분만 방식.','Chế-vương','Mổ lấy thai là phương pháp sinh mổ, trong đó bác sĩ mở tử cung và bụng mẹ để lấy em bé ra.',NULL,0,NULL),(3,'임당','임신성 당뇨','임신 중에 발병하는 당뇨병.','Im-dang','Đái tháo đường thai kỳ là bệnh tiểu đường xảy ra khi phụ nữ mang thai.',NULL,0,NULL),(4,'토덧','토하는 입덧','입덧 중 구토 증상이 심한 경우.','Thô-đất','Nôn nghén là hiện tượng nôn mửa nghiêm trọng trong thời kỳ ốm nghén.',NULL,0,NULL),(5,'먹덧','먹는 입덧','음식을 먹어야 구토 증상이 완화되는 입덧 증상.','Mục-đất','Nghén ăn là tình trạng buồn nôn được giảm bớt khi ăn.',NULL,0,NULL),(6,'초산','첫 출산','처음 아이를 낳는 것.','Chơ-san','Sinh con đầu lòng nghĩa là lần đầu tiên sinh con.',NULL,0,NULL),(7,'경산','경산부','두 번째 이상 출산하는 산모.','Khánh-san','Đa sản nghĩa là phụ nữ sinh con từ lần thứ hai trở đi.',NULL,0,NULL),(8,'영아산통',NULL,'생후 4개월 이하의 아기가 하루 3시간 이상, 주 3회 이상 과도하게 우는 현상.','Anh-a-san-thông','Đau bụng ở trẻ sơ sinh là tình trạng trẻ dưới 4 tháng khóc quá nhiều.',NULL,0,NULL),(9,'안눕법',NULL,'아기를 안았다가 눕혀서 잠을 재우는 수면 교육 방법.','An-núp-pháp','Phương pháp bế đặt giúp trẻ ngủ.',NULL,0,NULL),(10,'도치맘','고슴도치맘','모든 엄마에게 자기 자식이 가장 예뻐 보인다는 뜻.','Độ-chi-mẹ','Mẹ nhím mô tả người mẹ luôn yêu thương con mình nhất.',NULL,0,NULL),(11,'스와들업',NULL,'아기가 두 손을 올린 채로 잠들도록 속싸개를 싸는 방법.','Sư-và-dũn-áp','Quấn tã kiểu Swaddle Up giúp trẻ ngủ với hai tay giơ lên.',NULL,0,NULL),(12,'스와들미',NULL,'아기가 손을 뺄 수 있도록 쉽게 여미고 고정할 수 있는 속싸개.','Sư-và-dũn-mi','Quấn tã kiểu Swaddle Me giúp trẻ cử động tay.',NULL,0,NULL),(13,'역방쿠','역류방지쿠션','수유 후에 역류를 막기 위해 아기를 눕혀두는 쿠션.','Dịch-phòng-khu','Gối chống trào ngược là gối giúp đặt bé sau khi bú để ngăn ngừa trào ngược.',NULL,0,NULL),(14,'떡뻥',NULL,'이유식을 시작한 아기들이 먹는 뻥튀기 과자.','Đẹt-bẻng','Bánh ăn dặm là loại bánh trẻ em ăn khi bắt đầu ăn dặm.',NULL,0,NULL),(15,'완모','완전 모유수유','분유 없이 모유만으로 수유하는 것.','Hoàn-mô','Nuôi con hoàn toàn bằng sữa mẹ mà không có sữa công thức.',NULL,0,NULL),(16,'완부','완전 분유수유','모유 없이 분유만으로 수유하는 것.','Hoàn-phô','Nuôi con hoàn toàn bằng sữa công thức mà không có sữa mẹ.',NULL,0,NULL),(17,'첫수','첫 수유','아기가 아침에 처음 하는 수유.','Chớt-sư','Lần bú sữa đầu tiên trong ngày, thường là vào buổi sáng.',NULL,0,NULL),(18,'꿈수','꿈 수유','아기가 자면서도 수유하는 것.','Cúm-sư','Cho trẻ bú sữa trong khi ngủ.',NULL,0,NULL),(19,'모로반사',NULL,'놀라거나 자세 변화로 인해 팔과 다리를 쭉 뻗었다가 모으는 신생아의 반사운동.','Mô-rô-phản-sá','Phản xạ Moro là phản xạ duỗi chân tay khi trẻ sơ sinh bị giật mình.',NULL,0,NULL),(20,'새벽수유',NULL,'아기가 새벽에 일어나서 하는 수유.','Sảy-biếc-sư-du','Cho trẻ bú sữa vào sáng sớm sau khi thức dậy.',NULL,0,NULL),(21,'막수','막 수유','잠들기 전에 마지막으로 하는 수유.','Mạc-sư','Cho trẻ bú sữa cuối cùng trong ngày trước khi ngủ.',NULL,0,NULL),(22,'터미타임',NULL,'아기의 상체 근육을 키우기 위해 엎드려 있는 시간.','Tơ-mi-thời','Thời gian nằm sấp giúp trẻ phát triển cơ bắp ở phần trên cơ thể.',NULL,0,NULL),(23,'통잠',NULL,'마지막 수유 후 아침까지 깨지 않고 길게 자는 것.','Thông-chẩm','Ngủ xuyên đêm sau lần bú cuối cùng.',NULL,0,NULL),(24,'백일의 기적',NULL,'아기가 100일 전후로 통잠을 자기 시작하는 것.','Bách-nhật-dị-kỳ-tích','Phép màu sau 100 ngày là khi trẻ bắt đầu ngủ xuyên đêm.',NULL,0,NULL),(25,'백일의 기절',NULL,'100일이 지나도 아기가 통잠을 자지 않는 경우.','Bách-nhật-dị-kỳ-tắt','Sau 100 ngày mà vẫn không ngủ xuyên đêm.',NULL,0,NULL),(26,'원더윅스',NULL,'생후 20개월 동안 신체적, 정신적으로 급성장하는 10번의 주기.','Nguyên-đơ-uỳch','Wonder Weeks đề cập đến 10 giai đoạn phát triển thể chất và tinh thần của trẻ sơ sinh.',NULL,0,NULL),(27,'애착인형',NULL,'아이들이 항상 가지고 다니며 애착을 가지는 인형.','Ái-trác-nhân-hình','Đồ chơi gắn bó, một món đồ chơi trẻ luôn mang theo.',NULL,0,NULL),(28,'먹놀잠','먹고 놀고 자고','아기의 이상적인 생활 패턴.','Mục-nô-l-chẩm','Ăn, chơi, ngủ là mẫu sinh hoạt lý tưởng cho trẻ.',NULL,0,NULL),(29,'베페','베이비페어','육아 관련 행사나 물품 박람회.','Bê-phê','Hội chợ em bé, nơi trưng bày sản phẩm và sự kiện chăm sóc trẻ.',NULL,0,NULL),(30,'조동','조리원 동기','조리원에서 같이 지낸 친구.','Chô-đông','Bạn cùng phòng dưỡng là những người bạn cùng ở trong một trung tâm chăm sóc sau sinh.',NULL,0,NULL),(31,'육출','육아 출근','육아를 위해 일어나는 것을 출근에 비유한 것.','Dục-xuất','Đi làm nuôi con đề cập đến việc chăm sóc trẻ như việc đi làm hàng ngày.',NULL,0,NULL),(32,'육퇴','육아 퇴근','육아에서 해방되어 자유로운 시간을 갖는 것.','Dục-thối','Tan làm nuôi con, thời gian được nghỉ ngơi sau khi chăm sóc trẻ.',NULL,0,NULL),(33,'자부타임','자유부인타임','육아에서 벗어나 자유 시간을 갖는 엄마를 가리키는 말.','Ja-bu-thời','Thời gian dành cho mẹ giúp các bà mẹ có khoảng thời gian tự do.',NULL,0,NULL),(34,'키카','키즈카페','영유아 놀이시설과 카페를 결합한 공간.','Ki-ka','Khu vui chơi trẻ em kết hợp quán cà phê.',NULL,0,NULL),(35,'문센','문화센터','어린이들이 촉감 놀이 등을 할 수 있는 체험활동 공간.','Môn-sên','Trung tâm văn hóa cung cấp hoạt động trải nghiệm cho trẻ.',NULL,0,NULL),(36,'에잇포켓',NULL,'부모와 친척 8명이 한 아이를 위해 지출하는 상황.','Êít-pô-ket','8 túi tiền mô tả tình trạng cả gia đình đều chi tiền chăm sóc trẻ.',NULL,0,NULL),(37,'골드맘',NULL,'자녀에게 아낌없이 투자하는 엄마.','Gôn-măm','Mẹ vàng là những người mẹ đầu tư không tiếc cho con mình.',NULL,0,NULL),(38,'피딩족',NULL,'경제적 여유로움을 바탕으로 고가의 유아용품을 사는 부모.','Phí-dinh-tộc','Thế hệ Feeding chi nhiều tiền cho sản phẩm trẻ em cao cấp.',NULL,0,NULL),(39,'독박육아',NULL,'다른 도움 없이 한 사람이 아이를 전담해 기르는 것.','Độc-bác-dục-a','Chăm con một mình mô tả tình trạng một người phải tự chăm con.',NULL,0,NULL),(40,'둥둥이','둘째','둘째 아이를 뜻하는 말.','Đung-đung-y','Đứa trẻ thứ hai.',NULL,0,NULL),(41,'셋둥이','셋째','셋째 아이를 뜻하는 말.','Sét-đung-y','Đứa trẻ thứ ba.',NULL,0,NULL),(42,'삭제','자연분만','일반적인 방식으로 태아를 산도를 통해 분만하는 것.','Cha-bun','Sinh thường là phương pháp sinh thông qua âm đạo để thai nhi ra ngoài một cách tự nhiên.',NULL,1,NULL);
/*!40000 ALTER TABLE `slang` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-20  0:46:15
