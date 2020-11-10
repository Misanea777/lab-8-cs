import Controllers.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        while(true) {
            String email, pswd, usn = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.println("email : ");
                email = reader.readLine();
                System.out.println("username : ");
                usn = reader.readLine();
                System.out.println("password : ");
                pswd = reader.readLine();
                Controller.regUser(usn, pswd, email);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        try {
//            Controller.sendCode("filipescu2772@gmail.com");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }
}
