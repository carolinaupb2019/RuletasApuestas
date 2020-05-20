package com.pruebamasivian.ruletaapuesta.data;
import com.pruebamasivian.ruletaapuesta.enums.ErrorMessageEnum;

public class ResponseDTO {

	/**Objeto devuelto en el response*/
	private Object obj;
	/**Mensaje devuelto en el response*/
	private String responseMessage;
	/**Código de respuesta devuelto en el response*/
	private String responseCode;

	/**
	 * Constructor
	 */
	public ResponseDTO() {

	}

	/**
	 * Constructor caso exito
	 * 
	 * @param obj
	 */
	public ResponseDTO(Object obj) {
		this.obj = obj;
		responseMessage = ErrorMessageEnum.SUCCESS.getName();
		responseCode = ErrorMessageEnum.SUCCESS.getCode(); 
	}

	/**
	 * Constructor caso fallo
	 * 
	 * @param obj
	 * @param responseMessage
	 * @param responseCode
	 */
	public ResponseDTO(Object obj, String responseMessage, String responseCode) {
		this.obj = obj;
		this.responseMessage = responseMessage;
		this.responseCode = responseCode;
	}

	/**
	 * Método que obtiene el valor de obj
	 *
	 * @return obj, valor a obtener
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * Método que establece el valor a código
	 *
	 * @param obj, valor a establecer
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * Método que obtiene el valor de responseMessage
	 *
	 * @return responseMessage, valor a obtener
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * Método que establece el valor a código
	 *
	 * @param responseMessage, valor a establecer
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * Método que obtiene el valor de responseCode
	 *
	 * @return responseCode, valor a obtener
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * Método que establece el valor a código
	 *
	 * @param responseCode, valor a establecer
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseDTO [obj=" + obj + ", responseMessage=" + responseMessage + ", responseCode=" + responseCode
				+ "]";
	}
	
	

}