package com.wildma.bigworld.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wildma.bigworld.R;
import com.wildma.bigworld.adapter.HotspotNewsAdapter;
import com.wildma.bigworld.bean.HotspotNewsListBean;
import com.wildma.bigworld.presenter.implPresenter.HotspotNewsPresenterImpl;
import com.wildma.bigworld.presenter.implView.IHotspotNewsFragment;
import com.wildma.bigworld.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * Author       wildma
 * Date         2017/12/12
 * Desc	        ${热点新闻Fragment}
 */
public class HotspotNewsFragment extends BaseFragment implements IHotspotNewsFragment {

    HotspotNewsPresenterImpl mHotspotNewsPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar  mProgressBar;

    boolean             loading;
    HotspotNewsAdapter  mHotspotNewsAdapter;
    LinearLayoutManager mLinearLayoutManager;
    private String currentLoadDate;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hotspot_news;
    }

    @Override
    protected void initView() {
        super.initView();
        mHotspotNewsPresenter = new HotspotNewsPresenterImpl(getContext(), this);
        mHotspotNewsAdapter = new HotspotNewsAdapter(getContext());

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mHotspotNewsAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        recyclerViewListener();
    }

    @Override
    protected void initData() {
        super.initData();
        loadDate();
    }

    /**
     * recyclerView的滑动监听
     */
    private void recyclerViewListener() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) //向下滚动
                {
                    int visibleItemCount = mLinearLayoutManager.getChildCount();
                    int totalItemCount = mLinearLayoutManager.getItemCount();
                    int pastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading && (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = true;
                        loadMoreDate();
                    }
                }
            }
        });
    }


    /**
     * 加载数据
     */
    private void loadDate() {
        if (mHotspotNewsAdapter.getItemCount() > 0) {
            mHotspotNewsAdapter.clearData();
        }
        currentLoadDate = "0";
        mHotspotNewsPresenter.getLatestNews();

    }

    /**
     * 加载更多数据
     */
    private void loadMoreDate() {
        mHotspotNewsPresenter.getBeforeNews(currentLoadDate);
    }

    @Override
    public void updateList(HotspotNewsListBean hotspotNewsListBean) {
        if (loading) {
            loading = false;
        }
        currentLoadDate = hotspotNewsListBean.getDate();
        mHotspotNewsAdapter.addItems(hotspotNewsListBean.getStories());
        //        if the new data is not full of the screen, need load more data
        if (!mRecyclerView.canScrollVertically(View.SCROLL_INDICATOR_BOTTOM)) {
            loadMoreDate();
        }
    }

    @Override
    public void showProgressDialog() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hidProgressDialog() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHotspotNewsPresenter.unsubcrible();
    }

}
