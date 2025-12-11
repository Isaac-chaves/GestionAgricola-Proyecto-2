package Controlador;

// 1. IMPORTAR LA INTERFAZ CORRECTA
import Vista.TablaObserver; 
import java.util.ArrayList;
import java.util.List;

public class ObservadorManager {

    private static ObservadorManager instancia;
    // 2. DECLARAR CORRECTAMENTE LA LISTA
    private final List<TablaObserver> observadores; 

    private ObservadorManager() {
        // 3. INICIALIZAR LA LISTA EN EL CONSTRUCTOR
        observadores = new ArrayList<>(); 
    }

    public static ObservadorManager getInstancia() {
        if (instancia == null) {
            instancia = new ObservadorManager();
        }
        return instancia;
    }

    public void registrarObservador(TablaObserver observer) {
        if (!observadores.contains(observer)) {
            observadores.add(observer);
        }
    }

    public void eliminarObservador(TablaObserver observer) {
        observadores.remove(observer);
    }

    public void notificarCambio(String tipoEntidad) {
        // Esta es la línea que usted ve "roja". Es correcta si las 3 anteriores se cumplen.
        for (TablaObserver observer : observadores) {
            observer.actualizarTabla(tipoEntidad);
        }
    }
}