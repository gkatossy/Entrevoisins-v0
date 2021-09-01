package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    @BindView(R.id.imageButtonBack)
    ImageButton mButtonBack;

    @BindView(R.id.floatingActionButtonFav)
    FloatingActionButton mImageButtonFav;

    @BindView(R.id.image_avatar)
    ImageView mImageViewAvatar;

    @BindView(R.id.nameProfil)
    TextView mTextViewNameTitle;

    @BindView(R.id.activity_name_text)
    TextView mTextViewNameText;

    @BindView(R.id.activity_phone_text)
    TextView mTextViewPhoneText;

    @BindView(R.id.activity_social_link)
    TextView mTextViewSocialLink;

    @BindView(R.id.activity_address_text)
    TextView mTextViewAddress;

    @BindView(R.id.activity_aboutMe_content)
    TextView mTextViewAboutMe;

    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind( this);
        mApiService = DI.getNeighbourApiService();

        showInfos();
        addToFavorites();
        backButton();

    }

    /**
     * show neighbour's details using get extra
     */
    private void showInfos() {
        if (getIntent().hasExtra("Neighbour")) {
            Neighbour neighbour = getIntent().getExtras().getParcelable("Neighbour");

            Glide.with(this)
                    .load(neighbour.getAvatarUrl())
                    .into(mImageViewAvatar);
            mTextViewNameText.setText(neighbour.getName());
            mTextViewNameTitle.setText(neighbour.getName());
            mTextViewSocialLink.setText("www.facebook.fr/" + neighbour.getName());
            mTextViewPhoneText.setText(neighbour.getPhone());
            mTextViewAddress.setText(neighbour.getAddress());
            mTextViewAboutMe.setText(neighbour.getAboutMe());
        }
    }

    /**
     * activate or deactivate favorite button onClick.
     * set/unset neighbour as favorite and add/delete to/from favorites list.
     */

    private void addToFavorites() {
        if (getIntent().hasExtra("Neighbour")) {
            Neighbour neighbour = getIntent().getParcelableExtra("Neighbour");

            if (mApiService.getFavorite().contains(neighbour)) {
                mImageButtonFav.setImageResource(R.drawable.ic_star_white_24dp);
            }

            mImageButtonFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mApiService.getFavorite().contains(neighbour)) {
                        mApiService.addFavorite(neighbour);

                        mImageButtonFav.setImageResource(R.drawable.ic_star_white_24dp);
                        Toast.makeText(NeighbourDetailActivity.this, "added to favorites", Toast.LENGTH_LONG).show();
                    } else {
                        mApiService.deleteFavorite(neighbour);
                        mImageButtonFav.setImageResource(R.drawable.ic_star_border_white_24dp);
                        Toast.makeText(NeighbourDetailActivity.this, " deleted from favorites! ", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

    /**
     * back Button
     */
    private void backButton() {

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}