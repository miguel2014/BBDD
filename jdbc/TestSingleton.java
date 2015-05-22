package jdbc;

public class TestSingleton {
public static void main(String[] args) {
	//Creamos objetos Singleton
	//Singleton s1=new Singleton();//No se puede hacer pues el constructor es privador
	Singleton s1=Singleton.getOBJETO_UNICO();
	Singleton s2=Singleton.getOBJETO_UNICO();
	System.out.println(s1.equals(s2));
}
}
class Singleton{
	private static Singleton OBJETO_UNICO =new Singleton();
	//Constructor privado
	private Singleton(){};
	public static Singleton getOBJETO_UNICO() {
		return OBJETO_UNICO;
	};
	
}