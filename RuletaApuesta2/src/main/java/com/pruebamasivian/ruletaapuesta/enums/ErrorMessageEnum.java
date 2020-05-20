package com.pruebamasivian.ruletaapuesta.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Clase con los valores de enumeraciones de exito y error de la aplicación
 * 
 * @author Carolina Marulanda
 *
 */
public enum ErrorMessageEnum {

	SUCCESS("PROCESO EXITOSO", "00"),
	ERROR_NO_EXISTEN_RULETAS("ERROR NO EXISTEN RULETAS","-1"),
	ERROR_APUESTA_LIMITE("ERROR APUESTA LIMITE","-2"),
	ERROR_RULETA_CERRADA("ERROR RULETA CERRADA","-3"),
	ERROR_NO_EXISTE_APUESTAS_RULETA("ERROR NO EXISTEN APUESTAS","-4"),
	ERROR_NUMERO_NO_VALIDO_APUESTA("ERROR NÚMERO NO VALIDO PARA LA APUESTA","-5"),
	ERROR_NO_CONTROLADO("ERROR NO CONTROLADO","-6");
	


	/**Nombre del error*/
	private String name;
	
	/**Código del error*/
	private String code;

	/**Constructor*/
	@JsonCreator
	private ErrorMessageEnum(String name, String code) {
		this.name = name;
		this.code = code;
	}

	/**
	 * Método que obtiene el valor de nombre
	 *
	 * @return nombre, valor a obtener
	 */
	@JsonValue
	public String getName() {
		return name;
	}

	/**
	 * Método que establece el valor a código
	 *
	 * @param nombre, valor a establecer
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método que obtiene el valor de codigo
	 *
	 * @return codigo, valor a obtener
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Método que establece el valor a código
	 *
	 * @param codigo, valor a establecer
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
