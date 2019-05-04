package doan.tuan.quanlykaraoke.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "loai_phong_hat")
@Data
public class LoaiPhongHat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "ten_loai_phong")
    private String tenLoaiPhong;

    @Column(name = "gia_tien")
    private long giaTien;
}
