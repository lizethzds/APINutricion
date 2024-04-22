package modelo;

import java.util.HashMap;
import modelo.pojo.Medico;
import modelo.pojo.Paciente;
import modelo.pojo.RespuestaLoginAplicacion;
import modelo.pojo.RespuestaLoginEscritorio;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AutenticacionDAO {
    
    
    public static RespuestaLoginEscritorio verificarSesionEscritorio(String numeroDePersonal, 
                                                                     String password){
        RespuestaLoginEscritorio respuesta = new RespuestaLoginEscritorio();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                HashMap<String, String> parametros = new HashMap<>();
                parametros.put("numeroDePersonal", numeroDePersonal);
                parametros.put("password", password);
                Medico medicoSesion = conexionBD.selectOne("autenticacion.loginEscritorio", 
                                                            parametros);
                if(medicoSesion != null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenido(a) "+medicoSesion.getNombre()+" al sistema de administración.");
                    respuesta.setMedicoSesion(medicoSesion);
                }else{
                    respuesta.setMensaje("Número de personal y/o contraseña incorrectas, favor de verificar sus credenciales.");
                }
            } catch (Exception e) {
                    respuesta.setMensaje("Error: "+e.getMessage());
            }
        }else{
            respuesta.setMensaje("Error de conexión con la base de datos.");
        }
        return respuesta;
    }
    
    public static RespuestaLoginAplicacion verificarSesionApp(String correo, 
                                                                     String password){
        RespuestaLoginAplicacion respuesta = new RespuestaLoginAplicacion();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                HashMap<String, String> parametros = new HashMap<>();
                parametros.put("email", correo);
                parametros.put("password", password);
                Paciente pacienteSesion = conexionBD.selectOne("autenticacion.loginAplicacion", 
                                                            parametros);
                if(pacienteSesion != null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenido(a) "+pacienteSesion.getNombre()+" a la aplicacion.");
                    respuesta.setPacienteSesion(pacienteSesion);
                }else{
                    respuesta.setMensaje("Correo y/o password incorrectos, favor de verificar sus credenciales.");
                }
            } catch (Exception e) {
                    respuesta.setMensaje("Error: "+e.getMessage());
            }
        }else{
            respuesta.setMensaje("Error de conexión con la base de datos.");
        }
        return respuesta;
    }
}
