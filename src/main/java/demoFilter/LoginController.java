package demoFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "loginController", value = "/demo-filter/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("login")) {
            req.getRequestDispatcher("/demo-filter/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //username: HuyenNK6 và password: 123 và role : Quản lý
        //username: PH123 và password: 456 và role: Nhân viên
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (
                username.equals("HuyenNK6") && password.equals("123")
                        || username.equals("PH123") && password.equals("456")
        ) {
            HttpSession session = req.getSession();//lấy dữ liệu trong phiên làm việc
            session.setAttribute("taiKhoan", username);
            if (username.equals("HuyenNK6")) {
                session.setAttribute("chucVu", "quanLy");
                resp.sendRedirect("/quan-ly");
            } else {
                session.setAttribute("chucVu", "nhanVien");
                resp.sendRedirect("/nhan-vien");
            }
        } else {
            req.setAttribute("messageLogin", "Sai thông tin đăng nhập!!!");
            req.getRequestDispatcher("/demo-filter/login.jsp").forward(req, resp);
        }

    }
}
