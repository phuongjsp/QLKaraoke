package levantuan.quanlykaraoke.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dat_phong")
@Data
public class DatPhong implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    private long id;

    @Column(name = "ma_phieu_dat", unique = true, nullable = false, length = 32)
    private String maPhieuDat;

    @ManyToOne
    @JoinColumn(name = "ma_khach_hang", referencedColumnName = "id")
    private KhachHang khachHang;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngay_gio_dat", nullable = false)
    private Date ngayGioDat;

    @Column(name = "tien_coc", nullable = false)
    private int tienCoc;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien", referencedColumnName = "id")
    private NhanVien nhanVien;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngay_tao_phieu_dat", nullable = false)
    private Date ngayTaoPhieuDat;

    @ManyToOne
    @JoinColumn(name = "phong", referencedColumnName = "id")
    private Phong phong;
}

