package fr.formation.articlefragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import fr.formation.articlefragment.ListeFragment.OnArticleSelectedListener;

public class MainActivity extends FragmentActivity implements OnArticleSelectedListener {

	List<Article> listArticle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		listArticle = construireListeArticle();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FrameLayout frame = (FrameLayout) findViewById(R.id.fragment_container);
		if (frame != null){
			ListeFragment listeFragment = new ListeFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, listeFragment).commit();
		}
		
		
		
		
	}

	@Override
	public void onArticleSelected(int position) {
		DetailFragment detailFragment = (DetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragmentdetail);
		if (detailFragment != null){
			detailFragment.setArticle(listArticle.get(position));
		}else{
			detailFragment = new DetailFragment();
			Bundle args = new Bundle();
			args.putString("article", listArticle.get(position).getLibelle());
			detailFragment.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

	        // Replace whatever is in the fragment_container view with this fragment,
	        // and add the transaction to the back stack so the user can navigate back
	        transaction.replace(R.id.fragment_container, detailFragment);
	        transaction.addToBackStack(null);

	        // Commit the transaction
	        transaction.commit();
		}

	}
	

	private List<Article> construireListeArticle(){
		List<Article> listArticle = new ArrayList<Article>();
		listArticle.add(new Article(1, "Article 1"));
		listArticle.add(new Article(2, "Article 2"));
		listArticle.add(new Article(3, "Article 3"));
		listArticle.add(new Article(4, "Article 4"));
		listArticle.add(new Article(5, "Article 5"));
		listArticle.add(new Article(6, "Article 6"));
		return listArticle;
	}
}
