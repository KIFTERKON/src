create database Grupo_Izagar
use Grupo_Izagar

create table tb_establecimiento(
	folio int primary key,
	nombre varchar(20),
	abreviatura char(5),
	status int
)

create table tb_puesto(
	folio int identity primary key,
	nombre varchar(20),
	abreviatura char(5),
	status int
);

create table tb_sueldo(
	folio int identity primary key,
	sueldo float,
	abreviatura char(5),
	status int
);

create table tb_bono(
	folio int identity primary key,
	bono float,
	abreviatura char(5),
	status int
);

create table tb_empleado(
	folio int identity primary key,
	no_checador int,
	nombre varchar(20),
	ap_paterno varchar(20),
	ap_materno varchar(20),
	establecimiento_id int,
	puesto_id int,
	sueldo_id int,
	bono_id int,
	fuente_sodas int,
	gafete int,
	status int,
	fecha varchar(30),

	foreign key (establecimiento_id) references tb_establecimiento(folio),
	foreign key (puesto_id) references tb_puesto(folio),
	foreign key (sueldo_id) references tb_sueldo(folio),
	foreign key (bono_id) references tb_bono(folio)
	
);

create table tb_permiso(
	folio int primary key identity,
	nombre varchar(50),
	status int
);

create table tb_usuario(
	folio int primary key identity,
	nombre_completo varchar(52),
	contrasena varchar(32),
	permiso_id int,
	fecha varchar(30),
	fecha_actu varchar(30),
	status int,
	foreign key (permiso_id) references tb_permiso(folio)
);

select * from tb_fuente_sodas_rh
create table tb_fuente_sodas_rh(
	folio int primary key identity,
	ticket varchar(15),
	nombre_completo varchar(120),
	cantidad money,
	fecha varchar(14),
	status int
);

create table tb_fuente_sodas_auxf(
	folio int primary key identity,
	ticket varchar(15),
	nombre_completo varchar(120),
	cantidad money,
	fecha varchar(14),
	status int
);

select tb_empleado.folio as [Folio],
	   tb_empleado.nombre as [Nombre],
	   tb_empleado.ap_paterno as [Paterno],
	   tb_empleado.ap_materno as [Materno],
	   tb_establecimiento.nombre as [Establecimiento],
	   tb_puesto.nombre as [Puesto],
	   tb_sueldo.sueldo as [Sueldo],
	   tb_bono.bono as [Bono],
	   tb_empleado.status as [Status],
	   tb_empleado.fuente_sodas as [Fuentes],
	   tb_empleado.gafete as [Gafete] 

	from tb_empleado, tb_establecimiento, tb_puesto, tb_sueldo, tb_bono

	where
		 tb_empleado.establecimiento_id = tb_establecimiento.folio and
		 tb_empleado.puesto_id = tb_puesto.folio and
		 tb_empleado.sueldo_id = tb_sueldo.folio and
		 tb_empleado.bono_id = tb_bono.folio

