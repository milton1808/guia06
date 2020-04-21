package died.guia06;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CursoTest {
	ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	ArrayList<Curso> cursos = new ArrayList<Curso>();
	Curso cursoAparte = new Curso(10,"Ed Fisica",1,30,1,0);
	
	@BeforeEach 
	public void setUp() {
		alumnos.add(new Alumno("Alberto",32));
		alumnos.add(new Alumno("Carlos",3));
		alumnos.add(new Alumno("Brian",35));
		alumnos.add(new Alumno("Zaira",8));
		alumnos.add(new Alumno("Roman",10));
		alumnos.add(new Alumno("Macarena",5));
		

		
		cursos.add(new Curso(1, "matematica", 1, 30, 1, 0));
		cursos.add(new Curso(2, "fisica", 1, 2, 1, 0));
		cursos.add(new Curso(3, "quimica", 2, 30, 2, 1));
		cursos.add(new Curso(4, "died", 2, 30, 2, 1));
		cursos.add(new Curso(5, "diseño", 3, 30, 3, 2));
		cursos.add(new Curso(6, "ingles", 3, 30, 3, 2));
		cursos.add(new Curso(7, "Cs sociales", 4, 30, 4, 3));
		cursos.add(new Curso(8, "Cs naturales", 4, 30, 4, 3));
		cursos.add(new Curso(9, "dibujo", 5, 30, 5, 4));
		
		cursos.add(new Curso(10, "religion", 1, 30, 1, 0));
		cursos.add(new Curso(20, "informatica", 1, 2, 1, 0));
		
		
	}
	
	@Test
	void testInscripcionValida() throws IOException {
		Boolean inscripcion=false;
		
		inscripcion= cursos.get(0).inscribir(alumnos.get(0));
		
		assertTrue(inscripcion);
	}
	
	@Test
	void testInscripcionInvalidaPorCreditos() throws IOException {
		Boolean inscripcion=false;
		
		inscripcion= cursos.get(4).inscribir(alumnos.get(0));
		
		assertFalse(inscripcion);
	}
	
	@Test
	void testInscripcionInvalidaPorCupo() throws IOException {
		Boolean inscripcion=false;
		cursos.get(1).inscribir(alumnos.get(0));
		cursos.get(1).inscribir(alumnos.get(1));
		inscripcion= cursos.get(1).inscribir(alumnos.get(2));
		
		assertFalse(inscripcion);
	}
	@Test
	void testInscripcionInvalidaPorCicloLectivo() throws IOException {
		Boolean inscripcion=false;
		cursos.get(0).inscribir(alumnos.get(0));
		cursos.get(1).inscribir(alumnos.get(0));
		cursos.get(9).inscribir(alumnos.get(0));
		inscripcion= cursos.get(10).inscribir(alumnos.get(0));
		
		assertFalse(inscripcion);
	}
	@Test
	void testInscripcionInvalidaPorYaEstarInscripto() throws IOException {
		Boolean inscripcion=false;
		
		cursos.get(10).inscribir(alumnos.get(0));
		inscripcion= cursos.get(10).inscribir(alumnos.get(0));
		
		assertFalse(inscripcion);
	}
	@Test
	void testImprimirInscriptos() throws IOException {
		
		//creamos un string con el curso ordenado correctamente
		String valido = cursos.get(0).toString();
		String ordenado;
		valido = valido+"\n"+alumnos.get(0).toString();
		valido = valido+"\n"+alumnos.get(2).toString();
		valido = valido+"\n"+alumnos.get(1).toString();
		valido = valido+"\n"+alumnos.get(5).toString();
		valido = valido+"\n"+alumnos.get(4).toString();
		valido = valido+"\n"+alumnos.get(3).toString();
		
		//agregamos los alumnos desordenados 
		cursos.get(0).inscribir(alumnos.get(0));
		cursos.get(0).inscribir(alumnos.get(1));
		cursos.get(0).inscribir(alumnos.get(2));
		cursos.get(0).inscribir(alumnos.get(3));
		cursos.get(0).inscribir(alumnos.get(4));
		cursos.get(0).inscribir(alumnos.get(5));
		
		//imprimirmos los alumnos ordenados
		cursos.get(0).imprimirInscriptos();
		
		//recuperamos la lista ordenada por el metodo imprimirInscriptos
		ordenado=cursos.get(0).toString();
		
		//comparamos si se ordenó conforme a lo previsto
		assertEquals(ordenado,valido);
		
		
	}
	@Test
	void testImprimirInscriptosPorLibreta() throws IOException {
		
		//creamos un string con el curso ordenado correctamente
		String valido = cursos.get(0).toString();
		String ordenado;
		valido = valido+"\n"+alumnos.get(1).toString();
		valido = valido+"\n"+alumnos.get(5).toString();
		valido = valido+"\n"+alumnos.get(3).toString();
		valido = valido+"\n"+alumnos.get(4).toString();
		valido = valido+"\n"+alumnos.get(0).toString();
		valido = valido+"\n"+alumnos.get(2).toString();
		
		//agregamos los alumnos desordenados 
		cursos.get(0).inscribir(alumnos.get(0));
		cursos.get(0).inscribir(alumnos.get(1));
		cursos.get(0).inscribir(alumnos.get(2));
		cursos.get(0).inscribir(alumnos.get(3));
		cursos.get(0).inscribir(alumnos.get(4));
		cursos.get(0).inscribir(alumnos.get(5));
		
		//imprimirmos los alumnos ordenados
		cursos.get(0).imprimirInscriptosPorLibreta();
		
		//recuperamos la lista ordenada por el metodo imprimirInscriptos
		ordenado=cursos.get(0).toString();
		
		//comparamos si se ordenó conforme a lo previsto
		assertEquals(ordenado,valido);
		
		
	}
	
	
	@Test
	void testImprimirInscriptosPorCreditosObtenidos() throws IOException {
		//inscribimos todos al curso 9
		cursos.get(9).inscribir(alumnos.get(0));
		cursos.get(9).inscribir(alumnos.get(1));
		cursos.get(9).inscribir(alumnos.get(2));
		cursos.get(9).inscribir(alumnos.get(3));
		cursos.get(9).inscribir(alumnos.get(4));
		//aprueban todos menos el alumno 3
		alumnos.get(0).aprobar(cursos.get(9));
		alumnos.get(1).aprobar(cursos.get(9));
		alumnos.get(2).aprobar(cursos.get(9));
		alumnos.get(4).aprobar(cursos.get(9));
		//inscribimos a los que aprobaron al curso 2
		cursos.get(2).inscribir(alumnos.get(0));
		cursos.get(2).inscribir(alumnos.get(1));
		cursos.get(2).inscribir(alumnos.get(2));
		cursos.get(2).inscribir(alumnos.get(4));
		//aprueban todos menos el alumno 1
		alumnos.get(0).aprobar(cursos.get(2));
		alumnos.get(2).aprobar(cursos.get(2));
		alumnos.get(4).aprobar(cursos.get(2));
		//inscribimos a los que aprobaron al curso 5
		cursos.get(5).inscribir(alumnos.get(0));
		cursos.get(5).inscribir(alumnos.get(2));
		cursos.get(5).inscribir(alumnos.get(4));
		//aprueban todos menos el alumno 4
		alumnos.get(0).aprobar(cursos.get(2));
		alumnos.get(2).aprobar(cursos.get(2));
		//inscribimos a los que aprobaron al curso 7
		cursos.get(7).inscribir(alumnos.get(0));
		cursos.get(7).inscribir(alumnos.get(2));
		//aprueban todos menos el alumno 0
		alumnos.get(2).aprobar(cursos.get(7));
		
		/*
		 * Entonces deberia quedar, ordenado por creditos obtenidos:
		 * Alumno2 10creditos
		 * Alumno0 6creditos
		 * Alumno4 3creditos
		 * Alumno1 1credito
		 * Alumno3 0creditos
		 */
		
		
		
		//creamos un string con el curso 0 ordenado correctamente
		String valido = cursos.get(0).toString();
		String ordenado;
		valido = valido+"\n"+alumnos.get(2).toString();
		valido = valido+"\n"+alumnos.get(0).toString();
		valido = valido+"\n"+alumnos.get(4).toString();
		valido = valido+"\n"+alumnos.get(1).toString();
		valido = valido+"\n"+alumnos.get(3).toString();
		
		//agregamos los alumnos desordenados al curso 0 
		cursos.get(0).inscribir(alumnos.get(0));
		cursos.get(0).inscribir(alumnos.get(1));
		cursos.get(0).inscribir(alumnos.get(2));
		cursos.get(0).inscribir(alumnos.get(3));
		cursos.get(0).inscribir(alumnos.get(4));
		
		//imprimirmos los alumnos ordenados
		cursos.get(0).imprimirInscriptosPorCreditosObtenidos();
		
		//recuperamos la lista ordenada por el metodo imprimirInscriptosPorLibreta
		ordenado=cursos.get(0).toString();
		
		//comparamos si se ordenó conforme a lo previsto
		assertEquals(ordenado,valido);
		
		
	}
	
	@Test
	void testNuevaInscripcionValida() throws CreditosInsuficientesException, CupoCubiertoException, ExcesoDeCursosDelMismoCicloException, CursoYaAprobadoException, YaPerteneceAlCursoException, RegistroAuditoriaException {
		
		cursos.get(0).inscribirAlumno(alumnos.get(0));
		
		assertEquals(cursos.get(0),alumnos.get(0).getCursando().get(0));
		
	}
	
	 @Test
	void testNuevaInscripcionInvalidaPorCreditos() throws CupoCubiertoException, ExcesoDeCursosDelMismoCicloException, CursoYaAprobadoException, YaPerteneceAlCursoException, RegistroAuditoriaException {
	
		try {
			cursos.get(4).inscribirAlumno(alumnos.get(0));
			fail();
		} catch (CreditosInsuficientesException e) {
			assertEquals(e.getMessage(),"El Alumno no es digno de este Gran Curso. Posee creditos insuficientes.");
		}
		
	}
	
	@Test
	void testNuevaInscripcionInvalidaPorCupo() throws IOException, CreditosInsuficientesException, ExcesoDeCursosDelMismoCicloException, CursoYaAprobadoException, YaPerteneceAlCursoException, RegistroAuditoriaException{
		
		cursos.get(1).inscribir(alumnos.get(0));
		cursos.get(1).inscribir(alumnos.get(1));
		
		try {
			cursos.get(1).inscribirAlumno(alumnos.get(2));
			fail();
		} catch (CupoCubiertoException e) {
			assertEquals(e.getMessage(),"Suerte para la próxima. El alumno no se pudo incribir porque el cupo del curso está cubierto.");
		}
		
	}
	@Test
	void testNuevaInscripcionInvalidaPorCicloLectivo() throws IOException, CreditosInsuficientesException, CupoCubiertoException, CursoYaAprobadoException, YaPerteneceAlCursoException, RegistroAuditoriaException {
		cursos.get(0).inscribir(alumnos.get(0));
		cursos.get(1).inscribir(alumnos.get(0));
		cursos.get(9).inscribir(alumnos.get(0));
		
		
		try {
			cursos.get(10).inscribirAlumno(alumnos.get(0));
			fail();
		} catch (ExcesoDeCursosDelMismoCicloException e) {
			assertEquals(e.getMessage(),"Crees que puedes con todo pero no es así Nemo. No se pudo inscribir al alumno por exeso de materias del mismo ciclo lectivo.");

		}
		
		
	}
	@Test
	void testNuevaInscripcionInvalidaPorYaEstarInscripto() throws IOException, CreditosInsuficientesException, CupoCubiertoException, ExcesoDeCursosDelMismoCicloException, CursoYaAprobadoException, RegistroAuditoriaException {
				
		cursos.get(10).inscribir(alumnos.get(0));
		
		try {
			cursos.get(10).inscribirAlumno(alumnos.get(0));
			fail();
		} catch (YaPerteneceAlCursoException e) {
			assertEquals(e.getMessage(),"No te pases de vivo. El alumno ya está inscripto a este curso.");

		}
	
	}
	
	@Test
	void testNuevaInscripcionInvalidaPorTenerCursoAprobado() throws IOException, CreditosInsuficientesException, CupoCubiertoException, ExcesoDeCursosDelMismoCicloException, YaPerteneceAlCursoException, RegistroAuditoriaException {
				
		cursos.get(0).inscribir(alumnos.get(0));
		alumnos.get(0).aprobar(cursos.get(0));
		
		try {
			cursos.get(0).inscribirAlumno(alumnos.get(0));
			fail();
		} catch (CursoYaAprobadoException e) {
			assertEquals(e.getMessage(),"Si te olvidaste de todo asistí de oyente. El alumno ya tiene aprobado este curso.");

		}
	
	}

	
	// Una forma de probar este test es haciendo de solo lectura el archivo "registro.log"
	@Test
	void testNuevaInscripcionInvalidaPorErrorDeRegistro() throws IOException, CreditosInsuficientesException, CupoCubiertoException, ExcesoDeCursosDelMismoCicloException, CursoYaAprobadoException, YaPerteneceAlCursoException {
				
		try {
			cursos.get(0).inscribirAlumno(alumnos.get(0));
			fail();
		} catch (RegistroAuditoriaException e) {
			assertEquals(e.getMessage(),"Ocurrió un error en el Registro y no se pudo completar la operación. vuelvas prontos.");
		}
	
	
	}

}
