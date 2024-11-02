-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 02, 2024 at 06:01 PM
-- Server version: 8.2.0
-- PHP Version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_spring_mybatis`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_color`
--

DROP TABLE IF EXISTS `tb_color`;
CREATE TABLE IF NOT EXISTS `tb_color` (
  `color_id` int NOT NULL,
  `one` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `two` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `three` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`color_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_color`
--

INSERT INTO `tb_color` (`color_id`, `one`, `two`, `three`) VALUES
(1, 'Red', 'Yellow', 'Black'),
(2, 'Black', 'Brown', 'Pink'),
(3, 'White', 'Black', 'Blue');

-- --------------------------------------------------------

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE IF NOT EXISTS `tb_product` (
  `pro_id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `price` float NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `color_id` int NOT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_product`
--

INSERT INTO `tb_product` (`pro_id`, `name`, `price`, `image`, `color_id`) VALUES
(2, 'Iphone', 600.75, '28619_Screenshot (30).png', 2),
(1, 'Computer', 900.49, '28830_2023-06-27.png', 1),
(3, 'Table', 45, '18974_Screenshot 2023-06-29 173037.png', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE IF NOT EXISTS `tb_user` (
  `user_id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `photos` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `pro_id` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`user_id`, `name`, `gender`, `email`, `photos`, `pro_id`) VALUES
(1, 'Snoopy', 'Male', 'snoopy@gmail.com', '639269_ha.jpg', 1),
(2, 'Ironman', 'Male', 'Ironman@gmail.com', '098819d5-f032-4606-bb1a-9ec410e53298_Ironman.jpg', 2),
(3, 'wonderwomen', 'Female', 'wonderwomen@gmail.com', '822169_monder momen.jpg', 3);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
