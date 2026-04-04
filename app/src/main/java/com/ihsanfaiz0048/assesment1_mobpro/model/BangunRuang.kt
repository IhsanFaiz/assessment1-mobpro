package com.ihsanfaiz0048.assesment1_mobpro.model


data class BangunRuang(
    val nama: String,
    val hitung: (List<Float>) -> HasilBangunRuang
)
