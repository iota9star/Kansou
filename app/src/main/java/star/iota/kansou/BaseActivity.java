package star.iota.kansou;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private final long[] mHints = new long[2];
    private Unbinder unbinder;

    protected abstract void init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        unbinder = ButterKnife.bind(this);
        init();
        setFirstFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected abstract int getContentViewId();

    int getFragmentContainerId() {
        return 0;
    }

    void setFirstFragment() {

    }

    void showFragment(BaseFragment fragment) {
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tx = fm.beginTransaction();
            tx.replace(getFragmentContainerId(), fragment);
            tx.commit();
        }
    }

    void exit() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
            mHints[mHints.length - 1] = SystemClock.uptimeMillis();
            Snackbar.make(findViewById(android.R.id.content), "真的要退出了吗", Snackbar.LENGTH_SHORT).setAction("再按一次退出", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.exit(0);
                }
            }).show();
            if (SystemClock.uptimeMillis() - mHints[0] <= 1600) {
                System.exit(0);
            }
        }
    }

    void removeFragmentContainerChildrenViews() {
        ((ViewGroup) findViewById(getFragmentContainerId())).removeAllViews();
    }
}
