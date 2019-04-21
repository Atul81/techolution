-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.11-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for tecolution
CREATE DATABASE IF NOT EXISTS `tecolution` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tecolution`;

-- Dumping structure for table tecolution.expense_mng
CREATE TABLE IF NOT EXISTS `expense_mng` (
  `expense_id` int(11) NOT NULL AUTO_INCREMENT,
  `expense_type` int(11) NOT NULL,
  `expense_date` date NOT NULL,
  `expense_amount` double NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`expense_id`),
  KEY `FK_user` (`user_id`),
  KEY `FK_expense_mng_expense_type` (`expense_type`),
  CONSTRAINT `FK_expense_mng_expense_type` FOREIGN KEY (`expense_type`) REFERENCES `expense_type` (`type_id`),
  CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user_mng` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- Dumping data for table tecolution.expense_mng: ~47 rows (approximately)
/*!40000 ALTER TABLE `expense_mng` DISABLE KEYS */;
INSERT INTO `expense_mng` (`expense_id`, `expense_type`, `expense_date`, `expense_amount`, `user_id`) VALUES
	(9, 2, '2019-04-20', 12, 1),
	(10, 6, '2019-04-17', 3600, 1),
	(11, 4, '2019-04-26', 120, 1),
	(12, 1, '2019-04-30', 250000, 1),
	(13, 5, '2019-04-05', 200000, 1),
	(14, 2, '2019-04-22', 12, 2),
	(15, 6, '2019-04-17', 3600, 2),
	(16, 4, '2019-04-28', 1202, 2),
	(17, 1, '2019-04-02', 2500001, 2),
	(18, 5, '2019-04-03', 2000001, 2),
	(19, 2, '2019-04-16', 12000, 1),
	(20, 5, '2019-04-23', 120000, 1),
	(21, 5, '2019-04-21', 120000, 2),
	(22, 4, '2019-04-19', 12000, 1),
	(23, 2, '2019-04-18', 5000, 1),
	(24, 2, '2019-04-18', 2000, 1),
	(25, 1, '2019-04-15', 1000, 1),
	(26, 6, '2019-04-08', 300, 1),
	(27, 1, '2019-04-15', 100, 1),
	(28, 1, '2019-04-25', 50, 1),
	(29, 1, '2019-04-11', 56, 1),
	(30, 2, '2019-04-12', 120, 1),
	(31, 6, '2019-04-21', 2, 1),
	(32, 5, '2019-04-21', 1, 1),
	(33, 4, '2019-04-21', 1, 1),
	(34, 4, '2019-04-21', 2, 1),
	(35, 4, '2019-04-21', 89, 1),
	(36, 4, '2019-04-21', 833, 1),
	(37, 5, '2019-04-21', 343, 1),
	(38, 1, '2019-04-21', 120, 1),
	(39, 1, '2019-04-21', 200, 1),
	(40, 1, '2019-04-21', 70, 1),
	(41, 1, '2019-04-21', 2, 1),
	(42, 1, '2019-04-21', 2, 1),
	(43, 2, '2019-04-21', 2, 1),
	(44, 1, '2019-04-21', 2, 1),
	(45, 5, '2019-04-21', 1, 1),
	(46, 2, '2019-04-21', 2, 1),
	(47, 2, '2019-04-21', 2, 1),
	(48, 3, '2019-04-21', 2, 1),
	(49, 1, '2019-04-21', 1, 1),
	(50, 2, '2019-04-04', 3, 1),
	(51, 1, '2019-04-21', 16, 1),
	(52, 5, '2019-04-21', 110, 1),
	(53, 5, '2019-04-21', 10, 1),
	(54, 3, '2019-04-21', 1200, 1),
	(55, 3, '2019-04-21', 12, 1),
	(56, 3, '2019-04-21', 118781, 1);
/*!40000 ALTER TABLE `expense_mng` ENABLE KEYS */;

-- Dumping structure for table tecolution.expense_type
CREATE TABLE IF NOT EXISTS `expense_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_desc` varchar(50) DEFAULT '0',
  `ct_time` date DEFAULT current_timestamp(),
  `type_prior` double DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table tecolution.expense_type: ~6 rows (approximately)
/*!40000 ALTER TABLE `expense_type` DISABLE KEYS */;
INSERT INTO `expense_type` (`type_id`, `type_desc`, `ct_time`, `type_prior`) VALUES
	(1, 'Shopping', '2019-04-20', 0.3),
	(2, 'Food', '2019-04-20', 0.18),
	(3, 'Dining', '2019-04-20', 0.12),
	(4, 'Travel', '2019-04-20', 0.15),
	(5, 'Entertainment', '2019-04-20', 0.12),
	(6, 'Miscellaneous', '2019-04-20', 0.13);
/*!40000 ALTER TABLE `expense_type` ENABLE KEYS */;

-- Dumping structure for table tecolution.user_mng
CREATE TABLE IF NOT EXISTS `user_mng` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `expense_limit` double NOT NULL DEFAULT 0,
  `user_month` varchar(8) NOT NULL DEFAULT '0',
  `delFlg` varchar(1) NOT NULL DEFAULT 'N',
  `expense_init` double NOT NULL DEFAULT 0,
  `ct_date` date NOT NULL DEFAULT current_timestamp(),
  `user_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table tecolution.user_mng: ~2 rows (approximately)
/*!40000 ALTER TABLE `user_mng` DISABLE KEYS */;
INSERT INTO `user_mng` (`user_id`, `expense_limit`, `user_month`, `delFlg`, `expense_init`, `ct_date`, `user_name`) VALUES
	(1, 7, 'April', 'N', 700, '2019-04-20', 'Atul Pandey'),
	(2, 12000, 'April', 'N', 15000, '2019-04-20', 'System User');
/*!40000 ALTER TABLE `user_mng` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
