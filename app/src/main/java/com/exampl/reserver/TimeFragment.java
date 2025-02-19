package com.exampl.reserver;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TimeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time, container, false);

        // Click listener for the RecyclerView
        View.OnClickListener onClickListener = itemView -> {

            // Create fragment arguments containing the selected band ID
            int selectedId = (int) itemView.getTag();
            Bundle args = new Bundle();
            args.putInt("test", selectedId);
            Log.d(String.valueOf(selectedId), "Clicked on the event");
        };

        // Send bands to RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.time_list);
        List<TimeObj> times = new ArrayList<>();
        times.add(new TimeObj(1, getString(R.string._4_00_pm)));
        times.add(new TimeObj(2,  getString(R.string._4_30_pm)));
        times.add(new TimeObj(3, getString(R.string._5_00_pm)));
        times.add(new TimeObj(4, getString(R.string._5_30_pm)));
        times.add(new TimeObj(5, getString(R.string._6_00_pm)));
        times.add(new TimeObj(6, getString(R.string._6_30_pm)));
        times.add(new TimeObj(7, getString(R.string._7_00_pm)));
        times.add(new TimeObj(8, getString(R.string._7_30_pm)));
        recyclerView.setAdapter(new TimeAdapter(times, onClickListener));

        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        return rootView;
    }

    private class TimeAdapter extends RecyclerView.Adapter<TimeHolder> {

        private final List<TimeObj> mTimes;
        private final View.OnClickListener mOnClickListener;

        public TimeAdapter(List<TimeObj> times, View.OnClickListener onClickListener) {
            mTimes = times;
            mOnClickListener = onClickListener;
        }

        @NonNull
        @Override
        public TimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TimeHolder holder, int position) {
            TimeObj timeObj = mTimes.get(position);
            holder.bind(timeObj);
            holder.itemView.setTag(timeObj.getId());
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mTimes.size();
        }
    }

    private static class TimeHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;

        public TimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_time, parent, false));
            mNameTextView = itemView.findViewById(R.id.time_view);
        }

        public void bind(TimeObj timeObj) {
            mNameTextView.setText(timeObj.getNameTime());
        }
    }
}