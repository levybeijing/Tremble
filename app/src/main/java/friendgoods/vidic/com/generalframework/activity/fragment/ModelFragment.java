package friendgoods.vidic.com.generalframework.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.activity.ChallengeModelActivity;
import friendgoods.vidic.com.generalframework.activity.PKModelActivity;
import friendgoods.vidic.com.generalframework.activity.RelaxModelActivity;
import friendgoods.vidic.com.generalframework.activity.StoryModelActivity;
import friendgoods.vidic.com.generalframework.activity.base.BaseFragment;

public class ModelFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_model, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.tv_tiaozhan_option).setOnClickListener(this);
        view.findViewById(R.id.tv_gushi_option).setOnClickListener(this);
        view.findViewById(R.id.tv_pk_option).setOnClickListener(this);
        view.findViewById(R.id.tv_xiuxian_option).setOnClickListener(this);
    }

    @Override
    protected void onClick(int id) {
        switch (id){
            case R.id.tv_tiaozhan_option:
                startActivity(new Intent(getContext(),ChallengeModelActivity.class));
                break;
            case R.id.tv_gushi_option:
                startActivity(new Intent(getContext(),StoryModelActivity.class));
                break;
            case R.id.tv_pk_option:
                startActivity(new Intent(getContext(),PKModelActivity.class));
                break;
            case R.id.tv_xiuxian_option:
                startActivity(new Intent(getContext(),RelaxModelActivity.class));
                break;

        }
    }
}
