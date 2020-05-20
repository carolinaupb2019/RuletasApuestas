package com.pruebamasivian.ruletaapuesta.data;

import java.math.BigDecimal;

import com.pruebamasivian.ruletaapuesta.enums.EstadoRuletaEnum;

public class RuletaDTO {
	
	/**Identificador de la ruleta*/
	private String id;
	/**Estado de la ruleta*/
	private EstadoRuletaEnum estadoRuleta;
	/**Valor total apuestas ruleta*/
	private BigDecimal totalValorApuestasRuleta;
	
	
	/**
	 * Método para obtenerla variable id
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Método para obtenerla variable totalValorApuestasRuleta
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the totalValorApuestasRuleta
	 */
	public BigDecimal getTotalValorApuestasRuleta() {
		return totalValorApuestasRuleta;
	}
	/**
	 * Método para establecer valor a la variable totalValorApuestasRuleta
	 *
	 * @author Carolina Marulanda
	 *
	 * @param totalValorApuestasRuleta the totalValorApuestasRuleta to set
	 *
	 */
	public void setTotalValorApuestasRuleta(BigDecimal totalValorApuestasRuleta) {
		this.totalValorApuestasRuleta = totalValorApuestasRuleta;
	}
	/**
	 * Método para establecer valor a la variable id
	 *
	 * @author Carolina Marulanda
	 *
	 * @param id the id to set
	 *
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Método para obtenerla variable estadoRuleta
	 *
	 * @author Carolina Marulanda
	 * 
	 * @return the estadoRuleta
	 */
	public EstadoRuletaEnum getEstadoRuleta() {
		return estadoRuleta;
	}
	/**
	 * Método para establecer valor a la variable estadoRuleta
	 *
	 * @author Carolina Marulanda
	 *
	 * @param estadoRuleta the estadoRuleta to set
	 *
	 */
	public void setEstadoRuleta(EstadoRuletaEnum estadoRuleta) {
		this.estadoRuleta = estadoRuleta;
	}
	

}
