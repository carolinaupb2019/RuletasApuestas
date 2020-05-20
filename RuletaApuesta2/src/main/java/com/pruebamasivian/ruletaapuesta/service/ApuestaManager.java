/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pruebamasivian.ruletaapuesta.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pruebamasivian.ruletaapuesta.data.ApuestaDTO;
import com.pruebamasivian.ruletaapuesta.data.ResponseDTO;
import com.pruebamasivian.ruletaapuesta.data.RuletaDTO;
import com.pruebamasivian.ruletaapuesta.enums.ColorEnum;
import com.pruebamasivian.ruletaapuesta.enums.ErrorMessageEnum;
import com.pruebamasivian.ruletaapuesta.enums.EstadoRuletaEnum;
import com.pruebamasivian.ruletaapuesta.init.Inicializador;
import com.pruebamasivian.ruletaapuesta.util.RedisUtils;

import redis.clients.jedis.Jedis;

/**
 * Clase que implementa los metodos de negocio relacionados a las Apuestas
 * @author Carolina Marulanda
 *
 */
// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class ApuestaManager {

    @Inject
    private Logger log;
    /**Instancia clase inicializa instancias únicas para secuencias*/
    @Inject
    private Inicializador inicializador;
    /**Instancia manager ruleta*/
    @Inject
    private RuletaManager ruletaManager;
    
    /**
     * Crea la apuesta asociada a la ruleta
     * 
     * @param apuestaDTO, Objeto con datos de la apuesta a realizar
     * @return Objeto con mensajes de error
     * @throws Exception excepción de error
     */
    public ResponseDTO crearApuesta(ApuestaDTO apuestaDTO) throws Exception {
    	
    	log.info("Inicio crear apuesta");    	
    	
    	RedisUtils redisUtils = new RedisUtils();
    	Jedis jedis =redisUtils.getConnection();
    	ResponseDTO response = new ResponseDTO(null);
    	
    	// Se valida que el estado de la ruleta a apostar sea Abierto 
		if (EstadoRuletaEnum.ABIERTO == EstadoRuletaEnum
				.getEnumByName(jedis.hget(apuestaDTO.getIdRuleta(), "estado"))) {
			
			//Se valida valor apuesta y numero a apostar
			if(apuestaDTO.getValor().intValue()<=10000 && 
					apuestaDTO.getNumero()>=0 && apuestaDTO.getNumero()<=36) {
			
				String identificadorApuesta = inicializador.obtenerSecuenciaApuesta()+"Apuesta";		    	
		    	jedis.hset(identificadorApuesta, "idUsuario",  apuestaDTO.getIdUsuario().toString());
		    	jedis.hset(identificadorApuesta, "idRuleta", apuestaDTO.getIdRuleta() );
		    	jedis.hset(identificadorApuesta, "color", apuestaDTO.getColor().toString() );
		    	jedis.hset(identificadorApuesta, "numero", apuestaDTO.getNumero().toString() );
		    	jedis.hset(identificadorApuesta, "valor", apuestaDTO.getValor().toString() );
		    	
		    	List<String> llavesApuestas = inicializador.obtenerLlavesApuestas().get(apuestaDTO.getIdRuleta());
		    	
		    	if(llavesApuestas!=null) {
		    		llavesApuestas.add(identificadorApuesta);
		    	}else {
		    		//Se guardan las apuestas con llave principal idRuleta y la lista con datos de cada apuesta
		    		List<String> llaves = new ArrayList<String>();
		    		llaves.add(identificadorApuesta);
		    		inicializador.obtenerLlavesApuestas().put(apuestaDTO.getIdRuleta(), llaves);
		    	}		    		    		    	
			}else {		
				//Error de valor superado por apuesta 
	    		response.setResponseMessage(ErrorMessageEnum.ERROR_APUESTA_LIMITE.getName());
	    		response.setResponseCode(ErrorMessageEnum.ERROR_APUESTA_LIMITE.getCode());
	    		//Error de numero no valido para la apuesta 
	    		if(apuestaDTO.getNumero()>=0 && apuestaDTO.getNumero()<=36) {
	    			response.setResponseMessage(ErrorMessageEnum.ERROR_NUMERO_NO_VALIDO_APUESTA.getName());
		    		response.setResponseCode(ErrorMessageEnum.ERROR_NUMERO_NO_VALIDO_APUESTA.getCode());
	    		}
	    		
			}
		}else {	
			//Error de valor superado por apuesta 
    		response.setResponseMessage(ErrorMessageEnum.ERROR_RULETA_CERRADA.getName());
    		response.setResponseCode(ErrorMessageEnum.ERROR_RULETA_CERRADA.getCode());    		
		}
		
		log.info("Fin creacion apuesta");
		//Cierre conexión redis
		redisUtils.destroyPool();
		return response;
    }
   /**
    *  Lista las apuestas por id de ruleta
    *  
    * @param ruletaDTO, Objeto con identificador de la ruleta a listar apuestas
    * @return Lista de apuestas realizadas en la ruleta
    * @throws Exception excepción de error
    */
    public List<ApuestaDTO> listarApuestasporIdRuleta(RuletaDTO ruletaDTO) throws Exception {
    	
    	log.info("Inicio listar apuestas");
    	//conexion redis
    	RedisUtils redisUtils = new RedisUtils();
    	Jedis jedis =redisUtils.getDirectConnection();
    	List<ApuestaDTO> apuestasRuleta = new ArrayList<ApuestaDTO>();
    	ApuestaDTO apuesta= null;
    
    	   // Se recorre la lista de apuestas por llave
	    	for(String identificadorRuleta: inicializador.obtenerLlavesApuestas().keySet()) {
	    		// Se obtienen las apuestas que correspondan al id ruleta
	    		if(jedis.hget(identificadorRuleta, "idRuleta").equals(ruletaDTO.getId())) {
	    		
	    			apuesta= new ApuestaDTO();
		    		apuesta.setIdRuleta(jedis.hget(identificadorRuleta, "idRuleta"));
		    		apuesta.setIdUsuario(Integer.parseInt(jedis.hget(identificadorRuleta, "idUsuario")));
		    		apuesta.setColor(ColorEnum.getEnumByName(jedis.hget(identificadorRuleta, "color")));
		    		apuesta.setNumero(Integer.parseInt(jedis.hget(identificadorRuleta, "numero")));
		    		apuesta.setValor(new BigDecimal(jedis.hget(identificadorRuleta, "valor")));
		    		apuestasRuleta.add(apuesta);
		    		
	    		}
	    	
	    	}
    	
	    	//Cierre de conexión a Redis
	    	redisUtils.destroyPool();
	    	
    	    log.info("Fin listar apuestas");
    	  
    		return apuestasRuleta;
    	    	
    }

    /**
     * Metodo encargado de cerrar las apuestas de una ruleta determinada y contabilizar 
     * 
     * @param ruleta ruleta a cerrar
     * @return ResponseDTO Objeto con valores de la ruleta cerrada
     * @throws Exception excepcion de error
     */
    public ResponseDTO cerrarApuestas(RuletaDTO ruleta) throws Exception {
    	
    	List<ApuestaDTO> apuestas = new ArrayList<ApuestaDTO>();
    	apuestas =listarApuestasporIdRuleta(ruleta);
    	BigDecimal totalValorApuestaRuleta=new BigDecimal(0);
    	ruletaManager=new RuletaManager();
    	RuletaDTO ruletaDTO;
    	EstadoRuletaEnum ruletaCerrada;
    	
    	
    	if(apuestas !=null && !apuestas.isEmpty()) {
    		// Se totalizan los valores de
    		for(ApuestaDTO apuesta:apuestas) {
    			totalValorApuestaRuleta.add(apuesta.getValor());
    		}
    	} 
    	
    	ruletaCerrada= ruletaManager.cerrarRuleta(ruleta.getId());
    	
    	//Retorna en caso de realizar la sumatoria de apuestas exitosamente y haber cerrado la ruleta
    	if(!totalValorApuestaRuleta.equals(new BigDecimal(0)) && ruletaCerrada.getEstado().equals(EstadoRuletaEnum.CERRADO.toString())) {
    		ResponseDTO response = new ResponseDTO();
    		ruletaDTO= new RuletaDTO();
    		ruletaDTO.setEstadoRuleta(ruletaCerrada);
    		ruletaDTO.setTotalValorApuestasRuleta(totalValorApuestaRuleta);
    		response.setObj(ruletaDTO);
    		return new ResponseDTO();
    	//Retorna en caso de que la ruleta no tenga apuestas realizadas
    	}else {
    		ResponseDTO response = new ResponseDTO();
    		response.setObj(null);
    		response.setResponseMessage(ErrorMessageEnum.ERROR_NO_EXISTE_APUESTAS_RULETA.getName());
    		response.setResponseCode(ErrorMessageEnum.ERROR_NO_EXISTE_APUESTAS_RULETA.getCode());
    		return response;
    	}
    	
    }
    

}
