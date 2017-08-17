package star.iota.kansou;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;


class ItemAdapter extends RecyclerView.Adapter {
    private final int type;
    private final List<Bean.ItemBean> list;

    ItemAdapter(int type, List<Bean.ItemBean> list) {
        this.type = type;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case Contracts.Type.TWO:
                return new TwoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_two_column, viewGroup, false));
            case Contracts.Type.THREE:
                return new ThreeViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_three_column, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((BaseViewHolder) viewHolder).bindView(list.get(i));
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
