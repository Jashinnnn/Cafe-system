package com.example.capstone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class prodAdapter extends FirebaseRecyclerAdapter <prodModel,prodAdapter.myViewHolder>{
    public prodAdapter(@NonNull FirebaseRecyclerOptions<prodModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull prodModel model) {
        holder.Name.setText(model.getName());
        holder.Quantity.setText(model.getQuantity());
        holder.Stocks.setText(model.getStocks());
        holder.Utd.setText(model.getUtd());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.prod_popup))
                        .setExpanded(true, 1200)
                        .create();


                View view = dialogPlus.getHolderView();

                EditText Name = view.findViewById(R.id.popName);
                EditText Quantity = view.findViewById(R.id.popQuantity);
                EditText Stocks = view.findViewById(R.id.popStocks);
                EditText Utd = view.findViewById(R.id.popUtd);

                Button updateBtn = view.findViewById(R.id.updateBtn);

                Name.setText(model.getName());
                Quantity.setText(model.getQuantity());
                Stocks.setText(model.getStocks());
                Utd.setText(model.getUtd());

                dialogPlus.show();

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Name",Name.getText().toString());
                        map.put("Quantity",Quantity.getText().toString());
                        map.put("Stocks",Stocks.getText().toString());
                        map.put("Utd",Utd.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("product")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.itemView.getContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss(); // Dismiss dialog after successful update
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.itemView.getContext(), "Failed to update: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data cannot be undone");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("product")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });

                 builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                     Toast.makeText(holder.itemView.getContext(),"Cancelled", Toast.LENGTH_SHORT).show();
                     }
                 });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_products,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder  extends RecyclerView.ViewHolder{
        TextView Name,Quantity,Stocks,Utd;
        Button btnEdit,btnDelete;
        FloatingActionButton floatingActionButton;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.nametxt);
            Quantity=itemView.findViewById(R.id.Quantitytxt);
            Stocks =itemView.findViewById(R.id.Stockstxt);
            Utd =itemView.findViewById(R.id.Utdtxt);

            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);


        }
    }
}
