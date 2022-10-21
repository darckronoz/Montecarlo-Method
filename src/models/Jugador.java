package models;

import java.util.ArrayList;

public class Jugador {

	private String id;
	private int resistenciaRondaAnterior;
	private int resistenciaActual;
	private int experiencia;
	private int suerte;
	private int genero;
	private int cantidadLanzamiento;
	private int tirosExtra;
	private int puntaje;
	private int lanzamientosSinResistencia;
	private boolean tienePuntosDeExperiencia;
	private int sumatoriaSuerte;

	private ArrayList<Lanzamiento> lanzamientos;
	private ArrayList<Integer> puntajesRondas;


	 /**
     * 
     * @param id Valor que prepresenta un identificador para cada jugador
     * @param resistenciaInicial Valor que representa la resistencia con la cual inicia un jugador
     * @param experiencia Valor que representa la experiencia del jugador
     * @param suerte Valor aleatorio que representa la fortuna de cada jugador
     * @param genero Representa el sexo de cada jugador 1 para hombre y 0 para mujer
     */
	public Jugador(String id, int resistenciaInicial, int experiencia, int suerte, int genero) {
		this.id = id;
		this.resistenciaRondaAnterior=resistenciaInicial;
		this.resistenciaActual=resistenciaInicial;
		this.experiencia = experiencia;
		this.tienePuntosDeExperiencia=false;
		this.suerte = suerte;
		this.genero = genero;
		puntaje=0;
		cantidadLanzamiento=0;
		lanzamientosSinResistencia=0;
		tirosExtra=0;
		sumatoriaSuerte=suerte;
		lanzamientos = new ArrayList<>();
		puntajesRondas = new ArrayList<>();
	}

	 /**
     * 
     * @param lanzamiento Variable que prepresenta un lanzamiento realizado por el jugador
     */
	public void addLanzamiento(Lanzamiento lanzamiento) {
		lanzamientos.add(lanzamiento);
	}

	 /**
     *  metodo que realiza un nuevo lanzamiento  y asigna el punteje ganado al jugador 
     *  resta la resistencia del jugador al realizar el lanzamiento 
     * @return Valor que representa el puntaje ganado al realizar un lanzamiento
     */
	public int  lanzar() {
		int puntajeLanzamiento=0;
		if (resistenciaActual>=5) {
			Lanzamiento l = new Lanzamiento();
			puntajeLanzamiento= l.puntajeLanzamiento(genero);
			lanzamientos.add(l);
			cantidadLanzamiento++;
			if (!tienePuntosDeExperiencia) {
				resistenciaActual= resistenciaActual-5;
			}
		}
		puntaje+=puntajeLanzamiento;
		return puntajeLanzamiento;
	}
	
	 /**
     * metodo que realiza un nuevo lanzamiento extra y asigna el punteje ganado al jugador 
     * @return Valor que representa el puntaje ganado al realizar un lanzamiento extra
     */
	public int lanzarExtra() {
		int puntajeExtra = 0;
		Lanzamiento l = new Lanzamiento();
		puntajeExtra+= l.puntajeLanzamiento(genero);
		tirosExtra++;
		return puntajeExtra;
	}

	 /**
     * 
     * metodo que actualiza las variables del jugador al finalizar una ronda 
     */
	public void finDeRonda() {
		puntajesRondas.add(puntaje);
		setSuerte((int) Util.pseudoaleatorios(1,4));
		sumatoriaSuerte+=suerte;
		if (resistenciaRondaAnterior>=5) {
			if (experiencia<19) {
				resistenciaActual= (int) (resistenciaRondaAnterior-(Util.pseudoaleatorios(1, 2)));
			}
			else if (experiencia==19) {
				if (lanzamientosSinResistencia==2) {
					lanzamientosSinResistencia=0;
					tienePuntosDeExperiencia=false;
				}else {
					lanzamientosSinResistencia++;
					tienePuntosDeExperiencia=true;	
				}
			}
		}
		resistenciaRondaAnterior=resistenciaActual;
	}
	
	 /**
     * @return retorna el valor del id
     */
	
	public String getId() {
		return id;
	}

	 /**
     * @param actualiza el valor del id
     */
	public void setId(String id) {
		this.id = id;
	}

	 /**
     * @return retorna el valor de la resistencia
     */
	public int getResistencia() {
		return resistenciaActual;
	}

	 /**
     * @param actualiza el valor de la resistencia
     */
	public void setResistencia(int resistencia) {
		this.resistenciaActual = resistencia;
	}

	 /**
     * @return retorna el valor de la experiencia
     */
	public int getExperiencia() {
		return experiencia;
	}

	 /**
     * @param actualiza el valor de la experiencia
     */
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	 /**
     * @return retorna el valor de la suerte
     */
	public int getSuerte() {
		return suerte;
	}

	 /**
     * @param actualiza el valor de la suerte
     */
	public void setSuerte(int suerte) {
		this.suerte = suerte;
	}
	
	 /**
     * @return retorna el valor del genero
     */

	public int getGenero() {
		return genero;
	}

	 /**
     * @param actualiza el valor del genero
     */
	public void setGenero(int genero) {
		this.genero = genero;
	}

	 /**
     * @return retorna el valor la cantidad de lanzamientos realizados
     */
	public int getCantidadLanzamiento() {
		return cantidadLanzamiento;
	}

	 /**
     * @param actualiza el valor de los lanzamientos
     */
	public void setCantidadLanzamiento(int cantidadLanzamiento) {
		this.cantidadLanzamiento = cantidadLanzamiento;
	}

	 /**
     * @return retornala cantidad de tiros extra realizados
     */
	public int getTirosExtra() {
		return tirosExtra;
	}

	 /**
     * @param actualiza el valor de los tiros extra
     */
	public void setTirosExtra(int tirosExtra) {
		this.tirosExtra = tirosExtra;
	}
	
	 /**
     * @return retorna el valor del puntaje
     */
	public int getPuntaje() {
		return puntaje;
	}

	 /**
     * @return retorna el valor de la suerte total 
     */
	public int getSumatoriaSuerte() {
		return sumatoriaSuerte;
	}
	
	 /**
     * @return retorna uan lista con cada uno de los puntajes ganados en una ronda
     */
	public ArrayList<Integer> getPuntajesRondas() {
		return puntajesRondas;
	}



	@Override
	public String toString() {
		return "\n Jugador [id=" + id + ", resistenciaRondaAnterior=" + resistenciaRondaAnterior + ", resistenciaActual="
				+ resistenciaActual + ", experiencia=" + experiencia + ", suerte=" + suerte + " suerteTOTAL = "+sumatoriaSuerte+", genero=" + genero
				+ ", cantidadLanzamiento=" + cantidadLanzamiento + ", tirosExtra=" + tirosExtra + ", puntaje=" + puntaje
				+"]";
	}

}
