-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-05-2017 a las 21:13:15
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `prestamosneurolab`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dispositivos`
--

CREATE TABLE `dispositivos` (
  `IdDispositivo` int(12) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `EstadoDispositivo` int(12) NOT NULL,
  `Descripcion` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `dispositivos`
--

INSERT INTO `dispositivos` (`IdDispositivo`, `Nombre`, `EstadoDispositivo`, `Descripcion`) VALUES
(1, 'Microscopio', 1, 'Microscopio 1'),
(2, 'Tubo de ensayo', 2, 'Tubo de ensayo 1'),
(3, 'Computador', 1, 'Computador de escritorio'),
(4, 'Computador', 1, 'Computador de escritorio'),
(6, 'Computadora', 1, 'Computador de escritorio'),
(7, 'otro tubo de ensayo', 1, 'no se como se escribe completo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equiposxreserva`
--

CREATE TABLE `equiposxreserva` (
  `IdReserva` int(12) NOT NULL,
  `IdDispositivo` int(12) NOT NULL,
  `EstadoReserva` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosdispositivos`
--

CREATE TABLE `estadosdispositivos` (
  `IdEstadoDispositivo` int(12) NOT NULL,
  `TipoEstadoDispositivo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadosdispositivos`
--

INSERT INTO `estadosdispositivos` (`IdEstadoDispositivo`, `TipoEstadoDispositivo`) VALUES
(1, 'Disponible'),
(2, 'En préstamo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosreservas`
--

CREATE TABLE `estadosreservas` (
  `IdEstadoReserva` int(12) NOT NULL,
  `TipoEstadoReserva` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadosreservas`
--

INSERT INTO `estadosreservas` (`IdEstadoReserva`, `TipoEstadoReserva`) VALUES
(1, 'Pendiente'),
(2, 'Aprobada'),
(3, 'Entregado'),
(4, 'Cancelada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosusuarios`
--

CREATE TABLE `estadosusuarios` (
  `IdEstadoUsuario` int(12) NOT NULL,
  `TipoEstado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadosusuarios`
--

INSERT INTO `estadosusuarios` (`IdEstadoUsuario`, `TipoEstado`) VALUES
(1, 'Activo'),
(2, 'Inactivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `IdReserva` int(12) NOT NULL,
  `EstadoReserva` int(12) NOT NULL,
  `FechaRealizacion` date NOT NULL,
  `HoraInicio` varchar(12) NOT NULL,
  `HoraFinal` varchar(12) NOT NULL,
  `HoraRealizado` varchar(12) NOT NULL,
  `FechaReserva` date NOT NULL,
  `Usuario` varchar(20) NOT NULL,
  `FechaEntrega` date DEFAULT NULL,
  `HoraEntrega` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`IdReserva`, `EstadoReserva`, `FechaRealizacion`, `HoraInicio`, `HoraFinal`, `HoraRealizado`, `FechaReserva`, `Usuario`, `FechaEntrega`, `HoraEntrega`) VALUES
(2, 1, '2017-05-01', '07:00', '08:00', '06:00', '2017-05-02', '222', NULL, NULL),
(3, 1, '2017-05-02', '08:00', '08:00', '08:00', '2017-05-01', '111', NULL, NULL),
(4, 1, '2017-05-03', '06:00', '06:00', '09:00', '1969-12-31', '111', NULL, NULL),
(5, 1, '2017-05-03', '06:00', '06:00', '09:00', '1969-12-31', '111', NULL, NULL),
(6, 1, '2017-05-03', '06:00', '06:00', '09:00', '1969-12-31', '111', NULL, NULL),
(7, 1, '2017-05-03', '06:00', '06:00', '05:05:26', '1969-12-31', '111', NULL, NULL),
(8, 1, '2017-05-03', '06:00', '06:00', '05:06:12', '1969-12-31', '111', NULL, NULL),
(9, 1, '2017-05-03', '06:00', '06:00', '05:17:30', '2017-05-04', '111', NULL, NULL),
(10, 1, '2017-05-03', '06:00', '06:00', '05:21:47', '2017-05-04', '111', NULL, NULL),
(11, 1, '2017-05-03', '06:00', '06:00', '09:11:25', '2017-05-04', '111', NULL, NULL),
(12, 1, '2017-05-04', '06:06:00', '06:08:00', '12:03:46', '2017-05-25', '111', NULL, NULL),
(13, 1, '2017-05-04', '06:06:00', '06:08:00', '12:04:15', '2017-05-25', '111', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `IdRol` int(12) NOT NULL,
  `TipoRol` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`IdRol`, `TipoRol`) VALUES
(1, 'Administrador'),
(2, 'Estudiante');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `Identificacion` varchar(20) NOT NULL,
  `TipoDocumento` varchar(20) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Telefono` varchar(15) NOT NULL,
  `Email` varchar(120) NOT NULL,
  `Login` varchar(15) NOT NULL,
  `Password` varchar(125) NOT NULL,
  `DiasSanciones` int(3) NOT NULL,
  `EstadoUsuario` int(12) NOT NULL,
  `Rol` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Identificacion`, `TipoDocumento`, `Nombre`, `Apellido`, `Telefono`, `Email`, `Login`, `Password`, `DiasSanciones`, `EstadoUsuario`, `Rol`) VALUES
('1038115559', 'CC', 'Yeiffer', 'Herrera', '3187215055', 'yeiferhh@gmail.com', 'yeifer', '12345', 0, 1, 2),
('111', 'cedula', 'Viviana', 'Londoño', '555555', 'sanvilc@hotmail.com', 'sanvilc', '12345', 0, 1, 1),
('222', 'cedula', 'Prueba', 'prueba', '222', 'prueba@gmail.com', 'prueba', 'prueba', 0, 2, 2),
('3333', 'Cedula', 'Sandra Viviana', 'Londoño', '333355', 'prueba1@prueba1.com', 'sanvil', 'sanvilp', 0, 1, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `dispositivos`
--
ALTER TABLE `dispositivos`
  ADD PRIMARY KEY (`IdDispositivo`),
  ADD KEY `EstadoDispositivo` (`EstadoDispositivo`);

--
-- Indices de la tabla `equiposxreserva`
--
ALTER TABLE `equiposxreserva`
  ADD PRIMARY KEY (`IdReserva`,`IdDispositivo`,`EstadoReserva`),
  ADD KEY `IdDispositivo` (`IdDispositivo`),
  ADD KEY `EstadoReserva` (`EstadoReserva`);

--
-- Indices de la tabla `estadosdispositivos`
--
ALTER TABLE `estadosdispositivos`
  ADD PRIMARY KEY (`IdEstadoDispositivo`);

--
-- Indices de la tabla `estadosreservas`
--
ALTER TABLE `estadosreservas`
  ADD PRIMARY KEY (`IdEstadoReserva`);

--
-- Indices de la tabla `estadosusuarios`
--
ALTER TABLE `estadosusuarios`
  ADD PRIMARY KEY (`IdEstadoUsuario`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`IdReserva`),
  ADD KEY `Usuario` (`Usuario`),
  ADD KEY `EstadoReserva` (`EstadoReserva`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`IdRol`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`Identificacion`),
  ADD KEY `EstadoUsuario` (`EstadoUsuario`),
  ADD KEY `Rol` (`Rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `dispositivos`
--
ALTER TABLE `dispositivos`
  MODIFY `IdDispositivo` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `estadosdispositivos`
--
ALTER TABLE `estadosdispositivos`
  MODIFY `IdEstadoDispositivo` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `estadosreservas`
--
ALTER TABLE `estadosreservas`
  MODIFY `IdEstadoReserva` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `estadosusuarios`
--
ALTER TABLE `estadosusuarios`
  MODIFY `IdEstadoUsuario` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `IdReserva` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `IdRol` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `dispositivos`
--
ALTER TABLE `dispositivos`
  ADD CONSTRAINT `dispositivos_ibfk_1` FOREIGN KEY (`EstadoDispositivo`) REFERENCES `estadosdispositivos` (`IdEstadoDispositivo`);

--
-- Filtros para la tabla `equiposxreserva`
--
ALTER TABLE `equiposxreserva`
  ADD CONSTRAINT `equiposxreserva_ibfk_1` FOREIGN KEY (`IdReserva`) REFERENCES `reservas` (`IdReserva`),
  ADD CONSTRAINT `equiposxreserva_ibfk_2` FOREIGN KEY (`IdDispositivo`) REFERENCES `dispositivos` (`IdDispositivo`),
  ADD CONSTRAINT `equiposxreserva_ibfk_3` FOREIGN KEY (`EstadoReserva`) REFERENCES `estadosreservas` (`IdEstadoReserva`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`Usuario`) REFERENCES `usuarios` (`Identificacion`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`EstadoReserva`) REFERENCES `estadosreservas` (`IdEstadoReserva`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`EstadoUsuario`) REFERENCES `estadosusuarios` (`IdEstadoUsuario`),
  ADD CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`Rol`) REFERENCES `roles` (`IdRol`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
