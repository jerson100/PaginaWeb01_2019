package utils;

/**
 *
 * @author Jerson
 */
public class Validator {
    /**
     * Valida si uan cadena de texto contiene solo letras y espacios
     * @param txt
     * @return boolean
     */
    public static boolean validateLetter(String txt){
        return txt.trim().matches("^[a-z|A-Z|áéíóúÁÉÍÓÚñÑ]+$");
    }
    
    /**
     * Valida si una cadena de texto es un número
     * @param txt
     * @return boolean <br>
     * <ul>
     *    <li>True : Es un número</li>
     *    <li>False : Si no es un número</li>
     * </ul>
     */
    public static boolean validateNumber(String txt){
        return txt.trim().matches("^[0-9]+$");
    }
    
    public static boolean validateEmail(String txt){
        //mejorar para validar que no inicie con punto o cualquier otro 
        //caracter que no sea una letra
        return txt.matches("^\\w+@[a-zA-Z]+.[a-z]+");
    }
    
    /*
      Que contenga un número, una mayúzcula y una minúscula
    */
    public static boolean validatePassword(String pass){
        return pass.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[A-Za-z0-9]{8,20}");
    }
    
    public static void main(String[] args) {
        if(validateEmail("juamkoo@gmail.com")){
            System.out.println("dd");
        }
    }
    
}
