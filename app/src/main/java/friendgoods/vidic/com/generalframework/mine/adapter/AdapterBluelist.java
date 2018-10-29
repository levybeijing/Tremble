package friendgoods.vidic.com.generalframework.mine.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework._idle.BluetoothListActivity;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPosition;

public class AdapterBluelist extends RecyclerView.Adapter {
    private Context context;
    private List<BluetoothDevice> list;

    public AdapterBluelist(BluetoothListActivity context_) {
        context=context_;
    }

    public void setData(List<BluetoothDevice> list_) {
        list=list_;
        notifyDataSetChanged();
    }

    private OnItemClickListenerPosition itemClickListenerPosition;
    public void setOnItemClickListenerPosition(OnItemClickListenerPosition itemClickListenerPosition_){
        itemClickListenerPosition=itemClickListenerPosition_;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_bluelist, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).tv_name.setText(list.get(position).getName());
        ((MyViewHolder)holder).tv_mac.setText(list.get(position).getAddress());
        ((MyViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListenerPosition.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name,tv_mac;
        public MyViewHolder(View view)
        {
            super(view);
            tv_name= view.findViewById(R.id.tv_name_blueitem);
            tv_mac= view.findViewById(R.id.tv_mac_blueitem);
        }
    }
}
