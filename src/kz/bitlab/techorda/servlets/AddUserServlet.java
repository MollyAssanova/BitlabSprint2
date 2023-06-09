package kz.bitlab.techorda.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.bitlab.techorda.db.DBConnection;
import kz.bitlab.techorda.db.User;

import java.io.IOException;


@WebServlet(value = "/register")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "/register?emailerror";

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String rePassword = request.getParameter("re_password");

        String fullName = request.getParameter("full_name");

        User checkUser = DBConnection.getUser(email);

        if(checkUser==null){ //Проверяем, если пользователя с таким email не существует

            redirect = "/register?passworderror";

            if(password.equals(rePassword)){

                User user = new User();

                user.setEmail(email);

                user.setPassword(password);

                user.setFullName(fullName);

                if(DBConnection.addUser(user)){

                    redirect = "/register?success";

                }

            }

        }

        response.sendRedirect(redirect);

    }

}
