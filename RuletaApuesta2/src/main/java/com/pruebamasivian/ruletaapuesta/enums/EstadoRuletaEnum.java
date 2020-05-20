package com.pruebamasivian.ruletaapuesta.enums;

/**
 * Enumeración estado ruleta
 * 
 * @author Carolina Marulanda
 *
 */
public enum EstadoRuletaEnum {
	
	ABIERTO("ABIERTO"),
	CERRADO("CERRADO");
	
	/**Estado de la ruleta*/
	private String estado;
	
	/**Constructor*/
	private EstadoRuletaEnum(String estado) {
		this.estado = estado;
	}

	/**
	 * Método para obtenerla variable estado
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Método para establecer valor a la variable estado
	 *
	 * @author Carolina Marulanda
	 *
	 * @param estado the estado to set
	 *
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	/**
	 * Retorna el valor del enum por nombre
	 *  
	 * @param name descripción enum
	 * @return Objeto enumeración
	 */
	public static EstadoRuletaEnum getEnumByName(String name) {
		for (EstadoRuletaEnum c : EstadoRuletaEnum.values()) {
			if (c.estado.equals(name)) {
				return c;
			}
		}
		throw new IllegalArgumentException(name);
	}
	
	

}
