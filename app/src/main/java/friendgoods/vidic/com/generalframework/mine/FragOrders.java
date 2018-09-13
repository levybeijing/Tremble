package friendgoods.vidic.com.generalframework.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import friendgoods.vidic.com.generalframework.R;

public class FragOrders extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rv = view.findViewById(R.id.rv_orders);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        AdapterOrders adapter=new AdapterOrders(getContext());
        rv.setAdapter(adapter);
    }
}
