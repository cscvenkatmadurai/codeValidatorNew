import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by HARISH on 11/1/2015.
 */
public class Stack {
    private int arr[];
    private int curr;
    private  int  size;

    public Stack(int size) {
        this.size = size;
        arr = new int[size];
        curr  = -1;

    }
    public void push(int no){
        if(curr < size ){
            arr[++curr] = no;
        }else {
            System.out.println("Stack is full");
        }
    }
    public int pop(){
        if(curr < 0){
            System.out.println("Stack is empty");
            return -1;
        }else{
            return arr[curr--];

        }
    }
    public int peek(){
        if(curr < 0){
            System.out.println("Stack is empty");
            return -1;
        }else {
            return arr[curr];

        }

    }
    public  static Stack downCast(Object o){
        return (Stack)o;
    }

    public static void main(String[] args) throws Exception{
     Class c = Class.forName("Stack");
        Constructor con = c.getConstructor(new Class[]{int.class});
        Object a[] = new Object[]{new Integer(10)};
        Stack s = (Stack) con.newInstance(a);
        s.push(234);
        Method m = c.getMethod("push", new Class[]{int.class});
         m.invoke(s,new Object[]{new Integer(45)}) ;
        m = c.getMethod("peek", new Class[]{});
        System.out.println(m.invoke(s, new Object[]{}) );



    }



}
