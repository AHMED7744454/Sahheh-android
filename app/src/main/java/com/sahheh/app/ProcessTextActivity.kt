package com.sahheh.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class ProcessTextActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = intent?.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)?.toString()

        if (text.isNullOrEmpty()) {
            finish()
            return
        }

        val fixed = KeyboardMap.fix(text)
        val readOnly = intent?.getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, true) ?: true

        if (readOnly) {
            // النص مش قابل للاستبدال المباشر (زي صفحة ويب للقراءة بس)
            // فبنعرض خيار مشاركة/نسخ النص المصحح بدل ما نستبدله
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, fixed)
            }
            startActivity(Intent.createChooser(shareIntent, "النص المصحح"))
        } else {
            val result = Intent()
            result.putExtra(Intent.EXTRA_PROCESS_TEXT, fixed as CharSequence)
            setResult(RESULT_OK, result)
        }

        finish()
    }
}
