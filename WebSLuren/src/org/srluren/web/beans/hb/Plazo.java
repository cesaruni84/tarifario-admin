/**
 * 
 */
package org.srluren.web.beans.hb;

import java.io.Serializable;

/**
 * @author cesar
 *
 */
public class Plazo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descripcion;
	private int periodo;

	
	public Plazo(String descripcion, int periodo) {
		super();
		this.descripcion = descripcion;
		this.periodo = periodo;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the periodo
	 */
	public int getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	

}
