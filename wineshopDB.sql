-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Dic 18, 2022 alle 17:10
-- Versione del server: 10.4.25-MariaDB
-- Versione PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wineshop`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `client`
--

CREATE TABLE `client` (
  `Name` char(20) DEFAULT NULL,
  `Surname` char(20) DEFAULT NULL,
  `FiscalCode` char(16) NOT NULL,
  `Email` char(25) DEFAULT NULL,
  `Phone` decimal(10,0) DEFAULT NULL,
  `Address` char(50) DEFAULT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `client`
--

INSERT INTO `client` (`Name`, `Surname`, `FiscalCode`, `Email`, `Phone`, `Address`, `Username`, `Password`) VALUES
('Mattia', 'Rainieri', 'MTTRIN01', 'mat.rai@libero.it', '3321234657', 'via diffidati 40, Fidenza', 'audioservice', 'mattia'),
('Leonardo', 'Zanella', 'ZNLLLNRD01', 'leozan@outlook.it', '980987678', 'via coop, Fidenza', 'zanella', 'karate');

-- --------------------------------------------------------

--
-- Struttura della tabella `employee`
--

CREATE TABLE `employee` (
  `Name` char(20) DEFAULT NULL,
  `Surname` char(20) DEFAULT NULL,
  `FiscalCode` char(16) NOT NULL,
  `Email` char(25) DEFAULT NULL,
  `Phone` decimal(10,0) DEFAULT NULL,
  `Address` char(50) DEFAULT NULL,
  `Admin` tinyint(1) DEFAULT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `employee`
--

INSERT INTO `employee` (`Name`, `Surname`, `FiscalCode`, `Email`, `Phone`, `Address`, `Admin`, `Username`, `Password`) VALUES
('Elena', 'Azzi', '317247', 'ele.azzi@unipr.it', '3318212939', 'strada nuova prati 29, Coltaro', 1, 'elena01', 'ciaoelena'),
('Arianna', 'Cella', '317733', 'ari.cella@unipr.it', '3277417689', 'via milano 100, Salsomaggiore', 1, 'ari01', 'ciaoari'),
('Federico', 'Brandini', 'BRNDFDR02', 'fedebrando@gmail.com', '3324658790', 'via copisteria, Gaiano', 0, 'Fede', 'buondi'),
('Giorgio', 'Coccapani', 'CCCPNGRG01', 'giococca@gmail.com', '5647803920', 'vai busseto 30, Busseto', 0, 'george', 'cocca');

-- --------------------------------------------------------

--
-- Struttura della tabella `purchase`
--

CREATE TABLE `purchase` (
  `PurchaseId` decimal(6,0) NOT NULL,
  `FiscalCode` char(16) DEFAULT NULL,
  `FiscClient` varchar(16) NOT NULL,
  `Address` varchar(60) NOT NULL,
  `WineId` decimal(3,0) DEFAULT NULL,
  `Nbottles` decimal(3,0) DEFAULT NULL,
  `Price` decimal(6,2) DEFAULT NULL,
  `Signature` tinyint(1) DEFAULT NULL,
  `Accepted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `purchase`
--

INSERT INTO `purchase` (`PurchaseId`, `FiscalCode`, `FiscClient`, `Address`, `WineId`, `Nbottles`, `Price`, `Signature`, `Accepted`) VALUES
('1', '9870654', 'ZNLLLNRD01', 'via coop, Fidenza', '2', '10', '305.00', 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `sale`
--

CREATE TABLE `sale` (
  `SaleId` decimal(6,0) NOT NULL,
  `FiscalCode` char(16) DEFAULT NULL,
  `Address` char(50) DEFAULT NULL,
  `WineId` decimal(3,0) DEFAULT NULL,
  `Nbottles` decimal(3,0) DEFAULT NULL,
  `Price` decimal(6,2) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Signature` tinyint(1) DEFAULT NULL,
  `Accepted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `sale`
--

INSERT INTO `sale` (`SaleId`, `FiscalCode`, `Address`, `WineId`, `Nbottles`, `Price`, `Date`, `Signature`, `Accepted`) VALUES
('1', 'MTTRIN01', 'via diffidati 40, Fidenza', '1', '5', '38.00', '2022-12-18', 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `shipper`
--

CREATE TABLE `shipper` (
  `Name` char(20) DEFAULT NULL,
  `Surname` char(20) DEFAULT NULL,
  `FiscalCode` char(16) NOT NULL,
  `Email` char(25) DEFAULT NULL,
  `Phone` decimal(10,0) DEFAULT NULL,
  `Address` char(50) DEFAULT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `shipper`
--

INSERT INTO `shipper` (`Name`, `Surname`, `FiscalCode`, `Email`, `Phone`, `Address`, `Username`, `Password`) VALUES
('Bartolini', 'BRT', '0987', 'bartolinifast@ship.it', '988907890', 'via autostrada 54', 'barto', 'lini'),
('Poste', 'Italiane', '122112', 'ciaoposte@ciao.it', '1234321234', 'via lungoparma 9', 'poste', 'italiane');

-- --------------------------------------------------------

--
-- Struttura della tabella `supplier`
--

CREATE TABLE `supplier` (
  `Name` char(20) DEFAULT NULL,
  `Surname` char(20) DEFAULT NULL,
  `FiscalCode` char(16) NOT NULL,
  `Email` char(25) DEFAULT NULL,
  `Phone` decimal(10,0) DEFAULT NULL,
  `Address` char(50) DEFAULT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `supplier`
--

INSERT INTO `supplier` (`Name`, `Surname`, `FiscalCode`, `Email`, `Phone`, `Address`, `Username`, `Password`) VALUES
('Silvia', 'Bianchi', '9870654', 'silviaWhite@supplier.it', '5547689087', 'via Venezia 5, Reggio Emilia', 'silvia', '123'),
('Gigi', 'Rossi', '987609', 'gigiRed@supplier.it', '3321235467', 'via roma 45, Parma', 'gigi', '321');

-- --------------------------------------------------------

--
-- Struttura della tabella `wine`
--

CREATE TABLE `wine` (
  `WineId` decimal(3,0) NOT NULL,
  `Name` char(30) DEFAULT NULL,
  `Producer` varchar(50) DEFAULT NULL,
  `Origin` varchar(50) DEFAULT NULL,
  `Notes` varchar(200) DEFAULT NULL,
  `Vines` varchar(50) DEFAULT NULL,
  `Year` decimal(4,0) DEFAULT NULL,
  `NSales` decimal(5,0) DEFAULT NULL,
  `Quantity` decimal(5,0) DEFAULT NULL,
  `Quality` decimal(3,1) DEFAULT NULL,
  `Price` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `wine`
--

INSERT INTO `wine` (`WineId`, `Name`, `Producer`, `Origin`, `Notes`, `Vines`, `Year`, `NSales`, `Quantity`, `Quality`, `Price`) VALUES
('1', 'Lambrusco', 'Ceci', 'Emilia Romagna', 'Rosso frizzante', 'Torrile', '2021', '5', '35', '3.0', '7.60'),
('2', 'Pinot Nero', 'Case Gialle', 'Trentino Alto Adige', 'Rosso Rubino, Fermo', 'Borgogna', '2018', '0', '18', '4.5', '30.50'),
('3', 'Barbera d\'Asti', 'Bosco Galli', 'Piemonte', 'Di profumo fruttato e intenso, sprigiona note di mora, lampone, prugna e ciliegia mature.', 'Aliberti', '2020', '0', '15', '4.3', '19.50'),
('4', 'Valpollicella Ripasso', 'Zenato', 'Veneto', 'nasce dalle tipiche uve di Corvina, Rondinella e Oseleta. Vino intenso e corposo, morbido e vellutato ', 'Zenato', '2018', '0', '12', '3.7', '20.90');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`FiscalCode`,`Username`);

--
-- Indici per le tabelle `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`FiscalCode`,`Username`);

--
-- Indici per le tabelle `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`PurchaseId`),
  ADD KEY `FiscalCode` (`FiscalCode`),
  ADD KEY `WineId` (`WineId`),
  ADD KEY `purchase_ibfk_3` (`FiscClient`);

--
-- Indici per le tabelle `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`SaleId`),
  ADD KEY `FiscalCode` (`FiscalCode`),
  ADD KEY `WineId` (`WineId`);

--
-- Indici per le tabelle `shipper`
--
ALTER TABLE `shipper`
  ADD PRIMARY KEY (`FiscalCode`);

--
-- Indici per le tabelle `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`FiscalCode`);

--
-- Indici per le tabelle `wine`
--
ALTER TABLE `wine`
  ADD PRIMARY KEY (`WineId`);

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`FiscalCode`) REFERENCES `supplier` (`FiscalCode`),
  ADD CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`WineId`) REFERENCES `wine` (`WineId`),
  ADD CONSTRAINT `purchase_ibfk_3` FOREIGN KEY (`FiscClient`) REFERENCES `client` (`FiscalCode`);

--
-- Limiti per la tabella `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`FiscalCode`) REFERENCES `client` (`FiscalCode`),
  ADD CONSTRAINT `sale_ibfk_2` FOREIGN KEY (`WineId`) REFERENCES `wine` (`WineId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
