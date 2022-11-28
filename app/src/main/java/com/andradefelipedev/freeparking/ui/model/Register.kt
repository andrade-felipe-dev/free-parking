package com.andradefelipedev.freeparking.ui.model

import android.os.Parcelable
import com.andradefelipedev.freeparking.helper.FirebaseHelper
import kotlinx.parcelize.Parcelize
import java.sql.Time

@Parcelize
data class Register(
    var id: String = "",
    var vaga: String= "",
    var horarioChegada: Time,
    var horarioSaida: Time,
    var livre: Boolean,
    var status: Int = 0
): Parcelable {
    init {
        this.id = FirebaseHelper.getDatabase().push().key ?: ""
    }
}
