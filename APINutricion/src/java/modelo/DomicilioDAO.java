package modelo;

import modelo.pojo.Domicilio;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;


public class DomicilioDAO {
    
    public static Mensaje registarDomicilio(Domicilio domicilio){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                int filasAfectadas = conexionBD.insert("domicilio.registrar", domicilio);
                conexionBD.commit();
                if(filasAfectadas > 0){
                    msj.setError(false);
                    msj.setMensaje("Domicilio del paciente registrado correctamente.");
                }else{
                    msj.setMensaje("Hubo un error al registrar la información del domicilio "
                            + "del paciente, inténtelo más tarde.");
                }
            } catch (Exception e) {
                msj.setMensaje("Error: "+e.getMessage());
            } finally{
                conexionBD.close();
            }
        }else{
            msj.setMensaje("No se tiene conexión, por favor inténtalo más tarde.");
        }
        return msj;
    }
    
    public static Domicilio obtenerDomicilioPaciente(int idPaciente){
        Domicilio domicilio = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                domicilio = conexionBD.selectOne("domicilio.obtenerDomicilio", 
                                                    idPaciente);
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }
        return domicilio;
    }
}
