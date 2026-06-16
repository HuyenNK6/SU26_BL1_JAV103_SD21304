package demoFilter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "authFilter", value = {
        "/nhan-vien/*",
        "/quan-ly/*"
})
public class AuthFilter implements Filter {
    //implements methods
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //người dùng truy cập -> request-> filter -> controller/servlet
        /* FILTER
         - chưa đăng nhập -> Login
         - đã đăng nhập
                -> với tài khoản quản lý -> cho đi tiếp luôn (doFilter)
                -> với tài khoản nhân viên
                    -> URI chứa "nhan-vien" -> cho đi tiếp luôn (doFilter)
                    -> URI ko chứa "nhan-vien" (/quan-ly) -> ko cho truy cập
         */
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String taiKhoan = (String) session.getAttribute("taiKhoan");
        String chucVu = (String) session.getAttribute("chucVu");
        if (taiKhoan != null) {
            //có tài khoản
            if (chucVu.equals("quanLy")) {
                filterChain.doFilter(req, resp); //cho đi tiếp
            } else if (chucVu.equals("nhanVien")) {
                String uri = req.getRequestURI();
                //vai trò nhân viên -> chỉ được vào /nhan-vien/*
                if (uri.contains("nhan-vien")) {
                    filterChain.doFilter(req, resp); //cho đi tiếp
                } else {
                    //thông báo ko có quyền truy cập
                    req.getRequestDispatcher("/demo-filter/error403.jsp").forward(req, resp);
                }
            }
        } else {
            //chưa có tài khoản -> quay về đăng nhập
            resp.sendRedirect("/demo-filter/login");

        }
    }

    @Override
    public void destroy() {

    }
}
