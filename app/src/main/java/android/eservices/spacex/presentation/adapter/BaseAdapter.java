package android.eservices.spacex.presentation.adapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<MyViewHolder> {
    protected List<T> list;

    public BaseAdapter() {
        this.list = new ArrayList<>();
    }

    public void bindViewModels(List<T> list) {
        this.list.clear();
        if(list.isEmpty()){
            //remplir un champ pour expliquer qu'il n'y a rien à afficher
        }else{
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }
}
