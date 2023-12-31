USE [master]
GO
/****** Object:  Database [onlineticketDB]    Script Date: 4/24/2023 4:39:15 PM ******/
CREATE DATABASE [onlineticketDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'onlineticketDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\onlineticketDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'onlineticketDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\onlineticketDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [onlineticketDB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [onlineticketDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [onlineticketDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [onlineticketDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [onlineticketDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [onlineticketDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [onlineticketDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [onlineticketDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [onlineticketDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [onlineticketDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [onlineticketDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [onlineticketDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [onlineticketDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [onlineticketDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [onlineticketDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [onlineticketDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [onlineticketDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [onlineticketDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [onlineticketDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [onlineticketDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [onlineticketDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [onlineticketDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [onlineticketDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [onlineticketDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [onlineticketDB] SET RECOVERY FULL 
GO
ALTER DATABASE [onlineticketDB] SET  MULTI_USER 
GO
ALTER DATABASE [onlineticketDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [onlineticketDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [onlineticketDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [onlineticketDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [onlineticketDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [onlineticketDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [onlineticketDB] SET QUERY_STORE = ON
GO
ALTER DATABASE [onlineticketDB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [onlineticketDB]
GO
/****** Object:  Table [dbo].[asiento]    Script Date: 4/24/2023 4:39:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[asiento](
	[id] [varchar](255) NOT NULL,
	[id_evento] [int] NULL,
	[precio] [int] NULL,
	[estado] [varchar](50) NULL,
 CONSTRAINT [PK_asiento] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cliente]    Script Date: 4/24/2023 4:39:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cliente](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[rut] [varchar](50) NULL,
	[nombre] [varchar](255) NULL,
	[apellidos] [varchar](255) NULL,
	[direccion] [varchar](255) NULL,
	[correo] [varchar](255) NULL,
	[celular] [varchar](255) NULL,
	[username] [varchar](255) NULL,
	[password] [varchar](255) NULL,
 CONSTRAINT [PK_cliente] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[evento]    Script Date: 4/24/2023 4:39:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[evento](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](255) NULL,
	[fecha] [date] NULL,
	[hora] [time](7) NULL,
	[recinto] [varchar](255) NULL,
	[descripcion] [varchar](255) NULL,
	[cantidadasiento] [int] NULL,
	[cantidaddisponible] [int] NULL,
	[tipoevento] [varchar](255) NULL,
	[foto] [varchar](255) NULL,
 CONSTRAINT [PK_evento] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[reserva]    Script Date: 4/24/2023 4:39:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[reserva](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_evento] [int] NULL,
	[cantidadentradas] [int] NULL,
	[preciounitario] [int] NULL,
	[totalprecio] [int] NULL,
	[fechacompra] [date] NULL,
	[id_cliente] [int] NULL,
	[estado] [varchar](50) NULL,
 CONSTRAINT [PK_reserva] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[asiento] ([id], [id_evento], [precio], [estado]) VALUES (N'1Stand Up CoCo Alegre1000', 1, 30000, N'disponible')
INSERT [dbo].[asiento] ([id], [id_evento], [precio], [estado]) VALUES (N'2Ven Aquí10000', 2, 30000, N'disponible')
INSERT [dbo].[asiento] ([id], [id_evento], [precio], [estado]) VALUES (N'3Blur En Chile 202310000', 3, 25000, N'disponible')
INSERT [dbo].[asiento] ([id], [id_evento], [precio], [estado]) VALUES (N'6The Metal Fest 20232000', 6, 15000, N'disponible')
INSERT [dbo].[asiento] ([id], [id_evento], [precio], [estado]) VALUES (N'7No Te Va Gustar3000', 7, 15000, N'disponible')
INSERT [dbo].[asiento] ([id], [id_evento], [precio], [estado]) VALUES (N'8The Weeknd3000', 8, 25000, N'disponible')
GO
SET IDENTITY_INSERT [dbo].[evento] ON 

INSERT [dbo].[evento] ([id], [nombre], [fecha], [hora], [recinto], [descripcion], [cantidadasiento], [cantidaddisponible], [tipoevento], [foto]) VALUES (1, N'Stand Up CoCo Alegre', CAST(N'2023-05-05' AS Date), CAST(N'21:30:00' AS Time), N'Piedra Roja', N'Evento', 1000, 997, N'stand up', N'cocolegrand.jpg')
INSERT [dbo].[evento] ([id], [nombre], [fecha], [hora], [recinto], [descripcion], [cantidadasiento], [cantidaddisponible], [tipoevento], [foto]) VALUES (2, N'Ven Aquí', CAST(N'2023-05-05' AS Date), CAST(N'21:30:00' AS Time), N'Estadio Ester Roa Rebolledo', N'Los Bunkers en Concepción, vuelve la gran banda penquista con su tour ven aqui', 10000, 9995, N'concierto', N'bunkers.jpg')
INSERT [dbo].[evento] ([id], [nombre], [fecha], [hora], [recinto], [descripcion], [cantidadasiento], [cantidaddisponible], [tipoevento], [foto]) VALUES (3, N'Blur En Chile 2023', CAST(N'2023-11-11' AS Date), CAST(N'21:30:00' AS Time), N'Movistar Arena', N'Queremos que Blur haga una gira mundial nuevamente', 10000, 9986, N'concierto', N'blur.jpg')
INSERT [dbo].[evento] ([id], [nombre], [fecha], [hora], [recinto], [descripcion], [cantidadasiento], [cantidaddisponible], [tipoevento], [foto]) VALUES (6, N'The Metal Fest 2023', CAST(N'2023-04-23' AS Date), CAST(N'21:30:00' AS Time), N'Movistar Arena', N'El primer festival de metal de Chile No te quedes fuera de este esperado reencuentro después de 9 años, con las primeras bandas confirmadas Kreatorofficial, Testamento, Stratovarius y  Benediction Band', 2000, 2000, N'festival', N'metalfest.jpg')
INSERT [dbo].[evento] ([id], [nombre], [fecha], [hora], [recinto], [descripcion], [cantidadasiento], [cantidaddisponible], [tipoevento], [foto]) VALUES (7, N'No Te Va Gustar', CAST(N'2023-07-28' AS Date), CAST(N'21:30:00' AS Time), N'Teatro Caupolicán', N'Notevagustar regresa a Chile con el show que sus fans han esperado por años. El Crecimiento a pulso de la banda uruguaya mas querida en nuestro país se materializará en julio próximo', 3000, 3000, N'concierto', N'notevagustar.jpg')
INSERT [dbo].[evento] ([id], [nombre], [fecha], [hora], [recinto], [descripcion], [cantidadasiento], [cantidaddisponible], [tipoevento], [foto]) VALUES (8, N'The Weeknd', CAST(N'2023-10-15' AS Date), CAST(N'21:30:00' AS Time), N'Estadio Bicentenario de la Florida', N'
The Weeknd
Estadio Bicentenario de la Florida, Santiago de Chile, Chile', 4000, 3999, N'concierto', N'weeknd.png')
SET IDENTITY_INSERT [dbo].[evento] OFF
GO
SET IDENTITY_INSERT [dbo].[reserva] ON 

INSERT [dbo].[reserva] ([id], [id_evento], [cantidadentradas], [preciounitario], [totalprecio], [fechacompra], [id_cliente], [estado]) VALUES (1020, 1, 1, 30000, 30000, CAST(N'2023-04-20' AS Date), 1, N'pagado')
INSERT [dbo].[reserva] ([id], [id_evento], [cantidadentradas], [preciounitario], [totalprecio], [fechacompra], [id_cliente], [estado]) VALUES (1021, 3, 4, 25000, 100000, CAST(N'2023-04-20' AS Date), 1, N'pagado')
SET IDENTITY_INSERT [dbo].[reserva] OFF
GO
ALTER TABLE [dbo].[asiento]  WITH CHECK ADD  CONSTRAINT [FK_asiento_evento] FOREIGN KEY([id_evento])
REFERENCES [dbo].[evento] ([id])
GO
ALTER TABLE [dbo].[asiento] CHECK CONSTRAINT [FK_asiento_evento]
GO
ALTER TABLE [dbo].[reserva]  WITH CHECK ADD  CONSTRAINT [FK_reserva_cliente] FOREIGN KEY([id_cliente])
REFERENCES [dbo].[cliente] ([id])
GO
ALTER TABLE [dbo].[reserva] CHECK CONSTRAINT [FK_reserva_cliente]
GO
ALTER TABLE [dbo].[reserva]  WITH CHECK ADD  CONSTRAINT [FK_reserva_evento] FOREIGN KEY([id_evento])
REFERENCES [dbo].[evento] ([id])
GO
ALTER TABLE [dbo].[reserva] CHECK CONSTRAINT [FK_reserva_evento]
GO
USE [master]
GO
ALTER DATABASE [onlineticketDB] SET  READ_WRITE 
GO
