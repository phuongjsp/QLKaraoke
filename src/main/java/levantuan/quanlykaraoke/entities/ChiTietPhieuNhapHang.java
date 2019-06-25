package levantuan.quanlykaraoke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chi_tiet_phieu_nhap_hang")
@Data
public class ChiTietPhieuNhapHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;


    @ManyToOne
    @JoinColumn(name = "ten_dich_vu", referencedColumnName = "id")
    private DichVu dichVu;

    @Column(name = "so_luong", nullable = false)
    private int soLuong;

    @Column(name = "don_gia", nullable = false)
    private int donGia;

    @ManyToOne
    @JoinColumn(name = "phieu_nhap_hang", referencedColumnName = "id")
    @JsonIgnore
    private PhieuNhapHang phieuNhapHang;

}
