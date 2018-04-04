package seleccion;

public class FactoriaSeleccion {
	
	public static Seleccion getSeleccion(String tipo) {
		switch (tipo) {
		case "Ruleta":
			return new Ruleta();
		case "Torneo":
			return new Torneo();
		default:
			break;
		}
		return null;
		
	}

}
