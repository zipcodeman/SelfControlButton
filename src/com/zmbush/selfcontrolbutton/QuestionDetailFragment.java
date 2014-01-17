package com.zmbush.selfcontrolbutton;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zmbush.selfcontrolbutton.data.QuestionContent;

/**
 * A fragment representing a single Question detail screen. This fragment is
 * either contained in a {@link QuestionListActivity} in two-pane mode (on
 * tablets) or a {@link QuestionDetailActivity} on handsets.
 */
public class QuestionDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String     ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private QuestionContent.Question mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     * @param questionDetailActivity 
     */
    public QuestionDetailFragment() {
    }

    private Handler  h    = new Handler();
    private Runnable r;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = QuestionContent.ITEM_MAP.get(getArguments().getString(
                    ARG_ITEM_ID));
            
            this.getActivity().getActionBar().setTitle(mItem.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question_detail,
                container, false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        this.makeDecision();
    }

    public void makeDecision() {
        this.makeDecision(1000);
    }

    public void makeDecision(int delay) {
        final View no = this.getView().findViewById(R.id.no_image);
        final View yes = this.getView().findViewById(R.id.yes_image);
        final View loading = this.getView().findViewById(R.id.loading);

        
        final boolean ans = this.mItem.getAnswer();
        this.getActivity().getActionBar().setTitle(mItem.toString());

        no.setVisibility(View.GONE);
        yes.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);

        h.removeCallbacks(r);

        r = new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(View.GONE);
                if (ans) {
                    yes.setVisibility(View.VISIBLE);
                } else {
                    no.setVisibility(View.VISIBLE);
                }
            }
        };
        h.postDelayed(r, delay);
    }

    public CharSequence getTitle() {
        return mItem.getQuestion();
    }
}
