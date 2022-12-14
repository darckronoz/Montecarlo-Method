package models;
public class Launch {

	private double puntaje;
	private Board tablero ;


	public Launch() {
		tablero = new Board();
	}

	 /**
	  * @return un numero pseudoaleatorio que representa la posicion en la cual cae el lanzamiento
*/
	private double calcularPosicionLanzamiento() {
		return Util.pseudoaleatorios(0,100);
	}

	 /**
	  * @param numero que representa un genero o sexo 0 mujer 1 hombre
	  * @return el puntaje ganado por el lanzamiento
*/
	public int puntajeLanzamiento(int genero) {
		puntaje =tablero.validarPosicion(calcularPosicionLanzamiento(), genero);
		return (int) puntaje;
	}

	@Override
	public String toString() {
		return "Lanzamiento [puntaje=" + puntaje + ", tablero=" + tablero + "]";
	}

}
