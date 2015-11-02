/**
 * Created by HARISH on 11/1/2015.
 */
public class ClassMapper {
    static final String  integer = "int";
    static final String floatName = "float";

    /**
     * this method gets the name of the class for which class object has to be created and creates
     * class object
     * @param input class Name
     * @return class Object
     */

    public static Class getClass(String input){
        if(input.equals(integer)){
            return int.class;
        }else if(input.equals(floatName)){
            return float.class;
        }else{

            try {
                return Class.forName(input);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Exception");
            }
        }
        return null;
    }

    /**
     * This method gets list of strings for which class objects has to created and creates class object
     * @param ip String array which contains list of strings for which class objects has to be created
     * @return class array
     */
    public static Class[] getClassArray(String ip[]){
        Class res[] = new Class[ip.length];
        for (int i = 0; i < ip.length; i++) {
            res[i] = getClass(ip[i]);
        }
        return res;

    }
}
