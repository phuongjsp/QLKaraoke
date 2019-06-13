package levantuan.quanlykaraoke.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "hoa_don")
@Data
public class HoaDon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;


    @Column(name = "ma_hoa_don", unique = true, nullable = false, length = 32)
    private String maHoaDon;

    @Column(name = "tien_coc", nullable = false)
    private int tienCoc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "thoi_gian_tao", nullable = false)
    private Date thoiGianTao;

    @ManyToOne
    @JoinColumn(name = "phong", referencedColumnName = "id")
    private Phong phong;

    @ManyToOne
    @JoinColumn(name = "khach_hang", referencedColumnName = "id")
    private KhachHang khachHang;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gio_vao", nullable = false)
    private Date gioVao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gio_ra", nullable = false)
    private Date gioRa;

    @ManyToOne
    @JoinColumn(name = "nguoi_lap_hoa_don", referencedColumnName = "id")
    private NhanVien nhanVien;

}
