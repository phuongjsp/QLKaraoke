package levantuan.quanlykaraoke.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "phieu_nhap_hang")
@Data
public class PhieuNhapHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "thoi_gian_nhap", nullable = false)
    private Date thoiGianNhap;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_nhap", referencedColumnName = "id")
    private NhanVien nhanVien;

    @Column(name = "ma_phieu_nhap", unique = true, nullable = false, length = 32)
    private String maPhieuNhap;

}
