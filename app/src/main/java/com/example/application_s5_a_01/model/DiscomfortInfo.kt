package com.example.application_s5_a_01.model

import com.example.application_s5_a_01.R

data class DiscomfortInfo(
    val code: String,
    val description: String,
    val imageResId: Int
) {
    companion object {
        private val discomfortInfoMap = mapOf(
            "TG21" to DiscomfortInfo("TG21", "La température dépasse 21°C", R.drawable.ic_temperature_high),
            "TL19" to DiscomfortInfo("TL19", "La température est inférieure à 19°C", R.drawable.ic_temperature_low),
            "CG5K" to DiscomfortInfo("CG5K", "La concentration de CO2 dépasse 2000μg/m³", R.drawable.ic_co2_high),
            "DL10" to DiscomfortInfo("DL10", "Le niveau sonore dépasse 10dB", R.drawable.ic_db_high),
            "HG50" to DiscomfortInfo("HG50", "Le taux d'humidité dépasse 50%", R.drawable.ic_humidity_high),
            "PG10" to DiscomfortInfo("PG10", "Le niveau de particules dépasse 10µg/m³", R.drawable.ic_connection_error),
            "BSHP" to DiscomfortInfo("BSHP", "La salle est très polluée", R.drawable.ic_polluted),
            "BSPD" to DiscomfortInfo("BSPD", "Des personnes sont présentes dans la salle", R.drawable.ic_people)
        )

        fun getDiscomfortInfo(discomfortCode: String): DiscomfortInfo? {
            return discomfortInfoMap[discomfortCode]
        }
    }
}