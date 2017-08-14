package imastudio.realm;
/**
 * Created by AJISETYA
 */
public class MahasiswaModel {

    int idMahasiswa;
    String namaMahasiswa, alamatMahasiswa;

    public int getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(int idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getAlamatMahasiswa() {
        return alamatMahasiswa;
    }

    public void setAlamatMahasiswa(String alamatMahasiswa) {
        this.alamatMahasiswa = alamatMahasiswa;
    }

    /*konstruktor unutk berinteraksi dengan class ini
    serta menambahkan data ke dalam class ini*/
    public MahasiswaModel(int idMahasiswa, String namaMahasiswa, String alamatMahasiswa) {
        this.idMahasiswa = idMahasiswa;
        this.namaMahasiswa = namaMahasiswa;
        this.alamatMahasiswa = alamatMahasiswa;
    }
}
