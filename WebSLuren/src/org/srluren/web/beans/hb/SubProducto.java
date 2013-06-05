package org.srluren.web.beans.hb;

// Generated 29/10/2012 01:48:07 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SubProductos generated by hbm2java
 */
public class SubProducto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idSubProducto;
	private Producto producto;
	private String nombre;
	private String nemonico;
	private Date fechaReg;
	private Date fechaAct;
	private Date fechaEli;
	private Set<Tasa> tasas = new HashSet<Tasa>(0);

	public SubProducto() {
	}

	public SubProducto(Integer idSubProducto, Producto producto, String nombre,
			String nemonico) {
		this.idSubProducto = idSubProducto;
		this.producto = producto;
		this.nombre = nombre;
		this.nemonico = nemonico;
	}

	public SubProducto(Integer idSubProducto, Producto producto, String nombre,
			String nemonico, Date fechaReg, Date fechaAct, Date fechaEli,
			Set<Tasa> tasas) {
		this.idSubProducto = idSubProducto;
		this.producto = producto;
		this.nombre = nombre;
		this.nemonico = nemonico;
		this.fechaReg = fechaReg;
		this.fechaAct = fechaAct;
		this.fechaEli = fechaEli;
		this.tasas = tasas;
	}

	public Integer getIdSubProducto() {
		return this.idSubProducto;
	}

	public void setIdSubProducto(Integer idSubProducto) {
		this.idSubProducto = idSubProducto;
	}

	public Producto getProductos() {
		return this.producto;
	}

	public void setProductos(Producto producto) {
		this.producto = producto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNemonico() {
		return this.nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

	public Date getFechaReg() {
		return this.fechaReg;
	}

	public void setFechaReg(Date fechaReg) {
		this.fechaReg = fechaReg;
	}

	public Date getFechaAct() {
		return this.fechaAct;
	}

	public void setFechaAct(Date fechaAct) {
		this.fechaAct = fechaAct;
	}

	public Date getFechaEli() {
		return this.fechaEli;
	}

	public void setFechaEli(Date fechaEli) {
		this.fechaEli = fechaEli;
	}

	public Set<Tasa> getTasas() {
		return this.tasas;
	}

	public void setTasas(Set<Tasa> tasas) {
		this.tasas = tasas;
	}

}