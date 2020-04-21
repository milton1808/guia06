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
	
	public String getNombre() {
		return nombre;
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
	
	public void aprobar(Alumno a) {
		inscriptos.remove(a);
	}

	/**
	 * Este mÃ©todo, verifica si el alumno se puede inscribir y si es asÃ­ lo agrega al curso,
	 * agrega el curso a la lista de cursos en los que estÃ¡ inscripto el alumno y retorna verdadero.
	 * Caso contrario retorna falso y no agrega el alumno a la lista de inscriptos ni el curso a la lista
	 * de cursos en los que el alumno estÃ¡ inscripto.
	 * 
	 * Para poder inscribirse un alumno debe
	 * 		a) tener como minimo los creditos necesarios
	 *      b) tener cupo disponibles
	 *      c) puede estar inscripto en simultÃ¡neo a no mÃ¡s de 3 cursos del mismo ciclo lectivo.
	 * @param a
	 * @return
	 * @throws IOException 
	 */
	public Boolean inscribir(Alumno a) throws IOException {
		//se verifica si el alumno se puede inscribir
		if(a.creditosObtenidos() < creditosRequeridos) return false;
		if(inscriptos.size() >= cupo) return false;
		if(a.cantidadCursosCicloLectivo(cicloLectivo) >= 3) return false;
		if(inscriptos.contains(a)) return false;
		if(a.getAprobados().contains(this)) return false;
		
		// registra la inscripcion en el log
		try {
			log.registrar(this, "inscribir ",a.toString());
		}
		catch(IOException e){
			throw e;	
		}
		
		// inscribe al alumno al curso
		inscriptos.add(a);
		a.inscripcionAceptada(this);
		
		
		
		return true;
	}
	
	
	/**
	 * imprime los inscriptos en orden alfabetico
	 * @throws IOException 
	 */
	public void imprimirInscriptos() throws IOException {
		
		Collections.sort(inscriptos);
		
		
		//registra la impresión en el log
		try {
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
		}
		catch(IOException e){
			throw e;
		}
		
		System.out.println(this.toString());
		
	}
	
	//imprime inscriptos por nroLibreta en orden ascendente
	public void imprimirInscriptosPorLibreta() throws IOException {
		
		CompararLibreta comparador = new CompararLibreta();
		Collections.sort(inscriptos,comparador);		
		

		//registra la impresión en el log
		try {
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
		}
		catch(IOException e){
			throw e;
		}
		
		System.out.println(this.toString());
		
	}
	
	//imprime inscriptos por cantidad de creditos obtenidos en orden descendente
		public void imprimirInscriptosPorCreditosObtenidos() throws IOException {
			
			CompararCreditos comparador = new CompararCreditos();
			Collections.sort(inscriptos,comparador);		
			

			//registra la impresión en el log
			try {
				log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
			}
			catch(IOException e){
				throw e;
			}
			
			System.out.println(this.toString());
			
		}
		
		public void inscribirAlumno(Alumno a) throws CreditosInsuficientesException, CupoCubiertoException, ExcesoDeCursosDelMismoCicloException, CursoYaAprobadoException, YaPerteneceAlCursoException, RegistroAuditoriaException {
			//se verifica si el alumno se puede inscribir
			if(a.creditosObtenidos() < creditosRequeridos) throw new CreditosInsuficientesException("El Alumno no es digno de este Gran Curso. Posee creditos insuficientes.");
			if(inscriptos.size() >= cupo) throw new CupoCubiertoException("Suerte para la próxima. El alumno no se pudo incribir porque el cupo del curso está cubierto.");
			if(a.cantidadCursosCicloLectivo(cicloLectivo) >= 3) throw new ExcesoDeCursosDelMismoCicloException("Crees que puedes con todo pero no es así Nemo. No se pudo inscribir al alumno por exeso de materias del mismo ciclo lectivo.");
			if(inscriptos.contains(a)) throw new YaPerteneceAlCursoException("No te pases de vivo. El alumno ya está inscripto a este curso.");
			if(a.getAprobados().contains(this)) throw new CursoYaAprobadoException("Si te olvidaste de todo asistí de oyente. El alumno ya tiene aprobado este curso.");
			
			// registra la inscripcion en el log
			try {
				log.registrar(this, "inscribir ",a.toString());
			}
			catch(IOException e){
				throw new RegistroAuditoriaException("Ocurrió un error en el Registro y no se pudo completar la operación. vuelvas prontos.");	
			}
			
			// inscribe al alumno al curso
			inscriptos.add(a);
			a.inscripcionAceptada(this);
		}


}
