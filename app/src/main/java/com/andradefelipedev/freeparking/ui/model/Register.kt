package com.andradefelipedev.freeparking.ui.model

import android.os.Parcelable
import com.andradefelipedev.freeparking.helper.FirebaseHelper
import kotlinx.parcelize.Parcelize
import java.sql.Time

@Parcelize
data class Register(
    var id: String = "",
    var nome: String= "",
    var horarioChegada: String,
    var horarioSaida: String,
): Parcelable {
    init {
        this.id = FirebaseHelper.getDatabase().push().key ?: ""
    }
}
