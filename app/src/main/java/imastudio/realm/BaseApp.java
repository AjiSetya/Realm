package imastudio.realm;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
/**
 * Created by AJISETYA
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*konfigurasi realm*/
        RealmConfiguration configuration = new RealmConfiguration
                .Builder(this)
                .schemaVersion(0)
                .migration(new DataMigration())
                .build();

        Realm.setDefaultConfiguration(configuration);

    }


    /*class deklarasi database dan table*/
    private class DataMigration implements RealmMigration {

        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();
            /*karena versinya 1 maka*/
            if (oldVersion == 0) {
                /*membuat tabel baru dengan nama mahasiswa*/
                schema.create("mahasiswa")
                        .addField("idMahasiswa", int.class)
                        .addField("namaMahasiswa", String.class)
                        .addField("alamatMahasiswa", String.class);

                oldVersion++;
            }
        }
    }
}
