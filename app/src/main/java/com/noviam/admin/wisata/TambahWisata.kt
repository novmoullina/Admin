package com.noviam.admin.kuliner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.noviam.admin.R

import android.content.Intent
import kotlinx.android.synthetic.main.activity_tambah_detil_kuliner.*

class TambahWisata : AppCompatActivity(){
    private lateinit var judul : TextView
    private lateinit var edtNama : EditText
    private lateinit var edtAlamat : EditText
    private lateinit var edtDeskripsi : EditText
    private lateinit var btnSimpan : Button
    private lateinit var lvTambahDetil : ListView
    private lateinit var detilList : MutableList<DetilWisata>
    private lateinit var ref : DatabaseReference

    companion object{
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAMA ="extra_nama"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_detil_wisata)

        judul = findViewById(R.id.txt_judul_detil)
        edtNama = findViewById(R.id.edtnama)
        edtAlamat = findViewById(R.id.edtalamat)
        edtDeskripsi = findViewById(R.id.edtdeksripsi)
        btnSimpan = findViewById(R.id.btn_tambahDetil)
        lvTambahDetil = findViewById(R.id.lv_tambahDetil)

        val id = intent.getStringExtra("EXTRA_ID")
        val nama = intent.getStringExtra("EXTRA_NAMA")

        detilList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("detil anggota")
                .child(id!!)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    detilList.clear()
                    for (a in snapshot.children){
                        val detil = a.getValue(DetilWisata::class.java)
                        if (detil != null){
                            detilList.add(detil)
                        }
                    }

                    val adapter = DetilWisataAdapter(this@TambahWisata,
                            R.layout.activity_item_detil_wisata, detilList)
                    lvTambahDetil.adapter = adapter

                    println("Output : " + detilList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        btnSimpan.setOnClickListener {
            simpanDetil()
        }
    }


    private fun simpanDetil(){
        val nama = edtnama.text.toString().trim()
        val alamat = edtalamat.text.toString()
        val deskripsi = edtdeksripsi.text.toString()

        if(nama.isEmpty()){
            edtnama.error = "Isi nama terlebih dahulu"
            return
        }
        if(alamat.isEmpty()){
            edtalamat.error = "Isi alamat terlebih dahulu"
            return
        }
        if(deskripsi.isEmpty()){
            edtdeksripsi.error = "Isi deskripsi terlebih dahulu"
            return
        }
        val detilId = ref.push().key

        val detil = DetilWisata(detilId!!,  alamat, deskripsi)

        ref.child(detilId).setValue(detil).addOnCompleteListener {
            Toast.makeText(applicationContext, "Informasi tambahan berhasil ditambahkan",
                    Toast.LENGTH_SHORT)
                    .show()
        }
    }

}