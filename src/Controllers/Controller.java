package Controllers;

import DbAccess.DbControll;
import Encryption.Hasher;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    private static DbControll dbControll = new DbControll();

    public static String sendCode(String email) throws MessagingException {
        String code = Hasher.generateNewToken();
        EmailClient.sendAsHtml(email,
                "Code for confirmation",
                "<h2>" + code + "</p>");
        return Hasher.genHash(code);
    }

    private static boolean confirmEmail(String email) {
        try {
            String hashedCode = sendCode(email);
            System.out.println("Input code recived on specified email:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            long startTime = System.currentTimeMillis();
            String code = reader.readLine();
            if((System.currentTimeMillis() - startTime)/ 1000  % 60 > 120) {
                System.out.println("Code is expired");
                return false;
            }
            if(Hasher.checkHash(code, hashedCode)) {
                System.out.println("Email is confirmed!");
                return true;
            }
            else {
                System.out.println("Wrong code!");
                return false;
            }
        } catch (MessagingException | IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public static void regUser(String usn, String pswd, String email) {
        if(confirmEmail(email)) {
            dbControll.insertNewUser(usn, pswd, email);
        }
    }
}
