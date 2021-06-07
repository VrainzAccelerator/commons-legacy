package com.vrainz.cucumberTest.persistence;

public abstract class AbstractDao {

	protected static final String MENSAJE_EJECUTANDO_METODO = "Ejecutando en el metodo: {0}";
	protected static final String MENSAJE_EJECUTANDO_QUERY = "Ejecutando la query: {0}";
	protected static final String MENSAJE_ERROR_EJECUCION_SP = "Error al ejecutar el stored procedure: {0}";
	protected static final String MENSAJE_ERROR_EJECUCION_QUERY = "Error al ejecutar la query: {0}";
	protected static final String MENSAJE_ERROR_CONEXION_INEXISTENTE_BD = "No existe conexion a la base de datos";
	
}
