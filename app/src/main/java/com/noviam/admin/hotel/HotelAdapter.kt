package com.noviam.admin.kuliner

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import com.noviam.admin.R

class HotelAdapter (
        val anggotaContext: Context,
        val layoutResId: Int,
        val anggotaList: List<variabel_R_hotel>
) : ArrayAdapter<variabel_R_hotel>(anggotaContext, layoutResId, anggotaList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(anggotaContext)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val o_nama: TextView = view.findViewById(R.id.ou_nama)
        val o_alamat: TextView = view.findViewById(R.id.ou_alamat)
        val o_deskripsi: TextView = view.findViewById(R.id.ou_deskripsi)
        val imgEdit: ImageView = view.findViewById(R.id.icn_edit)

        val anggota = anggotaList[position]

        imgEdit.setOnClickListener{
            updateDialog(anggota)
        }
        o_nama.text ="Nama : " + anggota.nama
        o_alamat.text ="Alat : " + anggota.alamat
        o_deskripsi.text ="Bahan : " + anggota.deskripsi

        return view

    }

    private fun updateDialog(anggota: variabel_R_hotel){
        val builder = AlertDialog.Builder(anggotaContext)
        builder.setTitle("Update Data")
        val inflater = LayoutInflater.from(anggotaContext)
        val view = inflater.inflate(R.layout.activity_update_hotel, null)

        val  edtNama = view.findViewById<EditText>(R.id.upNama)
        val  edtAlamat = view.findViewById<EditText>(R.id.upAlat)
        val  edtDeksripsi= view.findViewById<EditText>(R.id.upBahan)

        edtNama.setText(anggota.nama)
        edtAlamat.setText(anggota.alamat)
        edtDeksripsi.setText(anggota.deskripsi)

        builder.setView(view)

        builder.setPositiveButton("Ubah") {p0, p1 ->
            val dbAnggota = FirebaseDatabase.getInstance().getReference("anggota")
            val nama = edtNama.text.toString().trim()
            val alamat = edtAlamat.text.toString().trim()
            val deskripsi = edtDeksripsi.text.toString()

            if (nama.isEmpty() or alamat.isEmpty() or deskripsi.isEmpty()) {
                Toast.makeText(anggotaContext, "Isi data secara lengkap tidak boleh kosong",
                        Toast.LENGTH_SHORT)
                        .show()
                return@setPositiveButton
            }

            val anggota = variabel_R_hotel(anggota.id, nama, alamat, deskripsi )

            dbAnggota.child(anggota.id).setValue(anggota)
            Toast.makeText(anggotaContext, "Data berhasil di update", Toast.LENGTH_SHORT)
                    .show()
        }
        builder.setNeutralButton("Batal") { p0, p1 -> }
        builder.setNegativeButton("Hapus") {p0, p1 ->
            val dbAnggota = FirebaseDatabase.getInstance().getReference( "anggota")
                    .child(anggota.id)
            val dbDetil = FirebaseDatabase.getInstance().getReference( " detil anggota")
                    .child(anggota.id)

            dbAnggota.removeValue()
            dbDetil.removeValue()

            Toast.makeText(anggotaContext, "Data berhasil di hapus", Toast.LENGTH_SHORT)
                    .show()
        }

        val alert = builder.create()
        alert.show()

    }
}