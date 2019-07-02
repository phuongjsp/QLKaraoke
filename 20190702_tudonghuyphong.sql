SET GLOBAL event_scheduler = ON;

CREATE EVENT IF NOT EXISTS huy_phong_qua_han
ON SCHEDULE EVERY 5 MINUTE
    STARTS CURRENT_TIMESTAMP
DO
    BEGIN
        CREATE TEMPORARY TABLE luu_id (hd int, ph int, kh int);
        INSERT INTO luu_id (hd, ph, kh) (select  a.id hd, p.id, kh.id from hoa_don a
                                                   left join khach_hang kh on a.khach_hang = kh.id
                                                   left join phong p on a.phong = p.id
                                                   where
                                                       (
                                                         (DATE_FORMAT(NOW(), '%Y%m%d%h%i') - DATE_FORMAT(thoi_gian_tao, '%Y%m%d%h%i') > 20 and kh.vip_count < 10) -- Bronze
                                                   or    (DATE_FORMAT(NOW(), '%Y%m%d%h%i') - DATE_FORMAT(thoi_gian_tao, '%Y%m%d%h%i') > 50 and kh.vip_count < 30) -- Silver
                                                   or    (DATE_FORMAT(NOW(), '%Y%m%d%h%i') - DATE_FORMAT(thoi_gian_tao, '%Y%m%d%h%i') > 90 and kh.vip_count < 60) -- Gold
                                                   or    (DATE_FORMAT(NOW(), '%Y%m%d%h%i') - DATE_FORMAT(thoi_gian_tao, '%Y%m%d%h%i') > 120 and kh.vip_count < 100) -- Platinum
                                                   or    (kh.vip_count >= 100 and DATE_FORMAT(NOW(), '%Y%m%d%h%i') - DATE_FORMAT(thoi_gian_tao, '%Y%m%d%h%i') >= kh.vip_count + 20 and  kh.vip_count <= 160) -- Diamond 160--
                                                   or    (kh.vip_count > 160 and DATE_FORMAT(NOW(), '%Y%m%d%h%i') - DATE_FORMAT(thoi_gian_tao, '%Y%m%d%h%i') > 180) -- Diamond 160++
                                                           )
                                                   and p.tinh_trang_phong = 2
                                                   and a.tinh_trang_phong = 1) ;
    update hoa_don set tinh_trang_phong = 4 where id in (select hd from luu_id);
    update khach_hang set vip_count = round(vip_count/10) where id in (select kh from luu_id);
    update phong set tinh_trang_phong = 1 where id in (select ph from luu_id);
    drop table luu_id;
end;
