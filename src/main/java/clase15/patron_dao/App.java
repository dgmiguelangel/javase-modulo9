package clase15.patron_dao;

public class App {

	public static void main(String[] args) {

		CompactoImpl impl = new CompactoImpl();

		Auto auto = impl.get("ABC-005");
//		System.out.println(auto);

//		Auto c = new Compacto("negro", "fiat", new Patente("ABC-005", true), true, 2);
//		impl.save(c);
		
//		System.out.println(impl.update(auto));
		
		System.out.println(impl.delete(auto));

	}

}
