package demoAjax;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ajaxController", value = "/api/ajax/giang-vien")
//AJAX là kỹ thuật cho phép trang web gửi và nhận dữ liệu với server mà ko cần load lại trang
//vd: ko dùng ajax -> người dùng thao tác -> request lên server -> server trả về 1 trang html -> load trang
//có dùng ajax -> người dùng thao tác -> JavaScript gửi request ngầm lên server -> server trả về dữ liệu JSON -> JS hiển thị (ko load trang)
public class AjaxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GiangVien gv = new GiangVien("HuyenNK6", "Khanh Huyen", 18, "UDPM");
        //chuyển Object gv sang String JSON
        Gson gson = new Gson();
        String data = gson.toJson(gv);
        //chuyển đổi kiểu dữ liệu của response
        resp.setContentType("application/json");
        //hiển thị dữ liệu lên màn hình
        PrintWriter pw = resp.getWriter();
        pw.println(data);
        pw.flush();
    }
}
