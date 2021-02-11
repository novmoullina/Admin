package com.noviam.admin.kuliner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.noviam.admin.R

class DetilKulinerAdapter (
    val detilContext: Context,
    val layoutResId: Int,
    val detilList: MutableList<DetilKuliner>
) : ArrayAdapter<DetilKuliner>(detilContext, layoutResId, detilList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(detilContext)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val tvAlamat = view.findViewById<TextView>(R.id.ou_alamat)
        val tvDeskripsi = view.findViewById<TextView>(R.id.ou_deskripsi)

        val detil = detilList[position]
        tvAlamat.text = detil.alamat
        tvDeskripsi.text = detil.deskripsi


        return view
    }

}