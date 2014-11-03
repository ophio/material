package com.vashisthg.androidl;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DemoFragment extends Fragment {

    @InjectView(R.id.demo_list) TwoWayView demoList;

    private DemoAdapter demoAdapter;

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

        demoList.setHasFixedSize(true);
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

}
