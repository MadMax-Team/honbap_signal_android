package com.example.HonbabSignal

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.text.Layout
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.HonbabSignal.databinding.ActivityKakaoLoginBinding

class KakaoLoginActivity : AppCompatActivity(){

    lateinit var binding: ActivityKakaoLoginBinding
    lateinit var webView: WebView
    lateinit var webViewLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.wView
        webView.setWebViewClient(WebViewClient()); // 웹뷰클라이언트
        //loadUrl ipv4 수정될 수 있음
        webView.loadUrl("http://15.164.98.165/auth/kakao")

        webViewLayout = binding.wViewLayout

        // 공통 설정
        webView.settings.run {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            setSupportMultipleWindows(true)
        }

        webView.webChromeClient = object: WebChromeClient() {

            /// ---------- 팝업 열기 ----------
            /// - 카카오 JavaScript SDK의 로그인 기능은 popup을 이용합니다.
            /// - window.open() 호출 시 별도 팝업 webview가 생성되어야 합니다.
            ///
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onCreateWindow(
                view: WebView,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: Message
            ): Boolean {

                // 웹뷰 만들기
                var childWebView = WebView(view.context)

                // 부모 웹뷰와 동일하게 웹뷰 설정
                childWebView.run {
                    settings.run {
                        javaScriptEnabled = true
                        javaScriptCanOpenWindowsAutomatically = true
                        setSupportMultipleWindows(true)
                    }
                    layoutParams = view.layoutParams
                    webViewClient = view.webViewClient
                    webChromeClient = view.webChromeClient
                }

                // 화면에 추가하기
                webViewLayout.addView(childWebView)
                // TODO: 화면 추가 이외에 onBackPressed() 와 같이
                //       사용자의 내비게이션 액션 처리를 위해
                //       별도 웹뷰 관리를 권장함
                //   ex) childWebViewList.add(childWebView)

                // 웹뷰 간 연동
                val transport = resultMsg.obj as WebView.WebViewTransport
                transport.webView = childWebView
                resultMsg.sendToTarget()

                return true
            }

            /// ---------- 팝업 닫기 ----------
            /// - window.close()가 호출되면 앞에서 생성한 팝업 webview를 닫아야 합니다.
            ///
            override fun onCloseWindow(window: WebView) {
                super.onCloseWindow(window)

                // 화면에서 제거하기
                webViewLayout.removeView(window)
                // TODO: 화면 제거 이외에 onBackPressed() 와 같이
                //       사용자의 내비게이션 액션 처리를 위해
                //       별도 웹뷰 array 관리를 권장함
                //   ex) childWebViewList.remove(childWebView)

            }
        }


    } //onCreate

}