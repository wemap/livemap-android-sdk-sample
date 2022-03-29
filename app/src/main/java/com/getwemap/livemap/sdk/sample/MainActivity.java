package com.getwemap.livemap.sdk.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends Activity {

    private final static ActivityNamed[] ACTIVITIES = new ActivityNamed[]{
            new ActivityNamed("Hello World", HelloWorldActivity.class),
            new ActivityNamed("Hello World Programmatically", ProgrammaticallyViewActivity.class),
            new ActivityNamed("Livemap Methods", LivemapMethodsActivity.class),
            new ActivityNamed("Livemap Events", LivemapEventsActivity.class),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL)
        );

        RecyclerView.Adapter<ActivityAdapter.MyViewHolder> adapter = new ActivityAdapter();
        recyclerView.setAdapter(adapter);
    }

    public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder> {


        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            MyViewHolder(TextView v) {
                super(v);
                textView = v;
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {

            TextView v = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_text_view, parent, false);

            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.textView.setText(ACTIVITIES[position].name);
            holder.textView.setOnClickListener(view ->
                    startActivity(new Intent(MainActivity.this, ACTIVITIES[position].activity))
            );
        }

        @Override
        public int getItemCount() {
            return ACTIVITIES.length;
        }
    }

    static class ActivityNamed {
        final String name;
        final Class<? extends Activity> activity;

        ActivityNamed(String name, Class<? extends Activity> activity) {
            this.name = name;
            this.activity = activity;
        }
    }

}
