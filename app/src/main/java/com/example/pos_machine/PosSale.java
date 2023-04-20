package com.example.pos_machine;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pos_machine.databinding.FragmentPosSaleBinding;

import java.util.ArrayList;
import java.util.List;


public class PosSale extends Fragment {

    private EditText sale_code, sale_item, quantity, sale_total, sale_tax , sale_quantity2;
    private Button searchBtn, addBtn, paidBtn , resetBtn;
    private TextView textView;
    private RecyclerView recycler_view_card;
    private FragmentPosSaleBinding fragmentPosSaleBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        fragmentPosSaleBinding=fragmentPosSaleBinding.inflate(getLayoutInflater());
        View nv = fragmentPosSaleBinding.getRoot();
        sale_code = fragmentPosSaleBinding.saleCodeEditText;  sale_item=fragmentPosSaleBinding.saleItemEditText;
        quantity=fragmentPosSaleBinding.saleQuantityEditText;
        sale_total=fragmentPosSaleBinding.saleTotalEditText;  sale_tax=fragmentPosSaleBinding.saleTaxEditText;
        sale_quantity2=fragmentPosSaleBinding.saleQuantity2EditText;
        searchBtn =fragmentPosSaleBinding.searchBtn;
        addBtn=fragmentPosSaleBinding.addBtn;
        paidBtn=fragmentPosSaleBinding.paidBtn;
        resetBtn=fragmentPosSaleBinding.resetBtn;
        recycler_view_card=fragmentPosSaleBinding.recyclerViewCard;
        //textView=fragmentPosSaleBinding.TextDisplay;

        RecyclerView mRecyclerView;
        mRecyclerView = fragmentPosSaleBinding.recyclerViewCard;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        List<Model> regCardList = new ArrayList<>();

        ItemAdapter adapter = new ItemAdapter(regCardList);
        mRecyclerView.setAdapter(adapter);

        String sc=sale_code.getText().toString();

        DB db;
        db = new DB(getContext());

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = db.searchItem(sale_code.getText().toString());
                if (itemName != null) {
                    sale_item.setText(itemName);
                }
                else {
                    sale_item.setText("");
                    Toast.makeText(getContext(), "Item not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final double[] totalval = {0};
        final double[] saleVal = {0};
        final double[] payableVal = {0};


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int q = Integer.parseInt(quantity.getText().toString()) ;
                String c =sale_code.getText().toString();
                String itemName = db.searchItem(c);
                int quen = Integer.parseInt(db.addItem1(c));
                double price = Double.parseDouble(db.addItem2(c)) ;
                if (itemName != null) {
                    if(q>quen){
                        Toast.makeText(getContext(), "Not Enough Quentity", Toast.LENGTH_SHORT).show();
                    }

                    regCardList.add(new Model(c,itemName,q,price,quen));
                    Model md = new Model(c,itemName,q,price,quen);

                    adapter.notifyItemInserted(regCardList.size() - 1);
                                totalval[0] =totalval[0]+price*q;
                                saleVal[0] =totalval[0]*0.15;
                                payableVal[0] =totalval[0]+saleVal[0];
                    sale_total.setText(""+totalval[0]);
                    sale_tax.setText(""+saleVal[0]);
                    sale_quantity2.setText(""+payableVal[0]);
                }
                else {
                    sale_item.setText("");
                    Toast.makeText(getContext(), "Item not found", Toast.LENGTH_SHORT).show();
                }

            }
        });


        paidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sale_code.setText("");    sale_item.setText("");   quantity.setText("");
                sale_total.setText("");   sale_tax.setText("");    sale_quantity2.setText("");
                saleVal[0]=0;  totalval[0]=0;  payableVal[0]=0;
                regCardList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        return nv;
    }
}

