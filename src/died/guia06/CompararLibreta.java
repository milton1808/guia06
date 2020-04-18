package died.guia06;

import java.util.Comparator;

public class CompararLibreta implements Comparator<Alumno> {

	@Override
	public int compare(Alumno o1, Alumno o2) {
		return o1.getNroLibreta()-o2.getNroLibreta();
	}

}
