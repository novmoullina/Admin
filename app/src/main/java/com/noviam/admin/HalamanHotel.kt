package com.noviam.admin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

import com.noviam.admin.kuliner.activity_3


class HalamanHotel :  Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val i = inflater.inflate(R.layout.halaman_hotel,
                container, false)
        val panggilkuliner: Button = i.findViewById(R.id.btntambahhotel)

        panggilkuliner.setOnClickListener {
            val intent = Intent(activity, activity_3::class.java)
            startActivity(intent)
        }
        return i
    }

}
