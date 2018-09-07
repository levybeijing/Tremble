package friendgoods.vidic.com.generalframework.activity.fragment;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {



    protected  void initClickListener(View ...views){
        for(View view : views){
            view.setOnClickListener(this);
        }
    }

    public interface FragmentTask<T> {
        T doInBackground();
        void postResult(T t);
    }

    @Override
    public void onClick(View v) {
        onClick(v.getId());
    }

    protected void onClick(int id){}

    //public abstract void fitScreen();

    public <T> void execute(final FragmentTask<T> task) {
        new AsyncTask<Void, Void, T>() {
            @Override
            protected T doInBackground(Void... params) {
                if (getActivity()==null||getActivity().isFinishing()) {
                    return null;
                }
                return task.doInBackground();
            }

            @Override
            protected void onPostExecute(T t) {
                super.onPostExecute(t);
                if (getActivity()==null||getActivity().isFinishing()) {
                    return;
                }
                task.postResult(t);
            }
        }.execute();
    }

}
