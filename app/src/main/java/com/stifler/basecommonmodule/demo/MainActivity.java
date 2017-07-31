package com.stifler.basecommonmodule.demo;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.stifler.basecommonmodule.demo.base.ui.BaseActivity;
import com.stifler.basecommonmodule.demo.module.cinema.CinemaListActivity;
import com.stifler.basecommonmodule.demo.module.movie.MovieListActivity;
import com.stifler.basecommonmodule.demo.module.show.ShowListActivity;
import com.stifler.basecommonmodule.demo.widget.B;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    CinemaListActivity.startActivity(MainActivity.this);
                    break;
                case R.id.action_share:
                    new B().showB();
                    msg += "Click share";
                    MovieListActivity.startActivity(MainActivity.this);
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    ShowListActivity.startActivity(MainActivity.this);
                    break;
            }

            if(!msg.equals("")) {
            }
            return true;
        }
    };

    @Override
    public void initData() {
    }

    @Override
    public void initInject() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void bindListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("My Title");
        toolbar.setSubtitle("Sub title");
        toolbar.setNavigationIcon(R.mipmap.arrow_back_black);
//        toolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
