package wangbo.bawei.com.mytbdome.shoping.model;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import wangbo.bawei.com.mytbdome.shoping.presenter.interfac.ICartPresenter;
import wangbo.bawei.com.mytbdome.shoping.util.CommonUtils;
import wangbo.bawei.com.mytbdome.shoping.util.OkHttp3Util;

/**
 * Created by Dash on 2017/12/12.
 */
public class CartModel {
    private ICartPresenter iCartPresenter;

    public CartModel(ICartPresenter iCartPresenter) {
        this.iCartPresenter = iCartPresenter;
    }

    public void getCartData(final String cartUrl) {
        //获取数据
        OkHttp3Util.doGet(cartUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(cartUrl,e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String json = response.body().string();

                    //返回数据到主线程
                    CommonUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            iCartPresenter.getSuccessCartJson(json);
                        }
                    });

                }
            }
        });

    }
}
