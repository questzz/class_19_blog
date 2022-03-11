package com.example.blog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.blog.databinding.ActivityMainBinding;
import com.example.blog.models.request.ReqJoin;
import com.example.blog.models.request.ReqLogin;
import com.example.blog.models.response.ResJoin;
import com.example.blog.models.response.ResLogin;
import com.example.blog.models.response.ResPost;
import com.example.blog.models.response.ResUserDto;
import com.example.blog.repository.BlogService;
import com.example.blog.utils.FragmentType;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private ActivityMainBinding binding;
    private BlogService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 통신 성공 여부 테스트
        service = BlogService.retrofit.create(BlogService.class);
        replaceFragment(FragmentType.LIST);
        addBottomNaviListener();
    }


    private void replaceFragment(FragmentType type) {
        Fragment fragment = null;
        switch (type) {
            case LIST:
                fragment = new BlogListFragment();
                break;
            case INFO:
                fragment = new InfoFragment();
                break;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.mainContainer.getId(), fragment);
        transaction.commit();
    }

    private void addBottomNaviListener() {
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        replaceFragment(FragmentType.LIST);
                        break;
                    case R.id.page_2:
                        replaceFragment(FragmentType.INFO);
                        break;
                }
                return true;
            }
        });
    }
}











