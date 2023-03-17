package utils;

import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
    public static boolean checkAuthUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        return user != null;
    }
}
