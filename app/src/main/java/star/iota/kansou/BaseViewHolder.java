package star.iota.kansou;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;


abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    protected abstract void bindView(Bean.ItemBean bean);
}
