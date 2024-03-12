package com.example.application_s5_a_01.model

import com.example.application_s5_a_01.R

data class DiscomfortInfo(
    val code: String,
    val description: String,
    val imageResId: Int
) {
    companion object {
        private val discomfortInfoMap = mapOf(
            "TG21" to DiscomfortInfo("TG21", "Température excède 21°", R.drawable.ic_temperature_high),
            "TL19" to DiscomfortInfo("TL19", "Température baisse 19°", R.drawable.ic_temperature_low),
            "CG5K" to DiscomfortInfo("CG5K", "Concentration de CO2 dépasse 5000°", R.drawable.ic_co2_high),
            "DL10" to DiscomfortInfo("DL10", "Le niveau de décibel dépasse 10 db", R.drawable.ic_db_high),
            "HG50" to DiscomfortInfo("HG50", "Le taux d'humidité dépasse 50%", R.drawable.ic_humidity_high),
            "PG10" to DiscomfortInfo("PG10", "Le niveau de particules dépasse 10 µg/m³", R.drawable.ic_connection_error),
            "BSHP" to DiscomfortInfo("BSHP", "La salle est très polluée", R.drawable.ic_polluted),
            "BSPD" to DiscomfortInfo("BSPD", "Des personnes présentes dans la salle", R.drawable.ic_people)
        )

        fun getDiscomfortInfo(discomfortCode: String): DiscomfortInfo? {
            return discomfortInfoMap[discomfortCode]
        }
    }
}