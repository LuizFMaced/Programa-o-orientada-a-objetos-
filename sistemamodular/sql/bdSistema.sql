-- phpMyAdmin SQL Dump
-- version 3.4.9
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tempo de Geração: 14/09/2016 às 02h48min
-- Versão do Servidor: 5.5.20
-- Versão do PHP: 5.3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `bdsistema`
--
CREATE DATABASE `bdsistema` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bdsistema`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `CPF` varchar(13) NOT NULL,
  `RG` varchar(9) DEFAULT NULL,
  `NOME` varchar(100) DEFAULT NULL,
  `DATANASCIMENTO` varchar(10) DEFAULT NULL,
  `ENDERECO` varchar(100) DEFAULT NULL,
  `BAIRRO` varchar(30) DEFAULT NULL,
  `CIDADE` varchar(30) DEFAULT NULL,
  `ESTADO` varchar(30) DEFAULT NULL,
  `CEP` varchar(8) DEFAULT NULL,
  `TELEFONE` varchar(13) DEFAULT NULL,
  `EMAIL` varchar(100) DEFAULT NULL,
  `FOTO` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`CPF`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`CPF`, `RG`, `NOME`, `DATANASCIMENTO`, `ENDERECO`, `BAIRRO`, `CIDADE`, `ESTADO`, `CEP`, `TELEFONE`, `EMAIL`, `FOTO`) VALUES
('12345678900', '123456789', 'João Ortiz', '08/06/0198', 'Rua Tuiuti, 223', 'Tatuapé', 'São Paulo', 'São Paulo', '03480000', '1129921225', 'jportiz@prof.ung.br', NULL),
('98765432100', '987654321', 'Aline Souza', '26/12/1994', 'Av. Celso Garcia, 133', 'Moóca', 'São Paulo', 'São Paulo', '03150000', '1199656985', 'asouza@prof.ung.br', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
