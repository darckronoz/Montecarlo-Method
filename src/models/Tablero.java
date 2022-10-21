package models;

import java.util.ArrayList;

public class Tablero {

	int[] rangoMujeres;
	int[] rangoHombres;

	ArrayList<String[]> tablero;

	public Tablero() {
		tablero = new ArrayList<>();
		rangoMujeres = new int[4];
		rangoHombres = new int[4];
		addDianas();
	}
	
	 /**
	  *metodo encargado de cargar los valores de la diana en la cual se van a realizar los lanzamientos 
*/
	private void addDianas() {
		String[] central={"Central","30","20","10"};
		rangoMujeres[0]=Integer.parseInt(central[1]);
		rangoHombres[0]=Integer.parseInt(central[2]);
		tablero.add(central);
		String[] intermedia={"Intermedia","38","33","9"};
		rangoMujeres[1]=Integer.parseInt(intermedia[1])+rangoMujeres[0];
		rangoHombres[1]=Integer.parseInt(intermedia[2])+rangoHombres[0];
		tablero.add(intermedia);
		String[] exterior={"Exterior","27","40","8"};
		rangoMujeres[2]=Integer.parseInt(exterior[1])+rangoMujeres[1];
		rangoHombres[2]=Integer.parseInt(exterior[2])+rangoHombres[1];
		tablero.add(exterior);
		String[] error={"Error","5","7","0"};
		rangoMujeres[3]=Integer.parseInt(error[1])+rangoMujeres[2];
		rangoHombres[3]=Integer.parseInt(error[2])+rangoHombres[2];
		tablero.add(error);
	}

	 /**
	  *metodo encargado de cargar los valores de la diana en la cual se van a realizar los lanzamientos 
	  * @param ubicacionDiana numero pseudoaleatorio que representa la hubicacion donde cae el lanzamiento entre 0 y 99
	  * @param genero genero al cual pertenece el jugador
*/
	public int validarPosicion(double ubicacionDiana,int genero) {
		int puntosGanados=0;
		if (genero==0) {
			for (@SuppressWarnings("unused") int i : rangoMujeres) {
				if (ubicacionDiana<=rangoMujeres[0]) {
					puntosGanados = 10;
				}
				if (ubicacionDiana>=rangoMujeres[0]&&ubicacionDiana<=rangoMujeres[1]) {
					puntosGanados = 9;
				}
				if (ubicacionDiana>=rangoMujeres[1]&&ubicacionDiana<=rangoMujeres[2]) {
					puntosGanados = 8;
				}
				if (ubicacionDiana>=rangoMujeres[2]&&ubicacionDiana<=rangoMujeres[3]) {
					puntosGanados = 0;
				}
			}
		}else {
			for (@SuppressWarnings("unused") int i : rangoHombres) {
				if (ubicacionDiana<=rangoHombres[0]) {
					puntosGanados = 10;
				}
				if (ubicacionDiana>=rangoHombres[0]&&ubicacionDiana<=rangoHombres[1]) {
					puntosGanados = 9;
				}
				if (ubicacionDiana>=rangoHombres[1]&&ubicacionDiana<=rangoHombres[2]) {
					puntosGanados = 8;
				}
				if (ubicacionDiana>=rangoHombres[2]&&ubicacionDiana<=rangoHombres[3]) {
					puntosGanados = 0;
				}
			}

		}
		return puntosGanados;
	}

}
