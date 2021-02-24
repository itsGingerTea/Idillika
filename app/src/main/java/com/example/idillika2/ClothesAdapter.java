package com.example.idillika2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {

    private List<Item> items;
    private LikesCallback callback;

    public ClothesAdapter(List<Item> items, LikesCallback callback) {
        this.items = items;
        this.callback = callback;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clothes_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public String id;
        public ImageView imageView;
        public TextView textViewTitle;
        public TextView textViewPrice;
        public TextView textViewBrand;
        public CheckBox buttonFavorite;
        Item itemId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img_item);
            textViewTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            textViewPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
            textViewBrand = (TextView) itemView.findViewById(R.id.tv_item_brand);
            buttonFavorite = (CheckBox) itemView.findViewById(R.id.tbtn_fav);
            buttonFavorite.setOnCheckedChangeListener((compoundButton, b) -> {
                if (compoundButton.isPressed()) {
                    //сюда передается айди лайкнутого элемента и сама лайкнутость
                    callback.onLikeClick(b, getBindingAdapterPosition());
                }

            });

        }

        public void bind(Item item) {
            itemId = item;
            Picasso.get()
                    .load(item.getImage())
                    .resize(400, 600)
                    .centerCrop()
                    .into(imageView);

            textViewTitle.setText(item.getTitle());
            textViewPrice.setText(item.getPrice() + "₽");
            textViewBrand.setText(item.getBrand());

            buttonFavorite.setChecked(item.isFavoriteInPrefs());

        }


    }
}
