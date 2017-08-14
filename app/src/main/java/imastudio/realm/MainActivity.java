package imastudio.realm;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static imastudio.realm.R.id.fab;
import static imastudio.realm.R.id.txtalamat;
import static imastudio.realm.R.id.txtnama;

public class MainActivity extends AppCompatActivity {

    /*variable pada objek*/
    RealmHelper realmHelper;
    ListView lv;
    private ArrayList<MahasiswaModel> data;
    private RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(MainActivity.this, String.valueOf(System.currentTimeMillis()), Toast.LENGTH_LONG).show();
        /*perkenalkan class RealmHelper pada class ini*/
        realmHelper = new RealmHelper(MainActivity.this);
        /*deklarasikan widaget listview*/
        lv = (ListView) findViewById(R.id.lv);
        data = new ArrayList<>();

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6309516150695970/6205708849");

        AdView adview = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);

        /*menmpilkan data saat activity muncul*/
        getData();

        /*event pada FAB*/
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*menampilkan dialog*/
                final Dialog dialog = new Dialog(MainActivity.this);
                /*memasukkan layout ke dalam dialog*/
                dialog.setContentView(R.layout.insert_mahasiswa);

                /*deklarasikan widget*/
                final EditText txtalamat = (EditText) dialog.findViewById(R.id.txtalamat);
                final EditText txtnama = (EditText) dialog.findViewById(R.id.txtnama);
                Button btntambah = (Button) dialog.findViewById(R.id.btnTambah);



                /*ketika tombol tambah dipilih maka*/
                btntambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*simpan data dengan memenggil method addMahasiswa*/
                        String nama = txtnama.getText().toString();
                        String alamat = txtalamat.getText().toString();
                        realmHelper.addMahasiswa(nama, alamat);
                        dialog.dismiss();
                        getData();
                    }
                });

                /*tampilkan dialog*/
                dialog.show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });

        /*event ketika listview diklik*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                /*model mahasiswa diambil dengan variable d sesuai posisi*/
                final MahasiswaModel d = data.get(i);

                /*menampilkan dialog*/
                /*perkenalkan diaog ke dalam actiity ini*/
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.edit_mahasiswa);

                /*deskripsikan widget*/
                final EditText edalamat = (EditText) dialog.findViewById(R.id.edalamat);
                final EditText ednama = (EditText) dialog.findViewById(R.id.ednama);
                Button btntambah = (Button) dialog.findViewById(R.id.btnEdit);
                Button btnhapus = (Button) dialog.findViewById(R.id.btnHapus);

                /*menampilkan data ke dalam EditText*/
                ednama.setText(d.getNamaMahasiswa());
                edalamat.setText(d.getAlamatMahasiswa());

                /*event pada tombol hapus*/
                btnhapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realmHelper.deleteData(d.idMahasiswa);
                        dialog.dismiss();
                        getData();
                    }
                });

                /*event pada tombol edit*/
                btntambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nama = ednama.getText().toString();
                        String alamat = edalamat.getText().toString();
                        realmHelper.updateData(d.idMahasiswa, nama, alamat);
                        dialog.dismiss();
                        getData();
                    }
                });

                dialog.show();
            }
        });
    }


    /*method mengambil data*/
    private void getData() {
        try {
            data = realmHelper.findAll();
        } catch (Exception e) {

        }

        /*memasukan data ke dalam ListVuiew*/
        try {
            CustomAdapter adapter = new CustomAdapter(this, data);
            lv.setAdapter(adapter);
        } catch (NullPointerException e) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
