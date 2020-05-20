package com.pruebamasivian.ruletaapuesta.util;

public class KeySingleton {
	
	

	    private Integer clave;
	    private static KeySingleton keySingleton;

	    // El constructor es privado, no permite que se genere un constructor por defecto.
	    private KeySingleton() {
	        this.clave = 1;
	        System.out.println("Clave: " + this.clave);
	    }

	    public static KeySingleton getSingletonInstance() {
	        if (keySingleton == null){
	        	keySingleton = new KeySingleton();
	        }
	        else{
	            System.out.println("No se puede crear el objeto porque ya existe un objeto de la clase KeySingleton");
	        }
	        
	        return keySingleton;
	    }

		/**
		 * Método para obtenerla variable clave
		 *
		 * @author Carolina Marulanda
		 * 
		 * @return the clave
		 */
		public Integer getClave() {
			return clave;
		}

		/**
		 * Método para establecer valor a la variable clave
		 *
		 * @author Carolina Marulanda
		 *
		 * @param clave the clave to set
		 *
		 */
		public void setClave(Integer clave) {
			this.clave = clave;
		}


}
