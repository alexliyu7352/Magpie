package me.zhouzhuo810.magpie.ui.widget.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.Arrays;
import java.util.List;

import me.zhouzhuo810.magpie.ui.widget.intef.IResProvider;

/**
 * Created by zz on 2016/8/22.
 */
public abstract class BaseFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter implements IResProvider {
    
    private List<String> titles;
    
    public BaseFragmentPagerAdapter(FragmentManager manager, String[] titles) {
        super(manager);
        if (titles != null) {
            this.titles = Arrays.asList(titles);
        }
    }
    
    public List<String> getTitles() {
        return titles;
    }
    
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
    
    public BaseFragmentPagerAdapter(FragmentManager manager, List<String> titles) {
        super(manager);
        this.titles = titles;
    }
    
    public void setPageTitle(int position, String title) {
        if (position >= 0 && titles != null && position < titles.size()) {
            titles.set(position, title);
            notifyDataSetChanged();
        }
    }
    
    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
    
    protected abstract Fragment getFragment(int position);
    
    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }
    
    
}
