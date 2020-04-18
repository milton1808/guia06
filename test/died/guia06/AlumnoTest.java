package died.guia06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlumnoTest {
	ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	ArrayList<Curso> cursos = new ArrayList<Curso>();
	Curso cursoAparte = new Curso(10,"Ed Fisica",1,30,1,0);
	
	@BeforeEach 
	public void setUp() {
		alumnos.add(new Alumno("Alberto",32));
		alumnos.add(new Alumno("Alberto Jose",32));
		alumnos.add(new Alumno("Brian",35));


		
		cursos.add(new Curso(1, "matematica", 1, 30, 1, 0));
		cursos.add(new Curso(2, "fisica", 1, 30, 1, 0));
		cursos.add(new Curso(3, "quimica", 2, 30, 2, 0));
		cursos.add(new Curso(4, "died", 2, 30, 2, 0));
		cursos.add(new Curso(5, "diseño", 3, 30, 3, 0));
		cursos.add(new Curso(6, "ingles", 3, 30, 3, 0));
		cursos.add(new Curso(7, "Cs sociales", 4, 30, 4, 0));
		cursos.add(new Curso(8, "Cs naturales", 4, 30, 4, 0));
		cursos.add(new Curso(9, "dibujo", 5, 30, 5, 0));
		
		
		
		
	}
	@Test
	public void assertNombreCorrecto(){
		
		assertSame(alumnos.get(0).getNombre(),"Alberto");
		
	}
	@Test
	public void assertLibretaCorrecta(){
		
		assertSame(alumnos.get(0).getNroLibreta(),32);
		
	}
	@Test
	public void assertNombreIncorrecto(){
		
		assertNotSame(alumnos.get(0).getNombre(),"juan63");
		
	}
	@Test
	public void assertLibretaIncorrecta(){
		
		assertNotSame(alumnos.get(0).getNroLibreta(),326);
		
	}
	
	@Test
	void testInscripcionAceptadaCorrecta() {
		for(Curso c : cursos) {
			alumnos.get(0).inscripcionAceptada(c);
		}		
		assertEquals(alumnos.get(0).getCursando(),cursos);
	}
	
	@Test
	void testInscripcionNoAceptada() {
		for(Curso c : cursos) {
			alumnos.get(0).inscripcionAceptada(c);
			alumnos.get(0).inscripcionAceptada(c);
		}		
		
		assertNotSame(alumnos.get(0).getCursando(),cursos);
	}
	
	@Test
	void testAprobarCorrecto() {
		for(Curso c : cursos) {
			alumnos.get(0).inscripcionAceptada(c);
		}
		for(Curso c : cursos) {
			alumnos.get(0).aprobar(c);
		}
		
		
		
		assertEquals(alumnos.get(0).getAprobados(),cursos);
		
	}
	@Test
	void testAprobarInorrecto() {
		for(Curso c : cursos) {
			alumnos.get(0).inscripcionAceptada(c);
		}
		for(Curso c : cursos) {
			alumnos.get(0).aprobar(c);
		}
		alumnos.get(0).aprobar(cursoAparte);
		
		assertEquals(alumnos.get(0).getAprobados(),cursos);
		
	}
	
	
	@Test
	void testCreditosObtenidos() {
		int creditos=0;
		for(Curso c : cursos) {
			alumnos.get(0).inscripcionAceptada(c);
		}
		for(Curso c : cursos) {
			alumnos.get(0).aprobar(c);
		}
		creditos = alumnos.get(0).creditosObtenidos();
		assertSame(creditos,25);
		
	}

	@Test
	void testEqualsVerdadero() {	
		assertTrue(alumnos.get(0).equals(alumnos.get(1)));
		
	}
	@Test
	void testEqualsFalso() {	
		assertFalse(alumnos.get(0).equals(alumnos.get(2)));
		
	}
	@Test
	void testEqualsDiferenteObjeto() {	
		assertFalse(alumnos.get(0).equals(cursos.get(0)));
	}
	@Test
	void testCompareToAlfabeticoAscendente() {	
		Integer comparacion;
		comparacion = alumnos.get(0).compareTo(alumnos.get(2));
		assertTrue(comparacion < 0);
		
	}
	@Test
	void testCompareToAlfabeticoAscendente2() {	
		Integer comparacion;
		comparacion = alumnos.get(2).compareTo(alumnos.get(1));
		assertTrue(comparacion > 0);
		
	}
	@Test
	void testCompareToAlfabeticoAscendenteIguales() {	
		Integer comparacion;
		comparacion = alumnos.get(2).compareTo(alumnos.get(2));
		assertTrue(comparacion == 0);
		
	}
	@Test
	void testCantidadCursosCicloLectivo() {	
		int respuesta;
		for(Curso c : cursos) {
			alumnos.get(0).inscripcionAceptada(c);
		}
		respuesta= alumnos.get(0).cantidadCursosCicloLectivo(1);
		assertEquals(respuesta,2);
		
	}

}
