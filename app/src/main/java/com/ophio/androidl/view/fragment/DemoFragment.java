package com.ophio.androidl.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ophio.androidl.R;
import com.ophio.androidl.view.DemoEntity;
import com.ophio.androidl.view.adapter.DemoAdapter;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DemoFragment extends Fragment implements  android.support.v7.view.ActionMode.Callback {

    private static final String LOGTAG = DemoFragment.class.getSimpleName();

    @InjectView(R.id.demo_list) TwoWayView demoList;

    private DemoAdapter demoAdapter;
    private ActionMode actionMode;

    public DemoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeList();
    }

    private void initializeList() {
        demoList.setHasFixedSize(true);
        final ItemClickSupport itemClick = ItemClickSupport.addTo(demoList);
        itemClick.setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView parent, View child, int position, long id) {
                Log.d(LOGTAG, "long click");
                if (actionMode != null) {
                    return true;
                }
                actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(DemoFragment.this);
                return true;
            }
        });
        demoAdapter = getMockDemoAdapter();
        demoList.setAdapter(demoAdapter);
    }

    private DemoAdapter getMockDemoAdapter() {

        List<DemoEntity> demoEntities = new ArrayList<DemoEntity>();


        DemoEntity demoEntity1 = new DemoEntity(R.drawable.image_one, "one");
        DemoEntity demoEntity2 = new DemoEntity(R.drawable.image_two, "two");
        DemoEntity demoEntity3 = new DemoEntity(R.drawable.image_three, "three");
        DemoEntity demoEntity4 = new DemoEntity(R.drawable.image_four, "four");
        DemoEntity demoEntity5 = new DemoEntity(R.drawable.image_five, "five");


        demoEntities.add(demoEntity1);
        demoEntities.add(demoEntity2);
        demoEntities.add(demoEntity3);
        demoEntities.add(demoEntity4);
        demoEntities.add(demoEntity5);

        return new DemoAdapter(demoEntities);
    }

    //Callbacks for contextual mode

    @Override
    public boolean onCreateActionMode(android.support.v7.view.ActionMode actionMode, Menu menu) {
        Log.d(LOGTAG, "on actionmode created");
        actionMode.setTitle("actionmode");
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.contextual_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(android.support.v7.view.ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(android.support.v7.view.ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    @Override
    public void onDestroyActionMode(android.support.v7.view.ActionMode actionMode) {
        this.actionMode = null;
    }
}
