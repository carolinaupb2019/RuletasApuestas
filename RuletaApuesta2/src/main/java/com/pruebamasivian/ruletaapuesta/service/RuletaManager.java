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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.pruebamasivian.ruletaapuesta.data.ResponseDTO;
import com.pruebamasivian.ruletaapuesta.data.RuletaDTO;
import com.pruebamasivian.ruletaapuesta.enums.ErrorMessageEnum;
import com.pruebamasivian.ruletaapuesta.enums.EstadoRuletaEnum;
import com.pruebamasivian.ruletaapuesta.init.Inicializador;
import com.pruebamasivian.ruletaapuesta.util.RedisUtils;

import redis.clients.jedis.Jedis;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class RuletaManager {

    @Inject
    private Logger log;
    
    @Inject
    private Inicializador inicializador;
    
    /**
     * Metodo encargado de crear la ruleta de apuestas
     * 
     * @param ruletaDTO
     * @return ResponseDTO, objeto con identificador de la ruleta de apuestas creada
     * @throws Exception excepción de error
     */
    public ResponseDTO crearRuleta() throws Exception {
    	
    	log.info("Inicio creacion ruleta");
    	
    	//Conexión a Redis
    	RedisUtils redisUtils = new RedisUtils();
    	Jedis jedis =redisUtils.getConnection();
    	
    	String identificadorRuleta = inicializador.obtenerSecuenciaRuleta()+"Ruleta";
    	
    	jedis.hset(identificadorRuleta, "estado", EstadoRuletaEnum.CERRADO.toString() );
    	inicializador.obtenerLlavesRuletas().add(identificadorRuleta);
    	
    	//Cierre de conexión a Redis
    	redisUtils.destroyPool();
    	
    	log.info("Fin creacion ruleta");
        
        return new ResponseDTO(identificadorRuleta);
    }
    
    /**
     * Metodo encargado de listar las ruletas existentes
     * 
     * @return ResponseDTO, objeto con la lista de ruletas
     * @throws Exception excepción de error
     */
    public ResponseDTO listarRuletas() throws Exception {
    	
    	log.info("Inicio listar ruletas");
    	
    	//Conexión a Redis
    	RedisUtils redisUtils = new RedisUtils();
    	Jedis jedis =redisUtils.getDirectConnection();
    	List<RuletaDTO> ruletas = null;
    	RuletaDTO ruleta;
    	
    	for(String identificadorRuleta: inicializador.obtenerLlavesRuletas()) {
    		
    		ruletas = new ArrayList<RuletaDTO>();
    		ruleta= new RuletaDTO();
    		
    		ruleta.setId(identificadorRuleta);
    		ruleta.setEstadoRuleta(EstadoRuletaEnum.getEnumByName(jedis.hget(identificadorRuleta, "estado")));
    		ruletas.add(ruleta);   		
    		
    	}
    	
    	log.info("Fin listar ruletas");
    	
    	//Cierre de conexión a Redis
    	redisUtils.destroyPool();
    	
    	if(ruletas !=null && !ruletas.isEmpty()) {
    		return new ResponseDTO(ruletas);
    	}else {
    		ResponseDTO response = new ResponseDTO();
    		response.setObj(null);
    		response.setResponseMessage(ErrorMessageEnum.ERROR_NO_EXISTEN_RULETAS.getName());
    		response.setResponseCode(ErrorMessageEnum.ERROR_NO_EXISTEN_RULETAS.getCode());
    		return response;
    	}
    	
    }
    /**
     * Método encargado de abrir la ruleta de apuestas
     * 
     * @param identificadorRuleta, código de la ruleta de apuestas
     * @return ResponseDTO, objeto con estado de la ruleta
     * @throws Exception excepción de error
     */
    public ResponseDTO aperturarRuleta(String identificadorRuleta) throws Exception {
    	
    	log.info("Inicio Aperturar Ruleta");
    	
    	RedisUtils redisUtils = new RedisUtils();
    	Jedis jedis =redisUtils.getDirectConnection();
    	    		
    	jedis.hset(identificadorRuleta, "estado", EstadoRuletaEnum.ABIERTO.toString() );
    	
    	//Cierre de conexión a Redis
    	redisUtils.destroyPool();
    		    	
    	log.info("Fin Aperturar Ruleta");
    	
    	return new ResponseDTO(EstadoRuletaEnum.ABIERTO);
       
    }
    /**
     * Método encargado de cerrar un ruleta de apuestas dado el código de esta
     * 
     * @param identificadorRuleta, id de la ruleta
     * @return estado de la ruleta de apuestas.
     * @throws Exception excepción de error
     */
    public EstadoRuletaEnum cerrarRuleta(String identificadorRuleta) throws Exception {
    	
    	log.info("Inicio Cerrar Ruleta");
    	
    	//Conexión a Redis
    	RedisUtils redisUtils = new RedisUtils();
    	Jedis jedis =redisUtils.getDirectConnection();
    	    		
    	jedis.hset(identificadorRuleta, "estado", EstadoRuletaEnum.CERRADO.toString() );
    	
    	//Cierre de conexión a Redis
    	redisUtils.destroyPool();
    		    	
    	log.info("Fin Cerrar Ruleta");
    	
    	return EstadoRuletaEnum.CERRADO;
       
    }
    
}
