CREATE TABLE public.tipo_cliente (
	id_tipcli serial NOT NULL,
	nombre_tipcli varchar(25) NOT NULL,
	descripcion_tipcli varchar(300) NOT NULL,
	CONSTRAINT tipo_cliente_pk PRIMARY KEY (id_tipcli)
);
COMMENT ON TABLE public.tipo_cliente IS 'Tabla para almacenar el tipo de cliente';

-- Column comments

COMMENT ON COLUMN public.tipo_cliente.id_tipcli IS 'Id del tipo de cliente';
COMMENT ON COLUMN public.tipo_cliente.nombre_tipcli IS 'Nombre del tipo de cliente';
COMMENT ON COLUMN public.tipo_cliente.descripcion_tipcli IS 'Descripción del tipo de cliente';


CREATE TABLE public.cliente (
	id_cli serial NOT NULL,
	nombre_cli varchar(25) NOT NULL,
	apellido_paterno_cli varchar(25) NOT NULL,
	apellido_materno_cli varchar(25) NULL,
	identificacion_cli varchar(10) NOT NULL,
	telefono_celular_cli varchar(10) NOT NULL,
	direccion_cli varchar(300) NOT NULL,
	correo_cli varchar(50) NOT NULL,
	fecha_nacimiento_cli date NOT NULL,
	id_tipcli int NOT NULL,
	foto_cli bytea NULL,
	CONSTRAINT cliente_pk PRIMARY KEY (id_cli)
);
COMMENT ON TABLE public.cliente IS 'Tabla para almacenar los clientes';

-- Column comments

COMMENT ON COLUMN public.cliente.id_cli IS 'Id del cliente';
COMMENT ON COLUMN public.cliente.nombre_cli IS 'Nombre del cliente';
COMMENT ON COLUMN public.cliente.apellido_paterno_cli IS 'Apellido paterno del cliente';
COMMENT ON COLUMN public.cliente.apellido_materno_cli IS 'Apellido materno del cliente';
COMMENT ON COLUMN public.cliente.identificacion_cli IS 'Identificación del cliente';
COMMENT ON COLUMN public.cliente.telefono_celular_cli IS 'Teléfono celular del cliente';
COMMENT ON COLUMN public.cliente.direccion_cli IS 'Dirección del cliente';
COMMENT ON COLUMN public.cliente.correo_cli IS 'Correo del cliente';
COMMENT ON COLUMN public.cliente.fecha_nacimiento_cli IS 'Fecha de nacimiento';
COMMENT ON COLUMN public.cliente.id_tipcli IS 'Identificador del tipo de cliente';
COMMENT ON COLUMN public.cliente.foto_cli IS 'Foto del cliente';

ALTER TABLE public.cliente ADD CONSTRAINT cliente_fk FOREIGN KEY (id_tipcli) REFERENCES public.tipo_cliente(id_tipcli);



CREATE TABLE public.reserva_viaje (
	id_res_cli serial NOT NULL,
	fecha_apertura_rescli date NOT NULL,
	numero_reserva_viaje varchar(10) NOT NULL,
	id_cli int NOT NULL,
	CONSTRAINT reserva_viaje_pk PRIMARY KEY (id_res_cli),
	CONSTRAINT reserva_viaje_fk FOREIGN KEY (id_cli) REFERENCES public.cliente(id_cli)
);
COMMENT ON TABLE public.reserva_viaje IS 'Reservas de viajes del cliente';

-- Column comments

COMMENT ON COLUMN public.reserva_viaje.id_res_cli IS 'Id reserva de viajes';
COMMENT ON COLUMN public.reserva_viaje.fecha_apertura_rescli IS 'Fecha de reserva de viaje';
COMMENT ON COLUMN public.reserva_viaje.numero_reserva_viaje IS 'Número de reserva de viaje';
COMMENT ON COLUMN public.reserva_viaje.id_cli IS 'Identificador de cliente';





CREATE TABLE public.detalle_reserva_viaje (
	id_detrescli serial NOT NULL,
	fecha_atencion_detrescli date NOT NULL,
	observaciones_detrescli varchar(300) NOT NULL,
	comentario_detrescli varchar(300) NOT NULL,
	id_res_cli int NOT NULL,
	CONSTRAINT detalle_reserva_viaje_pk PRIMARY KEY (id_detrescli),
	CONSTRAINT detalle_reserva_viaje_fk FOREIGN KEY (id_res_cli) REFERENCES public.reserva_viaje(id_res_cli)
);
COMMENT ON TABLE public.detalle_reserva_viaje IS 'Tabla para almacenar los detalles de la reserva de viajes';

-- Column comments
COMMENT ON COLUMN public.detalle_reserva_viaje.id_detrescli IS 'Identificador de detalle de reserva';
COMMENT ON COLUMN public.detalle_reserva_viaje.fecha_atencion_detrescli IS 'Fecha de Atención';
COMMENT ON COLUMN public.detalle_reserva_viaje.observaciones_detrescli IS 'Observaciones de reserva';
COMMENT ON COLUMN public.detalle_reserva_viaje.comentario_detrescli IS 'Comentario';
COMMENT ON COLUMN public.detalle_reserva_viaje.id_res_cli IS 'Identificador de reserva de viaje';

