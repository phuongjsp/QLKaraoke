package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.entities.DichVu;
import org.springframework.data.domain.Page;

import java.util.List;
public interface DichVuService {
//    List<DichVu> getAllDichVu();
//
//    DichVu updateDichVu(DichVu dichVu);
//
//    DichVu newDichVu(DichVu dichVu);
//
//    boolean xoaDichVu(Long id);

    Page<DichVu> getAllDichVu(Integer page, Integer size);

    DichVu updateDichVu(DichVu dichVu);

    DichVu newDichVu(DichVu dichVu);

    boolean xoaDichVu(Long id);
}

