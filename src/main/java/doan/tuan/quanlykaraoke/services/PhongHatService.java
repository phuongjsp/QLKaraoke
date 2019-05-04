package doan.tuan.quanlykaraoke.services;

import doan.tuan.quanlykaraoke.model.LoaiPhongHat;
import doan.tuan.quanlykaraoke.model.PhongHat;

import java.util.List;

public interface PhongHatService {

    List<PhongHat> getAll();

    boolean capNhatTrangThaiPhong(String tenPhong,boolean trangThai);
List<LoaiPhongHat> getAllLoaiPhongHat();

    boolean themPhongHat(String tenPhong,String tenLoaiPhong);
    boolean xoaPhongHat(String tenPhong);
    boolean capNhatPhongHat(String tenPhongCu,String tenPhongMoi,String tenLoaiPhong);

    boolean themLoaiPhongHat(String tenLoaiPhong,long gia);
    boolean xoaLoaiPhongHat(String tenLoaiPhong);
    boolean capNhatLoaiPhongHat(String tenLoaiPhongCu,String tenLoaiPhongMoi,long gia);

}
