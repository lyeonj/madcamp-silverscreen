package com.example.silverscreen.ui.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.silverscreen.databinding.FragmentMapBinding

class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val root = binding.root

        val webView = binding.webviewMap

        // 1) 자바스크립트 허용
        val settings: WebSettings = webView.settings
        binding.webviewMap.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled  = true
        }
        binding.webviewMap.webViewClient   = WebViewClient()
        binding.webviewMap.webChromeClient = WebChromeClient()
        binding.webviewMap.loadUrl("http://143.248.171.100:3000/map.html")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
