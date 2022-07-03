package academy.prog;

import jakarta.servlet.http.*;

import java.io.IOException;

//Добавить в 1-й пункт валидацию ввода на стороне сервера: если пользователь не ввел логин, пароль или возвраст или ввел данные неправильно,
// показываем ошибку на странице и просим ввести данные повторно. Ограничение на поле с паролем: минимум 10 символов.
//3) Добавить на стороне сервера проверку сложности введеннного пароля

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "Admin_1234";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        boolean pass_match = false;
        int age = 0;

        if (login.isEmpty() && password.isEmpty() && request.getParameter("age").isEmpty()) {
            session.setAttribute("template", "Please enter your credentials");
            response.sendRedirect("index.jsp");
            return;
        } else if (login.isEmpty()) {
            session.setAttribute("template", "Login_field is empty. Please enter login.");
            response.sendRedirect("index.jsp");
            return;
        } else if (password.isEmpty()) {
            session.setAttribute("template", "Password_field is empty. Please enter password.");
            response.sendRedirect("index.jsp");
            return;
        } else if (request.getParameter("age").isEmpty()) {
            session.setAttribute("template", "Age_field is empty. Please enter your age.");
            response.sendRedirect("index.jsp");
            return;
        } else try {
            age = Integer.parseInt(request.getParameter("age"));
        } catch (Exception e) {
            session.setAttribute("template", "The inputted symbols for the age are wrong, please input numbers");
            response.sendRedirect("index.jsp");
            return;
        }
        if (!password.matches("\\A(?=\\S*?[0-9@#$%^&+=])(?=\\S*?[a-z])(?=\\S*?[A-Z])\\S{10,}\\z")) {
            session.setAttribute("template", "Not correct Password. It must be at least 10 characters long <br>" +
                    " contain at least one uppercase and lowercase letter <br>" +
                    " contain no spaces");
            response.sendRedirect("index.jsp");
            return;
        } else pass_match = true;
        if (LOGIN.equals(login) && PASS.equals(password) && (age >= 18) && pass_match) {
            session = request.getSession(true);
            session.setAttribute("user_login", login);
            response.sendRedirect("index.jsp");
        } else if (LOGIN.equals(login) && PASS.equals(password) && (age < 18)) {
            session.setAttribute("template", "Age must be not less than 18");
            response.sendRedirect("index.jsp");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");
        session.removeAttribute("template");

        response.sendRedirect("index.jsp");
    }
}
