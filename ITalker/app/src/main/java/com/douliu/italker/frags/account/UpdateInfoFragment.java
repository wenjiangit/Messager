package com.douliu.italker.frags.account;


import android.content.Intent;
import android.graphics.Bitmap;
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
                        File portraitTempFile = App.getPortraitTempFile();

                        Uri sourceUri = Uri.fromFile(new File(path));
                        Uri destinationUri = Uri.fromFile(portraitTempFile);

                        UCrop.Options options = new UCrop.Options();
                        //设置图片压缩格式
                        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                        //设置图片压缩精度
                        options.setCompressionQuality(96);

                        UCrop.of(sourceUri, destinationUri)
                                .withAspectRatio(1, 1)//设置比例
                                .withMaxResultSize(520, 520)//最大宽高
                                .withOptions(options)//相关配置
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
    }
}
