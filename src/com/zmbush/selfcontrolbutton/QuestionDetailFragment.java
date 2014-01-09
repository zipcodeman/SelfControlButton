package com.zmbush.selfcontrolbutton;

import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zmbush.selfcontrolbutton.dummy.DummyContent;

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
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private DummyContent.DummyItem mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public QuestionDetailFragment() {
	}
	
	private Random rand = new Random();
	private Handler h = new Handler();
	private Runnable r;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
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
		this.makeDecision(0);
	}
	
	public void makeDecision(int delay) {
		final ImageView no = (ImageView)this.getView().findViewById(R.id.no_image);
		final ImageView yes = (ImageView)this.getView().findViewById(R.id.yes_image);
		
		final int selection = rand.nextInt(10);
		
		no.setVisibility(View.GONE);
		yes.setVisibility(View.GONE);
		
		h.removeCallbacks(r);

		r = new Runnable() {
			@Override
			public void run() {
				if (selection == 0) {
					yes.setVisibility(View.VISIBLE);
				} else {
					no.setVisibility(View.VISIBLE);
				}
			}
		};
		h.postDelayed(r, delay);
	}
}
