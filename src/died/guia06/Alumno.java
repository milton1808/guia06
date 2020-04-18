package died.guia06;

import java.util.ArrayList;
import java.util.List;


public class Alumno implements Comparable<Alumno> {

	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando;
	private List<Curso> aprobados;

	
	public Alumno(String nombre, Integer nroLibreta) {
		super();
		this.nombre = nombre;
		this.nroLibreta = nroLibreta;
		this.cursando = new ArrayList<Curso>();
		this.aprobados = new ArrayList<Curso>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public Integer getNroLibreta() {
		return nroLibreta;
	}
	public List<Curso> getCursando(){
		return cursando;
	}
	public List<Curso> getAprobados(){
		return aprobados;
	}
	
	// Agrega un curso a cursando si todavía no lo está cursando
	public void inscripcionAceptada(Curso c) {
		
		if(cursando.contains(c)) System.out.println("El Alumno ya pertenece a este curso.");
		else cursando.add(c);

	}
	
	// Agrega un curso a aprobados si el curso pertenece a cursando
	public void aprobar(Curso c) {
		if(cursando.contains(c)) {
			aprobados.add(c);
			cursando.remove(c);
		}
		else System.out.println("El alumno no pertenece al curso que se quiere aprobar.");
	}
	
	// Retorna la suma de los creditos otorgados de los cursos aprobados
	public int creditosObtenidos() {
		int creditos=0;
		for(Curso curso : this.aprobados) {
			creditos += curso.getCreditosOtorgados();
		}
		return creditos;
	}
	
	// Compara si dos alumnos son iguales teniendo en cuenta el numero de libreta
	@Override
	public boolean equals(Object o) {
		
		return (o instanceof Alumno && ((Alumno)o).getNroLibreta() == this.nroLibreta);
	}
	
	// Establece que la comparación de dos alumnos se hace alfabéticamente por el atributo nombre
	@Override
	public int compareTo(Alumno o) {
		return this.nombre.compareToIgnoreCase(o.getNombre());
	}
	
	// Devuelve la cantidad de cursos cursando de un ciclo lectivo dado
	
	public int cantidadCursosCicloLectivo(Integer cl) {
		int cantidad=0;
		for(Curso curso : this.cursando) {
			if(curso.getCicloLectivo() == cl) cantidad++;
		}

		return cantidad;
	}

}
