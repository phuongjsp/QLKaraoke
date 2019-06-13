package levantuan.quanlykaraoke.dto;

import levantuan.quanlykaraoke.entities.Phong;
import levantuan.quanlykaraoke.entities.ChiTietVatTu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhongDTO {
    private long id;
    private String maPhong;
    private int tinhTrangPhong;
    private Long idLoaiPhong;
    private String tenLoaiPhong;

    public PhongDTO(Phong phong) {
        this.id = phong.getId();
        this.maPhong = phong.getMaPhong();
        this.tinhTrangPhong = phong.getTinhTrangPhong();
        this.idLoaiPhong = phong.getLoaiPhong().getId();
        this.tenLoaiPhong = phong.getLoaiPhong().getLoaiPhong();
    }

    List<ChiTietVatTu> vatTus;
}
