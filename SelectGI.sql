use Grupo_Izagar

select * from tb_empleado
select * from tb_sueldo
select * from tb_puesto
select * from tb_establecimiento
select * from tb_bono
select * from tb_prestamo
select * from tb_rango_prestamos
select * from tb_usuario
select * from tb_permiso
select * from tb_fuente_sodas_rh
select * from tb_fuente_sodas_auxf
select * from tb_bancos
select * from tb_deduccion_inasistencia
select * from tb_asistencia_puntualidad
select * from tb_persecciones_extra

select distinct folio_empleado from tb_prestamo

alter table tb_persecciones_extra add status int
ALTER TABLE tb_fuente_sodas_auxf  ALTER COLUMN status_ticket char(1)
alter table tb_empleado add infonavit money

delete tb_deduccion_asistencia 

EXEC SP_RENAME 'tb_fuente_sodas_auxf.ticket', 'status_ticket'

sp_columns tb_fuente_sodas_auxf


select * from tb_empleado --where folio=1

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


select tb_empleado.folio as [Folio],
	   tb_empleado.nombre as [Nombre],
	   tb_empleado.ap_paterno as [Paterno],
	   tb_empleado.ap_materno as [Materno],
	   tb_establecimiento.nombre as [Establecimiento],		
	   tb_empleado.status as [Status],
	   tb_rango_prestamos.minimo as [RangoMin],
	   tb_rango_prestamos.maximo as [RangoMax],
	   tb_sueldo.sueldo as [Sueldo] 
from tb_empleado, tb_establecimiento, tb_sueldo, tb_rango_prestamos 
where 
	  tb_empleado.establecimiento_id = tb_establecimiento.folio and
	  tb_empleado.status < 3 and tb_empleado.fuente_sodas = '1' and
	  tb_empleado.sueldo_id = tb_sueldo.folio and 
	  tb_empleado.rango_prestamo_id = tb_rango_prestamos.folio  and
	  tb_empleado.sueldo_id = tb_sueldo.folio


select minimo,maximo 
from tb_empleado,tb_rango_prestamos 
where 
tb_empleado.rango_prestamo_id = tb_rango_prestamos.folio


select 
		tb_prestamo.folio as [folio],
		tb_prestamo.folio_empleado as [folio_Emp],
		tb_prestamo.nombre_completo as [nombre],
		tb_establecimiento.nombre as [establecimiento],
		tb_prestamo.fecha as [fecha],
		tb_prestamo.cantidad as [prestamo],
		tb_prestamo.descuento as[desc],
		tb_prestamo.saldo as [saldo]
from tb_prestamo,tb_establecimiento,tb_empleado
where tb_prestamo.status='1' 
and tb_prestamo.folio_empleado= tb_empleado.folio and tb_empleado.establecimiento_id = tb_establecimiento.folio


select
		tb_empleado.folio as folio,
		tb_empleado.nombre as nombre,
		tb_empleado.ap_paterno as ap_paterno,
		tb_empleado.ap_materno as ap_materno,
		tb_sueldo.sueldo as sueldo,
		tb_bono.bono as bono
from tb_empleado, tb_sueldo, tb_bono
where tb_empleado.sueldo_id = tb_sueldo.folio and
	  tb_empleado.bono_id = tb_bono.folio


select  tb_prestamo.folio_empleado as [Empleado],
		tb_prestamo.fecha as [fecha],
		tb_prestamo.saldo as [saldo],
		tb_prestamo.descuento as [desc],
		tb_prestamo.status_descuento as [statusD]
from    tb_prestamo,tb_empleado
where   tb_prestamo.folio_empleado= tb_empleado.folio


select sum(descuento) 
from tb_prestamo
left join tb_empleado on tb_empleado.folio=tb_prestamo.folio_empleado
where tb_prestamo.folio_empleado=3 and tb_prestamo.status_descuento=0


select (sum(tb_prestamo.cantidad)-sum(tb_prestamo.descuento))
from tb_prestamo
where tb_prestamo.folio_empleado=3 and tb_prestamo.status_descuento=0


select  tb_empleado.folio as [Folio],
		tb_empleado.nombre as [Nombre], 
		tb_empleado.ap_paterno as [Paterno], 
		tb_empleado.ap_materno as [Materno], 
		tb_establecimiento.nombre as [Establecimiento], 
		tb_empleado.status as [Status], 
		ROUND(tb_rango_prestamos.minimo,2) as [RangoMin], 
		ROUND(tb_rango_prestamos.maximo,2) as [RangoMax],

		tb_sueldo.sueldo as [sueldo]					 
		--sum(tb_prestamo.cantidad) as [Cantidad], 
		--sum(tb_prestamo.descuento) as [Descuento] 
from   tb_empleado, tb_establecimiento, tb_sueldo, tb_rango_prestamos
where   tb_empleado.establecimiento_id = tb_establecimiento.folio and
		tb_empleado.status < 3 and tb_empleado.fuente_sodas = '1' and
		tb_empleado.sueldo_id = tb_sueldo.folio and 
		tb_empleado.rango_prestamo_id = tb_rango_prestamos.folio and
		tb_empleado.sueldo_id = tb_sueldo.folio
