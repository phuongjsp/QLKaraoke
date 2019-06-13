package levantuan.quanlykaraoke.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phieu_nhan_phong")
@Data
public class PhieuNhanPhong implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @ManyToOne
    @JoinColumn(name = "ma_phieu_dat", referencedColumnName = "id")
    private DatPhong datPhong;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien", referencedColumnName = "id")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "khach_hang", referencedColumnName = "id")
    private KhachHang khachHang;

}
