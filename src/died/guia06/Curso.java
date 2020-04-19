package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import died.guia06.util.Registro;

/**
 * Clase que representa un curso. Un curso se identifica por su ID y por su nombre y ciclo lectivo.
 * Un curso guarda una lista de los inscriptos actuales que tienen.
 * Un curso, al aprobarlo, otorga una cantidad de creditos definidas en el curso.
 * Un curso requiere que para inscribirnos tengamos al menos la cantidad de creditos requeridas, y que haya cupo disponible
 * @author marti
 *
 */
public class Curso {

	private Integer id;
	private String nombre;
	private Integer cicloLectivo;
	private Integer cupo; 
	private List<Alumno> inscriptos;
	private Integer creditos;
	private Integer creditosRequeridos;
	
	private Registro log;
	
	public Curso() {
		super();
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
	}
	
	
	public Curso(Integer id, String nombre, Integer cicloLectivo, Integer cupo,
			Integer creditos, Integer creditosRequeridos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cicloLectivo = cicloLectivo;
		this.cupo = cupo;
		this.creditos = creditos;
		this.creditosRequeridos = creditosRequeridos;
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
	}


	public Integer getCreditosOtorgados() {
		return creditos;
	}
	public Integer getCicloLectivo() {
		return cicloLectivo;
	}
	@Override
	public String toString() {
		String frase;
		frase ="Curso: "+this.nombre+"\nId: "+this.id+"\nCiclo Lectivo: "+this.cicloLectivo+"\nCupo: "+this.cupo+"\nCreditos Otorgados: "+this.creditos+"\nCreditos Requerido: "+this.creditosRequeridos+"\nIntegrantes:\nNOMBRE\t\tLIBRETA\t\tCREDITOS OBTENIDOS";
		for(Alumno a : inscriptos) {
			frase = frase+"\n"+a.toString();
		}
		
		return frase;
		
	}

	/**
	 * Este método, verifica si el alumno se puede inscribir y si es así lo agrega al curso,
	 * agrega el curso a la lista de cursos en los que está inscripto el alumno y retorna verdadero.
	 * Caso contrario retorna falso y no agrega el alumno a la lista de inscriptos ni el curso a la lista
	 * de cursos en los que el alumno está inscripto.
	 * 
	 * Para poder inscribirse un alumno debe
	 * 		a) tener como minimo los creditos necesarios
	 *      b) tener cupo disponibles
	 *      c) puede estar inscripto en simultáneo a no más de 3 cursos del mismo ciclo lectivo.
	 * @param a
	 * @return
	 */
	public Boolean inscribir(Alumno a) {
		//se verifica si el alumno se puede inscribir
		if(a.creditosObtenidos() < creditosRequeridos) return false;
		if(inscriptos.size() >= cupo) return false;
		if(a.cantidadCursosCicloLectivo(cicloLectivo) >= 3) return false;
		if(inscriptos.contains(a)) return false;
		
		// registra la inscripcion en el log
		try {
			log.registrar(this, "inscribir ",a.toString());
		}
		catch(IOException e){
			System.out.println("Ocurrio un error al intentar escribir en el Registro.");
		}
		
		// inscribe al alumno al curso
		inscriptos.add(a);
		a.inscripcionAceptada(this);
		
		
		
		return true;
	}
	
	
	/**
	 * imprime los inscriptos en orden alfabetico
	 */
	public void imprimirInscriptos() {
		
		Collections.sort(inscriptos);
		System.out.println(this.toString());
		
		//registra la impresi�n en el log
		try {
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
		}
		catch(IOException e){
			System.out.println("Ocurrio un error al intentar escribir en el Registro.");
		}
		
	}
	
	//imprime inscriptos por nroLibreta en orden ascendente
	public void imprimirInscriptosPorLibreta() {
		
		CompararLibreta comparador = new CompararLibreta();
		Collections.sort(inscriptos,comparador);		
		System.out.println(this.toString());

		//registra la impresi�n en el log
		try {
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
		}
		catch(IOException e){
			System.out.println("Ocurrio un error al intentar escribir en el Registro.");
		}
		
	}
	
	//imprime inscriptos por cantidad de creditos obtenidos en orden descendente
		public void imprimirInscriptosPorCreditosObtenidos() {
			
			CompararCreditos comparador = new CompararCreditos();
			Collections.sort(inscriptos,comparador);		
			System.out.println(this.toString());

			//registra la impresi�n en el log
			try {
				log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
			}
			catch(IOException e){
				System.out.println("Ocurrio un error al intentar escribir en el Registro.");
			}
			
		}


}
