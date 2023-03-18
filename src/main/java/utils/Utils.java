package utils;

import models.User;
import org.jsoup.Jsoup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static User authUser(HttpSession session) {
        return (User) session.getAttribute("auth");
    }


    public static boolean checkAuthUser(HttpSession session){
        return authUser(session) != null;
    }

    public static String buildHash(String str) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        md.update(str.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest);
    }

    public static String htmlToString(String html) {
        return Jsoup.parse(html).text();
    }
}
