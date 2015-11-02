/**
 * Created by HARISH on 11/2/2015.
 */
public class ObjectMapper {
    static final String  integer = "int";
    static final String floatName = "float";

    /**
     * Given object type and value object is created
     * @param dataType  data type of the object
     * @param value value of the object
     * @return Object created
     */
    public static Object getObject(String dataType,String value){
        if(dataType.equals(integer)){
            return Integer.parseInt(value);
        }else if(dataType.equals(floatName)){
            return Float.parseFloat(value);
        }
        return null;

    }

    /**
     * Given array of data type and value of object to be created.An object array is created
     * @param dataType Array of Strings which contains data types for which object has to be created
     * @param value Array of strings which contains value of the objects
     * @return Object arrray
     */
    public static Object[] getObjectArray(String dataType[],String value[]){
        Object[] res = new Object[dataType.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = getObject(dataType[i],value[i]);
        }
        return res;
    }
}
