package buoi2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//value = {"/login", "/ket-qua-login"}: Đây là một mảng các URL patterns mà servlet này sẽ xử lý.
// Khi người dùng truy cập vào một trong các URL này, servlet loginServlet sẽ được gọi để xử lý yêu cầu.
@WebServlet(name = "LoginServlet" , value = {
        "/login",
        "/ket-qua-login"
})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tenTruong = "FPT Polytechnic";
        req.setAttribute("tenTruong", tenTruong);
        req.getRequestDispatcher("/buoi2/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //nhận kết quả từ JSP về thông qua name
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        req.setAttribute("username",username);
        req.setAttribute("password",password);
        req.getRequestDispatcher("/buoi2/ket-qua-login.jsp").forward(req,resp);
    }
}
