package levantuan.quanlykaraoke.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Column(name = "don_vi_doi_tac", length = 32)
    private String donViDoiTac;

    @Column(name = "dia_chi_doi_tac")
    private String diaChiDoiTac;

    @Column(name = "nguoi_dai_dien")
    private String nguoiDaiDien;

    @Column(name = "thong_tin_ndd")
    private String thongTinNDD;


    @Column(name = "type")
    private Integer type; // 0 : dich vu, 1: vat tu

    @Column(name = "tong_tien")
    private Integer tongTien;
}
