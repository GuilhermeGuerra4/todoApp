package com.contmesh.todo;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class ModalDialog extends BottomSheetDialogFragment {
    private BottomSheetListener mListener;
    private EditText input;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.modal_layout, container, false);
        Button add_task = view.findViewById(R.id.add_task);
        input = view.findViewById(R.id.input);

        add_task.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mListener.task_added(String.valueOf(input.getText()));
                dismiss();
            }
        });

        return view;
    }

    public interface BottomSheetListener{
        void task_added(String task_name);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (BottomSheetListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement BottomSheetListener");
        }
    }
}