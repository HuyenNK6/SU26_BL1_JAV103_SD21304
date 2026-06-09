package demoAjax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data //getter, setter, toString,...
public class GiangVien {
    private String ma;
    private String ten;
    private Integer tuoi;
    private String boMon;
}
