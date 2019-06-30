package levantuan.quanlykaraoke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "chi_tiet_phieu_nhap_vat_tu")
@Data
public class ChiTietPhieuNhapVatTu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @ManyToOne
    @JoinColumn(name = "vat_tu", referencedColumnName = "id")
    private VatTu vatTu;

    @Column(name = "so_luong", nullable = false)
    private int soLuong;

    @Column(name = "don_gia", nullable = false)
    private int donGia;

    @ManyToOne
    @JoinColumn(name = "phieu_nhap_hang", referencedColumnName = "id")
    @JsonIgnore
    private PhieuNhapHang phieuNhapHang;
}
