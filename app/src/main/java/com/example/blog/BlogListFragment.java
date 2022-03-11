package com.example.blog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blog.adapter.BlogListAdapter;
import com.example.blog.databinding.FragmentBlogListBinding;
import com.example.blog.interfaces.OnPageRequest;
import com.example.blog.models.response.ResPost;
import com.example.blog.repository.BlogService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlogListFragment extends Fragment implements OnPageRequest {

    private FragmentBlogListBinding binding;
    private BlogListAdapter adapter;
    private BlogService service;
    private String token;

    public BlogListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = BlogService.retrofit.create(BlogService.class);

        SharedPreferences preferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        token = preferences.getString("jwt", "");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlogListBinding.inflate(inflater, container, false);
        initRecyclerView();
        requestBlogList();

        return binding.getRoot();
    }



    private void initRecyclerView() {

        adapter = new BlogListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        binding.blogListRecyclerView.setAdapter(adapter);
        binding.blogListRecyclerView.setLayoutManager(linearLayoutManager);
        binding.blogListRecyclerView.hasFixedSize();
        binding.blogListRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
    }

    private void requestBlogList() {
        service.postList(token).enqueue(new Callback<ResPost>() {
            @Override
            public void onResponse(Call<ResPost> call, Response<ResPost> response) {
                if (response.isSuccessful()) {
                    ResPost resPost = response.body();
                    adapter.addItems(resPost.data);
                }
            }

            @Override
            public void onFailure(Call<ResPost> call, Throwable t) {

            }
        });
    }


    /**
     *
     * @param postId : 해당 게시글 아이디 값
     */
    @Override
    public void onPageChange(int postId) {
        Intent intent = new Intent(getContext(), BlogDetailActivity.class);
        intent.putExtra("postId", postId);
        startActivity(intent);
    }
}