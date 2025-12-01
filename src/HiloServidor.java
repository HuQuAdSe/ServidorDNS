import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class HiloServidor implements Runnable {
    private Socket socketCliente;
    private HashMap<String, ArrayList<Registro>> mapaFichero;

    public HiloServidor(Socket socketCliente, HashMap<String, ArrayList<Registro>> mapaFichero) {
        this.socketCliente = socketCliente;
        this.mapaFichero = mapaFichero;
    }

    @Override
    public void run() {
        try {

            boolean bandera = true;

            while (bandera) {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream())); // Lector del cliente
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true); // Escritor para enviar mensajes

                System.out.println("[ SERVIDOR '" + Thread.currentThread().getName() + "' ] Cliente conectado: " + socketCliente.getInetAddress());


            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
