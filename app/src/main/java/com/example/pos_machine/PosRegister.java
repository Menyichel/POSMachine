package com.example.pos_machine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pos_machine.databinding.FragmentPosRegisterBinding;

public class PosRegister extends Fragment {

    private EditText code, item , unit , unit_price;
    private Button BtnSave , resetBtn;
    private FragmentPosRegisterBinding fragmentPosRegisterBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentPosRegisterBinding = fragmentPosRegisterBinding.inflate(getLayoutInflater());
        View n = fragmentPosRegisterBinding.getRoot();
        code = fragmentPosRegisterBinding.codeEditText;
        item = fragmentPosRegisterBinding.itemEditText;
        unit = fragmentPosRegisterBinding.unitEditText;
        unit_price = fragmentPosRegisterBinding.unitPriceEditText;
        BtnSave = fragmentPosRegisterBinding.BtnSave;
        resetBtn=fragmentPosRegisterBinding.resetBtn;
         DB db;
        db = new DB(getContext());
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codeValue = code.getText().toString();
                String itemValue = item.getText().toString();
                String unitValue = unit.getText().toString();
                String unitPriceValue = unit_price.getText().toString();

                if(codeValue.isEmpty() || itemValue.isEmpty() || unitValue.isEmpty() || unitPriceValue.isEmpty()){
                    Toast.makeText(getActivity(), "Please fill all values", Toast.LENGTH_SHORT).show();
                } else {
                    int unitIntValue = Integer.parseInt(unitValue);

                    Boolean isInserted = db.insertInfo(codeValue, itemValue, unitIntValue, unitPriceValue);

                    if (isInserted == true) {
                        Toast.makeText(getActivity(), "New Data is inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Data is not inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code.setText("");
                item.setText("");
                unit.setText("");
                unit_price.setText("");
            }
        });



        // Inflate the layout for this fragment
        return n;
    }



}

