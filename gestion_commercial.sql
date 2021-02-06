-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 06 fév. 2021 à 21:20
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_commercial`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `codeClient` varchar(255) NOT NULL,
  `matriculeFiscale` varchar(255) NOT NULL,
  `raisonSociale` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephoneFixe` int(11) NOT NULL,
  `telephoneMobile` int(11) NOT NULL,
  `fax` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `website` varchar(255) NOT NULL,
  `etatFiscale` varchar(255) NOT NULL,
  `comptesBancaire` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`codeClient`, `matriculeFiscale`, `raisonSociale`, `adresse`, `telephoneFixe`, `telephoneMobile`, `fax`, `email`, `website`, `etatFiscale`, `comptesBancaire`) VALUES
('c1 ', 'azeaze', 'ezaze', 'aazeaz', 212121, 1, 112, '254855', '121545', '1', 1),
('c2', 'bbbbb', 'aaa', 'aaa', 121212, 131313, 101010, 'aze@gmail.com', 'aze1.com', '1', 2),
('123', 'abc', 'abc', 'abc', 122, 122, 122, 'abc', 'abc', 'abc', 123);

-- --------------------------------------------------------

--
-- Structure de la table `comptesbancaires`
--

CREATE TABLE `comptesbancaires` (
  `RIB` int(11) NOT NULL,
  `banque` varchar(255) NOT NULL,
  `agence` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `comptesbancaires`
--

INSERT INTO `comptesbancaires` (`RIB`, `banque`, `agence`) VALUES
(1, 'STB', 'Banque');

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `matriculeFiscale` varchar(255) NOT NULL,
  `raisonSociale` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `comptesBancaire` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephoneFixe` int(11) NOT NULL,
  `telephoneMobile` int(11) NOT NULL,
  `fax` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `website` varchar(255) NOT NULL,
  `etatFiscale` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`matriculeFiscale`, `raisonSociale`, `type`, `description`, `comptesBancaire`, `adresse`, `telephoneFixe`, `telephoneMobile`, `fax`, `email`, `website`, `etatFiscale`) VALUES
('aze1', 'eza1', 'aze', '1za1', 154885, '21252aze', 121212, 121252, 254125, 'aze@gmail.com', 'aze1', '1'),
('aze2', 'eza1', 'aze', '1za1', 154885, '21252aze', 121212, 121252, 254125, 'aze@gmail.com', 'aze1', '1'),
('aze3', 'eee1', 'aze', '1za1', 154885, '21252aze', 121212, 121252, 254125, 'aze@gmail.com', 'aze1', '1');

-- --------------------------------------------------------

--
-- Structure de la table `familleproduit`
--

CREATE TABLE `familleproduit` (
  `codeFamille` int(11) NOT NULL,
  `designationFamille` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `familleproduit`
--

INSERT INTO `familleproduit` (`codeFamille`, `designationFamille`) VALUES
(1, 'famille2'),
(9, 'hayakou');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `codeFournisseur` varchar(255) NOT NULL,
  `matriculeFiscale` varchar(255) NOT NULL,
  `raisonSociale` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephoneFixe` int(11) NOT NULL,
  `telephoneMobile` int(11) NOT NULL,
  `fax` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `website` varchar(255) NOT NULL,
  `etatFiscale` varchar(255) NOT NULL,
  `comptesBancaire` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`codeFournisseur`, `matriculeFiscale`, `raisonSociale`, `adresse`, `telephoneFixe`, `telephoneMobile`, `fax`, `email`, `website`, `etatFiscale`, `comptesBancaire`) VALUES
('1', '222', 'zeaz', '15az', 152200, 1, 1, 'azezze@fg.com', 'azeaze', '1', 12555),
('2', 'aze2', 'azee', '12a2aze', 121212, 121212, 121212, 'aze@gmail.com', 'aze.xom', 'aze1', 32),
('3', 'bbbb', 'bbbb', '32az', 323232, 222222, 121212, 'aze@gmail.com', 'aze1.com', 'aze1', 12),
('123', 'abc', 'abc', 'abc', 12, 121, 122, 'abc', 'abc', 'abc', 123);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `reference` varchar(255) NOT NULL,
  `designation` varchar(255) NOT NULL,
  `uniteMesure` varchar(255) NOT NULL,
  `fournisseur` varchar(255) NOT NULL,
  `familleProduit` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `stockMinimal` int(11) NOT NULL,
  `prixUnitaireHTaxe` int(11) NOT NULL,
  `tva` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`reference`, `designation`, `uniteMesure`, `fournisseur`, `familleProduit`, `stock`, `stockMinimal`, `prixUnitaireHTaxe`, `tva`) VALUES
('1222222', 'cab', 'kg', '123', 9, 122, 122, 322, 20);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD UNIQUE KEY `reference` (`reference`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
