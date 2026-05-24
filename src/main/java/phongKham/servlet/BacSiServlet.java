package phongKham.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import phongKham.entity.BacSi;
import phongKham.repository.BacSiRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "bacSiServlet", value = {
        "/bac-si/hien-thi",//GET
        "/bac-si/view-update",//GET
        "/bac-si/detail",//GET
        "/bac-si/delete",//GET
        "/bac-si/add",//POST
        "/bac-si/update"//POST
})
public class BacSiServlet extends HttpServlet {
    BacSiRepository bacSiRepo= new BacSiRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("hien-thi")){
            this.hienThi(req, resp);
        }else if(uri.contains("view-update")){
            this.viewUpdate(req, resp);
        }else if(uri.contains("detail")){
            this.detail(req, resp);
        }else if(uri.contains("delete")){
            this.delete(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void viewUpdate(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void hienThi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. lấy danh sách từ bên repository -> csdl
        List<BacSi> listBacSi= bacSiRepo.getAll();
        //2. set thuộc tính  -> gửi sang jsp
        req.setAttribute("listBacSi",listBacSi);
        req.getRequestDispatcher("/bacSi/hien-thi.jsp").forward(req,resp);
        //BTVN: hiển thị toàn bộ danh sách sang bên hien-thi.jsp
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
