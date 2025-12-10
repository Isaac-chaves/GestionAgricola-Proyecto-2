 

import Modelo.Service.CultivoService;
import Modelo.Dto.CultivoDTO;
import Modelo.ConexionBD; 
import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("--- 1. PRUEBA DE CONEXIÓN A MARIA DB ---");
        try (Connection conn = ConexionBD.getInstance().getConnection()) {
            if (conn != null) {
                System.out.println("✅ Conexión exitosa a la base de datos 'Gestion_Agricola' (MariaDB).");
            } else {
                System.out.println("❌ Fallo la conexión.");
            }
        } catch (Exception e) {
            System.err.println("❌ ERROR FATAL DE CONEXIÓN. Asegúrate de que Docker está corriendo.");
            System.err.println("Mensaje de error: " + e.getMessage());
            return;
        }
        
        System.out.println("\n------------------------------------------------");
        System.out.println("--- 2. PRUEBA DEL CULTIVOSERVICE (SELECT ALL) ---");
        
        CultivoService service = new CultivoService();

        try {
            List<CultivoDTO> cultivos = service.obtenerTodosLosCultivos();

            if (cultivos.isEmpty()) {
                System.out.println("⚠️ La tabla de cultivos está vacía. Verifica tu init.sql.");
            } else {
                System.out.println("✅ Datos de cultivos recuperados exitosamente (" + cultivos.size() + " registros):");
                for (CultivoDTO dto : cultivos) {
                    System.out.println("   ID: " + dto.getId() + 
                                       ", Nombre: " + dto.getNombre() + 
                                       ", Tipo: " + dto.getTipo() + 
                                       ", Área: " + dto.getAreaSembrada() + 
                                       ", Estado: " + dto.getEstadoCrecimiento());
                }
            }
        } catch (Exception e) {
            System.err.println("❌ ERROR al usar CultivoService. Revisa las dependencias y el CultivoMapper.");
            e.printStackTrace();
        }
    }
}