package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import com.example.myapplication.R


class WebViewFragment : Fragment() {


    private lateinit var webView: WebView
    private val callback: OnBackPressedCallback =
        object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    activity?.finish()
                }
            }
        }

    companion object {
        fun newInstance(): WebViewFragment {
            val fragment = WebViewFragment()
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = requireView().findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("http://www.google.com")
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}