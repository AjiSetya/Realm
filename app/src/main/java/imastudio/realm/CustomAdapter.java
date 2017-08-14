package imastudio.realm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.id;


/**
 * Created by AJISETYA on 9/29/2016.
 */
public class CustomAdapter extends BaseAdapter{
    private Context c;
    private ArrayList<MahasiswaModel> data;

    /*konstruktor untuk berinteraksi dengan class ini*/
    public CustomAdapter(Context c, ArrayList<MahasiswaModel> data) {
        this.c = c;
        this.data = data;
    }

    @Override
    public int getCount() {
        /*tampilkan seluruh data*/
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*variable untuk menginflate*/
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        /*gunakan layout listmahasiswa unutk menampung masing-masing data*/
        View v = inflater.inflate(R.layout.listmahasiswa,null);
        /*deskripsikan seluruh widget*/
        TextView txtnama = (TextView) v.findViewById(R.id.txttnama);
        TextView txtalamat = (TextView) v.findViewById(R.id.txttalamat);
        /*menampikan data*/
        txtnama.setText(data.get(i).getNamaMahasiswa());
        txtalamat.setText(data.get(i).getAlamatMahasiswa());
        return v;
    }
}
