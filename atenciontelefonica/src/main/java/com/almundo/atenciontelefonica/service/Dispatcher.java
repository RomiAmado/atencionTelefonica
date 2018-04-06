package com.almundo.atenciontelefonica.service;

import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

import com.almundo.atenciontelefonica.VO.Empleado;

/**
 * RAMADO 
 *
 */

public class Dispatcher implements Runnable {

	private Queue<Empleado> empleados;
	private int llamada;
	private Integer duracion;

	/**
	 * Constructor 
	 * @param empleados
	 * @param llamada
	 */
	public Dispatcher(Queue<Empleado> empleados, int llamada) {

		this.empleados = empleados;
		this.llamada = llamada;

	}

	/**
	 * funcion encargada de designar a los empleados.
	 * @param Empleado
	 * @return empleado
	 * @throws InterruptedException
	 */
	public Empleado dispatchCall() throws InterruptedException {

		Empleado empleado = null;
		if (this.getEmpleados().peek().getCargo().equals("operador")) {
			empleado = empleados.poll();
			return empleado;
		} else if (this.getEmpleados().peek().getCargo().equals("supervisor")) {
			empleado = empleados.poll();
			return empleado;
		} else if (this.getEmpleados().peek().getCargo().equals("director")) {
			empleado = empleados.poll();
			return empleado;
		}

		return empleado;
	}

	
	/**
	 * Funcion encargada de ejecutar las llamadas de manera concurrente.
	 * 
	 * 
	 */
	@Override
	public void run() {

		Empleado empleado = null;
		try {
			if (empleados.isEmpty()) {
				Thread.sleep(10000);
				System.out.println("La llamada =>" + this.llamada + " esta en espera ...");
			}
			empleado = this.dispatchCall();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("La llamada =>" + this.llamada + " es atendida por el => " + empleado.getCargo() + " Nº "
				+ empleado.getNombre());

		this.setDuracion(duracionDeLlamada());
		try {
			Thread.sleep(this.getDuracion());
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println("La llamada =>" + this.llamada + " atendida por el => " + empleado.getCargo() + " Nº "
				+ empleado.getNombre() + " FINALIZO .Su duracion fue de " + duracion / 1000 + " segundos (" + duracion
				+ ")");
		empleados.add(empleado);

	}

	/**
	 * Funcion encargada de dar el tiempo que dura una llamada
	 * @param Integer
	 * @return Integer
	 */
	public Integer duracionDeLlamada() {
		Integer duracion = ThreadLocalRandom.current().nextInt(5000, 10000 + 1);
		return duracion;
	}

	public Queue<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Queue<Empleado> empleados) {
		this.empleados = empleados;
	}

	public int getLlamada() {
		return llamada;
	}

	public void setLlamada(int llamada) {
		this.llamada = llamada;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

}
