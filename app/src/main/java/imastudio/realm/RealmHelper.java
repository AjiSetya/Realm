package imastudio.realm;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by AJISETYA on 9/29/2016.
 */
public class RealmHelper {

    /*variable untuk objek*/
    private Realm realm;
    private RealmResults<Mahasiswa> realmResults;
    private Context c;

    /*konstruktor unutk terhubung dengan class ini*/
    public RealmHelper(Context context) {
        this.c = context;
        this.realm = realm.getInstance(context);
    }

    /*method menambah data baru*/
    public void addMahasiswa(String nama, String alamat) {
        /*perkenalkan class Mahasiswa dengan membuat variable baru*/
        Mahasiswa mahasiswa = new Mahasiswa();
        /*memasukkan masing-masing data ke dalam class Mahasiswa*/
        /*bilangan rundom*/
        mahasiswa.setIdMahasiswa((int) (System.currentTimeMillis() / 100));
        mahasiswa.setNamaMahasiswa(nama);
        mahasiswa.setAlamatMahasiswa(alamat);

        realm.beginTransaction();

        realm.copyToRealm(mahasiswa);
        realm.commitTransaction();

        Toast.makeText(c, "Data tersimpan", Toast.LENGTH_SHORT).show();
    }

    /*method unutk menemka seluruh data*/
    public ArrayList<MahasiswaModel> findAll() {
        /*variable arraylist untuk menampung data*/
        ArrayList<MahasiswaModel> data = new ArrayList<>();
        realmResults = realm.where(Mahasiswa.class).findAll();
        realmResults.sort("idMahasiswa", Sort.DESCENDING);

        /*jika data ditemukan maka masing-masing data dimasukkan ke dalam modelmahasiswa*/
        if (realmResults.size() > 0) {
            for (int a = 0; a < realmResults.size(); a++) {
                String nama, alamat;
                int id = realmResults.get(a).getIdMahasiswa();
                nama = realmResults.get(a).getNamaMahasiswa();
                alamat = realmResults.get(a).getAlamatMahasiswa();

                data.add(new MahasiswaModel(id, nama, alamat));

                Toast.makeText(c, "Data ditemukan", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(c, "Data tidak ada", Toast.LENGTH_SHORT).show();
        }
        return data;
    }

    /*method untuk memperbarui data*/
    public void updateData(int id, String nama, String alamat) {
        realm.beginTransaction();
        /*cari berdasarkan kode*/
        Mahasiswa mahasiswa = realm.where(Mahasiswa.class).equalTo("idMahasiswa", id).findFirst();
        /*masukkan ke dalam class mahasiswa*/
        mahasiswa.setNamaMahasiswa(nama);
        mahasiswa.setAlamatMahasiswa(alamat);
        realm.commitTransaction();
    }

    /*method untuk menghapus data*/
    public void deleteData(int id) {
        realm.beginTransaction();
        RealmResults<Mahasiswa> dataresult = realm.where(Mahasiswa.class)
                .equalTo("idMahasiswa", id).findAll();
        dataresult.remove(0);
        dataresult.removeLast();
        dataresult.clear();
        realm.commitTransaction();
    }
}
