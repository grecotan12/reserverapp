package com.exampl.reserver;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Confirmation extends DialogFragment {

    public interface OnYesClick {
        void onYesClick();
    }

    private OnYesClick mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireActivity())
                .setTitle(R.string.confirm)
                .setMessage(R.string.confirm_selection)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    // User clicked Yes
                    mListener.onYesClick();
                })
                .setNegativeButton(R.string.no, (dialog, id) -> {
                    // User clicked No
                })
                .create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnYesClick) context;
    }
}
