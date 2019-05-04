package doan.tuan.quanlykaraoke.model;

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
    @Column(name = "id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "id_phong_hat")
    private PhongHat phongHat;

    @Column(name = "thoi_gian_vao")
    private Date thoiGianVao;

    @Column(name = "thoi_gian_ra")
    private Date thoiGianRa;

    @Column(name = "tienPhong")
    private long tienPhong;
}
