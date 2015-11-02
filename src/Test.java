import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by HARISH on 11/1/2015.
 */
public class Test {
    private String ipFile,opFile;

    private Class classs;
    private BufferedReader reader;
    private static final String method = "method",field = "field";
   // private PrintWriter out;

    /**
     *
     * @param ipFile Name of the file from which input is feed
     * @param opFile Name of the file to which op is sent
     */
    public Test(String ipFile, String opFile) {
        this.ipFile = ipFile;
        this.opFile = opFile;

        try {
            //reader read the data from input file line by line
            reader = new BufferedReader(new FileReader(ipFile));
            //1st line contains class name of the class which students works with
            String className = reader.readLine();
            classs = Class.forName(className);
       //     out = new PrintWriter(opFile);

        }catch (IOException | ClassNotFoundException e ){
            System.out.println(e.toString());
        }
    }

    public String getIpFile() {
        return ipFile;
    }

    public void setIpFile(String ipFile) {
        this.ipFile = ipFile;
    }

    public String getOpFile() {
        return opFile;
    }

    public void setOpFile(String opFile) {
        this.opFile = opFile;
    }

    /**
     *This method creates object for the class which student works with
     *
     * @return object for the class student works
     */
    private Object createObject(){
        try {
            //gets the data type of parameters used in  constuctor
            String dataType[] = reader.readLine().split(" ");
            //gets the value of parameters used in constructor
            String dataValue[] = reader.readLine().split(" ");
            //creates Class objects for parameters of constructor
            Class dataTypes[] = ClassMapper.getClassArray(dataType);
            //creates parameters for parameters used in constructor
            Object dataValus[] = ObjectMapper.getObjectArray(dataType, dataValue);
            //getConstrucor method creates an Constructor with the given parameters.newInstance creates an object with dataValues as intial value of attributes
            return classs.getConstructor(dataTypes).newInstance(dataValus);


        }catch (IOException | NoSuchMethodException | InstantiationException |IllegalAccessException | InvocationTargetException e){
            System.out.println("in create ob"+e.getMessage());
        }
        return null;

    }
    private void invokeMethod(Object ob){
        String methodName;//method to be invoked
        int noOfParamForTheMethodToBeInvoked;//no of parameter of the metod to be invoked
        String[] dataTypeOfParametersOfTheMethodToBeInvolved;
        String[] valueOfParametersOfTheMethodToBeInvoked;
        Class[] dataClassType = {};//class array for parameters of the method to be invoked
        Object[] dataObjectType = {};//Object array for the parameters of the method to be invoked
        Method m;//Method object for the method to be invoked

        try {
            String ip[] = reader.readLine().split(" ");
            methodName = ip[0];//reads the name of the method
            noOfParamForTheMethodToBeInvoked = Integer.parseInt(ip[1]);//reads the no of parameter of the method
            if(noOfParamForTheMethodToBeInvoked > 0){
                dataTypeOfParametersOfTheMethodToBeInvolved = reader.readLine().split(" ");//reads the data type of parameters
                valueOfParametersOfTheMethodToBeInvoked = reader.readLine().split(" "); // reads the value of parameter
                dataClassType = ClassMapper.getClassArray(dataTypeOfParametersOfTheMethodToBeInvolved);//creates the array of Classes for the parameter
                dataObjectType = ObjectMapper.getObjectArray(dataTypeOfParametersOfTheMethodToBeInvolved, valueOfParametersOfTheMethodToBeInvoked); // creates object for parameters
            }
            m = classs.getMethod(methodName, dataClassType);//create method object
            System.out.println(m.invoke(ob,dataObjectType)+" "+m.getName());//invoke the method print the output of method

        }catch (IOException | NoSuchMethodException | IllegalAccessException |InvocationTargetException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * prints the attribute value for requested object
     * @param ob
     */
    private void getField(Object ob){
        try {
            //get the field name for which value hast to be printed
            String fieldName = reader.readLine();
            //get the field
            Field f = classs.getDeclaredField(fieldName);
            //setAccessible is set to true inorder to access a private attribute
            f.setAccessible(true);
            //prints the attribute value
            System.out.println( f.get(ob));
        }catch (IOException  | NoSuchFieldException | IllegalAccessException e){
            System.out.println(e.toString() +"   exception");
        }


    }
    private void process(){
        Object ob = createObject();
        String read;
        try {
            while ((read = reader.readLine()) != null) {
                if(read.equals(method)){
                    invokeMethod(ob);
                }else if(read.equals(field)){
                    getField(ob);
                }else{
                    System.out.println(read+" some prob");
                }

            }
        }catch (IOException e){
            System.out.println("IOException");

        }
    }


    public static void main(String[] args) {
        Test t = new Test("F:\\CodeValidatorNew\\src\\input","");
        t.process();


    }

}
