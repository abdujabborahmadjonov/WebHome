package dev.abdujabbor.webhome

import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Adapter
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import dev.abdujabbor.webhome.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.security.ProtectionDomain

class MainActivity : AppCompatActivity() {


    lateinit var progressDialog:ProgressDialog
   lateinit var adapter :Adapter
    var url =""
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url =  "https://www.google.com/search?q="
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Downloading")
        progressDialog.setMessage("Waiting...")

        binding.myWeb.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressDialog.show()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressDialog.cancel()
                super.onPageFinished(view, url)
            }
        }

        binding.search.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding.myWeb.loadUrl(url+newText)
                return true
            }

        })


    }
}