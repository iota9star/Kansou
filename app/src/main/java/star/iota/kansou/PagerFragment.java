package star.iota.kansou;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerFragment extends BaseFragment implements PVContract.View {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private TabLayout mTabLayout;
    private ProgressBar mProgressBar;

    private DataPresenter mPresenter;

    public static PagerFragment newInstance(String url, int type, int tabMode) {
        PagerFragment fragment = new PagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putInt("type", type);
        bundle.putInt("tab_mode", tabMode);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {
        mTabLayout = ButterKnife.findById(getActivity(), R.id.tab_layout);
        mProgressBar = ButterKnife.findById(getActivity(), R.id.progress_bar);
        Bundle arguments = getArguments();
        int tabMode = arguments.getInt("tab_mode");
        String url = arguments.getString("url");
        int type = arguments.getInt("type");
        mTabLayout.setTabMode(tabMode);
        mPresenter = new DataPresenter(this);
        mPresenter.get(type, url);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_view_pager;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTabLayout != null) {
            mTabLayout.removeAllTabs();
            mTabLayout.setVisibility(View.GONE);
        }
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }

    @Override
    public void success(Bean result) {
        int type = result.getType();
        HashMap<String, ArrayList<Bean.ItemBean>> category = result.getCategory();
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Bean.ItemBean>> entry : category.entrySet()) {
            fragments.add(DataFragment.newInstance(type, entry.getValue()));
            titles.add(entry.getKey());
        }
        Collections.reverse(fragments);
        Collections.reverse(titles);
        PagerAdapter mAdapter = new PagerAdapter(getChildFragmentManager(), fragments, titles);
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setVisibility(View.VISIBLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void error(String error) {
        mProgressBar.setVisibility(View.GONE);
        Snackbar.make(getActivity().findViewById(android.R.id.content), "出现错误：" + error, Snackbar.LENGTH_SHORT).show();
    }
}
