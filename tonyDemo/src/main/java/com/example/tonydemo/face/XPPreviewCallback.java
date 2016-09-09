/*
 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tonydemo.face;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

final class XPPreviewCallback implements Camera.PreviewCallback {

    private static final String TAG = XPPreviewCallback.class.getSimpleName();

    private Handler previewHandler;
    private int previewMessage;

    XPPreviewCallback() {
    }

    void setHandler(Handler previewHandler, int previewMessage) {
        this.previewHandler = previewHandler;
        this.previewMessage = previewMessage;
    }

    /**
     * 摄像头预览的图片信息回调
     *
     * @param data
     * @param camera
     */
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Handler thePreviewHandler = previewHandler;
        if (thePreviewHandler != null) {
            //这里传送数据
            Message message = thePreviewHandler.obtainMessage(previewMessage,
                    200, 200, data);
            message.sendToTarget();
            previewHandler = null;
        } else {
            Log.d(TAG, "Got preview callback, but no handler or resolution available");
        }
    }

}
