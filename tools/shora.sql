-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2025 at 05:47 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shora`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_playlist`
--

CREATE TABLE `detail_playlist` (
  `id_detail` int(9) NOT NULL,
  `id_playlist` int(9) NOT NULL,
  `id_lagu` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lagu`
--

CREATE TABLE `lagu` (
  `id_lagu` int(9) NOT NULL,
  `judul_lagu` varchar(40) NOT NULL,
  `artis` varchar(30) NOT NULL,
  `tgl_rilis` date DEFAULT NULL,
  `link` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `lagu`
--

INSERT INTO `lagu` (`id_lagu`, `judul_lagu`, `artis`, `tgl_rilis`, `link`) VALUES
(1, 'Love Scenario', 'iKON', '2018-01-25', '/songs/IKON_LOVE SCENARIO.mp3'),
(2, 'My Youth', 'NCT Dream', '2021-05-10', '/songs/NCT Dream_My Youth.mp3'),
(3, 'BOOM', 'NCT Dream', '2019-07-29', '/songs/NCT Dream_BOOM.mp3'),
(4, 'Night Changes', 'One Direction', '2014-11-14', '/songs/One Direction_Night Changes.mp3'),
(5, 'Beatbox', 'NCT Dream', '2022-05-30', '/songs/NCT Dream_Beatbox.mp3'),
(7, 'Perfect', 'One Direction', '2015-10-16', '/songs/One Direction_Perfect.mp3'),
(8, 'We Go Up', 'NCT Dream', '2018-03-09', '/songs/NCT Dream_We Go Up.mp3'),
(9, 'Going Crazy', 'TREASURE', '2020-01-31', '/songs/TREASURE_Going Crazy.mp3'),
(10, 'Life is Still Going On', 'NCT Dream', '2021-06-25', '/songs/NCT Dream_Life is Still Going on.'),
(11, 'SHOUT OUT', 'ENHYPEN', '2022-07-04', '/songs/ENHYPEN_SHOUT OUT.mp3'),
(12, 'Shut Down', 'BLACKPINK', '2022-09-16', '/songs/BLACKPINK_Shut Down.mp3'),
(13, 'Bite Me', 'ENHYPEN', '2023-05-22', '/songs/ENHYPEN_Bite Me.mp3'),
(14, 'Run BTS', 'BTS', '2022-11-13', '/songs/BTS_RUN BTS.mp3'),
(15, 'Ridin\'', 'NCT Dream', '2020-04-30', '/songs/NCT Dream_Ridin\'.mp3'),
(16, 'LALALA', 'Stray Kids', '2023-11-10', '/songs/Stray Kids_LALALA.mp3'),
(17, 'Charmer', 'Stray Kids', '2022-03-18', '/songs/Stray Kids_Charmer.mp3'),
(18, 'LALISA', 'LISA', '2021-09-10', '/songs/LISA_LALISA.mp3'),
(19, 'FIRE', 'BTS', '2016-05-02', '/songs/BTS_FIRE.mp3'),
(20, 'BOSS', 'NCT U', '2018-02-19', '/songs/NCT U_BOSS.mp3'),
(21, 'Monster', 'EXO', '2016-09-06', '/songs/EXO_Monster.mp3');

-- --------------------------------------------------------

--
-- Table structure for table `likes`
--

CREATE TABLE `likes` (
  `id_likes` int(9) NOT NULL,
  `id_user` int(9) NOT NULL,
  `id_lagu` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `playlist`
--

CREATE TABLE `playlist` (
  `id_ playlist` int(9) NOT NULL,
  `id_user` int(9) NOT NULL,
  `judul_playlist` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(9) NOT NULL,
  `nama_user` varchar(40) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_playlist`
--
ALTER TABLE `detail_playlist`
  ADD PRIMARY KEY (`id_detail`);

--
-- Indexes for table `lagu`
--
ALTER TABLE `lagu`
  ADD PRIMARY KEY (`id_lagu`);

--
-- Indexes for table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id_likes`);

--
-- Indexes for table `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`id_ playlist`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_playlist`
--
ALTER TABLE `detail_playlist`
  MODIFY `id_detail` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lagu`
--
ALTER TABLE `lagu`
  MODIFY `id_lagu` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `likes`
--
ALTER TABLE `likes`
  MODIFY `id_likes` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `playlist`
--
ALTER TABLE `playlist`
  MODIFY `id_ playlist` int(9) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(9) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
