package clase15.laboratorio;

import java.util.HashMap;
import java.util.Map;

public interface Constantes {
	
	static Map<Integer, String> getCursos() {

		Map<Integer, String> cursos = new HashMap<Integer, String>();
		
		Integer id = 1;
		cursos.put(id++, "JAVA");
		cursos.put(id++, "PYTHON");
		cursos.put(id++, "SQL");
		cursos.put(id++, "JAVASCRIPT");
		cursos.put(id++, "REACT");

		return cursos;
	}
}
