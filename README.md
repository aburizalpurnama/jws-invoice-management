# APLIKASI INVOICE MANAGEMENT MENGGUNAKAN SPRING FRAMEWORK #

Aplikasi ini digunakan untuk mengelola invoice dan menyambung dengan berbagai macam metode pembayaran masa kini, 
antara lain :

    * Virtual Account Bank
        - Bank BNI
        - Bank CIMB
        - Bank BSI

    * e-wallet
        - OVO
        - Go-Pay

    * QR payment
        - QRIS
        
### FITUR APLIKASI ###

	>>> Sebagai Merchant <<<

	* Registrasi
	* Update Data
		- nama
		- logo
		- email
		- hp
		- ktp
		- npwp
		- callback url
	* Reset password
	* Mengelola payment provider
		- Company id
		- API Key / Secret
		- Metode integrasi (REST API, SOAP API, ISO-8583)
		- URL payment provider
		- Daftar alamat IP server payment provider
	* Rekap Tagihan
	* History pembayaran
	* Management Invoice
		- Membuat invoice
		- Mengganti nilai dan tanggal jatuh tempo
		- Membatalkan invoice
		- Mengganti payment provider
		
	>>> Sebagai payment Gateway <<<

>> Helpdesk <<
	* Cari tagihan
		- Kirim ulang notifikasi pembbayaran ke merchant
		- Cek status tagihan di payment provider
		
	* Cari pembayaran
	
	
>> Developer
	* Membuat VA dari invoice
	* mensimulasikan payment notif dari bank
	
		
	


1. Membuat repository di github / gitlab

2. Membuat project di start.spring.io
    Dependency
        - Sping Web
        - DBMS (mysql / postgresql)
        - Flyaway Migration
        - Spring Data JPA
        - Lombok
        - Thymleaf

3. Mengekstrak project yang digenerate

4. Membuild project dengan mengabaikan test : mvn clean package -DskipTests

5. push ke github

# Cara Setup Database (Menggunakan Docker dan postgreSql) #

7. Menjalankan postgreSql di Docker
   *password menggunakan random string dari random.org/strings
   *tag image jangan pake default(latest)
    * -v volume mapping simpan ke directory project, tapi tidak usah diupload ke repository
...   
>> POSTGRESQL

	$ docker run --rm\
		  --name invoice-db\
		  -e POSTGRES_DB=invoicedb\
		  -e POSTGRES_USER=invoice\
		  -e POSTGRES_PASSWORD=L5eACAebPZzrYbRZdUfm\
		  -e PGDATA=/var/lib/postgresql/data/pgdata\
		  -v "$PWD/invoicedb:/var/lib/postgresql/data"\
		  -p 5432:5432\
		postgres:13

>> mysql

	$ docker run --rm --name invoice-db\
		-e MYSQL_DATABASE=invoicedb\
		-e MYSQL_USER=invoice\
		-e MYSQL_PASSWORD=L5eACAebPZzrYbRZdUfm\
		-e MYSQL_ROOT_PASSWORD=L5eACAebPZzrYbRZdUfm\
		-v "$PWD/invoicedb:/var/lib/mysql"\
		-p 3306:3306\
		mysql:8.0
    
    

for Windows Command promt:

docker run --rm       --name invoice-db       -e POSTGRES_DB=invoicedb       -e POSTGRES_USER=invoice       -e POSTGRES_PASSWORD=L5eACAebPZzrYbRZdUfm       -e PGDATA=/var/lib/postgresql/data/pgdata       -v "f:/JavaProjects/belajarJavaWeb/invoiceManagement/invoicedb:/var/lib/postgresql/data"       -p 5432:5432     postgres:13
...
    * ketik command diatas kedalam teminal.

    * daftarkan postgresql kedalam path environment variable
      
    * cek koneksi database dengan command : psql -h 127.0.0.1 -U invoice invoicedb    
    // jika terdapat ERROR (psql: error: FATAL:  password authentication failed for user "invoice")
      gunakan perintah : docker exec -ti invoice-db psql -h 127.0.0.1 -U invoice invoicedb
      
    * pastikan tidak server database tidak bentrok
      
8. Mengisi application.properties
... 
   spring.datasource.url=jdbc:postgresql://localhost/invoicedb
   spring.datasource.username=invoice
   spring.datasource.password=L5eACAebPZzrYbRZdUfm
   
   spring.jpa.hibernate.ddl-auto=validate
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
...
   
9. membuat db.migration 
    # Penamaan versi memakai calver (Callender Versioning) sehingga menjadi V20210801__Skema_Awal.sql

    * Buat terlebih template terlebih dahulu untuk testing migrasi database
    ....
      CREATE TABLE payment_provider (
      id VARCHAR (36),
      PRIMARY KEY (id)
      );

    CREATE TABLE invoice_type (
    id VARCHAR (36),
    PRIMARY KEY (id)
    );
    
    CREATE TABLE invoice (
    id VARCHAR (36),
    PRIMARY KEY (id)
    );
    
    CREATE TABLE virtual_account (
    id VARCHAR (36),
    PRIMARY KEY (id)
    );
    
    CREATE TABLE payment (
    id VARCHAR (36),
    PRIMARY KEY (id)
    );
    ....
   
    * Jalankan project : mvn clean spring-boot:run
    
    ### Skema Database Entity Maping
    Buat Entity class
   
   10. Buat class entity untun setiap table pada database
   11. Buat class BaseEntity yang berisi field" yang ada di semua entity
       - di class BaseEntity juga akan diterapkan metode soft delete (data yang dihapus tidak benar" dihapus)
       - terdapat Auidit rail (pengecekan log)
   12. ganti "spring.jpa.hibernate.ddl-auto=validate" menjadi update
   13. jalankan lagi projectnya "mvn clean spring-boot:run"
   14. jalankan pg_dump untuk melihat syntax sql yang dihasilkan
   15. copy semua syntax sql block by blok kedalam db_migration 
   16. rubah lagi "spring.jpa.hibernate.ddl-auto=validate" menjadi validate
   17. restart database
   18. restart aplikasi
   19. tambahkan relasi many to many pada class InvoiceType
   20. buat bridge table many to many di skema database
   21. Restart docker, hapus folder, dan restart aplikasi
    
## Membuat DAO dan menerapkan Soft Delete

    22. Membauat Interface Dao untuk masing-masing bussines proses
        - extends PaginAndSortingRepository<Entity entityClass, Tipedata typePrimaryKey>

    23. Buat testing Audit rail dan soft delete
        - cukup test satu saja

    24. Buat config untuk JpaAuditing
        ...
        @Configuration
        @EnableJpaAuditing(auditorAwareRef = "auditorProvider")
        public class JpaAuditingConfig {
        
            @Bean
            public AuditorAware<String> auditorProvider(){
                return () -> Optional.of("Test user");
            }
        }
        ...
    
    26. tambahkan annotation @EntitiyListener(AuditingEntityListener.class) di BaseEntity (Sehingga bisa berjalan di 
        semua entity)

    27. Buat SQL yang akan selalu dijalankan ketika melakukan testing
        - Salah satu kegunaanya adalah untuk menghapus record sebelum melakukan testing,
        supaya tidak error saat field yang diinputkan harus unique.
            * Buat file sql di diresources test
            ...
                DELETE FROM invoice-type;
            ...
            * tambahkan annotation @Sql(script = "/folder/filesql.sql") pada class testing

    28. Testing UpdatedTime
        - Menjalankan Thread.sleep(2000), merubah record, kemudian menyimpan perubahan dengan hasil returnya
        diisikan ke objek.
        - Kemudian jalankan Assertion.assetNotEquals()

    29. Testing soft delete
        - Buat sql untuk insert record dengan status ACTIVE dan INACTIVE
        - Tambahkan file sql tersebut kedalam anotation @Sql di class testing
        - lakukan testing dengan menghitung jumlah record, kemudian menghapus salahsatunya.
        ...
         @Test
        void testSoftDelete() {
            long total = invoiceTypeDao.count();
            System.out.println("Total record sebelum didelete: " +total);
            Assertions.assertEquals(2, total);
    
            invoiceTypeDao.delete(invoiceTypeDao.findById("test02").get());
            total = invoiceTypeDao.count();
            System.out.println("Total record sesudahb didelete: " +total);
            Assertions.assertEquals(1, total);
        }
        ...
    
### Membuat Service, Exception, dan Helper
    - Service digunakan ketika terdapat bisnis proses yang melibatkan beberapa DAO (rumit),
    sedangkan jika hanya sederhana maka bisa langsung dibuat di controller

    - Checked Exception digunakan untuk exception yang bisa ditangani oleh controller,
    atau terdapat kondisi yang bisa ditangani oleh user (seperti salah input, dll).
    
    - Runtime exception digunakan untuk exception yang terjadi tidak bisa ditangani oleh user
    sehingga ditampilkan generic error yang memberi tahu kepada user untuk menghubungi team developer.

    
    - Untuk method yang kemungkinan akan dipakai di banyak tempat, jangan melakukan copy paste.
    akan tetapi harus dijadikan sebuah class static bernama helper sehingga bisa langsung dipakai di
    banyak tempat.

    29. Membuat PaymentService
        * membuat class service dan menambahkan annotation @Service, @Transactional
        // Proses bisnis :
        /**
         * 1. Cek Apakah Virtual Account ada ?
         *  - Query ke tabel virtual acount ( membuat objek VirtualAccountDao)
         *  - query dengan menggunakan findBy
         */

        // Contoh Implementasi topdown programming (coding dulu, trus automatis dibuatin method implementasinya sama 
        // editor)
        
        
        
###### PROSES BISNIS GANTI PASSWORD ######

	* Inputan dari User
		- Password Lama
		- Password Baru
		- Konfirmasi password baru
	
	* Proses
		1. Terima password Lama
		2. hash input password lama
		3. compare dengan hash DB
		4. compare password baru dengan konfirmasi
		5. hash password baru
		6. update DB
	
	
##### PROSES BISNIS LUPA PASSWORD #####

	* Inputan dari user
		- email
		
	* Proses
		1. Cari email di DB
			- Jika email yang diinput tidak ditemukan, JANGAN DIINFORMASIKAN KE USER !!!
		2. instruksikan cek email
		3. generate kode unik (UUID / SecureRandom)
		4. insert ke tabel reser password
		5. kirim link reset ke email ( https://app.com/resetpassword)

##### PERINTAH SQL UNTUK MENGURAI DATA WILAYAH ########

## Sql to get provinsi
select kode as id, kode as code, nama as name from wilayah_2020 where length(kode)=2;

## Sql to insert provinsi
insert into provinsi(id, code, name)
select kode, kode, nama from wilayah_2020 where length(kode) = 2;

## Sql to get kota_kabupaten
select kode as id, kode, nama from wilayah_2020 where length(kode)=5;

## Sql to insert kota_kabupaten
insert into kota_kabupaten(id, id_provinsi, code, name)
select kode, substring(kode, 1, 2), kode, nama from wilayah_2020 where length(kode) = 5;

##sql to get kecamatan
select kode as id, substring(kode, 1, 5) as id_kota_kab, kode as code, nama as name from wilayah_2020 where length(kode)=8;

##sql insert kecamatan
insert into kecamatan(id, id_kota_kab, code, name)
select kode, substring(kode, 1, 5), kode, nama from wilayah_2020 where length(kode) = 8;

##sql insert kelurahan
insert into kelurahan(id, id_kecamatan, code, name)
select kode, substring(kode, 1, 8), kode, nama from wilayah_2020 where length(kode) = 13;

##### GMAIL API CREDENTIALS ########

gmail API client id : 413833577093-g90fv48b3964kns7p5nv60ocad72cnvo.apps.googleusercontent.com
gmail API client secret : 9V6r9xZLQGBUa9AAzIp630oL
