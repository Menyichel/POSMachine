package com.example.pos_machine;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

        private List<Model> mCardList;

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public LinearLayout mCardView;
            public TextView mItemTextView;
            public TextView mQxUTextView;
            public TextView mTotalTextView;

            public ViewHolder(View itemView) {
                super(itemView);

                mCardView = itemView.findViewById(R.id.cardViewCart);
                mItemTextView = itemView.findViewById(R.id.regItemCard);
                mQxUTextView = itemView.findViewById(R.id.itemQxU);
                mTotalTextView = itemView.findViewById(R.id.itemUnitCard);
            }
        }

        public ItemAdapter(List<Model> cardList) {
            mCardList = cardList;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Model currentItem = mCardList.get(position);

            holder.mItemTextView.setText(String.format("%s%s", currentItem.getCode(), currentItem.get_item()));
            holder.mQxUTextView.setText(String.format("%dx%s", currentItem.getQuantity(), currentItem.getUnit_price()));
            holder.mTotalTextView.setText(String.valueOf(currentItem.getCost()));
        }


        @Override
        public int getItemCount() {
            return mCardList.size();
        }
    }