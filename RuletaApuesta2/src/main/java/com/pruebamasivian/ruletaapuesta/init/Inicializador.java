package com.pruebamasivian.ruletaapuesta.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
 
@Startup
@Singleton
public class Inicializador {
  
  private Integer secuenciaRuleta;
  
  private Integer secuenciaApuesta;
  
  private List<String> llavesRuletas;
  
  private Map<String, List<String>> llavesApuestas;
  
  @PostConstruct
  public void init() {
    
	  secuenciaRuleta = Integer.valueOf(0);
	  secuenciaApuesta = Integer.valueOf(0);
	  llavesRuletas = new ArrayList<>();
	  llavesApuestas = new HashMap<String, List<String>>();
  }
  
  
  public Integer obtenerSecuenciaRuleta() {	  
	  return secuenciaRuleta++;
  }
  
  public List<String> obtenerLlavesRuletas() {	  
	  return llavesRuletas;
  }
  
  public Integer obtenerSecuenciaApuesta() {	  
	  return secuenciaApuesta++;
  }
  
  public Map<String, List<String>> obtenerLlavesApuestas() {	  
	  return llavesApuestas;
  }
   
}
