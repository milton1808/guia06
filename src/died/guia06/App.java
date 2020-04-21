package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner pauser = new Scanner (System.in);
		
		String[] nomAlumnos = {"Laureano","Rufino","Noah","David","Raquel","Jesús","Celina","Marcelino","Milagro","Gervasio","Elisabet","Juana","Marcelo","Gloria","Doris","Nicolás","Rocio","Anabella","Guadalupe","Felipa","Wenceslao","Martín","Ada","Alfredo","Liria","Jose","Rolando","Corina","Sabrina","Elias"};
		
		String[] nomCursos= {"Matemática","Biología","Lengua","Geografía","Historia","Educación Tecnológica","Educación Ciudadana","Inglés","Ed. Física","Economía"};
		Integer[] clCursos = {1,1,1,1,2,2,2,3,3,4};
		Integer[] cuposCursos = {30,30,30,30,20,20,20,5,5,3};
		Integer[] creditosCursos = {1,1,1,1,2,2,2,3,3,4};
		Integer[] creditosRequeridos = {0,0,0,0,2,2,2,4,4,7};
		
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		Random numRandom= new Random();
		int randomCurso;
		int randomAlumno;
		
		// creamos alumnos
		for(int i=0; i<30;i++) {
			alumnos.add(new Alumno(nomAlumnos[i],i));
		}
		
		//creamos cursos
		for(int i=0; i<10;i++) {
			cursos.add(new Curso(i,nomCursos[i],clCursos[i],cuposCursos[i],creditosCursos[i],creditosRequeridos[i]));
		}
		
		// intentamos inscribir a algun alumno a un curso random e intentamos aprobarle otro curso a otro alumno, repetimos esto mil veces
		for(int i=0;i<1000;i++) {
			randomCurso = numRandom.nextInt(10);
			randomAlumno = numRandom.nextInt(30);
			
				
			try {
				cursos.get(randomCurso).inscribirAlumno(alumnos.get(randomAlumno));
				System.out.println("Se Inscribió a "+alumnos.get(randomAlumno).getNombre()+" al curso "+cursos.get(randomCurso).getNombre());
			} catch (CreditosInsuficientesException | CupoCubiertoException | ExcesoDeCursosDelMismoCicloException
					| CursoYaAprobadoException | YaPerteneceAlCursoException | RegistroAuditoriaException e) {
				System.out.println(e.getMessage());
			}
			
			
			
			randomCurso = numRandom.nextInt(10);
			randomAlumno = numRandom.nextInt(30);			
			alumnos.get(randomAlumno).aprobar(cursos.get(randomCurso));
			
		}
		
		//imprimirmos los cursos por orden alfabetico
		for(int i=0; i<10;i++) {
			try {
				
				cursos.get(i).imprimirInscriptos();
			
			} catch (IOException e) {
				System.out.println("No se realizó la operación. Error al intentar escribir en el Registro, verifique el archivo registro.log");

			}
		}
		
		System.out.println("Presione ENTER para mostrar la lista de inscriptos ordenados por número de libreta. ");
		pauser.nextLine();
		
		//imprimimos los cursos por nro de libreta
		for(int i=0; i<10;i++) {
			try {
				
				cursos.get(i).imprimirInscriptosPorLibreta();
			
			} catch (IOException e) {
				System.out.println("No se realizó la operación. Error al intentar escribir en el Registro, verifique el archivo registro.log");

			}
		}
		
		System.out.println("Presione ENTER para mostrar la lista de inscriptos ordenados por creditos obtenidos. ");
		pauser.nextLine();
		
		//imprimimos los cursos por nro de Creditos obtenidos
		for(int i=0; i<10;i++) {
			try {
				
				cursos.get(i).imprimirInscriptosPorCreditosObtenidos();
			
			} catch (IOException e) {
				System.out.println("No se realizó la operación. Error al intentar escribir en el Registro, verifique el archivo registro.log");

			}
		}
		
				
		pauser.close();
		
		
	}
}
