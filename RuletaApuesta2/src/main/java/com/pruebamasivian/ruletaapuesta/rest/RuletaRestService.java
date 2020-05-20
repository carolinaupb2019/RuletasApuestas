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
package com.pruebamasivian.ruletaapuesta.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pruebamasivian.ruletaapuesta.data.ResponseDTO;
import com.pruebamasivian.ruletaapuesta.enums.ErrorMessageEnum;
import com.pruebamasivian.ruletaapuesta.service.RuletaManager;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/Ruletas")
@RequestScoped
public class RuletaRestService {


    @Inject
    RuletaManager ruletaManager;

    /**
     * Crea la ruleta de apuestas
     * 
     * @return ResponseDTO Objeto con identificador de la ruleta creada
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDTO crearRuleta() {
        ResponseDTO responseDTO= new ResponseDTO() ;
		try {
			responseDTO = ruletaManager.crearRuleta();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseDTO.setObj(null);
			responseDTO.setResponseMessage(ErrorMessageEnum.ERROR_NO_CONTROLADO.getName());
			responseDTO.setResponseCode(ErrorMessageEnum.ERROR_NO_CONTROLADO.getCode());
    		return responseDTO;
		}
        return responseDTO;
    }

    /**
     * Abre la ruleta de apuestas
     * 
     * @param identificadorRuleta Identificador de la ruleta a abrir
     * @return ResponseDTO Objeto con estado de la operación realizada
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDTO aperturarRuleta(String identificadorRuleta)  {

    	ResponseDTO responseDTO= new ResponseDTO() ;

        try {
        	responseDTO = ruletaManager.aperturarRuleta(identificadorRuleta);
        } catch (Exception e) {
        	e.printStackTrace();
			responseDTO.setObj(null);
			responseDTO.setResponseMessage(ErrorMessageEnum.ERROR_NO_CONTROLADO.getName());
			responseDTO.setResponseCode(ErrorMessageEnum.ERROR_NO_CONTROLADO.getCode());
    		return responseDTO;       
        }

        return responseDTO;
    }
    
    /**
     * Lista las ruletas ceradas con su estado
     * 
     * @return ResponseDTO objeto con la lista de identificadores de ruletas existentes y el estado de cada una
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDTO listarRuletas() {

    	ResponseDTO responseDTO= new ResponseDTO() ;

        try {
        	responseDTO = ruletaManager.listarRuletas();
        } catch (Exception e) {
        	e.printStackTrace();
			responseDTO.setObj(null);
			responseDTO.setResponseMessage(ErrorMessageEnum.ERROR_NO_CONTROLADO.getName());
			responseDTO.setResponseCode(ErrorMessageEnum.ERROR_NO_CONTROLADO.getCode());
    		return responseDTO;       
        }

        return responseDTO;
    }

   
}
