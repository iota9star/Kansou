package star.iota.kansou;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class DataFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static DataFragment newInstance(int type, ArrayList<Bean.ItemBean> items) {
        DataFragment fragment = new DataFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putParcelableArrayList("items", items);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        ArrayList<Bean.ItemBean> items = bundle.getParcelableArrayList("items");
        ItemAdapter itemAdapter = new ItemAdapter(type, items);
        mRecyclerView.setItemAnimator(new LandingAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(itemAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_view;
    }
}
