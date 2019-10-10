package utils;

/**
 *
 * @author Jerson
 */
public class JeFile {

    private static final String[] EXTENSIONES_IMAGEN = {"jpg", "png","jpeg"};

    public static String validarImagen(String data) {
        return validarDataExtension(EXTENSIONES_IMAGEN, data);
    }

    public static String validarDataExtension(String extensiones[], String data) {
        String extension = obtenerExtension(data);
        if (extension == null)return null;
        return validarExtension(extensiones, extension)?extension:null;
    }

    public static boolean validarExtension(String[] extensiones, String extension) {
        for (String e : extensiones) {
            if (e.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    public static String obtenerExtension(String data) {
        int indiceP;
        if (data == null || data.isEmpty()) {
            return null;
        }
        if ((indiceP = data.lastIndexOf(".")) != -1) {
            if (indiceP + 1 == data.length()) {
                return null;
            }
            return data.substring(indiceP + 1);
        }
        return null;
    }

    public static void main(String[] args) {

        String dat = "efdmwifwe";

        System.out.println(obtenerExtension(dat));

    }

}
