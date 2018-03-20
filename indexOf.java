import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String a = s.next();
        String b = s.next();
        ArrayList<Integer> t = new ArrayList<Integer>();
        int in = a.indexOf(b);
        while(in != -1) {
            t.add(in);
            in = a.indexOf(b, in+b.length());
        }
        System.out.println(t.size());
    }
}