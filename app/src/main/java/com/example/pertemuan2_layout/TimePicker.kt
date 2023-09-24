package com.example.pertemuan2_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment

class TimePicker: DialogFragment() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(
            requireActivity(),
            activity as TimePickerDialog.OnTimeSetListener,
            hourOfDay,
            minute,
            true
        )
    }
}