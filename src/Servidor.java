import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Servidor {
    public static void main(String[] args) {
        HashMap<String, ArrayList<Registro>> mapaFichero = mapaFichero();
        int puerto = 5000; // Puerto donde escucha el servidor

        try (ServerSocket serverSocket = new ServerSocket(puerto)) { // Crea el servidor
            System.out.println("[SERVIDOR] Escuchando en el puerto " + puerto + "..."); // Mensaje informativo

            while (true) { // Acepta clientes sin detenerse
                Socket socketCliente = serverSocket.accept(); // Espera a un cliente
                System.out.println("[SERVIDOR] Cliente conectado: " + socketCliente.getInetAddress()); // Imprime informacion

                Thread hiloCliente = new Thread(new HiloServidor(socketCliente, mapaFichero)); // Hilo para atender al cliente
                hiloCliente.start(); // Inicia el hilo
            }

        } catch (IOException e) { // Si hay error en el servidor
            System.out.println("Error en el servidor: " + e.getMessage()); // Muestra mensaje
        }

    }

    public static HashMap<String, ArrayList<Registro>> mapaFichero() {
        try {
            HashMap<String, ArrayList<Registro>> mapaFichero = new HashMap<>();
            BufferedReader lector = new BufferedReader(new FileReader("src/Fichero/DominiosConRegistros"));
            String line = "";
            while ((line = lector.readLine()) != null) {
                String[] parte = line.split(" ");
                String dominio = parte[0];
                Registro registro = new Registro(parte[1], parte[2]);

                if (!mapaFichero.containsKey(dominio)) {
                    ArrayList<Registro> listaRegistros = new ArrayList<>();
                    listaRegistros.add(registro);
                    mapaFichero.put(dominio, listaRegistros);
                } else {
                    mapaFichero.get(dominio).add(registro);
                }
            }

            return mapaFichero;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}