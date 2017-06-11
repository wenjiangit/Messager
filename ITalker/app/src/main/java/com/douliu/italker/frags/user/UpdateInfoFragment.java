package com.douliu.italker.frags.user;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.douliu.italker.App;
import com.douliu.italker.R;
import com.douliu.italker.frags.media.GalleryFragment;
import com.example.commom.app.BaseFragment;
import com.example.commom.widget.PortraitImageView;
import com.example.factory.Factory;
import com.example.factory.net.UploadHelper;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateInfoFragment extends BaseFragment {

    private static final String TAG = "UpdateInfoFragment";

    @BindView(R.id.im_portrait)
    PortraitImageView mImPortrait;

    public UpdateInfoFragment() {
        // Required empty public constructor


    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_update_info;
    }

    @OnClick(R.id.im_portrait)
    void onPortraitClick() {
        new GalleryFragment()
                .setListener(new GalleryFragment.OnImageSelectedListener() {
                    @Override
                    public void onImageSelect(String path) {
                        String portraitPath = App.getPortraitPath();
                        Uri sourceUri = Uri.fromFile(new File(path));
                        Uri destinationUri = Uri.fromFile(new File(portraitPath));

                        UCrop.of(sourceUri, destinationUri)
                                .withAspectRatio(1, 1)
                                .withMaxResultSize(520, 520)
                                .start(getActivity());

                    }
                }).show(getChildFragmentManager(), GalleryFragment.class.getName());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri != null) {
                loadPortrait(resultUri);
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Log.e(TAG, "cropError: ", cropError);
        }
    }

    private void loadPortrait(Uri resultUri) {
        Glide.with(getActivity())
                .asBitmap()
                .load(resultUri)
                .apply(RequestOptions.centerCropTransform())
                .into(mImPortrait);

        final String localPath = resultUri.getPath();

        Log.i(TAG, "localPath: " + localPath);

        Factory.runOnUiAsync(new Runnable() {
            @Override
            public void run() {
                String url = UploadHelper.uploadPortrait(localPath);
                Log.i(TAG, "服务器地址: " + url);
            }
        });

    }
}
