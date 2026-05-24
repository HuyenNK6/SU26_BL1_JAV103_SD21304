package phongKham.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import phongKham.entity.BacSi;
import phongKham.repository.BacSiRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "bacSiServlet", value = {
        "/bac-si/hien-thi",//GET - done
        "/bac-si/view-update",//GET- btvn
        "/bac-si/detail",//GET-done
        "/bac-si/delete",//GET- btvn
        "/bac-si/add",//POST - done
        "/bac-si/update"//POST - btvn
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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("add")){
            this.add(req, resp);
        }else if(uri.contains("update")){
            this.update(req, resp);
        }

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1. lấy toàn bộ thông tin từ jsp gửi về
        String ten = req.getParameter("ten");
        String diaChi = req.getParameter("diaChi");
        BigDecimal luong = new BigDecimal(req.getParameter("luong"));
        Integer idPhongKham = Integer.valueOf(req.getParameter("idPhongKham"));
        //2. tạo đối tượng Bác sĩ
        BacSi bacSi = new BacSi(null, ten, diaChi, luong,idPhongKham);
        //3. thêm bác sĩ mới
        bacSiRepo.add(bacSi);
        //4. hiển thị danh sách mới
        resp.sendRedirect("/bac-si/hien-thi");//chuyển hướng sang URL khác
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. lấy id được gửi từ jsp
        String id = req.getParameter("id");
        //2. tìm đối tượng qua id
        BacSi bacSi= bacSiRepo.getOne(Integer.valueOf(id));
        //3. set thuộc tính cho đối tượng
        req.setAttribute("bacSi",bacSi);
        //4. gửi cả danh sách bacsi
        List<BacSi> listBacSi= bacSiRepo.getAll();
        req.setAttribute("listBacSi",bacSiRepo.getAll());
        req.getRequestDispatcher("/bacSi/hien-thi.jsp").forward(req,resp);
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


}
