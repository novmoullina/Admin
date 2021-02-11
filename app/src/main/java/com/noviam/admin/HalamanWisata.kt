package com.noviam.admin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.noviam.admin.kuliner.activity_2
import com.noviam.admin.kuliner.activity_4


class HalamanWisata : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val i = inflater.inflate(R.layout.halaman_wisata,
                container, false)
        val panggilkuliner: Button = i.findViewById(R.id.btntambahkuliner)

        panggilkuliner.setOnClickListener {
            val intent = Intent(activity, activity_4::class.java)
            startActivity(intent)
        }
        return i
    }

}