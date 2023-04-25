package servlets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginServletTest {
    @Test
    public void doGet() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        new LoginServlet().doGet(request, response);
        verify(response).sendRedirect("/login.jsp");
    }

    @Test
    public void doPost() throws IOException {
        doPostBadAuthorization();
        doPostTrueAuthorizationAdmin();
        doPostTrueAuthorizationUser();
    }

    private void doPostBadAuthorization() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("login")).thenReturn("myLogin");
        Mockito.when(request.getParameter("password")).thenReturn("myPassword");

        new LoginServlet().doPost(request, response);

        verify(response).sendRedirect("/login.jsp");
    }
    private void doPostTrueAuthorizationAdmin() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("login")).thenReturn("admin");
        Mockito.when(request.getParameter("password")).thenReturn("admin");

        new LoginServlet().doPost(request, response);

        verify(response).sendRedirect("/admin");
    }

    private void doPostTrueAuthorizationUser() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("login")).thenReturn("user");
        Mockito.when(request.getParameter("password")).thenReturn("admin");

        new LoginServlet().doPost(request, response);

        verify(response).sendRedirect("/");
    }
}