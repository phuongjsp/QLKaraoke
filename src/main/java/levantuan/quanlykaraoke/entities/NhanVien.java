package levantuan.quanlykaraoke.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "nhan_vien")
@Data
public class NhanVien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @Column(name = "ma_nhan_vien", unique= true, nullable = false, length = 32)
    private String username;

    @Column(name = "so_dien_thoai",length = 32)
    private String soDienThoai;

    @Column(name = "dia_chi",length = 32)
    private String diaChi;


    @Column(name = "ten_nhan_vien", length = 32)
    private String tenNhanVien;

    @Column(name = "cmnd", length = 32)
    private String Cmnd;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "gioi_tinh", nullable = false,  columnDefinition = "bit default 0")
    private boolean gioiTinh;

    @Column(name = "tinh_trang", nullable = false,  columnDefinition = "bit default 0")
    private boolean tinhTrang;

}
