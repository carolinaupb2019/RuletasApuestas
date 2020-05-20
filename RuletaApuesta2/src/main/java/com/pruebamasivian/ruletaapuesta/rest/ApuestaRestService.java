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
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.pruebamasivian.ruletaapuesta.data.ApuestaDTO;
import com.pruebamasivian.ruletaapuesta.data.ResponseDTO;
import com.pruebamasivian.ruletaapuesta.data.RuletaDTO;
import com.pruebamasivian.ruletaapuesta.enums.ErrorMessageEnum;
import com.pruebamasivian.ruletaapuesta.service.ApuestaManager;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/Apuestas")
@RequestScoped
public class ApuestaRestService {

    @Inject
    ApuestaManager apuestaManager;

	/**
	 * Crea la apuesta asocaida al usuario y los datos de la apuesta
	 * 
	 * @param apuestaDTO, datos de la apuesta
	 * @param idUsuario, usuario de la apuesta
	 * @return ResponseDTO, con id apuesta creada
	 */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDTO crearApuesta(@HeaderParam("idUsuario") Integer idUsuario, ApuestaDTO apuestaDTO) {
        ResponseDTO responseDTO= new ResponseDTO() ;
		try {
			
			apuestaDTO.setIdUsuario(idUsuario);			
			responseDTO = apuestaManager.crearApuesta(apuestaDTO);
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
     * Cierra la ruleta recibida por parametro
     * @param ruleta, ruleta a cerrar
     * @return ResponseDTO, objeto con valor total de las apuestas realizadas en la ruleta y el estado de la ruleta
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseDTO cerrarApuestas(RuletaDTO ruleta)   {

    	ResponseDTO responseDTO= new ResponseDTO() ;

        try {
        	responseDTO = apuestaManager.cerrarApuestas(ruleta);
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
