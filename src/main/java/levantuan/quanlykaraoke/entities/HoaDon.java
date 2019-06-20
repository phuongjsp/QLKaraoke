package levantuan.quanlykaraoke.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hoa_don")
@Data
public class HoaDon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;


    @Column(name = "ma_hoa_don", unique = true, length = 32)
    private String maHoaDon;

    @Column(name = "tien_coc")
    private int tienCoc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "thoi_gian_tao")
    private Date thoiGianTao;

    @ManyToOne
    @JoinColumn(name = "phong", referencedColumnName = "id")
    private Phong phong;

    @ManyToOne
    @JoinColumn(name = "khach_hang", referencedColumnName = "id")
    private KhachHang khachHang;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gio_vao")
    private Date gioVao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "gio_ra")
    private Date gioRa;

    @ManyToOne
    @JoinColumn(name = "nguoi_lap_hoa_don")
    private NhanVien nhanVien;

    @Column(name = "tinh_trang_phong")
    private Integer tinhTrangHoaDon; // 1. đang đặt phòng, 2 đã vào phòng, 3.đã trả phòng, 4.đã hủy hóa đơn

}
