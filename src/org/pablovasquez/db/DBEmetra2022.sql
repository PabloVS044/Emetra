/*
Nombre: Pablo José Vásquez Santos
Carnet: 2018044
Fecha de creación: 26/09/2022
*/


Drop database if exists DBEmetra2022;

Create database DBEmetra2022;

Use DBEmetra2022;

Create table Vecinos(
	NIT varchar(15) not null,
    DPI bigint(13) not null,
    nombres varchar(100) not null,
    apellidos varchar(100) not null,
    direccion varchar(100) not null,
    municipalidad varchar(45),
    codigoPostal INT,
    telefono varchar(8),
    primary key PK_NIT(NIT)
);

Create table Vehiculos(
	placa varchar(15) not null,
    marca varchar(45) not null,
    modelo varchar(45) not null,
    tipoDeVehiculo varchar(60) not null,
    vecinos_NIT varchar(15),
    primary key PK_placa(placa),
    constraint FK_Vehiculos_Vecinos foreign key (vecinos_NIT) 
		references Vecinos(NIT)
);

/*Procedimientos Almacenados Vecinos*/


Delimiter //
Create procedure sp_AgregarVecino(in NIT varchar(15), in DPI bigint(13), in nombres varchar(100), in apellidos varchar(100), in direccion varchar(100), in municipalidad varchar(45), in codigoPostal int, in telefono varchar(8))
	Begin
		Insert into Vecinos(NIT, DPI, nombres, apellidos, direccion, municipalidad, codigoPostal, telefono)
        values(NIT, DPI, nombres, apellidos, direccion, municipalidad, codigoPostal, telefono);
    End //
Delimiter ;

call sp_AgregarVecino('652547852','236521452',"Juan Jose","Lopez Villagran","calle 1","Mixco",'01007',"54236587");
call sp_AgregarVecino('516516516','275752752',"Jose Roberto","Lopez Villagran","calle 1","Mixco",'01007',"54236587");
call sp_AgregarVecino('622555442','275272727',"Juan Manuel","Lopez Villagran","calle 1","Mixco",'01007',"54236587");
call sp_AgregarVecino('894981888','272752527',"Manuel Andres","Lopez Villagran","calle 1","Mixco",'01007',"54236587");



Delimiter //
	Create procedure sp_ListarVecinos()
    Begin
		Select
			v.NIT,
            v.DPI,
            v.nombres,
            v.apellidos,
            v.direccion,
            v.municipalidad,
            v.codigoPostal,
            v.telefono
            from Vecinos v;
    End //
Delimiter ;

call sp_ListarVecinos();

Delimiter //
	Create procedure sp_BuscarVecino(in NIT varchar(15))
    Begin
		Select
			v.NIT,
            v.DPI,
            v.nombres,
            v.apellidos,
            v.direccion,
            v.municipalidad,
            v.codigoPostal,
            v.telefono
            from Vecinos v
				Where NIT  = NIT;
    End //
Delimiter ;

call sp_BuscarVecino('652547852');



Delimiter //
	Create procedure sp_EliminarVecino(in NIT_elim varchar(15))
    Begin
		delete from  Vecinos
			Where NIT_elim = NIT;
    End //
Delimiter ;

call sp_EliminarVecino('652547852');



Delimiter //
	Create procedure sp_EditarVecino(in NIT_edit varchar(15), in DPI_edit bigint(13), in nombres_edit varchar(100), in apellidos_edit varchar(100), in direccion_edit varchar(100), in municipalidad_edit varchar(45), in codigoPostal_edit int, in telefono_edit varchar(8))
	Begin
			update Vecinos v
				set v.DPI = DPI_edit,
                    v.nombres = nombres_edit,
                    v.apellidos = apellidos_edit,
                    v.direccion = direccion_edit,
                    v.municipalidad = municipalidad_edit,
                    v.codigoPostal = codigoPostal_edit,
                    v.telefono = telefono_edit
						where NIT = NIT_edit;
    End //
Delimiter ;

call sp_EditarVecino('516516516','27275252',"Ricardo Andres","Lopez Villagran","calle 1","Mixco",'01007',"54236587");


/*Procedimientos Almacenados Vehiculos*/


Delimiter //
Create procedure sp_AgregarVehiculo(in placa varchar(15), in marca varchar(45), in modelo varchar(45), in tipoDeVehiculo varchar(60), in vecinos_NIT varchar(15))
	Begin
		Insert into Vehiculos(placa, marca, modelo, tipoDeVehiculo, vecinos_NIT)
        values(placa, marca, modelo, tipoDeVehiculo, vecinos_NIT);
    End //
Delimiter ;

call sp_AgregarVehiculo("P165O","Toyota","Hilux","Pickup",516516516);



Delimiter //
	Create procedure sp_ListarVehiculos()
    Begin
		Select
			v.placa,
            v.marca,
            v.modelo,
            v.tipoDeVehiculo,
            v.vecinos_NIT
            from Vehiculos v;
    End //
Delimiter ;

call sp_ListarVehiculos();

Delimiter //
	Create procedure sp_BuscarVehiculo(in placa varchar(15))
    Begin
		Select
			v.NIT,
            v.DPI,
            v.nombres,
            v.apellidos,
            v.direccion,
            v.municipalidad,
            v.codigoPostal,
            v.telefono
            from Vecinos v
				Where NIT  = NIT;
    End //
Delimiter ;

call sp_BuscarVecino('652547852');



Delimiter //
	Create procedure sp_EliminarVecino(in NIT_elim varchar(15))
    Begin
		delete from  Vecinos
			Where NIT_elim = NIT;
    End //
Delimiter ;

call sp_EliminarVecino('652547852');



Delimiter //
	Create procedure sp_EditarVecino(in NIT_edit varchar(15), in DPI_edit bigint(13), in nombres_edit varchar(100), in apellidos_edit varchar(100), in direccion_edit varchar(100), in municipalidad_edit varchar(45), in codigoPostal_edit int, in telefono_edit varchar(8))
	Begin
			update Vecinos v
				set v.DPI = DPI_edit,
                    v.nombres = nombres_edit,
                    v.apellidos = apellidos_edit,
                    v.direccion = direccion_edit,
                    v.municipalidad = municipalidad_edit,
                    v.codigoPostal = codigoPostal_edit,
                    v.telefono = telefono_edit
						where NIT = NIT_edit;
    End //
Delimiter ;

call sp_EditarVecino('516516516','2727525275275',"Ricardo Andres","Lopez Villagran","calle 1","Mixco",'01007',"54236587");









