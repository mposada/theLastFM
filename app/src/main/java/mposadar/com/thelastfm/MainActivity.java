package mposadar.com.thelastfm;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import mposadar.com.thelastfm.ui.adapter.PagerAdapter;
import mposadar.com.thelastfm.ui.fragment.TopAlbumsFragment;
import mposadar.com.thelastfm.ui.fragment.TopArtistsFragment;

public class MainActivity extends AppCompatActivity {

    // UI REFERENCE
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.ViewPager);
        tabLayout = (TabLayout) findViewById(R.id.TabLayout);

        setupViewPager();
    }

    /**
     * setup the viewPager and tabLayout
     */
    private void setupViewPager() {
        // SET THE ADAPTER TO VIEWPAGER AND PAST THE FRAGMENTS
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),
                buildFragments()));
        // SET THE VIEWPAGER TO TAB LAYOUT
        tabLayout.setupWithViewPager(viewPager);
        // SET THE ICONS TO EACH TAB
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_fire));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_album));
    }

    /**
     * Add the fragments to an ArrayList of Fragments
     * to return to the PagerAdapter
     * @return
     */
    private ArrayList<Fragment> buildFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        // INITIALIZE THE FRAGMENTS
        TopArtistsFragment topArtistsFragment = new TopArtistsFragment();
        TopAlbumsFragment topAlbumsFragment = new TopAlbumsFragment();
        // ADD THE FRAGMENTS TO THE ARRAY LIST
        fragments.add(topArtistsFragment);
        fragments.add(topAlbumsFragment);
        // RETURN THE ARRAY LIST
        return fragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
