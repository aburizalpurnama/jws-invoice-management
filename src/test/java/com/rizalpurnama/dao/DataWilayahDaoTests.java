package com.rizalpurnama.dao;

import com.rizalpurnama.entity.Kecamatan;
import com.rizalpurnama.entity.Kelurahan;
import com.rizalpurnama.entity.KotaKabupaten;
import com.rizalpurnama.entity.Provinsi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class DataWilayahDaoTests {

    @Autowired private ProvinsiDao provinsiDao;
    @Autowired private KotaKabupatenDao kotaKabupatenDao;
    @Autowired private KecamatanDao kecamatanDao;
    @Autowired private KelurahanDao kelurahanDao;

    @Test
    void testProvinsi() {
        Provinsi provinsi = provinsiDao.findById("61").get();
        Assertions.assertNotNull(provinsi);
        System.out.println(provinsi.getName());
    }

    @Test
    void testKotaKabupaten() {
        Provinsi provinsi = provinsiDao.findById("35").get();
        Iterable<KotaKabupaten> kokabList= kotaKabupatenDao.findByProvinsi(provinsi);
        Assertions.assertNotNull(kokabList);
        kokabList.forEach(kokab -> System.out.println(kokab.getId()+" : "+kokab.getName()));
    }

    @Test
    void testKecamatan() {
        KotaKabupaten kokab = kotaKabupatenDao.findById("35.74").get();
        Iterable<Kecamatan> itrKecamatan = kecamatanDao.findByKotaKabupaten(kokab);
        Assertions.assertNotNull(itrKecamatan);
        itrKecamatan.forEach(kecamatan -> System.out.println(kecamatan.getId()+" : "+ kecamatan.getName()));

    }

    @Test
    void testKelurahan() {
        Kecamatan kecamatan = kecamatanDao.findById("35.74.03").get();
        Iterable<Kelurahan> itrKelurahan = kelurahanDao.findByKecamatan(kecamatan);
        Assertions.assertNotNull(itrKelurahan);
        itrKelurahan.forEach(kelurahan -> System.out.println(kelurahan.getId()+" : "+ kelurahan.getName()));

    }
}
