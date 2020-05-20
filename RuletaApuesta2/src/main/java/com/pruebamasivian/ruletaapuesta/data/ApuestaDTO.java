package com.pruebamasivian.ruletaapuesta.data;
import java.math.BigDecimal;

import com.pruebamasivian.ruletaapuesta.enums.ColorEnum;

public class ApuestaDTO {
	
	/**Identificador del usuario apostador*/
	private Integer idUsuario;
	/**Valor de la apuesta*/
	private BigDecimal Valor;
	/**Identificador de la ruleta*/
	private String idRuleta;
	/**Numero de la apuesta*/
	private Integer numero;
	/**Color de la apuesta*/
	private ColorEnum color;
	
	
	/**
	 * Método para obtenerla variable idUsuario
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the idUsuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}
	/**
	 * Método para establecer valor a la variable idUsuario
	 *
	 * @author Carolina Marulanda
	 *
	 * @param idUsuario the idUsuario to set
	 *
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * Método para obtenerla variable valor
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return Valor;
	}
	/**
	 * Método para establecer valor a la variable valor
	 *
	 * @author Carolina Marulanda
	 *
	 * @param valor the valor to set
	 *
	 */
	public void setValor(BigDecimal valor) {
		Valor = valor;
	}
	/**
	 * Método para obtenerla variable idRuleta
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the idRuleta
	 */
	public String getIdRuleta() {
		return idRuleta;
	}
	/**
	 * Método para establecer valor a la variable idRuleta
	 *
	 * @author Carolina Marulanda
	 *
	 * @param idRuleta the idRuleta to set
	 *
	 */
	public void setIdRuleta(String idRuleta) {
		this.idRuleta = idRuleta;
	}
	/**
	 * Método para obtenerla variable numero
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}
	/**
	 * Método para establecer valor a la variable numero
	 *
	 * @author Carolina Marulanda
	 *
	 * @param numero the numero to set
	 *
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	/**
	 * Método para obtenerla variable color
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the color
	 */
	public ColorEnum getColor() {
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
	public void setColor(ColorEnum color) {
		this.color = color;
	}
	
	
	
	

}
