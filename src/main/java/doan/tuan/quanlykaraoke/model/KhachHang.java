package doan.tuan.quanlykaraoke.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "khach_hang")
@Data
public class KhachHang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "ho_ten")
    private String hoTen;
    @Column(name = "so_dien_thoai", length = 15)
    @Size(min = 10, max = 12)
    private String sdt;
    @Column(name = "dia_chi")
    private String diaChi;
}
