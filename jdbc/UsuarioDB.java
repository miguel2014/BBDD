package jdbc;

import java.util.List;

public class UsuarioDB {

	public static void main(String[] args) {
		UsuarioDAOImplementacion uIMP=new UsuarioDAOImplementacion();
		List<UsuarioDTO> lista=uIMP.getUsuarios();
		System.out.println(lista);
		UsuarioDTO u=new UsuarioDTO("Mike","Apellido");
		//uIMP.addUsuario(u);
		uIMP.eliminarUsuario(u);
		Conexion.desconectar();
	}

}
