package pruebas; 

import Modelo.Dto.AlmacenamientoDTO;
import Modelo.Dto.CultivoDTO;
import Modelo.Dto.ProduccionDTO;
import Modelo.Dto.ProductoAlmacenadoDTO;
import Modelo.Dto.TrabajadorDTO;
import Modelo.Dto.DistribucionDTO;
import Modelo.Service.AlmacenamientoService;
import Modelo.Service.CultivoService;
import Modelo.Service.ProduccionService;
import Modelo.Service.ProductoAlmacenadoService;
import Modelo.Service.TrabajadorService;
import Modelo.Service.DistribucionService;
import java.time.LocalDate;

public class Main {

    // Cédula de prueba nueva generada dinámicamente para evitar conflicto UNIQUE
    // Esto resuelve el error: Duplicate entry '303330333'
    private static final String NUEVA_CEDULA_TRABAJADOR = generarCedulaUnica();
    
    // Cédula existente en gestion_agricola.sql para pruebas de FK en Distribucion
    private static final String CEDULA_EXISTENTE_FK = "202220222"; 
    
    // Método simple para generar una cédula única basada en el tiempo
    private static String generarCedulaUnica() {
        // Usa el tiempo actual en milisegundos y toma los últimos 9 dígitos
        String timeStr = String.valueOf(System.currentTimeMillis());
        // Aseguramos que tenga 9 dígitos
        if (timeStr.length() < 9) {
            return "000000000".substring(timeStr.length()) + timeStr;
        }
        return timeStr.substring(timeStr.length() - 9);
    }
    
    public static void main(String[] args) {
        
        System.out.println("--- PRUEBAS DE INSERCIÓN DE DATOS ---");
        System.out.println("Cédula de Trabajador a insertar (Única): " + NUEVA_CEDULA_TRABAJADOR);
        
        // 1. Cultivo (Soluciona el error 'nombre' cannot be null)
        System.out.println("\n--- Prueba CultivoService ---");
        probarInsertarCultivo(); 
        
        // 2. Almacén (Soluciona error de compilación: guardarAlmacenamiento -> guardarAlmacen)
        System.out.println("\n--- Prueba AlmacenamientoService ---");
        probarInsertarAlmacenamiento();
        
        // 3. Producción (Requiere Cultivo, y ProduccionDAO debe estar corregido)
        System.out.println("\n--- Prueba ProduccionService ---");
        probarInsertarProduccion(); 

        // 4. Producto Almacenado (Requiere Almacén y Producción)
        System.out.println("\n--- Prueba ProductoAlmacenadoService ---");
        probarInsertarProductoAlmacenado();

        // 5. Trabajador (Resuelve el error de 'Duplicate entry')
        System.out.println("\n--- Prueba TrabajadorService ---");
        probarInsertarTrabajador();

        // 6. Distribución (Requiere Trabajador, Producto Almacenado, Cultivo)
        System.out.println("\n--- Prueba DistribucionService ---");
        probarInsertarDistribucion(); 
    }

    private static void probarInsertarCultivo() {
        CultivoService service = new CultivoService();
        CultivoDTO nuevoCultivo = new CultivoDTO();
        
        // Rellenar campos obligatorios (nombre, areaSembrada, fecha)
        nuevoCultivo.setNombre("Maíz de Prueba"); 
        nuevoCultivo.setTipo("Grano");
        nuevoCultivo.setAreaSembrada(200);
        nuevoCultivo.setEstadoCrecimiento("Siembra");
        nuevoCultivo.setFechaSiembra(LocalDate.now().toString());
        // Asumiendo una fecha de cosecha futura
        nuevoCultivo.setFechaCosecha(LocalDate.now().plusMonths(4).toString());
        
        boolean exito = service.guardarCultivo(nuevoCultivo);
        System.out.println("Resultado de insertar Cultivo (Maíz de Prueba): " + (exito ? "ÉXITO" : "FALLO"));
    }
    
    private static void probarInsertarAlmacenamiento() {
        AlmacenamientoService service = new AlmacenamientoService();
        AlmacenamientoDTO nuevoAlmacenamiento = new AlmacenamientoDTO();
        
        // Rellenar campos obligatorios según AlmacenamientoDTO.java
        nuevoAlmacenamiento.setNombre("Bodega de Test (Temporal)");
        // El DTO usa double para capacidadKg
        nuevoAlmacenamiento.setCapacidadKg(1000.0); 
        
        // *** CORRECCIÓN CLAVE: El método es guardarAlmacen, no guardarAlmacenamiento ***
        boolean exito = service.guardarAlmacen(nuevoAlmacenamiento); 
        System.out.println("Resultado de insertar Almacenamiento (Bodega de Test): " + (exito ? "ÉXITO" : "FALLO"));
    }

    private static void probarInsertarProduccion() {
        ProduccionService service = new ProduccionService();
        ProduccionDTO nuevaProduccion = new ProduccionDTO();

        // Datos de Producción (asumiendo que Cultivo 1 existe del script SQL)
        nuevaProduccion.setIdCultivo(1); 
        nuevaProduccion.setFechaCosecha(LocalDate.now().toString());
        nuevaProduccion.setCantidadRecolectadaKg(150); 
        nuevaProduccion.setCalidad(100);
        nuevaProduccion.setDestino("Venta Local");
        
        boolean exito = service.guardarProduccion(nuevaProduccion);
        System.out.println("Resultado de insertar Producción para Cultivo 1: " + (exito ? "ÉXITO" : "FALLO"));
    }

    private static void probarInsertarProductoAlmacenado() {
        // Esta prueba asume que la inserción de Produccion y Almacenamiento fue exitosa.
        ProductoAlmacenadoService service = new ProductoAlmacenadoService();
        ProductoAlmacenadoDTO nuevoProducto = new ProductoAlmacenadoDTO();
        
        // Debe rellenar aquí los setters necesarios para ProductoAlmacenadoDTO
        
        boolean exito = service.guardarProductoAlmacenado(nuevoProducto);
        System.out.println("Resultado de insertar Producto Almacenado (Tomate): " + (exito ? "ÉXITO" : "FALLO"));
    }

    private static void probarInsertarTrabajador() {
        TrabajadorService service = new TrabajadorService();
        TrabajadorDTO nuevoTrabajador = new TrabajadorDTO();
        
        // Se usa la cédula generada dinámicamente
        nuevoTrabajador.setCedula(NUEVA_CEDULA_TRABAJADOR); 
        nuevoTrabajador.setNombre("Carlos Varela Test");
        // Correo único
        nuevoTrabajador.setCorreo("carlos_unica_test@" + NUEVA_CEDULA_TRABAJADOR + ".com"); 
        nuevoTrabajador.setTelefono("7777-7777");
        nuevoTrabajador.setPuesto("Supervisor");
        nuevoTrabajador.setHorario("L-V 7am-4pm");
        nuevoTrabajador.setSalario(600000.00);

        boolean exito = service.guardarTrabajador(nuevoTrabajador);
        System.out.println("Resultado de insertar Trabajador (Carlos Varela, Cédula: " + NUEVA_CEDULA_TRABAJADOR + "): " + (exito ? "ÉXITO" : "FALLO"));
    }

private static void probarInsertarDistribucion() {
        DistribucionService service = new DistribucionService();
        DistribucionDTO nuevaDistribucion = new DistribucionDTO(); 
        nuevaDistribucion.setIdProductoAlmacenado(1); 
        
        // *** CORRECCIÓN CLAVE: Usar la CÉDULA recién insertada para asegurar la FK ***
        nuevaDistribucion.setIdTrabajadorResponsable(NUEVA_CEDULA_TRABAJADOR); 
        
        nuevaDistribucion.setCantidad(25.0);
        nuevaDistribucion.setDestino("Mercado Nacional");
        nuevaDistribucion.setFechaDistribucion(LocalDate.now().toString());

        boolean exito = service.guardarDistribucion(nuevaDistribucion);
        System.out.println("Resultado de insertar Distribución: " + (exito ? "ÉXITO" : "FALLO"));
    }
}