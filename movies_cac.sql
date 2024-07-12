-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-07-2024 a las 23:02:23
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `movies_cac`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `directores`
--

CREATE TABLE `directores` (
  `id_director` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `edad` int(11) NOT NULL,
  `nacionalidad` varchar(50) NOT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `directores`
--

INSERT INTO `directores` (`id_director`, `nombre`, `apellido`, `edad`, `nacionalidad`, `activo`) VALUES
(1, 'Juan', 'García', 45, 'Argentino', 1),
(2, 'María', 'López', 38, 'Mexicana', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movies`
--

CREATE TABLE `movies` (
  `idPelicula` int(11) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `fechaLanzamiento` date DEFAULT NULL,
  `genero` varchar(50) NOT NULL,
  `duracion` int(11) NOT NULL,
  `reparto` varchar(150) DEFAULT NULL,
  `sinapsis` varchar(200) DEFAULT NULL,
  `director` int(11) DEFAULT NULL,
  `imagen` varchar(150) NOT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movies`
--

INSERT INTO `movies` (`idPelicula`, `titulo`, `fechaLanzamiento`, `genero`, `duracion`, `reparto`, `sinapsis`, `director`, `imagen`, `activo`) VALUES
(2, 'Pelicula 1', '2024-07-12', 'Acción', 120, 'Actor A, Actor B', 'Sinapsis de la película 1', 1, 'imagen1.jpg', 0),
(3, 'Pelicula 2', '2024-08-15', 'Drama', 150, 'Actriz C, Actor D', 'Sinapsis de la película 2', 2, 'imagen2.jpg', 1),
(4, 'Inception', '2010-07-16', 'Science Fiction', 148, 'Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.', 1, 'inception.jpg', 1),
(5, 'The Matrix', '1999-03-31', 'Action', 136, 'Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss', 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.', 2, 'matrix.jpg', 0),
(13, 'Titanic', '1998-01-01', 'Romance', 195, 'Leonardo DiCaprio, Kate Winslet', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.', 1, 'titanic.jpg', 1),
(14, 'El mago de oz', '1998-01-01', 'accion', 195, 'actores', '', 1, 'imagen.jpg', 1),
(15, 'Pokemon safiro', '1998-01-01', 'accion', 60, 'pikachu', '', 1, 'imagen.jpg', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `directores`
--
ALTER TABLE `directores`
  ADD PRIMARY KEY (`id_director`);

--
-- Indices de la tabla `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`idPelicula`),
  ADD KEY `director` (`director`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `directores`
--
ALTER TABLE `directores`
  MODIFY `id_director` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `movies`
--
ALTER TABLE `movies`
  MODIFY `idPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `movies`
--
ALTER TABLE `movies`
  ADD CONSTRAINT `movies_ibfk_1` FOREIGN KEY (`director`) REFERENCES `directores` (`id_director`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
