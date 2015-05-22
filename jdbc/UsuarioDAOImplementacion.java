package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImplementacion implements UsuarioDAO {
	//Obenemos la conexion como un atributo de la clase
	Connection conexion=Conexion.getConexion();
	
	@Override
	public List<UsuarioDTO> getUsuarios() {
		// TODO Auto-generated method stub
		List<UsuarioDTO> lista=new ArrayList<UsuarioDTO>();
		UsuarioDTO usuario=null;
		String sql="select * from alumno";
		Statement s;
		try {
			s = conexion.createStatement();
			ResultSet r=s.executeQuery(sql);
			while(r.next()){
				String nombre=r.getString("nombre");
				String apellidos=r.getString("apellidos");
				usuario=new UsuarioDTO(nombre, apellidos);
				lista.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return lista;
	}

	@Override
	public void addUsuario(UsuarioDTO u) {
		// TODO Auto-generated method stub
		String sql="insert into alumno(nombre,apellidos) values(?,?)";
		try {
			PreparedStatement s=conexion.prepareStatement(sql);
			s.setString(1,u.getNombre());
			s.setString(1,u.getDni());
			s.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarUsuario(UsuarioDTO u) {
		// TODO Auto-generated method stub
		String sql="delete from alumno where nombre=?";
		try {
			PreparedStatement s=conexion.prepareStatement(sql);
			s.setString(1,u.getNombre());
			s.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub

	}

}
