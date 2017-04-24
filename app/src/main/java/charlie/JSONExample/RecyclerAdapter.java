package charlie.JSONExample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by charlie on 2017. 4. 24..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
    ArrayList<Item> mItems;

    public RecyclerAdapter(ArrayList<Item> items){
        mItems = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mItemNationTv.setText(mItems.get(position).getNation());
        holder.mItemNameTv.setText(mItems.get(position).getName());
        holder.mItemAddressTv.setText(mItems.get(position).getAddress());
        holder.mItemAgeTv.setText(mItems.get(position).getAge()+" 세");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView mItemNationTv;
        private TextView mItemNameTv;
        private TextView mItemAddressTv;
        private TextView mItemAgeTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemNationTv = (TextView) itemView.findViewById(R.id.itemNationTv);
            mItemNameTv = (TextView) itemView.findViewById(R.id.itemNameTv);
            mItemAddressTv = (TextView) itemView.findViewById(R.id.itemAddressTv);
            mItemAgeTv = (TextView) itemView.findViewById(R.id.itemAgeTv);
        }
    }
}
