package phongKham.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import phongKham.entity.BacSi;
import phongKham.entity.PhongKham;
import phongKham.repository.BacSiRepository;
import phongKham.repository.PhongKhamRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "bacSiServlet", value = {
        "/bac-si/hien-thi",//GET - done
        "/bac-si/view-update",//GET- btvn
        "/bac-si/detail",//GET-done
        "/bac-si/delete",//GET- btvn
        "/bac-si/add",//POST - done
        "/bac-si/update",//POST - btvn
        "/bac-si/paging"//GET
})
public class BacSiServlet extends HttpServlet {
    BacSiRepository bacSiRepo = new BacSiRepository();
    PhongKhamRepository phongKhamRepo = new PhongKhamRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThi(req, resp);
        } else if (uri.contains("view-update")) {
            this.viewUpdate(req, resp);
        } else if (uri.contains("detail")) {
            this.detail(req, resp);
        } else if (uri.contains("delete")) {
            this.delete(req, resp);
        } else if (uri.contains("paging")) {
            this.paging(req, resp);
        }
    }

    private void paging(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNumber = 1;
        int pageSize = 3;
        //check ko âm
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (req.getParameter("pageNumber") != null) {
            pageNumber = Integer.valueOf(req.getParameter("pageNumber"));
        }
        int totalRecords = bacSiRepo.getAll().size();
        int totalPage = (int) Math.ceil((double) totalRecords / pageSize);

        if (pageNumber > totalPage) {
            pageNumber = totalPage;
        }
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("listBacSi", bacSiRepo.paging(pageNumber, pageSize));
        req.setAttribute("listPhongKham", phongKhamRepo.getAll());
        req.getRequestDispatcher("/bacSi/hien-thi.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("add")) {
            this.add(req, resp);
        } else if (uri.contains("update")) {
            this.update(req, resp);
        }

    }

    private void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. lấy id của đối tượng cần update
        Integer id = Integer.valueOf(req.getParameter("id"));
        //2. tìm đối tượng cần update
        BacSi bacSi = bacSiRepo.getOne(id);
        //3. set thuộc tính
        req.setAttribute("bacSi", bacSi);
        req.setAttribute("listPhongKham", phongKhamRepo.getAll());//bổ sung
        //4. chuyển sang view-update.jsp
        req.getRequestDispatcher("/bacSi/view-update.jsp").forward(req, resp);
    }

    @SneakyThrows
    private void update(HttpServletRequest req, HttpServletResponse resp) {
        BacSi bacSi = new BacSi();
        //dùng để gán dữ liệu lấy được từ form về với thuộc tính của object JavaBean- BacSi
        //muốn gán được: name của parameter với tên thuộc tính phải giống nhau
        BeanUtils.populate(bacSi, req.getParameterMap());

        Integer idPhongKham = Integer.valueOf(req.getParameter("idPhongKham"));
        bacSi.setPhongKham(phongKhamRepo.getOne(idPhongKham));

        bacSiRepo.update(bacSi);
        resp.sendRedirect("/bac-si/hien-thi");
    }


    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1. lấy toàn bộ thông tin từ jsp gửi về
        String ten = req.getParameter("ten");
        String diaChi = req.getParameter("diaChi");
        BigDecimal luong = new BigDecimal(req.getParameter("luong"));
        //-> tìm đối tượng Phòng khám theo id
        Integer idPhongKham = Integer.valueOf(req.getParameter("idPhongKham"));
        PhongKham phongKham = phongKhamRepo.getOne(idPhongKham);
        //2. tạo đối tượng Bác sĩ
        BacSi bacSi = new BacSi(null, ten, diaChi, luong, phongKham);
        //3. thêm bác sĩ mới
        bacSiRepo.add(bacSi);
        //4. hiển thị danh sách mới
        resp.sendRedirect("/bac-si/hien-thi");//chuyển hướng sang URL khác
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1. lấy id của đối tượng cần xóa từ JSP
        Integer id = Integer.valueOf(req.getParameter("id"));
        //2. xóa khỏi DB
        bacSiRepo.delete(id);
        //3. điều hướng sang trang hiển thị
        resp.sendRedirect("/bac-si/hien-thi");
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. lấy id được gửi từ jsp
        String id = req.getParameter("id");
        //2. tìm đối tượng qua id
        BacSi bacSi = bacSiRepo.getOne(Integer.valueOf(id));
        //3. set thuộc tính cho đối tượng
        req.setAttribute("bacSi", bacSi);
        //4. gửi cả danh sách bacsi
        // List<BacSi> listBacSi= bacSiRepo.getAll();
        //List<PhongKham> listPhongKham= phongKhamRepo.getAll();//lấy thêm list PK
        req.setAttribute("listBacSi", bacSiRepo.getAll());
        req.setAttribute("listPhongKham", phongKhamRepo.getAll());//set thêm list PK
        req.getRequestDispatcher("/bacSi/hien-thi.jsp").forward(req, resp);
    }


    private void hienThi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. lấy danh sách từ bên repository -> csdl
        List<BacSi> listBacSi = bacSiRepo.getAll();
        List<PhongKham> listPhongKham = phongKhamRepo.getAll();//lấy thêm list PK
        //2. set thuộc tính  -> gửi sang jsp
        req.setAttribute("listBacSi", listBacSi);
        req.setAttribute("listPhongKham", listPhongKham);//set thêm list PK
        req.getRequestDispatcher("/bacSi/hien-thi.jsp").forward(req, resp);
    }


}
