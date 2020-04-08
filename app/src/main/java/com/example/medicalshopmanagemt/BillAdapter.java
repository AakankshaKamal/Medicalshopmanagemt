package com.example.medicalshopmanagemt;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MessageViewHolder> {


    private List<Bill> BillList;

    public BillAdapter(List<Bill>userMessageList)
    {
        this.BillList=userMessageList;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView txt;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

          image=itemView.findViewById(R.id.img);
          txt=itemView.findViewById(R.id.time);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_bill,parent,false);
        return new MessageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        Bill bill=BillList.get(position);
      String url=bill.getQr();
      String time=bill.getTime();

      Picasso.get().load(url).into(holder.image);
      holder.txt.setText(time);

    }

    @Override
    public int getItemCount() {
        return BillList.size();
    }


    }

