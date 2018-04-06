package com.almundo.atenciontelefonica.VO;
/**
 * RAMADO 
 *
 */
public class Empleado {

	private String nombre;
	private String cargo;
	
	

	public Empleado(String nombre, String cargo) {
		super();
		this.nombre = nombre;
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
