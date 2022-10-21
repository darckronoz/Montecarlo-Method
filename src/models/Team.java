package models;

import java.util.ArrayList;

public class Team {

	private String nombre;
	private int jugadorALanzar;
	private int marcadorGlobal;
	private ArrayList<Player> jugadores;
	private Player jugadorGanador;
	
	 /**
     * @param nombre representa el identificador para cada equipo
     * @param jugadores lista la cual contiene los jugadores que hacen parte del equipo
     */

	public Team(String nombre,ArrayList<Player> jugadores) {
		jugadorALanzar=0;
		this.nombre = nombre;
		this.jugadores = jugadores;
		marcadorGlobal=0;
	}

	/**
     * @return retorna la sumatoria de los puntajes de todos los jugadores
     */
	public int getMarcadorGlobal() {
		for (Player jugador : jugadores) {
			marcadorGlobal+=jugador.getPuntaje();
		}
		return marcadorGlobal;
	}

	/**
     * @return retorna el jugador ganador por su puntaje
     */
	public Player ganadorIndividual() {
		Player ganadorIndividual=null;
		int data=0;
		for (Player jugador : jugadores) {
			if (jugador.getPuntaje()>data) {
				data= jugador.getPuntaje();
				ganadorIndividual=jugador;
			}
		}
		return ganadorIndividual;
	}

	/**
     * busca el jugador a lanzar y le permite lanzar mientras tiene resistencia 
     */
	public void ALanzar() {
		Player jugador= jugadores.get(getJugadorALanzar());
		while(jugador.getResistencia()>=5) {
			marcadorGlobal +=jugador.lanzar();
		}
		jugadorALanzar++;
	}

	/**
     * @return jugadorALanzar representa el siguiente jugador del equipo que debera lanzar
     */
	private int getJugadorALanzar() {
		if (jugadorALanzar==jugadores.size()) {
			jugadorALanzar=0;
		}
		return jugadorALanzar;
	}

	/**
     * @return la cantidad de jugadores que aun tienen resistencia para lanzar 
     */
	public int jugadoresDiponibles() {
		int energia =0;
		for (Player jugador : jugadores) {			
			if (jugador.getResistencia()>=5) {
				energia++;
			}
		}
		return energia;
	}

	/**
	 * @param id identificador de un jugador
     * @return jugador que coincide con el id suministrado
     */
	public Player buscarJugador(String id) {
		Player jugador=null;
		for (Player jugadorN : jugadores) {
			if (jugadorN.getId().equals(id)) {
				 jugador=jugadorN;
			}
		}
		return jugador;
	}
	
	/**
     * @return jugador con mas suerte al cual se le concede el tiro extra
     */
	public Player sorteTiroExtra() {
		int mayorSuerte= 0;
		Player jugadorMasSuerte= null;
		for (Player jugador : jugadores) {
			if (jugador.getSuerte()>mayorSuerte) {
				mayorSuerte=jugador.getSuerte();
				jugadorMasSuerte= jugador;
			}
		}
		jugadorMasSuerte.lanzarExtra();
		return jugadorMasSuerte;
	}
	
	
	/**
     * @return jugador que posee mas puntos 
     */
	public Player ganadorEquipo() {
		int mayorpuntaje= 0;
		Player jugadorMaspuntaje= null;
		for (Player jugador : jugadores) {
			if (jugador.getSuerte()>mayorpuntaje) {
				mayorpuntaje=jugador.getPuntaje();
				jugadorMaspuntaje= jugador;
			}
		}
		jugadorGanador=jugadorMaspuntaje;
		return jugadorGanador;
	}
	
	/**
     * metodo que recorre cada uno de los jugadores del equipo y los prepara para una siguiente ronda
     */

	public void siguienteRonda() {
		for (Player jugador : jugadores) {
			jugador.finDeRonda();
		}
	}
	
	/**
     * @param marcadorGlobal permite modificar el marcador total del equipo
     */
	public void setMarcadorGlobal(int marcadorGlobal) {
		this.marcadorGlobal = marcadorGlobal;
	}
	
	/**
     * @return nombre que identifica el equipo
     */
	public String getNombre() {
		return nombre;
	}

	/**
     * @return lista con todos los jugadores que conforman el equipo
     */
	public ArrayList<Player> getJugadores() {
		return jugadores;
	}
	
	/**
     * @return Jugador el cual posee mas puntaje de todo el equipo
     */
	public Player getJugadorGanador() {
		return jugadorGanador;
	}


	@Override
	public String toString() {
		return " \n  Equipo [nombre=" + nombre + ", jugadorALanzar=" + jugadorALanzar + ", marcadorGlobal=" + marcadorGlobal
				+ ", jugadores=" + jugadores + "]";
	}
}
