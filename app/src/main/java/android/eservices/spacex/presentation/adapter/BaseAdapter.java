package android.eservices.spacex.presentation.adapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<MyViewHolder> {
    protected List<T> list;

    public BaseAdapter() {
        this.list = new ArrayList<>();
    }

    public void bindViewModels(List<T> list, String header) {
        this.list.clear();
        if(list.isEmpty()){
            //remplir un champ pour expliquer qu'il n'y a rien à afficher
        }else{
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }
}
