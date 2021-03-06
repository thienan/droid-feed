package com.droidfeed.ui.binding

import android.text.format.DateUtils
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import com.droidfeed.R
import com.droidfeed.util.extention.loadImage
import java.util.*

@BindingAdapter("imageResource")
fun loadImage(imageView: ImageView, url: Any) {
    imageView.loadImage(url)
}

@BindingAdapter("avdImageResource")
fun avdImageResource(imageView: ImageView, avdImageResource: Int) {
    imageView.setImageResource(avdImageResource)
}

@BindingAdapter("visibilityToggle")
fun visibilityToggle(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("contentProgressBarVisibility")
fun contentProgressBarVisibility(view: ContentLoadingProgressBar, show: Boolean) {
    if (show) view.show() else view.hide()
}

@BindingAdapter("loadHtml")
fun loadHtml(webView: WebView, htmlContent: String) {
    if (htmlContent.isNotBlank()) {
        webView.loadData(htmlContent, "text/html", "UTF-8")
    }
}

@BindingAdapter("relativeDate")
fun setRelativeTime(view: TextView, timeStamp: Long) {
    view.text = DateUtils.getRelativeTimeSpanString(
        timeStamp,
        Calendar.getInstance(TimeZone.getDefault()).timeInMillis,
        DateUtils.SECOND_IN_MILLIS
    )
}

@BindingAdapter(value = ["publisher", "timestamp"], requireAll = true)
fun setRelativeDate(view: TextView, publisher: String?, timestamp: Long?) {
    val date = if (timestamp == null) {
        ""
    } else {
        DateUtils.getRelativeTimeSpanString(
            timestamp,
            Calendar.getInstance(TimeZone.getDefault()).timeInMillis,
            android.text.format.DateUtils.SECOND_IN_MILLIS,
            DateUtils.FORMAT_ABBREV_ALL
        )
    }

    view.text = view.context.getString(R.string.publisher_time, publisher ?: "", date.toString())
}