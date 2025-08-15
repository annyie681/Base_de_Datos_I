import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TreeMap; //HashMap es sin orden, TreeMap es con orden :)
import java.util.Map;

public class leerArchivo {
    public static void main(String[] args) {
        String documento = "https://raw.githubusercontent.com/annyie681/Base_de_Datos_I/main/codigos_postales_hmo.csv";
        Map<Integer, Integer> codigosPostalesUnicos = new TreeMap<>(); // creo un Map que es una estructura de datos ue
                                                                       // almacena pares de clave-valor
        try {
            URL direccionURL = new URL(documento); // Convierto el String "documento" a una dirección URL
            BufferedReader leerDocumento = new BufferedReader(new InputStreamReader(direccionURL.openStream()));
            // direccionURL.openStream() abre una conexión a la URL que pongo y devuelve un
            // flujo de entrada (InputStream) que permite leer los bytes que vienen de la
            // URL.
            // new InputStreamReader(...) convierte bytes en texto que puedo leer en java
            // new BufferedReader(...) envuelve el InputStreamReader(...) en un buffer, lo
            // que mejora el rendimiento cuando hay muchas líneas

            leerDocumento.readLine(); // leo la linea completa, la devuelvo como string y paso a la siguiente

            String linea; // Creo la linea para que pueda ir leyendo una por una las lineas

            while ((linea = leerDocumento.readLine()) != null) {
                String[] datosDocumento = linea.split(","); // separa los datos de las lineas cuando hay comas

                if (datosDocumento.length >= 2) {
                    String CP = datosDocumento[0].trim(); // los CP serán el 1er dato de cada línea
                    // .trim elimina espacios
                    String asentamientos = datosDocumento[1].trim(); // los asentamientos serán el 2do dato
                    int codigoPostal = Integer.parseInt(CP); // convierto los codigos postales a int

                    codigosPostalesUnicos.put(codigoPostal, codigosPostalesUnicos.getOrDefault(codigoPostal, 0) + 1);
                    // getOrDefault(codigoPostal, 0) busca el valor actual (cuenta) asociado a
                    // codigoPostal.
                    // Si no existe (es la primera vez que aparece), devuelve 0.
                    // Luego le suma 1 para indicar que se encontró otra vez ese código postal.
                    // Finalmente con put actualiza el conteo en el mapa para ese código postal.
                }
                // El if evita un error asegurándose de que la línea tenga al menos dos columnas
                // antes de acceder a ellas
            }

            for (Map.Entry<Integer, Integer> entrada : codigosPostalesUnicos.entrySet()) {
                System.out.println("C. P. = " + entrada.getKey() + "    Núm. Asentamientos = " + entrada.getValue());
            }
            // entry representa una entrada individual del map
            // .entrySet() devuelve un conjunto de todas las entradas del mapa (da acceso a
            // todos los pares clave-valor del Map)
            // entry.getKey() devuelve la clave de la entrada actual (el codigo postal)
            // entry.getValue() devuelve el valor asociado a esa clave (cuántas veces
            // aparece el cp)

        } catch (Exception e) {
            System.out.println("Error al leer el archivo.");
        }
    }
}