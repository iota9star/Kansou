package star.iota.kansou;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

public class MainActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private Drawer mDrawer;
    private int mCurrentFragmentId;

    @Override
    protected void init() {
        setSupportActionBar(mToolbar);
        initDrawer();
        initDrawerEvent();
    }

    @Override
    protected void setFirstFragment() {
        mCurrentFragmentId = Contracts.Menu.INDEX_ID;
        showFragment(PagerFragment.newInstance(Contracts.Menu.INDEX_URL, Contracts.Type.THREE, TabLayout.MODE_SCROLLABLE));
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.frame_layout_fragment_container;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    private void initDrawer() {
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withItemAnimator(new LandingAnimator())
                .withHeader(R.layout.drawer_header_view)
                .withHeaderDivider(false)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(Contracts.Menu.INDEX).withIdentifier(Contracts.Menu.INDEX_ID),
                        new PrimaryDrawerItem().withName(Contracts.Menu.ANIMEKA).withIdentifier(Contracts.Menu.ANIMEKA_ID),
                        new PrimaryDrawerItem().withName(Contracts.Menu.MOVIE).withIdentifier(Contracts.Menu.MOVIE_ID),
                        new PrimaryDrawerItem().withName(Contracts.Menu.OVA).withIdentifier(Contracts.Menu.OVA_ID),
                        new PrimaryDrawerItem().withName(Contracts.Menu.OAD).withIdentifier(Contracts.Menu.OAD_ID),
                        new PrimaryDrawerItem().withName(Contracts.Menu.BOX).withIdentifier(Contracts.Menu.BOX_ID)
                )
                .withSelectedItem(Contracts.Menu.INDEX_ID)
                .build();
    }


    private void initDrawerEvent() {
        mDrawer.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem dItem) {
                BaseFragment currentFragment = null;
                int identifier = (int) dItem.getIdentifier();
                if (identifier == mCurrentFragmentId || identifier == 999) return false;
                mCurrentFragmentId = identifier;
                switch (identifier) {
                    case Contracts.Menu.INDEX_ID:
                        currentFragment = PagerFragment.newInstance(Contracts.Menu.INDEX_URL, Contracts.Type.THREE, TabLayout.MODE_SCROLLABLE);
                        break;
                    case Contracts.Menu.ANIMEKA_ID:
                        currentFragment = PagerFragment.newInstance(Contracts.Menu.ANIMEKA_URL, Contracts.Type.THREE, TabLayout.MODE_FIXED);
                        break;
                    case Contracts.Menu.MOVIE_ID:
                        currentFragment = PagerFragment.newInstance(Contracts.Menu.MOVIE_URL, Contracts.Type.THREE, TabLayout.MODE_FIXED);
                        break;
                    case Contracts.Menu.OVA_ID:
                        currentFragment = PagerFragment.newInstance(Contracts.Menu.OVA_URL, Contracts.Type.THREE, TabLayout.MODE_FIXED);
                        break;
                    case Contracts.Menu.OAD_ID:
                        currentFragment = PagerFragment.newInstance(Contracts.Menu.OAD_URL, Contracts.Type.TWO, TabLayout.MODE_FIXED);
                        break;
                    case Contracts.Menu.BOX_ID:
                        currentFragment = PagerFragment.newInstance(Contracts.Menu.BOX_URL, Contracts.Type.TWO, TabLayout.MODE_FIXED);
                        break;
                }
                removeFragmentContainerChildrenViews();
                showFragment(currentFragment);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null && mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        }
    }

    @Override
    public void onBackPressed() {
        exit();
    }
}
