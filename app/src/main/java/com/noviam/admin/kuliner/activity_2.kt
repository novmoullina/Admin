package com.noviam.admin.kuliner

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.input_kuliner.*
import com.noviam.admin.R

    class activity_2 : AppCompatActivity(), View.OnClickListener {

        private lateinit var edNama: EditText
        private lateinit var edAlamat: EditText
        private lateinit var edDeskripsi: EditText
        private lateinit var btnSimpan: Button
        private lateinit var listData: ListView
        private lateinit var ref: DatabaseReference
        private lateinit var anggotaList : MutableList<variabel_R_kuliner>

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.input_kuliner)

            ref = FirebaseDatabase.getInstance().getReference("anggota")

            edNama = findViewById(R.id.edt_nama)
            edAlamat = findViewById(R.id.edtalamat)
            edDeskripsi = findViewById(R.id.edtdeksripsi)
            btnSimpan = findViewById(R.id.btn_simpan)
            listData = findViewById(R.id.lv_hasil)

            btnSimpan.setOnClickListener(this)
            anggotaList = mutableListOf()

            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (a in snapshot.children){
                            val anggota = a.getValue(variabel_R_kuliner::class.java)
                            if (anggota !=null){
                                anggotaList.add(anggota)
                            }
                        }

                        val adapter = KulinerAdapter( this@activity_2,
                            R.layout.activity_item_kuliner, anggotaList)
                        listData.adapter = adapter

                        println("Output : " +anggotaList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@activity_2, "error: "+error, Toast.LENGTH_LONG).show()
                }
            })

            listData.setOnItemClickListener { parent, view, position, id ->
                val anggota = anggotaList.get(position)
                val intent = Intent(this@activity_2, TambahKuliner::class.java)
                intent.putExtra(TambahKuliner.EXTRA_ID, anggota.id)
                intent.putExtra(TambahKuliner.EXTRA_NAMA, anggota.nama)
                startActivity(intent)
            }
        }

        override fun onClick(v: View?) {
            simpanData()
        }

        private fun simpanData(){
            val nama = edNama.text.toString().trim()
            val alamat = edAlamat.text.toString()
            val deskripsi= edDeskripsi.text.toString()


            if (nama.isEmpty()or alamat.isEmpty() or deskripsi.isEmpty()){
                Toast.makeText( this, "Isi data secara lengkap tidak boleh kosong",
                    Toast.LENGTH_SHORT).show()
                return
            }

            val anggotaId = ref.push().key
            val anggota = variabel_R_kuliner(anggotaId!!, nama, alamat, deskripsi)

            ref.child(anggotaId).setValue(anggota).addOnCompleteListener {
                Toast.makeText(
                    applicationContext, "Data berhasil ditambahkan",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
