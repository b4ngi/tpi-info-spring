# tpi-info-spring

Version de java: Java 17

Para iniciar el servidor, setear la informacion de la base de datos de manera correcta --> en aplicattion.properties indicar el nombre de la base de datos a utilizar, el username y el password.

Documentacion Swagger:
	Iniciar el servidor e ingresar al link: http://localhost:8080/swagger-ui/index.html#/turno-rest-controller

	--> Para realizar peticiones a la API, guiarse con la documentacion en Swagger --> Ver ejemplos de los bodys.

Funcionalidades API

	Organizaciones
		-> Registrar una organizacion
		-> Modificar atributos de una organizacion -> Requiere clave de organizacion
		-> Eliminar una organizacion -> Requiere clave de organizacion -> Eliminacion logica
		-> Buscar todas las organizaciones activas
		-> Buscar por nombre de organizacion -> El nombre de una organizacion es unico.
		-> Buscar por cuit de organizacion -> El cuit de una organizacion es unico.

	Personas
		-> Registrar una persona
		-> Modificar atributos de una persona -> Requiere clave de persona
		-> Eliminar una persona -> Requiere clave de persona -> Eliminacion logica
		-> Buscar todas las personas activas
		-> Buscar por apellido -> Devuelve una lista de todas las personas con el apellido indicado(apellido no es unico)
		-> Buscar por DNI -> El dni de una persona es unico

	Eventos
		-> Registrar un evento -> Requiere nombre de organizacion y clave de organizacion
			-> Si se ingresa una "fechaRealizacion", se deduce que el evento es de tipo UNICO
			-> Si no se ingresa una "fechaRealizacion", se deduce que el evento es de tipo RECURRENTE
		-> Modificar atributos de un evento -> Requiere nombre de organizacion y clave de organizacion
		-> Eliminar un evento -> Requiere nombre de organizacion y clave de organizacion
		-> Buscar todos los eventos activos
		-> Buscar todos los eventos de una organizacion

	Turnos
		-> Registrar un turno -> Requiere nombre de organizacion, dni de persona, nombre de evento
			-> Si el evento ingresado es de tipo RECURRENTE, se debe ingresar una "fechaEvento" para asentar el registro del evento
			-> Si el evento ingresado es de tipo UNICO, la "fechaEvento" es la misma que la fecha de realizacion del evento
		-> Buscar todos los turnos activos por organizacion
		-> Buscar todos los turnos activos de un evento de una organizacion

	-> Las claves de Persona y de Organizacion son strings de 8 caracteres autogenerados.
	-> Se manejan la mayoria de los errores con un controller advice.

A mejorar:
	-> El manejo de las fechas