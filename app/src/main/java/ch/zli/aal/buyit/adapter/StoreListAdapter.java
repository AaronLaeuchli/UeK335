package ch.zli.aal.buyit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.zli.aal.buyit.R;
import ch.zli.aal.buyit.model.Store;

public class StoreListAdapter extends ArrayAdapter<Store> {
    public StoreListAdapter(Context context, List<Store> stores) {
        super(context, 0, stores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Store store = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.name)).setText(store.getStoreName());
        return convertView;
    }
}
