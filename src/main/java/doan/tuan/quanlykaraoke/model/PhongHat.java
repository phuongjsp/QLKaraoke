package doan.tuan.quanlykaraoke.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phong_hat")
@Data
public class PhongHat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "ten_phong")
    private String tenphong;
    @ManyToOne()
    @JoinColumn(name = "id_loai_phong")
    private LoaiPhongHat loaiPhongHat;
    @Column(name = "trang_thai",nullable = false, columnDefinition = "bit default 0")
    private boolean trangThai;
}
