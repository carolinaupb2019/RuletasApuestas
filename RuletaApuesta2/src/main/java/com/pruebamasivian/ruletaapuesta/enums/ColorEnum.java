package com.pruebamasivian.ruletaapuesta.enums;

public enum ColorEnum {
	
	NEGRO("COLOR NEGRO"),
	ROJO("COLOR ROJO");
	
	/**Color de la apuesta*/
	private String color;
	
	/**Constructor*/
	private ColorEnum(String color) {
		this.color = color;
	}


	/**
	 * Método para obtenerla variable color
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Método para establecer valor a la variable color
	 *
	 * @author Carolina Marulanda
	 *
	 * @param color the color to set
	 *
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Retorna el valor del enum por nombre
	 *  
	 * @param name descripción enum
	 * @return Objeto enumeración
	 */
	public static ColorEnum getEnumByName(String name) {
		for (ColorEnum c : ColorEnum.values()) {
			if (c.color.equals(name)) {
				return c;
			}
		}
		throw new IllegalArgumentException(name);
	}
	
	
	
}
