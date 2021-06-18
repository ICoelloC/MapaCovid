-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-06-2021 a las 11:52:55
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mapacovid`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencias`
--

CREATE TABLE `incidencias` (
  `id` int(11) NOT NULL,
  `region` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `infectados` int(11) NOT NULL,
  `fallecidos` int(11) NOT NULL,
  `dadosAlta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `incidencias`
--

INSERT INTO `incidencias` (`id`, `region`, `fecha`, `infectados`, `fallecidos`, `dadosAlta`) VALUES
(6, 5, '2021-06-14', 5, 2, 10),
(7, 1, '2021-06-07', 9, 1, 3),
(8, 3, '2021-06-01', 6, 0, 1),
(9, 4, '2021-06-18', 4, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `regiones`
--

CREATE TABLE `regiones` (
  `id` int(11) NOT NULL,
  `region` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `regiones`
--

INSERT INTO `regiones` (`id`, `region`) VALUES
(1, 'Ciudad Real'),
(2, 'Toledo'),
(3, 'Cuenca'),
(4, 'Albacete'),
(6, 'Guadalajara');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `tipo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `tipo`) VALUES
(1, 'admin'),
(2, 'gestor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nick` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `clave` blob NOT NULL,
  `rol` int(11) NOT NULL,
  `clavePub` blob NOT NULL,
  `clavePriv` blob NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_b`
--

CREATE TABLE `usuarios_b` (
  `id` int(11) NOT NULL,
  `nick` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `rol` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios_b`
--

INSERT INTO `usuarios_b` (`id`, `nick`, `email`, `pass`, `rol`, `activo`) VALUES
(1, 'ICoelloC', 'icoello@gmail.com', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 1, 1),
(3, 'Admin', 'admin@admin.com', 'd033e22ae348aeb5660fc2140aec35850c4da997', 1, 1),
(4, 'Otro', 'otro@otro.com', 'a1da106dfc67e74b885d8ae72de62d41ce5278cc', 1, 1),
(6, 'GestorAPP', 'gestor@gestor.com', 'f7ff3c8ea53e68bd308e6deed6a01a98c7886308', 2, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `incidencias`
--
ALTER TABLE `incidencias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `regiones`
--
ALTER TABLE `regiones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios_b`
--
ALTER TABLE `usuarios_b`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `incidencias`
--
ALTER TABLE `incidencias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `regiones`
--
ALTER TABLE `regiones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios_b`
--
ALTER TABLE `usuarios_b`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
