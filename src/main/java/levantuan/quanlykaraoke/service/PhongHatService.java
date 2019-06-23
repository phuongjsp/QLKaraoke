package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.dto.PhongDTO;
import levantuan.quanlykaraoke.dto.UpdatePhongDTO;
import levantuan.quanlykaraoke.entities.LoaiPhong;
import levantuan.quanlykaraoke.entities.Phong;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PhongHatService {

    List<LoaiPhong> getAllLoaiPhong();

    LoaiPhong updateLoaiPhong(LoaiPhong loaiPhong);

    LoaiPhong newLoaiPhong(LoaiPhong loaiPhong);

    boolean xoaLoaiPhong(Long id);

    Page<Phong> getALlPhong(Integer vip, Integer status, Integer page, Integer size);

    PhongDTO getById(Long idPhong);

    PhongDTO newPhong(PhongDTO phong);

    boolean updatePhong(List<UpdatePhongDTO> phong,Long type, Long idPhong);

    boolean deletePhong(Long idPhong);

    boolean datPhong(Long idPhong, Long idKhachHang, Long timeStamp, String user, int tienCoc);

    void donPhongHat(Long id);
}
