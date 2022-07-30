-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 05, 2020 at 04:38 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Cekanje_MNE`
--

-- --------------------------------------------------------

--
-- Table structure for table `Firme`
--

CREATE TABLE `Firme` (
  `id` int(11) NOT NULL,
  `Naziv` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Firme`
--

INSERT INTO `Firme` (`id`, `Naziv`) VALUES
(1, 'Lovcen Banka'),
(3, 'Zapad Banka'),
(4, 'Atlas Banka'),
(5, 'Universal Capital Banka'),
(6, 'Erste Banka'),
(7, 'Prva Banka CG'),
(8, 'Ziraat Banka'),
(9, 'Invest Banka Montenegro'),
(10, 'Podgoricka Banka'),
(11, 'Hipotekarna Banka'),
(12, 'Crnogorska komercijalna banka'),
(13, 'NLB Banka'),
(14, 'Addiko Banka'),
(15, 'NOVA Banka'),
(16, 'Komercijalna Banka'),
(17, 'Ministarstvo Unutrašnjih Poslova'),
(18, 'Telekom'),
(19, 'Mtel'),
(20, 'Telenor');

-- --------------------------------------------------------

--
-- Table structure for table `firme_objekata`
--

CREATE TABLE `firme_objekata` (
  `id` int(11) NOT NULL,
  `fk_firme` int(11) NOT NULL,
  `fk_objekta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `firme_objekata`
--

INSERT INTO `firme_objekata` (`id`, `fk_firme`, `fk_objekta`) VALUES
(1, 1, 2),
(3, 3, 2),
(4, 4, 2),
(5, 5, 2),
(6, 6, 2),
(7, 7, 2),
(8, 8, 2),
(9, 9, 2),
(10, 10, 2),
(11, 11, 2),
(12, 12, 2),
(13, 13, 2),
(14, 14, 2),
(15, 15, 2),
(16, 16, 2),
(17, 17, 1),
(18, 18, 3),
(19, 19, 3),
(20, 20, 3);

-- --------------------------------------------------------

--
-- Table structure for table `Gradovi`
--

CREATE TABLE `Gradovi` (
  `id` int(11) NOT NULL,
  `Naziv` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Gradovi`
--

INSERT INTO `Gradovi` (`id`, `Naziv`) VALUES
(1, 'Podgorica'),
(2, 'Niksic'),
(3, 'Pljevlja'),
(4, 'Bijelo Polje'),
(5, 'Cetinje'),
(6, 'Bar'),
(7, 'Herceg Novi'),
(8, 'Berane'),
(9, 'Budva'),
(10, 'Ulcinj'),
(11, 'Tivat'),
(12, 'Rozaje'),
(13, 'Kotor'),
(14, 'Danilovgrad'),
(15, 'Mojkovac'),
(16, 'Plav'),
(17, 'Kolasin'),
(18, 'Zabljak'),
(19, 'Pluzine'),
(20, 'Andrijevica'),
(21, 'Savnik'),
(22, 'Petnjica'),
(23, 'Tuzi'),
(24, 'Gusinje');

-- --------------------------------------------------------

--
-- Table structure for table `Korisnici`
--

CREATE TABLE `Korisnici` (
  `id` int(11) NOT NULL,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Korisnici`
--

INSERT INTO `Korisnici` (`id`, `Ime`, `Prezime`) VALUES
(3, 'Nikola', 'Burzanovic'),
(13, 'Mihailo', 'Damjanovic'),
(15, 'Marko', 'Markovic'),
(17, 'Petar', 'Vuksanovic'),
(25, 'Jovan', 'Vukovic'),
(37, 'Petar', 'Bulatovic'),
(46, 'Nikola', 'Stamatovic'),
(50, 'Ivan', 'Bojovic'),
(66, 'Matija', 'Maras'),
(87, 'Veljko', 'Ivanovic'),
(237, 'Janko', 'Jankovic');

-- --------------------------------------------------------

--
-- Table structure for table `Lokacije`
--

CREATE TABLE `Lokacije` (
  `id` int(11) NOT NULL,
  `adresa/naziv` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Lokacije`
--

INSERT INTO `Lokacije` (`id`, `adresa/naziv`) VALUES
(1, 'Bulevar Dzordza Vasingtona, br 56/I, Podgorica 81000'),
(2, '62/2 Bulevar Ivana Crnojevica, Podgorica 81000, Montenegro'),
(3, 'Cetinjski Put, Podgorica, Montenegro'),
(4, 'Moskovska br. 2b/VII\r\n81000 Podgorica'),
(5, 'II sprat, bb Vaka Djurovica, Podgorica 81000, Montenegro'),
(6, 'Miljana Vukova, Podgorica, Montenegro'),
(7, 'Stanka Dragojevica, Podgorica 81000, Montenegro'),
(8, '35 Bulevar Svetog Petra Cetinjskog, Podgorica, Montenegro'),
(9, '85 Slobode, Podgorica, Montenegro'),
(10, '46 Marka Miljanova, Podgorica, Montenegro'),
(11, '2A Arsenija Boljevica, Podgorica 81000, Montenegro'),
(12, 'Bulevar Pera Cetkovica, Podgorica, Montenegro'),
(13, '11 Studentska, Podgorica, Montenegro'),
(14, '141 Bulevar Svetog Petra Cetinjskog, Podgorica, Montenegro'),
(15, 'bb Vuka Karadzica, 81000Podgorica, Montenegro'),
(16, '77 Bulevar Ivana Crnojevica, Podgorica'),
(17, '115 Bulevar Svetog Petra Cetinjskog, Podgorica, Montenegro'),
(18, '45 Bulevar Svetog Petra Cetinjskog, Podgorica 81000, Montenegro'),
(19, 'Cetinjski Put, Delta City Shopping mall, Podgorica 81000, Montenegro'),
(20, '28 Bratstva i Jedinstva, Podgorica 81000, Montenegro'),
(21, '21 Josipa Broza Tita, Podgorica 81000, Montenegro'),
(22, '323 Kralja Nikole, Podgorica 81000, Montenegro'),
(23, 'Trg nezavisnosti 35, 81000, Montenegro'),
(24, '2D Moskovska, Podgorica 81000, Montenegro'),
(25, '67 Josipa Broza Tita, Podgorica 81000, Montenegro'),
(26, '91 Slobode, Podgorica, Montenegro'),
(27, 'Moskovska, Podgorica, Montenegro'),
(28, '46 Bulevar Stanka Dragojevica, Podgorica, Montenegro'),
(29, 'Cetinjski put,preko puta Delte'),
(30, '56 Hercegovacka, Podgorica, Montenegro'),
(31, '98 Bulevar Dzordza Vasingtona, Podgorica, Montenegro'),
(32, 'Bazar, 8 Blaza Jovanovica, Podgorica, Montenegro'),
(33, '37 Bulevar Svetog Petra Cetinjskog, Podgorica, Montenegro'),
(34, 'Marka Miljanova, Podgorica, Montenegro'),
(35, '22 Bulevar Svetog Petra Cetinjskog, Podgorica'),
(36, 'br. 74 Bulevar Svetog Petra Cetinjskog, Podgorica'),
(37, '7 Miljana Vukova, Podgorica'),
(38, '6-14 Novaka Miloševa, Podgorica '),
(39, 'Golubovci'),
(40, '21 Kralja Nikole, Podgorica'),
(41, '74 Slobode, Podgorica 81110'),
(42, ' Rimski Trg, Podgorica'),
(43, 'Cetinjski Put, Podgorica 81110'),
(44, ' Moskovska, Podgorica 81000'),
(45, 'bb Trg Republike, Podgorica 81000'),
(46, '4 Rimski Trg, Podgorica 81000'),
(47, '80 Slobode, Podgorica 81000'),
(48, ' Josipa Broza Tita bb, Podgorica 81000'),
(49, '29 Moskovska, Podgorica'),
(50, 'Cetinjski put bb, Delta City, Podgorica 81000');

-- --------------------------------------------------------

--
-- Table structure for table `lokacije_firmi`
--

CREATE TABLE `lokacije_firmi` (
  `id` int(11) NOT NULL,
  `fk_firme` int(11) NOT NULL,
  `fk_lokacije` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lokacije_firmi`
--

INSERT INTO `lokacije_firmi` (`id`, `fk_firme`, `fk_lokacije`) VALUES
(1, 1, 1),
(2, 1, 2),
(4, 3, 4),
(5, 4, 5),
(6, 4, 6),
(7, 5, 7),
(8, 5, 8),
(9, 5, 9),
(10, 6, 10),
(11, 6, 11),
(12, 6, 12),
(13, 7, 13),
(14, 7, 14),
(15, 7, 15),
(16, 8, 16),
(17, 9, 17),
(18, 10, 18),
(19, 10, 19),
(20, 10, 20),
(21, 10, 21),
(22, 10, 22),
(23, 10, 23),
(24, 10, 24),
(25, 11, 25),
(26, 11, 26),
(27, 12, 27),
(28, 13, 28),
(29, 13, 29),
(30, 14, 30),
(31, 14, 31),
(32, 14, 32),
(33, 14, 33),
(34, 15, 34),
(35, 17, 35),
(36, 16, 36),
(37, 16, 37),
(38, 12, 38),
(39, 12, 39),
(40, 19, 40),
(41, 19, 41),
(42, 19, 42),
(43, 19, 43),
(44, 20, 44),
(45, 20, 45),
(46, 20, 46),
(47, 18, 47),
(48, 18, 48),
(49, 18, 49),
(50, 18, 50);

-- --------------------------------------------------------

--
-- Table structure for table `Objekti`
--

CREATE TABLE `Objekti` (
  `id` int(11) NOT NULL,
  `Naziv` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Objekti`
--

INSERT INTO `Objekti` (`id`, `Naziv`) VALUES
(1, 'MUP'),
(2, 'Banke'),
(3, 'Telekomunikacioni servisi'),
(4, 'Poste');

-- --------------------------------------------------------

--
-- Table structure for table `objekti_u_gradovima`
--

CREATE TABLE `objekti_u_gradovima` (
  `id` int(11) NOT NULL,
  `fk_objekta` int(11) NOT NULL,
  `fk_grada` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Usluge`
--

CREATE TABLE `Usluge` (
  `id` int(11) NOT NULL,
  `Naziv` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `usluge_firmi`
--

CREATE TABLE `usluge_firmi` (
  `id` int(11) NOT NULL,
  `fk_usluge` int(11) NOT NULL,
  `fk_firme` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Firme`
--
ALTER TABLE `Firme`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `firme_objekata`
--
ALTER TABLE `firme_objekata`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_firme` (`fk_firme`),
  ADD KEY `fk_objekta` (`fk_objekta`);

--
-- Indexes for table `Gradovi`
--
ALTER TABLE `Gradovi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Korisnici`
--
ALTER TABLE `Korisnici`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Lokacije`
--
ALTER TABLE `Lokacije`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lokacije_firmi`
--
ALTER TABLE `lokacije_firmi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_firme` (`fk_firme`),
  ADD KEY `fk_lokacije` (`fk_lokacije`);

--
-- Indexes for table `Objekti`
--
ALTER TABLE `Objekti`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `objekti_u_gradovima`
--
ALTER TABLE `objekti_u_gradovima`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_gradova` (`fk_grada`),
  ADD KEY `id_objekata` (`fk_objekta`);

--
-- Indexes for table `Usluge`
--
ALTER TABLE `Usluge`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usluge_firmi`
--
ALTER TABLE `usluge_firmi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_firme` (`fk_firme`),
  ADD KEY `fk_usluge` (`fk_usluge`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Firme`
--
ALTER TABLE `Firme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `firme_objekata`
--
ALTER TABLE `firme_objekata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `Gradovi`
--
ALTER TABLE `Gradovi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `Lokacije`
--
ALTER TABLE `Lokacije`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `lokacije_firmi`
--
ALTER TABLE `lokacije_firmi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `Objekti`
--
ALTER TABLE `Objekti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `objekti_u_gradovima`
--
ALTER TABLE `objekti_u_gradovima`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Usluge`
--
ALTER TABLE `Usluge`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usluge_firmi`
--
ALTER TABLE `usluge_firmi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `firme_objekata`
--
ALTER TABLE `firme_objekata`
  ADD CONSTRAINT `firme_objekata_ibfk_1` FOREIGN KEY (`fk_firme`) REFERENCES `Firme` (`id`),
  ADD CONSTRAINT `firme_objekata_ibfk_2` FOREIGN KEY (`fk_objekta`) REFERENCES `Objekti` (`id`);

--
-- Constraints for table `lokacije_firmi`
--
ALTER TABLE `lokacije_firmi`
  ADD CONSTRAINT `lokacije_firmi_ibfk_1` FOREIGN KEY (`fk_firme`) REFERENCES `Firme` (`id`),
  ADD CONSTRAINT `lokacije_firmi_ibfk_2` FOREIGN KEY (`fk_lokacije`) REFERENCES `Lokacije` (`id`);

--
-- Constraints for table `objekti_u_gradovima`
--
ALTER TABLE `objekti_u_gradovima`
  ADD CONSTRAINT `objekti_u_gradovima_ibfk_1` FOREIGN KEY (`fk_grada`) REFERENCES `Gradovi` (`id`),
  ADD CONSTRAINT `objekti_u_gradovima_ibfk_2` FOREIGN KEY (`fk_objekta`) REFERENCES `Objekti` (`id`);

--
-- Constraints for table `usluge_firmi`
--
ALTER TABLE `usluge_firmi`
  ADD CONSTRAINT `usluge_firmi_ibfk_1` FOREIGN KEY (`fk_firme`) REFERENCES `Firme` (`id`),
  ADD CONSTRAINT `usluge_firmi_ibfk_2` FOREIGN KEY (`fk_usluge`) REFERENCES `Usluge` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
