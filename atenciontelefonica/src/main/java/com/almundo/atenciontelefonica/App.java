package com.almundo.atenciontelefonica;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.almundo.atenciontelefonica.VO.Director;
import com.almundo.atenciontelefonica.VO.Empleado;
import com.almundo.atenciontelefonica.VO.Operador;
import com.almundo.atenciontelefonica.VO.Supervisor;
import com.almundo.atenciontelefonica.service.Dispatcher;

/**
 * RAMADO 
 *
 */
public class App {
	/**
	 * Se crea un Queue con los empleados y se realiza un thread por llamada, con la
	 * capacidad de procesar 10 thread como maximo al mismo tiempo.
	 */
	public static void main(String[] args) {
		Queue<Empleado> empleados = new LinkedList<Empleado>();
		empleados.add(new Operador("1", "operador"));
		empleados.add(new Operador("2", "operador"));
		empleados.add(new Operador("3", "operador"));
		empleados.add(new Operador("4", "operador"));
		empleados.add(new Supervisor("1", "supervisor"));
		empleados.add(new Director("1", "director"));

		ExecutorService executor = Executors.newFixedThreadPool(10);

		for (int llamada = 1; llamada < 13; llamada++) {

			Runnable atender = new Dispatcher(empleados, llamada);

			executor.execute(atender);

		}
		executor.shutdown();

	}

}
