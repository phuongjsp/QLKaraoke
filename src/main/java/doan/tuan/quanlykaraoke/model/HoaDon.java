package doan.tuan.quanlykaraoke.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_dat_phong")
    private DatPhong datPhong;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hoaDon")
    private List<HoaDonSP> hoaDonSPs;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ngay_gio_tao")
    private Date ngayGioTao;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "sub_total")
    private String subTotal;

    @Column(name = "discount")
    private String discount;

    @Column(name = "total")
    private String total;

    @Column(name = "trang_thai", columnDefinition = "tinyint(1) default 1")
    private boolean trangThai;


}
