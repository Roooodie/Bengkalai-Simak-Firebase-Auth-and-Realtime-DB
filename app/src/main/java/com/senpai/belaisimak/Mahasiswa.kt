package com.senpai.belaisimak

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Mahasiswa{
    var id: String = ""
    var nama: String? = null
    var nim: String? = null
    var kelas: String? = null
    var jurusan: String? = null
    var studi: String? = null
    var kompen: String? = null
    var kajur: String? = null
    var kprodi: String? = null
    var wali: String? = null
    var bengkel: String? = null


    constructor(){

    }

    constructor(id: String, nama: String?, nim: String?, kelas: String?, jurusan:String?, studi:String?, kompen:String?, kajur:String?, kprodi:String?, wali:String?, bengkel:String?) {
        this.id = id
        this.nama = nama
        this.nim = nim
        this.kelas = kelas
        this.jurusan = jurusan
        this.studi = studi
        this.kompen = kompen
        this.kajur = kajur
        this.kprodi = kprodi
        this.wali = wali
        this.bengkel = bengkel

    }
}