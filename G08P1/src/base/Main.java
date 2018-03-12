package base;
import seleccion.Ruleta;

import java.util.ArrayList;

import GUI.Menu;
import cruce.UnPunto;
import funciones.Funcion1;
import mutacion.Mutacion;

public class Main {

	// PRUEBA
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//ArrayList<Gen> poblacion = null; //cadena de bits (genotipo)
		//double[] fenotipo = null; //fenotipo
		//double aptitud = 0;//funci�n de evaluaci�n fitness adaptaci�n);
		//double puntuacion = 0; //puntuaci�n relativa(aptitud/suma)
		//double punt_acum = 0; //puntuaci�n acumulada para selecci�n
		int longitud = 5; //longitud de poblaci�n 
		double tolerancia = 0.0001; //tambien conocido como precision 
		//double lgen = 0;
		boolean maximize = false;
	
		Menu menu = new Menu();
		menu.setVisible(true);
		
		//Funcion1 funcion1 = new Funcion1(/*fenotipo, puntuacion, punt_acum,*/ 
		//		longitud, tolerancia, /*lgen, maximize,*/ 0, 32);
		/*
		ArrayList<Gen> p = funcion1.getPoblacion();
		double[] f = funcion1.getFenotipo();

		System.out.println();
		
		Ruleta r = new Ruleta(funcion1.getFitness());
		r.showSeleccionados();
		
		System.out.println();
		
		for (int i = 0; i < longitud; i++) {
			
			boolean[] ptotal = p.get(i).getAlelos();
			
			for (int j = 0; j < ptotal.length; j++) {
				if(ptotal[j] == true) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
			System.out.println(f[i]);
			System.out.println();
		}
		
		UnPunto cruce = new UnPunto(0.7, funcion1);
		cruce.showCruzados();

		Mutacion mutacion1 = new Mutacion(0.02, funcion1);
		System.out.println();
		
		p = funcion1.getPoblacion();
		for (int i = 0; i < longitud; i++) {
			
			boolean[] ptotal = p.get(i).getAlelos();
			
			for (int j = 0; j < ptotal.length; j++) {
				if(ptotal[j] == true) System.out.print(1);
				else System.out.print(0);
			}
			System.out.println();
			System.out.println(f[i]);
			System.out.println();
		}
		
		System.out.println("------------------------------------------------------------");
		*/
	}
}
